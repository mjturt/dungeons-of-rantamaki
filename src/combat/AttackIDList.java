package combat;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/*
 * Simple class to contain a list of all known attacks to the game engine
 * 
 *
 */

public class AttackIDList {
	private ArrayList<Attack> IDList;
	private ArrayList<String> attacks;
	
	/**
	 * reads attacks from attacklist as resource, for packing it to the JAR. Then uses BufferedReader to read the binary inputstream
	 * as string values, that are passed to appropriate parser to initialize the Attack object. Assumes UTF-8 encoding for files.
	 * Uses current thread's context to get classloader for fetching the resourceStream.
	 */
	public AttackIDList() {
		IDList = new ArrayList<Attack>();
		attacks = new ArrayList<String>();
		String str;
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream("combat/attacklist");
			BufferedReader br = new BufferedReader(new InputStreamReader(is,StandardCharsets.UTF_8));
			if(is!=null) {
				while((str = br.readLine()) != null) {
					attacks.add(str);
				}
			}
			else {
				throw new FileNotFoundException();
			}
			for(String s: attacks) {
				IDList.add(parseLine(s));
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
