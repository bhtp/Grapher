package grapher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    
    public static Map<String, String> parse(String raw)
    {
        Map<String, String> temp = new HashMap<String, String>();
        int nameStart;
        int nameEnd;
        int dataStart;
        int dataEnd = raw.indexOf("{");
        while((nameStart = raw.indexOf("\"", dataEnd + 1)+ 1)!=0)
        {
            nameEnd = raw.indexOf("\"", nameStart + 1);
            dataStart = raw.indexOf("\"", nameEnd + 1) + 1;
            dataEnd = raw.indexOf("\"", dataStart + 1);
            temp.put(raw.substring(nameStart, nameEnd), raw.substring(dataStart, dataEnd));
        }
        return temp;
    }
    
    public static Map<String, String> fetchDataSet(String url)
    {
        return parse(getData(url));
    }
}
