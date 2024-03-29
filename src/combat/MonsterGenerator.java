package combat;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

public class MonsterGenerator {
	private final ArrayList<Monster> monsterList;
	private final ArrayList<String> monsters;

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
			final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			final InputStream is = classloader.getResourceAsStream("combat/monsterlist");
			final BufferedReader br = new BufferedReader(new InputStreamReader(is,StandardCharsets.UTF_8));
			if(is!=null) {
				while((str = br.readLine()) != null) {
					monsters.add(str);
				}
				br.close();
				is.close();
				int i=-1;
				for(final String s: monsters) {
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
		catch(final NullPointerException npe) {
			System.out.println("Something went wrong :(");
			npe.printStackTrace();
		}
		catch(final IOException ioe) {
			System.out.println("Something went wrong :(");
			ioe.printStackTrace();
		}
	}
	//returns the monster by it's line number in a file
	//monster level will be player.level+-0.1player.level
	public Monster getMonster(int i, int lvl) { 
		final Random r = new Random();
		final Monster prototype = monsterList.get(i);
		int delta = (int)Math.floor(lvl/10.0);
		delta = (r.nextInt(delta+1-delta)+delta);
		for(int j=1;j<lvl + delta;j++) {
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
		final String[] monsterArray = line.split(",");
		return new Monster(Integer.valueOf(monsterArray[0]), monsterArray[1], Integer.valueOf(monsterArray[2]), Integer.valueOf(monsterArray[3]), Integer.valueOf(monsterArray[4]));
		}
	/**
	 * 
	 * @param m = the monster we're attaching these skills for
	 * @param inputline = the string that contains comma separated id's for attacks and spells,
	 * separated by semicolon
	 */
	private void addSkills(Monster m, String inputline) {
		final AttackIDList lista = new AttackIDList();
		final SpellIDList grimoire = new SpellIDList();
		final String[] skills = inputline.split(";");
		final String[] attacks = skills[0].split(",");
		final String[] spells = skills[1].split(",");
		for(final String a: attacks) {
			m.addAttack(lista.getAttack(Integer.valueOf(a)));
		}
		for(final String s: spells) {
			m.addSpell(grimoire.getSpell(Integer.valueOf(s)));
		}
	}
	
	public int getMonsterListSize() {
		return this.monsterList.size();
	}
}
