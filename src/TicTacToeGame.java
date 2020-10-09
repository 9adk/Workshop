import java.util.Scanner;

public class TicTacToeGame {
	Scanner scanner = new Scanner(System.in);
	static String symbol1;
	public static String[] tictactoe = new String[10];
	static String computer;

	/**
	 * Usecase 1
	 */
	public void createBoard() {
		for (int i = 1; i < 10; i++) {
			tictactoe[i] = " ";
		}
	}

	/**
	 * Usecase2
	 */
	public void chooseSymbol() {

		do {
			System.out.println("Enter the symbol for player from X or O");
			symbol1 = scanner.nextLine();
			if (symbol1.equals("X")) {
				computer = "O";
				break;
			} else if (symbol1.equals("O")) {
				computer = "X";
				break;
			} else {
				System.out.println("Invalid input");
			}
		} while (symbol1 != "O" || symbol1 != "X");
	}

	/**
	 * Uecase3
	 */
	public void showBoard() {
		for (int i = 1; i < 10; i++) {
			System.out.print(tictactoe[i]);
			if ((2 * i) % 3 != 0) {
				System.out.print(" | ");
			}
			if (i % 3 == 0 && i != 9) {
				System.out.println("\n----------");
			}
		}
		System.out.println();
	}

	/**
	 * Usecase4 and Usecase5
	 * 
	 * @param position
	 * @param whoIsPlaying
	 * @return
	 */
	public String makeMove(int position, String whoIsPlaying, String symbol) {
		boolean check = isWinner(tictactoe,symbol);
		boolean tie = isTie();
		if (tictactoe[position] == " " && check == false && tie == false) {
			if (whoIsPlaying.equals("User")) {
				tictactoe[position] = symbol1;
				whoIsPlaying = "Computer";
			} else {
				tictactoe[position] = computer;
				whoIsPlaying = "User";
			}
		} else {
			System.out.println("Enter empty position");
		}
		return whoIsPlaying;
	}
	/**
	 * Usecase6
	 * 
	 * @return
	 */
	public String playFirst() {
		int chance = (int) Math.floor(Math.random() * 10) % 2;
		String startFirst;
		String toss;
		if (chance == 1) {
			toss = "Heads";
			startFirst = "User";
		} else {
			toss = "Tails";
			startFirst = "Computer";
		}
		System.out.println(toss + "..." + startFirst + " will play first");
		return startFirst;
	}


	/**
	 * Uscase7
	 * @return
	 */
	public boolean isTie() {
		boolean flag = true;
		for (int i = 1; i < tictactoe.length; i++) {
			if (tictactoe[i] == " ") {
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * Usecase7
	 * @param symbol
	 * @return
	 */
	public boolean isWinner(String[] board, String symbol) {
		boolean flag = false;
		if (board[1].equals(symbol) && board[2].equals(symbol) && board[3].equals(symbol)) {
			flag = true;
		} else if (board[4].equals(symbol) && board[5].equals(symbol) && board[6].equals(symbol)) {
			flag = true;
		} else if (board[7].equals(symbol) && board[8].equals(symbol) && board[9].equals(symbol)) {
			flag = true;
		} else if (board[1].equals(symbol) && board[5].equals(symbol) && board[9].equals(symbol)) {
			flag = true;
		} else if (board[3].equals(symbol) && board[5].equals(symbol) && board[7].equals(symbol)) {
			flag = true;
		} else if (board[1].equals(symbol) && board[4].equals(symbol) && board[7].equals(symbol)) {
			flag = true;
		} else if (board[2].equals(symbol) && board[5].equals(symbol) && board[8].equals(symbol)) {
			flag = true;
		} else if (board[3].equals(symbol) && board[6].equals(symbol) && board[9].equals(symbol)) {
			flag = true;
		}
		return flag;
	}
	/**Usecase8
	 * @return
	 */
	public int winPosition() {
		String[] copyOfBoard = tictactoe.clone();
		for(int i = 1; i < copyOfBoard.length; i++){
			if(copyOfBoard[i].equals(" ")) {
				copyOfBoard[i] = computer;
				if(isWinner(copyOfBoard,computer)) {
					return i;
				}
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		TicTacToeGame tictac = new TicTacToeGame();
		Scanner scan = new Scanner(System.in);
		tictac.createBoard();
		tictac.chooseSymbol();
		String player = tictac.playFirst();
		do {
			if (player.equals("Computer")) {
				int compsPosition = tictac.winPosition();
				player = tictac.makeMove(compsPosition, player, computer);
				tictac.showBoard();
			} else {
				System.out.println("Enter the position for move");
				int position = scan.nextInt();
				scan.nextLine();
				player = tictac.makeMove(position, player, symbol1);
				tictac.showBoard();
			}
			if (tictac.isWinner(tictactoe,symbol1) == true) {
				System.out.println("Winner is User");
				break;
			} else if (tictac.isWinner(tictactoe,computer) == true) {
				System.out.println("Winner is Computer");
				break;
			}
		} while (tictac.isTie() == false);
		tictac.showBoard();
		scan.close();
	}
}
