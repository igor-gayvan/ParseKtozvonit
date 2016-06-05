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
    private final static Pattern pattern = Pattern.compile("\\+?(?<country>\\d{1,3})?\\s*?\\((?<oper>\\d{2,3})\\)\\s*(?<number>\\d{3}(\\-\\d{2}){2})");

    private static final String FOLDER_FOR_SAVE = "./data";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException {
        String allText = "";

        URL site = new URL(URL_FOR_PARSE);

        List<Phone> phoneList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(site.openStream()))) {
            for (String line; (line = reader.readLine()) != null;) {
                for (Matcher matcher = pattern.matcher(line); matcher.find();) {
                    String numberRaw = matcher.group();
                    String country = matcher.group("country");
                    String operator = matcher.group("oper");
                    String number = matcher.group("number");

                    phoneList.add(new Phone(numberRaw, country, operator));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        File saveFolder = new File(FOLDER_FOR_SAVE);
                saveFolder.mkdir();
        
        String fileName = "phone_list.txt";

        try (FileOutputStream fos = new FileOutputStream(new File(saveFolder, fileName))) {
            for (Phone ph : phoneList) {
                String convert = ph.convert();
                
                fos.write(convert.getBytes());
                fos.write('\n');
            }
            fos.close();

            System.out.printf("Phone's list save!%n");
            System.out.printf("Count: %d%n%n", phoneList.size());

        } catch (IOException ex) {
            System.err.println("Невозможно создать файл списка телефонов");
        }

    }
}
