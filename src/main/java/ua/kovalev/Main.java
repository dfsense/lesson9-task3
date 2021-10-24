package ua.kovalev;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String text = null;
        try (FileInputStream fio = new FileInputStream("test.txt")){
            byte[] bytes = new byte[fio.available()];
            fio.read(bytes);
            text = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ServiceAnalysisText serviceAnalysisText = new ServiceAnalysisText(text);
        serviceAnalysisText.goAnalysis();

    }
}
