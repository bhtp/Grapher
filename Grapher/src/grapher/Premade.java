package grapher;

import java.io.Serializable;

/**
 *
 * @author Parker
 */
public class Premade implements Serializable{
    public String url;
    public String urlField;
    
    /**
     * creates a premade source to acquire data from 
     * @param url the source of the data
     * @param urlField the field to look for when parsing data
     */
    public Premade(String url, String urlField)
    {
        this.url = url;
        this.urlField = urlField;
    }
    
    /**
     * 
     * @return the url field at the url 
     */
    public String toString()
    {
        return urlField + "@" + url; 
    }
}
