package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

public class HandlerIO {
	public static String sname;
	public static String spath;
	public static Handler handler;
	
	public HandlerIO() {
	}
	
	public static void writeHandler(Handler h) throws IOException{
		spath = Paths.get(".").toAbsolutePath().toString();//because java is an absolute bitch
		spath = spath.substring(0, (spath.length()-1)) + "save/"; //when it comes down to relative file paths
		sname = "game.sav";
		FileOutputStream outStream = new FileOutputStream(spath+sname);
		new ObjectOutputStream(outStream).writeObject(h);
	}
	
	public static Handler readHandler() throws IOException, ClassNotFoundException, FileNotFoundException{
		spath = Paths.get(".").toAbsolutePath().toString();//because java is an absolute bitch
		spath = spath.substring(0, (spath.length()-1)) + "save/"; //when it comes down to relative file paths
		sname = "game.sav";
		FileInputStream inStream = new FileInputStream(spath + sname);
		ObjectInputStream in = new ObjectInputStream(inStream);
		Handler readHandler = (Handler) in.readObject();
		return readHandler;
	}
}
