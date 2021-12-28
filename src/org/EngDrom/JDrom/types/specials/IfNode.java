package org.EngDrom.JDrom.types.specials;

import java.util.ArrayList;

import org.EngDrom.JDrom.types.Node;
import org.EngDrom.JDrom.utils.tuples.Tuple;

public class IfNode extends Node {
	
	// [(expr0, block0), (expr1, block1) ... (expression i, block i)]
	private ArrayList<Tuple> tuples = new ArrayList<Tuple>();
	private Node else_statement;
	
	public IfNode(Object[] args) {
		for (int i = 1; i < args.length; i += 2) {
			tuples.add(new Tuple(true, (Node) args[i - 1], (Node) args[i]));
		}
		
		if ( (args.length % 2) == 1) {
			else_statement = (Node) args[args.length - 1];
		}
	}

	@Override
	public Object evaluate() {
		for (Tuple tuple:tuples) {
			Node expr  = (Node) tuple.get(Node.class, 0);
			Node block = (Node) tuple.get(Node.class, 1);
			
			boolean result = (boolean) expr.evaluate();
			if (result) {
				return block.evaluate();
			}
		}
		
		if (else_statement != null)
			return else_statement.evaluate();
		return null;
	}

}
