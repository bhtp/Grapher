package grapher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author 308809052, 308516582
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
            innerSet.fetchData();
        }
        
    }
    
    public final int getMillis()
    {
        try{
            return (int)(Math.round(Double.parseDouble(interval.getText()) * 60000));
        }
        catch(NumberFormatException e)
        {
            return -1;
        }
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
            
    
    public boolean updateInterval()
    {
        int delay = getMillis();
        if(delay > 0)
        {
           timer.setDelay(delay); 
           return true;
        }
        return false;
    }
    
    public void pause()
    {
        timer.stop();
    }
    
    public boolean isRunning()
    {
        return timer.isRunning();
    }
    
}
