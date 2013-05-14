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
 * @author Parker
 */
public class NetworkFunctions {
    
    public static String getData(String urlString) {
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
             mue.printStackTrace();
        } 
        catch (IOException ioe) {
             ioe.printStackTrace();
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
    
    public static String parse(String raw, String field)
    {
        int start = raw.indexOf(field) + field.length() + 1;
        start = raw.indexOf(":", start);
        start = raw.indexOf("\"", start) + 1;
        int end = raw.indexOf("\"", start+1);
        return raw.substring(start, end);
    }
    
    public static String fetchData(String url, String field)
    {
        return parse(getData(url), field);
    }
}
