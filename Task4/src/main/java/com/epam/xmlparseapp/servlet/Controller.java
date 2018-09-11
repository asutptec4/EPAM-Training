package com.epam.xmlparseapp.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.xmlparseapp.entity.Device;
import com.epam.xmlparseapp.service.ParserFactory;
import com.epam.xmlparseapp.xmlparser.AbstractParser;

@WebServlet("/parse")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = -6573884767047220656L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req,
	    HttpServletResponse resp) throws ServletException, IOException {
	String parserName = req.getParameter("parser");
	AbstractParser parser = ParserFactory.createDeviceParser(parserName);
	parser.buildDeviceSet(req.getParameter("filePath"));
	Set<Device> set = parser.getDevices();
	req.setAttribute("deviceSet", set);
	RequestDispatcher dispatcher = getServletContext()
		.getRequestDispatcher("/jsp/view.jsp");
	dispatcher.forward(req, resp);
    }

}
