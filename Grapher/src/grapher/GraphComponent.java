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
    
    public GraphComponent()
    {
        set = new DataSet(this);
    }
    
    public int getUsableHeight()
    {
        return getHeight();
    }
    
    public int getUsableWidth()
    {
        return getWidth();
    }
    
    public static void paintGraph(Graphics2D g2, ArrayList<Pair> points)
    {
        Pair last = points.get(0);
        for(int i = 0; i < points.size(); i++)
        {
            Pair temp = points.get(i);
            g2.drawLine(last.x, last.y, temp.x, temp.y);
            last = temp;
        }
    }
    
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)(g);
        paintGraph(g2, set.getPointsInRange());
    }
    
}
