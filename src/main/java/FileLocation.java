
public class FileLocation {
    private int memoryBlockId;
    private int startIndex;
    private int endIndex;

    FileLocation(int memoryBlockId, int startIndex,int endIndex){
        this.memoryBlockId = memoryBlockId;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getMemoryBlockId() {
        return this.memoryBlockId;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

}
