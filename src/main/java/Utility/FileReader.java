package Utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    private List<String> data;

    public FileReader() {
        this.data = new ArrayList<>();
    }

    public List<String> getData() {
        return this.data;
    }

    public void read(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(this.getClass().getResource(fileName).getFile()));
            scanner.nextLine(); // reads the file title line
            while (scanner.hasNextLine()) {
                String dataLine = scanner.nextLine();
                this.data.add(dataLine);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
