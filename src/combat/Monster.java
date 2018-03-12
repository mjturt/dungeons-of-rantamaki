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
		final Random r = new Random();
		final int decide = r.nextInt(101);
		if(decide<=75) {
			Attack maxPhys = null;
			Attack maxMagic = null;
			double maxPhysDamage = 0.0;
			double maxMagicDamage = 0.0;
			for(int i=0;i<this.moveList.size();i++) {
				final Attack a = this.moveList.get(i);
				final double aDmg = this.calculateDamage(target, a);
				if(aDmg > maxPhysDamage) {
					maxPhys = a;
					maxPhysDamage = aDmg;
				}
			}
			for(int j=0;j<this.spellBook.size();j++) {
				final Attack m = this.spellBook.get(j);
				if(m.getMana()>this.mana) {
					continue;
				}
				final double mDmg = this.calculateDamage(target, m);
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
			final int attack = r.nextInt(this.moveList.size());
			return this.moveList.get(attack);
		}
	}
}
