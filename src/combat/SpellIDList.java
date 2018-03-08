package combat;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
/*
 * Simple class to contain a list of all known attacks to the game engine
 * 
 *
 */

public class SpellIDList {
	private ArrayList<Attack> IDList;

	public SpellIDList() {
		IDList = new ArrayList<Attack>();
		try {
			Path sPath = FileSystems.getDefault().getPath("./src/combat/spelllist");
			List<String> spells = Files.readAllLines(sPath); //spellist is read line by line as String as utf-8 and autoclosed
			for(String s: spells) {
				IDList.add(parseLine(s));
			}
		}
		catch(IOException ioe) {
			System.out.println("Something went wrong :(");
		}
		
	}
	//returns the attack by it's line number in a file
	public Attack getSpell(int i) { 
		return IDList.get(i);
	}
	/**
	 * 
	 * @param line = statline for the spell
	 * @return new AttackType.MAGICAL attack
	 */
	private Attack parseLine(String line) {
		line = line.trim();
		String[] lineArray = line.split(",");
		return new Attack(lineArray[0], AttackType.valueOf(lineArray[1]), Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3]));
	}
}