/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njpo_fabryka;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jakub
 */
public class ContactDataGenerator extends PageGenerator {
    
    private String randomNumber;
    private String randomAddress;
    private String randomName;
    
    private StringBuilder sb;
    
    public ContactDataGenerator() {
        sb = new StringBuilder();
    }
    private void fillNumber() {
        Random random = new Random();
        randomNumber = "";
        for(int i = 0; i < 6; i ++) {
            randomNumber += random.nextInt(10);
        }
    }
    private void fillAddress() {
        Random random = new Random();
        String text = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("data/addresses.txt"),"UTF-8"));
            String line;
                while((line = in.readLine()) != null) {
                    text = text + line + ".";
                }
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContactDataGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContactDataGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] lines = text.split("\\.");
        String address = lines[random.nextInt(lines.length)];
        String[] split = address.split(", ");
        randomAddress = split[0] + " " + random.nextInt(100) + " " + split[1];
    }
    private void fillName() {
        Random random = new Random();
        String text = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader("data/names.txt"));
            String line;
                while((line = in.readLine()) != null) {
                    text = text + line + ".";
                }
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContactDataGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContactDataGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] lines = text.split("\\.");
        randomName = lines[random.nextInt(lines.length)];
    }
    public String buildPage() {
        String html = "";
        fillName();
        fillNumber();
        fillAddress();
        addBeggining(sb);
        addContactData();
        addEnding(sb);
        System.out.println(sb.toString());
        buildHTML(sb.toString(), "contact");
        return html;
    }
    private void addContactData() {
        sb.append("<h1>" + "Imie: ").append(randomName).append("</h1>");
        sb.append("<p>" + "Adres: ").append(randomAddress).append("</p>");
        sb.append("<p>" + "Numer telefonu: ").append(randomNumber).append("</p>");
    }
}
