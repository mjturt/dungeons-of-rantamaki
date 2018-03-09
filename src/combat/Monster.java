package combat;

import java.util.Random;

public class Monster extends Creature {
	
	public Monster(int hp, String name, int strength, int defense, int magic) {
		super(hp, name, strength, defense, magic);
		this.level = 1;
	}

	/**
	 * @param target = the target creature
	 * @return the most hard-hitting attack
	 * 
	 */
	public Attack selectAttack(Creature target) {
		Random r = new Random();
		int decide = r.nextInt(101);
		if(decide<=75) {
			Attack maxPhys = null;
			Attack maxMagic = null;
			double maxPhysDamage = 0.0;
			double maxMagicDamage = 0.0;
			for(int i=0;i<this.moveList.size();i++) {
				Attack a = this.moveList.get(i);
				double aDmg = this.calculateDamage(target, a);
				if(aDmg > maxPhysDamage) {
					maxPhys = a;
					maxPhysDamage = aDmg;
				}
			}
			for(int j=0;j<this.spellBook.size();j++) {
				Attack m = this.spellBook.get(j);
				if(m.getMana()>this.mana) {
					continue;
				}
				double mDmg = this.calculateDamage(target, m);
				if(mDmg > maxMagicDamage) {
					maxMagic = m;
					maxMagicDamage = mDmg;				
				}
			}
			
			if(maxMagicDamage > maxPhysDamage) {
				return maxMagic;
			}
			else {
				return maxPhys;
			}
		}
		else {
			int attack = r.nextInt(this.moveList.size());
			return this.moveList.get(attack);
		}
	}
}
