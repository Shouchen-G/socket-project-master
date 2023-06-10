package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.mavenproject.chatdata.App;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
 

public class MyClient implements Runnable {
	TextArea receivedMsgArea;
	Label ipText;
	Label portText;
	TextArea sendMsgArea;
	Text statusText;
	Button sendButton;
	RadioButton isEncrypt;
 
 
	public MyClient(TextArea receivedMsgArea, Label ipText, Label portText, TextArea sendMsgArea,
			Text statusText, Button sendButton,RadioButton isEncrypt) {
		this.isEncrypt = isEncrypt;
		this.receivedMsgArea = receivedMsgArea;
		this.ipText = ipText;
		this.portText = portText;
		this.sendMsgArea = sendMsgArea;
		this.statusText = statusText;
		this.sendButton = sendButton;
	}
	
	
	public void updateIpAndPort(Socket socket) {
		Platform.runLater(()->{
			ipText.setText(socket.getLocalAddress().toString().substring(1));
			portText.setText(String.valueOf(socket.getLocalPort()));
		});
	}
 
	@Override
	public void run() {
		try {
			Socket socket = new Socket("127.0.0.1", 9999);
			updateIpAndPort(socket);
			statusText.setText("Connected..");
//			receivedMsgArea.appendText("Hello, I am server..." + "\n");
			InputStream in = socket.getInputStream();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(in));
			OutputStream out = socket.getOutputStream();
			PrintWriter pWriter = new PrintWriter(out);
		
//			System.out.println(isEncrypt.isSelected());
			sendButton.setOnAction(e->{
				if(isEncrypt.isSelected()) {
					String encryptM = CryptoManager.encrypt(sendMsgArea.getText());
					pWriter.write(socket.getLocalSocketAddress().toString().substring(1) + ":  " +encryptM 
				 + " [encrypted]"+ "\r\n");
					pWriter.flush();
					
				}else {
					
					pWriter.write(socket.getLocalSocketAddress().toString().substring(1) + "  " + sendMsgArea.getText() + "\r\n");
					pWriter.flush();
				}
			
			});
			
			String message;
			while(true) {
				message = bReader.readLine();
				receivedMsgArea.appendText(message + "\n");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			statusText.setText("Server is not activated.");
			receivedMsgArea.appendText("Server is not activated.");
			e.printStackTrace();
		}
	}
 
}
