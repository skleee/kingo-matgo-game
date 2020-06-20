package kingoMatgo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChoiceDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField dialogText;
	private Card selectedCard;
	/**
	 * Create the dialog.
	 */
	public ChoiceDialog(JFrame parentFrame, Card card1, Card card2) {
		super(parentFrame, true);
		setTitle("Card choice");
		setBounds(100, 100, 318, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		{
			// First choice
			JButton choiceBtn1 = new JButton();
			choiceBtn1.setIcon(MatgoGame.resizeImage(MatgoGame.changeCardToFile(card1), 130, 220)); 
			choiceBtn1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedCard = card1;
					setVisible(false);
				}
			});
			contentPanel.add(choiceBtn1);
		}
		{
			// Second choice
			JButton choiceBtn2 = new JButton();
			choiceBtn2.setIcon(MatgoGame.resizeImage(MatgoGame.changeCardToFile(card2), 130, 220));
			choiceBtn2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedCard = card2;
					setVisible(false);
				}
			});
			contentPanel.add(choiceBtn2);
		}
		{
			dialogText = new JTextField();
			dialogText.setEditable(false);
			dialogText.setText("Choose one card to get!");
			dialogText.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(dialogText, BorderLayout.NORTH);
			dialogText.setColumns(10);
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	// Get selected card
	public Card getSelectedCard() {
		return selectedCard;
	}

}
