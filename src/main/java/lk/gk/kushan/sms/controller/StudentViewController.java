package lk.gk.kushan.sms.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.gk.kushan.sms.db.DBConnection;
import lk.gk.kushan.sms.model.Student;
import lk.gk.kushan.sms.model.util.Gender;

import java.sql.*;
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


        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblStudents.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudents.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblStudents.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        loadAllStudents();


        Platform.runLater(() -> {

            root.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_ANY), () -> btnNewStudent.fire());
            root.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY), () -> btnSaveStudent.fire());
            root.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.F1), () -> tblStudents.requestFocus());
        });

        tblStudents.getSelectionModel().selectedItemProperty().addListener((ov,previous,current)->{
            btnDeleteStudent.setDisable(current==null);
            if(current == null) return;

            txtId.setText(current.getId() + "");
            txtFirstName.setText(current.getFirstName());
            txtLastName.setText(current.getLastName());
            txtAddress.setText(current.getAddress());
            tglGender.selectToggle(current.getGender() == Gender.MALE ? rdMale : rdFemale);
            txtDOB.setValue(current.getDateOfBirth());

        });


    }

    private void loadAllStudents() {
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Student");
            ObservableList<Student> studentList = tblStudents.getItems();


            while (rst.next()) {
                int id = rst.getInt("id");
                String firstName = rst.getString("first_name");
                String lastName = rst.getString("last_name");
                String address = rst.getString("address");
                //Gender gender=rst.getString("gender").equals("MALE")? Gender.MALE: Gender.FEMALE;
                Gender gender=Gender.valueOf(rst.getString("gender"));
                Date dob = rst.getDate("dob");

                studentList.add(new Student(id, firstName, lastName, address, gender, dob.toLocalDate()));

            }


            studentList.forEach((Student student) -> System.out.println(student));
            studentList.forEach(System.out::println);// converting to the method referencing

            Platform.runLater(() -> btnNewStudent.fire());
            Platform.runLater(btnNewStudent::fire);

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to load student details,try again").showAndWait();
            Platform.exit();
        }
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
