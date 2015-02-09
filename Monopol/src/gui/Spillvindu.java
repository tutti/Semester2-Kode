package gui;

import javax.swing.*;

public class Spillvindu {
	
	private static JFrame hovedvindu;
	
	static {
		hovedvindu = new JFrame("Spillvindu");
		hovedvindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hovedvindu.setLayout(null);
        
//        JLabel label = new JLabel("Hello World");
//        label.setBounds(100, 100, 200, 200);
//        hovedvindu.getContentPane().add(label);
        
        JButton testknapp = new JButton();
        testknapp.setBounds(100, 100, 20, 20);
        hovedvindu.getContentPane().add(testknapp);
 
        //Display the window.
        hovedvindu.setSize(800, 600);
        hovedvindu.setVisible(true);
        
        System.out.println("MOOOOOO");
	}
	
}
