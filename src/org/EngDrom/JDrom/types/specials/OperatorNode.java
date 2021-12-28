package org.EngDrom.JDrom.types.specials;

import org.EngDrom.JDrom.lexer.TokenType;
import org.EngDrom.JDrom.types.Node;

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
	public Object evaluate() {
		Object l = left.evaluate();
		Object r = right.evaluate();
		
		return null;
	}

}
