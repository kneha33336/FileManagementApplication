import java.security.SecureRandom;

public interface FileService {
    public String createFile(String filename, String data);
    public String updateFile(String filename, String data);
    public String readFile(String filename);
    public String deleteFile(String filename);
}
