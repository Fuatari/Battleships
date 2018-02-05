import java.util.Random;

public class Grid {

	private final int NORTH = 0;
	private final int EAST = 1;
	private final int SOUTH = 2;
	private final int WEST = 3;

	Random ran = new Random();
	Placement move = new Placement();

	int gridX = 6;
	int gridY = gridX;
	int maximumShots = 17 + ((gridX - 5) * gridX);

	char[][] gameGrid = new char[gridX][gridY];

	public void initialiseGrid() {
		// Loops through every column
		for (int i = 0; i < gameGrid.length; i++) {
			// Loops through the row
			for (int j = 0; j < gameGrid.length; j++) {
				// Initialises each cell as " "
				gameGrid[i][j] = ' ';
			}
		}
	}

	public int chooseRandomDirection() {
		return ran.nextInt(4);
	}

	public int ranPos() {
		return ran.nextInt(gameGrid.length);
	}

	public boolean checkIfOpen(int inputX, int inputY) {
		if (gameGrid[inputX][inputY] == ' ') {
			return true;
		}
		return false;
	}

	public boolean checkIfValidCell(int inputX, int inputY) {
		if (inputX >= 0 && inputX < gameGrid.length) {
			if (inputY >= 0 && inputY < gameGrid.length) {
				return true;
			}
		}
		return false;
	}

	public int checkIfValidDirection(int inputX, int inputY, int direction, int shipLength) {

		// WHATS GOING ON HERE

		int validPosition = -1;

		switch (direction) {
		case NORTH:
			if (inputY - shipLength >= 0) {
				validPosition = NORTH;
			}
		case EAST:
			if (inputX + shipLength < gameGrid.length) {
				validPosition = EAST;
			}
		case SOUTH:
			if (inputY + shipLength < gameGrid.length) {
				validPosition = SOUTH;
			}
		case WEST:
			if (inputX - shipLength >= 0) {
				validPosition = WEST;
			}
		}

		return validPosition;
	}

	public void placeRandomDestroyer(int inputLength) {
		int shipX = ranPos();
		int shipY = ranPos();
		int shipLength = inputLength - 1;
		boolean inBounds = false;

		while (!checkIfOpen(shipX, shipY)) {
			shipX = ranPos();
			shipY = ranPos();
		}

		gameGrid[shipX][shipY] = 'D';

		// Check if its in bounds
		while (!inBounds) {
			int loop = 1;
			int ranDirection = chooseRandomDirection();
			switch (checkIfValidDirection(shipX, shipY, ranDirection, shipLength)) {
			case NORTH:
				// Ships can currently overlap each other
				// checkIfOpen only looks at end points
				for (loop = 1; loop <= shipLength; loop++) {
					if (checkIfOpen(shipX, shipY - shipLength)) {
						gameGrid[shipX][shipY - loop] = 'D';
					}
					// These loops don't actually do what they should
					// inBounds can go into the previous if
					if (loop == shipLength) {
						inBounds = true;
					}
				}
				break;
			case EAST:
				for (loop = 1; loop <= shipLength; loop++) {
					if (checkIfOpen(shipX + shipLength, shipY)) {
						gameGrid[shipX + loop][shipY] = 'D';
					}
					if (loop == shipLength) {
						inBounds = true;
					}
				}
				break;
			case SOUTH:
				for (loop = 1; loop <= shipLength; loop++) {
					if (checkIfOpen(shipX, shipY + shipLength)) {
						gameGrid[shipX][shipY + loop] = 'D';
					}
					if (loop == shipLength) {
						inBounds = true;
					}
				}
				break;
			case WEST:
				for (loop = 1; loop <= shipLength; loop++) {
					if (checkIfOpen(shipX - shipLength, shipY)) {
						gameGrid[shipX - loop][shipY] = 'D';
					}
					if (loop == shipLength) {
						inBounds = true;
					}
				}
				break;
			}
		}
	}

	// X and Y are purposefully inverse here.
	public void takeShot(int playerShotY, int playerShotX) {
		if (checkIfValidCell(playerShotY, playerShotX)) {
			if (checkIfOpen(playerShotX, playerShotY)) {
				gameGrid[playerShotX][playerShotY] = '~';
				System.out.println("\n" + (playerShotY + 1) + ", " + (playerShotX + 1) + " --> Miss!\n");
			} else {
				if (gameGrid[playerShotX][playerShotY] == '#') {
					System.out.print("\nThe wreckage is blown to even smaller pieces.");
				}
				gameGrid[playerShotX][playerShotY] = '#';
				System.out.println("\n" + (playerShotY + 1) + ", " + (playerShotX + 1) + " --> Hit!\n");
			}
		} else {
			System.out.println("\n" + (playerShotY + 1) + ", " + (playerShotX + 1) + " --> Critical Miss!\n");
		}
	}

	public String displayGrid() {
		String mapOutput = "    ";
		for (int count = 0; count < gameGrid.length; count++) {
			mapOutput += (count + 1) + "    ";
		}
		mapOutput += "\n";
		// Loops through every row
		for (int i = 0; i < gameGrid.length; i++) {
			mapOutput += (i + 1) + " ";
			// Loops through the column
			for (int j = 0; j < gameGrid.length; j++) {
				// Prints the contents of each cell
				if (gameGrid[i][j] == 'D') {
					mapOutput += " [ ] ";
				} else {
					mapOutput += " [" + gameGrid[i][j] + "] ";
				}
			}
			// Adds a new line for each row
			mapOutput += "\n\n";
		}
		return mapOutput;
	}

}