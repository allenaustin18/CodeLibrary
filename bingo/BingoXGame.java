package bingo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BingoXGame implements Winnable {

	private boolean winnerDetermined;
	private int playerCount;
	private List<BingoCard> bingoBoardList;
	BingoCard user;
	BingoCard computer;
	boolean[][] marked;
	private String userWins;
	private String computerWins;
	private boolean isComputerWin = false;
	private boolean isUserWin = false;
	static Random randomNumber = new Random();

	public BingoXGame(BingoCard user, BingoCard computer) {
		bingoBoardList = new ArrayList<BingoCard>();
		bingoBoardList.add(user);
		bingoBoardList.add(computer);
		this.user = user;
		this.computer = computer;
		this.winnerDetermined = false;
	}

	public int draw() {

		return 1;
	}

	@Override
	public boolean gameOver() {
		
		if (this.isUserWin && userWins != null && !this.userWins.equals("") && this.isComputerWin && computerWins != null
				&& !this.computerWins.equals("")) {
			System.out.println("BINGO!! TIED GAME!!");
		} else if (this.isUserWin && userWins != null && !this.userWins.equals("")) {
			System.out.println("BINGO!! User Wins :)");
			this.winnerDetermined = this.isUserWin;
		} else if (this.isComputerWin && computerWins != null && !this.computerWins.equals("")) {
			System.out.println("BINGO!! Computer Wins :(");
			this.winnerDetermined = this.isComputerWin;
		}
		
		return this.winnerDetermined;
	}

	@Override
	public void play() {
		System.out.println("USER");
		user.toString();
		System.out.println("COMPUTER");
		computer.toString();
		while (this.winnerDetermined == false) {
			int check = genRandomNum();
			// Scanner in = new Scanner(System.in);
			System.out.println("Calling:" + check);

			this.isUserWin = validateIfUserWins(user, check);
			this.isComputerWin = validateIfComputerWins(computer, check);
			System.out.println("USER");
			System.out.println("---------------");
			user.toString();
			System.out.println("COMPUTER");
			System.out.println("---------------");
			computer.toString();
			System.out.println("==================");
			this.winnerDetermined = gameOver();
		}
	}

	private boolean validateIfComputerWins(BingoCard bingoCard, int check) {
		boolean isComputerwin = false;
		bingoCard.mark(check);
		boolean[][] marked = bingoCard.getMarked();
		/*int count = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (marked[i][j] == true) {
					count++;
					break;
				}
			}
			if (count == 5) {
				isComputerwin = true;
				this.computerWins = "Computer Wins";
			}
		}*/
		
		int i = 0;
		int count = 0;
		// Checks the top left to bottom right diagnal for a win.
		while (i < 5 && marked[i][i] != false) {
			//System.out.println("top"+marked[i][i] );
			count++;
			i++;
		} // end while

		i = 5 - 1;
		int j = 0;
		// Checks the bottom left to top right diagnal for a win.
		while (i != -1 && marked[i][j] != false) {
			//System.out.println("right"+marked[i][j] );
			count++;
			i--;
			j++;
		}
		if (count == 10) {
			isComputerwin = true;
			this.computerWins = "Computer Wins";
		}
			//return true;
		
		return isComputerwin;
	}

	private boolean validateIfUserWins(BingoCard bingoCard, int check) {
		boolean isUserWin = false;
		bingoCard.mark(check);
		boolean[][] marked = bingoCard.getMarked();
		/*int count = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (marked[i][j] == true) {
					count++;
					break;
				}
				
			}
			if (count == 5) {
				isUserWin = true;
				this.userWins = "User Wins";
			}
		}*/
		
		int i = 0;
		int count = 0;
		// Checks the top left to bottom right diagnal for a win.
		while (i < 5 && marked[i][i] != false) {
			count++;
			i++;
		} // end while

		i = 5 - 1;
		int j = 0;
		// Checks the bottom left to top right diagnal for a win.
		while (i != -1 && marked[i][j] != false) {
			count++;
			i--;
			j++;
		}
		if (count == 10) {
			isUserWin = true;
			this.userWins = "User Wins";
		}
			//return true;
		
		return isUserWin;
	}

	public static int genRandomNum() {

		int randomNum = randomNumber.nextInt(75 - 1) + 1;

		return randomNum;
	}
}
