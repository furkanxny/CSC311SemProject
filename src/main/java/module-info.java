module com.mycompany.furkan_uzun_assignment3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.furkan_uzun_assignment3 to javafx.fxml;
    exports com.mycompany.furkan_uzun_assignment3;
}
