package grapher;

import javax.swing.JTextField;

/**
 *
 * @author 308809052
 */
public class DataSource {
    
    private JTextField url;
    private JTextField fieldName;
    private String urlString;
    private String fieldString;
    private Window parent;
    
    public DataSource()
    {
        url = null;
        fieldName = null;
    }
    
    public DataSource(JTextField url, JTextField fieldName, Window parent)
    {
        this.url = url;
        this.fieldName = fieldName;
        this.parent = parent;
        setSource();
        setField();
    }
    
    public void setSource()
    {
        this.urlString = url.getText();
    }
    
    public void setField()
    {
        this.fieldString = fieldName.getText();
    }
    
    public double getData()
    {
            return Double.parseDouble(NetworkFunctions.fetchData(urlString, fieldString, parent));
    }
    
}
