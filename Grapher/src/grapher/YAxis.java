package grapher;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.text.DecimalFormat;

/**
 *
 * @author Parker
 */
public class YAxis {
    private int width;
    private DataSet set;
    private GraphComponent parent;
    private Font font;
    private final int PADDING = 2;
    
    public YAxis(int width, DataSet set, GraphComponent parent, Font font)
    {
        this.width = width;
        this.set = set;
        this.parent = parent;
        this.font = font;
    }
    
    public void paint(Graphics2D g2)  
    {  
        FontMetrics metrics = g2.getFontMetrics(font);
        
        drawString(set.getMid(), 0.5, g2, metrics, true);
        
        if(!parent.set.isFlat())
        {
            drawString(set.getMaximum(), 0, g2, metrics, false);
            drawString((set.getMaximum() + set.getMid())/2, 0.25, g2, metrics, false);
            drawString((set.getMinimum() + set.getMid())/2, 0.75, g2, metrics, false);
            drawString(set.getMinimum(), 1, g2, metrics, false);
        }
        parent.set.setTimeRatio();
    }     
    
    public void drawString(double value, double pos, Graphics2D g2, FontMetrics metrics, boolean force)
    {
        String val = truncateString(value);
        drawString(g2, val, 0, (int)(Math.round(parent.getUsableHeight() * pos + metrics.getHeight() * 0.5)));
        int newWidth = metrics.stringWidth(val);
        if( newWidth > width || force)
        {
            width = newWidth;
        }
    }

   public static String truncateString(double raw)
   {
       return Double.toString((double)((int)(Math.round(raw * 100)))/100);
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
        return width + PADDING;
    }
    
}
