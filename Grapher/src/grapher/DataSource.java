package grapher;

import javax.swing.JTextField;

/**
 *
 * @author 308809052
 */
public class DataSource {
    
    private JTextField url;
    private JTextField fieldName;
    
    public DataSource()
    {
        url = null;
        fieldName = null;
    }
    
    public DataSource(JTextField url, JTextField fieldName)
    {
        this.url = url;
        this.fieldName = fieldName;
    }
    
    public void setSource(JTextField url)
    {
        this.url = url;
    }
    
    public void setField(JTextField field)
    {
        this.fieldName = field;
    }
    
    public double getData()
    {
        return Double.parseDouble(NetworkFunctions.fetchData(url.getText(), fieldName.getText()));
    }
    
}
