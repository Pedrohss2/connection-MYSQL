package application;

import db.DB;


import java.sql.*;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DB.getConnection();
            preparedStatement = connection.prepareStatement(
                 "INSERT INTO seller "
                 +  "(seller.Name, Email, BirthDate, BaseSalary, DepartmentId) "
                 + "VALUES "
                 + "(?, ?, ?, ?, ?)");

            preparedStatement.setString(1, "Pedro Henrique");
            preparedStatement.setString(2, "pedro@gmail.com");
            preparedStatement.setDate(3, new java.sql.Date(simpleDateFormat.parse("22/04/1985").getTime()));
            preparedStatement.setDouble(4, 2000.0);
            preparedStatement.setInt(5, 1);

            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println("Total rows affected: " + rowsAffected);
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
        catch (ParseException error) {
            error.printStackTrace();
        }
        finally {
            DB.closeStatement(preparedStatement);
            DB.closeConnection();
        }


    }
}
