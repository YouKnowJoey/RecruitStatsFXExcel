/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.liaisondash;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Joey
 */
public class PDFController {

    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private VBox pdfPreviewContainer;
    
    private double scale = 1.0;
    
    private void importedData(){
        //import the data from the new excel row
    }
    
    public void setPDFPreview(VBox pdfPreview) {
        pdfPreviewContainer.getChildren().setAll(pdfPreview.getChildren());
        double initialScale = .50;
        pdfPreviewContainer.setScaleX(initialScale);
        pdfPreviewContainer.setScaleY(initialScale);
        centerScrollPane();
    }
    
    @FXML
    public void zoomOut(ActionEvent event) {
        scale -= 0.25; // Decrease the scale factor
        if (scale < 0.25) {
            scale = 0.25; // Prevent scale from becoming too small
        }
        pdfPreviewContainer.setScaleX(scale);
        pdfPreviewContainer.setScaleY(scale);
        
        // Adjust the viewport size of the ScrollPane to match the new scale
        scrollPane.setPrefViewportWidth(pdfPreviewContainer.getBoundsInParent().getWidth());
        scrollPane.setPrefViewportHeight(pdfPreviewContainer.getBoundsInParent().getHeight());
        centerScrollPane();
    }

    @FXML
    public void zoomIn(ActionEvent event) {
        scale += 0.25; // Increase the scale factor
        pdfPreviewContainer.setScaleX(scale);
        pdfPreviewContainer.setScaleY(scale);

        // Adjust the viewport size of the ScrollPane to match the new scale
        scrollPane.setPrefViewportWidth(pdfPreviewContainer.getBoundsInParent().getWidth());
        scrollPane.setPrefViewportHeight(pdfPreviewContainer.getBoundsInParent().getHeight());
        centerScrollPane();
    }
    
    private void centerScrollPane() {
        // Center the ScrollPane view
        double contentWidth = pdfPreviewContainer.getBoundsInParent().getWidth();
        double contentHeight = pdfPreviewContainer.getBoundsInParent().getHeight();
        double viewportWidth = scrollPane.getViewportBounds().getWidth();
        double viewportHeight = scrollPane.getViewportBounds().getHeight();

        // Calculate center position
        double hValue = (contentWidth - viewportWidth) / 2 / contentWidth;
        double vValue = (contentHeight - viewportHeight) / 2 / contentHeight;

        // Set the scroll values
        scrollPane.setHvalue(hValue);
        scrollPane.setVvalue(vValue);
    }
}
