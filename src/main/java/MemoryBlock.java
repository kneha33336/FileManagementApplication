
public class MemoryBlock {
    private int memoryBlockId;
    private int startIndex;
    private int endIndex;
    private int lastUnusedIndex;
    private byte[] data;
    private boolean isAvailable;

    MemoryBlock(int memoryBlockId, int singleMemoryBlockSize){
        this.memoryBlockId = memoryBlockId;
        this.startIndex = 0;
        this.endIndex = singleMemoryBlockSize - 1;
        this.lastUnusedIndex = 0;
        this.data = new byte[singleMemoryBlockSize];
    }

    public int getmemoryBlockId() {
        return this.memoryBlockId;
    }

    public int getLastUnusedIndex() {
        return this.lastUnusedIndex;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public byte[] getBytes() {
        return this.data;
    }

    public void setLastUnusedIndex(int lastUnusedIndex) {
        this.lastUnusedIndex = lastUnusedIndex;
    }

    public boolean getIsAvailable(){return this.isAvailable;}

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
