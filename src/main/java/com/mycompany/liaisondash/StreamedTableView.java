package com.mycompany.liaisondash;

/**
 *
 * @author Joey Garcia
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class StreamedTableView {
    
    // Data Singleton - Global access to imported Excel.
    public static final StreamedTableView INSTANCE =  new StreamedTableView();

    //Variables
    private File chosenFile;
    private TableView<String[]> tableView;
    private ObservableList<String[]> data = FXCollections.observableArrayList();
    private List<String> excelHeaders;

    private XSSFWorkbook excelWB = null;
    private XSSFSheet excelSheet = null;

    //Constructor
    public StreamedTableView() {

        // Create an ObservableList
        data = FXCollections.observableArrayList();

        // Create TableView and set items
        tableView = createTableView(data);
    }

    //Table Methods
    
    public static StreamedTableView getInstance(){
        return INSTANCE;
    }
    
    private TableView<String[]> createTableView(ObservableList<String[]> items) {
        this.tableView = new TableView<>();

        tableView.setItems(items);

        return tableView;
    }

    private void addRow(String[] rowData) {
        if (data != null) {
            data.add(rowData);
        } else {
            System.out.println("Data in addRow() is null.");
        }
    }

    private void addRowToTable(String[] rowData) {
        System.out.println(rowData.toString());
        System.out.println(getFile());
        if (this.chosenFile != null && rowData != null) {
            //read already imported excel file
            try (FileInputStream fis = new FileInputStream(this.chosenFile)) {
                excelWB = new XSSFWorkbook(fis);
                excelSheet = excelWB.getSheetAt(0);
                int lastRowNum = excelSheet.getLastRowNum();
                XSSFRow newRow = excelSheet.createRow(lastRowNum + 1);

                for (int i = 0; i < rowData.length; i++) {
                    XSSFCell cell = newRow.createCell(i);
                    cell.setCellValue(rowData[i]);
                }
                
                // Write changes to the Excel file
                try (FileOutputStream fos = new FileOutputStream(this.chosenFile)) {
                    excelWB.write(fos);
                    fos.flush();
                }
                excelWB.close();
                showInfoDialog("Row added to Excel successfully!");
            } catch (IOException e) {
                showErrorDialog("Error writing to Excel file: " + e.getMessage());
            } catch (Exception e) {
                showErrorDialog("Error adding row to Excel: " + e.getMessage());
            }
        } else {
            showErrorDialog("Excel sheet or rowData is null. Unable to add row.");
        }
    }

    public void importExcel(File excelFile) throws IOException {
        INSTANCE.clearData();
        try (FileInputStream fis = new FileInputStream(excelFile)) {
            excelWB = new XSSFWorkbook(fis);
            excelSheet = excelWB.getSheetAt(0);

            // Read headers from the first row of Excel
            XSSFRow headerRow = excelSheet.getRow(0);
            excelHeaders = readHeaders(headerRow);

            for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                XSSFRow excelRow = excelSheet.getRow(row);
                String[] rowData = readRow(excelRow);
                addRow(rowData);
            }

            excelWB.close();
            showInfoDialog("Excel Successfully Imported... !!");
        } catch (IOException e) {
            showErrorDialog("Error processing Excel file: " + e.getMessage());
        } finally{
            setFile(excelFile);
        }
    }

    private List<String> readHeaders(XSSFRow headerRow) {
        List<String> headers = new ArrayList<>();
        for (int col = 0; col < headerRow.getLastCellNum(); col++) {
            XSSFCell cell = headerRow.getCell(col);
            headers.add(cell.toString());
        }
        return headers;
    }

    private String[] readRow(XSSFRow excelRow) {
        String[] rowData = new String[excelHeaders.size()];
        for (int col = 0; col < excelRow.getLastCellNum(); col++) {
            XSSFCell cell = excelRow.getCell(col);
            if(excelRow.getCell(col) != null){
                rowData[col] = cell.toString();
            }
        }
        return rowData;
    }

    public void addVolunteer(Volunteer volunteer, String gtScores, String volunteerType) {
        LocalDate todayDate;
        todayDate = LocalDate.now();

        String employeeId = volunteer.getEmployeeId();
        String dodId = volunteer.getDodId();
        String ssn = volunteer.getSsn();
        String lastName = volunteer.getPersonInfo().getInformation(BioInfo.InformationType.LASTNAME);
        String firstName = volunteer.getPersonInfo().getInformation(BioInfo.InformationType.FIRSTNAME);
        String middleName = "";
        String mos = volunteer.getMos();
        String sex = volunteer.getPersonInfo().getInformation(BioInfo.InformationType.GENDER);
        String gtScore = gtScores;
        String securityClearance = volunteer.getSecurityClearance();
        String citizenship = "USA"; //Only Allowable answer, pop-up exceptions if this not the case.
        String race = volunteer.getPersonInfo().getInformation(BioInfo.InformationType.ETHNICITY);
        String ethnicity = volunteer.getPersonInfo().getInformation(BioInfo.InformationType.ETHNICITY);
        String aitUnit = volunteer.getAitUnit();
        String aitCompany = "";
        String liaisonLocation = volunteer.getLiaisonLocation();
        System.out.println("Almost through Volunteer started.");
        String volunteerDate = convertLocalDate(todayDate);
        String graduationDate = convertLocalDate(volunteer.getGraduationDate());
        String dropDate = "";
        String shipDate = "";
        System.out.println("Almost through Volunteer started.");
        String contractAcquisition = volunteerType; //option 40 or volunteer

        String[] rowData = {employeeId, dodId, ssn, lastName, firstName, middleName, mos,
            sex, gtScore, securityClearance, citizenship, race, ethnicity, aitUnit,
            aitCompany, liaisonLocation, volunteerDate, graduationDate, dropDate,
            shipDate, contractAcquisition};

        addRowToTable(rowData);
    }

    public TableView<String[]> getTableView() {
        return tableView;
    }
    
    public File getFile(){
        return this.chosenFile;
    }

    private void setFile(File file) {
        this.chosenFile = file;
    }

    public void initializeComponents() {
        // Clear existing columns
        tableView.getColumns().clear();

        // Create TableColumn for each header
        for (String header : excelHeaders) {
            TableColumn<String[], String> column = new TableColumn<>(header);
            column.setCellValueFactory(cellData -> {
                int columnIndex = excelHeaders.indexOf(header);
                return new SimpleStringProperty(cellData.getValue()[columnIndex]);
            });
            tableView.getColumns().add(column);
        }
    }
    
    // Method to clear the data
    private void clearData() {
        data.clear(); // Clear the data stored in your data structure
    }

    //Relevant Methods Convert LocalDate DataType
    public String convertLocalDate(LocalDate date) {
        System.out.println(date + " started.");
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String convertedDate = date.format(formatter);
            System.out.println(convertedDate + " complete.");
            return convertedDate;
        } else {
            return "";
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
