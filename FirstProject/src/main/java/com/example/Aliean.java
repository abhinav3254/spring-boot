package com.example;

public class Aliean {
	private int aId;
	private String aName;
	private String tech;
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
}
