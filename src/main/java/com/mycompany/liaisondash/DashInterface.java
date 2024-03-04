package com.mycompany.liaisondash;


public interface DashInterface {
    
    //add Student to program
    public void addVolunteer();

    //modify existing Student in program
    public void modifyVolunteer();

    //ship Student from program
    public void shipVolunteer();

    //drop Student from program
    public void dropVolunteer();

    //Displays Current active roster
    public void showRoster();


    //future feature- take attendance of active roster
    //public void takeAttendance();

    //future feature- check specific soldier's attendance
    //public void checkAttendance(object soldier);

    //future feature- print attendance of active roster
    //public void printAttendance()
}
