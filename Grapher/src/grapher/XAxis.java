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
public class XAxis {
    
    private int height;
    private DataSet set;
    private GraphComponent parent;
    private Font font;
    public final static int LABELS = 5;
    public final static Color BG_COLOR = Color.gray;
    
    public XAxis(int height, DataSet set, GraphComponent parent, Font font)
    {
        this.height = height;
        this.set = set;
        this.parent = parent;
        this.font = font;
    }
    
    public int getHeight()
    {
        return height;
    }
    
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
          renderString(g2, set.getTimeAt(LABELS),(int)(Math.round(value)), parent.getUsableHeight() + parent.PADDING*2, metrics);
          value -= increment;
      }
    }
    
    public void renderString(Graphics2D g2, String text, int x, int y, FontMetrics metrics)  
    {  
        FontRenderContext frContext = g2.getFontRenderContext();   
        TextLayout textLayout = new TextLayout(text, font, frContext);  
        g2.setColor(Color.black);
        textLayout.draw(g2, (int)(Math.round(x - (0.5 * metrics.stringWidth(text)))), y + parent.PADDING);
        g2.setColor(Color.darkGray);
        g2.drawLine(x, 0, x, parent.PADDING * 2 + parent.getUsableHeight());
        System.out.println(x);
        
    } 
}
