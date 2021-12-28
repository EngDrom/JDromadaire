package org.EngDrom.JDrom.parser.grammar;

import java.util.ArrayList;

import org.EngDrom.JDrom.lexer.Lexer;
import org.EngDrom.JDrom.lexer.Token;
import org.EngDrom.JDrom.lexer.TokenType;
import org.EngDrom.JDrom.parser.grammar.rules.ExpressionRule;
import org.EngDrom.JDrom.parser.grammar.rules.ListRule;
import org.EngDrom.JDrom.parser.grammar.rules.ManyRule;
import org.EngDrom.JDrom.parser.grammar.rules.OptionnalRule;
import org.EngDrom.JDrom.parser.grammar.rules.OrRule;
import org.EngDrom.JDrom.parser.grammar.rules.TokenRule;

/**
 * Compile a String of character to an expression rule that can be used by the ParserRule class
 */
public class RuleCompiler {
	
	private static ArrayList<Token> tokens;
	private static int tok_idx;

	public static ListRule compile ( String str ) {
		Lexer lex = new Lexer(str);
		lex.build();
		
		tokens  = lex.tokens;
		tok_idx = 0;
	
		return _compile();
	}
	
	private static ListRule _compile ( ) {
		ArrayList<ParserRule> rules = new ArrayList<ParserRule>();
		
		while (tok_idx < tokens.size()) {
			if (tokens.get(tok_idx).type == TokenType.RPAREN)
				break;
			
			ParserRule rule = factor(rules);
			if (rule != null)
				rules.add(rule);
			
			tok_idx += 1;
		}
		
		return new ListRule(rules);
	}
	
	private static ParserRule factor ( ArrayList<ParserRule> rules ) {
		if (tokens.get(tok_idx).type == TokenType.NAME && tokens.get(tok_idx).value.equals("EXPR")) {
			return new ExpressionRule();
		} 
		if (tokens.get(tok_idx).type == TokenType.LPAREN) {
			tok_idx += 1;
			ParserRule value = _compile();
			if (tok_idx < tokens.size() && tokens.get(tok_idx).type != TokenType.RPAREN)
				tok_idx -= 1;
			
			return value;
		}
		if (tokens.get(tok_idx).type == TokenType.LHOOK) {
			tok_idx += 1;
			ParserRule value = _compile();
			if (tok_idx < tokens.size() && tokens.get(tok_idx).type != TokenType.RHOOK)
				tok_idx -= 1;
			return new OptionnalRule(value);
		}
		if (tokens.get(tok_idx).type == TokenType.VERT_LINE) {
			ParserRule left = rules.get(rules.size() - 1);
			tok_idx += 1;
			ParserRule right = factor(rules);
			rules.set(rules.size() - 1, new OrRule(left, right));
			return null;
		}
		if (tokens.get(tok_idx).type == TokenType.DIVIDE) {
			tok_idx += 1;
			boolean add_val = tokens.get(tok_idx).type == TokenType.DIVIDE;
			if (tokens.get(tok_idx).type == TokenType.DIVIDE)
				tok_idx += 1;
			
			String name = tokens.get(tok_idx).value;
			
			if (tok_idx+1 < tokens.size() && tokens.get(tok_idx+1).type == TokenType.DIVIDE)
				tok_idx += 1;
			
			return new TokenRule(name, add_val);
		}
		if (tokens.get(tok_idx).type == TokenType.TIMES) {
			ParserRule left = rules.get(rules.size() - 1);
			tok_idx += 1;
			rules.set(rules.size() - 1, new ManyRule(left, -1, false));
		}
		
		return null;
	}
	
}
