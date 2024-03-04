package com.mycompany.liaisondash;

import javafx.scene.control.Alert;

/**
 *
 * @author Joey
 * @important Use "Platform.runLater()" to execute code on the JavaFX Application Thread.
 */
public class JavaFxException extends Exception{
    
    public enum AlertType {
        INFORMATION,
        ERROR
    }
    
    /**
     * Creates a new instance of <code>JavaFxException</code> without detail
     * message.
     */
    public JavaFxException() {
    }

    /**
     * Constructs an instance of <code>JavaFxException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public JavaFxException(String msg) {
        super(msg);
        showErrorDialog(msg);
    }
    
    public JavaFxException(String msg, AlertType alert) {
        super(msg);
        switch (alert){
            case INFORMATION:
                showInfoDialog(msg);
                break;
            case ERROR:
                showErrorDialog(msg);
                break;
            default:
                System.out.println("Incorrect Alert.");
        }
    }
    
    // Dialog Boxes 
    public void showInfoDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
