/**
 * @(#)ATimerExample.java
 *
 *
 * @author Walid 
 *
 * 
 * @version 1.00 2014/11/19
 */

/*****************************************************
*    Title: ATimerExample
*    Author: Walid
*    Site owner/sponsor: stackoverflow.com
*    Date: 2013
*    Code version: edited Jan 10 '13 at 17:42
*    Availability: http://stackoverflow.com/questions/9721066/how-to-display-java-timer-on-a-separate-j-frame-form-label/
*    
*****************************************************/
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class ATimerExample  {

    Timer timer;
   public int counter = 0;

    public ATimerExample() {
        final JFrame frame = new JFrame("somethgi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JLabel label = new JLabel("0");
        JPanel panel = new JPanel();
        panel.add(label, BorderLayout.SOUTH);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(false);// dont want this to pop up as two jlabels will now appear
        
       // JavaProjectGuiClass mm = new JavaProjectGuiClass();

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               label.setText(String.valueOf(counter));
                counter--;
                if (counter == 0) {
                    //timer.removeActionListener(this);
                      timer.stop();
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {                                         // main method that runs the timer//
        SwingUtilities.invokeLater(new Runnable() {                                  // runs an inner class using the runnable interface//

            @Override
            public void run() {
                new ATimerExample();
            }
        });
    }
}





 
