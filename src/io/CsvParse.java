package io;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Created by lijin9 on 2018/7/4.
 */
public class CsvParse {


    public static void main(String[] args) throws Exception{

        String path = "C:\\Users\\lijin9\\Desktop\\data.csv";
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, "UTF-8");
            sc.useDelimiter("\\n");
            if(sc.hasNextLine()){
                String firstLine = sc.nextLine();
                System.out.println(firstLine);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    System.out.println(line);
                }
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
    }
}
