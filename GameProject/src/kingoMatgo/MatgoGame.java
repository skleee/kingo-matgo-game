package kingoMatgo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;


import javax.swing.JTextPane;


/**
 * MatgoGame class
 * @author SKLEE
 *
 */
public class MatgoGame extends JFrame {

	private JPanel contentPane;
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackgroundTitle.png")).getImage();	
	
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	private ImageIcon exitButtonHoveredImage = new ImageIcon(Main.class.getResource("../images/exitButtonHover.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon ruleButtonBasicImage = new ImageIcon(Main.class.getResource("../images/ruleButtonBasic.png"));
	private ImageIcon ruleButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/ruleButtonEntered.png"));
	private ImageIcon homeButtonBasicImage = new ImageIcon(Main.class.getResource("../images/homeButtonBasic.png"));
	private ImageIcon homeButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/homeButtonEntered.png"));	
	private ImageIcon characterButtonBasicImage1 = new ImageIcon(Main.class.getResource("../images/character1Basic.png"));
	private ImageIcon characterButtonEnteredImage1 = new ImageIcon(Main.class.getResource("../images/character1Entered.png"));
	private ImageIcon characterButtonBasicImage2 = new ImageIcon(Main.class.getResource("../images/character2Basic.png"));
	private ImageIcon characterButtonEnteredImage2 = new ImageIcon(Main.class.getResource("../images/character2Entered.png"));
	private ImageIcon leftArrowButtonBasic = new ImageIcon(Main.class.getResource("../images/leftArrowButtonBasic.png"));
	private ImageIcon leftArrowButtonEntered = new ImageIcon(Main.class.getResource("../images/leftArrowButtonEntered.png"));
	private ImageIcon rightArrowButtonBasic = new ImageIcon(Main.class.getResource("../images/rightArrowButtonBasic.png"));
	private ImageIcon rightArrowButtonEntered = new ImageIcon(Main.class.getResource("../images/rightArrowButtonEntered.png"));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton ruleButton = new JButton(ruleButtonBasicImage);
	private JButton homeButton = new JButton(homeButtonBasicImage);
	private JButton characterButton1 = new JButton(characterButtonBasicImage1);
	private JButton characterButton2 = new JButton(characterButtonBasicImage2);
	private JButton leftButton = new JButton(leftArrowButtonBasic);
	private JButton rightButton = new JButton(rightArrowButtonBasic);

	private JPanel computerHandPanel = new JPanel();
	private JPanel userHandPanel = new JPanel();
	private JPanel tableCenterpanel = new JPanel();
	private JPanel computerTablePanel = new JPanel();
	private JPanel userTablePanel = new JPanel();
	
	private JTextPane progressTextPane = new JTextPane();
	private JScrollPane scrollPane = new JScrollPane();

	private int mouseX, mouseY;
	private int playerCharacter;
	
	private JButton[] userHandBtn = new JButton[24];
	private JLabel[] centerCardLabel = new JLabel[48];
	private JLabel[] userTableLabel = new JLabel[40];
	private JLabel[] computerTableLabel = new JLabel[40];
	private ArrayList<String> userHandImages = new ArrayList<String>();


	public static int goCnt = 0;
	private int myGoScore = 0;
	private int comGoScore = 0;
	private boolean isEnded = false; // Go or stop
	private int rulePage = 1;

	// Initialize game variables
	Deck deck;
	UserPlayer me;
	ComputerPlayer computer;
	TableCenter tableCenter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MatgoGame frame = new MatgoGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MatgoGame() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		setUndecorated(true);
		setTitle("KINGO MATGO");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		getContentPane().setLayout(null);
		
	
		
		JPanel testback = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(background, 0, 0, null);
				super.paintComponent(g);
			}
		};
		
		characterButton1.setVisible(false);
		characterButton2.setVisible(false);
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		scrollPane.setVisible(false);
		progressTextPane.setVisible(false);

		
		exitButton.setBounds(1240, 0, 40, 40);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered (MouseEvent e) {
				exitButton.setIcon(exitButtonHoveredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonHoveredMusic = new Music("buttonHoveredMusic.mp3", false);
				buttonHoveredMusic.start();
 			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonClickedMusic = new Music("buttonClickedMusic.mp3", false);
				buttonClickedMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		getContentPane().add(exitButton);
		
		homeButton.setBounds(1190, 0, 40, 40);
		homeButton.setBorderPainted(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setFocusPainted(false);
		homeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered (MouseEvent e) {
				homeButton.setIcon(homeButtonEnteredImage);
				homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonHoveredMusic = new Music("buttonHoveredMusic.mp3", false);
				buttonHoveredMusic.start();
 			}
			@Override
			public void mouseExited(MouseEvent e) {
				homeButton.setIcon(homeButtonBasicImage);
				homeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonClickedMusic = new Music("buttonClickedMusic.mp3", false);
				buttonClickedMusic.start();
				switchToHome();
			}
		});
		getContentPane().add(homeButton);
		
		startButton.setBounds(60, 420, 320, 180);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered (MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonHoveredMusic = new Music("buttonHoveredMusic.mp3", false);
				buttonHoveredMusic.start();
 			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonClickedMusic = new Music("buttonClickedMusic.mp3", false);
				buttonClickedMusic.start();
				switchToCharChoose();
			}
		});
		getContentPane().add(startButton);
		
		ruleButton.setBounds(370, 420, 320, 180);
		ruleButton.setBorderPainted(false);
		ruleButton.setContentAreaFilled(false);
		ruleButton.setFocusPainted(false);
		ruleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered (MouseEvent e) {
				ruleButton.setIcon(ruleButtonEnteredImage);
				ruleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonHoveredMusic = new Music("buttonHoveredMusic.mp3", false);
				buttonHoveredMusic.start();
 			}
			@Override
			public void mouseExited(MouseEvent e) {
				ruleButton.setIcon(ruleButtonBasicImage);
				ruleButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonClickedMusic = new Music("buttonClickedMusic.mp3", false);
				buttonClickedMusic.start();
				rulePage = 1;
				switchToRule(rulePage);
			}
		});
		getContentPane().add(ruleButton);
		
		// Button for character choice
		characterButton1.setBounds(102, 44, 500, 800);
		characterButton1.setBorderPainted(false);
		characterButton1.setContentAreaFilled(false);
		characterButton1.setFocusPainted(false);
		characterButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered (MouseEvent e) {
				characterButton1.setIcon(characterButtonEnteredImage1);
				characterButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonHoveredMusic = new Music("buttonHoveredMusic.mp3", false);
				buttonHoveredMusic.start();
 			}
			@Override
			public void mouseExited(MouseEvent e) {
				characterButton1.setIcon(characterButtonBasicImage1);
				characterButton1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonClickedMusic = new Music("buttonClickedMusic.mp3", false);
				buttonClickedMusic.start();
				playerCharacter = 1;
				switchToGamePlay();
			}
		});
		getContentPane().add(characterButton1);
		
		// Button for character choice
		characterButton2.setBounds(580, -31, 500, 900);
		characterButton2.setBorderPainted(false);
		characterButton2.setContentAreaFilled(false);
		characterButton2.setFocusPainted(false);
		characterButton2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered (MouseEvent e) {
				characterButton2.setIcon(characterButtonEnteredImage2);
				characterButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonHoveredMusic = new Music("buttonHoveredMusic.mp3", false);
				buttonHoveredMusic.start();
 			}
			@Override
			public void mouseExited(MouseEvent e) {
				characterButton2.setIcon(characterButtonBasicImage2);
				characterButton2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonClickedMusic = new Music("buttonClickedMusic.mp3", false);
				buttonClickedMusic.start();
				// character chosen and play game
				playerCharacter = 2;
				switchToGamePlay();
			}
		});
		getContentPane().add(characterButton2);
		
		// Rule page left button
		leftButton.setBounds(5, 420, 70, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered (MouseEvent e) {
				leftButton.setIcon(leftArrowButtonEntered);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonHoveredMusic = new Music("buttonHoveredMusic.mp3", false);
				buttonHoveredMusic.start();
 			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftArrowButtonBasic);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonClickedMusic = new Music("buttonClickedMusic.mp3", false);
				buttonClickedMusic.start();
				if (rulePage > 1) {
					rulePage--;
				}
				switchToRule(rulePage);
			}
		});
		getContentPane().add(leftButton);
		
		// Rule page right button
		rightButton.setBounds(1205, 420, 70, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered (MouseEvent e) {
				rightButton.setIcon(rightArrowButtonEntered);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonHoveredMusic = new Music("buttonHoveredMusic.mp3", false);
				buttonHoveredMusic.start();
 			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightArrowButtonBasic);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonClickedMusic = new Music("buttonClickedMusic.mp3", false);
				buttonClickedMusic.start();
				if (rulePage < 3) {
					rulePage++;
				}
				switchToRule(rulePage);
			}
		});
		getContentPane().add(rightButton);

		// Menubar
		menuBar.setBounds(0, 0, 1280, 40);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		getContentPane().add(menuBar);
		
		// Computer hand
		computerHandPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				Image resizedImg = resizeImage("flippedCard10.png", 285, 190).getImage();
				g.drawImage(resizedImg, 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		computerHandPanel.setBounds(789, 15, 285, 190);

		// User hand
		userHandPanel = new JPanel() {
			public void paintComponent(Graphics g) {			
				Image resizedImg = resizeImage("basicGreenBackground.png", 365, 232).getImage();
				g.drawImage(resizedImg, 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		userHandPanel.setBounds(707, 475, 365, 232);
		userHandPanel.setLayout(new GridLayout(2,5));
		
		// Table center
		tableCenterpanel = new JPanel() {
			public void paintComponent(Graphics g) {			
				Image resizedImg = resizeImage("darkGreenBackground.png", 710, 232).getImage();
				g.drawImage(resizedImg, 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		tableCenterpanel.setLayout(new GridLayout(2, 7));
		tableCenterpanel.setBounds(8, 224, 710, 232);
		
		// User table
		userTablePanel = new JPanel() {
			public void paintComponent(Graphics g) {			
				Image resizedImg = resizeImage("darkGreenBackground.png", 702, 230).getImage();
				g.drawImage(resizedImg, 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		userTablePanel.setBounds(8, 476, 702, 230);
		userTablePanel.setLayout(new GridLayout(2, 6));
		
		// Computer table
		computerTablePanel = new JPanel() {
			public void paintComponent(Graphics g) {			
				Image resizedImg = resizeImage("darkGreenBackground.png", 702, 210).getImage();
				g.drawImage(resizedImg, 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		computerTablePanel.setBounds(8, 0, 702, 210);
		computerTablePanel.setLayout(new GridLayout(2, 6));

		// Progress text pane
		progressTextPane = new JTextPane();
		scrollPane = new JScrollPane(progressTextPane);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportView(progressTextPane);
		scrollPane.setBounds(790, 224, 290, 236);
		
		// Music
		Music introMusic = new Music("intro.mp3", true);
		introMusic.start();
	}
	
	// Append to text pane for game progress
	private void appendToPane(JTextPane tp, String msg, Color c) {
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
		aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

		int len = tp.getDocument().getLength();
		tp.setCaretPosition(len);
		tp.setCharacterAttributes(aset, false);
		tp.replaceSelection(msg);
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);	
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintComponents(g); // draw JLabel etc
		this.repaint();
	}
	
	/**
	 * Switch page
	 */
	public void switchToHome() {
		startButton.setVisible(true);
		ruleButton.setVisible(true);
		characterButton1.setVisible(false);
		characterButton2.setVisible(false);
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		computerHandPanel.setVisible(false);
		userHandPanel.setVisible(false);
		computerTablePanel.setVisible(false);
		userTablePanel.setVisible(false);
		scrollPane.setVisible(false);
		progressTextPane.setVisible(false);
		tableCenterpanel.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/introBackgroundTitle.png"))
				.getImage();
	}

	public void switchToCharChoose() {
		startButton.setVisible(false);
		ruleButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/characterBackground.png"))
				.getImage();
		characterButton1.setVisible(true);
		characterButton2.setVisible(true);
	}
	
	public void switchToRule(int i) {
		startButton.setVisible(false);
		ruleButton.setVisible(false);
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		// Switch rule page
		background = new ImageIcon(Main.class.getResource("../images/ruleBackground"+String.valueOf(i)+".png"))
				.getImage();
		
	}
	
	public void switchToGamePlay() {
		// Change background depending on user's character choice
		background = new ImageIcon(Main.class.getResource("../images/gameBackground"+String.valueOf(playerCharacter)+".png"))
				.getImage();
		contentPane.add(userHandPanel);
		contentPane.add(userTablePanel);
		contentPane.add(computerHandPanel);
		contentPane.add(computerTablePanel);
		contentPane.add(tableCenterpanel);
		characterButton1.setVisible(false);
		characterButton2.setVisible(false);
		computerHandPanel.setVisible(true);
		userHandPanel.setVisible(true);
		tableCenterpanel.setVisible(true);
		userTablePanel.setVisible(true);
		computerTablePanel.setVisible(true);
		contentPane.add(scrollPane, BorderLayout.EAST);	
		scrollPane.setVisible(true);
		progressTextPane.setVisible(true);
		// Initializing game
		initGame(this);
		appendToPane(progressTextPane, "=========Game Start!!!========\n", Color.RED);
	}

	// Resize image
	public static ImageIcon resizeImage(String filename, int width, int height) {
		Image originImage = new ImageIcon(Main.class.getResource("../images/" + filename)).getImage();
		Image changedImage = originImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon iconNew = new ImageIcon(changedImage);
		return iconNew;
	}
	
	// Change card image to file
	public static String changeCardToFile(Card card) {
		String filename = String.valueOf(card.getMonth()) + "_" + card.getPicture() + ".png";
		return filename;
	}
	
	// Get clicked button index
	public int getClickedBtnIdx(JButton btn) {
		int idx = 0;
		for (int i=0; i<10; i++) {
			if (userHandBtn[i] == btn) {
				idx = i;
			}
		}
		return idx;
	}
	
	// Reset game
	public void resetGame() {
		goCnt = 0;
		myGoScore = 0;
		comGoScore = 0;
		isEnded = false;
		userHandBtn = new JButton[48];
		centerCardLabel = new JLabel[48];
		userTableLabel = new JLabel[48];
		computerTableLabel = new JLabel[48];
		userHandImages.clear();
		computerHandPanel.removeAll();
		userHandPanel.removeAll();
		tableCenterpanel.removeAll();
		computerTablePanel.removeAll();
		userTablePanel.removeAll();
		progressTextPane.setText("");
	}
	
	// Initializing game and all the GUI and variables
	public void initGame(MatgoGame myFrame) {

		resetGame();
		
		// New objects
		deck = new Deck(1);
		me = new UserPlayer(deck);
		computer = new ComputerPlayer(deck);
		tableCenter = new TableCenter(deck);
		
		// Get file name
		for (int i=0; i<10; i++) {
			userHandImages.add(changeCardToFile(me.getHand().get(i)));	
		}		
		// Show center cards
		for (int i=0; i<8; i++) {
			centerCardLabel[i] = new JLabel();
			centerCardLabel[i].setPreferredSize(new Dimension(65, 110));
			centerCardLabel[i].setIcon(resizeImage(changeCardToFile(tableCenter.getCenterCard().get(i)), 65, 110));
			tableCenterpanel.add(centerCardLabel[i]);
		}		
		// Show user's hand buttons
		for (int i=0; i<10; i++) {
			userHandBtn[i] = new JButton();
			userHandBtn[i].setPreferredSize(new Dimension(65, 110));
			userHandBtn[i].setIcon(resizeImage(changeCardToFile(me.getHand().get(i)), 65, 110)); // matched card 있으면 노란색으로
			userHandBtn[i].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton clickedbtn = (JButton)e.getSource();
					int idx = getClickedBtnIdx(clickedbtn);									
					clickedbtn.setEnabled(false);
					playUserTurn(me.getHand().get(idx));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					if (isEnded==false) {
						playComputerTurn();						
					}
				}
			});
			userHandPanel.add(userHandBtn[i]);
		}
		myFrame.getContentPane().add("Center", userHandPanel);	
	}
	
	/**
	 * Player's turn
	 * @param card: card that user pick
	 */
	public void playUserTurn(Card card) {
		appendToPane(progressTextPane, ">>>>>>> YOUR TURN <<<<<<<\n", Color.RED);
		String tmp = "> You put card "+ String.valueOf(card + "\n");
		appendToPane(progressTextPane, tmp, Color.BLACK);
		ArrayList<Card> userNewCardList = new ArrayList<Card>();
		Card selectedOne;
		
		// Check if there is a same card
		ArrayList<Card> sameMonthCard1 = tableCenter.getMatchedCard(card);
		if (sameMonthCard1.size() == 1) {
			userNewCardList.add(card);
			userNewCardList.add(sameMonthCard1.get(0));
		}
		else if (sameMonthCard1.size() == 2) {
			ChoiceDialog chooseCardDialog = new ChoiceDialog(MatgoGame.this, sameMonthCard1.get(0), sameMonthCard1.get(1));
			selectedOne = chooseCardDialog.getSelectedCard();
			userNewCardList.add(card);
			userNewCardList.add(selectedOne);
		}
		else if (sameMonthCard1.size() == 3) {
			appendToPane(progressTextPane, "** SSEUL\n", Color.BLUE);
			userNewCardList.add(card);
			userNewCardList.add(sameMonthCard1.get(0));					
			userNewCardList.add(sameMonthCard1.get(1));
			userNewCardList.add(sameMonthCard1.get(2));
		}
		
		// Take one card from the deck and add to table
		Card newCard = deck.getCard();
		tmp = "> You picked "+ String.valueOf(newCard) + " from the deck.\n";
		appendToPane(progressTextPane, tmp, Color.BLACK);		

		// Check if there is a same card
		ArrayList<Card> sameMonthCard2 = tableCenter.getMatchedCard(newCard);
		if (sameMonthCard2.size() == 1) {
			if (newCard.getMonth() == card.getMonth()) {
				appendToPane(progressTextPane, "** BBEOK\n", Color.BLUE);
				userNewCardList.clear();
			}
			else {
				appendToPane(progressTextPane, "** TTADAK\n", Color.BLUE);
				userNewCardList.add(newCard);
				userNewCardList.add(sameMonthCard2.get(0));
			}
		}
		else if (sameMonthCard2.size() == 2) {
			if (newCard.getMonth() == card.getMonth()) {
				userNewCardList.add(card);
				userNewCardList.add(newCard);
				userNewCardList.add(sameMonthCard2.get(0));					
				userNewCardList.add(sameMonthCard2.get(1));
				appendToPane(progressTextPane, "** SSEUL\n", Color.BLUE);
			}
			else {
				ChoiceDialog chooseCardDialog = new ChoiceDialog(MatgoGame.this, sameMonthCard2.get(0), sameMonthCard2.get(1));
				selectedOne = chooseCardDialog.getSelectedCard();
				appendToPane(progressTextPane, "** TTADAK\n", Color.BLUE);
				userNewCardList.add(newCard);
				userNewCardList.add(selectedOne);
			}
		}
		else if (sameMonthCard2.size() == 3) {
			appendToPane(progressTextPane, "** SSEUL\n", Color.BLUE);
			userNewCardList.add(newCard);
			userNewCardList.add(sameMonthCard2.get(0));					
			userNewCardList.add(sameMonthCard2.get(1));
			userNewCardList.add(sameMonthCard2.get(2));
		}

		// Discard my card or deck card if no match
		if (userNewCardList.contains(card) == false) {
			centerCardLabel[tableCenter.getCenterCard().size()] = new JLabel(); 
			centerCardLabel[tableCenter.getCenterCard().size()].setPreferredSize(new Dimension(65, 110));
			centerCardLabel[tableCenter.getCenterCard().size()].setIcon(resizeImage(changeCardToFile(card), 65, 110));
			tableCenterpanel.add(centerCardLabel[tableCenter.getCenterCard().size()]);
			tableCenter.addCard(card);
		}
		if (userNewCardList.contains(newCard) == false) {
			centerCardLabel[tableCenter.getCenterCard().size()] = new JLabel(); 
			centerCardLabel[tableCenter.getCenterCard().size()].setPreferredSize(new Dimension(65, 110));
			centerCardLabel[tableCenter.getCenterCard().size()].setIcon(resizeImage(changeCardToFile(newCard), 65, 110));
			tableCenterpanel.add(centerCardLabel[tableCenter.getCenterCard().size()]);
			tableCenter.addCard(newCard);
		}
		String tmpstr = "> You get ";
		// Remove from the center add to my table
		ArrayList<Integer> delidxList = new ArrayList<Integer>();
		for (Card c: userNewCardList) {
			tmpstr += String.valueOf(c) + " & ";
			if (c.equals(card) == false && c.equals(newCard) == false) {
				int delidx = tableCenter.getCenterCard().indexOf(c);
				delidxList.add(delidx);
				tableCenter.removeCard(c);
			}	
			userTableLabel[me.getTable().size()] = new JLabel(); // Add to center table GUI
			userTableLabel[me.getTable().size()].setPreferredSize(new Dimension(65, 110));
			userTableLabel[me.getTable().size()].setIcon(resizeImage(changeCardToFile(c), 65, 110));
			userTablePanel.add(userTableLabel[me.getTable().size()]);
			me.addCardToTable(c);
		}
		for (Integer i: delidxList) {
			tableCenterpanel.remove(centerCardLabel[i]);			
		}		
		
		// If no match, nothing added to my table
		if (userNewCardList.isEmpty()) {
			appendToPane(progressTextPane, "> No matches. You get nothing from the table.\n", Color.BLACK);
		}
		else {
			appendToPane(progressTextPane, tmpstr + " from the table.\n", Color.BLACK);			
		}
		
		// Reset center table
		tableCenterpanel.removeAll();
		for (int i=0; i<tableCenter.getCenterCard().size(); i++) {
			centerCardLabel[i] = new JLabel();
			centerCardLabel[i].setPreferredSize(new Dimension(65, 110));
			centerCardLabel[i].setIcon(resizeImage(changeCardToFile(tableCenter.getCenterCard().get(i)), 65, 110));
			tableCenterpanel.add(centerCardLabel[i]);
		}
		if (isGameEnded(me) == true) {
			isEnded = true;
			JOptionPane.showMessageDialog(MatgoGame.this, "Game ended!",
					"GAME ENDED", JOptionPane.WARNING_MESSAGE);
			switchToGamePlay();
		}

	}
	
	/**
	 * Computer's turn
	 */
	public void playComputerTurn() {
		// Computer put randomly chosen card
		Card card = computer.playCard(tableCenter);
		appendToPane(progressTextPane, ">>>>>>> COMPUTER TURN <<<<<<<\n", Color.RED);
		String tmp = "> Computer put card "+ String.valueOf(card + "\n");
		appendToPane(progressTextPane, tmp, Color.BLACK);
		
		ArrayList<Card> comNewCardList = new ArrayList<Card>();		
		Card selectedOne;
		
		// Check if there is a same card
		ArrayList<Card> sameMonthCard1 = tableCenter.getMatchedCard(card);
		if (sameMonthCard1.size() == 1) {
			comNewCardList.add(card);
			comNewCardList.add(sameMonthCard1.get(0));
		}
		else if (sameMonthCard1.size() == 2) {
			ArrayList<Card> tmparr = new ArrayList<Card>();
			tmparr.add(sameMonthCard1.get(0));
			tmparr.add(sameMonthCard1.get(1));
			Collections.shuffle(tmparr);
			selectedOne = tmparr.get(0);
			comNewCardList.add(card);
			comNewCardList.add(selectedOne);
		}
		else if (sameMonthCard1.size() == 3) {
			appendToPane(progressTextPane, "** SSEUL\n", Color.BLUE);
			comNewCardList.add(card);
			comNewCardList.add(sameMonthCard1.get(0));					
			comNewCardList.add(sameMonthCard1.get(1));
			comNewCardList.add(sameMonthCard1.get(2));
		}		
		
		// Take one card from the deck and add to table		
		Card newCard = deck.getCard();
		tmp = "> Computer picked "+ String.valueOf(newCard) + " from the deck.\n";
		appendToPane(progressTextPane, tmp, Color.BLACK);
		
		
		// Check if there is a same card
		ArrayList<Card> sameMonthCard2 = tableCenter.getMatchedCard(newCard);
		if (sameMonthCard2.size() == 1) {
			if (newCard.getMonth() == card.getMonth()) {
				appendToPane(progressTextPane, "** BBEOK\n", Color.BLUE);
				comNewCardList.clear();
			}
			else {
				appendToPane(progressTextPane, "** TTADAK\n", Color.BLUE);
				comNewCardList.add(newCard);
				comNewCardList.add(sameMonthCard2.get(0));
			}
		}
		else if (sameMonthCard2.size() == 2) {
			if (newCard.getMonth() == card.getMonth()) {
				appendToPane(progressTextPane, "** SSEUL", Color.BLUE);
				comNewCardList.add(card);
				comNewCardList.add(newCard);
				comNewCardList.add(sameMonthCard2.get(0));					
				comNewCardList.add(sameMonthCard2.get(1));
			}
			else {
				ArrayList<Card> tmparr = new ArrayList<Card>();
				tmparr.add(sameMonthCard2.get(0));
				tmparr.add(sameMonthCard2.get(1));
				Collections.shuffle(tmparr);
				selectedOne = tmparr.get(0);
				appendToPane(progressTextPane, "** TTADAK\n", Color.BLUE);
				comNewCardList.add(newCard);
				comNewCardList.add(selectedOne);
			}
		}
		else if (sameMonthCard2.size() == 3) {
			appendToPane(progressTextPane, "** SSEUL\n", Color.BLUE);
			comNewCardList.add(newCard);
			comNewCardList.add(sameMonthCard2.get(0));					
			comNewCardList.add(sameMonthCard2.get(1));
			comNewCardList.add(sameMonthCard2.get(2));
		}
		
		// Put my card or deck card to the table if no match
		if (comNewCardList.contains(card) == false) {
			centerCardLabel[tableCenter.getCenterCard().size()] = new JLabel(); 
			centerCardLabel[tableCenter.getCenterCard().size()].setPreferredSize(new Dimension(65, 110));
			centerCardLabel[tableCenter.getCenterCard().size()].setIcon(resizeImage(changeCardToFile(card), 65, 110));
			tableCenterpanel.add(centerCardLabel[tableCenter.getCenterCard().size()]);
			tableCenter.addCard(card);
		}
		if (comNewCardList.contains(newCard) == false) {
			centerCardLabel[tableCenter.getCenterCard().size()] = new JLabel(); 
			centerCardLabel[tableCenter.getCenterCard().size()].setPreferredSize(new Dimension(65, 110));
			centerCardLabel[tableCenter.getCenterCard().size()].setIcon(resizeImage(changeCardToFile(newCard), 65, 110));
			tableCenterpanel.add(centerCardLabel[tableCenter.getCenterCard().size()]);
			tableCenter.addCard(newCard);
		}
		
		String tmpstr = "> Computer get ";
		// Remove from the center add to my table
		ArrayList<Integer> delidxList = new ArrayList<Integer>();
		for (Card c: comNewCardList) {
			tmpstr += String.valueOf(c) + " & ";
			if (c.equals(card) == false & c.equals(newCard) == false) {
				int delidx = tableCenter.getCenterCard().indexOf(c);
				delidxList.add(delidx);
				tableCenter.removeCard(c);
			}	
			computerTableLabel[computer.getTable().size()] = new JLabel(); // Add to center table GUI
			computerTableLabel[computer.getTable().size()].setPreferredSize(new Dimension(65, 110));
			computerTableLabel[computer.getTable().size()].setIcon(resizeImage(changeCardToFile(c), 65, 110));
			computerTablePanel.add(computerTableLabel[computer.getTable().size()]);
			computer.addCardToTable(c);
		}
		for (Integer i: delidxList) {
			//System.out.println(i + " ");		
			tableCenterpanel.remove(centerCardLabel[i]);			
		}
		for (Integer i: delidxList) {
			tableCenterpanel.remove(centerCardLabel[i]);			
		}
		// If no match, nothing added to my table
		if (comNewCardList.isEmpty()) {
			appendToPane(progressTextPane, "> No matches. Computer get nothing from the table.\n", Color.BLACK);
		}
		else {
			appendToPane(progressTextPane, tmpstr + " from the table.\n", Color.BLACK);			
		}
		
		// Reset center table
		tableCenterpanel.removeAll();
		for (int i=0; i<tableCenter.getCenterCard().size(); i++) {
			centerCardLabel[i] = new JLabel();
			centerCardLabel[i].setPreferredSize(new Dimension(65, 110));
			centerCardLabel[i].setIcon(resizeImage(changeCardToFile(tableCenter.getCenterCard().get(i)), 65, 110));
			tableCenterpanel.add(centerCardLabel[i]);
		}
		
		// Check if game ends
		if (isGameEnded(computer) == true) {
			isEnded = true;
			JOptionPane.showMessageDialog(MatgoGame.this, "Game ended!",
					"GAME ENDED", JOptionPane.WARNING_MESSAGE);
			switchToGamePlay();
		}		
	}
	/**
	 * Check if game is ended or not
	 * @param p: Player me or computer
	 * @return true if game is ended
	 */
	private boolean isGameEnded(Player p) {		
		// If player is me
		if (p == me) {
			int myScore = me.calculateScore();
			appendToPane(progressTextPane,"Score: "+myScore+"\n", Color.RED);
			if (goCnt>0) {
				if (myScore > myGoScore) {
					appendToPane(progressTextPane,"You are the winner. \n" , Color.RED);
					appendToPane(progressTextPane, "===========GAME ENDED============\n", Color.RED);
					return true;
				}
			}
			if (myScore >= 5) {				
				GoChoiceDialog goOrStop = new GoChoiceDialog(MatgoGame.this, "GO", "STOP");
				String choice = goOrStop.getAnswer();
				if (choice == "GO") {
					goCnt++;
					myGoScore = myScore;
					comGoScore = computer.calculateScore();
					appendToPane(progressTextPane,"GO!!!!!\n", Color.BLUE);
					return false;
				}
				else {
					isEnded = true;
					appendToPane(progressTextPane,"STOP!!!!!\n", Color.BLUE);			
					String tmp = "Your final score: " + String.valueOf(me.getFinalScore(myScore)) + "\n";
					appendToPane(progressTextPane,"You are the winner. \n" , Color.RED);
					appendToPane(progressTextPane, "===========GAME ENDED============\n", Color.RED);
					return true;
				}
			}
		}
		// If computer's turn
		else if (p == computer) {
			int comScore = computer.calculateScore();
			if (goCnt>0) {
				if (comScore > comGoScore) {
					appendToPane(progressTextPane,"You lose points after GO. YOU LOSE. \n" , Color.RED);
					appendToPane(progressTextPane, "===========GAME ENDED============\n", Color.RED);
					return true;
				}
			}
			appendToPane(progressTextPane,"Score: "+comScore+"\n", Color.RED);
			if (comScore >= 5) {
				isEnded = true;
				appendToPane(progressTextPane,"STOP!!!!!\n", Color.BLUE);
				String tmp =  " YOU LOSE. \n" + "===========GAME ENDED============\n";
				appendToPane(progressTextPane,tmp, Color.RED);
				return true;
			}
		}
		// Check if tie
		if (deck.getNumCards() == 0) {
			String tmp =  "===========GAME ENDED============\n " + "> No card left in deck. Tie!\n";
			appendToPane(progressTextPane,tmp, Color.RED);
			return true;
		}
		return false;
	}
	
}
