package main;

import service.LogProcessorService;
import service.impl.LogProcessorServiceImpl;

public class Main {

    static LogProcessorService processorService = new LogProcessorServiceImpl();

    public static void main(String[] args) {
        try {
            processorService.processLogs();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}