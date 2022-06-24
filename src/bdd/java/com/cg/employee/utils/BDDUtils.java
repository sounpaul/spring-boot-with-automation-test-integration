package com.cg.employee.utils;

import com.opencsv.CSVWriter;
import net.serenitybdd.core.Serenity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

public class BDDUtils {

    private static Logger logger = LoggerFactory.getLogger(BDDUtils.class);

    public static int randomIdGenerator() {
        Random random = new Random();
        int id = random.nextInt(5000);
        System.out.println("Generated ID : " + id);
        return id;
    }

    public static void writeTestResult(String scenarioName, String scenarioResult) {
        File file = new File("src\\bdd\\resources\\results.csv");
        try {
            FileWriter outputFile = new FileWriter(file, true);
            String[] csvData = new String[3];
            csvData[0] = scenarioName;
            csvData[1] = scenarioResult;
            CSVWriter writer = new CSVWriter(outputFile);
            System.out.println("Appended <" + scenarioName + "," + scenarioResult + "> to " + file.getPath());
            writer.writeNext(csvData);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return sdf.format(date);
    }
}
