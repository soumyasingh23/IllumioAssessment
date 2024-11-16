package service;

import constant.FileConstants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static constant.FileConstants.*;


public class ReaderService {
    InputStream inputStream;
    BufferedReader reader;

    public ReaderService() {
         inputStream = ReaderService.class.getClassLoader().getResourceAsStream(FileConstants.LOG_FILE);
         reader = new BufferedReader(new InputStreamReader(inputStream));
    }


    public List<String[]> readFile(String filePath, String separator) {
        List<String[]> lines = new ArrayList<>();
        try  {
            InputStream inputStream = ReaderService.class.getClassLoader().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            reader.readLine();
            String line;
            while((line = reader.readLine())!=null) {
                String[] values = line.split(separator);
                lines.add(values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    public List<String[]> readLogFile(int batchSize) {
        List<String[]> list = new ArrayList<>();
        try  {
            String line;
            int read = 0;

            while(read < batchSize && (line = reader.readLine())!=null) {
                String[] values = line.split(SPACE_SEPARATOR);
                list.add(values);
                read++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
