/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njpo_fabryka;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jakub
 */
public class PageGenerator {
    
    protected void buildHTML(String page, String name) {
        try {
            PrintWriter out = new PrintWriter("results/" +  name + ".html");
            out.print(page);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PageGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected StringBuilder addBeggining(StringBuilder sb) {
        return sb.append("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<body>\n");
    }
    protected StringBuilder addEnding(StringBuilder sb) {
        return sb.append("</body>\n" +
                    "</html>");
    }
    
}
