package org.EngDrom.JDrom.types.std;

import org.EngDrom.JDrom.types.Node;

public class StackNode extends Node {

    public StackNode parent = null;
    public StackNode () { super(); }
    public StackNode (StackNode parent) { 
        super(); 
        this.parent = parent;
    }

    @Override
    public Object evaluate(StackNode context) {
        return this;
    }
    
}
