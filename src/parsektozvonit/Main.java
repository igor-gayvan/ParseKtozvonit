/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsektozvonit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Igor Gayvan
 */
public class Main {

    private final static String URL_FOR_PARSE = "http://ktozvonit.com.ua/operators/067/15/0000000-0009999";
    private final static Pattern pattern = Pattern.compile("\\((?<oper>\\d{3})\\)\\s*(?<number>\\d{3}(\\-?\\d{2}){2})");
    private final static String DEFAULT_CODE_COUNTRY = "+38";
    private static final String FOLDER_FOR_SAVE = "./data";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException {
        String allText = "";

        URL site = new URL(URL_FOR_PARSE);

        Phone phone = new Phone();
        List<Phone> phoneList = new ArrayList<>();

        if (site != null) {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new InputStreamReader(site.openStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);

                    while (matcher.find()) {
                        String numberRaw = matcher.group();
                        String operator = matcher.group("oper");
                        String number = matcher.group("number");

                        phoneList.add(new Phone(numberRaw));
                    }
                }
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            new File(FOLDER_FOR_SAVE).mkdir();
            String fileName = FOLDER_FOR_SAVE + "/phone_list.txt";

            try (FileOutputStream fos = new FileOutputStream(fileName, false)) {
                int cntPhones = 0;

                for (Phone ph : phoneList) {
                    fos.write(ph.getNumberRaw().getBytes());
                    fos.write('\n');

                    cntPhones++;
                }
                fos.close();

                System.out.printf("Phone's list save!%n");
                System.out.printf("Count: %d%n%n", cntPhones);

            } catch (IOException ex) {
                System.err.println("Невозможно создать файл списка телефонов");
            }
        }

    }
}


