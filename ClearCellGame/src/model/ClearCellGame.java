package model;

import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game.
 * We define an empty cell as BoardCell.EMPTY. An empty row is defined as one
 * where every cell corresponds to BoardCell.EMPTY.
 * 
 * @author Department of Computer Science, UMCP
 */

public class ClearCellGame extends Game {
	private int strategy;
	private Random random;
	private int score;

	/**
	 * Defines a board with empty cells. It relies on the super class constructor to
	 * define the board. The random parameter is used for the generation of random
	 * cells. The strategy parameter defines which clearing cell strategy to use
	 * (for this project it will be 1). For fun, you can add your own strategy by
	 * using a value different that one.
	 * 
	 * @param maxRows
	 * @param maxCols
	 * @param random
	 * @param strategy
	 */
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.strategy = 1;
		this.random = random;
		this.score = 0;
	}

	/**
	 * The game is over when the last board row (row with index board.length -1) is
	 * different from empty row.
	 */
	private boolean checkEmptyRow(BoardCell [] row) {
		for(int entry = 0; entry < getMaxCols(); entry++) {
			if (row[entry] != BoardCell.EMPTY) {
				return false;
			}
		}
		return true;
	}
	public boolean isGameOver() {
		if (checkEmptyRow(board[getMaxRows() - 1])) {
			return false;
		}else {
			return true;
		}
	}

	public int getScore() {
		return score;
	}

	/**
	 * This method will attempt to insert a row of random BoardCell objects if the
	 * last board row (row with index board.length -1) corresponds to the empty row;
	 * otherwise no operation will take place.
	 */
	public void nextAnimationStep() { 
		BoardCell[][] copy = new BoardCell[board.length][board[0].length]; 
		if (!isGameOver()) { 
			for (int row = 0; row < board.length; row++) { 
				for ( int col = 0; col < board[0].length; col++) { 
					if (row == 0) { 
						copy[row][col] = BoardCell.getNonEmptyRandomBoardCell(random); 
					}else { 
						copy[row][col] = board[row - 1][col]; 
					} 
				} 
			} 
			board = copy; 
		} 
	} 

	/**
	 * This method will turn to BoardCell.EMPTY the cell selected and any adjacent
	 * surrounding cells in the vertical, horizontal, and diagonal directions that
	 * have the same color. The clearing of adjacent cells will continue as long as
	 * cells have a color that corresponds to the selected cell. Notice that the
	 * clearing process does not clear every single cell that surrounds a cell
	 * selected (only those found in the vertical, horizontal or diagonal
	 * directions).
	 * 
	 * IMPORTANT: Clearing a cell adds one point to the game's score.<br />
	 * <br />
	 * 
	 * If after processing cells, any rows in the board are empty,those rows will
	 * collapse, moving non-empty rows upward. For example, if we have the following
	 * board (an * represents an empty cell):<br />
	 * <br />
	 * RRR<br />
	 * GGG<br />
	 * YYY<br />
	 * * * *<br/>
	 * <br />
	 * then processing each cell of the second row will generate the following
	 * board<br />
	 * <br />
	 * RRR<br />
	 * YYY<br />
	 * * * *<br/>
	 * * * *<br/>
	 * <br />
	 * IMPORTANT: If the game has ended no action will take place.
	 * 
	 * 
	 */
	private void clearUp (int rowIndex, int colIndex, BoardCell color) {
		if (board[rowIndex][colIndex] == color) {
			board[rowIndex][colIndex] = BoardCell.EMPTY;
			score++;
			if (rowIndex - 1 >= 0) {
				clearUp(rowIndex - 1, colIndex, color);
			}
		}
	}
	private void clearDown (int rowIndex, int colIndex, BoardCell color) {
		if (board[rowIndex][colIndex] == color) {
			board[rowIndex][colIndex] = BoardCell.EMPTY;
			score++;
			if (rowIndex + 1 < board.length) {
				clearDown(rowIndex + 1, colIndex, color);
			}
		}
	}
	private void clearUpLeft (int rowIndex, int colIndex, BoardCell color) {
		if (board[rowIndex][colIndex] == color) {
			board[rowIndex][colIndex] = BoardCell.EMPTY;
			score++;
			if (rowIndex - 1 >= 0 && colIndex - 1 >= 0) {
				clearUpLeft(rowIndex - 1, colIndex - 1, color);
			}
		}
	}
	private void clearUpRight (int rowIndex, int colIndex, BoardCell color) {
		if (board[rowIndex][colIndex] == color) {
			board[rowIndex][colIndex] = BoardCell.EMPTY;
			score++;
			if (rowIndex - 1 >= 0 && colIndex + 1 < board[rowIndex].length) {
				clearUpRight(rowIndex - 1, colIndex + 1, color);
			}
		}
	}
	private void clearDownLeft (int rowIndex, int colIndex, BoardCell color) {
		if (board[rowIndex][colIndex] == color) {
			board[rowIndex][colIndex] = BoardCell.EMPTY;
			score++;
			if (rowIndex + 1 < board.length && colIndex - 1 >= 0) {
				clearDownLeft(rowIndex + 1, colIndex - 1, color);
			}
		}
	}
	private void clearDownRight (int rowIndex, int colIndex, BoardCell color) {
		if (board[rowIndex][colIndex] == color) {
			board[rowIndex][colIndex] = BoardCell.EMPTY;
			score++;
			if (rowIndex + 1 < board.length && colIndex + 1 < board[rowIndex].length) {
				clearDownRight(rowIndex + 1, colIndex + 1, color);
			}
		}
	}
	private void clearLeft (int rowIndex, int colIndex, BoardCell color) {
		if (board[rowIndex][colIndex] == color) {
			board[rowIndex][colIndex] = BoardCell.EMPTY;
			score++;
			if (colIndex - 1 >= 0) {
				clearLeft(rowIndex, colIndex - 1, color);
			}
		}
	}
	private void clearRight (int rowIndex, int colIndex, BoardCell color) {
		if (board[rowIndex][colIndex] == color) {
			board[rowIndex][colIndex] = BoardCell.EMPTY;
			score++;
			if (colIndex + 1 < board[rowIndex].length) {
				clearRight(rowIndex, colIndex + 1, color);
			}
		}
	}

	private BoardCell[][] collapseCells(BoardCell[][] board) {
		BoardCell[][] output = new BoardCell[getMaxRows()][getMaxCols()];
		int firstRow = 0;
		int lastRow = getMaxRows() - 1;
		for (int entry = 0; entry < getMaxRows(); entry++) {
			if (checkEmptyRow(board[entry])) {
				output[lastRow] = board[entry];
				lastRow--;
			}else {
				output[firstRow] = board[entry];
				firstRow++;
			}	
		}
		return output;
	}
	public void processCell(int rowIndex, int colIndex) {
		if (!isGameOver()) {
			BoardCell color = board[rowIndex][colIndex];
			if (board[rowIndex][colIndex] != BoardCell.EMPTY) {
				board[rowIndex][colIndex] = BoardCell.EMPTY;
				score++;
				if (rowIndex > 0) {
					clearUp(rowIndex - 1, colIndex, color);
				}
				if (rowIndex < board.length - 1) {
					clearDown(rowIndex + 1, colIndex, color);
				}
				if (colIndex > 0) {
					clearLeft(rowIndex, colIndex - 1, color);
				}
				if (colIndex < board[rowIndex].length - 1) {
					clearRight(rowIndex, colIndex + 1, color);
				}
				if (rowIndex > 0 && colIndex < board[rowIndex].length - 1) {
					clearUpRight(rowIndex - 1, colIndex + 1, color);	
				}
				if (rowIndex > 0 && colIndex > 0) {
					clearUpLeft(rowIndex - 1, colIndex - 1, color);
				}
				if (rowIndex < board.length - 1 && colIndex > 0) {
					clearDownLeft(rowIndex + 1, colIndex - 1, color);
				}
				if (rowIndex < board.length - 1 && colIndex < board[rowIndex].length - 1) {
					clearDownRight(rowIndex + 1, colIndex + 1, color);	
				}	
			}
			board = collapseCells(board);
		}
	}
}