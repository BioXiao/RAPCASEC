import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.*;


public class graphicalInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	//File selection parameters
	public static JFrame Frame;
	public static JPanel content;
	public static Container cp;
	public static JButton runButton;
	public static JButton openTreatmentButton;
	public static JButton openControlButton;
	public static JButton openOutputDirButton;
	public static JTextField TreatmentFileName;
	public static JTextField ControlFileName;
	public static JTextField outputDirectory;
	public static JLabel runName;
	public static JTextField callName;
	
	
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
	public static JTextField alignmentParameters;
	public static JLabel alignmentParams;
	
	//Read Filtering Parameter
	public static JLabel readFiltering;
	public static JCheckBox filterReads;

	//Peak calling parameters
	public static JLabel peakCallingHeader;
	public static JCheckBox peakCallingSettingsDefault; 
	public static JCheckBox noModel;
	public static JLabel shiftSizeLabel;
	public static JTextField shiftSize;
	public static JLabel pValueLabel;
	public static JTextField pValue;
	public static JLabel spaceLabel;
	public static JTextField space;
	public static JLabel keepDupLabel;
	public static ButtonGroup keepDupSelection;
	public static JRadioButton keepDupAuto;
	public static JRadioButton keepDupNum; 
	public static JTextField keepDupNumDuplicates;
	public static JRadioButton keepDupAll;
	
	//ROSE parameters
	public static JLabel ROSEheader;
	public static JLabel StitchDistanceLabel;
	public static JTextField StitchDistance;
	public static JLabel TSSLabel;
	public static JTextField TSS;
	
	
	
	public graphicalInterface(){
		Frame = new JFrame("RAPaSEC");
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cp = getContentPane();
		cp.setLayout(new GridLayout(4,1));

		TreatmentFileName = new JTextField("Treatment .fastq:");
		TreatmentFileName.setEditable(false);
		ControlFileName = new JTextField("Control .fastq:");
		ControlFileName.setEditable(false);
		outputDirectory = new JTextField("Output Directory");
		outputDirectory.setEditable(false);
		runName = new JLabel("File Tag:");
		callName = new JTextField();
		
		content = new JPanel();
		openTreatmentButton = new JButton("Select a Treatment File");
		openControlButton = new JButton("Select a Control File");
		openOutputDirButton = new JButton("Select an output directory");
		
		openTreatmentButton.addActionListener(new OpenTreatment());
		openControlButton.addActionListener(new OpenControl());
		openOutputDirButton.addActionListener(new OpenOutput());
		
		content = new JPanel();
		content.setLayout(new GridLayout(4,2));
		content.add(TreatmentFileName);
		content.add(openTreatmentButton);
		content.add(ControlFileName);
		content.add(openControlButton);
		content.add(outputDirectory);
		content.add(openOutputDirButton);
		content.add(runName);
		content.add(callName);
		content.setBorder(BorderFactory.createLineBorder(Color.black));		
		cp.add(content);
		
		//Alignment Panel
		setUpAlignmentParameters();
		
		//PeakCalling Panel
		setUpPeakCalling();
		
		//ROSE Panel
		setUpRose();
		
		Frame.add(cp);
		Frame.pack();
		Frame.setVisible(true);
	}
	
	public static void setUpAlignmentParameters(){
		alignmentHeader = new JLabel("Bowtie Alignment Parameters");
		Font font = alignmentHeader.getFont();
		Font boldFont = new Font(font.getName(), Font.BOLD, font.getSize()+5);
		alignmentHeader.setFont(boldFont);
		alignmentSettingsDefault = new JCheckBox("Default Alignment Settings", true);
		alignmentSettingsPresets = new JCheckBox("Preset Alignment Parameters",true);
		presetOptions = new ButtonGroup();
		sensitive = new JRadioButton("--sensitive",false);
		verySensitive = new JRadioButton("--very-sensitive",true);
		fast = new JRadioButton("--fast");
		veryFast = new JRadioButton("--very-fast");
		local = new JCheckBox("--local",true);
		alignmentParameters = new JTextField();
		presetOptions.add(sensitive);
		presetOptions.add(verySensitive);
		presetOptions.add(fast);
		presetOptions.add(veryFast);
		alignmentParams = new JLabel("Enter specific alignment commands:");
		readFiltering = new JLabel("Check to filter reads that align to multiple locations:");
		filterReads = new JCheckBox();
	
		alignmentSettingsPresets.setEnabled(false);
		sensitive.setEnabled(false);
		verySensitive.setEnabled(false);
		fast.setEnabled(false);
		veryFast.setEnabled(false);
		local.setEnabled(false);
		alignmentParameters.setEnabled(false);
		filterReads.setSelected(true);
		
		
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
					alignmentParameters.setText("");
					alignmentParameters.setEnabled(false);
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
					alignmentParameters.setText("");
					
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
							.addComponent(alignmentSettingsPresets)
							.addComponent(local)
							.addGroup(alignmentParamLayout.createSequentialGroup()
									.addComponent(alignmentParams)
									.addComponent(alignmentParameters))
							.addGroup(alignmentParamLayout.createSequentialGroup()
									.addComponent(readFiltering)
									.addComponent(filterReads))
							)
				.addGroup(alignmentParamLayout.createParallelGroup()
							.addComponent(sensitive)
							.addComponent(verySensitive)
							.addComponent(fast)
							.addComponent(veryFast))
		);
		alignmentParamLayout.setVerticalGroup(alignmentParamLayout.createSequentialGroup()
				.addComponent(alignmentHeader)
				.addGroup(alignmentParamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(alignmentSettingsDefault)
							.addComponent(sensitive))
				.addGroup(alignmentParamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)  
							.addComponent(alignmentSettingsPresets)
							.addComponent(verySensitive))
				.addGroup(alignmentParamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(local)
							.addComponent(fast))
				.addComponent(veryFast)
				.addGroup(alignmentParamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(alignmentParams)
							.addComponent(alignmentParameters))
				.addGroup(alignmentParamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(readFiltering)
							.addComponent(filterReads))				
		);
												
		
		
		content.setBorder(BorderFactory.createLineBorder(Color.black));
		cp.add(content);
		
	}
	
	public static void setUpPeakCalling(){
		peakCallingHeader = new JLabel("MACS Peak Calling Parameters");
		Font font = peakCallingHeader.getFont();
		Font boldFont = new Font(font.getName(), Font.BOLD, font.getSize()+5);
		peakCallingHeader.setFont(boldFont);
		peakCallingSettingsDefault = new JCheckBox("Default Peak Calling Settings");
		noModel = new JCheckBox("No model (Enter your own shift size)");
		shiftSizeLabel = new JLabel("Shift size:");
		shiftSize = new JTextField();
		pValueLabel = new JLabel("P-value 10^-(x):");
		pValue = new JTextField();
		spaceLabel = new JLabel("Wiggle File Resolution:");
		space = new JTextField();
		keepDupLabel = new JLabel("Number of duplicate tags to keep:");
		keepDupSelection = new ButtonGroup();
		keepDupAuto = new JRadioButton("Auto (MACS will calculate probability)");
		keepDupNum = new JRadioButton("Enter max number of duplicate tags:");
		keepDupAll = new JRadioButton("Keep all duplicates");
		keepDupSelection.add(keepDupAuto);
		keepDupSelection.add(keepDupNum);
		keepDupSelection.add(keepDupAll);
		keepDupNumDuplicates = new JTextField();
		
		peakCallingSettingsDefault.setSelected(true);
		noModel.setEnabled(false);
		noModel.setSelected(true);
		shiftSize.setEnabled(false);
		shiftSize.setText("180");
		pValue.setEnabled(false);
		pValue.setText("-5");
		space.setEnabled(false);
		space.setText("50");
		keepDupAuto.setEnabled(false);
		keepDupNum.setEnabled(false);
		keepDupAll.setEnabled(false);
		keepDupAuto.setSelected(true); 
		keepDupNumDuplicates.setEnabled(false);
		
		
		content = new JPanel();
		GroupLayout peakParamLayout = new GroupLayout(content);
		content.setLayout(peakParamLayout);
		peakParamLayout.setAutoCreateGaps(true);
		peakParamLayout.setAutoCreateContainerGaps(true);
		peakParamLayout.setHorizontalGroup(peakParamLayout.createSequentialGroup()
				.addGroup(peakParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(peakCallingHeader)
						.addComponent(peakCallingSettingsDefault)
						.addGroup(peakParamLayout.createSequentialGroup()
								.addComponent(noModel)
								.addComponent(shiftSizeLabel)
								.addComponent(shiftSize))
						.addGroup(peakParamLayout.createSequentialGroup()
								.addComponent(pValueLabel)
								.addComponent(pValue)
								.addComponent(spaceLabel)
								.addComponent(space))
						.addGroup(peakParamLayout.createSequentialGroup()
								.addComponent(keepDupLabel)
								.addGroup(peakParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(keepDupAll)
										.addComponent(keepDupAuto)
										.addGroup(peakParamLayout.createSequentialGroup()
												.addComponent(keepDupNum)
												.addComponent(keepDupNumDuplicates))
								)
						)
				)
		);
		peakParamLayout.setVerticalGroup(peakParamLayout.createSequentialGroup()
				.addComponent(peakCallingHeader)
				.addComponent(peakCallingSettingsDefault)
				.addGroup(peakParamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(noModel)
						.addComponent(shiftSizeLabel)
						.addComponent(shiftSize))
				.addGroup(peakParamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(pValueLabel)
						.addComponent(pValue)
						.addComponent(spaceLabel)
						.addComponent(space))
				.addGroup(peakParamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(keepDupLabel)
						.addComponent(keepDupAll))
				.addComponent(keepDupAuto)
				.addGroup(peakParamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(keepDupNum)
						.addComponent(keepDupNumDuplicates))
				);
		
		ActionListener peakDefaultListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (!selected){
					noModel.setEnabled(true);
					shiftSize.setEnabled(true);
					pValue.setEnabled(true);
					space.setEnabled(true);
					keepDupAuto.setEnabled(true);
					keepDupNum.setEnabled(true);
					keepDupAll.setEnabled(true);
				}
				else{
					noModel.setEnabled(false);
					noModel.setSelected(true);
					shiftSize.setEnabled(false);
					shiftSize.setText("180");
					pValue.setEnabled(false);
					pValue.setText("-5");
					space.setEnabled(false);
					space.setText("50");
					keepDupAuto.setEnabled(false);
					keepDupAuto.setSelected(true);
					keepDupNum.setEnabled(false);
					keepDupAll.setEnabled(false);
					keepDupNumDuplicates.setEnabled(false);
					keepDupNumDuplicates.setText("");
				}
			}
		};
		peakCallingSettingsDefault.addActionListener(peakDefaultListener);
		
		ActionListener noModelListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected){
					shiftSize.setEnabled(true);
				}
				else{
					shiftSize.setEnabled(false);
					shiftSize.setText("");
				}
			}
		};
		noModel.addActionListener(noModelListener);
		
		
		ChangeListener keepDupNumListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changEvent) {
		        AbstractButton aButton = (AbstractButton)changEvent.getSource();
		        ButtonModel aModel = aButton.getModel();
		        boolean armed = aModel.isArmed();
		        boolean pressed = aModel.isPressed();
		        boolean selected = aModel.isSelected();
		        if(selected){
		        	keepDupNumDuplicates.setEnabled(true);
		        }
		        else{
		        	keepDupNumDuplicates.setEnabled(false);
		        	keepDupNumDuplicates.setText("");
		        }
		      }
		    };
		keepDupNum.addChangeListener(keepDupNumListener);
		
		
		
		
		
		content.setBorder(BorderFactory.createLineBorder(Color.black));
		cp.add(content);
		
	}
	
	public static void setUpRose(){
		ROSEheader = new JLabel("ROSE Parameters");
		Font font = ROSEheader.getFont();
		Font boldFont = new Font(font.getName(), Font.BOLD, font.getSize()+5);
		ROSEheader.setFont(boldFont);
		StitchDistanceLabel = new JLabel("Maximum Linking Distance for stitching:");
		TSSLabel = new JLabel("Distance from TSS to exclude:");
		StitchDistance = new JTextField("12500");
		TSS = new JTextField("2000");
		runButton = new JButton("RUN");
		
		content = new JPanel();
		GroupLayout RoseLayout = new GroupLayout(content);
		content.setLayout(RoseLayout);
		RoseLayout.setAutoCreateGaps(true);
		RoseLayout.setAutoCreateContainerGaps(true);
		
		RoseLayout.setHorizontalGroup(RoseLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(RoseLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(ROSEheader)
						.addGroup(RoseLayout.createSequentialGroup()
								.addComponent(StitchDistanceLabel)
								.addComponent(StitchDistance))
						.addGroup(RoseLayout.createSequentialGroup()
								.addComponent(TSSLabel)
						.addComponent(TSS))					
			)
			.addComponent(runButton)
				
		);				
		RoseLayout.setVerticalGroup(RoseLayout.createSequentialGroup()
				.addComponent(ROSEheader)
				.addGroup(RoseLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(StitchDistanceLabel)
						.addComponent(StitchDistance))
				.addGroup(RoseLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(TSSLabel)
						.addComponent(TSS))
			    .addComponent(runButton)
		);
						
		content.setBorder(BorderFactory.createLineBorder(Color.black));
		cp.add(content);
		
	}
	
	class OpenOutput implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		JFileChooser c = new JFileChooser();
    	    c.setCurrentDirectory(new java.io.File("."));
    	    c.setDialogTitle("Select an output directory");
    	    c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	    c.setAcceptAllFileFilterUsed(false);
    		int rVal = c.showOpenDialog(Frame);
    		if (rVal == JFileChooser.APPROVE_OPTION){
    			outputDirectory.setText("Output Folder: " + c.getCurrentDirectory().toString());
    		}
    		if (rVal == JFileChooser.CANCEL_OPTION) {
    			outputDirectory.setText("You pressed cancel.");
    	
    		}
    	}
    	
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
