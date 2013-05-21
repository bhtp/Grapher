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
        drawString(g2, Double.toString(set.getMaximum()), 0, parent.getUsableHeight());
        drawString(g2, Double.toString((set.getMaximum() + set.getMinimum())/2), 0, (int)(Math.round(parent.getUsableHeight()/2)));
        drawString(g2, Double.toString(set.getMinimum()), 0, 0);
    }
    
    public void drawString(Graphics2D g2, String text, int x, int y)
    {
        FontRenderContext frContext = g2.getFontRenderContext();
        Font font = new Font("Helvetica",Font.BOLD, 24);
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
