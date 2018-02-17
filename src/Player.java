/* TODO: Implement leveling up + stat gain
 * 
 */
package dungeons_of_rantamaki;
class Player extends Creature {
	private int experience;
	
	public Player(int hp, String name, int strength, int defense, int magic) {
		super(hp, name, strength, defense, magic);
		this.experience=0;
	}
	
	public int getExp() {
		return this.experience;
	}
	
	/**@param: gained experience
	 * @return: nothing
	 * just adds the experience to the player 
	 */
	public void addExp(int exp) {
		this.experience+=exp;
	}
}
