import java.util.*;

public class File {
    private String fileName;
    private List<FileLocation> fileLocation;
    private int count;
    private int size;

    File(String fileName, int size){
        this.fileName = fileName;
        this.fileLocation = new ArrayList<FileLocation>();
        this.count = 0;
        this.size =  size;;
    }

    public String getFileName() {
        return this.fileName;
    }

    public List<FileLocation> getFileLocation(){
        return this.fileLocation;
    }

    public int getCount() {
        return this.count;
    }

    public int getSize() {
        return this.size;
    }

    public int setCount(int count) {
        return this.count = count;
    }

    public void setFileLocation(List<FileLocation> fileLocation){
        this.fileLocation = fileLocation;
    }

}
