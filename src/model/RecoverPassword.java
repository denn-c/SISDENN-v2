package model;

import controller.Dialog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecoverPassword {
    DataBaseConnection dataBaseConnection = new DataBaseConnection();
    Connection connection = dataBaseConnection.getConnection();


    public String[] getUserName(Users users) {

        String[] data = new String[2];
        String sql = "SELECT userName, email, phone FROM user WHERE userName =?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, users.getUserName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                data[0] = resultSet.getString(2);
                data[1] = String.valueOf(resultSet.getInt(3));
                return data;
            }
        } catch (SQLException e) {
            Dialog.error("Error de consulta MySQL", "Ocurrió un error al extraer el correo de la base de datos", e.getMessage(), Dialog.SQL());
            return null;
        }
        return null;
    }
}
