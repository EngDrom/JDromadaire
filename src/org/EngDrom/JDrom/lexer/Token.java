package org.EngDrom.JDrom.lexer;

/**
 * Token object used for lexer, can represent characters, names, numbers and much more
 * @author MrThimote, Itai12
 */

public class Token {

	public final TokenType type;
	public final String    value;
	
	public int idx;
	public int col;
	public int line;
	public int file;
	
	/**
	 * Constructors
	 */
	public Token (TokenType type, String value) {
		this.type  = type;
		this.value = value;
	}
	
	public Token (TokenType type) {
		this.type  = type;
		this.value = null;
	}
	
	public String toString() {
		if (this.value != null)
			return this.type + ":" + this.value;
		return this.type.toString();
	}

	public Token setPosition(int idx, int col, int line, int file) {
		this.idx = idx;
		this.col = col;
		this.line = line;
		this.file = file;
		
		return this;
	}
	
}
