package org.EngDrom.JDrom;

import org.EngDrom.JDrom.lexer.Lexer;
import org.EngDrom.JDrom.parser.ParserConfig;
import org.EngDrom.JDrom.parser.config.ParserCursor;
import org.EngDrom.JDrom.parser.grammar.ParserRule;
import org.EngDrom.JDrom.types.specials.IdentityNode;

/**
 * Configuration File for the JDromadaire programming language
 * @author MrThimote, Itai12
 */

public class JDromConfig {

	public static final String RELEASE_TYPE = "Dev";
	public static final String MAJOR        = "0";
	public static final String MINOR        = "0";
	public static final String SUBVERSION   = "0";
	
	public static final String VERSION = RELEASE_TYPE + '.' + MAJOR + '.' + MINOR + '.' + SUBVERSION;
	
	public static void main(String[] args) {
		Lexer l0 = new Lexer(" function f(x, y) { return x + 1; } ");
		l0.build();
		ParserRule rule0 = ParserRule.compile("/FUNCTION/ //NAME/ /LPAREN/ [//NAME/ [/COMMA/ //NAME/]*] /RPAREN/ {}");
		
		ParserCursor cursor = new ParserCursor(l0.tokens);
		System.out.println(rule0.parse(cursor) == IdentityNode.COMPILER_ERR_NODE);
		Object[] cargs = cursor.args();
		for (int i = 0; i < cargs.length; i ++)
			System.out.println(cargs[i]);
	}
	
}
