package combat;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
/**
 * For getting items from pool of all items available in-game.
 */
public class ItemGenerator {
	private ArrayList<Consumable> IDList;

	public ItemGenerator () {
		IDList = new ArrayList<Consumable>();
		try {
			Path aPath = FileSystems.getDefault().getPath("./src/combat/itemlist");
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
	/**
	 * getItem retrieves a Consumable from pool of all known Consumables defined in itemlist
	 * 
	 * @param i index to get
	 * @return A new Consumable
	 */
	public Consumable getItem(int i) { 
		return IDList.get(i);
	}
	/**
	 * Parses information from file
	 * 
	 * @param line To parse
	 * @return Consumable
	 */
	private Consumable parseLine(String line) {
		String[] lineArray = line.split(",");
		return new Consumable(lineArray[0],Integer.parseInt(lineArray[1]), Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3]));
	}
	/**
	 * Returns the size of the item pool.
	 * @return size of item pool
	 */
	public int getListSize() {
		return this.IDList.size();
	}
}