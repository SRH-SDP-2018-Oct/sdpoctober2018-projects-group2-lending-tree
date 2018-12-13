package org.lendingtree.project;

import java.sql.SQLException;
import java.util.Scanner;

public class Representative extends User{
    private Institution institution;
    private String institutionDepartment;
    private int institutionDepartmentId;

    protected Institution getInstitution(){return this.institution;}
    protected void setInstitution(Institution newInstitution) {this.institution = newInstitution;}
    protected String getInstitutionDepartment(){return this.institutionDepartment;}
    protected void setInstitutionDepartment(String newInstitutionDepartment){this.institutionDepartment = newInstitutionDepartment;}
    protected int getInstitutionDepartmentId(){return this.institutionDepartmentId;}
    protected void setInstitutionDepartmentId(int newInstitutionDepartmentId){this.institutionDepartmentId = newInstitutionDepartmentId;}

    private int inputInstitutionId() throws SQLException{
        Scanner userInput = new Scanner(System.in);
        int maxInstitutionValue;
        int institutionId;

        maxInstitutionValue = RepresentativeDatabase.listInstitutions() + 1;

        System.out.println(maxInstitutionValue + ". Register new institution");

        do{
            System.out.println("Please select a valid number for institution: ");
            while(!userInput.hasNextInt()) {
                System.out.println("That is not a valid selection");
                userInput.next();
            }
            institutionId = userInput.nextInt();
            if(institutionId == maxInstitutionValue){
                this.institution = new Institution();
                this.institution.register();
            }
        } while(institutionId < 1 || institutionId > maxInstitutionValue);

        return institutionId;
    }

    private int inputInstitutionDepartmentId() throws SQLException{
        Scanner userInput = new Scanner(System.in);
        int maxInstitutionDepartmentValue;
        int institutionDepartmentId;

        maxInstitutionDepartmentValue = RepresentativeDatabase.listInstitutionDepartments();

        do{
            System.out.println("Please select a valid number for the institution department: ");
            while(!userInput.hasNextInt()) {
                System.out.println("That is not a valid selection");
                userInput.next();
            }
            institutionDepartmentId = userInput.nextInt();
        } while(institutionDepartmentId < 1 || institutionDepartmentId > maxInstitutionDepartmentValue);

        return institutionDepartmentId;
    }

    @Override
    public void register() throws SQLException {
        super.register();

        this.institution = InstitutionDatabase.getInstitution(this.inputInstitutionId());
        System.out.println("You selected: " + this.institution.getName());
        this.institutionDepartmentId = this.inputInstitutionDepartmentId();
        this.institutionDepartment = RepresentativeDatabase.getInstitutionDepartment(this.institutionDepartmentId);
        System.out.println("You selected: " + this.institutionDepartment);

        this.setId(RepresentativeDatabase.insert(this));
    }

    @Override
    public User login(){
        Scanner userInput = new Scanner(System.in);
        String message = "Login successful.";
        Representative loginRepresentative = new Representative();
        int remainingAttempts = 3;
        String userInputPassword = new String();

        System.out.println("Please enter the user email: ");
        loginRepresentative.setEmail(userInput.nextLine());
        try {
            loginRepresentative = RepresentativeDatabase.getRepresentative(loginRepresentative.getEmail());
            do{
                if(remainingAttempts < 3)
                    System.out.println("Invalid password, please try again. " + remainingAttempts + " remaining attempts");
                remainingAttempts--;
                System.out.println("Please enter the user password: ");
                userInputPassword = EncryptionTools.encryptPassword(userInput.nextLine());
            }while(remainingAttempts > 0 && userInputPassword.compareTo(loginRepresentative.getPassword())!=0);

        } catch (Exception e) {
            message = "Invalid email address. User does not exist.";
            System.out.println(message);
            return null;
        }

        if(userInputPassword.compareTo(loginRepresentative.getPassword())!=0) {
            message = "Invalid password. No attempts remaining.";
            loginRepresentative = null;
        }

        System.out.println(message);
        return  loginRepresentative;
    }
}
