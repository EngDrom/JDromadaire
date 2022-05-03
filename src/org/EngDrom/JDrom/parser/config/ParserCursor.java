package org.EngDrom.JDrom.parser.config;

import java.util.ArrayList;
import java.util.LinkedList;

import org.EngDrom.JDrom.lexer.Token;

public class ParserCursor {

	public int tok_idx = 0;
	private LinkedList<Integer> saves = new LinkedList<Integer>();
	private ArrayList<Token> tokens;
	private LinkedList<Object> arguments = new LinkedList<Object>();
	private LinkedList<Integer> args_saves = new LinkedList<Integer>();
	
	public Object[] args() {
		return arguments.toArray();
	}
	
	public ParserCursor (ArrayList<Token> types) {
		this.tokens = types;
	}
	
	public void save() {
		saves.add(this.tok_idx);
		args_saves.add(arguments.size());
	}
	
	public void free(boolean found) {
		if (!found)
			this.restore();
		else {
			this.saves.removeLast();
			this.args_saves.removeLast();
		}
	}

	public Token get_cur_token() {
		return this.tokens.get(this.tok_idx);
	}

	public void restore() {
		if (this.saves.size() == 0) {
			tok_idx = 0;
		} else {
			tok_idx = this.saves.getLast();
		}
		
		if (this.arguments.size() == 0) {
			this.arguments.clear();
		} else {
			int rst = this.args_saves.getLast();
			while (rst < this.arguments.size()) {
				this.arguments.removeLast();
			}
		}
	}
	
	public void addArgument(Object obj) {
		this.arguments.add(obj);
	}
	
}
