package world;


import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import combat.Player;
/**
 * Simple world class
 * 
 * TODO: cleanup, refine
 */
public class World {
	private int width;
	private int height;
	private Tile[][] world;
	private int goalX;
	private int goalY;
	private int startX;
	private int startY;

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
		this.generateStart();
		this.generateGoal();
		this.generateOpenings();
		this.spawnMonsters();

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
	public void render(Player p) {
		int left = 0;
		int right = 0;
		int up = 0;
		int down = 0;
		
		if (p.getY() - 10 < 0) {
			up= 0;
		} else {
			up = p.getY() - 10;
		}
		
		if (p.getY() + 10 > this.height) {
			down = this.height;
		} else {
			down = p.getY() + 10;
		}
		
		if (p.getX() - 10 < 0) {
			left = 0;
		} else {
			left = p.getX() - 10;
		}
		
		if (p.getX() + 10 > this.width) {
			right = this.width;
		} else {
			right = p.getX() + 10;
		}
		for (int y = up; y < down; y++) {
			for (int x = left; x < right; x++) {
				if (y == p.getY() && x == p.getX()) {
					System.out.print("P" + " ");
				} else {
					if (y == this.goalY && x == this.goalX) {
						System.out.print("G" + " ");
					} else if (y == this.startY && x == this.startX) {
						System.out.print("S" + " ");
					} else {
						if (this.world[y][x].getPassable()) {
							System.out.print(" " + " ");
						} else {
							System.out.print("#" + " ");
						}
					}
				}
				
			}
			System.out.println();
		}
	}
	
	public void printWorld() {
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
	
	public void generateStart () {
		Random r = new Random();
		this.startY = r.nextInt(this.height);
		this.startX = r.nextInt(this.width);
		while (!this.getTile(this.startY, this.startX).getPassable()) {
			this.startY = r.nextInt(this.height);
			this.startX = r.nextInt(this.width);
		}
	}
	
	public int[] getStart () {
		int[] start = new int[2];
		start[0] = this.startY;
		start[1] = this.startX;
		return start;
	}
	
	public void generateGoal () {
		Random r = new Random();
		this.goalY = r.nextInt(this.height);
		this.goalX = r.nextInt(this.width);
		while (!this.getTile(this.goalY, this.goalX).getPassable() && this.startX == this.goalX && this.startY == this.goalY) {
			this.goalY = r.nextInt(this.height);
			this.goalX = r.nextInt(this.width);
		}
	}
	
	public int[] getGoal () {
		int[] goal = new int[2];
		goal[0] = this.goalY;
		goal[1] = this.goalX;
		return goal;
	}
	
	public void spawnMonsters() {
		Random r = new Random();
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if ((r.nextDouble()) > 0.92 && this.world[i][j].getPassable()) {
					this.world[i][j].setMonster(true);
				}
			}
		}
	}
	/*
	 * A horrible way to generate opening to the maze, needs cleanup. 
	 */
	public void generateOpenings() {
		Random r = new Random();
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				double rand = r.nextDouble();
				if (rand > 0.96) {
					int n = r.nextInt(5);
					if (n + i < this.height - 1 && n + j < this.width - 1) {
						for (int k = i; k < i + n; k++) {
							for (int l = j; l < j + n; l++) {
								this.world[k][l].setPassable(true);
							}
						}
					}
				}
			}
		}
	}
}
