package combat;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MonsterGenerator {
	private ArrayList<Monster> monsterList;
	private ArrayList<String> monsters;

	/**
	 * reads monsters from monsterlist as resource, for packing it to the JAR. Then uses BufferedReader to read the binary inputstream
	 * as string values, that are passed to appropriate parser to initialize the Monster object. Assumes UTF-8 encoding for files.
	 * Uses current thread's context to get classloader for fetching the resourceStream.
	 */
	public MonsterGenerator() {
		monsterList = new ArrayList<Monster>();
		monsters = new ArrayList<String>();
		String str;
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream("combat/monsterlist");
			BufferedReader br = new BufferedReader(new InputStreamReader(is,StandardCharsets.UTF_8));
			if(is!=null) {
				while((str = br.readLine()) != null) {
					monsters.add(str);
				}
				int i=-1;
				for(String s: monsters) {
					System.out.println(s.indexOf(";"));
					if (s.indexOf(";")==-1) { //check if it's monster statline
						monsterList.add(parseLine(s));
						i++;
					}
					else {
						addSkills(monsterList.get(i), s);
					}
				}
			}
			else {
				throw new FileNotFoundException();
			}
		}
		catch(NullPointerException npe) {
			System.out.println("Something went wrong :(");
			npe.printStackTrace();
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
		try {
			return new Monster(Integer.valueOf(monsterArray[0]), monsterArray[1], Integer.valueOf(monsterArray[2]), Integer.valueOf(monsterArray[3]), Integer.valueOf(monsterArray[4]));
		}
		catch(ArrayIndexOutOfBoundsException oob) {
			System.out.println(line);
			oob.printStackTrace();
			return null;
			}
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
