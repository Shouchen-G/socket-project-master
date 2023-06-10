package UI;
	
import javafx.scene.layout.BorderPane;
import common.MyServer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class ServerUI extends Application {
	TextArea receivedMsgArea = new TextArea();
	Label ipText = new Label();
	Label portText = new Label();
	TextArea sendMsgArea = new TextArea();
	Label statusText = new Label();
	Button sendButton = new Button(" Send ");
	ObservableList<String> clients = FXCollections.observableArrayList();
	ListView<String> clientListView = new ListView<>(clients);
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
		receivedMsgArea.setPrefHeight(410);
		rightPane.add(receivedMsgArea, 0, 1);
		
		//left gridpane IPAdress+Port
		GridPane leftPane1 = new GridPane();
		leftPane1.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		leftPane1.setHgap(5.5);
		leftPane1.setVgap(5.5);
		leftPane1.add(new Label("IPAdress:"), 0, 0);
//		ipText.setEditable(false);
		leftPane1.add(ipText, 1, 0);
		leftPane1.add(new Label("Port:"), 0, 1);
//		portText.setEditable(false);
		leftPane1.add(portText, 1, 1);
		
		Button startB = new Button("START");
		leftPane1.add(startB, 0, 2);
		
		//left gridpane Choose Client
		GridPane leftPane2 = new GridPane();
		leftPane2.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		leftPane2.setHgap(5.5);
		leftPane2.setVgap(5.5);
		leftPane2.add(new Label("Choose Client:"), 0, 0);
		clientListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		clientListView.setMaxHeight(80);
		clientListView.setMaxWidth(275);
		leftPane2.add(clientListView, 0, 1);
		//left gridpane Send Message
		leftPane2.add(new Label("Send Message:"), 0, 2);
		sendMsgArea.setMaxHeight(150);
		sendMsgArea.setMaxWidth(275);
		sendMsgArea.setWrapText(true);
		leftPane2.add(sendMsgArea, 0, 3);
		
		//left gridpane Connect Status + button
		GridPane leftPane3 = new GridPane();
		
//		Button button = new Button("click");
		
		leftPane3.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		leftPane3.setHgap(5.5);
		leftPane3.setVgap(5.5);
//		statusText.setEditable(false);
		leftPane3.add(statusText, 0, 0);
		leftPane3.add(sendButton, 1, 0);
//		leftPane3.add(button, 2, 0);
		
//		button.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent arg0) {
//				// TODO Auto-generated method stub
////				System.out.println("click");
//				new ChatView();
////				ClientUI clientUI = new ClientUI();
////				Scene scene2 = new Scene(clientUI);
////				primaryStage.setScene(scene2);
//			}
//		});
		
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(leftPane1, leftPane2, leftPane3);
		HBox hBox = new HBox();
		hBox.getChildren().addAll(vBox, rightPane);
		
		Scene scene = new Scene(hBox);
		
		primaryStage.setTitle("server");
		primaryStage.setScene(scene);
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
		primaryStage.show();
		
		statusText.setText("0 Connect success.");
		
		new Thread(new MyServer(ipText, portText, sendMsgArea, statusText, sendButton, receivedMsgArea, clients, clientListView)).start();
	}
	
	public static void main(String[] args) {
		launch(args);

	}
}

//reference:https://blog.csdn.net/wqsbenren/article/details/89063274


