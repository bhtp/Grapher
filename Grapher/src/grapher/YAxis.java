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
        
        String mid = truncateString(set.getMid());
        updateWidth(mid, metrics, true);
        
        String max = null, maxMid = null, minMid = null, min = null;
        
        if(!parent.set.isFlat())
        {
            max = truncateString(set.getMaximum());
            updateWidth(max, metrics, false);
            
            maxMid = truncateString((set.getMaximum() + set.getMid())/2);
            updateWidth(maxMid, metrics, false);
            
            minMid = truncateString((set.getMinimum() + set.getMid())/2);
            updateWidth(minMid, metrics, false);
                        
            min = truncateString(set.getMinimum());
            updateWidth(min, metrics, false);
        }
        
        g2.setBackground(Color.lightGray);
        g2.clearRect(0, 0, width, parent.getHeight());
        
        drawString(mid, 0.5, g2, metrics);
        
        if(!parent.set.isFlat())
        {
            drawString(max, 0, g2, metrics);
            drawString(maxMid, 0.25, g2, metrics);
            drawString(minMid, 0.75, g2, metrics);
            drawString(min, 1, g2, metrics);
        }
        
        parent.set.setTimeRatio();
    }     
    
    public void updateWidth(String val, FontMetrics metrics, boolean force)
    {
        int newWidth = metrics.stringWidth(val);
        if( newWidth > width || force)
        {
            width = newWidth;
        }
    }
    
    public void drawString(String val, double pos, Graphics2D g2, FontMetrics metrics)
    {
        drawString(g2, val, 0, (int)(Math.round(parent.getUsableHeight() * pos) - (metrics.getHeight() * 0.5) + parent.PADDING));
        int yPos = (int)(Math.round(parent.getUsableHeight() * pos) + parent.PADDING);
        g2.setColor(Color.gray);
        g2.drawLine(width, yPos, parent.getWidth(), yPos);
        g2.setColor(Color.black);
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
        textLayout.draw(g2, x, y + parent.PADDING);  
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
