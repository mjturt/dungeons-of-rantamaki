package gui;

import java.io.IOException;

import javax.swing.JFrame;

public class HandlerIOTest {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		System.out.println(frame);
		Handler h = new Handler(frame);
		try {
			HandlerIO.writeHandler(h);
		}
		catch(IOException ioe) {
			System.out.println("Something went wrong :(");
			ioe.printStackTrace();
		}
		
		try {
			Handler readHandler = HandlerIO.readHandler();
			System.out.println(readHandler == h);
			System.out.println(readHandler.toString());
		}
		catch(IOException ioe) {
			System.out.println("Something went wrong :(");
			ioe.printStackTrace();
		}
		catch(ClassNotFoundException ioe) {
			System.out.println("Something went wrong in class :(");
			ioe.printStackTrace();
		}
	}
}
