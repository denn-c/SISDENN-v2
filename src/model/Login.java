package model;

import java.sql.*;

public class Login {
    DataBaseConnection dataBaseConnection = new DataBaseConnection();
    Connection connection = dataBaseConnection.getConnection();


    public int checkLogin(Users users) {
        String sql = "SELECT idusuario, idtipo_usuario, nombres_apellidos, correo, usuario, contrasenia FROM usuario where usuario = ?";

        if (connection == null) return 0;
        else {
            try {
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
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
                return -1;
            }

        }
    }
}
