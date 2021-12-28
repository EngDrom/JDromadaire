package org.EngDrom.JDrom.parser.grammar.rules;

import org.EngDrom.JDrom.lexer.TokenType;
import org.EngDrom.JDrom.parser.config.ParserCursor;
import org.EngDrom.JDrom.parser.grammar.ParserRule;
import org.EngDrom.JDrom.types.Node;
import org.EngDrom.JDrom.types.specials.IdentityNode;

public class TokenRule extends ParserRule {

	private TokenType type;
	private boolean add_val;
	
	public TokenRule ( TokenType type, boolean add_val ) {
		this.type = type;
		this.add_val = add_val;
	}
	
	public TokenRule ( String name, boolean add_val ) {
		this.type = TokenType.valueOf(name);
		this.add_val = add_val;
	}

	@Override
	public Node parse(ParserCursor cursor) {
		if (cursor.get_cur_token().type == this.type) {
			if ( add_val ) {
				if ( cursor.get_cur_token().value != null ) {
					cursor.addArgument(cursor.get_cur_token().value);
				} else {
					cursor.addArgument(cursor.get_cur_token().type);
				}
			}
			cursor.tok_idx += 1;
			return IdentityNode.COMPILER_CONTINUE_NODE;
		}
			
		return IdentityNode.COMPILER_ERR_NODE;
	}
	
}
