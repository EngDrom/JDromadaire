package org.EngDrom.JDrom.types.specials;

import org.EngDrom.JDrom.lexer.TokenType;
import org.EngDrom.JDrom.types.Node;
import org.EngDrom.JDrom.types.std.StackNode;

public class OperatorNode extends Node {

	private Node left;
	private Node right;
	private TokenType operator;
	
	public OperatorNode(Node left, Node right, TokenType operator) {
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
	
	public String toString() {
		return '(' + left.toString() + ' ' + this.operator + ' ' + right.toString() + ')';
	}

	@Override
	public Object evaluate(StackNode context) {
		Object l = left.evaluate(context);
		Object r = right.evaluate(context);
		
		return null;
	}

}
