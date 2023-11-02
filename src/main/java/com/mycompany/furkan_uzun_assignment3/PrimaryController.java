package com.mycompany.furkan_uzun_assignment3;

import static com.mycompany.furkan_uzun_assignment3.App.main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.application.Platform;
import static javafx.beans.binding.Bindings.length;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

//interface regexFormats {
//
//    String regexPhone = "\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d";
//    String regexNames = "\\b[A-Z][a-zA-Z]+";
//    String regexEmail = "[a-z0-9]+@[a-z0-9]+.[0-z]{2,4}";
//    String regexSalary = "[0-9]+.[0-9]{0,2}";
//
//}
public class PrimaryController implements Regex {

    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, String> emailColumn;
    @FXML
    private TableColumn<Employee, String> phoneColumn;
    @FXML
    private TableColumn<Employee, Double> salaryColumn;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField salary;
    @FXML
    private TextField statusTF;

    private final ObservableList<Employee> data
            = FXCollections.observableArrayList();

    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tableView.setItems(data);
    }

    @FXML
    private void openMenuItemHandler(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        String[] arr;
        if (selectedFile != null) {
            File file = selectedFile;
            Scanner infile = new Scanner(file);
            String line;
            while (infile.hasNext()) {

                line = infile.nextLine();
                arr = line.split(",");
                data.add(new Employee(arr[0], arr[1], arr[2], (arr[3]), Double.valueOf(arr[4])));
            }
        }
        statusTF.clear();
        statusTF.appendText("reading data from " + selectedFile);

    }

    @FXML
    private void saveMenuItemHandler(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showSaveDialog(null);
        PrintStream outfile = null;
        outfile = new PrintStream(selectedFile);
        for (int i = 0; i < data.size(); i++) {
            outfile.println(data.get(i).getFirstName() + " " + data.get(i).getLastName() + " "
                    + data.get(i).getEmail() + " " + data.get(i).getPhone() + " " + data.get(i).getSalary());
        }
        statusTF.clear();
        statusTF.appendText("writing to File " + selectedFile.toString());
    }

    @FXML
    private void closeMenuItemHandler(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void addButton(ActionEvent event) {
        String userFirstName = firstName.getText().toString();
        String userLastName = lastName.getText().toString();
        String userEmail = email.getText().toString();
        String userPhoneString = phone.getText().toString();
        String userSalaryString = "";
        double userSalaryDouble = 0;

        if (!salary.getText().toString().isEmpty() && salary.getText().toString().matches(regexSalary)) {
            userSalaryString = salary.getText().toString();
            userSalaryDouble = Double.parseDouble(salary.getText());
        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("INVALID INPUT");
//            String errM = "No Salary was entered";
//            alert.setContentText(errM);
//            alert.showAndWait();
//            
        }

        //double userSalaryDouble = Double.parseDouble(salary.getText());
        if (userFirstName.matches(regexNames) && userLastName.matches(regexNames)
                && userEmail.matches(regexEmail) && userPhoneString.matches(regexPhone)
                && !salary.getText().isEmpty() && userSalaryString.matches(regexSalary)) {

            data.add(new Employee(userFirstName, userLastName, userEmail, userPhoneString, userSalaryDouble));

            statusTF.clear();
            statusTF.appendText("A new employee has been added ");
            firstName.clear();
            lastName.clear();
            email.clear();
            phone.clear();
            salary.clear();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("INVALID INPUT");
            ArrayList<String> arry = new ArrayList<>();

            if (userFirstName.matches(regexNames) == false && userFirstName.isBlank() == false) {
                arry.add("Invalid first name");
                firstName.clear();
            }
            if (userLastName.matches(regexNames) == false && userLastName.isBlank() == false) {
                arry.add("Invalid last name");
                lastName.clear();
            }
            if (userEmail.matches(regexEmail) == false && userEmail.isBlank() == false) {
                arry.add("Invalid email");
                email.clear();
            }
            if (userPhoneString.matches(regexPhone) == false && userPhoneString.isBlank() == false) {
                arry.add("Invalid Phone");
                phone.clear();
            }

            if (userSalaryString.matches(regexSalary) == false && !salary.getText().isEmpty()) {
                arry.add("Invalid Salary");
                salary.clear();

            }
            if (userFirstName.isBlank()) {
                arry.add("No first name was entered");
            }
            if (userLastName.isBlank()) {
                arry.add("No last name was entered");
            }
            if (userEmail.isBlank()) {
                arry.add("No email was entered");
            }
            if (userPhoneString.isBlank()) {
                arry.add("No phone was entered");
            }
            if (salary.getText().isEmpty()) {
                arry.add("No Salary was entered");
            }

            StringBuffer strBfr = new StringBuffer();

            Iterator<String> iterator1 = arry.iterator();

            while (iterator1.hasNext()) {
                strBfr.append(iterator1.next() + "\n");
            }

            alert.setContentText(strBfr.toString());
            alert.showAndWait();

        }
    }

    @FXML
    private void deleteButton(ActionEvent event) {
        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());
        statusTF.clear();
        statusTF.appendText("An employees has been deleted ");

    }

    @FXML
    private void clearAllButton(ActionEvent event) {
        tableView.getItems().clear();
        statusTF.clear();
        statusTF.appendText("All employee has been deleted ");

    }

    @FXML
    private void removeDuplicatesButton(ActionEvent event) {
        Set<Employee> set = new HashSet<Employee>(data);
        ObservableList<Employee> deduplicatedList = FXCollections.observableArrayList(set);
        data.clear();
        data.addAll(deduplicatedList);
        statusTF.clear();
        statusTF.appendText("Duplicates has been delted!");

    }

    @FXML
    private void about(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Assignment 4 by Furkan Uzun");
        alert.setContentText("I certify this submission is my original work.");
        alert.showAndWait();
    }

    @FXML
    private void sortByEmail(ActionEvent event) {
        data.sort((Employee e1, Employee e2) -> e1.getEmail().compareTo(e2.getEmail()));
        statusTF.clear();
        statusTF.appendText("Data sorted by Email");
        if (!firstNameColumn.hasProperties()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No Employee");
            alert.setContentText("There is no employees to sort!! \n Please add an employee first!!");
            alert.showAndWait();

        }

    }

    @FXML
    private void sortByFirstName(ActionEvent event) {
        data.sort((Employee e1, Employee e2) -> e1.getFirstName().compareTo(e2.getFirstName()));
        statusTF.clear();
        statusTF.appendText("Data sorted by First Name");
        if (!firstNameColumn.hasProperties()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No Employee");
            alert.setContentText("There is no employees to sort!! \n Please add an employee first!!");
            alert.showAndWait();

        }

    }

    @FXML
    private void sortByLastName(ActionEvent event) {
        data.sort((Employee e1, Employee e2) -> e1.getLastName().compareTo(e2.getLastName()));
        statusTF.clear();
        statusTF.appendText("Data sorted by Last Name");
        if (!firstNameColumn.hasProperties()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No Employee");
            alert.setContentText("There is no employees to sort!! \n Please add an employee first!!");
            alert.showAndWait();

        }

    }

    @FXML
    private void sortByPhone(ActionEvent event) {
        data.sort((Employee e1, Employee e2) -> String.valueOf(e1.getPhone()).compareTo(String.valueOf(e2.getPhone())));
        statusTF.clear();
        statusTF.appendText("Data sorted by Phone");
        if (!firstNameColumn.hasProperties()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No Employee");
            alert.setContentText("There is no employees to sort!! \n Please add an employee first!!");
            alert.showAndWait();

        }

    }

    @FXML
    private void sortBySalary(ActionEvent event) {
        data.sort((Employee e1, Employee e2) -> e1.getSalary().compareTo(e2.getSalary()));
        //Collections.sort(data, Collections.reverseOrder());
        Collections.reverse(data);
        statusTF.clear();
        statusTF.appendText("Data sorted by Salary");
        if (!firstNameColumn.hasProperties()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No Employee");
            alert.setContentText("There is no employees to sort!! \n Please add an employee first!!");
            alert.showAndWait();

        }
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (obj instanceof Employee) {
            Employee temp = (Employee) obj;
            if (this.firstName.equals(temp.getFirstName()) && this.lastName.equals(temp.getLastName()) && this.email.equals(temp.getEmail()) && this.phone.equals(temp.getPhone())
                    && this.salary.equals(temp.getSalary())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub

        return (this.firstName.hashCode() + this.lastName.hashCode() + this.email.hashCode() + this.phone.hashCode() + this.salary.hashCode());
    }

}
