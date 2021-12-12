package businessLogicLayer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Aceasta clasa este pentru scrierea intr-un fisier text
 * Created by d.rona on 10.05.2019.
 */
public class ReportUtil {

    /**
     * Aceasta metoda scrie linie cu linie intr-un fisier 
     * @param filename - unde va scrie
     * @param line - linia
     */
    public static void writeLineToFile(String filename, String line) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true));
            writer.write(line);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFileContent(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
