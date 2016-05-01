package org.strangeway.cefc.window;

import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;
import org.cef.handler.CefAppHandlerAdapter;
import org.strangeway.cefc.server.CefcServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Yuriy Artamonov
 */
public class CefcWindow extends JFrame {

    public void init() {
        CefApp.addAppHandler(new CefAppHandlerAdapter(null) {
            @Override
            public void stateHasChanged(org.cef.CefApp.CefAppState state) {
                // Shutdown the app if the native CEF part is terminated
                if (state == CefApp.CefAppState.TERMINATED) {
                    exitApp();
                }
            }
        });
        CefSettings settings = new CefSettings();
        settings.windowless_rendering_enabled = false;
        CefApp cefApp = CefApp.getInstance(settings);

        CefClient client = cefApp.createClient();
        CefBrowser browser = client.createBrowser("http://localhost:8080", false, false);
        Component cefBrowserPane = browser.getUIComponent();

        getContentPane().add(cefBrowserPane, BorderLayout.CENTER);

        pack();
        setSize(1280, 800);
        setVisible(true);

        ImageIcon windowIcon = new ImageIcon(
                getClass().getResource("/org/strangeway/cefc/window/monitor.png"));
        setIconImage(windowIcon.getImage());

        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenDimension.width / 2 - this.getSize().width / 2,
                screenDimension.height / 2 - this.getSize().height / 2);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CefApp.getInstance().dispose();
                exitApp();
            }
        });
    }

    private void exitApp() {
        CefcServer.stop();
        System.exit(0);
    }
}