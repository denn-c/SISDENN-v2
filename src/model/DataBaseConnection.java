package model;

import controller.Dialog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private Connection connection;
    public Connection getConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/sisdenn?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
        } catch (SQLException e) {
            Dialog.error("¡Error de conexión!","Ocurrió un error al establecer conexión con la base de datos.", e.getMessage(),Dialog.DATABASE_NOT_CONNECTED());
        }
        return connection;
    }
}
