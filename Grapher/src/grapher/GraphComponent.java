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
 * @author 308809052, 308516582
 */
public class GraphComponent extends JComponent{
    
   public DataSet set;
   public YAxis yAxis;
   public XAxis xAxis;
   public boolean circles;
   public final int PADDING = 15;
   public final int CIRCLE_RADIUS = 4;
   public final int STROKE_WIDTH = 2;
    
   /**
    * initializes the graph given a set of data
    * @param set DataSet to be graphed
    */
    public void init(DataSet set)
    {
        this.set = set;
        this.set.setParent(this);
        this.yAxis = new YAxis(25, set, this, new Font("Arial", Font.PLAIN, 18));
        this.xAxis = new XAxis(25, set, this, new Font("Arial", Font.PLAIN, 18));
        this.circles = false;
    }
    
    /**
     * toggles data point circling
     */
    public void checkCircles()
    {
        circles = !circles;
    }
    
    /**
     * 
     * @return the height allowed to the graph 
     */
    public int getUsableHeight()
    {
        return getHeight() - (PADDING * 2) - xAxis.getHeight();
    }
    
    /**
     * 
     * @return the width allowed to the graph 
     */
    public int getUsableWidth()
    {
        return getWidth() - yAxis.getWidth() - (PADDING * 2);
    }
    
    /**
     * 
     * @return the width of the y-axis labeling 
     */
    public int getYAxisWidth()
    {
        return yAxis.getWidth();
    }
    
    /**
     * 
     * @return the height of the x-axis labeling
     */
    public int getXAxisHeight()
    {
        return xAxis.getHeight();
    }
    
    /**
     * draws the points
     * @param g2 the Graphics2D object to be used
     * @param points the points to draw
     */
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
    
    /**
     * draws the graph
     * @param g the Graphics object to be used
     */
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
