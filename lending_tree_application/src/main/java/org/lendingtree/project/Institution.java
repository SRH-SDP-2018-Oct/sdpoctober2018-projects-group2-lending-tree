package org.lendingtree.project;

import java.sql.SQLException;
import java.util.Scanner;

public class Institution {
    private int id;
    private int institutionTypeId;
    private String institutionType;
    private String name;
    private String address;
    private String email;
    private String financialStatus;
    private Double rating;

    protected int getId(){return this.id;}
    protected void setId(int newId){this.id = newId;}
    protected int getInstitutionTypeId(){return institutionTypeId;}
    protected void setInstitutionTypeId(int newInstitutionTypeId){this.institutionTypeId = newInstitutionTypeId;}
    protected String getInstitutionType(){return institutionType;}
    protected void setInstitutionType(String newInstitutionType){this.institutionType = newInstitutionType;}
    protected String getName(){return this.name;}
    protected void setName(String newName){this.name = newName;}
    protected String getAddress(){return this.address;}
    protected void setAddress(String newAddress){this.address = newAddress;}
    protected String getEmail(){return this.email;}
    protected void setEmail(String newEmail){this.email = newEmail;}
    protected String getFinancialStatus(){return this.financialStatus;}
    protected void setFinancialStatus(String newFinancialStatus){this.financialStatus = newFinancialStatus;}
    protected Double getRating(){return this.rating;}
    protected void setRating(Double newRating){this.rating = newRating;}

    private int inputInstitutionTypeId() throws SQLException {
        Scanner userInput = new Scanner(System.in);
        int maxInstitutionTypeValue;
        int institutionTypeId;

        maxInstitutionTypeValue = InstitutionDatabase.listInstitutionTypes();

        do{
            System.out.println("Please select a valid number for institution type: ");
            while(!userInput.hasNextInt()) {
                System.out.println("That is not a valid selection");
                userInput.next();
            }
            institutionTypeId = userInput.nextInt();
        } while(institutionTypeId < 1 || institutionTypeId > maxInstitutionTypeValue);

        return institutionTypeId;
    }

    protected void register() throws SQLException{

        this.institutionTypeId = inputInstitutionTypeId();
        this.institutionType = InstitutionDatabase.getInstitutionType(this.getInstitutionTypeId());
        System.out.println("You selected: " + this.institutionType);
        this.name = InputValidationTools.inputName("Institution Name");
        this.address = InputValidationTools.inputAddress();
        this.email = InputValidationTools.inputEmail();
        this.financialStatus = InputValidationTools.inputFinancialStatus();
        this.rating = InputValidationTools.inputRating();

        InstitutionDatabase.insert(this);
    }
}
