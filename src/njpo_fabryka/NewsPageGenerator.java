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
public class NewsPageGenerator extends PageGenerator{
    private ArrayList<String> randomWords;
    private ArrayList<String> randomNews;

    private StringBuilder sb;
    
    public NewsPageGenerator() {
        sb = new StringBuilder();
        randomWords = new ArrayList();
        randomNews = new ArrayList();
    }

    public String buildPage() {
        String html = "";
        fillRandomWords();
        fillRandomNews();
        addBeggining(sb);
        addNews();
        addEnding(sb);
        System.out.println(sb.toString());
        buildHTML(sb.toString(), "news");
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
            Logger.getLogger(NewsPageGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NewsPageGenerator.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void fillRandomNews() {
        if(randomWords.size() > 0) {
            int maxNumOfNews = randomWords.size()/100;
            int numOfNews = 0;
            String news = "";
            for(int i = 0; i < randomWords.size(); i ++){
                news += randomWords.get(i) + " ";
                if(i%100 == 0 && i != 0) {
                    numOfNews ++;
                    randomNews.add(news);
                    news = "";
                    if(numOfNews >= maxNumOfNews) break;
                }
            }
        }
    }
    private void addNews() {
        for(int i = 0; i < randomNews.size(); i ++) {
            String[] words = randomNews.get(i).split(" ");
            String title = words[0] + " " + words[1];
            sb.append("<h1>").append(title).append("</h1>");
            sb.append("<p>").append(randomNews.get(i)).append("</p>");
        }
    }
}
