package org.strangeway.cefc;

import org.strangeway.cefc.server.CefcServer;

/**
 * @author Yuriy Artamonov
 */
public final class CefcLauncher {

    public static void main(String[] args) {
        CefcServer.start();

        CefcApplication.start();
    }
}