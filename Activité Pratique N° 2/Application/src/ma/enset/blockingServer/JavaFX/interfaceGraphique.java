package ma.enset.blockingServer.JavaFX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class interfaceGraphique extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("application chat");
        BorderPane borderPane=new BorderPane();

        Label labelHost=new Label("Host :");
        TextField textFieldHost=new TextField("localhost"); //0.0.0.0
        Label labelPort=new Label("Port :");
        TextField textFieldPort=new TextField("3");

        Button buttonConnecter=new Button("Connecter");

        HBox hBox=new HBox();  hBox.setSpacing(10);  hBox.setPadding(new Insets(10));
        hBox.getChildren().addAll(labelHost,textFieldHost,labelPort,textFieldPort,buttonConnecter);

        borderPane.setTop(hBox);

        VBox vBox=new VBox(); vBox.setSpacing(10);  vBox.setPadding(new Insets(10));
        ObservableList<String> list= FXCollections.observableArrayList();
        ListView<String> listView=new ListView<>(list);
        vBox.getChildren().add(listView);

        borderPane.setCenter(vBox);

        Scene scene=new Scene(borderPane,600,400);
        primaryStage.setScene(scene);
        primaryStage.show();

        buttonConnecter.setOnAction( (evt)->{
            String host=textFieldHost.getText();
            int port=Integer.parseInt(textFieldPort.getText());
//            Scanner scanner = new Scanner(System.in);
            try {
                Socket socket = new Socket(host, port);

                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is); //read one caracter
                BufferedReader br = new BufferedReader(isr); //read chaine caracter

                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);

                new Thread(() -> {
                    try {
                        while (true) {
                            String res = br.readLine();
                            list.add(res);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
