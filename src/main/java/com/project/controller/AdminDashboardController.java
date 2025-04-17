package com.project.controller;

import com.project.model.User;
import com.project.service.UserService;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminDashboardController implements UserAwareController {

    @FXML
    private TableView<User> usersTable;

    @FXML
    private Label statusLabel;

    private final UserService userService = new UserService();
    private ObservableList<User> users = FXCollections.observableArrayList();

    @Override
    public void setUser(User user) {
        loadUsers();
    }

    private void loadUsers() {
        users.setAll(userService.getAllUsers());
        usersTable.setItems(users);
        initializeTableColumns();
    }

    private void initializeTableColumns() {
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        usersTable.getColumns().setAll(idColumn, usernameColumn, roleColumn);
    }

    @FXML
    private void handleManageUsers() {
        statusLabel.setText("Managing users...");
    }

    @FXML
    private void handleDeleteUser() {
        User selected = usersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            userService.deleteUser(selected.getId());
            loadUsers();
            statusLabel.setText("Deleted user: " + selected.getUsername());
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}