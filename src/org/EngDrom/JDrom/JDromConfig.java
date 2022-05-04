package org.EngDrom.JDrom;

import org.EngDrom.JDrom.lexer.Lexer;
import org.EngDrom.JDrom.parser.ParserConfig;
import org.EngDrom.JDrom.parser.config.ParserCursor;
import org.EngDrom.JDrom.parser.grammar.ParserRule;
import org.EngDrom.JDrom.parser.grammar.rules.BlockRule;
import org.EngDrom.JDrom.parser.grammar.rules.ListRule;
import org.EngDrom.JDrom.types.Node;
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
		Lexer l0 = new Lexer(" for (x = 1; x + 1; x + 1) { x + 1 } ");
		l0.build();
		BlockRule rule0 = new BlockRule();
		ParserCursor cursor = new ParserCursor(l0.tokens);
		rule0.parse(cursor, false);
		Node result = (Node) cursor.args()[0];
		System.out.println(result);
	}
	
}
