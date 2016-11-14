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
public class HTMLPageFactory {
    public static final int GALLERY_PAGE = 0;
    public static final int CONTACT_PAGE = 1;
    public static final int INFO_PAGE = 2;
    public static final int NEWS_PAGE = 3;
    
    public static String makePage(int type) {
        switch(type) {
            case 0: return new GalleryPageGenerator().buildPage();
            case 1: return new ContactDataGenerator().buildPage();
            case 2: return new InfoPageGenerator().buildPage();
            case 3: return new NewsPageGenerator().buildPage();
            default: return null;
        }
    }
}
