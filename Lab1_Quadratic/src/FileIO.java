import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
    private File file;
    private ArrayList<String> body = new ArrayList<String>();

    public FileIO(String s) {
        file = new File(s);
        setUpFile(file);
    }

    public FileIO(File f) {
        setUpFile(f);
    }

    public FileIO setUpFile(File f) {
        file = f;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            body = getRawFile();
            updateFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public void wipeFile() {
        body.clear();
        updateFile();
    }

    public ArrayList<String> writeAdd(String inputStr) {
        body.add(inputStr);
        return updateFile();
    }

    public String getBodyLine(int lineNumber) {
        if (lineNumber >= body.size() || lineNumber < 0) {
            return "";
        }
        return body.get(lineNumber);
    }

    public ArrayList<String> getBody() {
        return body;
    }

    public ArrayList<String> getRawFile() {
        ArrayList<String> text = new ArrayList<String>();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                text.add(reader.nextLine());
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error Scanning File...");
            e.printStackTrace();
        }
        return text;
    }

    public ArrayList<String> reloadBody() {
        body = getRawFile();
        return body;
    }

    public ArrayList<String> updateFile() {
        try {
            PrintStream input = new PrintStream(file);
            for (String a : body) {
                input.println(a);
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error Updating File...");
            e.printStackTrace();
        }
        return body;
    }
}