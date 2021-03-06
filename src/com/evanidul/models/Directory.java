package com.evanidul.models;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Directory implements Cloneable {

	private String name;
	private Directory parentdir = null;  //stays null if it's root, must be there otherwise
	private LinkedList<Directory> subdirectories = new LinkedList<Directory>();
	
	public Directory(String _name) {
		name = _name;
	}
	
	public Directory(String _name, Directory parent) {
		name = _name;
		setParentdir(parent);
	}
	
	public String getName() {
		return name;
	}
	
	public String getAbsoluteName() {
		if (parentdir != null) {
			return parentdir.getAbsoluteName() + "\\" + name;
		} else {
			return "\\";
		}
		 
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public LinkedList<Directory> getSubdirectories() {
		Collections.sort(subdirectories, new Comparator() {
            @Override
            public int compare(Object dirone, Object dirtwo) {
            	return ((Directory)dirone).getName().compareTo(((Directory)dirtwo).getName());
            }
        });

		
		
		return subdirectories;
	}

	public void setSubdirectories(LinkedList<Directory> subdirectories) {
		this.subdirectories = subdirectories;
	}

	public Directory getParentdir() {
		return parentdir;
	}

	public void setParentdir(Directory parentdir) {
		this.parentdir = parentdir;
	}


	public boolean isEmpty(){
		if (getSubdirectories().size() >1) return true;
		return false;
	}
	
	public Directory getSubDirectory(String subdirname) {
		for(Directory subdir : subdirectories) {
			if (subdir.getName().toLowerCase().equals(subdirname.toLowerCase())) {
				return subdir;
			}
		} 
		return null;
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
