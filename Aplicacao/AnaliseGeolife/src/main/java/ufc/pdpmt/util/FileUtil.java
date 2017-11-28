package ufc.pdpmt.util;


import ufc.pdpmt.model.Point;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtil {

    public static void writeResults(String nameFile, List<Point> dataSet) {
        File file = new File("./resultados/" + nameFile);
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (Point point: dataSet) {
                writer.write(point.toCSV()+"\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
