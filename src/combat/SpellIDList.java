package combat;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
			String spath = Paths.get(".").toAbsolutePath().toString();
			spath = spath.substring(0, (spath.length()-1)) + "src\\combat\\";
			Path aPath = Paths.get(spath, "spellist");
			List<String> spells = Files.readAllLines(aPath); //spellist is read as utf-8 and autoclosed
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
	
	private Attack parseLine(String line) {
		String[] lineArray = line.split(",");
		return new Attack(lineArray[0], AttackType.valueOf(lineArray[1]), Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3]));
	}
}