package grapher;

import java.io.Serializable;

/**
 *
 * @author Parker
 */
public class Premade implements Serializable{
    public String url;
    public String urlField;
    
    public Premade(String url, String urlField)
    {
        this.url = url;
        this.urlField = urlField;
    }
    
    public String toString()
    {
        return urlField + "@" + url; 
    }
}
