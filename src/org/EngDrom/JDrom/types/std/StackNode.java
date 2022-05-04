package org.EngDrom.JDrom.types.std;

import java.util.HashMap;

import org.EngDrom.JDrom.types.Node;

public class StackNode extends Node {

    private HashMap<String, Object> stored = new HashMap<>();

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

    public Object getitem(Object key) {
        if (key instanceof String) {
            String str = (String)key;

            return this.stored.get(str);
        } 

        return null;
    }

    public void setitem (Object key, Object value) {
        if (key instanceof String) {
            String str = (String) key;
            this.stored.put(str, value);
        }
    }
    
}
