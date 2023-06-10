package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
 

public class MyServer implements Runnable {
	
	
	public static final int PORT = 9999;
	
	Map<String, PrintWriter> map = new HashMap<>();

	ObservableList<String> clients;
	ListView<String> clientListView;
	private static Map <String,ClientsHandler> clientTread = new HashMap<String,ClientsHandler>();
	ArrayList<ClientsHandler> clientHandlers = new ArrayList<>();
//	private static ExecutorService threadPool = Executors.newCachedThreadPool();
	Label ipText;
	Label portText;
	TextArea sendMsgArea;
	Label statusText;
	Button sendButton;
	TextArea receivedMsgArea;
//	Button startB;
 
	
	public MyServer(Label ipText, Label portText, TextArea sendMsgArea, Label statusText, 
			Button sendButton, TextArea receivedMsgArea, ObservableList<String> clients, ListView<String> clientListView) {
		
		this.ipText = ipText;
		this.portText = portText;
		this.sendMsgArea = sendMsgArea;
		this.statusText = statusText;
		this.sendButton = sendButton;
		this.receivedMsgArea = receivedMsgArea;
		this.clients = clients;
		this.clientListView = clientListView;
//		this.startB = startB;
	}
	
	
	public void updateIpAndPort() {
		
		Platform.runLater(()->{
			ipText.setText("127.0.0.1");
			portText.setText(String.valueOf(PORT));
		});
	}
	
	@Override
	public void run() {
		
		ServerSocket server;
		Socket socket;
				try {
					
					updateIpAndPort();
					server = new ServerSocket(PORT);
					while(true) {
						socket = server.accept();
//						System.out.println("begin");
			
						ClientsHandler sh = new ClientsHandler(map, socket, sendMsgArea, statusText, sendButton, receivedMsgArea,
								clients, clientListView);
						Thread thread = new Thread(sh);

//						clientHandlers.add(sh);
						
						thread.start();
//						threadPool.submit(sh);
//						clientTread.put(socket.getRemoteSocketAddress().toString().substring(1), sh);
//						clientHandlers.add(sh);
//						threadPool.execute(sh);
						
					}
				} catch (IOException | InterruptedException | ExecutionException e) {
					// TODO 
					e.printStackTrace();
				}
			}
		

}
