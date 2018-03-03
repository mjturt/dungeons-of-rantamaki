package combat;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/*
 * Simple class to contain a list of all known attacks to the game engine
 * 
 *
 */

public class AttackIDList {
	private ArrayList<Attack> IDList;
	
	public AttackIDList() {
		try {
			List<String> attacks = Files.readAllLines(Paths.get("attacklist")); //attacklist is read as utf-8 and autoclosed
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
	
	private Attack parseLine(String line) {
		String[] lineArray = line.split(",");
		if(line.length() > 3 && lineArray[3] != null) {
			return new Attack(lineArray[0], AttackType.valueOf(lineArray[1]), Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3]));
		}
		else {
			return new Attack(lineArray[0], AttackType.valueOf(lineArray[1]), Integer.parseInt(lineArray[2]));
		}
	}
}

