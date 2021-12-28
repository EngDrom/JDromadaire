package org.EngDrom.JDrom.types.specials;

import org.EngDrom.JDrom.types.Node;

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
	public Object evaluate() {
		initial.evaluate();
		
		Object o = condition.evaluate();
		while ( (boolean) (o instanceof Boolean ? o : o != null) ) {
			Object n0 = loop.evaluate();
			
			// TODO break returns and continues
			
			o = condition.evaluate();
		}
		
		return null;
	}

}
