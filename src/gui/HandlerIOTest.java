package gui;

import java.io.IOException;
import java.util.ArrayList;

public class HandlerIOTest {
	public static void main(String[] args) {
		try {
			HandlerIO.writeHandler(new ArrayList<GameObject>());
		}
		catch(final IOException ioe) {
			System.out.println("Something went wrong :(");
			ioe.printStackTrace();
		}
		
		try {
			final ArrayList<GameObject> readHandler = HandlerIO.readHandler();
			System.out.println(readHandler);
			System.out.println(readHandler.toString());
		}
		catch(final IOException ioe) {
			System.out.println("Something went wrong :(");
			ioe.printStackTrace();
		}
		catch(final ClassNotFoundException ioe) {
			System.out.println("Something went wrong in class :(");
			ioe.printStackTrace();
		}
	}
}
