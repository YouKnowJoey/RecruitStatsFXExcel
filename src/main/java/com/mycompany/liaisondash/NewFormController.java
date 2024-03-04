package com.mycompany.liaisondash;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class NewFormController implements Initializable{
    
    //Global Table Object - Data Singleton
    private StreamedTableView streamedTable = StreamedTableView.getInstance();

    
    //New Volunteer Object- Form Creation
    private AzVolunteer azRecruit = new AzVolunteer();
    
    //New Recruit Variables
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField ssn1;
    @FXML
    private TextField ssn2;
    @FXML
    private TextField ssn3;
    @FXML
    private TextField dodId;
    @FXML
    private TextField gtScore;
    @FXML
    private TextField employeeId;
    @FXML
    private TextField classNumber;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private DatePicker graduationDate;
    @FXML
    private ChoiceBox<String> chooseLiaison;
    @FXML
    private ChoiceBox<String> chooseGender;
    @FXML
    private ChoiceBox<String> chooseEthnicity;
    @FXML
    private ChoiceBox<String> chooseMos;
    @FXML
    private ChoiceBox<String> chooseHomestate;
    @FXML
    private ChoiceBox<String> chooseClearance;
    @FXML
    private ChoiceBox<String> chooseVolunteerType;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        newRecruitComponents();
    }
    public void newRecruitComponents() {
        // ChoiceBoxes for New Recuit Tab

        //Choose Liaison Box
        chooseLiaison.getItems().addAll("Fort Huachuca", "Fort Eisenhower", "Fort Leonard Wood", "Fort Moore", "Fort Jackson", "Fort Sam Houston", "Fort Gordon");
        chooseLiaison.setValue("Choose Liaison");
        chooseLiaison.setOnAction(event -> handleLiaisonSelection(chooseLiaison.getValue()));

        //Choose Gender Box
        chooseGender.getItems().addAll(0, BioInfo.getAllGenderNames());
        chooseGender.setValue("Gender");

        //Choose Ethnicity Box
        Map<String, String> ethnicityMap = new HashMap<>();
        ethnicityMap = BioInfo.getEthnicityMap();
        chooseEthnicity.getItems().addAll(ethnicityMap.values());
        chooseEthnicity.setValue("Ethnicity");

        //Choose HomeState Box
        chooseHomestate.getItems().addAll(0, azRecruit.getStates());
        chooseHomestate.setValue(" ");
        
        //Chooose Security Clearance Box
        chooseClearance.getItems().addAll(azRecruit.getClearances());
        chooseClearance.setValue(" ");

        //Chooose Volunteer Type Box
        chooseVolunteerType.getItems().addAll(azRecruit.getVolunteerType());
        chooseVolunteerType.setValue(" ");
        
        // Choose MOS Box 
        // -> Uses Event Handler "handleLiaisonSelection()"
    }

    // Event handler method for Liaison Selection
    private void handleLiaisonSelection(String selectedLiaison) {
        switch (selectedLiaison) {
            case "Fort Huachuca":
                System.out.println("Handling Fort Huachuca");
                chooseMos.getItems().addAll(0, azRecruit.getMosArray());
                break;
            case "Fort Eisenhower":
                System.out.println("Handling Fort Eisenhower");
                // Add your logic or actions for Fort Eisenhower
                break;
            case "Fort Leonard Wood":
                System.out.println("Handling Fort Leonard Wood");
                // Add your logic or actions for Fort Leonard Wood
                break;
            case "Fort Moore":
                System.out.println("Handling Fort Moore");
                // Add your logic or actions for Fort Moore
                break;
            case "Fort Jackson":
                System.out.println("Handling Fort Jackson");
                // Add your logic or actions for Fort Jackson
                break;
            case "Fort Sam Houston":
                System.out.println("Handling Fort Sam Houston");
                // Add your logic or actions for Fort Sam Houston
                break;
            case "Fort Gordon":
                System.out.println("Handling Fort Gordon");
                // Add your logic or actions for Fort Gordon
                break;
            default:
                System.out.println("No specific handling for " + selectedLiaison);
        }
    }

    @FXML
    private void submitApplication(ActionEvent event) {
        try {
            String ssn = combineText(ssn1, ssn2, ssn3);
            String ethnicityChar = String.valueOf(chooseEthnicity.getValue().charAt(0));
            BioInfo bio = new BioInfo(firstName.getText(), lastName.getText(), dateOfBirth.getValue(),
                     chooseHomestate.getValue(), chooseGender.getValue(), ethnicityChar);
            //userInput = +","
            switch (chooseLiaison.getValue()) {
                case "Fort Huachuca":
                    System.out.println("Handling Fort Huachuca Submit App");
                    System.out.println(this.streamedTable.getFile().getAbsolutePath());
                    azRecruit = new AzVolunteer(bio, chooseMos.getValue(), dodId.getText(), ssn, chooseClearance.getValue());
                    this.streamedTable.addVolunteer(azRecruit, gtScore.getText(), chooseVolunteerType.getValue());
                    System.out.println("Successful Fort Huachuca Submit App");
                    break;
                case "Fort Eisenhower":
                    System.out.println("Handling Fort Eisenhower");
                    // Add your logic or actions for Fort Eisenhower
                    break;
                case "Fort Leonard Wood":
                    System.out.println("Handling Fort Leonard Wood");
                    // Add your logic or actions for Fort Leonard Wood
                    break;
                case "Fort Moore":
                    System.out.println("Handling Fort Moore");
                    // Add your logic or actions for Fort Moore
                    break;
                case "Fort Jackson":
                    System.out.println("Handling Fort Jackson");
                    // Add your logic or actions for Fort Jackson
                    break;
                case "Fort Sam Houston":
                    System.out.println("Handling Fort Sam Houston");
                    // Add your logic or actions for Fort Sam Houston
                    break;
                case "Fort Gordon":
                    System.out.println("Handling Fort Gordon");
                    // Add your logic or actions for Fort Gordon
                    break;
            }
        } catch (IllegalArgumentException ex) {
            String exceptionMessage = ex.getMessage();
            JavaFxException fxException = new JavaFxException(exceptionMessage);
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException" + ex);
            JavaFxException fxException = new JavaFxException("Ensure all dates and dropdowns are appropriately chosen.");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private String combineText(TextField textField1, TextField textField2, TextField textField3) {
        String combinedText = textField1.getText().trim() + textField2.getText().trim() + textField3.getText().trim();
        return combinedText;
    }

}
