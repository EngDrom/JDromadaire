package org.EngDrom.JDrom.parser.grammar.rules;

import java.util.ArrayList;

import org.EngDrom.JDrom.lexer.TokenType;
import org.EngDrom.JDrom.parser.ParserConfig;
import org.EngDrom.JDrom.parser.config.ParserCursor;
import org.EngDrom.JDrom.parser.grammar.ParserRule;
import org.EngDrom.JDrom.types.Node;
import org.EngDrom.JDrom.types.specials.BlockNode;
import org.EngDrom.JDrom.types.specials.IdentityNode;

public class BlockRule extends ParserRule {

    public BlockRule() {
        super();
    }

    @Override
    public Node parse(ParserCursor cursor) {
        if (cursor.get_cur_token().type != TokenType.LCURLYBRACKET)
            return IdentityNode.COMPILER_ERR_NODE;
        cursor.tok_idx += 1;

        ArrayList<Node> nodes = new ArrayList<Node>();

        cursor.save();

        while (cursor.tok_idx < cursor.token_count()) {
            if (cursor.get_cur_token().type == TokenType.RCURLYBRACKET)
                break;

            if (cursor.get_cur_token().type == TokenType.EOF) {
                cursor.tok_idx += 1;
                continue;
            }

            Node final_node = null;
            for (ParserRule rule: ParserConfig.rules) {
                Node node = rule.parse(cursor);

                if (node == IdentityNode.COMPILER_ERR_NODE)
                    continue;

                final_node = node;
                break;
            }

            if (final_node != null) {
                nodes.add(final_node);
            } else {
                cursor.restore();
                return IdentityNode.COMPILER_ERR_NODE;
            }

            if (cursor.tok_idx >= cursor.token_count() 
             || (cursor.get_cur_token().type != TokenType.EOF
             &&  cursor.get_cur_token().type != TokenType.RCURLYBRACKET)) {
                cursor.restore();
                return IdentityNode.COMPILER_ERR_NODE;
            }
        }

        if (cursor.get_cur_token().type != TokenType.RCURLYBRACKET) {
            cursor.restore();
            return IdentityNode.COMPILER_ERR_NODE;
        }
        cursor.tok_idx += 1;

        cursor.restore_arguments();
        cursor.free(true);

        cursor.addArgument( new BlockNode(nodes) );

        return IdentityNode.COMPILER_CONTINUE_NODE;
    }

}
