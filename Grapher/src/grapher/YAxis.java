package grapher;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

/**
 *
 * @author Parker
 */
public class YAxis {
    private int width;
    private DataSet set;
    private GraphComponent parent;
    
    public YAxis(int width, DataSet set, GraphComponent parent)
    {
        this.width = width;
        this.set = set;
        this.parent = parent;
    }
    
    public void paint(Graphics2D g2)
    {
        
    }
    
    public int getHeight()
    {
        return parent.getUsableHeight();
    }
    
    public int getWidth()
    {
        return width;
    }
    
}
