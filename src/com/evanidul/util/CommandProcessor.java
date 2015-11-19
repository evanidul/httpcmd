package com.evanidul.util;

import javax.servlet.http.HttpServletRequest;

import com.evanidul.models.Directory;

public class CommandProcessor {

	
	//should throw all the exceptions for ls/mkdir/cd/pwd
	public String execute(String command, HttpServletRequest request) throws Exception {
		String[] args = command.split(" ");
		
		validateArgs(args);
		
		//does state changes of cmds, does not render
		return process(args, request);
		
	}
	
	private void validateArgs(String[] args) throws Exception {
		
		switch(args[0].toLowerCase()) {
			case "ls":
				//ls takes no args, just validate that it's LS
				validateLS(args);
				break;
			case "mkdir":
				//mkdir takes 1 arg, the name of the new folder.  It must be there
				validateMKDIR(args);
				break;
			case "cd":
				//cd takes 1 arg, the name of the folder to cd in.  It must be a directory in the subdirectories of the current folder, or ".." for the parent dir
				validateCD(args);
				break;
			case "pwd":
				//pwd takes no args
				validatePWD(args);
				break;
			default:
				throw new Exception("Invalid command.  Use valid command (ls | mkdir | cd | pwd");
		}		
	}
	
	
	/** placeholder, Eric's demo allows you to pass args to LS **/
	private void validateLS(String[] args) throws Exception {
		
	}
	
	/** only checks if the name is null...anything else **/
	private void validateMKDIR(String[] args) throws Exception {
		if(args[1] == null) {
			throw new Exception ("Name cannot be null");
		}
	}
	
	/** kind of different from Eric's.  Just checking if a name is passed.  Doesn't validate if it's a valid dir **/
	private void validateCD(String[] args) throws Exception {
		if(args[1] == null) {
			throw new Exception ("dir name cannot be null");
		}
	}
	
	/** placeholder, Eric's demo allows you to pass in extra args to PWD **/
	private void validatePWD(String[] args) throws Exception {
		
	}
	

	
	private String process(String[] args, HttpServletRequest request) throws Exception {
		switch(args[0].toLowerCase()) {
			case "ls":
				/* ls doesn't do anything.  User stays in same dir, nothing new gets made */
				return "ls";
			case "mkdir":
				//mkdir takes 1 arg, the name of the new folder.  It must be there
				String newFolderName = args[1];
				Directory parent = (Directory) request.getSession().getAttribute("currentdir");
				Directory newfolder = new Directory(newFolderName, parent);
				parent.addSubdirectory(newfolder);
				//we don't change directories after creating it...
				return "mkdir";
			case "cd":
				//cd takes 1 arg, the name of the folder to cd in.  It must be a directory in the subdirectories of the current folder, or ".." for the parent dir
				Directory current = (Directory) request.getSession().getAttribute("currentdir");
				if (args[1].equals("..")) {
					request.getSession().setAttribute("currentdir", current.getParentdir());
					return "cd";
				}
				//cd into a subdirectory
				Directory changee = current.getSubDirectory(args[1]);
				if (changee != null) {
					//cd into that dir
					request.getSession().setAttribute("currentdir", changee);
					return "cd";
				} else {
					//not a valid dir
					throw new Exception("Cannot cd into " + args[1] + ".  Directory does not exist");
				}				
				
			case "pwd":
				//no changes, view just prints out the current directory's abs url 
				return "pwd";
			default:
				//should never hit this, but it's here anyway...
				throw new Exception("Invalid command.  Use valid command (ls | mkdir | cd | pwd");
		}		
	}
	
}
