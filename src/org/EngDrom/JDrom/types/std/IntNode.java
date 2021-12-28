package org.EngDrom.JDrom.types.std;

import org.EngDrom.JDrom.types.Node;

public class IntNode extends Node {
	
	private int value;
	public int getValue() {
		return value;
	}
	
	public IntNode(String str) {
		this.value = Integer.parseInt(str);
	}
	
	public IntNode(int value) {
		this.value = value;
	}
	
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public Object evaluate() {
		return value;
	}

}
