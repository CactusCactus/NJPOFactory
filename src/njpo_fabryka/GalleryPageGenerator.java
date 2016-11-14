/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njpo_fabryka;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jakub
 */
public class GalleryPageGenerator extends PageGenerator{
    private ArrayList<String> imageSources;
    private StringBuilder sb;
    
    public GalleryPageGenerator() {
        imageSources = new ArrayList();
        sb = new StringBuilder();
    }
    private void fillImageSources() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("data/image_sources.txt"));
            String line;
                while((line = in.readLine()) != null) {
                    imageSources.add(line);
                }
            in.close();

        }   catch (FileNotFoundException ex) {
            Logger.getLogger(GalleryPageGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GalleryPageGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void addImages() {
        Random random = new Random();
        for(int i = 0; i < 3; i ++) {
            sb.append("<img src=\"").append(imageSources.get(random.nextInt(imageSources.size()))).append("\" ");
            sb.append("height=\"350\" width=\"250\"\n" + "\">" + "\n");
        }
    }
    public String buildPage() {
        String html = "";
        fillImageSources();
        addBeggining(sb);
        addImages();
        addEnding(sb);
        System.out.println(sb.toString());
        buildHTML(sb.toString(), "gallery");
        return html;
    }
}
