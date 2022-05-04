package org.EngDrom.JDrom.types.specials;

import java.util.ArrayList;

import org.EngDrom.JDrom.types.Node;
import org.EngDrom.JDrom.types.std.StackNode;

public class BlockNode extends Node {

    private ArrayList<Node> nodes;

    public BlockNode(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public Object evaluate(StackNode context) {
        for (Node node: nodes) {
            node.evaluate(context);
        }
        return null;
    }

    public String toString() {
        StringBuffer strbf = new StringBuffer();

        strbf.append("BLOCK\n");
        for (Node node:this.nodes) {
            strbf.append(node.toString());
            strbf.append("\n");
        }
        strbf.setLength(strbf.length() == 0 ? 0 : strbf.length() - 1);

        String str = strbf.toString();

        return str.replaceAll("\n", "\n  ");
    }
    
}
