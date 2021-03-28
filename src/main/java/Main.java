public class Main {
    public static void main(String[] args) {

        FileService storage = new FileServiceImplem(100, 10);
        storage.createFile("", "My first test file");
        storage.createFile(null, "My first test file");
        storage.createFile("file1", "My first test file 1");
        storage.readFile("file1");
        //storage.deleteFile("file1");
        //storage.readFile("file1");
        storage.createFile("file1", "My first test file 2");
        storage.readFile("file1(1)");
        //storage.deleteFile("file1(1)");
        storage.readFile("file1(1)");
        storage.readFile("file1");
        storage.updateFile("file1", "My first updated test file");
        storage.readFile("file1(1)");
        storage.readFile("file1");
        //storage.deleteFile("file1");
        storage.readFile("file1");
        //Memory full check
        storage.updateFile("file1", "veryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");

    }
}
