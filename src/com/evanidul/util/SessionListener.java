package com.evanidul.util;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.evanidul.models.Directory;

@WebListener
public class SessionListener implements HttpSessionListener  {
	
	@Override
	  public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("init session...");
		Directory root = new Directory("/");
		arg0.getSession().setAttribute("root", root);
		
		Directory appsdir = new Directory("Applications", root);
		root.addSubdirectory(appsdir);
		
		Directory docsdir = new Directory("Documents", root);
		root.addSubdirectory(docsdir);
		
		//start the user at root
		arg0.getSession().setAttribute("currentdir", root);
		
		//arg0.getSession().setAttribute("init", true);
	  }

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		
	}

}
