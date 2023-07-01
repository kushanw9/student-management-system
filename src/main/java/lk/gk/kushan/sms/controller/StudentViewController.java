package lk.gk.kushan.sms.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.gk.kushan.sms.db.DBConnection;
import lk.gk.kushan.sms.model.Student;
import lk.gk.kushan.sms.model.util.Gender;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class StudentViewController {
    
    
    public Button btnDeleteStudent;
    public Button btnNewStudent;
    public Button btnSaveStudent;
    public Label lblAddress;
    public Label lblDOB;
    public Label lblFirstName;
    public Label lblId;
    public Label lblLastName;
    public RadioButton rdFemale;
    public RadioButton rdMale;
    public AnchorPane root;
    public TableView<Student> tblStudents;
    public ToggleGroup tglGender;
    public TextField txtAddress;
    public DatePicker txtDOB;
    public TextField txtFirstName;
    public TextField txtId;
    public TextField txtLastName;
    public TextField txtSearch;

    public void initialize() {
        lblId.setLabelFor(txtId);
        lblFirstName.setLabelFor(txtFirstName);
        lblLastName.setLabelFor(txtLastName);
        lblAddress.setLabelFor(txtAddress);
        lblDOB.setLabelFor(txtDOB);
        btnDeleteStudent.setDisable(true);

        txtSearch.textProperty().addListener((ov,previous,current)->{
            Connection connection = DBConnection.getInstance().getConnection();

            try {
                Statement stm = connection.createStatement();
                String sql = "SELECT * FROM Student WHERE first_name LIKE '%1$s' OR last_name LIKE '%1$s' OR address LIKE '%1$s'";

                sql=String.format(sql,"%"+current+"%");
                ResultSet rst = stm.executeQuery(sql);
                ObservableList<Student> studentList = tblStudents.getItems();
                studentList.clear();

                while (rst.next()) {
                    int id = rst.getInt("id");
                    String firstName = rst.getString("first_name");
                    String lastName = rst.getString("last_name");
                    String address = rst.getString("address");
                    Gender gender = Gender.valueOf(rst.getString("gender"));
                    LocalDate dob = rst.getDate("dob").toLocalDate();

                    studentList.add(new Student(id,firstName,lastName,address,gender,dob));



                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


    }

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
