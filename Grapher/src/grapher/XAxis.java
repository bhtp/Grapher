package grapher;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

/**
 *
 * @author Parker, Jonah
 */
public class XAxis {
    
    private int height;
    private DataSet set;
    private GraphComponent parent;
    private Font font;
    public final static int LABELS = 5;
    public final static Color BG_COLOR = Color.gray;
    
    /**
     * instantiates the x-axis
     * @param height of the x-axis
     * @param set of data
     * @param parent graph of the x-axis
     * @param font of the x-axis labeling
     */
    public XAxis(int height, DataSet set, GraphComponent parent, Font font)
    {
        this.height = height;
        this.set = set;
        this.parent = parent;
        this.font = font;
    }
    
    /**
     * 
     * @return the height of the x-axis 
     */
    public int getHeight()
    {
        return height;
    }
    
    /**
     * draws the x-axis
     * @param g2 Graphics2D object to be used
     */
    public void paint(Graphics2D g2)
    {
      set.setNow();
      FontMetrics metrics = g2.getFontMetrics(font);
      height = metrics.getHeight();
      g2.setBackground(BG_COLOR);
      g2.clearRect(0, parent.getHeight() - height, parent.getWidth(), height);
      double increment = (double)(parent.getUsableWidth()) / LABELS;
      double value = parent.yAxis.getWidth() + parent.getUsableWidth();
      for(int i = 0; i < LABELS + 1; i++)
      {
          renderString(g2, set.getTimeAt(),(int)(Math.round(value)), parent.getUsableHeight() + parent.PADDING*2, metrics);
          set.nextTime(LABELS);
          value -= increment;
      }
    }
    
    /**
     * draws a String on the x-axis
     * @param g2 Graphics2D object to be used
     * @param text String to be drawn
     * @param x starting x coordinate of the String
     * @param y starting y value of the String
     * @param metrics the FontMetrics object that is used
     */
    public void renderString(Graphics2D g2, String text, int x, int y, FontMetrics metrics)  
    {  
        FontRenderContext frContext = g2.getFontRenderContext();   
        TextLayout textLayout = new TextLayout(text, font, frContext);  
        g2.setColor(Color.black);
        textLayout.draw(g2, (int)(Math.round(x - (0.5 * metrics.stringWidth(text)))), y + parent.PADDING);
        g2.setColor(Color.darkGray);
        g2.drawLine(x, 0, x, parent.PADDING * 2 + parent.getUsableHeight());
    } 
}
