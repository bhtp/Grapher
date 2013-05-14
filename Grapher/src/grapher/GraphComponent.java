package grapher;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
    
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)(g);
        int[] points = set.getPointsInRange();
        for(int i = 0; i < points.length; i++)
        {
            g2.drawLine();
        }
        
    }
    
}
