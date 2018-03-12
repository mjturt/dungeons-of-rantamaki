package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class requests the home directory of the user from the system, and checks if the save directory
 * userhome/rantamaki exists, and handles the writing and reading of the game's status file.
 *
 */

public class HandlerIO {
	private static Path savePath = Paths.get(System.getProperty("user.home"), ".rantamaki"); //create path by requesting the home and adding /rantamaki/
	private static File savedir = new File(savePath.toString());							//to it
	public HandlerIO() {
	}
	
	
	/**
	 * 
	 * @param h the serializable handler we want to write for saving and loading the game's state
	 * @throws IOException if there's any issue with output streams
	 */
	public static void writeHandler(ArrayList<GameObject> objects) throws IOException{
		if(!Files.isDirectory(savePath)) {
			System.out.println("Creating savegame directory");
			System.out.println(savedir.toString());
			savedir.mkdir();
		}
		final FileOutputStream outStream = new FileOutputStream(savedir.toString() + "/game.sav");//this bit of code
		final ObjectOutputStream oos = new ObjectOutputStream(outStream);							//creates the file
		oos.writeObject(objects);																	//if it isn't found
		oos.close();
		outStream.close();
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException	if there's issue with reading 
	 * @throws ClassNotFoundException if the object can't be read
	 * @throws FileNotFoundException  if the savedirectory is not found
	 */
	public static ArrayList<GameObject> readHandler() throws IOException, ClassNotFoundException, FileNotFoundException{
		if(!Files.isDirectory(savePath)) {
			throw new FileNotFoundException(); //throw exception if the save folder is not found on the system
		}
		final FileInputStream inStream = new FileInputStream(savedir.toString() + "/game.sav");
		final ObjectInputStream in = new ObjectInputStream(inStream);
		final ArrayList<GameObject> readHandler = (ArrayList<GameObject>) in.readObject();
		for (final GameObject o : readHandler) {
			System.out.println("read object: " + o.toString());
		}
		in.close();
		inStream.close();
		return readHandler;
	}
}
