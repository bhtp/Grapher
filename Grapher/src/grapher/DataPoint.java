package grapher;

import java.util.Calendar;



/**
 *
 * @author 308809052
 */
public class DataPoint {
    
    public double value;
    public Calendar time;
    
    public DataPoint(double value)
    {
        this.value = value;
        this.time = Calendar.getInstance();
    }
    
    public DataPoint(double value, long time)
    {
        this.value = value;
        this.time = Calendar.getInstance();
        this.time.setTimeInMillis(time);
    }
    
    public double getValue()
    {
        return value;
    }
    
    public long getTimeNum()
    {
        return time.getTime().getTime();
    }
    
    public Calendar getTime()
    {
        return time;
    }
    
    public String getTimeString()
    {
        return time.get(Calendar.HOUR_OF_DAY) + " : " + time.get(Calendar.MINUTE);
    }
}
