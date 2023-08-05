package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Aliean {
	private int aId;
	private String aName;
	private String tech;
	@Autowired
	private Laptop laptop;
	
	public Laptop getLaptop() {
		return laptop;
	}

	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}

	public Aliean() { System.out.println("Constructor called"); }
	
	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
	}
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	@Override
	public String toString() {
		return "Aliean [aId=" + aId + ", aName=" + aName + ", tech=" + tech + "]";
	}
	
	public void show() {
		System.out.println("Inside show method");
		laptop.compile();
	}
	
}
