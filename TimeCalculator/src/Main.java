import java.awt.BorderLayout;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;



public class Main extends GUI{
	
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
		launchFrame();
		
//		int[] date1 = getDate1();
//		for (int i=0; i< date1.length; i++) {
//		System.out.println(date1[i]);
//		}
	}
}

