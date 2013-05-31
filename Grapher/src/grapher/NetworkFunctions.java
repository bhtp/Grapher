package grapher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
/**
 *
 * @author Parker, Jonah
 */
public class NetworkFunctions {
    
    /**
     * gets data from online source
     * @param urlString the source of the data
     * @param parent the Window parent
     * @return the data acquired from the online source as a String
     */
    public static String getData(String urlString, Window parent) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;
        String out;
        out = "";
        try {
            url = new URL(urlString);
            URLConnection urlConn = url.openConnection();
            urlConn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            is = urlConn.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                out += line;
            }
        } 
        catch (MalformedURLException mue) {
             System.out.println("Invalid URL Entered");
             parent.pauseWithButton();
        } 
        catch (IOException ioe) {
             System.out.println("Problem Retreiving Data");
             parent.pauseWithButton();
        } 
        finally {
                try {
                    is.close();
                     } 
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
        }
        return out;
    }
    
    /**
     * parses the String for a specific field
     * @param raw the String to parse
     * @param field the field to parse for
     * @return the parsed String
     */
    public static String parse(String raw, String field)
    {
        int start = raw.indexOf(field) + field.length() + 1;
        start = raw.indexOf(":", start);
        start = raw.indexOf("\"", start) + 1;
        int end = raw.indexOf("\"", start+1);
        return raw.substring(start, end);
    }
    
    /**
     * fetches data from online source
     * @param url the online source
     * @param field the field to look for
     * @param parent the Window parent
     * @return a parsed version of the acquired data
     */
    public static String fetchData(String url, String field, Window parent)
    {
        return parse(getData(url, parent), field);
    }
}
