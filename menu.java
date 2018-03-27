package DB.Project;

import javafx.application.Application;
import javafx.geometry.Hpos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.stage.Stage;
import java.awt.*;
import javax.swing.*;

class menu extends Application extends Jpanel {
   @Override
   public void start(Stage primaryStage) { 
   
   //variables for images
   private ImageIcon image1;
   private JLabel label1;
   private ImageIcon image2;
   private JLabel label2;
   
   
   
   //Buttons you want to press
   Hbox hbox = new Hbox();
   hBox.setSpacing(10);
   hbox.setAlignment(Pos.CENTER);
   Button btStart = new Button("Start Tetris"); 
   Button btAuthorview = new Button("About Author");
   Button btControl = new Button("Controls");
   hbox.getChildren().addAll(btStart, btAuthorview, btControl);
   
   btStart.StartOnAction(new EventHandler<ActionEvent>() {
      @Override 
      public void handle {
      
      
   });
   
   bt.Authorview.StartOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle {
      
      setLayout(new FlowLayout());
      image1 = new ImageIcon(getClass().getResource(tetris-h.JPG));
      label1 = new JLabel(image1);
      add(label1);
      image2 = new ImageIcon(getClass().getResource(11059957_10207168251952039_7610068404575190407.JPG));
      label2 = new JLabel(image2);
      add(label2);
      new image2toString.("Name: David Bell, hobbies: Surfing, video games,Age: 26); 

      
      
   });
   
   bt.Control(new EventHandler<ActionEvent>() {
      @Override
      public void handle { 
      System.out.println("Move: left/right descend quickly: down, rotate tetramino: Space bar."); 
      
      
   
   
   Scene scene = new Scene(hBox, 300, 50);
   primaryStage.setTitle("DBTetrismenu"); 
   primaryStage.setScene(scene);
   primaryStage.show();
   
   }//end main method
}//end class