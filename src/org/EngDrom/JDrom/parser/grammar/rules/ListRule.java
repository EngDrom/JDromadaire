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
		
		
		if (cls != null) {
			Node data = this.get_linked(cursor);
			cursor.restore_arguments();
			cursor.free(true);
			cursor.addArgument(data);
			return data;
		}

		cursor.free(true);
		return IdentityNode.COMPILER_CONTINUE_NODE;
	}

	private Constructor<? extends Node> getConstructor () {
		for (Constructor<?> cstr: cls.getConstructors()) {
			if (cstr.getParameterTypes().length == 1
			   && cstr.getParameterTypes()[0].toString().equals("class [Ljava.lang.Object;")) {
				
				return (Constructor<? extends Node>) cstr;
			}
		}	

		return null;
	}

	public Node get_linked(ParserCursor cursor) {
		try {
			return this.getConstructor().newInstance(new Object[] {cursor.args()});
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not find constructor for parser");
			return null;
		}
	}
	
	public ListRule link(Class<? extends Node> cls) {
		this.cls = cls;
		return this;
	}
	
}
