package org.lendingtree.project;

import java.sql.SQLException;

public abstract class User {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phone;
    protected String password;

    public abstract void register() throws SQLException;
    public abstract User login() throws SQLException;

    public int getId(){
        return this.id;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPhone(){
        return this.phone;
    }

    public String getPassword(){
        return this.password;
    }

    public void setId(int newId){
        this.id = newId;
    }

    public void setFirstName(String newFirstName){
        this.firstName = newFirstName;
    }

    public void setLastName(String newLastName){
        this.lastName = newLastName;
    }

    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    public void setPhone(String newPhone){
        this.phone = newPhone;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }

}