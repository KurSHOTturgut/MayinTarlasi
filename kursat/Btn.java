package minesweeper;

import javax.swing.JButton;

public class Btn extends JButton {
	private int row;
	private int column; 
	private int count; 
	private boolean isMined; 
	private boolean isFlagged;
	
	public Btn(int row, int column) {
		super();
		this.row = row;
		this.column = column;
		this.count = 0;
		this.isMined = false; 
		this.isFlagged = false;
	}
	
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isMined() {
		return isMined;
	}

	public void setMined(boolean isMined) {
		this.isMined = isMined;
	}

	public boolean isFlagged() {
		return isFlagged;
	}

	public void setFlagged(boolean isFlagged) {
		this.isFlagged = isFlagged;
	}
	
	
	
	
}