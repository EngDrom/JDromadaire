package org.EngDrom.JDrom.types.specials;

import org.EngDrom.JDrom.types.Node;

public class IdentityNode extends Node {

	public static final Node COMPILER_CONTINUE_NODE           = new IdentityNode();
	public static final Node COMPILER_CONTINUE_BUTHADERR_NODE = new IdentityNode();
	public static final Node COMPILER_ERR_NODE                = new IdentityNode();
	
	private IdentityNode() {}

	@Override
	public Object evaluate() {
		return null;
	}
	
}
