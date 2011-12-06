package grapher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author 308809052
 */
public class DataTiming {
    private DataSet set;
    private JTextField interval;
    private Timer timer;
    
    class InnerAction implements ActionListener{

        DataSet innerSet;
        public InnerAction(DataSet innerSet)
        {
            this.innerSet = innerSet;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Called");
            innerSet.fetchData();
        }
        
    }
    
    public final int getMillis()
    {
        return (int)(Math.round(Double.parseDouble(interval.getText()) * 60000));
    }
    
    public DataTiming(DataSet set, JTextField interval)
    {
        this.set = set;
        this.interval = interval;
        timer = new Timer(getMillis(), new InnerAction(set));
    }
    
    public void start()
    {
        timer.start();
    }
            
    
    public void updateInterval()
    {
        timer.setDelay(getMillis());
    }
    
}
