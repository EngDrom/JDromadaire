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
		// Assume it returns a Node
		Node l = (Node) left.evaluate(context);
		Node r = (Node) right.evaluate(context);

		if (this.operator == TokenType.PLUS)   return l.__add__(r);
		if (this.operator == TokenType.MINUS)  return l.__sub__(r);
		if (this.operator == TokenType.TIMES)  return l.__mul__(r);
		if (this.operator == TokenType.DIVIDE) return l.__div__(r);
		
		return null;
	}

}
