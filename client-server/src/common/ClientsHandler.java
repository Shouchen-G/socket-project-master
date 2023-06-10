package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import com.mavenproject.chatdata.App;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
 

public class ClientsHandler implements Runnable {
	
	Socket socket;
	TextArea sendMsgArea;
	Label statusText;
	Button sendButton;
	TextArea receivedMsgArea;
	ObservableList<String> clients;
	ListView<String> clientListView;
	Map<String, PrintWriter> map;
	Map <String,ClientsHandler> clientTread;
	ArrayList<ClientsHandler> clientHandlers;
//	App data;
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	


	
 
	public ClientsHandler(Map<String, PrintWriter> map, Socket socket, TextArea sendMsgArea, Label statusText, Button sendButton,
			TextArea receivedMsgArea, ObservableList<String> clients, ListView<String> clientListView
			) throws IOException, InterruptedException, ExecutionException {
		
		this.map = map;
		this.socket = socket;
		this.sendMsgArea = sendMsgArea;
		this.statusText = statusText;
		this.sendButton = sendButton;
		this.receivedMsgArea = receivedMsgArea;
		this.clients = clients;
		this.clientListView = clientListView;
//		this.data = new App();
	}
	
	

	



	
	public void updateForConnect(String remoteSocketAddress) {
		Platform.runLater(()->{
			clients.add(remoteSocketAddress);
//			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			receivedMsgArea.appendText(String.valueOf(clients.size()) + " Connected from " + remoteSocketAddress + " " + sdf.format(new Date()) + "\n");
			statusText.setText(String.valueOf(clients.size()) + " Connect success.");
			
		});
	}
	
	
	public void updateForDisConnect(String remoteSocketAddress) {
		Platform.runLater(()->{
			clients.remove(remoteSocketAddress);
			statusText.setText(String.valueOf(clients.size()) + " Connect success.");
			receivedMsgArea.appendText(remoteSocketAddress + " out of connected.." + "\n");
			map.remove(remoteSocketAddress);
		});
	}
	
	
	public void sendMessage() {
		Set<PrintWriter> printWriters = new HashSet<>();
		clientListView.getSelectionModel().selectedItemProperty().addListener(ov->{
			printWriters.clear();
			for(String key: clientListView.getSelectionModel().getSelectedItems()) {
//				String hhString = clientListView.getSelectionModel().getSelectedItem();
				printWriters.add(map.get(key));
			}
		});
		sendButton.setOnAction(e->{
			for (PrintWriter printWriter : printWriters) {
//				printWriter.write("127.0.0.1:9999" + "  " + sendMsgArea.getText() + "\r\n");
				String s = sendMsgArea.getText();
				printWriter.write( s + "\r\n");
				printWriter.flush();
				try {
					App.saveData("Server at: "+sdf.format(new Date()),s);
				} catch (InterruptedException | ExecutionException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		
//			System.out.println("hhh");
//			for (Map.Entry<String, ServerHandler> set :
//	            clientTread.entrySet()) {
//				String msg = sendMsgArea.getText();
//	           set.getValue().pWriter.write(msg);
//	           pWriter.flush();
//	       }
//			
//			
		});
	}
	
//	public void broadcastMessage() {
//		
//
//		
//	}
	
	@Override
	public void run() {
		String remoteSocketAddress = socket.getRemoteSocketAddress().toString().substring(1);
		updateForConnect(remoteSocketAddress);
		try {
			InputStream in = socket.getInputStream();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(in));
			OutputStream out = socket.getOutputStream();
			PrintWriter pWriter = new PrintWriter(out);
			map.put(remoteSocketAddress, pWriter);
			sendMessage();
			
//			for(String client:clients) {
//				pWriter.println(client);
//			}
//			pWriter.println(remoteSocketAddress);
//			pWriter.println("hello");
//			pWriter.flush();
			
//			sendButton.setOnAction(e->{
//			for (ClientsHandler c : clientTread.values()) {
//				OutputStream out = null;
//				try {
//					out = c.socket.getOutputStream();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				PrintWriter pWriter = new PrintWriter(out);
//				pWriter.println("127.0.0.1:9999" + "  " + sendMsgArea.getText() + "\r\n");
//				pWriter.flush();
//				StringBuffer s = new StringBuffer();
//				s.append('a');
//				pWriter.println(s);
//			}
//			});
//			
//			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//			Message message1 = (Message) ois.readObject();
			
		
			
			
			String message;
//			data = new App();
			while(true) {
				message = bReader.readLine();
				receivedMsgArea.appendText(message + "\n");
				App.saveData("Clients at: "+sdf.format(new Date()), message);
			}
		} catch (IOException e) {
			updateForDisConnect(remoteSocketAddress);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
}

// reference:https://blog.csdn.net/wqsbenren/article/details/89063274
//https://www.youtube.com/watch?v=ZIzoesrHHQo&t=165s
//https://www.youtube.com/watch?v=rd272SCl-XE
//https://www.youtube.com/watch?v=gLfuZrrfKes&list=PLoNhTf3GwFOfvJFgszH7cRuenIFHEXfHG&index=8
//https://www.youtube.com/watch?v=_1nqY-DKP9A&list=PLoNhTf3GwFOfvJFgszH7cRuenIFHEXfHG&index=10
