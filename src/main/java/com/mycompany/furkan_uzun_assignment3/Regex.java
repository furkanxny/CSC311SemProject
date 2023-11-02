/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.furkan_uzun_assignment3;

/**
 *
 * @author ozen
 */
public interface Regex {
    
    
    String regexPhone = "\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d";
    String regexNames = "\\b[A-Z][a-zA-Z]+";
    String regexEmail = "[a-z0-9]+@[a-z0-9]+.[0-z]{2,4}";
    String regexSalary = "[0-9]+.[0-9]{0,2}";

    
}
