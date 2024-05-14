package com.mycompany.liaisondash;

import java.util.*;
import java.util.List;
import java.time.LocalDate;

public abstract class Volunteer {

    private final List<String> STATE_NAMES = List.of(
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida",
            "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
            "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
            "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania",
            "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
            "West Virginia", "Wisconsin", "Wyoming"
    );

    private final String[] CLEARANCE = {"TS", "S", "PENDING"};

    private final String[] VOLUNTEER_TYPE = {"RGV4", "UNRG"}; // "Option 40" and "Volunteer Statement" Respectively.

    //Variables
    private BioInfo personInfo;
    private String mos; //Military Occupation
    private String dodId; // MIL DOD ID
    private String ssn; //social security number
    private String employeeId; // "IPPS-A EID"
    private String securityClearance; //Date Service member signed
    private String classNumber; //subject to change
    private LocalDate graduationDate; //subject to change

    private LocalDate shipDate; // User defines when Person ships 

    //Constructor
    protected Volunteer() {
    }

    protected Volunteer(BioInfo personInfo, String mos, String dodId, String ssn, String securityClearance) {
        this.personInfo = personInfo;
        this.mos = mos;
        setDodId(dodId);
        setSsn(ssn);
        setSecurityClearance(securityClearance);
    }

    protected Volunteer(BioInfo personInfo, String mos, String dodId, String ssn, String securityClearance, String employeeId, String classNumber, LocalDate graduationDate) {
        this.personInfo = personInfo;
        this.mos = mos;
        setDodId(dodId);
        setSsn(ssn);
        setSecurityClearance(securityClearance);
        // Added to constructor
        setEmpoyeeId(employeeId);
        this.classNumber = classNumber;
        this.graduationDate = graduationDate;
    }

    //Getter Methods
    public BioInfo getPersonInfo() {
        return personInfo;
    }

    public String getMos() {
        return mos;
    }

    public String getDodId() {
        return dodId;
    }

    public String getSsn() {
        return ssn;
    }

    public String getSecurityClearance() {
        return securityClearance;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    public List<String> getStates() {
        return new ArrayList<>(STATE_NAMES);
    }

    public String[] getClearances() {
        return CLEARANCE;
    }

    public String[] getVolunteerType() {
        return VOLUNTEER_TYPE;
    }

    //Setter Methods
    private void setSsn(String ssn) {
        int length = ssn.length();
        if (length == 9) {
            this.ssn = ssn;
        } else {
            throw new IllegalArgumentException("Unacceptable SSN inputted: " + ssn);
        }
    }

    private void setDodId(String dodId) {
        int length = dodId.length();
        if (length == 10) {
            this.dodId = dodId;
        } else {
            throw new IllegalArgumentException("Unacceptable DODId inputted: " + dodId);
        }
    }

    private void setSecurityClearance(String securityClearance) {
        for (String clearance : CLEARANCE) {
            if (clearance == securityClearance.strip()) {
                this.securityClearance = securityClearance;
            }
        }
    }

    public void setShipDate(LocalDate shipDate) {
        this.shipDate = shipDate;
    }

    public void setEmpoyeeId(String employeeId) {
        int length = employeeId.length();
        if (length == 0) {
            this.employeeId = employeeId;
        } else {
            throw new IllegalArgumentException("Unacceptable IPPS-A Employee ID inputted: " + employeeId);
        }
    }

    // Abstract Methods - Based on location & Liaison
    public abstract String getLiaisonLocation();

    public abstract String getAitUnit();

    public abstract List<String> getMosArray();

}
