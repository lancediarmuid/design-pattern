package compositepattern;

public abstract class FileSystemNode {
    // 节点路径
    protected String path;

    public FileSystemNode(String path){
        this.path = path;
    }

    public abstract int countNumOfFiles();
    public abstract long countSizeOfFiles();

    public String getPath(){
        return path;
    }
}
