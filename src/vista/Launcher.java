package vista;

import javax.swing.JFrame;

public class Launcher {

	public static void main(String[] args) {
		Marco mc = new Marco();
		mc.setVisible(true);

		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

