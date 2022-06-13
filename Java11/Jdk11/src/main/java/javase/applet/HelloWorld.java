package docs_oracle_com.javase.applet;

import javax.swing.*;

public class HelloWorld extends JApplet  {

    //Called when this applet is loaded into the browser.
    public void init() {
        //Execute a job on the event-dispatching thread; creating this applet's GUI.
        try {
            SwingUtilities.invokeAndWait(() -> {
                JLabel lbl = new JLabel("Hello World");
                add(lbl);
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }
    }

}
