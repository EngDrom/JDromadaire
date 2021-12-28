package org.EngDrom.JDrom.types.specials;

import org.EngDrom.JDrom.types.Node;

public class SetNode extends Node {
	
	private String name = "__last__";
	private Node   expr;
	
	public SetNode (Object[] args) {
		if (args.length == 2) {
			name = (String) args[0];
			expr = (Node)   args[1];
		} else {
			expr = (Node)   args[0];
		}
	}

	@Override
	public Object evaluate() {
		Object o = expr.evaluate();
		// TODO save data
		
		return o;
	}

}
