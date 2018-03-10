package combat;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * For getting items from pool of all items available in-game.
 */
public class ItemGenerator {
	private ArrayList<Consumable> Itemlist;
	private ArrayList<String> items;
	
	/**
	 * reads Items from itemlist as resource, for packing it to the JAR. Then uses BufferedReader to read the binary inputstream
	 * as string values, that are passed to appropriate parser to initialize the Consumable object. Assumes UTF-8 encoding for files.
	 * Uses current thread's context to get classloader for fetching the resourceStream.
	 */
	public ItemGenerator () {
		Itemlist = new ArrayList<Consumable>();
		items	 = new ArrayList<String>();
		String str;
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream("combat/itemlist");
			BufferedReader br = new BufferedReader(new InputStreamReader(is,StandardCharsets.UTF_8));
			if(is!=null) {
				while((str = br.readLine()) != null) {
					items.add(str);
				}
			}
			else {
				throw new FileNotFoundException();
			}
			for(String s: items) {
				Itemlist.add(parseLine(s));
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
	/**
	 * getItem retrieves a Consumable from pool of all known Consumables defined in itemlist
	 * 
	 * @param i index to get
	 * @return A new Consumable
	 */
	public Consumable getItem(int i) { 
		return Itemlist.get(i);
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
		return this.Itemlist.size();
	}
}