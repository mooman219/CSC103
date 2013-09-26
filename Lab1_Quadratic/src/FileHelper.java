import java.io.File;
import java.io.IOException;

public class FileHelper {
    public static File create(String path) {
        File file = new File(path);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                System.out.println("Error creating file.");
                e.printStackTrace();
            }
        }
        return file;
    }

    public static void delete(String path) {
        File file = new File(path);
        if(!file.exists()) {
            file.delete();
        }
    }

    public static boolean exists(String path) {
        return new File(path).exists();
    }
}