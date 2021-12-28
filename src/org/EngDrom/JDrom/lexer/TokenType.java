package org.EngDrom.JDrom.lexer;

/**
 * Token Type enumerator used for lexer, can represent characters, names, numbers and much more
 * @author MrThimote, Itai12
 */

public enum TokenType {
	// Defautl Types
	NUMBER, NAME,

	// Default Tokens
	SET, 
	
	// Block tokens
	FUNCTION, FOR, IF, ELSE, WHILE, BREAK, CONTINUE, 

	// Operators
	PLUS, MINUS, TIMES, DIVIDE,
	
	// Boolean operators
	NOT, OR, XOR, AND, 
	
	// Comparators
	EQUALS, NEQUALS, INF, INFEQ, GT, GTEQ,
	
	// Matching pairs
	LPAREN, RPAREN, // ()
	LHOOK, RHOOK,   // []
	LCURLYBRACKET, RCURLYBRACKET, // {}
	
	// Special tokens
	VERT_LINE, // |
	COMMA, DOT, // , .
	
	// End of line
	EOF, 
}
