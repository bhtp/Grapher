package grapher;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author 308809052
 */
public class GraphComponent extends JComponent{
    
   public DataSet set;
   public YAxis yAxis;
   public XAxis xAxis;
   public boolean circles;
   public final int PADDING = 15;
   public final int CIRCLE_RADIUS = 4;
   public final int STROKE_WIDTH = 2;
    
    public void init(DataSet set)
    {
        this.set = set;
        this.set.setParent(this);
        this.yAxis = new YAxis(25, set, this, new Font("Arial", Font.PLAIN, 18));
        this.xAxis = new XAxis(25, set, this, new Font("Arial", Font.PLAIN, 18));
        this.circles = false;
    }
    
    public void checkCircles()
    {
        circles = !circles;
    }
    
    public int getUsableHeight()
    {
        return getHeight() - (PADDING * 2) - xAxis.getHeight();
    }
    
    public int getUsableWidth()
    {
        return getWidth() - yAxis.getWidth() - (PADDING * 2);
    }
    
    public int getYAxisWidth()
    {
        return yAxis.getWidth();
    }
    
    public int getXAxisHeight()
    {
        return xAxis.getHeight();
    }
    
    public void paintGraph(Graphics2D g2, ArrayList<Pair> points)
    {
        Pair last = points.get(0);
        for(int i = 0; i < points.size(); i++)
        {
            Pair temp = points.get(i);
            g2.setStroke(new BasicStroke(STROKE_WIDTH));
            g2.setColor(Color.black);
            g2.drawLine(last.x - STROKE_WIDTH/2, last.y + PADDING, temp.x - STROKE_WIDTH/2, temp.y + PADDING);
            if(circles)
                g2.drawOval(temp.x - CIRCLE_RADIUS / 2 - 1, temp.y - CIRCLE_RADIUS / 2 - 1 + PADDING, CIRCLE_RADIUS, CIRCLE_RADIUS);
            last = temp;
        }
    }
    
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)(g);
        g2.setBackground(Color.white);
        g2.clearRect(0, 0, getWidth(), getUsableHeight() + PADDING*2);
        yAxis.paint(g2);
        xAxis.paint(g2);
        paintGraph(g2, set.getPointsInRange());
        super.paint(g);
    }
    
}
