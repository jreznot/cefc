package org.strangeway.cefc.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * @author Yuriy Artamonov
 */
public class CefcServer {

    private static Server serverInstance;

    public static void start() {
        Server embeddedServer = new Server(8080);

        ServletContextHandler contextHandler = new ServletContextHandler(null, "/", true, false);
        embeddedServer.setHandler(contextHandler);

        HashSessionManager manager = new HashSessionManager();
        SessionHandler sessions = new SessionHandler(manager);
        contextHandler.setSessionHandler(sessions);

        ServletHolder servletHolder = new ServletHolder(CefcServlet.class);
        contextHandler.addServlet(servletHolder, "/*");

        try {
            embeddedServer.start();
        } catch (Exception e) {
            System.out.print("Server stopped\n" + e.getMessage());
            e.printStackTrace(System.out);
        }

        serverInstance = embeddedServer;
    }

    public static void stop() {
        try {
            serverInstance.stop();
        } catch (Exception e) {
            System.out.print("Server stopped\n" + e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}