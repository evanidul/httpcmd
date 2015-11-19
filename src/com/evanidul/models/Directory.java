package com.evanidul.models;

import java.util.LinkedList;

public class Directory implements Cloneable {

	private String name;

	private LinkedList<Directory> subdirectories = new LinkedList<Directory>();
	
	public Directory() {}
	
	public Directory(String _name) {
		name = _name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public LinkedList<Directory> getSubdirectories() {
		return subdirectories;
	}

	public void setSubdirectories(LinkedList<Directory> subdirectories) {
		this.subdirectories = subdirectories;
	}


	public boolean isEmpty(){
		if (getSubdirectories().size() >1) return true;
		return false;
	}
	
	/**
	 * basically what mkdir does, except we will keep this code like model code and move mkdir to a command interpreter class or something
	 * @param newdir
	 */
	public void addSubdirectory(Directory newdir) {
		getSubdirectories().add(newdir);
	}
	
	@Override
	public String toString() {
		return name;
		
	}

		
	
}
