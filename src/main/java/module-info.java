module wgu.softwareproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens wgu.softwareproject to javafx.fxml;
    exports wgu.softwareproject;
}