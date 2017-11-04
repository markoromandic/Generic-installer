package developer_mode;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import design_gui.BoundsPopupMenuListener;
import design_gui.MyJButtonClose;
import design_gui.MyJButtonNext;
import design_gui.MyJCheckBoxTermsAccept;
import design_gui.MyJTextFieldBrowseLocation;
import design_gui.MyTermsAndConditions;
import design_gui.MyTimerButtonBrowse;
import design_gui.MyTimerTextField;
import design_gui.TextPrompt;
import design_gui.TextPrompt.Show;
import design_gui.myJPanel;
import functionality_gui.MouseListeners;
import net.jimmc.jshortcut.JShellLink;

public class ViewDeveloper
{
	private ModelDeveloper mDeveloper;

	private JButton buttonExit;
	private JButton buttonMinimize;
	private JButton buttonNext;
	private JButton buttonPreview;
	private JButton buttonAbout;
	private JButton buttonBrowse;
	private ImageIcon imageMessageBoxResized;
	private JLayeredPane layeredPane;

	private String listOfLanguages;

	private ArrayList<JPanel> nizPanela = new ArrayList<>();;

	private JTextField textFieldCoordiantes;

	private int currentNumberOfPanels;

	private JComboBox comboBoxMainArgument;
	private JComboBox comboBoxPanels;
	private JComboBox comboBoxObjects;
	private ImageIcon icnWhitePanel;
	private ImageIcon imageIconMainPanel;
	private JTextField textFieldTextArguments;

	private JLabel labelChoose, labelPanels, labelObjects, labelCoordinates;

	private JList<String> listOfParameters;
	private DefaultListModel<String> model;

	private JCheckBox checkBoxMenuAbout;
	private JCheckBox checkBoxMenuQuit;
	private JCheckBox checkBoxMenuLanguageSwitcher;

	private boolean shortCutCheck = false;
	private boolean readMeFileCheck = false;
	private boolean autorun = false;

	private ArrayList<JLabel> listOfLabels;
	private ArrayList<JButton> listOfDestinationButtons;
	private ArrayList<JCheckBox> listOfCBShortcuts;
	private ArrayList<JCheckBox> listOfCBReadMe;
	private ArrayList<JCheckBox> listOfCBAutoRun;
	private ArrayList<JComboBox<String>> listOfComboBoxLanguageSwitcher;
	private ArrayList<MyTermsAndConditions> listOfTermsAndConditions;
	private ArrayList<MyJButtonNext> listOfNextButtons;
	private ArrayList<MyJButtonClose> listOfCloseButtons;

	private ImageIcon icnPreview, icnAdd, icnPreviewHovered, icnAddHovered;

	private ArrayList<String> idList;

	private String destPath;

	private String loctionOfFileInOurStrucutre = "";

	private String nameOfSoftware = "";
	private String nameOfReadMeFile = "";

	private String destinacijaReadMe;
	private String destinacijaInstalacije;

	private ImageIcon icnBrowse;

	public ViewDeveloper(ModelDeveloper mDeveloper)
	{
		this.mDeveloper = mDeveloper;
		initialize();
		languageMessageBox();
	}

	private void initialize()
	{
		JFrame frameMain = new JFrame();
		JPanel panelBackground = new JPanel();
		JLabel labelBackground = new JLabel();
		JLabel labelLeftHand = new JLabel();
		JLabel labelRightHand = new JLabel();
		JLabel labelWhitePanel = new JLabel();

		JLabel labelPreviewPanel = new JLabel();

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

		layeredPane = frameMain.getLayeredPane();
		ImageIcon icnIconBackground = new ImageIcon("img/BackgroundEmpty.png");
		ImageIcon icnIconLeftHand = new ImageIcon("img/LeftHand.png");
		ImageIcon icnIconRightHand = new ImageIcon("img/RightHand.png");
		icnWhitePanel = new ImageIcon("img/whitePanel.png");
		ImageIcon icnExit = new ImageIcon("img/ExitDevBtn.png");
		ImageIcon icnMinimize = new ImageIcon("img/MinimizeDevBtn.png");
		ImageIcon icnMessageBox = new ImageIcon("img/messageBox.png");
		ImageIcon icnAbout = new ImageIcon("img/AboutDevBtn.png");
		icnBrowse = new ImageIcon("img/browse.png");
		icnPreview = new ImageIcon("img/Preview.png");
		icnAdd = new ImageIcon("img/Add.png");
		icnPreviewHovered = new ImageIcon("img/PreviewHovered.png");
		icnAddHovered = new ImageIcon("img/AddHovered.png");

		Image img = icnMessageBox.getImage();
		img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imageMessageBoxResized = new ImageIcon(img);

		img = icnBrowse.getImage();
		img = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		final ImageIcon imageBrowseResized = new ImageIcon(img);

		frameMain.setSize(1600, 706);
		frameMain.setLocationRelativeTo(null);
		frameMain.setResizable(false);
		frameMain.setIconImage(icnIconBackground.getImage());
		frameMain.setUndecorated(true);
		frameMain.setBackground(new Color(1.0f, 1.0f, 1.0f, 0f));
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MouseListeners mouseListeners = new MouseListeners(frameMain);

		buttonExit = new JButton();
		buttonExit.setBounds(220, 210, 18, 18);
		buttonExit.setIcon(icnExit);
		buttonExit.setContentAreaFilled(false);
		buttonExit.setBorder(BorderFactory.createEmptyBorder());
		buttonMinimize = new JButton();
		buttonMinimize.setBounds(246, 210, 18, 18);
		buttonMinimize.setIcon(icnMinimize);
		buttonMinimize.setContentAreaFilled(false);
		buttonMinimize.setBorder(BorderFactory.createEmptyBorder());

		Image imgP = icnPreview.getImage();
		imgP = imgP.getScaledInstance(130, 40, Image.SCALE_SMOOTH);
		icnPreview = new ImageIcon(imgP);

		imgP = icnPreviewHovered.getImage();
		imgP = imgP.getScaledInstance(130, 40, Image.SCALE_SMOOTH);
		icnPreviewHovered = new ImageIcon(imgP);

		imgP = icnAdd.getImage();
		imgP = imgP.getScaledInstance(130, 40, Image.SCALE_SMOOTH);
		icnAdd = new ImageIcon(imgP);

		imgP = icnAddHovered.getImage();
		imgP = imgP.getScaledInstance(130, 40, Image.SCALE_SMOOTH);
		icnAddHovered = new ImageIcon(imgP);

		buttonPreview = new JButton();
		buttonPreview.setBounds(335, 455, 130, 40);
		buttonPreview.setContentAreaFilled(false);
		buttonPreview.setBorder(BorderFactory.createEmptyBorder());
		buttonPreview.setFocusable(false);
		buttonPreview.setIcon(icnPreview);

		JLabel previewButtonLabel = new JLabel("Preview", SwingConstants.CENTER);
		previewButtonLabel.setMinimumSize(new Dimension(100, 20));
		previewButtonLabel.setMaximumSize(new Dimension(100, 20));
		previewButtonLabel.setPreferredSize(new Dimension(100, 20));
		previewButtonLabel.setText("<html><p style='color:white;'><b>Preview</b>");
		buttonPreview.add(previewButtonLabel);

		buttonPreview.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				super.mouseEntered(e);
				buttonPreview.setIcon(icnPreviewHovered);
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				super.mouseExited(e);
				buttonPreview.setIcon(icnPreview);
			}
		});

		buttonNext = new JButton();
		buttonNext.setBounds(335, 415, 130, 40);
		buttonNext.setContentAreaFilled(false);
		buttonNext.setBorder(BorderFactory.createEmptyBorder());
		buttonNext.setFocusable(false);
		buttonNext.setIcon(icnAdd);

		JLabel nextButtonLabel = new JLabel("Add", SwingConstants.CENTER);
		nextButtonLabel.setMinimumSize(new Dimension(100, 20));
		nextButtonLabel.setMaximumSize(new Dimension(100, 20));
		nextButtonLabel.setPreferredSize(new Dimension(100, 20));
		nextButtonLabel.setText("<html><p style='color:white;'><b>Add</b>");
		buttonNext.add(nextButtonLabel);

		buttonNext.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				super.mouseEntered(e);
				buttonNext.setIcon(icnAddHovered);
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				super.mouseExited(e);
				buttonNext.setIcon(icnAdd);
			}
		});

		buttonAbout = new JButton();
		buttonAbout.setBounds(515, 210, 65, 18);
		buttonAbout.setIcon(icnAbout);
		buttonAbout.setContentAreaFilled(false);
		buttonAbout.setBorder(BorderFactory.createEmptyBorder());
		buttonBrowse = new JButton();
		buttonBrowse.setBounds(510, 380, 25, 25);
		buttonBrowse.setIcon(imageBrowseResized);
		buttonBrowse.setContentAreaFilled(false);
		buttonBrowse.setBorder(BorderFactory.createEmptyBorder());
		buttonBrowse.setVisible(false);

		String[] setupStrings =
		{ "Panel", "Installation" };
		comboBoxMainArgument = new JComboBox<String>(setupStrings);
		comboBoxMainArgument.setSelectedIndex(0);
		comboBoxMainArgument.setBounds(310, 265, 110, 30);
		comboBoxMainArgument.setVisible(true);

		currentNumberOfPanels = 1;
		String[] panelStrings =
		{ "Panel 1", "Add new" };
		comboBoxPanels = new JComboBox<String>(panelStrings);

		comboBoxPanels.setSelectedIndex(0);
		comboBoxPanels.setBounds(225, 335, 75, 25);
		comboBoxPanels.setVisible(true);

		String[] objectStrings =
		{ "Label", "Button Next", "Button Close", "Language Switcher", "Terms and Conditions", "Installation", "Software Folder Destination", "Shourtcut option", "Run software after instalation", "Open Read-me File", "Image" };
		comboBoxObjects = new JComboBox<String>(objectStrings);
		comboBoxObjects.setSelectedIndex(0);
		comboBoxObjects.setBounds(310, 335, 157, 25);
		comboBoxObjects.setVisible(true);

		BoundsPopupMenuListener objectWiderListener = new BoundsPopupMenuListener(true, false);
		comboBoxObjects.addPopupMenuListener(objectWiderListener);

		labelChoose = new JLabel();
		labelChoose.setText("Choose: ");
		labelChoose.setBounds(243, 265, 200, 30);
		labelChoose.setVisible(true);

		labelPanels = new JLabel();
		labelPanels.setText("Panels");
		labelPanels.setBounds(225, 308, 80, 30);
		labelPanels.setVisible(true);

		labelObjects = new JLabel();
		labelObjects.setText("Objects");
		labelObjects.setBounds(310, 308, 200, 30);
		labelObjects.setVisible(true);

		labelCoordinates = new JLabel();
		labelCoordinates.setText("Coordinates");
		labelCoordinates.setBounds(478, 308, 200, 30);
		labelCoordinates.setVisible(true);

		textFieldCoordiantes = new JTextField()
		{
			public Point getToolTipLocation(MouseEvent event)
			{
				return new Point(16, getHeight() / 2);
			}
		};

		textFieldCoordiantes.setBounds(478, 335, 100, 26);
		textFieldCoordiantes.setVisible(true);

		textFieldTextArguments = new JTextField()
		{
			public Point getToolTipLocation(MouseEvent event)
			{
				return new Point(16, getHeight() / 2);
			}
		};

		textFieldTextArguments.setBounds(250, 380, 250, 25);
		textFieldTextArguments.setVisible(true);

		checkBoxMenuAbout = new JCheckBox("About");
		checkBoxMenuAbout.setBounds(225, 370, 100, 25);
		checkBoxMenuAbout.setVisible(false);
		checkBoxMenuAbout.setBackground(Color.WHITE);

		checkBoxMenuQuit = new JCheckBox("Quit");
		checkBoxMenuQuit.setBounds(330, 370, 100, 25);
		checkBoxMenuQuit.setVisible(false);
		checkBoxMenuQuit.setBackground(Color.WHITE);

		checkBoxMenuLanguageSwitcher = new JCheckBox("Language switcher");
		checkBoxMenuLanguageSwitcher.setBounds(440, 370, 138, 25);
		checkBoxMenuLanguageSwitcher.setVisible(false);
		checkBoxMenuLanguageSwitcher.setBackground(Color.WHITE);

		model = new DefaultListModel<>();
		listOfParameters = new JList<>(model);

		JScrollPane panelScroll = new JScrollPane(listOfParameters);
		panelScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelScroll.setBounds(250, 505, 303, 150);
		panelScroll.setVisible(true);
		panelBackground.setSize(800, 706);
		panelBackground.setBackground(new Color(1.0f, 1.0f, 1.0f, 0f));

		labelBackground.setSize(800, 706);
		labelBackground.setIcon(icnIconBackground);
		labelBackground.addMouseListener(mouseListeners);
		labelBackground.addMouseMotionListener(mouseListeners);

		imageIconMainPanel = new ImageIcon("img/mainPanel.png");
		labelPreviewPanel.setBounds(800, 240, 680, 370);
		labelPreviewPanel.setIcon(imageIconMainPanel);
		labelPreviewPanel.addMouseListener(new MouseAdapter()
		{
		});
		labelPreviewPanel.addMouseMotionListener(new MouseAdapter()
		{
		});

		labelWhitePanel.setBounds(206, 240, 389, 432);
		labelWhitePanel.setIcon(icnWhitePanel);
		labelWhitePanel.addMouseListener(new MouseAdapter()
		{
		});
		labelWhitePanel.addMouseMotionListener(new MouseAdapter()
		{
		});

		labelLeftHand.setBounds(0, 316, 245, 234);
		labelLeftHand.setIcon(icnIconLeftHand);

		labelRightHand.setBounds(558, 316, 245, 234);
		labelRightHand.setIcon(icnIconRightHand);

		layeredPane.add(labelBackground, new Integer(1));
		layeredPane.add(buttonExit, new Integer(2));
		layeredPane.add(buttonMinimize, new Integer(2));
		layeredPane.add(labelWhitePanel, new Integer(2));
		layeredPane.add(buttonAbout, new Integer(2));
		layeredPane.add(comboBoxMainArgument, new Integer(3));
		layeredPane.add(labelChoose, new Integer(3));
		layeredPane.add(labelPanels, new Integer(3));
		layeredPane.add(comboBoxPanels, new Integer(3));
		layeredPane.add(comboBoxObjects, new Integer(3));
		layeredPane.add(labelObjects, new Integer(3));
		layeredPane.add(textFieldCoordiantes, new Integer(3));
		layeredPane.add(labelCoordinates, new Integer(3));
		layeredPane.add(textFieldTextArguments, new Integer(3));
		layeredPane.add(buttonBrowse, new Integer(3));
		layeredPane.add(checkBoxMenuAbout, new Integer(3));
		layeredPane.add(checkBoxMenuQuit, new Integer(3));
		layeredPane.add(checkBoxMenuLanguageSwitcher, new Integer(3));
		layeredPane.add(panelScroll, new Integer(3));
		layeredPane.add(buttonNext, new Integer(3));
		layeredPane.add(buttonPreview, new Integer(3));
		layeredPane.add(labelLeftHand, new Integer(4));
		layeredPane.add(labelRightHand, new Integer(4));

		frameMain.add(panelBackground);
		frameMain.setVisible(true);

		TextPrompt textFieldCoordinatesPromt = new TextPrompt("X,Y,W,H", textFieldCoordiantes);
		textFieldCoordinatesPromt.setForeground(Color.GRAY);
		textFieldCoordinatesPromt.changeAlpha(128);
		textFieldCoordinatesPromt.changeStyle(Font.BOLD + Font.ITALIC);
		textFieldCoordinatesPromt.setShow(Show.FOCUS_LOST);
		textFieldCoordinatesPromt.setIcon(imageMessageBoxResized);
		toolTip(textFieldCoordiantes, "X, Y, WIDTH, HEIGHT");

		TextPrompt textFieldArgumentsPromt = new TextPrompt("text in language1;text in language2;text in language3", textFieldTextArguments);
		textFieldArgumentsPromt.setForeground(Color.GRAY);
		textFieldArgumentsPromt.changeAlpha(128);
		textFieldArgumentsPromt.changeStyle(Font.BOLD);
		textFieldArgumentsPromt.setShow(Show.FOCUS_LOST);
		textFieldArgumentsPromt.setIcon(imageMessageBoxResized);
		toolTip(textFieldTextArguments, "Use \";\" between languages");

		MyTimerTextField animationTextField = new MyTimerTextField(textFieldTextArguments);
		MyTimerButtonBrowse animationButtonBrowse = new MyTimerButtonBrowse(buttonBrowse);

		comboBoxObjects.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (!comboBoxObjects.getSelectedItem().toString().equals("Language Switcher") && !comboBoxObjects.getSelectedItem().toString().equals("Installation") && !comboBoxObjects.getSelectedItem().toString().equals("Image") && !comboBoxObjects.getSelectedItem().toString().equals("Open Read-me File"))
				{
					textFieldTextArguments.setVisible(true);
					textFieldCoordinatesPromt.setText("X,Y,W,H");
					checkBoxMenuAbout.setVisible(false);
					checkBoxMenuLanguageSwitcher.setVisible(false);
					checkBoxMenuQuit.setVisible(false);
					buttonBrowse.setVisible(false);
				}
				else if (comboBoxObjects.getSelectedItem().toString().equals("Image"))
				{
					buttonBrowse.setVisible(true);
				}
				else if (comboBoxObjects.getSelectedItem().toString().equals("Open Read-me File"))
				{
					buttonBrowse.setVisible(true);
				}
				else
				{
					textFieldTextArguments.setVisible(false);
					checkBoxMenuAbout.setVisible(false);
					checkBoxMenuLanguageSwitcher.setVisible(false);
					checkBoxMenuQuit.setVisible(false);
					buttonBrowse.setVisible(false);

					if (comboBoxObjects.getSelectedItem().toString().equals("Button Next"))
					{
						textFieldCoordinatesPromt.setText("X,Y,130,40");
					}
					else if (comboBoxObjects.getSelectedItem().toString().equals("Button Close"))
					{
						textFieldCoordinatesPromt.setText("Y,X,130,40");
					}

				}
			}
		});

		comboBoxPanels.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (comboBoxPanels.getSelectedItem().toString().equals("Add new"))
				{
					comboBoxPanels.removeItem("Add new");
					currentNumberOfPanels++;
					comboBoxPanels.addItem(("Panel " + currentNumberOfPanels));
					comboBoxPanels.addItem("Add new");
					comboBoxPanels.setSelectedIndex(currentNumberOfPanels - 1);
				}
			}
		});

		comboBoxMainArgument.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (comboBoxMainArgument.getSelectedItem().toString().equals("Installation"))
				{
					textFieldTextArguments.setVisible(true);
					comboBoxPanels.setVisible(false);
					comboBoxObjects.setVisible(false);
					textFieldCoordiantes.setVisible(false);
					animationTextField.start(true);
					animationButtonBrowse.start(true);
					labelPanels.setVisible(false);
					labelCoordinates.setVisible(false);
					labelObjects.setVisible(false);
					checkBoxMenuAbout.setVisible(false);
					checkBoxMenuLanguageSwitcher.setVisible(false);
					checkBoxMenuQuit.setVisible(false);
					buttonBrowse.setVisible(true);
				}
				else if (comboBoxMainArgument.getSelectedItem().toString().equals("Panel") && comboBoxObjects.getSelectedItem().toString().equals("Image") && comboBoxObjects.getSelectedItem().toString().equals("Open Read-me File"))
				{
					comboBoxPanels.setVisible(true);
					comboBoxObjects.setVisible(true);
					textFieldCoordiantes.setVisible(true);
					labelPanels.setVisible(true);
					labelCoordinates.setVisible(true);
					labelObjects.setVisible(true);

					textFieldTextArguments.setVisible(true);
					checkBoxMenuAbout.setVisible(false);
					checkBoxMenuLanguageSwitcher.setVisible(false);
					checkBoxMenuQuit.setVisible(false);
					buttonBrowse.setVisible(true);
					animationTextField.start(false, comboBoxPanels, comboBoxObjects, textFieldCoordiantes, labelPanels, labelCoordinates, labelObjects);
					animationButtonBrowse.start(false, comboBoxPanels, comboBoxObjects, textFieldCoordiantes, labelPanels, labelCoordinates, labelObjects);
				}
				else if (comboBoxMainArgument.getSelectedItem().toString().equals("Panel"))
				{
					if (true)
					{
						textFieldTextArguments.setVisible(true);
						comboBoxPanels.setVisible(true);
						comboBoxObjects.setVisible(true);
						textFieldCoordiantes.setVisible(true);
						labelPanels.setVisible(true);
						labelCoordinates.setVisible(true);
						labelObjects.setVisible(true);
						checkBoxMenuAbout.setVisible(false);
						checkBoxMenuQuit.setVisible(false);
					}
					animationTextField.start(false, comboBoxPanels, comboBoxObjects, textFieldCoordiantes, labelPanels, labelCoordinates, labelObjects);
					animationButtonBrowse.start(false, comboBoxPanels, comboBoxObjects, textFieldCoordiantes, labelPanels, labelCoordinates, labelObjects);
				}

				else if (comboBoxMainArgument.getSelectedItem().toString().equals("Menu-bar"))
				{
					comboBoxPanels.setVisible(false);
					comboBoxObjects.setVisible(false);
					textFieldCoordiantes.setVisible(true);
					labelPanels.setVisible(false);
					labelCoordinates.setVisible(true);
					labelObjects.setVisible(false);
					buttonBrowse.setVisible(false);
					textFieldTextArguments.setVisible(false);
					checkBoxMenuAbout.setVisible(true);
					checkBoxMenuLanguageSwitcher.setVisible(false);
					checkBoxMenuQuit.setVisible(true);
				}
			}
		});

		buttonExit.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				System.exit(0);
			}
		});

		buttonPreview.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				generisi(0);
			}
		});

		buttonAbout.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				new ViewAbout();
			}
		});

		buttonNext.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				addToList();
				restartTextFields();
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

		buttonBrowse.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				JFileChooser chooser = new JFileChooser();

				if (comboBoxObjects.getSelectedItem().toString().equals("Image"))
				{
					FileFilter imageFilter = new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes());
					chooser.setFileFilter(imageFilter);
					int returnVal = chooser.showOpenDialog(null);

					if (returnVal == JFileChooser.APPROVE_OPTION)
					{

						String destPath = chooser.getSelectedFile().getAbsolutePath();

						File from = new File(destPath);

						String fileName = chooser.getSelectedFile().getName();

						File to = new File("data/" + fileName);

						textFieldTextArguments.setText(to.toString());

						if (to.exists())
							to.delete();

						try
						{
							Files.copy(from.toPath(), to.toPath());
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
				}
				else if (comboBoxObjects.getSelectedItem().toString().equals("Open Read-me File"))
				{
					int returnVal = chooser.showOpenDialog(null);

					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						String destPath = chooser.getSelectedFile().getAbsolutePath();

						File from = new File(destPath);

						String fileName = chooser.getSelectedFile().getName();

						File to = new File("data/" + fileName);

						nameOfReadMeFile = to.toString();

						loctionOfFileInOurStrucutre = to.getAbsolutePath();

						if (to.exists())
							to.delete();

						try
						{
							Files.copy(from.toPath(), to.toPath());
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}

				}
				else if (comboBoxMainArgument.getSelectedItem().toString().equals("Installation"))
				{
					FileFilter softwareFilter = new FileNameExtensionFilter("Software", "jar", "exe", "txt");
					chooser.setFileFilter(softwareFilter);
					int returnVal = chooser.showOpenDialog(null);

					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						String destPath = chooser.getSelectedFile().getAbsolutePath();

						File from = new File(destPath);

						String fileName = chooser.getSelectedFile().getName();

						File to = new File("data/" + fileName);

						loctionOfFileInOurStrucutre = to.getAbsolutePath();

						textFieldTextArguments.setText(loctionOfFileInOurStrucutre);

						destinacijaInstalacije = loctionOfFileInOurStrucutre;

						nameOfSoftware = fileName;

						if (to.exists())
							to.delete();

						try
						{
							Files.copy(from.toPath(), to.toPath());
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}

				}
			}
		});

		listOfParameters.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				int modifiers = e.getModifiersEx();

				String proveri = KeyEvent.getModifiersExText(modifiers);

				int keyPressedNum = e.getKeyCode();
				String keyChar = e.getKeyText(keyPressedNum);

				if (keyChar.equals("Delete") || (proveri.equals("Ctrl") && keyChar.equals("D")))
					deleteColumn();

				if (proveri.equals("Ctrl") && keyChar.equals("U") && !model.isEmpty() && !listOfParameters.isSelectionEmpty())
					updateColumn();

				if (proveri.equals("Alt") && keyChar.equals("D"))
					clearListOfParameters();
			}
		});

		MouseListener mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent mouseEvent)
			{
				JList theList = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2)
				{
					int index = theList.locationToIndex(mouseEvent.getPoint());
					if (index >= 0)
					{
						Object o = theList.getModel().getElementAt(index);

						String[] nizParametara = o.toString().substring(1, o.toString().length() - 1).split(";");

						if (nizParametara[0].equals("i"))
						{
							comboBoxMainArgument.setSelectedItem("Installation");
							textFieldTextArguments.setText(nizParametara[1]);
						}
						else if (nizParametara[0].contains("Panel"))
						{
							comboBoxPanels.setSelectedItem(nizParametara[0]);
							comboBoxObjects.setSelectedItem(nizParametara[1]);
							textFieldCoordiantes.setText(nizParametara[2]);
							if (nizParametara[1].equals("Label") || nizParametara[1].equals("Terms and Conditions"))
								textFieldTextArguments.setText(nizParametara[3]);
						}
					}
				}
			}
		};

		listOfParameters.addMouseListener(mouseListener);

		KeyListener keyListenerForList = new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				int modifiers = e.getModifiersEx();

				String proveri = KeyEvent.getModifiersExText(modifiers);

				int keyPressedNum = e.getKeyCode();
				String keyChar = e.getKeyText(keyPressedNum);

				if ((proveri.equals("Ctrl") && keyChar.equals("D")))
					deleteColumn();

				else if (proveri.equals("Ctrl") && keyChar.equals("U") && !model.isEmpty() && !listOfParameters.isSelectionEmpty())
					updateColumn();

				else if (proveri.equals("Alt") && keyChar.equals("A") && !model.isEmpty())
					clearListOfParameters();

				else if (keyChar.equals("Enter"))
				{
					addToList();
					restartTextFields();
				}

			}
		};

		textFieldTextArguments.addKeyListener(keyListenerForList);
		textFieldCoordiantes.addKeyListener(keyListenerForList);
		comboBoxMainArgument.addKeyListener(keyListenerForList);
		comboBoxPanels.addKeyListener(keyListenerForList);
		comboBoxObjects.addKeyListener(keyListenerForList);

		frameMain.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				int modifiers = e.getModifiersEx();

				String proveri = KeyEvent.getModifiersExText(modifiers);

				int keyPressedNum = e.getKeyCode();
				String keyChar = e.getKeyText(keyPressedNum);

				if (proveri.equals("Alt") && keyChar.equals("A"))
					clearListOfParameters();
			}
		});
	}

	private void toolTip(JTextField txtToolTip, String tekst)
	{
		txtToolTip.setToolTipText(tekst);
		UIManager.put("ToolTip.background", new Color(255, 209, 147));
	}

	private void restartTextFields()
	{
		textFieldCoordiantes.setText("");
		textFieldTextArguments.setText("");
	}

	public void addControllerSave(ActionListener controller)
	{
		buttonPreview.addActionListener(controller);
	}

	public String[] getListOfLanguages()
	{
		return listOfLanguages.split(";");
	}

	public DefaultListModel<String> getModel()
	{
		return model;
	}

	public String selectedItemInJList()
	{
		return listOfParameters.getSelectedValue().toString();
	}

	public void updateColumn()
	{
		String updatedParameters = "";
		int index = listOfParameters.getSelectedIndex();

		if (comboBoxMainArgument.getSelectedItem().equals("Instalation"))
		{
			updatedParameters = "<i" + ";" + textFieldTextArguments.getText() + ">";
			model.setElementAt(updatedParameters, index);
		}
		else if (comboBoxMainArgument.getSelectedItem().equals("Panel"))
		{
			if (!comboBoxObjects.getSelectedItem().toString().equals("Language Switcher") && !comboBoxObjects.getSelectedItem().toString().equals("Installation"))
			{
				updatedParameters = "<" + comboBoxPanels.getSelectedItem().toString() + ";" + comboBoxObjects.getSelectedItem().toString() + ";" + textFieldCoordiantes.getText() + ";" + textFieldTextArguments.getText() + ">";
			}
			else if (comboBoxObjects.getSelectedItem().toString().equals("Open Read-me File"))
			{
				updatedParameters = "<" + comboBoxPanels.getSelectedItem().toString() + ";" + comboBoxObjects.getSelectedItem().toString() + ";" + textFieldCoordiantes.getText() + ";" + nameOfReadMeFile + ";" + textFieldTextArguments.getText() + ">";
			}
			else
			{
				updatedParameters = "<" + comboBoxPanels.getSelectedItem().toString() + ";" + comboBoxObjects.getSelectedItem().toString() + ";" + textFieldCoordiantes.getText() + ">";
			}
			model.setElementAt(updatedParameters, index);
		}
	}

	public void deleteColumn()
	{
		int index = listOfParameters.getSelectedIndex();

		model.removeElementAt(index);
	}

	public void clearListOfParameters()
	{
		model.clear();

		listOfParameters.setModel(model);
	}

	public void addToList()
	{
		String updatedParameters = "";
		if (comboBoxMainArgument.getSelectedItem().toString().equals("Panel"))
		{
			if (!comboBoxObjects.getSelectedItem().toString().equals("Language Switcher") && !comboBoxObjects.getSelectedItem().toString().equals("Installation") && !comboBoxObjects.getSelectedItem().toString().equals("Open Read-me File"))
			{
				updatedParameters = "<" + comboBoxPanels.getSelectedItem().toString() + ";" + comboBoxObjects.getSelectedItem().toString() + ";" + textFieldCoordiantes.getText() + ";" + textFieldTextArguments.getText() + ">";
			}
			else if (comboBoxObjects.getSelectedItem().toString().equals("Open Read-me File"))
			{
				updatedParameters = "<" + comboBoxPanels.getSelectedItem().toString() + ";" + comboBoxObjects.getSelectedItem().toString() + ";" + textFieldCoordiantes.getText() + ";" + nameOfReadMeFile + ";" + textFieldTextArguments.getText() + ">";
			}
			else
			{
				updatedParameters = "<" + comboBoxPanels.getSelectedItem().toString() + ";" + comboBoxObjects.getSelectedItem().toString() + ";" + textFieldCoordiantes.getText() + ">";
			}

		}
		else if (comboBoxMainArgument.getSelectedItem().toString().equals("Installation"))
		{
			updatedParameters = "<i" + ";" + nameOfSoftware + ">";
		}

		model.addElement(updatedParameters);
	}

	private void languageMessageBox()
	{
		JTextField textFieldlistOfLanguages = new JTextField();

		final JComponent[] inputs = new JComponent[]
		{ new JLabel("Name the languages"), textFieldlistOfLanguages };

		TextPrompt textFieldlistOfLanguagesPromt = new TextPrompt("example1;example2;example3", textFieldlistOfLanguages);
		textFieldlistOfLanguagesPromt.setForeground(Color.GRAY);
		textFieldlistOfLanguagesPromt.changeAlpha(128);
		textFieldlistOfLanguagesPromt.changeStyle(Font.BOLD + Font.ITALIC);
		textFieldlistOfLanguagesPromt.setShow(Show.FOCUS_LOST);
		textFieldlistOfLanguagesPromt.setIcon(imageMessageBoxResized);
		toolTip(textFieldlistOfLanguages, "Insert languages for installer, use \";\" between languages to separate them");

		int result = JOptionPane.showConfirmDialog(null, inputs, "Choose your language", JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION)
		{
			listOfLanguages = textFieldlistOfLanguages.getText();
			if (listOfLanguages.equals(""))
				listOfLanguages = "(deafault)";
		}
		if (result == JOptionPane.CLOSED_OPTION)
		{
			System.exit(0);
		}
	}

	private void generisi(int indeks)
	{
		try
		{
			String sviParametri[] = new String[listOfParameters.getModel().getSize()];

			destinacijaReadMe = null;

			idList = new ArrayList<>();

			listOfLabels = new ArrayList<>();
			listOfDestinationButtons = new ArrayList<>();
			listOfCBShortcuts = new ArrayList<>();
			listOfCBReadMe = new ArrayList<>();
			listOfCBAutoRun = new ArrayList<>();
			listOfComboBoxLanguageSwitcher = new ArrayList<>();
			listOfTermsAndConditions = new ArrayList<>();
			listOfNextButtons = new ArrayList<>();
			listOfCloseButtons = new ArrayList<>();

			System.out.println(listOfParameters.getModel().getSize());
			for (int i = 0; i < listOfParameters.getModel().getSize(); i++)
			{
				sviParametri[i] = listOfParameters.getModel().getElementAt(i).toString();
			}

			MyTermsAndConditions.accepted = true;

			for (int i = 0; i < nizPanela.size(); i++)
			{
				nizPanela.get(i).setVisible(false);
			}

			nizPanela = new ArrayList<>();
			int numberOfPanels = 0;

			for (int i = 0; i < sviParametri.length; i++)
			{

				String jedanRed[] = sviParametri[i].substring(1, sviParametri[i].length() - 1).split(";");
				String prviArgument = jedanRed[0];

				if (prviArgument.length() >= 5)
				{
					if (prviArgument.substring(0, 5).equals("Panel"))
					{

						int redniBrojPanela = Integer.parseInt(prviArgument.substring(6, prviArgument.length()));

						if (redniBrojPanela >= numberOfPanels)
						{
							for (int j = numberOfPanels; j <= redniBrojPanela; j++)
							{
								System.out.println("Koliko ovde puta");
								myJPanel newPanel = new myJPanel(imageIconMainPanel);
								newPanel.setLayout(null);
								newPanel.setBackground(new Color(1.0f, 1.0f, 1.0f, 0f));
								nizPanela.add(newPanel);
							}
							numberOfPanels = redniBrojPanela + 1;
						}

						nizPanela.get(redniBrojPanela - 1).setBounds(800, 240, 680, 370);

						String koordinateString[] = jedanRed[2].split(",");
						int koordinate[] = new int[4];

						for (int j = 0; j < koordinateString.length; j++)
						{
							koordinate[j] = Integer.parseInt(koordinateString[j]);
						}

						if (jedanRed[1].equals("Label"))
						{
							JLabel newLabel = new JLabel(jedanRed[3]);
							newLabel.setBounds(koordinate[0], koordinate[1], koordinate[2], koordinate[3]);
							System.out.println("Na ovaj panel radis: " + redniBrojPanela);
							nizPanela.get(redniBrojPanela - 1).add(newLabel);
							listOfLabels.add(newLabel);
							idList.add("lb");
						}
						else if (jedanRed[1].equals("Button Next"))
						{
							MyJButtonNext newButtonNext = new MyJButtonNext(nizPanela, redniBrojPanela - 1, koordinate[2], koordinate[3]);

							newButtonNext.setText(jedanRed[3]);

							listOfNextButtons.add(newButtonNext);
							newButtonNext.setBounds(koordinate[0], koordinate[1], koordinate[2], koordinate[3]);
							nizPanela.get(redniBrojPanela - 1).add(newButtonNext);
							idList.add("btnNext");
						}
						else if (jedanRed[1].equals("Button Close"))
						{
							MyJButtonClose newButtonClose = new MyJButtonClose(koordinate[2], koordinate[3]);

							newButtonClose.setBounds(koordinate[0], koordinate[1], koordinate[2], koordinate[3]);

							newButtonClose.setText(jedanRed[3]);

							newButtonClose.addActionListener(new ActionListener()
							{

								@Override
								public void actionPerformed(ActionEvent e)
								{
									if (shortCutCheck)
									{
										System.out.println(destinacijaInstalacije);
										createDesktopShortcut(destinacijaInstalacije, nameOfSoftware);
									}
									if (readMeFileCheck)
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
									if (autorun)
									{
										File file = new File(destinacijaInstalacije);

										try
										{
											openReadMeFile(file);
											System.out.println("pokusao");
										}
										catch (Exception ex)
										{
											ex.printStackTrace();
										}
									}
									System.exit(0);
								}
							});

							nizPanela.get(redniBrojPanela - 1).add(newButtonClose);

							listOfCloseButtons.add(newButtonClose);
							idList.add("btnClose");
						}
						else if (jedanRed[1].equals("Language Switcher"))
						{
							String[] languages = listOfLanguages.split(";");

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

							jComboBoxLanguageSwitcher.setBounds(koordinate[0], koordinate[1], koordinate[2], koordinate[3]);
							nizPanela.get(redniBrojPanela - 1).add(jComboBoxLanguageSwitcher);
							listOfComboBoxLanguageSwitcher.add(jComboBoxLanguageSwitcher);
							idList.add("cbLang");
						}
						else if (jedanRed[1].equals("Terms and Conditions"))
						{
							MyTermsAndConditions newTerms = new MyTermsAndConditions(nizPanela.get(redniBrojPanela - 1), koordinate, jedanRed[3]);

							idList.add("tmc");
						}
						else if (jedanRed[1].equals("Installation"))
						{
							JProgressBar progressBar = new JProgressBar();
							progressBar.setBounds(koordinate[0], koordinate[1], koordinate[2], koordinate[3]);

							nizPanela.get(redniBrojPanela - 1).add(progressBar);

							idList.add("inst");
						}
						else if (jedanRed[1].equals("Software Folder Destination"))
						{
							MyJTextFieldBrowseLocation textFieldDestinationFolder = new MyJTextFieldBrowseLocation(nizPanela.get(redniBrojPanela - 1));
							TextPrompt textFieldDestinationFolderPromt = new TextPrompt("Choose destination folder", textFieldDestinationFolder);
							textFieldDestinationFolderPromt.setForeground(Color.GRAY);
							textFieldDestinationFolderPromt.changeAlpha(128);
							textFieldDestinationFolderPromt.changeStyle(Font.BOLD);
							textFieldDestinationFolderPromt.setShow(Show.FOCUS_LOST);
							textFieldDestinationFolderPromt.setIcon(imageMessageBoxResized);

							textFieldDestinationFolder.setBounds(koordinate[0], koordinate[1], koordinate[2], koordinate[3]);

							nizPanela.get(redniBrojPanela - 1).add(textFieldDestinationFolder);

							Image img = icnBrowse.getImage();
							img = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
							final ImageIcon imageBrowseResized = new ImageIcon(img);

							JButton buttonDestinationFolder = new JButton();
							buttonDestinationFolder.setIcon(imageBrowseResized);
							buttonDestinationFolder.setContentAreaFilled(false);
							buttonDestinationFolder.setBorder(BorderFactory.createEmptyBorder());

							JFileChooser chooser = new JFileChooser();
							chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

							buttonDestinationFolder.setBounds(koordinate[0] + koordinate[2] + 1, koordinate[1], 80, koordinate[3]);

							nizPanela.get(redniBrojPanela - 1).add(buttonDestinationFolder);
							listOfDestinationButtons.add(buttonDestinationFolder);
							idList.add("txtDest");

							buttonDestinationFolder.addMouseListener(new MouseAdapter()
							{

								@Override
								public void mouseClicked(MouseEvent e)
								{
									super.mouseClicked(e);

									int returnVal = chooser.showOpenDialog(null);
									if (returnVal == JFileChooser.APPROVE_OPTION)
									{
										destPath = chooser.getSelectedFile().getAbsolutePath();
										textFieldDestinationFolder.setText(destPath);
									}

								}
							});
						}
						else if (jedanRed[1].equals("Shourtcut option"))
						{
							JCheckBox checkBoxShourtCut = new JCheckBox(jedanRed[3]);

							checkBoxShourtCut.setBounds(koordinate[0], koordinate[1], koordinate[2], koordinate[3]);

							checkBoxShourtCut.addActionListener(new ActionListener()
							{

								@Override
								public void actionPerformed(ActionEvent e)
								{
									if (checkBoxShourtCut.isSelected())
									{
										shortCutCheck = true;
									}
								}
							});

							nizPanela.get(redniBrojPanela - 1).add(checkBoxShourtCut);
							listOfCBShortcuts.add(checkBoxShourtCut);
							idList.add("cbSC");
						}
						else if (jedanRed[1].equals("Run software after instalation"))
						{
							JCheckBox checkBoxRunAfterInst = new JCheckBox(jedanRed[3]);

							checkBoxRunAfterInst.setBounds(koordinate[0], koordinate[1], koordinate[2], koordinate[3]);

							checkBoxRunAfterInst.addActionListener(new ActionListener()
							{
								@Override
								public void actionPerformed(ActionEvent e)
								{
									if (checkBoxRunAfterInst.isSelected())
									{
										autorun = true;
									}
								}
							});

							nizPanela.get(redniBrojPanela - 1).add(checkBoxRunAfterInst);
							listOfCBAutoRun.add(checkBoxRunAfterInst);
							idList.add("cbRun");
						}
						else if (jedanRed[1].equals("Open Read-me File"))
						{
							JCheckBox checkBoxReadMeFile = new JCheckBox(jedanRed[4]);

							checkBoxReadMeFile.setBounds(koordinate[0], koordinate[1], koordinate[2], koordinate[3]);

							destinacijaReadMe = jedanRed[3];

							checkBoxReadMeFile.addActionListener(new ActionListener()
							{

								@Override
								public void actionPerformed(ActionEvent e)
								{
									if (checkBoxReadMeFile.isSelected())
									{
										readMeFileCheck = true;
									}
								}
							});

							nizPanela.get(redniBrojPanela - 1).add(checkBoxReadMeFile);
							listOfCBReadMe.add(checkBoxReadMeFile);
							idList.add("cbRead");
						}
						else if (jedanRed[1].equals("Image"))
						{
							ImageIcon icon = new ImageIcon(jedanRed[3]);

							Image img = icon.getImage();
							img = img.getScaledInstance(koordinate[2], koordinate[3], Image.SCALE_SMOOTH);
							ImageIcon image = new ImageIcon(img);

							JLabel label = new JLabel();
							label.setIcon(image);

							label.setBounds(koordinate[0], koordinate[1], koordinate[2], koordinate[3]);

							nizPanela.get(redniBrojPanela - 1).add(label);
						}
					}
				}
			}

			for (int i = 0; i < nizPanela.size(); i++)
			{
				nizPanela.get(i).setVisible(false);
				layeredPane.add(nizPanela.get(i), new Integer(10));
			}

			nizPanela.get(0).setVisible(true);

			for (int i = 0; i < nizPanela.get(0).getComponentCount(); i++)
			{
				if (nizPanela.get(0).getComponent(i) instanceof MyJCheckBoxTermsAccept)
				{
					MyTermsAndConditions.accepted = false;
					for (int j = 0; j < nizPanela.get(0).getComponentCount(); j++)
					{
						if (nizPanela.get(0).getComponent(j) instanceof MyJButtonNext)
						{
							MyJButtonNext mb = (MyJButtonNext) nizPanela.get(0).getComponent(j);
							mb.setButtonDisabled();

						}
					}
					break;
				}
			}

			for (int i = 0; i < nizPanela.get(0).getComponentCount(); i++)
			{
				if (nizPanela.get(0).getComponent(i) instanceof MyJTextFieldBrowseLocation)
				{
					MyJTextFieldBrowseLocation.enabled = false;
					for (int j = 0; j < nizPanela.get(0).getComponentCount(); j++)
					{
						if (nizPanela.get(0).getComponent(j) instanceof MyJButtonNext)
						{
							MyJButtonNext mb = (MyJButtonNext) nizPanela.get(0).getComponent(j);
							mb.setButtonDisabled();

						}
					}
					break;
				}
			}
		}
		catch (Exception e) 
		{
			System.err.println("Wrong input of parameters");
		}
		
	}

	private void promenaJezika(int indeks)
	{
		try
		{
			String sviParametri[] = new String[listOfParameters.getModel().getSize()];

			int pomLb = 0, pomBtnNext = 0, pomBtnClose = 0, pomCbLang = 0, pomTxtDest = 0, pomCbSC = 0, pomcbRun = 0, pomCbRead = 0, pomTC = 0;

			for (int i = 0; i < listOfParameters.getModel().getSize(); i++)
			{
				sviParametri[i] = listOfParameters.getModel().getElementAt(i).toString();
			}

			for (int i = 0; i < sviParametri.length; i++)
			{
				String jedanRed[] = sviParametri[i].substring(1, sviParametri[i].length() - 1).split(";");
				String prviArgument = jedanRed[0];

				if (prviArgument.length() >= 5)
				{
					if (jedanRed[1].equals("Label") && idList.get(i).equals("lb"))
					{
						listOfLabels.get(pomLb++).setText(jedanRed[3 + indeks]);
					}
					else if (jedanRed[1].equals("Button Next") && idList.get(i).equals("btnNext"))
					{
						listOfNextButtons.get(pomBtnNext++).setText(jedanRed[3 + indeks]);
					}
					else if (jedanRed[1].equals("Button Close") && idList.get(i).equals("btnClose"))
					{
						listOfCloseButtons.get(pomBtnClose++).setText(jedanRed[3 + indeks]);
					}
					else if (jedanRed[1].equals("Language Switcher") && idList.get(i).equals("cbLang"))
					{
						listOfComboBoxLanguageSwitcher.get(pomCbLang++).setSelectedIndex(indeks);
					}
					else if (jedanRed[1].equals("Terms and Conditions") && idList.get(i).equals("tmc"))
					{
						listOfTermsAndConditions.get(pomTC++).setText(jedanRed[3 + indeks]);
					}
					else if (jedanRed[1].equals("Software Folder Destination") && idList.get(i).equals("txtDest"))
					{
						listOfDestinationButtons.get(pomTxtDest++).setText(jedanRed[3 + indeks]);
					}
					else if (jedanRed[1].equals("Shourtcut option") && idList.get(i).equals("cbSC"))
					{
						listOfCBShortcuts.get(pomCbSC++).setText(jedanRed[3 + indeks]);
					}
					else if (jedanRed[1].equals("Run software after instalation") && idList.get(i).equals("cbRun"))
					{
						listOfCBAutoRun.get(pomcbRun++).setText(jedanRed[3 + indeks]);
					}
					else if (jedanRed[1].equals("Open Read-me File") && idList.get(i).equals("cbRead"))
					{
						listOfCBReadMe.get(pomCbRead++).setText(jedanRed[4 + indeks]);
					}
				}
			}
		}
		catch (Exception e) 
		{
			System.err.println("Wrong input of parameters");
		}
	}

	private void openReadMeFile(File document) throws IOException
	{
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
		catch (Exception e)
		{
			System.err.println("SHOURTCUT NOT WORKING");
		}
	}

	public String getNameOfSoftware()
	{
		return nameOfSoftware;
	}
}
