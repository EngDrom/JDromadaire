package org.EngDrom.JDrom.parser.grammar.rules;

import java.lang.reflect.Method;

import org.EngDrom.JDrom.lexer.Token;
import org.EngDrom.JDrom.lexer.TokenType;
import org.EngDrom.JDrom.parser.config.ParserCursor;
import org.EngDrom.JDrom.parser.grammar.ParserRule;
import org.EngDrom.JDrom.types.Node;
import org.EngDrom.JDrom.types.specials.IdentityNode;
import org.EngDrom.JDrom.types.specials.OperatorNode;
import org.EngDrom.JDrom.types.std.IntNode;

class InnerExpressionException extends RuntimeException { }

public class ExpressionRule extends ParserRule {
	/**
	 * DO NOT REMOVE
	 * 
	 * It is used to lock the methods in place for the java compiler otherwise it removes them
	 * @throws Exception
	 */
	private void lock_methods() throws Exception {
		multiplication();
		boolean_operators();
		comparators();
	}
	
	private ParserCursor cursor;
	private Token get_cur_token() {
		return cursor.get_cur_token();
	}

	@Override
	public Node parse(ParserCursor cursor) {
		this.cursor = cursor;
		cursor.save();
		
		try {
			Node value = this.addition();

			this.cursor = null;
			cursor.free(true);
			cursor.addArgument(value);
			return IdentityNode.COMPILER_CONTINUE_NODE;
		} catch ( InnerExpressionException excep ) { 
		} catch (Exception e) {
			// System.err.println("Unexpected error during parsing");
		}

		this.cursor = null;
		cursor.free(false);
		return IdentityNode.COMPILER_ERR_NODE;
	}
	
	private boolean in (TokenType[] types, TokenType type) {
		for (TokenType t:types) {
			if (t == type) {
				return true;
			}
		}
		return false;
	}
	
	private Node operator_priority (TokenType[] types, Method method) throws Exception {
		Node left = (Node) method.invoke(this);
		
		while (this.in (types, this.get_cur_token().type)) {
			TokenType operator = this.get_cur_token().type;
			this.cursor.tok_idx += 1;
			
			Node right = (Node) method.invoke(this);
			left = new OperatorNode(left, right, operator);
		}
		
		return left;
	}
	
	public Node addition ( ) throws Exception {
		return this.operator_priority( 
				new TokenType[] {TokenType.PLUS, TokenType.MINUS}, 
				this.getClass().getMethod("multiplication")
			);
	}
	
	public Node multiplication ( ) throws Exception {
		return this.operator_priority( 
				new TokenType[] {TokenType.TIMES, TokenType.DIVIDE}, 
				this.getClass().getMethod("comparators")
			);
	}
	
	public Node comparators ( ) throws Exception {
		return this.operator_priority( 
				new TokenType[] {TokenType.GT, TokenType.GTEQ, TokenType.EQUALS, TokenType.NEQUALS, TokenType.INF, TokenType.INFEQ}, 
				this.getClass().getMethod("boolean_operators")
			);
	}
	
	public Node boolean_operators ( ) throws Exception {
		return this.operator_priority( 
				new TokenType[] {TokenType.OR, TokenType.XOR, TokenType.AND}, 
				this.getClass().getMethod("factor")
			);
	}
	
	public Node factor () throws Exception {
		
		Token tok = this.get_cur_token();
		this.cursor.tok_idx += 1;
		
		if (tok.type == TokenType.NUMBER) {
			return new IntNode(tok.value);
		}
		
		throw new InnerExpressionException();
	}

}
