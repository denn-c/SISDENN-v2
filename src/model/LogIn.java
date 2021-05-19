package model;

import controller.Dialog;

import java.sql.*;

public class LogIn {
    DataBaseConnection dataBaseConnection = new DataBaseConnection();
    Connection connection = dataBaseConnection.getConnection();


    public int checkLogin(Users users) {
        String sql = "SELECT idUse, idUserType, nameLastName, email, userName, password FROM user where userName = ?";

        if (connection == null) return 0;
        else {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, users.getUserName());
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    if (users.getPassword().equals(resultSet.getString(6))) {
                        users.setId(resultSet.getInt(1));
                        users.setNameLastName(resultSet.getString(3));
                        users.setUserType(resultSet.getInt(2));
                        return 1;
                    }
                    return -1;
                }
                return -1;
            } catch (SQLException e) {
                Dialog.error("Error de consulta","Ocurrió un error al ejecutar la consulta a la base de datos",e.getMessage(),Dialog.DATABASE_NOT_CONNECTED());
                return 0;
            }
        }
    }
}
