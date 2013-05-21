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
    private int scale;
    private int start;
    private GraphComponent parent;
    private double ratio;
    private double timeRatio;
    private double max;
    private double min;
    private double range;
    private boolean empty;
    public final double DEFAULT_RANGE = 10;
    private DataSource source;
    
    public DataSet(DataSource source, JTextField scaleField)
    {
        this.set = new ArrayList<DataPoint>();
        this.start = 0;
        this.source = source;
        this.scaleField = scaleField;
        this.empty = true;
    }
    
    public void setParent(GraphComponent parent)
    {
        this.parent = parent;
    }
    
    public void setScale()
    {
        this.scale = (int)(Math.round(Double.parseDouble(scaleField.getText())));
        hardUpdate();
    }
    
    public void update()
    {
        setStartBound();
        setRatio();
        parent.repaint();
    }
    
    public void hardUpdate()
    {
        hardSetStartBound();
        setTimeRatio();
        parent.repaint();
    }
    
    public GraphComponent getParent()
    {
        return parent;
    }
    
    public Calendar getCutOff()
    {
        Calendar temp = Calendar.getInstance();
        temp.add(Calendar.MINUTE, -scale);
        return temp;
    }
    
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
    
    public void hardSetStartBound()
    {
        start = 0;
        setStartBound();
    }
    
    public void add(DataPoint d)
    {
        set.add(d);
        System.out.println("Value:" + d.value);
        update();
    }
    
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
        System.out.println("Max: " + max);
    }
    
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
        System.out.println("Min: " + min);
        
    }
    
    public void setRange()
    {
        range = max - min;
    }
    
    public double getMaximum()
    {
        return max;
    }
    
    public double getMinimum()
    {
        return min;
    }
    
    public double getRange()
    {
        return range;
    }
    
    public double getMid()
    {
        return (min + max)/2;
    }
    
    public int convert(double value)
    {
        return (int)(parent.getUsableHeight() - Math.round((value - min) * ratio));
    }
    
    public int convertTime(Calendar value)
    {
        Calendar now = Calendar.getInstance();
        int days = now.get(Calendar.DAY_OF_YEAR) - value.get(Calendar.DAY_OF_YEAR);
        int hours = days * 24 + now.get(Calendar.HOUR_OF_DAY) - value.get(Calendar.HOUR_OF_DAY);
        int minutes = hours * 60 + now.get(Calendar.MINUTE) - value.get(Calendar.MINUTE);
        int seconds = minutes * 60 + now.get(Calendar.SECOND) - value.get(Calendar.SECOND);
        return (int)(parent.getUsableWidth() - Math.round((double)(seconds) / 60 * timeRatio ));
    }
    
    public void setTimeRatio()
    {
        timeRatio = parent.getUsableWidth() / scale;
    }
    
    public double getTimeRatio()
    {
        return timeRatio;
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
    
    public boolean isEmpty()
    {
        return empty;
    }
    
    public ArrayList<Pair> getPointsInRange()
    {
        ArrayList<Pair> ret = new ArrayList<Pair>();
        if(empty)
        {
            ret.add(new Pair(0, 0));
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
    
}
