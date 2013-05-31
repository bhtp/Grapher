package grapher;

import java.util.Calendar;



/**
 *
 * @author 308809052, 308516582
 */
public class DataPoint {
    
    public double value;
    public Calendar time;
    
    /**
     * instantiates a new data point
     * @param value the value of a Bitcoin in US dollars 
     */
    public DataPoint(double value)
    {
        this.value = value;
        this.time = Calendar.getInstance();
    }
    
    /**
     * instantiates a new data point
     * @param value the value of a Bitcoin in US dollars
     * @param time the time of the corresponding value
     */
    public DataPoint(double value, long time)
    {
        this.value = value;
        this.time = Calendar.getInstance();
        this.time.setTimeInMillis(time);
    }
    
    /**
     * 
     * @return value 
     */
    public double getValue()
    {
        return value;
    }
    
    /**
     * 
     * @return the time of the DataPoint as a number of milliseconds 
     */
    public long getTimeNum()
    {
        return time.getTime().getTime();
    }
    
    /**
     * 
     * @return the time of the DataPoint as a Calender object
     */
    public Calendar getTime()
    {
        return time;
    }
    
    /**
     * 
     * @return the time of the DataPoint as a String 
     */
    public String getTimeString()
    {
        return time.get(Calendar.HOUR_OF_DAY) + " : " + time.get(Calendar.MINUTE);
    }
}
