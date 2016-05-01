package org.strangeway.cefc;

import org.strangeway.cefc.window.CefcWindow;

import javax.swing.*;

/**
 * @author Yuriy Artamonov
 */
public final class CefcApplication {

    public static void start() {
        SwingUtilities.invokeLater(() ->
                new CefcWindow().init()
        );
    }
}