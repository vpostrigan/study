package advanced_java2_deitel.chapter2_swing;

import javax.swing.*;
import java.awt.*;

public class WebBrowser extends JFrame {
    private WebToolBar toolBar;
    private WebBrowserPane browserPane;

    public WebBrowser() {
        super("Deitel Web Browser");

        browserPane = new WebBrowserPane();
        toolBar = new WebToolBar(browserPane);

        Container contentPane = getContentPane();
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(browserPane), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        WebBrowser browser = new WebBrowser();
        browser.setDefaultCloseOperation(EXIT_ON_CLOSE);
        browser.setSize(640, 480);
        browser.setVisible(true);
    }

}
