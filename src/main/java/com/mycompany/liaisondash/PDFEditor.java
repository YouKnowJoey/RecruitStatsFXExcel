/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.liaisondash;

/**
 *
 * @author Joey
 */
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.text.PDFTextStripper;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.embed.swing.SwingFXUtils;

import java.awt.image.BufferedImage;


public class PDFEditor{

    // PDF file Path (relative to project)
    private static final String PDF_PATH = "src/main/resources/com/mycompany/liaisondash/RangerVolunteerStatement_SSGGarcia.pdf";
    private PDDocument document;
    private String pdfText;

    public PDFEditor() {
        initiatePDF();
    }

    private void initiatePDF(){
        try {
            document = Loader.loadPDF(new File(PDF_PATH));
            // Create PDFTextStripper object
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // Extract text from PDF
            pdfText = pdfStripper.getText(document);

        } catch (IOException e) {
            System.err.println("Error while processing PDF: " + e.getMessage());
        }
    }

    public void testTextPDF() {
        // Display extracted text
        System.out.println("Text extracted from PDF:\n");
        System.out.println(pdfText);
    }

    public VBox previewPDF() {
        VBox vbox = new VBox();
        try {
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
                ImageView imageView = new ImageView(fxImage);
                vbox.getChildren().add(imageView);
            }
        } catch (IOException e) {
            System.err.println("Error while rendering PDF for preview: " + e.getMessage());
        }
        return vbox;
    }

    //method --- public signPDF(){use CaC authentication certifcates}
    
    // method --- public savePDF(outputStream) to different location

    // method --- public addObjects to PDF
}
