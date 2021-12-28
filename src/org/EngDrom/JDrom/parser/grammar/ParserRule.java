package org.EngDrom.JDrom.parser.grammar;

import org.EngDrom.JDrom.parser.config.ParserCursor;
import org.EngDrom.JDrom.parser.grammar.rules.ListRule;
import org.EngDrom.JDrom.types.Node;

/**
 * The goal of a ParserRule is to represent a grammar system
 * The parser has a grammar itself and is relying also on the Lexer for rule generation.
 * 
 * '()'    are used to do an operator and to separate things
 * 'EXPR'  is used to propose an expression
 * '|'     is used to propose multiple choices
 * '/X/'   is used to represent the token type X, put //X/ to add the value of the token like name to put it in the stack
 * '[]'    is used to represent an optional part of the grammar 
 * '{}'    is used to symbolize a block of multiple instructions
 * '*'     is used to say you can use as much as you wan't from the last expression, will require at least 1.
 * 
 * An example of this is the IF, ELSE and ELSE IF grammar rule :
 * /IF/ /LPAREN/ EXPR /RPAREN/ {} (([/ELSE/ /IF/ /LPAREN/ EXPR /RPAREN/ {}])*) [/ELSE/ {}]
 * 
 * A second example for a function :
 * /FUNCTION/ /LPAREN/ [/NAME/ (([/COMMA/ /NAME/])*)] /RPAREN/ {}
 */
public abstract class ParserRule {

	public static ListRule compile ( String str ) {
		return RuleCompiler.compile(str);
	}
	
	public abstract Node parse ( ParserCursor cursor );
	
}
