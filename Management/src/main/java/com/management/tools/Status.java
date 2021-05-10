package com.management.tools;

public enum Status {
	
	DEFAULT("Default"), VIP("Vip");
	
	private String label;
	
	Status(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
}
