import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class graphicalInterface extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//File selection parameters
	public static JFrame Frame;
	public static JPanel content;
	public static JButton runButton;
	public static JButton openTreatmentButton;
	public static JButton openControlButton;
	public static JTextField TreatmentFileName;
	public static JTextField ControlFileName;
	
	//Alignment parameters
	public static JLabel alignmentHeader;
	public static JCheckBox alignmentSettingsDefault;
	public static JCheckBox alignmentSettingsPresets;
	public static ButtonGroup presetOptions;
	public static JRadioButton verySensitive;
	public static JRadioButton sensitive;
	public static JRadioButton veryFast;
	public static JRadioButton fast;
	public static JCheckBox local;
	public final JTextField alignmentParameters;
	public static JLabel alignmentParams;
	
	//Read Filtering Parameter
	public static JLabel readFiltering;
	public static JCheckBox filterReads;

	
	public graphicalInterface(){
		Frame = new JFrame("RAPaSEC");
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TreatmentFileName = new JTextField("Treatment .fastq:");
		TreatmentFileName.setEditable(false);
		ControlFileName = new JTextField("Control .fastq:");
		ControlFileName.setEditable(false);
	
	
		
		content = new JPanel();
		openTreatmentButton = new JButton("Select a Treatment File");
		openControlButton = new JButton("Select a Control File");
		
		openTreatmentButton.addActionListener(new OpenTreatment());
		openControlButton.addActionListener(new OpenControl());
	
		
		
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(5,1));

		
		content = new JPanel();
		content.setLayout(new GridLayout(2,2));
		content.add(TreatmentFileName);
		content.add(openTreatmentButton);
		content.add(ControlFileName);
		content.add(openControlButton);
		content.setBorder(BorderFactory.createLineBorder(Color.black));		
		cp.add(content);
		
		//Alignment Panel
		alignmentHeader = new JLabel("Bowtie Alignment Parameters");
		alignmentSettingsDefault = new JCheckBox("Default Alignment Settings", true);
		alignmentSettingsPresets = new JCheckBox("Preset Alignment Parameters",true);
		presetOptions = new ButtonGroup();
		sensitive = new JRadioButton("--sensitive Preset",false);
		verySensitive = new JRadioButton("--very-sensitive Preset",true);
		fast = new JRadioButton("--fast Preset");
		veryFast = new JRadioButton("--very-fast Preset");
		local = new JCheckBox("--local Preset",true);
		alignmentParameters = new JTextField();
		presetOptions.add(sensitive);
		presetOptions.add(verySensitive);
		presetOptions.add(fast);
		presetOptions.add(veryFast);
		alignmentParams = new JLabel("Enter specific alignment commands:");
		
	
		alignmentSettingsPresets.setEnabled(false);
		sensitive.setEnabled(false);
		verySensitive.setEnabled(false);
		fast.setEnabled(false);
		veryFast.setEnabled(false);
		local.setEnabled(false);
		alignmentParameters.setEnabled(false);
		
	
		
		ActionListener defaultListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (!selected){
					alignmentSettingsPresets.setEnabled(true);
					sensitive.setEnabled(true);
					verySensitive.setEnabled(true);
					verySensitive.setSelected(false);
					fast.setEnabled(true);
					veryFast.setEnabled(true);
					local.setEnabled(true);
					local.setSelected(false);
					presetOptions.clearSelection();
					
				}
				else{
					alignmentSettingsPresets.setEnabled(false);
					alignmentSettingsPresets.setSelected(true);
					sensitive.setEnabled(false);
					verySensitive.setEnabled(false);
					verySensitive.setSelected(true);
					fast.setEnabled(false);
					veryFast.setEnabled(false);
					local.setEnabled(false);
					local.setSelected(true);
				}
			}
		};
		alignmentSettingsDefault.addActionListener(defaultListener);
		
		ActionListener presetsListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (!selected){
					sensitive.setEnabled(false);
					verySensitive.setEnabled(false);
					fast.setEnabled(false);
					veryFast.setEnabled(false);
					local.setEnabled(false);
					local.setSelected(false);
					presetOptions.clearSelection();
					alignmentParameters.setEnabled(true);
					
				}
				else{
					sensitive.setEnabled(true);
					verySensitive.setEnabled(true);
					fast.setEnabled(true);
					veryFast.setEnabled(true);
					local.setEnabled(true);
					alignmentParameters.setEnabled(false);
					
				}
			}
		};
		
		
		alignmentSettingsPresets.addActionListener(presetsListener);
		
		content = new JPanel();
		GroupLayout alignmentParamLayout = new GroupLayout(content);
		content.setLayout(alignmentParamLayout);
		alignmentParamLayout.setAutoCreateGaps(true);
		alignmentParamLayout.setAutoCreateContainerGaps(true);
		
		alignmentParamLayout.setHorizontalGroup(alignmentParamLayout.createSequentialGroup()
												.addGroup(alignmentParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(alignmentHeader)
												.addComponent(alignmentSettingsDefault)
												.addComponent(alignmentParams))
												.addGroup(alignmentParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(alignmentSettingsPresets)
												.addComponent(local)
												.addComponent(alignmentParameters))
												.addGroup(alignmentParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(sensitive)
												.addComponent(verySensitive)
												.addComponent(fast)
												.addComponent(veryFast))
		);
		alignmentParamLayout.setVerticalGroup(alignmentParamLayout.createSequentialGroup()
												.addComponent(alignmentHeader)
												.addGroup(alignmentParamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(alignmentSettingsDefault)
												.addComponent(alignmentSettingsPresets)
												.addComponent(sensitive))
												.addGroup(alignmentParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)  
												.addComponent(local)
												.addComponent(verySensitive))
												.addComponent(fast)
												.addComponent(veryFast)
												.addGroup(alignmentParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(alignmentParams)
												.addComponent(alignmentParameters))
												
												
		);
												
		
		
		content.setBorder(BorderFactory.createLineBorder(Color.black));
		cp.add(content);
		
		//Read Filtering Panel
		readFiltering = new JLabel("Check to filter reads that align to multiple locations:");
		filterReads = new JCheckBox();
		
		content = new JPanel();
		GroupLayout readFilteringLayout = new GroupLayout(content);
		content.setLayout(readFilteringLayout);
		readFilteringLayout.setAutoCreateGaps(true);
		readFilteringLayout.setAutoCreateContainerGaps(true);
		
		readFilteringLayout.setHorizontalGroup(readFilteringLayout.createSequentialGroup()
												.addComponent(readFiltering)
												.addComponent(filterReads)
		);
		readFilteringLayout.setVerticalGroup(readFilteringLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(readFiltering)
												.addComponent(filterReads)
	    );
		
		cp.add(content);
		
		content = new JPanel();
		runButton = new JButton("RUN");
		content.add(runButton,BorderLayout.SOUTH);
		cp.add(content);

		
		Frame.add(cp);
		Frame.pack();
		Frame.setVisible(true);
	}
	
    class OpenTreatment implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		JFileChooser c = new JFileChooser();
    		int rVal = c.showOpenDialog(Frame);
    		if (rVal == JFileChooser.APPROVE_OPTION){
    			TreatmentFileName.setText("FastQ File: " + c.getSelectedFile().toString());
    		}
    		if (rVal == JFileChooser.CANCEL_OPTION) {
    			TreatmentFileName.setText("You pressed cancel.");
    	
    		}
    	}
    	
    }
    
    class OpenControl implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		JFileChooser c = new JFileChooser();
    		int rVal = c.showOpenDialog(Frame);
    		if (rVal == JFileChooser.APPROVE_OPTION){
    			ControlFileName.setText("FastQ File: " + c.getSelectedFile().toString());
    		}
    		if (rVal == JFileChooser.CANCEL_OPTION) {
    			ControlFileName.setText("You pressed cancel.");
    	
    		}
    	}
    	
    }
	
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(Frame,"Welcome to Read Alignment Peak and Super Enhancer Calling!","RAPaSEC",JOptionPane.INFORMATION_MESSAGE);
		graphicalInterface mine = new graphicalInterface();
		
	}

}
