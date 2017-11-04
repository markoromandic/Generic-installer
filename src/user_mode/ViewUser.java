package user_mode;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import design_gui.MyJButtonClose;
import design_gui.MyJButtonNext;
import design_gui.MyJCheckBoxTermsAccept;
import design_gui.MyJProgressBar;
import design_gui.MyJTextFieldBrowseLocation;
import design_gui.MyTermsAndConditions;
import design_gui.TextPrompt;
import design_gui.TextPrompt.Show;
import design_gui.myJPanel;
import functionality_gui.Help;
import functionality_gui.MouseListeners;
import net.jimmc.jshortcut.JShellLink;

public class ViewUser
{
	private ModelUser mUser;
	
	private JButton buttonExit;
	private JButton buttonMinimize;
	private JButton buttonNext;
	private ImageIcon imageMessageBoxResized;
	private JLayeredPane layeredPane;
	
	private ArrayList<String> listOfLanguages;
	
	private ArrayList<JPanel> nizPanela = new ArrayList<>();;
	
	private ImageIcon imageIconMainPanel;

	private ArrayList<String> listOfParameters;
	
	private boolean shortCutCheck = false;
	private boolean readMeFileCheck = false;
	private boolean autorun = false;
	
	private ArrayList<JLabel> listOfLabels = new ArrayList<>();
	private ArrayList<TextPrompt> listOfDestinationButtons = new ArrayList<>();
	private ArrayList<JCheckBox> listOfCBShortcuts = new ArrayList<>();
	private ArrayList<JCheckBox> listOfCBReadMe = new ArrayList<>();
	private ArrayList<JCheckBox> listOfCBAutoRun = new ArrayList<>();
	private ArrayList<JComboBox<String>> listOfComboBoxLanguageSwitcher = new ArrayList<>();
	private ArrayList<MyJButtonNext> listOfNextButtons;
	private ArrayList<MyJButtonClose> listOfCloseButtons;
	private ArrayList<MyTermsAndConditions> listOfTerms;
	
	public static String destPath = "";
	
	private ArrayList<String> idList = new ArrayList<>();
	
	private String nameOfSoftware = "";
	
	private ImageIcon icnBrowse;
	
	public ViewUser(ModelUser mUser)
	{
		
		this.mUser = mUser;
		
		//
		//Declaration and setup
		//
		JFrame frameMain = new JFrame();
		FlowLayout layoutFlow = new FlowLayout();
		
		icnBrowse = new ImageIcon("img/browse.png");
		
		listOfParameters = new ArrayList<>();
		
		ImageIcon imageIconStrongman = new ImageIcon("img/strongman.png");
		imageIconMainPanel = new ImageIcon("img/mainPanel.png");
		ImageIcon imageIconTopPanel = new ImageIcon("img/topPanel.png");
		ImageIcon imageIconExit = new ImageIcon("img/exitButton.png");
		ImageIcon imageIconMinimize = new ImageIcon("img/minimizeButton.png");
		ImageIcon imageIconNext = new ImageIcon("img/Next.png");
		ImageIcon imageIconNextHovered = new ImageIcon("img/NextHovered.png");
		ImageIcon imageIconNextDisabled = new ImageIcon("img/NextButtonDisabled.png");
		
		JLabel labelStrongman = new JLabel();
		JLabel labelMainPanel = new JLabel();
		JLabel labelTopPanel = new JLabel();
		JLabel labelIntroductionText = new JLabel();
		JLabel labelTitle = new JLabel();
		JLabel labelFinish = new JLabel();
		
		JPanel panelMain = new JPanel();
		JPanel panelTop = new JPanel();
		
		buttonExit = new JButton();
		buttonMinimize = new JButton();
		buttonNext = new JButton();
		
		layeredPane = frameMain.getLayeredPane();
		JCheckBox checkboxAccept = new JCheckBox("Prihvatam");
		
		listOfNextButtons = new ArrayList<>();
		listOfCloseButtons = new ArrayList<>();
		listOfTerms = new ArrayList<>();
		
		//
		//Frame attributes
		//
		frameMain.setSize(800, 500);
		frameMain.setLocationRelativeTo(null); 
		frameMain.setResizable(false);
		frameMain.setUndecorated(true);
		frameMain.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Changes icon of program
		frameMain.setIconImage(imageIconStrongman.getImage());
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}
		catch (InstantiationException e1)
		{
			e1.printStackTrace();
		}
		catch (IllegalAccessException e1)
		{
			e1.printStackTrace();
		}
		catch (UnsupportedLookAndFeelException e1)
		{
			e1.printStackTrace();
		}
		
		//
		//Attributes
		//
		labelFinish.setText("Hvala Vam sto ste instalirali nas proizvod!");
		layoutFlow.setVgap(0);
		labelIntroductionText.setText("<html><p style='text-align:center;'>Dobrodosli u genericki instaler<br>Ovaj instaler instalira PROGRAM na vas OS<br>Pritisnite Dalje da nastavite ili Prekini da prekinete instalaciju </p>");
		labelTitle.setText("<html><body><h1 style= \"color:white;text-align:center;  \"> Genericki instaler - 202.3 </h1></body>");
		labelIntroductionText.setHorizontalAlignment(JLabel.CENTER);
		
		panelTop.setBounds(0, 70, 680, 31);
		panelTop.setLayout(layoutFlow);
		panelTop.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		
		panelMain.setBounds(0, 100, 680, 370);
		panelMain.setLayout(layoutFlow);
		panelMain.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		
		//
		//Image setup
		//
		labelStrongman.setIcon(imageIconStrongman);
		labelStrongman.setPreferredSize(new Dimension(217, 500));
		labelStrongman.setBorder(BorderFactory.createEmptyBorder());
		labelStrongman.setBounds(800-220,0,220,500);
		
		labelMainPanel.setIcon(imageIconMainPanel);
		labelMainPanel.setPreferredSize(new Dimension(680, 370));
		labelMainPanel.setBorder(BorderFactory.createEmptyBorder());
		
		labelTopPanel.setIcon(imageIconTopPanel);
		labelTopPanel.setPreferredSize(new Dimension(680, 50));
		labelTopPanel.setBorder(BorderFactory.createEmptyBorder());
		
		buttonExit.setBounds(10, 78, 18, 18);
		buttonExit.setIcon(imageIconExit);
		buttonExit.setContentAreaFilled(false);
		buttonExit.setBorder(BorderFactory.createEmptyBorder());
		
		buttonMinimize.setBounds(38, 78, 18, 18);
		buttonMinimize.setIcon(imageIconMinimize);
		buttonMinimize.setContentAreaFilled(false);
		buttonMinimize.setBorder(BorderFactory.createEmptyBorder());
		
		Image img = imageIconNext.getImage();
		img = img.getScaledInstance(200, 46, Image.SCALE_SMOOTH);
		final ImageIcon imageIconNextResized = new ImageIcon(img);
		
		img = imageIconNextHovered.getImage();
		img = img.getScaledInstance(200, 46, Image.SCALE_SMOOTH);
		final ImageIcon imageIconNextHoveredResized = new ImageIcon(img);
		
		img = imageIconNextDisabled.getImage();
		img = img.getScaledInstance(200, 46, Image.SCALE_SMOOTH);
		final ImageIcon imageIconNextDisabledResized = new ImageIcon(img);
		
		buttonNext.setBounds(15, 400, 220, 50);
		buttonNext.setIcon(imageIconNextResized);
		buttonNext.setContentAreaFilled(false);
		buttonNext.setBorder(BorderFactory.createEmptyBorder());
		buttonNext.setFocusable(false);
		
		checkboxAccept.setContentAreaFilled(false);
		checkboxAccept.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		
		labelTitle.setBounds(180, 70, 680, 31);
		
		panelTop.add(labelTopPanel);
		panelMain.add(labelMainPanel);

		layeredPane.add(labelStrongman, new Integer(30));
		layeredPane.add(panelTop,new Integer(28));
		layeredPane.add(buttonExit,new Integer(29));
		layeredPane.add(buttonMinimize,new Integer(29));
		layeredPane.add(labelTitle,new Integer(29));
		
		//
		//Frame Appereance
		//
		frameMain.setVisible(true);
		
		//
		//Listeners
		//
		MouseListeners listener = new MouseListeners(frameMain);
		labelTopPanel.addMouseListener(listener);
		labelTopPanel.addMouseMotionListener(listener);
		labelStrongman.addMouseListener(listener);
		labelStrongman.addMouseMotionListener(listener);
		
		listOfParameters.add("<Panel 1;Label;0,0,100,20;sta god>");
		listOfParameters.add("<Panel 2;Label;0,0,150,20;Woohooooooo drugii>");
		listOfParameters.add("<Panel 1;Button Next;20,200,220,50>");
		
		buttonExit.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				System.exit(0);
			}
		});
		
		buttonMinimize.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				frameMain.setExtendedState(JFrame.ICONIFIED);
				frameMain.setExtendedState(frameMain.getExtendedState() | JFrame.ICONIFIED);
			}
		});
		
		checkboxAccept.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(checkboxAccept.isSelected())
				{
					Help.disabled=false;
					buttonNext.setIcon(imageIconNextResized);
				}
				else 
				{
					Help.disabled=true;
					buttonNext.setIcon(imageIconNextDisabledResized);
				}
			}
		});
		
		buttonNext.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				if(!Help.disabled)
				buttonNext.setIcon(imageIconNextHoveredResized);
			}
			@Override
			public void mouseExited(MouseEvent arg0) 
			{
				if(!Help.disabled)
				buttonNext.setIcon(imageIconNextResized);
			}
		});
	}
	
	public void addController(ActionListener controller) 
	{
		buttonExit.addActionListener(controller); 
		buttonNext.addActionListener(controller); 
		buttonMinimize.addActionListener(controller); 
	}
	
	private void promenaJezika(int indeks)
	{
		String sviParametri[] = new String[listOfParameters.size()];
		
		int pomLb = 0, pomBtnNext = 0, pomBtnClose = 0, pomCbLang = 0, pomTxtDest = 0, pomCbSC = 0, pomcbRun = 0, pomCbRead = 0, pomTerms = 0;
		
		
		for(int i = 0; i< listOfParameters.size(); i++)
		{
			sviParametri[i] = listOfParameters.get(i);
		}
		
		for(int i = 0; i<sviParametri.length; i++)
		{
			String jedanRed[] = sviParametri[i].substring(1, sviParametri[i].length() - 1).split(";");
			String prviArgument = jedanRed[0];
			
			if(prviArgument.length() >= 5)
			{	
				if(jedanRed[1].equals("Label")  && idList.get(i).equals("lb"))
				{
					listOfLabels.get(pomLb++).setText(jedanRed[3 + indeks]);;
				}
				else if(jedanRed[1].equals("Button Next") && idList.get(i).equals("btnNext"))
				{
					listOfNextButtons.get(pomBtnNext++).setText(jedanRed[3 + indeks]);
				}
				else if(jedanRed[1].equals("Button Close") && idList.get(i).equals("btnClose"))
				{
					listOfCloseButtons.get(pomBtnClose++).setText(jedanRed[3 + indeks]);
				}
				else if(jedanRed[1].equals("Language Switcher") && idList.get(i).equals("cbLang"))
				{
					listOfComboBoxLanguageSwitcher.get(pomCbLang++).setSelectedIndex(indeks);
				}
				else if(jedanRed[1].equals("Terms and Conditions") && idList.get(i).equals("tmc"))
				{
					listOfTerms.get(pomTerms++).setText(jedanRed[3+indeks]);
				}
				else if(jedanRed[1].equals("Software Folder Destination") && idList.get(i).equals("txtDest"))
				{
					listOfDestinationButtons.get(pomTxtDest++).setText(jedanRed[3 + indeks]);
				}
				else if(jedanRed[1].equals("Shourtcut option") && idList.get(i).equals("cbSC"))
				{
					listOfCBShortcuts.get(pomCbSC++).setText(jedanRed[3 + indeks]);
				}
				else if(jedanRed[1].equals("Run software after instalation") && idList.get(i).equals("cbRun"))
				{
					listOfCBAutoRun.get(pomcbRun++).setText(jedanRed[3 + indeks]);
				}
				else if(jedanRed[1].equals("Open Read-me File") && idList.get(i).equals("cbRead"))
				{
					listOfCBReadMe.get(pomCbRead++).setText(jedanRed[4 + indeks]);
				}
			}
		}
	}
	
	
	public static String placeOfInstalation = "";
	
	private static String destinacijaReadMe = "";
	
	public void generisi(int indeks)
	{
		nameOfSoftware = mUser.getNazivSoftvera();
		
		listOfParameters = mUser.getList();
		listOfLanguages = mUser.getListLanguages();
		
		
		String sviParametri[] = new String[listOfParameters.size()];
		
		for(int i = 0; i< listOfParameters.size(); i++)
		{
			sviParametri[i] = listOfParameters.get(i);
		}
		
//		cleaning of old
		MyTermsAndConditions.accepted=true;
		
		for(int i=0; i<nizPanela.size(); i++)
		{
			nizPanela.get(i).setVisible(false);
		}
		
		nizPanela = new ArrayList<>();
		int numberOfPanels = 0;
		
		for(int i = 0; i<sviParametri.length; i++)
		{
			String jedanRed[] = sviParametri[i].substring(1, sviParametri[i].length()-1).split(";");
			String prviArgument = jedanRed[0];
			
			if(prviArgument.length()==1)
			{
				if(prviArgument.substring(0, 1).equals("i"))
				{
					placeOfInstalation = jedanRed[1];
				}
				
				idList.add("i0");
			}
			else if(prviArgument.length()>=5)
			{
				
				if(prviArgument.substring(0, 5).equals("Panel"))
				{
					int redniBrojPanela = Integer.parseInt(prviArgument.substring(6,prviArgument.length()));
					
					
					if (redniBrojPanela>=numberOfPanels)
					{
						for(int j=numberOfPanels; j<=redniBrojPanela; j++)
						{
							myJPanel newPanel = new myJPanel(imageIconMainPanel);
							newPanel.setLayout(null);
							newPanel.setBackground(new Color(1.0f,1.0f,1.0f,0f));
							nizPanela.add(newPanel);
						}
						numberOfPanels = redniBrojPanela + 1;
					}
					
					nizPanela.get(redniBrojPanela-1).setBounds(0, 100, 680, 370);

					String koordinateString[] = jedanRed[2].split(",");
					int koordinate[] = new int[4];
					
					for(int j=0; j < koordinateString.length; j++)
					{
						
						koordinate[j] = Integer.parseInt(koordinateString[j]);
					}

					if(jedanRed[1].equals("Label"))
					{
						JLabel newLabel = new JLabel(jedanRed[3 + indeks]);
						newLabel.setBounds(koordinate[0],koordinate[1],koordinate[2],koordinate[3]);
						System.out.println("Na ovaj panel radis: " + redniBrojPanela);
						nizPanela.get(redniBrojPanela-1).add(newLabel);
						listOfLabels.add(newLabel);
						idList.add("lb");
					}
					else if(jedanRed[1].equals("Button Next"))
					{
						MyJButtonNext newButtonNext = new MyJButtonNext(nizPanela, redniBrojPanela-1, koordinate[2], koordinate[3]);
						
						newButtonNext.setText(jedanRed[3]);
						
						newButtonNext.setBounds(koordinate[0],koordinate[1],koordinate[2],koordinate[3]);
						nizPanela.get(redniBrojPanela-1).add(newButtonNext);
						idList.add("btnNext");
						listOfNextButtons.add(newButtonNext);
					}
					else if(jedanRed[1].equals("Button Close"))
					{
						MyJButtonClose newButtonClose = new MyJButtonClose(koordinate[2], koordinate[3]);
						
						newButtonClose.setText(jedanRed[3]);
						
						newButtonClose.addActionListener(new ActionListener()
						{
							
							@Override
							public void actionPerformed(ActionEvent e)
							{
								if(shortCutCheck)
								{
									createDesktopShortcut(installedPath, nameOfSoftware);
								}
								if(readMeFileCheck)
								{	
									File file = new File(destinacijaReadMe);
									
									try
									{
										openReadMeFile(file);
									}
									catch (Exception ex)
									{
										ex.printStackTrace();
									}
								}
								if(autorun)
								{
									File file = new File(installedPath);
									
									System.out.println(installedPath + "ds");
									
									try
									{
										openReadMeFile(file);
									}
									catch (Exception ex)
									{
										ex.printStackTrace();
									}
								}
								System.exit(0);
							}
						});
						
						newButtonClose.setBounds(koordinate[0],koordinate[1],koordinate[2],koordinate[3]);
						nizPanela.get(redniBrojPanela-1).add(newButtonClose);
						idList.add("btnClose");
						listOfCloseButtons.add(newButtonClose);
					}
					else if(jedanRed[1].equals("Language Switcher"))
					{
						String[] languages = new String[listOfLanguages.size()];
						for(int m = 0; m<listOfLanguages.size();m++){
							languages[m] = listOfLanguages.get(m);
						}
						
						JComboBox<String> jComboBoxLanguageSwitcher = new JComboBox<String>(languages);
						
						jComboBoxLanguageSwitcher.addActionListener(new ActionListener()
						{
							@Override
							public void actionPerformed(ActionEvent e)
							{
								int indeks = jComboBoxLanguageSwitcher.getSelectedIndex();
								
								promenaJezika(indeks);
							}
						});
						
						jComboBoxLanguageSwitcher.setBounds(koordinate[0],koordinate[1],koordinate[2],koordinate[3]);
						nizPanela.get(redniBrojPanela-1).add(jComboBoxLanguageSwitcher);
						listOfComboBoxLanguageSwitcher.add(jComboBoxLanguageSwitcher);
						idList.add("cbLang");
					}
					else if(jedanRed[1].equals("Terms and Conditions"))
					{
						MyTermsAndConditions newTerms = new MyTermsAndConditions(nizPanela.get(redniBrojPanela-1), koordinate, jedanRed[3 + indeks]);

						listOfTerms.add(newTerms);
						idList.add("tmc");
					}
					else if(jedanRed[1].equals("Installation"))
					{
						MyJProgressBar progressBar = new MyJProgressBar();
						progressBar.setBounds(koordinate[0], koordinate[1], koordinate[2], koordinate[3]);
						
						nizPanela.get(redniBrojPanela-1).add(progressBar);
						idList.add("inst");
					}
					else if(jedanRed[1].equals("Software Folder Destination"))
					{
						MyJTextFieldBrowseLocation textFieldDestinationFolder = new MyJTextFieldBrowseLocation(nizPanela.get(redniBrojPanela-1));
						
						TextPrompt textFieldDestinationFolderPromt = new TextPrompt(jedanRed[3], textFieldDestinationFolder);
						textFieldDestinationFolderPromt.setForeground( Color.GRAY );
						textFieldDestinationFolderPromt.changeAlpha(128);
						textFieldDestinationFolderPromt.changeStyle(Font.BOLD);
						textFieldDestinationFolderPromt.setShow(Show.FOCUS_LOST);
						textFieldDestinationFolderPromt.setIcon(imageMessageBoxResized);
						
						textFieldDestinationFolder.setBounds(koordinate[0],koordinate[1],koordinate[2],koordinate[3]);
						
						nizPanela.get(redniBrojPanela-1).add(textFieldDestinationFolder);
						
						Image img = icnBrowse.getImage();
						img = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
						final ImageIcon imageBrowseResized = new ImageIcon(img);
						
						JButton buttonDestinationFolder = new JButton();
						buttonDestinationFolder.setIcon(imageBrowseResized);
						buttonDestinationFolder.setContentAreaFilled(false);
						buttonDestinationFolder.setBorder(BorderFactory.createEmptyBorder());

						JFileChooser chooser = new JFileChooser();
						chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						buttonDestinationFolder.setBounds(koordinate[0] + koordinate[2] + 1,koordinate[1],80,koordinate[3]);
						
						nizPanela.get(redniBrojPanela - 1).add(buttonDestinationFolder);
						listOfDestinationButtons.add(textFieldDestinationFolderPromt);
						idList.add("txtDest");

						buttonDestinationFolder.addMouseListener(new MouseAdapter() 
						{
							
							@Override
							public void mouseClicked(MouseEvent e) 
							{
								super.mouseClicked(e);
								
								int returnVal = chooser.showOpenDialog(null);
								if(returnVal == JFileChooser.APPROVE_OPTION) 
								{
									
									destPath = chooser.getSelectedFile().getAbsolutePath();
									
									textFieldDestinationFolder.setText(destPath);
									
								}
								
							}
						});
					}
					else if(jedanRed[1].equals("Shourtcut option"))
					{
						JCheckBox checkBoxShourtCut = new JCheckBox(jedanRed[3]);
						
						checkBoxShourtCut.setBounds(koordinate[0],koordinate[1],koordinate[2],koordinate[3]);
						
						checkBoxShourtCut.addActionListener(new ActionListener()
						{
							
							@Override
							public void actionPerformed(ActionEvent e)
							{
								if(checkBoxShourtCut.isSelected())
									shortCutCheck = true;
								else shortCutCheck = false;
							}
						});
						
						nizPanela.get(redniBrojPanela - 1).add(checkBoxShourtCut);
						listOfCBShortcuts.add(checkBoxShourtCut);
						idList.add("cbSC");
					}
					else if(jedanRed[1].equals("Run software after instalation"))
					{
						JCheckBox checkBoxRunAfterInst = new JCheckBox(jedanRed[3]);
						
						checkBoxRunAfterInst.setBounds(koordinate[0],koordinate[1],koordinate[2],koordinate[3]);
						
						checkBoxRunAfterInst.addActionListener(new ActionListener()
						{
							@Override
							public void actionPerformed(ActionEvent e)
							{
								if(checkBoxRunAfterInst.isSelected())
									autorun = true;
							}
						});
						
						nizPanela.get(redniBrojPanela - 1).add(checkBoxRunAfterInst);
						listOfCBAutoRun.add(checkBoxRunAfterInst);
						idList.add("cbRun");
					}
					else if(jedanRed[1].equals("Open Read-me File"))
					{
						JCheckBox checkBoxReadMeFile = new JCheckBox(jedanRed[4]);
						
						checkBoxReadMeFile.setBounds(koordinate[0],koordinate[1],koordinate[2],koordinate[3]);
						
						destinacijaReadMe = jedanRed[3];
						
						checkBoxReadMeFile.addActionListener(new ActionListener()
						{
							@Override
							public void actionPerformed(ActionEvent e)
							{
								if(checkBoxReadMeFile.isSelected())
									readMeFileCheck = true;
								else readMeFileCheck = false;
							}
						});
						
						nizPanela.get(redniBrojPanela - 1).add(checkBoxReadMeFile);
						listOfCBReadMe.add(checkBoxReadMeFile);
						idList.add("cbRead");
					}
					else if(jedanRed[1].equals("Image"))
					{
						ImageIcon icon  = new ImageIcon(jedanRed[3]);
						
						Image img = icon.getImage();
						img = img.getScaledInstance(koordinate[2], koordinate[3], Image.SCALE_SMOOTH);
						ImageIcon image = new ImageIcon(img);
						
						JLabel label = new JLabel();
						label.setIcon(image);
						
						label.setBounds(koordinate[0],koordinate[1],koordinate[2],koordinate[3]);
						
						idList.add("img");
						nizPanela.get(redniBrojPanela - 1).add(label);
					}
					
				}
			}
		}
		
		//KOD KOJI DODA PANEL ALI TO LEPO URADI NEGDE DRUGDE
		for(int i=0; i<nizPanela.size(); i++)
		{
			nizPanela.get(i).setVisible(false);
			layeredPane.add(nizPanela.get(i), new Integer(4));
		}
		
		nizPanela.get(0).setVisible(true);
		
		for(int i=0; i<nizPanela.get(0).getComponentCount(); i++){
			if(nizPanela.get(0).getComponent(i) instanceof MyJCheckBoxTermsAccept){
				MyTermsAndConditions.accepted=false;
				for(int j=0; j<nizPanela.get(0).getComponentCount(); j++){
//					System.out.println("U foru sam");
					if(nizPanela.get(0).getComponent(j) instanceof MyJButtonNext){
						MyJButtonNext mb = (MyJButtonNext) nizPanela.get(0).getComponent(j);
						mb.setButtonDisabled();
						
					}
				}
				break;
			}
		}
		
		
		
		for(int i=0; i<nizPanela.get(0).getComponentCount(); i++){
			if(nizPanela.get(0).getComponent(i) instanceof MyJTextFieldBrowseLocation){
				MyJTextFieldBrowseLocation.enabled=false;
				for(int j=0; j<nizPanela.get(0).getComponentCount(); j++){
//					System.out.println("U foru sam");
					if(nizPanela.get(0).getComponent(j) instanceof MyJButtonNext){
						MyJButtonNext mb = (MyJButtonNext) nizPanela.get(0).getComponent(j);
						mb.setButtonDisabled();
						
					}
				}
				break;
			}
		}
	}
	
	private static String instalerLocation, installedPath = "";
	
	public static void install(JPanel panel){
		if(placeOfInstalation.equals("")){
			JOptionPane.showMessageDialog(null, "No file destination to install it");
			return;
		}
		if(destPath.equals("")){
			JOptionPane.showMessageDialog(null, "I don't know where to install it!");
			return;
		}
		File from = new File("data/" + placeOfInstalation);
		
		int poslednjaCrta = placeOfInstalation.lastIndexOf("/");
		
		String nazivFajla = placeOfInstalation.substring(poslednjaCrta+1, placeOfInstalation.length());
		System.out.println(nazivFajla);
		
		File to = new File(destPath + "/"+nazivFajla);
		installedPath = destPath+"\\"+nazivFajla;
		
		if(to.exists())
		{
			to.delete();
		}
		try {
			Files.copy(from.toPath(), to.toPath());
			for(int i=0; i<panel.getComponentCount(); i++){
				if(panel.getComponent(i) instanceof MyJProgressBar){
					MyJProgressBar.installationNotInProgress=true;
					MyJProgressBar myPB= (MyJProgressBar)panel.getComponent(i);
					myPB.setTo100();
					for(int j=0; j<panel.getComponentCount(); j++){
						if(panel.getComponent(j) instanceof MyJButtonNext){
							MyJButtonNext mb = (MyJButtonNext) panel.getComponent(j);
							mb.setButtonEnabled();
							
						}
					}
					break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void openReadMeFile(File document) throws IOException 
	{
		System.out.println("otvorio");
		
	    Desktop dt = Desktop.getDesktop();
	    dt.open(document);
	}
	
	private void createDesktopShortcut(String filePath, String fileName) 
	{
		try 
		{
			JShellLink link = new JShellLink();
			link.setFolder(JShellLink.getDirectory("desktop"));
			link.setName(fileName);
			link.setPath(filePath);
			link.save();
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
}
