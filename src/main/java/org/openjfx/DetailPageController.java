package org.openjfx;

import javafx.fxml.FXML;

public class DetailPageController {

    @FXML
    protected void initialize(){
        System.out.println(Data.getDataString());
    }
}
