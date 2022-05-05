package org.EngDrom.JDrom.types.std;

import java.util.HashMap;

import org.EngDrom.JDrom.types.Node;

public class StackNode extends Node {

    private HashMap<String, Object> stored = new HashMap<>();
    private HashMap<String, Object> heaped = new HashMap<>();

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

            if (this.heaped.containsKey(str)) return this.heaped.get(str);
            return this.stored.get(str);
        } 

        return null;
    }

    public void setitem (Object key, Object value) {
        if (key instanceof String) {
            String str = (String) key;

            if (this.heaped.containsKey(str)) this.heaped.put(str, value);
            else this.stored.put(str, value);
        }
    }
    
}
