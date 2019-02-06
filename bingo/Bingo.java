package bingo;

import java.util.Scanner;

public class Bingo 
{
	public static void main(String[] args) 
	{
		BingoGame bingoGame = null;
		BingoXGame bingoXGame =null;
		FullCardBingoGame bingoFullGame =null;
		FourCornersBingoGame bingoFourCornersGame = null;
		Scanner scan = new Scanner(System.in);
		
		int option;
		do
		{
			System.out.println("What type of Bingo game would you like to play?");
			System.out.println("\t1. Standard Bingo\n\t2. Four Corners Bingo\n\t3. X Bingo\n\t4. Full Card Bingo");
			System.out.print("Select option: ");
		
			option = scan.nextInt();
		
			
			switch (option)
			{
				case 1:
					BingoCard user = new BingoCard();
					BingoCard computer = new BingoCard();
					bingoGame = new BingoGame(user, computer);
					break;
				case 2: 
					BingoCard userBingoFourCornerGame = new BingoCard();
					BingoCard computerBingoFourCornerFullGame = new BingoCard();
					bingoFourCornersGame = new FourCornersBingoGame(userBingoFourCornerGame, computerBingoFourCornerFullGame);
					break;
				case 3:
					BingoCard userBingoXGame = new BingoCard();
					BingoCard computerBingoXGame = new BingoCard();
					bingoXGame = new BingoXGame(userBingoXGame, computerBingoXGame);
					break;
				case 4:
					BingoCard userBingoFullGame = new BingoCard();
					BingoCard computerBingoFullGame = new BingoCard();
					bingoFullGame = new FullCardBingoGame(userBingoFullGame, computerBingoFullGame);
					break;
				default:
					System.out.println("Select an option between 1 and 4. \n");
			}
		} while (option < 1 || option > 4);
		
		if(bingoGame != null) 
			bingoGame.play(); // POLYMORPHIC STATEMENT
		
		if(bingoXGame!= null)
			bingoXGame.play(); //BingoXgame
		
		if(bingoFullGame != null) {
			bingoFullGame.play();
		}
		
		if(bingoFourCornersGame !=null) {
			bingoFourCornersGame.play();
		}
	}
}
