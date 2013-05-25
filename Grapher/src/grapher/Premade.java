package grapher;

/**
 *
 * @author Parker
 */
public class Premade {
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
