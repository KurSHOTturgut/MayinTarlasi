package minesweeper;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MineSweeper implements MouseListener {
	JFrame frame;
	Btn[][] board = new Btn[10][10];
	int openedBtnCount;;

	public MineSweeper() {
		openedBtnCount = 0;
		frame = new JFrame("Minesweeper");
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		frame.setLayout(new GridLayout(10,10));
	
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				Btn b = new Btn(row, col);
				frame.add(b);
				b.addMouseListener(this);
				board[row][col] = b;
			}
		}
		
		generateMine();
		updateCount();
		
			
		
		frame.setVisible(true);
	}
	
	
	public void generateMine() {
		int i = 0;
		while(i < 10) { // 10 mines
			int randomRow = (int) (Math.random() * board.length);
			int randomCol = (int) (Math.random() * board.length);
			
			
			while(board[randomRow][randomCol].isMined()) {
				randomRow = (int) (Math.random() * board.length);
				randomCol = (int) (Math.random() * board.length);
			}
			board[randomRow][randomCol].setMined(true); 
			i++;
		}
	}

	public void print() {
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				if(board[row][col].isMined()) { 
					board[row][col].setIcon(new ImageIcon("mine.png"));
				}
				else {
					board[row][col].setText(board[row][col].getCount()+""); 
					board[row][col].setEnabled(false);
				}
			}
		}
	}
	
	public void showMines() {
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				if(board[row][col].isMined()) { 
					board[row][col].setIcon(new ImageIcon("mine.png"));
				}
				
			}
		}
	}
	
	public void updateCount() {
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				if(board[row][col].isMined()) {
					counting(row,col);
				}
			}
		}
	}
	
	public void counting(int row, int col) {
		for(int i = row-1; i <= row+1; i++) {
			for(int j = col-1; j <= col+1; j++) {
				try { 
					int value = board[i][j].getCount();
					board[i][j].setCount(++value);
				} catch(Exception e) {
					
				}
			}
		}
	}


	public void open(int row, int col) {
		if(row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col].getText().length() > 0 || !board[row][col].isEnabled()) { // outside the area. possible mistake cases 
			return;
		}
		else if(board[row][col].getCount() != 0) {
			board[row][col].setText(board[row][col].getCount()+"");
			board[row][col].setEnabled(false);
			openedBtnCount++;
		}
		else { // 0
			openedBtnCount++;
			board[row][col].setEnabled(false); 
			open(row-1, col);
			open(row+1, col);
			open(row, col-1);
			open(row, col+1);
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Btn b = (Btn) e.getComponent();
		if(e.getButton() == 1) { 
			if(b.isMined()) { 
				JOptionPane.showMessageDialog(frame, "Game Over!");
				print();
			}
			else {
				open(b.getRow(), b.getColumn());
				if(openedBtnCount == board.length * board[0].length - 10) { 
					JOptionPane.showMessageDialog(frame, "You Win!");
					print();
				}
			}
		} 
		else if(e.getButton() == 3) { 
			
			
			
			if(!b.isFlagged()) {
				b.setIcon(new ImageIcon("flag.png"));
				b.setFlagged(true);
			} 
			else {
				b.setIcon(null);
				b.setFlagged(false);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
}