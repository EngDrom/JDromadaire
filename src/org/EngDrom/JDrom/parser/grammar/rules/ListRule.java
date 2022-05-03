package org.EngDrom.JDrom.parser.grammar.rules;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import org.EngDrom.JDrom.parser.config.ParserCursor;
import org.EngDrom.JDrom.parser.grammar.ParserRule;
import org.EngDrom.JDrom.types.Node;
import org.EngDrom.JDrom.types.specials.IdentityNode;

public class ListRule extends ParserRule {
	
	private ArrayList<ParserRule> sub_rules;
	private Class<? extends Node> cls = null;

	public ListRule(ArrayList<ParserRule> sub_rules) {
		this.sub_rules = sub_rules;
	}

	@Override
	public Node parse(ParserCursor cursor) {
		cursor.save();
		
		for (ParserRule sr:sub_rules) {
			Node n = sr.parse(cursor);
			if (n == IdentityNode.COMPILER_ERR_NODE) {
				cursor.restore();
				return n;
			}
		}
		
		cursor.free(true);
		
		if (cls != null) {
			return this.get_linked(cursor);
		}
		return IdentityNode.COMPILER_CONTINUE_NODE;
	}

	public Node get_linked(ParserCursor cursor) {
		try {
			Constructor<? extends Node> method = cls.getConstructor(Object[].class);
			
			return method.newInstance(cursor.args());
		} catch (Exception e) {
			System.out.println("Could not find constructor for parser");
			return null;
		}
	}
	
	public ListRule link(Class<? extends Node> cls) {
		this.cls = cls;
		return this;
	}
	
}
