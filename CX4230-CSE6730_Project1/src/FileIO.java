import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class FileIO {

    public static Map<Integer, Data> readData(String file) throws IOException {
        Map<Integer, Data> map = new HashMap<Integer, Data>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                int reportNumber = Integer.parseInt(fields[1]);
                String occurDate = fields[2];
                String occurTime = fields[3];
                String neighborhood = fields[4];
                map.put(id, new Data(id, reportNumber, occurDate, occurTime, neighborhood));
            }
        } catch(Exception e) {
            System.out.println("Error");
        }
        return map;
    }

    public static void writeData(Data data, String targetFile) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(targetFile, true));
        out.println(data.toString());
        out.close();
    }
}


