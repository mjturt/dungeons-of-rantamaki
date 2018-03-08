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

public class AttackIDList {
	private ArrayList<Attack> IDList;

	public AttackIDList() {
		IDList = new ArrayList<Attack>();
		try {
			Path aPath = FileSystems.getDefault().getPath("./src/combat/attacklist");
			List<String> attacks = Files.readAllLines(aPath); //attacklist is read line by line as String as utf-8 and autoclosed
			for(String s: attacks) {
				IDList.add(parseLine(s));
			}
		}
		catch(IOException ioe) {
			System.out.println("Something went wrong :(");
		}
		
	}
	//returns the attack by it's line number in a file
	public Attack getAttack(int i) { 
		return IDList.get(i);
	}
	/**
	 * 
	 * @param line = the attack's statline, comma separated values
	 * @return new AttackType.PHYSICAL attack 
	 */
	private Attack parseLine(String line) {
		line = line.trim();
		String[] lineArray = line.split(",");
		return new Attack(lineArray[0], AttackType.valueOf(lineArray[1]), Integer.parseInt(lineArray[2]));
	}
}
