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

public class ItemGenerator {
	private ArrayList<Consumable> IDList;

	public ItemGenerator () {
		IDList = new ArrayList<Consumable>();
		try {
			String spath = Paths.get(".").toAbsolutePath().toString();
			spath = spath.substring(0, (spath.length()-1)) + "src/combat/";
			Path aPath = Paths.get(spath, "itemlist");
			List<String> attacks = Files.readAllLines(aPath); //itemlist is read as utf-8 and autoclosed
			for(String s: attacks) {
				IDList.add(parseLine(s));
			}
		}
		catch(IOException ioe) {
			System.out.println("Something went wrong :(");
			ioe.printStackTrace();
		}
		
	}
	//returns the attack by it's line number in a file
	public Consumable getItem(int i) { 
		return IDList.get(i);
	}
	
	private Consumable parseLine(String line) {
		String[] lineArray = line.split(",");
		return new Consumable(lineArray[0],Integer.parseInt(lineArray[1]), Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3]));
	}
	
	public int getListSize() {
		return this.IDList.size();
	}
}