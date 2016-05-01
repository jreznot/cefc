package org.strangeway.cefc.server;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import org.strangeway.cefc.app.CefcUI;

import javax.servlet.annotation.WebServlet;

/**
 * @author Yuriy Artamonov
 */
@WebServlet(value = "/*")
@VaadinServletConfiguration(productionMode = false, ui = CefcUI.class)
public final class CefcServlet extends VaadinServlet {
}