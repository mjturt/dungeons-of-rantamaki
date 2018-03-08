package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HandlerIO {
	public static String spath = FileSystems.getDefault().getPath("./sav/game.sav").toString();
	
	public HandlerIO() {
	}
	
	public static void writeHandler(Handler h) throws IOException{
		FileOutputStream outStream = new FileOutputStream(spath);
		ObjectOutputStream oos = new ObjectOutputStream(outStream);
		oos.writeObject(h);
		oos.close();
	}
	
	public static Handler readHandler() throws IOException, ClassNotFoundException, FileNotFoundException{
		FileInputStream inStream = new FileInputStream(spath);
		ObjectInputStream in = new ObjectInputStream(inStream);
		Handler readHandler = (Handler) in.readObject();
		in.close();
		return readHandler;
	}
}
