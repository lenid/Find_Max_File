package gran;

public class Folder {

    private String path;
    private long size = 0;
    private boolean containFolder = false;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void addSize(long size) {
        this.size += size;
    }

    public boolean isContainFolder() {
        return containFolder;
    }

    public void setContainFolder(boolean containFolder) {
        this.containFolder = containFolder;
    }
}
