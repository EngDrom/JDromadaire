package org.EngDrom.JDrom.lexer;

import org.EngDrom.JDrom.utils.tuples.Tuple;

public class LexerConfig {
	
	public static final Tuple[] TOKEN_NAME = new Tuple[] {
			new Tuple(true, TokenType.FUNCTION, "function"),
			new Tuple(true, TokenType.FOR, "for"),
			new Tuple(true, TokenType.IF, "if"),
			new Tuple(true, TokenType.ELSE, "else"),
			new Tuple(true, TokenType.BREAK, "break"),
			new Tuple(true, TokenType.CONTINUE, "continue"),
			new Tuple(true, TokenType.WHILE, "while"),
	};
	
	// Has to be built like a research tree with same patterns being adjacents
	// ('=' and '==' are same patterns and '=' must be first but the order of '!' and '=' doesn't matter)
	public static final Tuple[] TOKEN_OPERATORS = new Tuple[] {
			new Tuple(true, TokenType.SET, "="),
			new Tuple(true, TokenType.EQUALS, "=="),
			new Tuple(true, TokenType.NOT, "!"),
			new Tuple(true, TokenType.NEQUALS, "!="),
			new Tuple(true, TokenType.PLUS, "+"),
			new Tuple(true, TokenType.MINUS, "-"),
			new Tuple(true, TokenType.TIMES, "*"),
			new Tuple(true, TokenType.DIVIDE, "/"),
			new Tuple(true, TokenType.EOF, "\n"),
			new Tuple(true, TokenType.EOF, ";"),
			new Tuple(true, TokenType.COMMA, ","),
			new Tuple(true, TokenType.DOT, "."),

			new Tuple(true, TokenType.VERT_LINE, "|"),
			new Tuple(true, TokenType.LPAREN, "("),
			new Tuple(true, TokenType.RPAREN, ")"),
			new Tuple(true, TokenType.LHOOK, "["),
			new Tuple(true, TokenType.RHOOK, "]"),
			new Tuple(true, TokenType.LCURLYBRACKET, "{"),
			new Tuple(true, TokenType.RCURLYBRACKET, "}"),
	};
	
	public static final String IGNORE_CHARS = "\n\r\t ";
	public static final String NUMBER_CHARS = "0123456789";
	public static final String NAME_CHARS   = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789";

}
