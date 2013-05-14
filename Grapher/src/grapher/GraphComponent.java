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
        
    }
    
}
