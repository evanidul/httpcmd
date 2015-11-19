package com.evanidul.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evanidul.models.Directory;
import com.evanidul.util.CommandProcessor;

/**
 * Servlet implementation class DirectoryController
 */
@WebServlet("/DirectoryController")
public class DirectoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DirectoryController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		render(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processCommand(request, response);
		render(request, response);
	}
	
	private void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** fake state
		 * 
		 */
		Directory root = new Directory("root");
		
		Directory appsdir = new Directory("Applications", root);
		root.addSubdirectory(appsdir);
		
		Directory docsdir = new Directory("Documents", root);
		root.addSubdirectory(docsdir);
		
		
		//request.setAttribute("rootdir", root);
		request.getSession().setAttribute("currentdir", root);
//		request.setAttribute("cmdexecuted", "asdfasdf");
		getServletContext().getRequestDispatcher("/WEB-INF/pages/directory.jsp").forward(request, response);
	}
	
	/** processCommand should model changes and http session state changes before passing control to render() **/
	private void processCommand(HttpServletRequest request, HttpServletResponse response) {
		
		String command = request.getParameter("command");	
		System.out.println("received cmd:" + command);
		try {
			CommandProcessor cmdline = new CommandProcessor();
			/** not great to pass request here, but gonna do it anyway.  In a real app I probably would spend time to design around it **/
			String cmdexecuted = cmdline.execute(command,request);
			System.out.println("cmdexecuted:" + cmdexecuted);
			request.setAttribute("cmdexecuted", cmdexecuted);
		} catch (Exception ex) {
			System.out.println("got error: " + ex.getMessage());
			request.setAttribute("errormessage", ex.getMessage());
		}
		
		//CommandProcessor returns the view markup for that window?
		
	}
	
	

}
