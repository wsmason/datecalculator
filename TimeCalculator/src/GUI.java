import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GUI {

	static void launchFrame() {
		JFrame frame = new JFrame("Date Calculator");
		JButton button = new JButton();
		frame.getContentPane().add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); 
		
		button.setText("test");
		ActionListener l = null;
		button.addActionListener(l);
		//test asdf afsddf 
		//master without windowbuilder
		//master without windowbuilder

	}

}
