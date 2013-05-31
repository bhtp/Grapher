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
    private final static Color BG_COLOR = Color.lightGray;
    
    /**
     * creates the y-axis labeling area
     * @param width of the area
     * @param set of data to be graphed
     * @param parent Graph 
     * @param font of the labeling
     */
    public YAxis(int width, DataSet set, GraphComponent parent, Font font)
    {
        this.width = width;
        this.set = set;
        this.parent = parent;
        this.font = font;
    }

    /**
     * draws the y-axis with an updated width 
     * @param g2 the g2 object to be used
     */
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

        g2.setBackground(BG_COLOR);
        g2.clearRect(0, 0, width, parent.getUsableHeight() + parent.PADDING*2);

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
    
    /**
     * updates the width of the y-axis
     * @param val the value of the new DataPoint
     * @param metrics the metrics of the Font
     * @param force an override to change the width regardless of the new value's width
     */
    public void updateWidth(String val, FontMetrics metrics, boolean force)
    {
        int newWidth = metrics.stringWidth(val);
        if( newWidth > width || force)
        {
            width = newWidth;
        }
    }

    /**
     * draws the labels
     * @param val the String to be drawn
     * @param pos the position of the label
     * @param g2 the Graphics2D object to be used
     * @param metrics the metrics of the used Font
     */
    public void drawString(String val, double pos, Graphics2D g2, FontMetrics metrics)
    {
        renderString(g2, val, 0, (int)(Math.round(parent.getUsableHeight() * pos) - (metrics.getHeight() * 0.5) + parent.PADDING));
        int yPos = (int)(Math.round(parent.getUsableHeight() * pos) + parent.PADDING);
        g2.setColor(Color.gray);
        g2.drawLine(width, yPos, parent.getWidth(), yPos);
        g2.setColor(Color.black);
    }

    /**
     * renders String as pixel values
     * @param g2 the Graphics2D object to be used
     * @param text the text to be drawn
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public void renderString(Graphics2D g2, String text, int x, int y)  
    {  
        FontRenderContext frContext = g2.getFontRenderContext();   
        TextLayout textLayout = new TextLayout(text, font, frContext);  
        g2.setColor(Color.black);
        textLayout.draw(g2, x, y + parent.PADDING);  
    }  

    /**
     * truncates the String to display the value to two decimal places
     * @param raw the raw String
     * @return the truncated String
     */
    public static String truncateString(double raw)
    {
       return Double.toString((double)((int)(Math.round(raw * 100)))/100);
    }
   
    /**
     * 
     * @return the height of the graph 
     */
    public int getHeight()
    {
        return parent.getUsableHeight();
    }

    /**
     * 
     * @return the width of the y-axis 
     */
    public int getWidth()
    {
        return width + PADDING;
    }
    
}
