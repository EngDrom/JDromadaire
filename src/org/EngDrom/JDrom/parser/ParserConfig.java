package org.EngDrom.JDrom.parser;

import java.util.ArrayList;

import org.EngDrom.JDrom.lexer.Token;
import org.EngDrom.JDrom.parser.config.ParserCursor;
import org.EngDrom.JDrom.parser.grammar.ParserRule;
import org.EngDrom.JDrom.parser.grammar.RuleCompiler;
import org.EngDrom.JDrom.types.specials.ForNode;
import org.EngDrom.JDrom.types.specials.IfNode;
import org.EngDrom.JDrom.types.specials.SetNode;

public class ParserConfig {
	
	public static ParserRule[] rules = new ParserRule[] {
		
		// Set node
		RuleCompiler.compile("//NAME/ /SET/ EXPR")
					.link(SetNode.class),
		// Simple node, goes also to SetNode to allow for __last__ variable containing the last return value
		RuleCompiler.compile("EXPR")
					.link(SetNode.class),
		
		// If statement
		RuleCompiler.compile("/IF/ /LPAREN/ EXPR /RPAREN/ {} [/ELSE/ /IF/ /LPAREN/ EXPR /RPAREN/ {}]* [/ELSE/ {}]")
					.link(IfNode.class),

		// For statement
		RuleCompiler.compile("/FOR/ /LPAREN/ EXPR /EOF/ EXPR /EOF/ EXPR /RPAREN/ {}")
					.link(ForNode.class),
		
	};
	
}
