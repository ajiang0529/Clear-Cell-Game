package tests;

import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.BoardCell;
import model.ClearCellGame;
import model.Game;

/* The following directive executes tests in sorted order */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {

	/* Remove the following test and add your tests */
	private static String getBoardStr(Game game) {
		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();

		String answer = "Board(Rows: " + maxRows + ", Columns: " + maxCols + ")\n";
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++) {
				answer += game.getBoardCell(row, col).getName();
			}
			answer += "\n";
		}

		return answer;
	}
	@Test
	public void testingEmptyBoard() {
		int maxRows = 4, maxCols = 5, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
		String answer = getBoardStr(ccGame);

		System.out.println(answer);
	}
	@Test
	public void testingAnimationSteps() {
		int maxRows = 4, maxCols = 5, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
		ccGame.nextAnimationStep();
		ccGame.nextAnimationStep();

		String answer = getBoardStr(ccGame);
		System.out.println(answer);
	}
	@Test
	public void testingHorizontalCells() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(1, 4);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		System.out.println(answer);
	}
	@Test
	public void testingVerticalCells() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setColWithColor(2, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(1, 2);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		System.out.println(answer);
	}
	@Test
	public void testingDiagonalCells() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setColWithColor(2, BoardCell.YELLOW);
		ccGame.setColWithColor(3, BoardCell.YELLOW);
		ccGame.setColWithColor(4, BoardCell.YELLOW);
		ccGame.setColWithColor(5, BoardCell.YELLOW);
		ccGame.setColWithColor(6, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(4, 4);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		System.out.println(answer);
	}
	@Test
	public void testingGetScore() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(1, 4);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		answer += "\n" + ccGame.getScore(); 
		System.out.println(answer);
	}
	@Test
	public void testingCollapseCells() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.EMPTY);

		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(1, 4);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		System.out.println(answer);
	}
	@Test
	public void testingGetMaxCols() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.EMPTY);
		System.out.println(ccGame.getMaxCols());
	}
	@Test
	public void testingGetMaxRows() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.EMPTY);
		System.out.println(ccGame.getMaxRows());
	}
	@Test
	public void testingSetAndGetBoardCell() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setBoardCell(4, 4, BoardCell.GREEN);
		System.out.println(ccGame.getBoardCell(4, 4));
	}
	@Test
	public void testingSetRowWithColor() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(4, BoardCell.GREEN);
		System.out.println(ccGame.getBoardCell(4, 4));
	}
	@Test
	public void testingSetColWithColor() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setColWithColor(4, BoardCell.GREEN);
		System.out.println(ccGame.getBoardCell(4, 4));
	}
	@Test
	public void testingisGameOver() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.RED);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
		System.out.println(ccGame.isGameOver());
	}
}
