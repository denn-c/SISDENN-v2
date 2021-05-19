package model;

import controller.Dialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class SignIn {

    DataBaseConnection dataBaseConnection = new DataBaseConnection();
    Connection connection = dataBaseConnection.getConnection();

    public ObservableList<String> loadUserType() {

        ObservableList<String> user_type = FXCollections.observableArrayList();
        String sql = "SELECT * FROM user_type";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                user_type.add(resultSet.getString(2));
            }
        } catch (SQLException e) {
            Dialog.error("Error de consulta", "Ocurrió un error al cargar los tipos de usuario de la base de datos", e.getMessage(), Dialog.DATABASE_NOT_CONNECTED());
        }
        return user_type;
    }

    public boolean setUser(Users users) {
        String sql = "INSERT INTO user (idUserType,nameLastName, email, userName, password, phone) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, users.getUserType());
            preparedStatement.setString(2, users.getNameLastName());
            preparedStatement.setString(3, users.getEmail());
            preparedStatement.setString(4, users.getUserName());
            preparedStatement.setString(5, users.getPassword());
            preparedStatement.setInt(6, users.getPhone());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            Dialog.error("Registro fallido","Su solicitud de registro a sido denegado",e.getMessage(),Dialog.ACCESS_GRANTED());
        }
        return false;
    }
}
