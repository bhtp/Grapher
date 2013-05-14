package grapher;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author 308809052
 */
public class DataSet {
    
    private ArrayList<DataPoint> set;
    private int scale;
    private int start;
    private GraphComponent parent;
    private double ratio;
    private final int DEFAULT_SCALE = 15;
    
    public DataSet(GraphComponent parent)
    {
        this.set = new ArrayList<DataPoint>();
        this.scale = DEFAULT_SCALE;
        this.parent = parent;
        this.start = 0;
    }
    
    public void setScale(int scale)
    {
        this.scale = scale;
        hardUpdate();
    }
    
    public void update()
    {
        setStartBound();
        parent.repaint();
    }
    
    public void hardUpdate()
    {
        hardSetStartBound();
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
        while(set.get(start).getTime().before(cutOff) && start < set.size())
            start++;
    }
    
    public void hardSetStartBound()
    {
        start = 0;
        setStartBound();
    }
    
    public void add(DataPoint d)
    {
        set.add(d);
        update();
    }
    
    public double getMaximum()
    {
        double max = Double.MIN_VALUE;
        for(int i = start; i < set.size(); i++)
        {
            double temp = set.get(i).getValue();
            if(temp < max)
            {
                max = temp;
            }
        }
        return max;
    }
    
    public double getMinimum()
    {
        double min = Double.MAX_VALUE;
        for(int i = start; i < set.size(); i++)
        {
            double temp = set.get(i).getValue();
            if(temp < min)
            {
                min = temp;
            }
        }
        return min;
    }
    
    public double getRange()
    {
        return getMaximum() - getMinimum();
    }
    
    public int convert(double value)
    {
        return (int)(Math.round(value * ratio));
    }
    
    public void setRatio()
    {
        ratio = parent.getHeight() / getRange();
    }
    
    public double getRatio()
    {
        return ratio;
    }
    
    public int [] getPointsInRange()
    {
        int [] temp = new int[set.size() - start];
        int point = 0;
        int loc = start;
        while(loc < set.size())
        {
            temp[point] = convert(set.get(loc).getValue());
        }
        return temp;
    }
    
}
