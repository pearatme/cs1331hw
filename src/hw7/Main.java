package hw7;

import hw5.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    Stage privilegeStage;
    Pane labelPane, studentListPane, menuPane;
    Label currentStudentCount, maxStudentCount;
    TextField nameField;
    Button addStudent, dequeueStudent;
    ListView<String> studentList;
    volatile Boolean authenticated;
    int maxNumberOfStudents;

    public void buildStudentListPane() {
        studentList = new ListView<>();
        studentList.getItems().addAll("Angela", "Sung", "Angela (again)");
        studentList.setPrefWidth(400);
        studentListPane = new HBox(studentList);
    }

    public void buildLabelPane() {
        currentStudentCount = new Label("");
        maxStudentCount = new Label("");
        labelPane = new FlowPane(currentStudentCount, maxStudentCount);
        updateLabelPane();
    }

    public void updateLabelPane() {
        currentStudentCount.setText(String.format("Current Number Of Students In Queue: %d", studentList.getItems().size()));
        if (studentList.getItems().size() > maxNumberOfStudents)
            maxNumberOfStudents = studentList.getItems().size();
        maxStudentCount.setText(String.format("Max Number Of Students In Queue: %d", maxNumberOfStudents));
    }

    public void openAuthentication() {
        authenticated = null;
        privilegeStage.show();
    }

    public void closeAuthentication() {
        privilegeStage.hide();
        if(!authenticated)
            return;

        studentList.getItems().remove(0);
        updateLabelPane();
    }

    public void buildMenuPane() {
        nameField = new TextField();
        addStudent = new Button("Add Student") {
            public void fire() {
                super.fire();
                studentList.getItems().add(nameField.getText());
                nameField.setText("");
                updateLabelPane();
            }
        };
        dequeueStudent = new Button("DequeueStudent") {
            public void fire() {
                super.fire();
                openAuthentication();
            }
        };
        menuPane = new HBox(nameField, addStudent, dequeueStudent);
    }

    public void buildPrivilegeStage() {
        Label confirmationLabel = new Label("Confirmation");
        confirmationLabel.setFont(Font.font(14));
        AnchorPane.setLeftAnchor(confirmationLabel, 10.);
        AnchorPane confirmationPane = new AnchorPane();
        confirmationPane.getChildren().addAll(confirmationLabel);
        confirmationPane.setPrefHeight(50);

        Label passwordLabel = new Label("Please Enter Password");
        PasswordField passwordField = new PasswordField();
        HBox passwordPane = new HBox(passwordLabel, passwordField);
        passwordPane.setPrefHeight(30);

        Button cancelButton = new Button("Cancel") {
            public void fire() {
                super.fire();
                authenticated = false;
                closeAuthentication();
            }
        };
        Button okButton = new Button("ok") {
            @Override
            public void fire() {
                super.fire();
                authenticated = passwordField.getText().equals("CS1321R0X");
                closeAuthentication();
            }
        };
        HBox buttonPane = new HBox(cancelButton, okButton);
        buttonPane.setPrefHeight(20);

        privilegeStage = new Stage() {
            public void close() {
                super.close();
                authenticated = false;
                closeAuthentication();
            }
        };
        privilegeStage.setTitle("Privilege Check");
        VBox privilegePane = new VBox(confirmationPane, passwordPane, buttonPane);
        privilegeStage.setScene(new Scene(privilegePane, 350, 200));
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox parentPane = new VBox();

        buildStudentListPane();
        buildLabelPane();
        buildMenuPane();

        parentPane.getChildren().addAll(labelPane, studentListPane, menuPane);
        Scene mainScene = new Scene(parentPane, 400, 450);

        stage.setTitle("CS 1321 Office Hours Queue");
        stage.setScene(mainScene);
        stage.show();

        buildPrivilegeStage();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
