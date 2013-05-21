package grapher;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author 308809052
 */
public class GraphComponent extends JComponent{
    
   DataSet set;
   YAxis yAxis;
    
    public void init(DataSet set)
    {
        this.set = set;
        this.set.setParent(this);
        this.yAxis = new YAxis(25, set, this);
    }
    
    public int getUsableHeight()
    {
        return getHeight() - 10;
    }
    
    public int getUsableWidth()
    {
        return getWidth() - yAxis.getWidth();
    }
    
    public static void paintGraph(Graphics2D g2, ArrayList<Pair> points)
    {
        if(points.size() == 0)
            return;
        Pair last = points.get(0);
        for(int i = 0; i < points.size(); i++)
        {
            Pair temp = points.get(i);
            //System.out.println(last.x + "," + last.y + "," + temp.x + ","+temp.y);
            g2.drawLine(last.x, last.y, temp.x, temp.y);
            g2.drawOval(temp.x, temp.y, 1, 1);
            last = temp;
        }
    }
    
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)(g);
        paintGraph(g2, set.getPointsInRange());
        yAxis.paint(g2);
        super.paint(g);
    }
    
}
