module travel.travelagency {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.logging.log4j;
    requires hibernate.jpa;

  opens travel.travelagency to javafx.fxml;
    exports travel.travelagency;
    exports travel.travelagency.controllers;
    opens travel.travelagency.controllers to javafx.fxml;
}