package combat;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MonsterGenerator {
	private ArrayList<Monster> monsterList;
	
	public MonsterGenerator() {
		monsterList = new ArrayList<Monster>();
		//Path mPath = Paths.get(System.getProperty("user.dir")+"/src/combat/monsterlist");
		Path mPath = FileSystems.getDefault().getPath("./src/combat/monsterlist");
		try {
			List<String> monsters = Files.readAllLines(mPath); //monsterlist is read line by line as String as utf-8 and autoclosed
			int i=-1;
			for(String s: monsters) {
				if (s.indexOf(";")==-1) { //check if it's monster statline
					monsterList.add(parseLine(s));
					i++;
				}
				else {
					addSkills(monsterList.get(i), s);
				}
				
			}
		}
		catch(IOException ioe) {
			System.out.println("Something went wrong :(");
			ioe.printStackTrace();
		}
	}
	//returns the monster by it's line number in a file
	//with level that ifs inputted, could use randomization?
	public Monster getMonster(int i, int lvl) { 
		Monster prototype = monsterList.get(i);
		for(int j=1;j<lvl;j++) {
			prototype.LevelUp();
		}
		return prototype;
	}
	/**
	 * 
	 * @param line = comma separated statline and name for the monster
	 * @return Monster that has stats read from comma separated values from the string
	 */
	private Monster parseLine(String line) {
		line = line.trim();
		String[] monsterArray = line.split(",");
		return new Monster(Integer.valueOf(monsterArray[0]), monsterArray[1], Integer.valueOf(monsterArray[2]), Integer.valueOf(monsterArray[3]), Integer.valueOf(monsterArray[4]));
		}
	/**
	 * 
	 * @param m = the monster we're attaching these skills for
	 * @param inputline = the string that contains comma separated id's for attacks and spells,
	 * separated by semicolon
	 */
	private void addSkills(Monster m, String inputline) {
		AttackIDList lista = new AttackIDList();
		SpellIDList grimoire = new SpellIDList();
		String[] skills = inputline.split(";");
		String[] attacks = skills[0].split(",");
		String[] spells = skills[1].split(",");
		for(String a: attacks) {
			m.addAttack(lista.getAttack(Integer.valueOf(a)));
		}
		for(String s: spells) {
			m.addSpell(grimoire.getSpell(Integer.valueOf(s)));
		}
	}
	
	public int getMonsterListSize() {
		return this.monsterList.size();
	}
}
