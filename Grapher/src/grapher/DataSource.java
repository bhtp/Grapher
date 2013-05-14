package grapher;

/**
 *
 * @author 308809052
 */
public class DataSource {
    
    private String url;
    private String fieldName;
    
    public DataSource()
    {
        url = null;
        fieldName = null;
    }
    
    public DataSource(String url, String fieldName)
    {
        this.url = url;
        this.fieldName = fieldName;
    }
    
    public void setSource(String url)
    {
        this.url = url;
    }
    
    public void setField(String field)
    {
        this.fieldName = field;
    }
    
    public double getData()
    {
        return Double.parseDouble(NetworkFunctions.fetchData(url, fieldName));
    }
    
}
