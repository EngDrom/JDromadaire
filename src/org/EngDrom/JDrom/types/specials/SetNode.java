package org.EngDrom.JDrom.types.specials;

import org.EngDrom.JDrom.types.Node;
import org.EngDrom.JDrom.types.std.StackNode;

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
	public Object evaluate(StackNode context) {
		Object o = expr.evaluate(context);
		context.setitem(this.name, o);
		
		return o;
	}

	public String toString() {
		return this.name + " = " + this.expr.toString();
	}

}
