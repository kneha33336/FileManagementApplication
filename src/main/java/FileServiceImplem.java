import java.util.*;
import java.io.*;

public class FileServiceImplem implements FileService{
    public int totalMemoryBlockSize;
    int singleMemoryBlockSize;
    List<MemoryBlock> memoryBlock;
    HashMap<String, File> allFiles;

    public  FileServiceImplem(int totalMemoryBlockSize, int singleMemoryBlockSize){
        this.totalMemoryBlockSize = totalMemoryBlockSize;
        this.singleMemoryBlockSize = singleMemoryBlockSize;
        int totalBlocks = totalMemoryBlockSize / singleMemoryBlockSize;
        this.memoryBlock = createMemoryBlocks(totalBlocks, singleMemoryBlockSize);
        this.allFiles = new HashMap<>();
    }

    public List<MemoryBlock> createMemoryBlocks(int totalBlocks, int singleMemoryBlockSize) {
        List<MemoryBlock> allMemoryBlock = new ArrayList<MemoryBlock>();
        for(int singleBlock = 0; singleBlock < totalBlocks; singleBlock++) {
            MemoryBlock newBlock = new MemoryBlock(singleBlock, singleMemoryBlockSize);
            allMemoryBlock.add(newBlock);
        }
        return allMemoryBlock;
    }

    public String createFile(String fileName, String fileData) {
        try {
            if(fileName == null || fileName == "" || fileData == null || fileData == "") {
                System.out.println("Invalid file");
                return "Invalid file";
            }
            byte[] bytes = fileData.getBytes();
            System.out.println(new String(bytes));
            List<MemoryBlock> allAvailableBlocks = new ArrayList<MemoryBlock>();
            int length = getAvailableBlocks(bytes.length, allAvailableBlocks);
            if(length > 0) {
                System.out.println("Can't create file as Memory is full");
                return "Can't create file as Memory is full";
            }
            writeFile(fileName, bytes, allAvailableBlocks);
            return "File created successfully with fileName as " + fileName;
        }catch(Exception ex) {
            System.out.println("createFile ==> ERROR " + ex.toString());
            throw ex;
        }
    }

    private void writeFile(String fileName, byte[] bytes, List<MemoryBlock> allAvailableBlocks) {
        int bytesWritten = 0;
        if(allFiles.containsKey(fileName)) {
            File duplicateFile = allFiles.get(fileName);
            int count = duplicateFile.getCount();
            fileName += "(" + Integer.toString(count + 1) + ")";
            duplicateFile.setCount(count + 1);
            System.out.println("Duplicate file name is + " + fileName);
        }
        System.out.println("write " + new String(bytes));
        File newFile = new File(fileName, bytes.length);
        allFiles.put(fileName, newFile);
        //System.out.println(allAvailableBlocks.size());
        for(MemoryBlock block : allAvailableBlocks) {
            byte[] data = block.getBytes();
            FileLocation location = new FileLocation(block.getmemoryBlockId(), block.getLastUnusedIndex(), block.getLastUnusedIndex());
            for(int i = block.getLastUnusedIndex(); i < block.getEndIndex() + 1; i++) {
                if(bytesWritten == bytes.length) {
                    System.out.println("File successfully written");
                    break;
                }
                data[i] = bytes[bytesWritten];
                bytesWritten += 1;
                block.setLastUnusedIndex(i + 1);
                location.setEndIndex(i);
            }
            newFile.getFileLocation().add(location);
        }
    }


    private int getAvailableBlocks(int length, List<MemoryBlock> allAvailableBlocks){
        for(MemoryBlock block : memoryBlock) {
            if(length <= 0) {
                return length;
            }
            int window = block.getEndIndex() - block.getLastUnusedIndex() + 1;
            if(window > 0) {
                allAvailableBlocks.add(block);
                length -= window;
            }
        }
        return length;
    }

    public String readFile(String fileName) {
        if(fileName == null || fileName == "") {
            System.out.println("Invalid file");
            return "Invalid file";
        }else if(!allFiles.containsKey(fileName)) {
            System.out.println("Cannot read as File is not present in system");
            return "Cannot read as File is not present in system";
        }
        File file = allFiles.get(fileName);
        byte[] data = new byte[file.getSize()];
        int index = 0;
        //System.out.println(file.getFileLocation().size());
        for(FileLocation location : file.getFileLocation()) {
            int id = location.getMemoryBlockId();
            int start = location.getStartIndex();
            int end = location.getEndIndex();
            MemoryBlock currentBlock = memoryBlock.get(id);
            byte[] currentBlockData = currentBlock.getBytes();
            while(start <= end) {
                data[index] = currentBlockData[start];
                start += 1;
                index += 1;
            }
        }
        System.out.println(new String(data));
        return new String(data);
    }

    public String deleteFile(String fileName) {
        if(fileName == null || fileName == "") {
            System.out.println("Invalid file");
            return "Invalid file";
        }else if(!allFiles.containsKey(fileName)) {
            System.out.println("Cannot delete as File is not present in system");
            return "Cannot delete as File is not present in system";
        }

        File file = allFiles.get(fileName);
        for(FileLocation location : file.getFileLocation()) {
            int id = location.getMemoryBlockId();
            int start = location.getStartIndex();
            int end = location.getEndIndex();
            //System.out.println("Delete file with id " + id + " start " + start + " end " + end);
            MemoryBlock currentBlock = memoryBlock.get(id);
            byte[] currentBlockData = currentBlock.getBytes();
            int lastUnusedIndex = currentBlock.getLastUnusedIndex();
            int i;
            for(i = end + 1; i < lastUnusedIndex; i++, start++) {
                //shift
                currentBlockData[start] = currentBlockData[i];

                /** Todo : Update the fileLOcations of all the affected files **/
            }
            currentBlock.setLastUnusedIndex(i);
        }
        allFiles.remove(fileName);
        System.out.println("File deleted successfully");
        return "File deleted successfully";
    }

    public String updateFile(String fileName, String fileData) {
        if(fileName == null || fileName == "") {
            System.out.println("Invalid file");
            return "Invalid file";
        }else if(!allFiles.containsKey(fileName)) {
            System.out.println("Cannot update as File is not present in system");
            return "Cannot update as File is not present in system";
        }

        deleteFile(fileName);
        createFile(fileName, fileData);

        System.out.println("File updated successfully with fileName as " + fileName + " with fileData as " + fileData);
        return "File updated successfully with fileName as " + fileName;
    }
}






















