/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.furkan_uzun_assignment3;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

/**
 *
 * @author ozen
 */
public class Employee {
    
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty email;
    private SimpleStringProperty phone;
    private SimpleDoubleProperty salary;
    
    public Employee(String firstName, String lastName, String email, String phone, double salary) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.salary = new SimpleDoubleProperty(salary);
        
    }
    
    public String getFirstName() {
        return this.firstName.get();
    }
    
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    
    public String getLastName() {
        return this.lastName.get();
    }
    
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    
    public String getEmail() {
        return this.email.get();
    }
    
    public void setEmail(String email) {
        this.email.set(email);
    }
    
    public String getPhone() {
        return this.phone.get();
    }
    
    public void setPhone(String phone) {
        this.phone.set(phone);
    }
    
    
    public Double getSalary() {
        return this.salary.get();
    }
    
    public void setSalary(double salary) {
        this.salary.set(salary);
    }

    
    
}
