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
	public Object evaluate(StackNode context) {
		return this;
	}


	/**
	 * Operators
	 */
	public Object __add__ (Object other) {
		if (other instanceof IntNode)
			return new IntNode(this.value + ((IntNode)other).value);

		return null;
	}
	public Object __sub__ (Object other) {
		if (other instanceof IntNode)
			return new IntNode(this.value - ((IntNode)other).value);

		return null;
	}
	public Object __mul__ (Object other) {
		if (other instanceof IntNode)
			return new IntNode(this.value * ((IntNode)other).value);

		return null;
	}
	public Object __div__ (Object other) {
		if (other instanceof IntNode)
			return new IntNode(this.value / ((IntNode)other).value);

		return null;
	}

}
