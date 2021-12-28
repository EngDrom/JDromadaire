package org.EngDrom.JDrom.exceptions.lexer;

import org.EngDrom.JDrom.lexer.Lexer;

public class CharacterException extends RuntimeException {
	
	private int idx;
	private int col;
	private int line;
	private int prog;

	public CharacterException ( int idx, int col, int line, int prog ) {
		this.idx = idx;
		this.col = col;
		this.line = line;
		this.prog = prog;
	}
	
	@Override
	public void printStackTrace() {
		System.err.println("Error occured during compilation : Could not find character");
		
		Lexer container = Lexer.getProgram(prog);
		String sequence = container.str.substring(Math.max(0, idx - 5), Math.min(container.str.length(), idx + 5));
		sequence.replaceAll("\n", "");
		
		System.err.println("\tAt line " + line + ", at col " + col);
		System.err.println(sequence);
		int centeridx = idx - (Math.max(0, idx - 5));
		for (int i = 0; i < centeridx; i++) {
			System.err.print(' ');
		}
		System.err.println("^");
	}
	@Override
	public String getMessage() {
		this.printStackTrace();
		return "Exception during compilation, see above error report";
	}
}
