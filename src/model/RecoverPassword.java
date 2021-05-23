package model;

import controller.Dialog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecoverPassword {
    DataBaseConnection dataBaseConnection = new DataBaseConnection();
    Connection connection = dataBaseConnection.getConnection();

    String[] userdata = new String[2];

    public int checkUser(Users users) {
        String sql = "SELECT userName, email, phone FROM user WHERE userName =?";
        if (connection == null) return 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, users.getUserName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userdata[0] = resultSet.getString(1);
                userdata[1] = resultSet.getString(2);
                return 1;
            }
            return -1;
        } catch (SQLException e) {
            Dialog.error("Error de consulta MySQL", "Ocurrió un error al extraer el correo de la base de datos", e.getMessage(), Dialog.SQL());
            return 0;
        }
    }

    public String[] getUserdata() {
        return userdata;
    }
}
