package world;


import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import combat.Player;
/**
 * Simple world class containing an array of Tiles making up the world
 */
public class World {
	private int width;
	private int height;
	private Tile[][] world;
	private int goalX;
	private int goalY;
	private int startX;
	private int startY;
	/**
	 * Due to restrictions in world creation (see world.World.createWorld()), the world should always be of odd width and height.
	 * Creates a new World, with given parameters and generates start and goal Tiles, also spawns monsters to Tiles based on world.World.spawnMonsters() (frequency can be adjusted).
	 * Last creates some opening to the maze to make the world feel less cramped. 
	 * @param width World width
	 * @param height World height
	 */
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

		this.createWorld();
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
	 * Creates a world array by calling world.World.recursiveCreateWorld
	 */
	private void createWorld() {
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
		recursiveCreateWorld(row, column);
	}

	
/**
 * Uses a simple recursion to create a mathematically sound maze (the generated maze is always solvable).
 * 
 * 
 * @param r = row Starting row
 * @param c = column Starting column
 */
	private void recursiveCreateWorld(int r, int c) {
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
					recursiveCreateWorld(r - 2, c);
				}
				break;
			case 2:
				if (c + 2 >= this.height - 1) {
					continue;
				}
				if (!this.world[r][c + 2].getPassable()) {
					this.world[r][c + 2].setPassable(true);
					this.world[r][c + 1].setPassable(true);
					recursiveCreateWorld(r, c + 2);
				}
				break;
			case 3:
				if (r + 2 >= this.width - 1) {
					continue;
			}
				if (!this.world[r + 2][c].getPassable()) {
					this.world[r + 2][c].setPassable(true);
					this.world[r + 1][c].setPassable(true);
					recursiveCreateWorld(r + 2, c);
				}
				break;
			case 4:
				if (c - 2 <= 0) {
					continue;
				}
				if (!this.world[r][c - 2].getPassable()) {
					this.world[r][c - 2].setPassable(true);
					this.world[r][c - 1].setPassable(true);
					recursiveCreateWorld(r, c - 2);
				}
				break;
			}
		}
	}

	/**
	 * Random direction generator for recursiveCreateWorld
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
	/**
	 * ASCII render method for playing the game in command line environment.
	 * 
	 * @param p Player object for referencing relative position.
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
	/**
	 * Prints the whole World array. For testing purposes only.
	 */
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
	/**
	 * Generates a sane starting position, so the player won't spawn inside a wall or outside the array.
	 * 
	 */
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
	/**
	 * Generates a goal to a random corner of the map
	 */
	public void generateGoal () {
		Random r = new Random();
		int n = r.nextInt(4);
		switch (n) {
		case 0: 
			this.goalX = 1;
			this.goalY = 1;
		case 1:
			this.goalX = this.width - 1;
			this.goalY = this.height - 1;
		case 2:
			this.goalX = 1;
			this.goalY = this.height -1;
		case 3:
			this.goalX = this.width - 1;
			this.goalY = 1;
		}
	}
	
	public int[] getGoal () {
		int[] goal = new int[2];
		goal[0] = this.goalY;
		goal[1] = this.goalX;
		return goal;
	}
	/**
	 * Sets world.Tile.monster to true randomly.
	 */
	private void spawnMonsters() {
		Random r = new Random();
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if ((r.nextDouble()) > 0.92 && this.world[i][j].getPassable()) {
					this.world[i][j].setMonster(true);
				}
			}
		}
	}
	/**
	 * A messy way for creating opening to the maze
	 */
	private void generateOpenings() {
		Random r = new Random();
		for (int i = 1; i < this.height - 1; i++) {
			for (int j = 1; j < this.width - 1; j++) {
				double rand = r.nextDouble();
				if (rand > 0.96) {
					int n = r.nextInt(5);
					if (n + i < this.height  - 1 && n + j < this.width - 1) {
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
