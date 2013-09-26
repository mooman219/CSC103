import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO {
    private File file;
    private List<String> body = new ArrayList<String>();

    public FileIO(File file) {
        this.file = file;
        create();
        reload();
    }

    public FileIO(String path) {
        this(new File(path));
    }

    public void addLine(String inputStr) {
        body.add(inputStr);
        write();
    }

    public void addLines(List<String> lines) {
        body.addAll(lines);
        write();
    }

    public void clear() {
        body.clear();
        write();
    }

    public void create() {
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                System.out.println("Error creating file.");
                e.printStackTrace();
            }
        }
    }

    public void delete() {
        if(!file.exists()) {
            file.delete();
        }
    }

    public boolean exists() {
        return file.exists();
    }

    public List<String> getBody() {
        return body;
    }

    public File getFile() {
        return file;
    }

    public String getLine(int lineNumber) {
        if(lineNumber >= body.size() || lineNumber < 0) {
            return "";
        }
        return body.get(lineNumber);
    }

    public ArrayList<String> getRawBody() {
        ArrayList<String> text = new ArrayList<String>();
        try(Scanner reader = new Scanner(file)) {
            while(reader.hasNextLine()) {
                text.add(reader.nextLine());
            }
        } catch(Exception e) {
            System.out.println("Error reading File.");
            e.printStackTrace();
        }
        return text;
    }

    public void reload() {
        body = getRawBody();
    }

    public void write() {
        try(PrintStream input = new PrintStream(file)) {
            for(String a : body) {
                input.println(a);
            }
        } catch(Exception e) {
            System.out.println("Error writing File.");
            e.printStackTrace();
        }
    }
}