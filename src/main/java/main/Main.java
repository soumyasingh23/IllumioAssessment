package main;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import model.LookupEntry;
import model.LookupKey;
import service.CountService;
import service.LogProcessorService;
import service.MetadataService;
import service.impl.CountServiceImpl;
import service.impl.LogProcessorServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    static LogProcessorService processorService = new LogProcessorServiceImpl();

    public static void main(String[] args) {
        try{
            processorService.processLogs();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}