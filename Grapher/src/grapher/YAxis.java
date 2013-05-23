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
        
        String mid = truncateString(set.getMid());
        drawString(g2, mid , 0, (int)(Math.round(parent.getUsableHeight()/2) + metrics.getHeight()/2));
        int newWidth = metrics.stringWidth(mid);
        width = newWidth;
        
        if(!parent.set.isFlat())
        {
            String min = truncateString(set.getMinimum());
            drawString(g2, min, 0, parent.getUsableHeight());
            newWidth = metrics.stringWidth(min);
            if( newWidth > width)
                width = newWidth;

            String max = truncateString(set.getMaximum());
            drawString(g2, max, 0, 0 + metrics.getHeight());
            newWidth = metrics.stringWidth(max);
            if( newWidth > width)
                width = newWidth;
        }
    }     

   public static String truncateString(double raw)
   {
       return Double.toString((int)(Math.round(raw * 100))/100);
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
