module experia.coffee.experiacoffee {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.base;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;


    //requires eu.hansolo.tilesfx;

    opens experia.coffee.experiacoffee to javafx.fxml;
    exports experia.coffee.experiacoffee;
    exports experia.coffee.experiacoffee.data;
    exports experia.coffee.experiacoffee.model;
    exports experia.coffee.experiacoffee.controller;
    opens experia.coffee.experiacoffee.controller to javafx.fxml;
    exports experia.coffee.experiacoffee.model.SingletonPattern;
    exports experia.coffee.experiacoffee.model.BuilderPattern;
    exports experia.coffee.experiacoffee.model.ObserverPattern;
    exports experia.coffee.experiacoffee.model.StatePattern.OrderStatus;
}