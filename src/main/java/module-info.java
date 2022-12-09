module wgu.softwareproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens inv_mgmt_sys to javafx.fxml;
    exports inv_mgmt_sys;
}