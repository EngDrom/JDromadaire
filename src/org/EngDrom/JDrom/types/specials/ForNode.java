package org.EngDrom.JDrom.types.specials;

import org.EngDrom.JDrom.types.Node;
import org.EngDrom.JDrom.types.std.StackNode;

public class ForNode extends Node {
	
	private Node initial;
	private Node condition;
	private Node loop;

	private Node block;
	
	public ForNode(Object[] args) {
		this.initial = (Node) args[0];
		this.condition = (Node) args[1];
		this.loop = (Node) args[2];
		this.block = (Node) args[3];
	}
	
	@Override
	public Object evaluate(StackNode context) {
		initial.evaluate(context);
		
		Object o = condition.evaluate(context);
		while ( (boolean) (o instanceof Boolean ? o : o != null) ) {
			block.evaluate(context);

			Object n0 = loop.evaluate(context);
			
			// TODO break returns and continues
			
			o = condition.evaluate(context);
		}
		
		return null;
	}

}
