package org.EngDrom.JDrom.types;

import org.EngDrom.JDrom.types.std.StackNode;

public abstract class Node {
	
	public abstract Object evaluate(StackNode context);

	public Object getitem (Object key) {
		throw new RuntimeException();
	}
	public void setitem (Object key, Object value) {
		throw new RuntimeException();
	}

	public Object __add__ (Object other) {
		return null;
	}
	public Object __sub__ (Object other) {
		return null;
	}
	public Object __mul__ (Object other) {
		return null;
	}
	public Object __div__ (Object other) {
		return null;
	}

}
