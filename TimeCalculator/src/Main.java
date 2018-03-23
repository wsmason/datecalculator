import java.awt.BorderLayout;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;



public class Main {
	
	static int[] getDate1() {
		int[] date = new int[3];
		Scanner scanner = new Scanner(System.in);
		String dateInput = scanner.next();
		System.out.println(dateInput);
		scanner.close();
		//entry 0: month; entry 1: day; entry 2: year
		date[0] = Integer.parseInt(dateInput.substring(0, 2));
		date[1] = Integer.parseInt(dateInput.substring(3, 5));
		date[2] = Integer.parseInt(dateInput.substring(6, 10));
		System.out.println(date);
		return date;
	}
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Date Calculator");
		JButton button = new JButton();
		frame.add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		button.setSize(50, 50);
		frame.setVisible(true);
		int[] date1 = getDate1();
		for (int i=0; i< date1.length; i++) {
		System.out.println(date1[i]);
		}
	}
}

