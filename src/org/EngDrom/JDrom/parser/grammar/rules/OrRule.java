package org.EngDrom.JDrom.parser.grammar.rules;

import org.EngDrom.JDrom.parser.config.ParserCursor;
import org.EngDrom.JDrom.parser.grammar.ParserRule;
import org.EngDrom.JDrom.types.Node;
import org.EngDrom.JDrom.types.specials.IdentityNode;

public class OrRule extends ParserRule {

	private ParserRule left;
	private ParserRule right;
	
	public OrRule(ParserRule left, ParserRule right) {
		this.left  = left;
		this.right = right;
	}

	@Override
	public Node parse(ParserCursor cursor) {
		Node n0 = this.left.parse(cursor);
		if (n0 != IdentityNode.COMPILER_ERR_NODE)
			return n0;
		
		cursor.restore();
		int i = 0;
		return this.right.parse(cursor);
	}

}
