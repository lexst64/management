package com.management.tools;

public enum Status {
	
	DEFAULT {
		@Override
		public String toString() {
			return "Default";
		}
	},
	
	VIP {
		@Override
		public String toString() {
			return "Vip";
		}
	}
	
}
