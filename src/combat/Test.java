package combat;

import world.World;

class Test {
	public static void main(String[] args) {
		
		World maailma = new World(99, 99);
		Player pelaaja = new Player(10, "Testi", 10, 10, 20);
		maailma.getTile(0, 1).setPassable(true);
		maailma.getTile(maailma.getHeight()-1, maailma.getWidth()-2).setPassable(true);
		pelaaja.setLocation(1, 1);
		maailma.testPopulate(pelaaja);
	}
}
