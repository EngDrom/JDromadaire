package org.EngDrom.JDrom.lexer;

import java.util.ArrayList;

import org.EngDrom.JDrom.exceptions.lexer.CharacterException;
import org.EngDrom.JDrom.utils.tuples.Tuple;

/**
 * The lexer has for goal to transform a string into an array of token
 * @author MrThimote, Itai12
 */

public class Lexer {
	
	private static ArrayList<Lexer> lexers = new ArrayList<Lexer>();
	public static Lexer getProgram(int prog) {
		return (prog >= 0 && prog < lexers.size()) ? lexers.get(prog) : null;
	}
	public static void resetLexers() {
		Lexer.lexers.clear();
	}
	
	public final String str;
	public final int prog_idx;
	public ArrayList<Token> tokens;
	
	private int     idx;
	private char    chr;
	private boolean advanced;
	
	private int col  = 0;
	private int line = 1;
	
	public static final char NULL_CHR = '\0';
	
	/**
	 * Move pointer by offset
	 * @param off
	 * @return whether the new pointer is valid
	 */
	private boolean move(int off) {
		if (off < 0) System.err.println("Negative offset not implemented, might be glitched");
		if (off == 1) {
			this.idx     += off;
			this.advanced = this.idx < this.str.length() && this.idx >= 0;
			this.chr      = (this.advanced)
							? this.str.charAt(this.idx)
							: NULL_CHR;

			this.col += 1;
			if (this.chr == '\n') {
				this.col = 1;
				this.line += 1;
			}
			return this.advanced;
		}
		
		if (off >= 0) {
			for (int i = 0; i < off; i++) {
				this.move(1);
			}
		}
		
		return this.advanced;
	}
	// Special Move functions
	private boolean advance() { return move(1);  }
	private boolean devance() { return move(-1); }
	
	/**
	 * Get character at index offsetted by a certain number
	 * @param off
	 * @return the character at index current + off or NULL_CHR if it's out of bound
	 */
	private char get(int off) {
		int nidx = this.idx + off;
		return (nidx < this.str.length() && nidx >= 0)
				? this.str.charAt(nidx)
				: NULL_CHR;
	}
	// Special Get functions
	private char next()     { return get(1);  }
	private char previous() { return get(-1); }
	
	public Lexer(String str) {
		this.str = str;
		this.prog_idx = Lexer.lexers.size();
		Lexer.lexers.add(this);
	}
	
	public void build() {
		this.idx = -1;
		this.tokens = new ArrayList<Token>();
		this.advance();
		
		while (this.advanced) {
			Token new_token = null;
			if ((new_token = this.lex_number()) != null) {
				
			} else if ((new_token = this.lex_name()) != null) {
				
			} else if ((new_token = this.lex_operator()) != null) {
				
			} else if (!LexerConfig.IGNORE_CHARS.contains(String.valueOf(this.chr))) {
				throw new CharacterException(idx, col, line, prog_idx);
			}
			
			if (new_token != null)
				this.tokens.add(new_token);
			
			this.advance();
		}
		
		if (this.tokens.size() == 0 || this.tokens.get(this.tokens.size() - 1).type != TokenType.EOF)
			this.tokens.add(new Token(TokenType.EOF));
	}
	
	private Token lex_by_string(String str, TokenType type) {
		if (!str.contains(String.valueOf(this.chr))) return null;
		
		int idx = this.idx;
		int file = this.prog_idx;
		int col = this.col;
		int line = this.line;
		
		int start = idx;
		int end   = idx;
		
		while (str.contains(String.valueOf(this.next()))) {
			end += 1;
			this.advance();
		}
		
		return new Token(type, this.str.substring(start, end + 1))
				.setPosition(idx, col, line, file);
	}
	
	private Token lex_operator() {
		int cur_height = 0;
		if (this.idx < 0) return null;
		
		int sidx = this.idx;
		int file = this.prog_idx;
		int col = this.col;
		int line = this.line;
		int eidx = this.idx;
		TokenType type = null;
		
		for (Tuple tuple:LexerConfig.TOKEN_OPERATORS) {
			TokenType tok = (TokenType) tuple.get(TokenType.class, 0);
			String    str = tuple.get_str(1);
			
			if (str.length() != cur_height + 1) {
				if (str.length() <= cur_height)
					break;
				continue;
			}
			
			if (eidx < this.str.length() && str.charAt(cur_height) == this.str.charAt(eidx)) {
				cur_height += 1;
				eidx += 1;
				type = tok;
			}
		}
		
		if (type != null) {
			for (int i = sidx; i < eidx-1; i++)
				this.advance();
			
			return new Token(type)
				.setPosition(sidx, col, line, file);
		}
		
		return null;
	}
	private Token lex_name() {
		Token tok = this.lex_by_string(LexerConfig.NAME_CHARS, TokenType.NAME);
		if (tok == null) return null;
		
		for (Tuple tup:LexerConfig.TOKEN_NAME) {
			String expected = tup.get_str(1);
			if (tok.value.equals(expected)) {
				return new Token((TokenType) tup.get(TokenType.class, 0))
						.setPosition(tok.idx, tok.col, tok.line, tok.file);
			}
		}
		
		return tok;
	}
	private Token lex_number() {
		return this.lex_by_string(LexerConfig.NUMBER_CHARS, TokenType.NUMBER);
	}

}
