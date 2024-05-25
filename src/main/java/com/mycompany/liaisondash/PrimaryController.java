package com.mycompany.liaisondash;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;


import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;



/**
 * JavaFX Primary Controller for File Chooser
 */
public class PrimaryController {
    
    //Global Table Object - Data Singleton
    private StreamedTableView streamTable = StreamedTableView.getInstance();

    //Import Tab Variables
    @FXML
    private Button openButton;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void handleOpenButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Recruiting Excel File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xls", "*.xlsx", "*.xlsm"));

        // Set the initial directory to the user's current working directory
        String userDirectoryString = System.getProperty("user.dir");
        //String relativePath = "./LiaisonDashboard";
        File userDirectory = new File(userDirectoryString); // <- (Parent, child relative Path)
        fileChooser.setInitialDirectory(userDirectory);

        Stage stage = (Stage) openButton.getScene().getWindow();
        File excelFile = fileChooser.showOpenDialog(stage);

        if (excelFile != null) {
            try {
                // Create StreamedTableView with the selected Excel file
                streamTable.importExcel(excelFile);
                streamTable.initializeComponents(); // Initialize the TableView
                scrollPane.setContent(streamTable.getTableView()); // Set content of ScrollPane to TableView

                
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            streamTable.showInfoDialog("User cancelled");
        }
    }
}