package UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import common.ClientsHandler;
import common.CryptoManager;
import common.Message;
import common.MyClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class ChatView extends JFrame {

	
	private static final long serialVersionUID = 1L;
	JTextArea textArea;
	JTextField textField;
	JButton button;
	JPanel panel;
	String sender;
	String receiver;
	
	public static void main(String[] args) {
		ChatView chatView = new ChatView();
	}
	
	public ChatView () {
//		this.sender = sender;
//		this.receiver = recevier;
		textArea = new JTextArea();
		textField = new JTextField(15);
		button = new JButton("Decrypt");
		panel = new JPanel();
		panel.add(textField);
		panel.add(button);
		
		this.add(textArea,"Center");
		this.add(panel,"South");
		this.setSize(300,200);
		this.setVisible(true);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String decrptString = CryptoManager.decrypt(textField.getText());
				textArea.append(decrptString);
			
			}
		});
	
}

	

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		if(e.getSource() == button) {
////			Message m = new Message();
////			
////			m.setSender(this.sender);
////			m.setRecevier("you");
////			m.setText("hello");
////			System.out.println(m.getText());
//			System.out.println("hello");
//		}
//	}

	
	
	
	
}

	

	
