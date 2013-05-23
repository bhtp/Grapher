package grapher;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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
    private Font font;
    
    public YAxis(int width, DataSet set, GraphComponent parent, Font font)
    {
        this.width = width;
        this.set = set;
        this.parent = parent;
        this.font = font;
    }
    
    public void paint(Graphics2D g2)  
    {  
        FontMetrics metrics = g2.getFontMetrics();
        drawString(g2, Double.toString(set.getMaximum()), 0, 0 + metrics.getHeight());
        width = metrics.stringWidth(Double.toString(set.getMaximum()));
        drawString(g2, Double.toString((set.getMaximum() + set.getMinimum())/2), 0, (int)(Math.round(parent.getUsableHeight()/2) + metrics.getHeight()/2));
        int midWidth = metrics.stringWidth(Double.toString((set.getMaximum() + set.getMinimum())/2));
        if(midWidth > width)
            width = midWidth;
        drawString(g2, Double.toString(set.getMinimum()), 0, parent.getUsableHeight());
        if(metrics.stringWidth(Double.toString(set.getMinimum())) > width)
            width = metrics.stringWidth(Double.toString(set.getMinimum()));
    }     

   public void drawString(Graphics2D g2, String text, int x, int y)  
   {  
        FontRenderContext frContext = g2.getFontRenderContext();   
        TextLayout textLayout = new TextLayout(text, font, frContext);  
        g2.setColor(Color.black);
        textLayout.draw(g2, x, y);  
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
