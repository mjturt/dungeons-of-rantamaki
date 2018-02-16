package dungeons_of_rantamaki;

import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;

/**
 * @author vpeex
 * 
 *
 *         A simple world class consisting of an array of Tiles.
 */
public class World {
	int width = 100;
	int height = 100;
	private Tile[][] world;

	public World() {
		this.world = new Tile[height][width];
		for (int i = 0; i < this.world.length; i++) {
			for (int j = 0; j < this.world.length; j++) {
				this.world[i][j] = new Tile();
			}
		}

		this.populateWorld();

	}

	/**
	 * public method for populating the world array
	 */
	private void populateWorld() {
		Random r = new Random();
		int row = r.nextInt(this.height);
		while (row % 2 == 0) {
			row = r.nextInt(this.height);
		}

		int column = r.nextInt(this.width);
		while (column % 2 == 0) {
			column = r.nextInt(this.width);
		}
		this.world[row][column].setPassable(true);
		recursivePopulateWorld(row, column);
	}

	/**
	 * @param r = row
	 * @param c = column
	 *
	 *            private method for recursively creating a maze for the world array
	 */
	private void recursivePopulateWorld(int r, int c) {
		Integer[] rand = randDirections();

		for (int i = 0; i < rand.length; i++) {

			switch (rand[i].intValue()) {
			case 1:
				if (r - 2 <= 0)
					continue;
				if (!this.world[r - 2][c].getPassable()) {
					this.world[r][c + 2].setPassable(true);
					this.world[r][c + 1].setPassable(true);
					recursivePopulateWorld(r - 2, c);
				}
				break;
			case 2:
				if (c + 2 >= this.height - 1)
					continue;
				if (!this.world[r][c + 2].getPassable()) {
					this.world[r][c + 2].setPassable(true);
					this.world[r][c + 1].setPassable(true);
					recursivePopulateWorld(r, c + 2);
				}
				break;
			case 3:
				if (r + 2 >= this.width - 1)
					continue;
				if (!this.world[r + 2][c].getPassable()) {
					this.world[r + 2][c].setPassable(true);
					this.world[r + 1][c].setPassable(true);
					recursivePopulateWorld(r + 2, c);
				}
				break;
			case 4:
				if (c - 2 <= 0)
					continue;
				if (!this.world[r][c - 2].getPassable()) {
					this.world[r][c - 2].setPassable(true);
					this.world[r][c - 1].setPassable(true);
					recursivePopulateWorld(r, c - 2);
				}
				break;
			}
		}
	}

	/**
	 * @return list of random directions (1-4), used by recursivePopulateWorld
	 */
	private Integer[] randDirections() {
		ArrayList<Integer> directions = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			directions.add(i + 1);
		}
		Collections.shuffle(directions);

		return directions.toArray(new Integer[4]);
	}

	public void testPopulate() {
		for (int i = 0; i < this.world.length; i++) {
			for (int j = 0; j < this.world[0].length; j++) {
				if (this.world[i][j].getPassable()) {
					System.out.print("+" + " ");
				} else {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}
	}
}
