package grapher;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JTextField;

/**
 *
 * @author 308809052
 */
public class DataSet {
    
    private ArrayList<DataPoint> set;
    private JTextField scaleField;
    private double scale;
    private int start;
    private GraphComponent parent;
    private double ratio;
    private double timeRatio;
    private double max;
    private double min;
    private double range;
    private boolean empty;
    private Calendar now;
    private Calendar axisNow;
    private DataSource source;
    
    /**
     * instantiates a DataSet
     * @param source the source of the exchange rate values
     * @param scaleField the text field in which the scale for the x-axis is entered
     */
    public DataSet(DataSource source, JTextField scaleField)
    {
        this.set = new ArrayList<DataPoint>();
        this.start = 0;
        this.source = source;
        this.scaleField = scaleField;
        this.empty = true;
    }
    
    /**
     * sets the GraphComponent parent
     * @param parent the associated GraphComponent
     */
    public void setParent(GraphComponent parent)
    {
        this.parent = parent;
    }
    
    /**
     * Adjusts the x-axis scale based on the content of scaleField
     */
    public void setScale()
    {
        try {
            scale = Double.parseDouble(scaleField.getText());
        }
        catch(NumberFormatException e)
        {
            System.out.println("Invalid Scale Entered");
        }
    }
    
    /**
     * refreshes the graph with new information
     */
    public void update()
    {
        setStartBound();
        setRatio();
        parent.repaint();
    }
    
    /**
     * 
     */
    public void hardUpdate()
    {
        hardSetStartBound();
        setTimeRatio();
        parent.repaint();
    }
    
    /**
     * 
     * @return the GraphComponent parent
     */
    public GraphComponent getParent()
    {
        return parent;
    }
    
    /**
     * returns the cutoff time of the x-axis
     * @return cutoff time as a Calender object
     */
    public Calendar getCutOff()
    {
        Calendar temp = Calendar.getInstance();
        temp.add(Calendar.MINUTE, -(int)(Math.round((scale * 60))));
        return temp;
    }
    
    /**
     * sets start to the last point before the earliest one on the graph
     */
    public void setStartBound()
    {
        Calendar cutOff = getCutOff();
        while(start < set.size() && set.get(start).getTime().before(cutOff))
            start++;
        if(start == set.size())
        {
            empty = true;
        }
        else
        {
            empty = false;
        }
    }
    
    /**
     * sets start to the last point before the earliest one on the graph 
     * begins at the first DataPoint
     */
    public void hardSetStartBound()
    {
        start = 0;
        setStartBound();
    }
    
    /**
     * adds a DataPoint and updates the graph
     * @param d the DataPoint to be added
     */
    public void add(DataPoint d)
    {
        set.add(d);
        update();
    }
    
    /**
     * determines the greatest value in the DataSet
     */
    public void setMaximum()
    {
        max = Double.MIN_VALUE;
        for(int i = start; i < set.size(); i++)
        {
            double temp = set.get(i).getValue();
            if(temp > max)
            {
                max = temp;
            }
        }
    }
    
    /**
     * determines the smallest value in the DataSet
     */
    public void setMinimum()
    {
        min = Double.MAX_VALUE;
        for(int i = start; i < set.size(); i++)
        {
            double temp = set.get(i).getValue();
            if(temp < min)
            {
                min = temp;
            }
        }   
    }
    
    /**
     * determines the difference between the maximum and minimum values
     */
    public void setRange()
    {
        range = max - min;
    }
    
    /**
     * 
     * @return the maximum value in the DataSet 
     */
    public double getMaximum()
    {
        return max;
    }
    
    /**
     * 
     * @return the minimum value in the DataSet
     */
    public double getMinimum()
    {
        return min;
    }
    
    /**
     * 
     * @return the difference between the maximum and minimum values of the DataSet 
     */
    public double getRange()
    {
        return range;
    }
    
    /**
     * 
     * @return the average of the maximum and minimum values of the DataSet 
     */
    public double getMid()
    {
        return (min + max)/2;
    }
    
    /**
     * determines the location on the graph a value should be located
     * @param value the value to be placed on the graph
     * @return the y value of the location of the value 
     */
    public int convert(double value)
    {
        if(range == 0)
        {
            return (int)(Math.round(parent.getUsableHeight()/2));
        }
        return (int)(parent.getUsableHeight() - Math.round((value - min) * ratio));
    }
    
    /**
     * determines the pixel value at which a Calender object should be rendered on the graph
     * @param value the Calender object to be placed on the graph
     * @return the x pixel value that the object should be rendered at
     */
    public int convertTime(Calendar value)
    {
        int days = now.get(Calendar.DAY_OF_YEAR) - value.get(Calendar.DAY_OF_YEAR);
        int hours = days * 24 + now.get(Calendar.HOUR_OF_DAY) - value.get(Calendar.HOUR_OF_DAY);
        int minutes = hours * 60 + now.get(Calendar.MINUTE) - value.get(Calendar.MINUTE);
        int seconds = minutes * 60 + now.get(Calendar.SECOND) - value.get(Calendar.SECOND);
        return (int)(parent.getUsableWidth() - Math.round((double)(seconds) / 3600 * timeRatio ) + parent.getYAxisWidth());
    }
    
    /**
     * determines the width to be allowed to each time on the graph
     */
    public void setTimeRatio()
    {
        timeRatio = parent.getUsableWidth() / scale;
    }
    
    /**
     * 
     * @return the width to be allowed to each time on the graph
     */
    public double getTimeRatio()
    {
        return timeRatio;
    }
    
    /**
     * determines the current time
     */
    public void setNow()
    {
        now = Calendar.getInstance();
        axisNow = (Calendar)(now.clone());
    }
    
    /**
     * 
     * @param LABELS
     * @return the new time that the x-axis is pointing to 
     */
    public String getTimeAt()
    {
        if(scale <= 24)
        {
            return getTime();
        }
        else
        {
            return axisNow.get(Calendar.DAY_OF_MONTH) + "/" + axisNow.get(Calendar.MONTH) + " " + getTime();
        }
    }
    
    public void nextTime(int LABELS)
    {
        axisNow.add(Calendar.MINUTE, -(int)(Math.round((scale / LABELS) * 60)));
    }
    
    public String getTime()
    {
        return pad(Integer.toString(axisNow.get(Calendar.HOUR_OF_DAY))) + " : " + pad(Integer.toString(axisNow.get(Calendar.MINUTE)));
    }
    
    public static String pad(String raw)
    {
        if(raw.length() < 2)
        {
            return "0" + raw;
        }
        return raw;
    }
            
    public void setRatio()
    {
        setMaximum();
        setMinimum();
        setRange();
        ratio = parent.getUsableHeight() / range;
    }
    
    public double getRatio()
    {
        return ratio;
    }
    
    public boolean isFlat()
    {
        return (range == 0);
    }
    
    public boolean isEmpty()
    {
        return empty;
    }
    
    public ArrayList<Pair> getPointsInRange()
    {
        ArrayList<Pair> ret = new ArrayList<Pair>();
        if(empty)
        {
            ret.add(new Pair((int)(parent.getUsableWidth() + parent.getYAxisWidth()), (int)(Math.round(parent.getUsableHeight()/2))));
        }
        for(int i = start; i < set.size(); i++)
        {
            DataPoint temp = set.get(i);
            ret.add(new Pair(convertTime(temp.getTime()), convert(temp.getValue())));
        }           
        return ret;
    }
    
    public void fetchData()
    {
        add(new DataPoint(source.getData()));
    }
    
    public void clear()
    {
        set.clear();
        hardUpdate();
        update();
    }
    
}
