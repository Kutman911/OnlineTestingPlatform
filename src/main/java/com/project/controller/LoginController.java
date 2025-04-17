package com.project.controller;

import com.project.model.User;
import com.project.service.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final AuthService authService = new AuthService();

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = authService.login(username, password);

        if (user != null) {
            try {
                redirectToDashboard(user);
            } catch (IOException e) {
                errorLabel.setText("Error loading dashboard");
                e.printStackTrace();
            }
        } else {
            errorLabel.setText("Invalid username or password");
        }
    }

    private void redirectToDashboard(User user) throws IOException {
        String fxmlFile;
        switch (user.getRole()) {
            case "ADMIN":
                fxmlFile = "/views/admin_dashboard.fxml";
                break;
            case "TEACHER":
                fxmlFile = "/views/teacher_dashboard.fxml";
                break;
            case "STUDENT":
                fxmlFile = "/views/student_dashboard.fxml";
                break;
            case "COURSE_MANAGER":
                fxmlFile = "/views/manager_dashboard.fxml";
                break;
            default:
                throw new IllegalArgumentException("Unknown user role: " + user.getRole());
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        // Pass the user to the dashboard controller if needed
        Object controller = loader.getController();
        if (controller instanceof UserAwareController) {
            ((UserAwareController) controller).setUser(user);
        }

        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}

