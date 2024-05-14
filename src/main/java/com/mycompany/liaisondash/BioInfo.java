package com.mycompany.liaisondash;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import java.time.LocalDate;
import java.time.Period;
import java.text.SimpleDateFormat;


public class BioInfo {

    // Enumeration for biographical information types
    public enum InformationType {
        FIRSTNAME,
        LASTNAME,
        AGE,
        BIRTHDATE,
        HOMESTATE,
        GENDER,
        ETHNICITY
    }

    public enum Gender {
        MALE,
        FEMALE
    }

    // Instance variables to store biographical information
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String homeState;
    private String gender;
    private String ethnicity;

    // Create a HashMap to store ethnicities
    private static final Map<String, String> ETHNICITY_MAP = new HashMap<>();
    // Static block to initialize the ethnicity map
    static {
        ETHNICITY_MAP.put("A", "Asian / Pacific Islander");
        ETHNICITY_MAP.put("B", "Black");
        ETHNICITY_MAP.put("W", "White / Caucasian");
        ETHNICITY_MAP.put("H", "Hispanic");
        ETHNICITY_MAP.put("N", "Native American / Alaska Native");
        ETHNICITY_MAP.put("O", "Other");
    }

    // Constructors
    public BioInfo(String firstName, String lastName, LocalDate birthDate, String homeState, String gender, String ethnicity) {
        if (firstName.stripLeading().stripTrailing() == "" || lastName.stripLeading().stripTrailing() == ""){
            throw new IllegalArgumentException("Invalid Name Entry.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.homeState = homeState;
        this.gender = gender;
        setEthnicity(ethnicity);
        checkAge();
    }

    // Method to get biographical information based on type
    // Add more cases as needed
    public String getInformation(InformationType type) {
        switch (type) {
            case FIRSTNAME:
                return this.firstName;
            case LASTNAME:
                return this.lastName;
            case AGE:
                return Integer.toString(getAge());
            case BIRTHDATE:
                return getBirthDate();
            case HOMESTATE:
                return this.homeState;
            case GENDER:
                return this.gender; // Returns string of the enum constant
            case ETHNICITY:
                return this.ethnicity;
            default:
                return "Unknown Type.";
        }
    }

    //Getter Methods
    private int getAge(){
        LocalDate currentDate = LocalDate.now();
        // Calculate the period between the birthdate and current date
        Period period = Period.between(this.birthDate, currentDate);
        // Extract the years from the period
        return period.getYears();
    }
    
    public String getBirthDate(){
        // Create a SimpleDateFormat with the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        String formattedDate = dateFormat.format(this.birthDate);
        return formattedDate;
    }

    public static Map<String, String> getEthnicityMap(){
        return ETHNICITY_MAP;
    }

    
    public static List<Gender> getAllGendersList() {
        return Arrays.asList(Gender.values());
    }
    
        // Method to get all gender names as a list of strings
    public static List<String> getAllGenderNames() {
        return Arrays.stream(Gender.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
    
    
    // Validate and set ethnicity
    private void setEthnicity(String ethnicityCode) {
        if (ETHNICITY_MAP.containsKey(ethnicityCode)) {
            this.ethnicity = ETHNICITY_MAP.get(ethnicityCode);
        } else {
            throw new IllegalArgumentException("Invalid ethnicity code: " + ethnicityCode);
        }
    }

    

    // Check and validate programmed ethnicities 
    // --- Needs update when testing Excels ---
    public void checkEthnicity(String ethnicityCode){
        if (!ETHNICITY_MAP.containsKey(ethnicityCode)){
            throw new IllegalArgumentException("Invalid ethnicity code: " + ethnicityCode);
        }
    }

    // Checks for correct age range --- Review for Accuracy
    private void checkAge() {
        if (getAge() < 18 || getAge() > 65) {
            throw new IllegalArgumentException("Incorrect age input.");
        }
    }

}
