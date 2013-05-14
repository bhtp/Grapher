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

        @Override
        public void actionPerformed(ActionEvent e) {
            set.fetchData();
        }
        
    }
    
    public int getMillis()
    {
        return (int)(Math.round(Double.parseDouble(interval.getText()) * 60000));
    }
    
    public DataTiming(DataSet set, JTextField interval)
    {
        this.set = set;
        this.interval = interval;
        this.timer = new Timer(getMillis(), new InnerAction());
    }
    
    public void updateInterval()
    {
        timer.setDelay(getMillis());
    }
    
}
