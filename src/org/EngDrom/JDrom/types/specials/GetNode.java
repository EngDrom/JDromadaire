package org.EngDrom.JDrom.types.specials;

import org.EngDrom.JDrom.types.Node;
import org.EngDrom.JDrom.types.std.StackNode;

public class GetNode extends Node {

    private String name;

    public GetNode(String name) {
        this.name = name;
    }

    @Override
    public Object evaluate(StackNode context) {
        return context.getitem(this.name);
    }

    public String toString() {
        return this.name;
    }
    
}
