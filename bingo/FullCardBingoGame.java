package bingo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FullCardBingoGame implements Winnable {

	private boolean winnerDetermined;
	private List<BingoCard> bingoBoardList;
	BingoCard user;
	BingoCard computer;
	boolean[][] marked;
	private String userWins;
	private String computerWins;
	static Random randomNumber = new Random();
	private boolean isComputerWin = false;
	private boolean isUserWin = false;

	public FullCardBingoGame(BingoCard user, BingoCard computer) {
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
			gameOver();
		}
	}

	private boolean validateIfComputerWins(BingoCard bingoCard, int check) {
		boolean isComputerwin = false;
		bingoCard.mark(check);
		boolean[][] marked = bingoCard.getMarked();
		int count = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (marked[i][j] == true) {
					count++;
				}
			}
			if (count == 25) {
				isComputerwin = true;
				this.computerWins = "Computer Wins";
			}
		}
		
		return isComputerwin;
	}

	private boolean validateIfUserWins(BingoCard bingoCard, int check) {
		boolean isUserWin = false;
		bingoCard.mark(check);
		boolean[][] marked = bingoCard.getMarked();
		int count = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (marked[i][j] == true) {
					count++;
				}
				
			}
			if (count == 25) {
				isUserWin = true;
				this.userWins = "User Wins";
			}
		}
		return isUserWin;
	}

	public static int genRandomNum() {

		int randomNum = randomNumber.nextInt(75 - 1) + 1;

		return randomNum;
	}
}
