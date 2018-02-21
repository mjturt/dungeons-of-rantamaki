

import java.util.Random;

import dungeons_of_rantamaki.Tile;

import java.util.Collections;
import java.util.ArrayList;
/**
 * Simple world class
 * 
 * TODO: cleanup, refine
 */
public class World {
	private int width;
	private int height;
	private Tile[][] world;

	public World(int width, int height) {
		this.width = width;
		this.height = height;
		this.world = new Tile[height][width];
		
		/*
		 * for filling the array with tiles, should maybe move this to its own method
		 */
		for (int i = 0; i < this.world.length; i++) {
			for (int j = 0; j < this.world.length; j++) {
				this.world[i][j] = new Tile();
			}
		}

		this.populateWorld();

	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Tile getTile(int a, int b) {
		return world[a][b];
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
 */
	private void recursivePopulateWorld(int r, int c) {
		Integer[] rand = randDirections();

		for (int i = 0; i < rand.length; i++) {

			switch (rand[i].intValue()) {
			case 1:
				if (r - 2 <= 0) {
					continue;
				}
				if (!this.world[r - 2][c].getPassable()) {
					this.world[r - 2][c].setPassable(true);
					this.world[r - 1][c].setPassable(true);
					recursivePopulateWorld(r - 2, c);
				}
				break;
			case 2:
				if (c + 2 >= this.height - 1) {
					continue;
				}
				if (!this.world[r][c + 2].getPassable()) {
					this.world[r][c + 2].setPassable(true);
					this.world[r][c + 1].setPassable(true);
					recursivePopulateWorld(r, c + 2);
				}
				break;
			case 3:
				if (r + 2 >= this.width - 1) {
					continue;
			}
				if (!this.world[r + 2][c].getPassable()) {
					this.world[r + 2][c].setPassable(true);
					this.world[r + 1][c].setPassable(true);
					recursivePopulateWorld(r + 2, c);
				}
				break;
			case 4:
				if (c - 2 <= 0) {
					continue;
				}
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
	/*
	 * test method, remove from final
	 */
	public void testPopulate() {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.world[i][j].getPassable()) {
					System.out.print(" " + " ");
				} else {
					System.out.print("#" + " ");
				}
			}
			System.out.println();
		}
	}
}
