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
    
    /**
     * instantiates a data source without a specified url and field name
     */
    public DataSource()
    {
        url = null;
        fieldName = null;
    }
    
    /**
     * instantiates a data source
     * @param url the url of the DataSource
     * @param fieldName the name of the field
     * @param parent the parent Window for the DataSource
     */
    public DataSource(JTextField url, JTextField fieldName, Window parent)
    {
        this.url = url;
        this.fieldName = fieldName;
        this.parent = parent;
        setSource();
        setField();
    }
    
    /**
     * sets the url as the content in the url text field
     */
    public void setSource()
    {
        this.urlString = url.getText();
    }
    
    /**
     * sets the field name as the content of the field name text field
     */
    public void setField()
    {
        this.fieldString = fieldName.getText();
    }
    
    /**
     * 
     * @return the exchange rate data from the online source 
     */
    public double getData()
    {
            return Double.parseDouble(NetworkFunctions.fetchData(urlString, fieldString, parent));
    }
    
}
