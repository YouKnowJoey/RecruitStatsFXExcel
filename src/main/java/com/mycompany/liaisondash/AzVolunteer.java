package com.mycompany.liaisondash;

import java.util.*;
import java.util.List;

public class AzVolunteer extends Volunteer {

    private final List<String> MOS_INTEL_ARR;

    public AzVolunteer() {
        this.MOS_INTEL_ARR = List.of("35F", "35G", "35M", "35L", "35P", "35S", "35N", "15W", "15E");
    }

    public AzVolunteer(BioInfo personInfo, String mos, String dodId, String ssn, String securityClearance) {
        super(personInfo, mos, dodId, ssn, securityClearance);
        this.MOS_INTEL_ARR = List.of("35F", "35G", "35M", "35L", "35P", "35S", "35N", "15W", "15E");

        try {
            checkMos(mos);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    private void checkMos(String mosString) throws IllegalArgumentException {
        if (!MOS_INTEL_ARR.contains(mosString)) {
            throw new IllegalArgumentException("Invalid MOS chosen: " + mosString);
        }
    }

    @Override
    public List<String> getMosArray() {
        return new ArrayList<>(MOS_INTEL_ARR);
    }
    
    @Override
    public String getLiaisonLocation(){
        return "FTHAZ"; //Location Code
    }
    
    @Override
    public String getAitUnit(){
        String mos = this.getMos();
        switch (mos) {
            case "35F":
                return "305";
            case "35G":
            case "35L":
            case "35M":
                return "309";
            case "15W":
            case "15E":
                return "2-13";
            case "35P":
            case "35S":
            case "35N":
                return "344";

            default:
                return "Unknown AIT Unit";
        }    
    }
}
