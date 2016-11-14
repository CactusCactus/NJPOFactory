/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njpo_fabryka;

/**
 *
 * @author jakub
 */
public class NJPO_Fabryka {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HTMLPageFactory.makePage(HTMLPageFactory.GALLERY_PAGE);
        HTMLPageFactory.makePage(HTMLPageFactory.CONTACT_PAGE);
        HTMLPageFactory.makePage(HTMLPageFactory.INFO_PAGE);
        HTMLPageFactory.makePage(HTMLPageFactory.NEWS_PAGE);
    }
    
}
