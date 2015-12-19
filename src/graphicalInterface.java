import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class graphicalInterface extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame Frame;
	public static JPanel content;
	public static JButton runButton;
	public static JButton openButton;
	public static JFileChooser fastqFile;
	public static JTextField fileName;

	
	public graphicalInterface(){
		Frame = new JFrame("RAPaSEC");
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fileName = new JTextField("File name:");
		fileName.setEditable(false);
	
	
		
		content = new JPanel();
		openButton = new JButton("Select a .fastq File");
		runButton = new JButton("RUN");
		openButton.addActionListener(new OpenL());
		
	
		content.add(openButton, BorderLayout.NORTH);
		content.add(runButton,BorderLayout.SOUTH);

		
		Container cp = getContentPane();
		cp.add(content, BorderLayout.SOUTH);
		
		content = new JPanel();
		content.setLayout(new GridLayout(2,1));
		content.add(fileName);

		cp.add(content, BorderLayout.NORTH);
		
		Frame.add(cp);
		Frame.pack();
		Frame.setVisible(true);
	}
	
    class OpenL implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		JFileChooser c = new JFileChooser();
    		int rVal = c.showOpenDialog(Frame);
    		if (rVal == JFileChooser.APPROVE_OPTION){
    			fileName.setText("FastQ File: " + c.getSelectedFile().toString());
    		}
    		if (rVal == JFileChooser.CANCEL_OPTION) {
    			fileName.setText("You pressed cancel.");
    	
    		}
    	}
    	
    }
	
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(Frame,"Welcome to Read Alignment Peak and Super Enhancer Calling!","RAPaSEC",JOptionPane.INFORMATION_MESSAGE);
		graphicalInterface mine = new graphicalInterface();
		
	}

}
