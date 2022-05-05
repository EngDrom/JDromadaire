package org.EngDrom.JDrom.types.std;

import org.EngDrom.JDrom.types.Node;

public class StringNode extends Node {

    public String string;

    public StringNode (String str) {
        this.string = str;
    }

    @Override
    public Object evaluate(StackNode context) {
        return this;
    }
    
}
