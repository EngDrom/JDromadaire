package org.EngDrom.JDrom.parser.grammar.rules;

import org.EngDrom.JDrom.parser.config.ParserCursor;
import org.EngDrom.JDrom.parser.grammar.ParserRule;
import org.EngDrom.JDrom.types.Node;
import org.EngDrom.JDrom.types.specials.IdentityNode;

public class ManyRule extends ParserRule {
	
	private ParserRule rule;
	private int times_max;
	private boolean all_needed;
	
	public ManyRule ( ParserRule rule, int times_max, boolean all_needed ) {
		this.rule       = rule;
		this.times_max  = times_max;
		this.all_needed = all_needed;
	}

	@Override
	public Node parse(ParserCursor cursor) {
		cursor.save();
		
		int idx = 0;
		while (idx != times_max) { // != to allow -1 for an infinite amount
			Node n = rule.parse(cursor);
			
			if (n != IdentityNode.COMPILER_CONTINUE_NODE) {
				if (all_needed) {
					cursor.restore();
					return IdentityNode.COMPILER_ERR_NODE;
				}
				break;
			}
		}
		
		cursor.free(true);
		
		return null;
	}

	
	
}
