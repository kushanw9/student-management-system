package lk.gk.kushan.sms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class StudentViewController {

    @FXML
    private Button btnDeleteStudent;

    @FXML
    private Button btnNewStudent;

    @FXML
    private Button btnSaveStudent;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblDOB;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblId;

    @FXML
    private Label lblLastName;

    @FXML
    private RadioButton rdFemale;

    @FXML
    private RadioButton rdMale;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblStudents;

    @FXML
    private ToggleGroup tglGender;

    @FXML
    private TextField txtAddress;

    @FXML
    private DatePicker txtDOB;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnDeleteStudentOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewStudentOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveStudentOnAction(ActionEvent event) {

    }

    @FXML
    void tblStudentsOnKeyReleased(KeyEvent event) {

    }

}
