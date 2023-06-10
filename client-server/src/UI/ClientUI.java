package UI;

import common.MyClient;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
 

public class ClientUI extends Application{
	final TextArea receivedMsgArea = new TextArea();
	final Label ipText = new Label();
	final Label portText = new Label();
	final TextArea sendMsgArea = new TextArea();
	final Text statusText = new Text();
	final Button sendButton = new Button(" Send ");
	RadioButton isEncrypt = new RadioButton("Encrypt");
	Button decryptButton = new Button("Decrypt message");
 
	@Override
	public void start(Stage primaryStage) throws Exception {
		//right gridpane Received Message
		GridPane rightPane = new GridPane();
		rightPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		rightPane.setHgap(5.5);
		rightPane.setVgap(5.5);
		rightPane.add(new Label("Received Message:"), 0, 0);
		receivedMsgArea.setWrapText(true);
		receivedMsgArea.setEditable(false);
		receivedMsgArea.setMaxWidth(350);
		receivedMsgArea.setPrefHeight(350);
		rightPane.add(receivedMsgArea, 0, 1);
		
		//leftpane IPAdress+Port
		GridPane leftPane1 = new GridPane();
		leftPane1.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		leftPane1.setHgap(5.5);
		leftPane1.setVgap(5.5);
		leftPane1.add(new Label("IPAdress:"), 0, 0);
//		ipText.setEditable(false);
//		ipText.setBackground(null);
		
		leftPane1.add(ipText, 1, 0);
		leftPane1.add(new Label("Local Port:"), 0, 1);
//		portText.setEditable(false);
		leftPane1.add(portText, 1, 1);
		
		//leftpane Send Message
		GridPane leftPane2 = new GridPane();
		leftPane2.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		leftPane2.setHgap(5.5);
		leftPane2.setVgap(5.5);
		leftPane2.add(new Label("Send Message:"), 0, 2);
		sendMsgArea.setPrefHeight(250);
		sendMsgArea.setMaxWidth(275);
		sendMsgArea.setWrapText(true);
		leftPane2.add(sendMsgArea, 0, 3);
		leftPane2.add(statusText, 0, 1);
		
		//leftpane Connect Status + button
		GridPane leftPane3 = new GridPane();
		leftPane3.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		leftPane3.setHgap(5.5);
		leftPane3.setVgap(5.5);
//		statusText.setEditable(false);
//		leftPane3.add(statusText, 0, 0);
		leftPane3.add(sendButton, 0, 0);
		leftPane3.add(isEncrypt, 1, 0);
		leftPane3.add(decryptButton, 0, 1);
		
		decryptButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new ChatView();
			}
		});
		
		//put all grind pane in hbox
		VBox vBox = new VBox();
		vBox.getChildren().addAll(leftPane1, leftPane2, leftPane3);
		HBox hBox = new HBox();
		hBox.getChildren().addAll(vBox, rightPane);
		
		Scene scene = new Scene(hBox);
		primaryStage.setTitle("client");
		primaryStage.setScene(scene);
		//close tread when close UI
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
		primaryStage.show();
		
		ipText.setText("Not connect to server");
		
		
		new Thread(new MyClient(receivedMsgArea, ipText, portText, sendMsgArea, statusText, sendButton,isEncrypt)).start();
	}
	
	public static void main(String[] args) {
		launch(args);

	}
}

//reference:https://blog.csdn.net/wqsbenren/article/details/89063274
