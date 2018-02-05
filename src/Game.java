import java.util.Scanner;

public class Game {

	Grid grid = new Grid();
	Scanner scanner = new Scanner(System.in);

	public void startGame() {
		grid.initialiseGrid();
		grid.placeRandomDestroyer(2);
		grid.placeRandomDestroyer(3);
		grid.placeRandomDestroyer(4);
		grid.placeRandomDestroyer(5);
		System.out.println(grid.displayGrid());
	}

	public void playGame() {
		int playerShotX = -1;
		int playerShotY = -1;
		for (int i = 0; i < grid.maximumShots; i++) {
			System.out.println("Shots remaining: " + (grid.maximumShots - i));
			System.out.print("Choose a row to fire on: ");
			playerShotX = scanner.nextInt() - 1;
			System.out.print("\nChoose a column to fire on: ");
			playerShotY = scanner.nextInt() - 1;
			grid.takeShot(playerShotX, playerShotY);
			System.out.println(grid.displayGrid());
		}

		System.out.println("Out of shots!");
		scanner.close();
	}

}
