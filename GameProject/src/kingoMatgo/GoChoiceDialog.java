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
/**
 * Dialog to choose go or stop
 * @author SKLEE
 *
 */
public class GoChoiceDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField dialogText;
	private String answer;
	/**
	 * Create the dialog.
	 */
	public GoChoiceDialog(JFrame parentFrame, String c1, String c2) {
		super(parentFrame, true);
		setTitle("GO OR STOP");
		setBounds(100, 100, 318, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		{
			JButton choiceBtn1 = new JButton(c1);
			choiceBtn1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					answer = c1;
					setVisible(false);
				}
			});
			contentPanel.add(choiceBtn1);
		}
		{
			JButton choiceBtn2 = new JButton(c2);
			choiceBtn2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					answer = c2;
					setVisible(false);
				}
			});
			contentPanel.add(choiceBtn2);
		}
		{
			dialogText = new JTextField();
			dialogText.setEditable(false);
			dialogText.setText("GO OR STOP");
			dialogText.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(dialogText, BorderLayout.NORTH);
			dialogText.setColumns(10);
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public String getAnswer() {
		return answer;
	}

}
