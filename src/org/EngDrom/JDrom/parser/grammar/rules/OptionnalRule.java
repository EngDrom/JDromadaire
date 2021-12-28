package org.EngDrom.JDrom.parser.grammar.rules;

import org.EngDrom.JDrom.parser.config.ParserCursor;
import org.EngDrom.JDrom.parser.grammar.ParserRule;
import org.EngDrom.JDrom.types.Node;
import org.EngDrom.JDrom.types.specials.IdentityNode;

public class OptionnalRule extends ParserRule {
	
	private ParserRule rule;
	
	public OptionnalRule (ParserRule rule) {
		this.rule = rule;
	}

	@Override
	public Node parse(ParserCursor cursor) {
		Node n = this.rule.parse(cursor);
		if (n == IdentityNode.COMPILER_ERR_NODE)
			return IdentityNode.COMPILER_CONTINUE_BUTHADERR_NODE;
		
		return n;
	}

}
