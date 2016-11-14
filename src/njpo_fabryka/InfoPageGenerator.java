/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njpo_fabryka;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jakub
 */
public class InfoPageGenerator extends PageGenerator {
    
    private StringBuilder sb;
    private ArrayList<String> randomWords;
    
    public InfoPageGenerator() {
        sb = new StringBuilder();
        randomWords = new ArrayList();
    }

    public String buildPage() {
        String html = "";
        fillRandomWords();
        addBeggining(sb);
        addText();
        addEnding(sb);
        System.out.println(sb.toString());
        buildHTML(sb.toString(), "info");
        return html;
    }
        public void fillRandomWords() {
        String text = "";
        try {
            BufferedReader in  = new BufferedReader(new InputStreamReader(new FileInputStream("data/text.txt"),"UTF-8"));
            String line;
                while((line = in.readLine()) != null) {
                    text = text + line;
                }
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InfoPageGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InfoPageGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        text = text.replaceAll("[^\\w& ]", "");
        String[] words = text.split(" ");
        
        words = randomizeWords(words);
        for(int i = 0; i < words.length; i++) {
            randomWords.add(words[i]);
        }
    }
    private String[] randomizeWords(String[] words) {
        Random random = new Random();
        for (int i = words.length - 1; i > 0; i--)
        {
          int index = random.nextInt(i + 1);
          String a = words[index];
          words[index] = words[i];
          words[i] = a;
        }
        return words;
    }
    
    private void addText() {
        sb.append("<p> ");
        for(String s: randomWords) {
            sb.append(s + " ");
        }
        sb.append("</p>");
    }
}
