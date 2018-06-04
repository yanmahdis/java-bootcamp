package model;

import java.sql.*;
import java.time.Instant;

public class Database {
    protected Connection con;

    public void createConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = this.getConnection();
            System.out.println("Database Connection Success");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class Not Found Exception :" + ex);
        } catch (SQLException ex) {
            System.out.println("SQL Exception:" + ex);
        }
    }

    public void printUser() {
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT a.name, b.total FROM USERS a LEFT JOIN DEBTS b ON a.id = b.userId");
            while(rs.next()) {
                String name = rs.getString("name");
                String total = rs.getString("total");
                System.out.println(name+" "+total);
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("SQL Exception:" + ex);
        }
    }

    public void insertUser(String id, String name) throws SQLException{
        Statement stmt = null;
        try {
            stmt = this.con.createStatement();
            String insertQuery = String.format("INSERT INTO USERS (ID,NAME, MODIFIEDDATE) VALUES('%s','%s', %d)", id, name, this.getEpochNow());
            stmt.execute(insertQuery);
        } catch (SQLException ex) {
            System.out.println("SQL Exception:" + ex);
            throw ex;
        } finally {
            stmt.close();
        }
    }

    public void updateUser(String id, String name) throws SQLException {
        PreparedStatement updateStatement = null;
        String updateQuery = "UPDATE USERS SET NAME = ?, MODIFIEDDATE = ? WHERE ID = ?";

        try {
            updateStatement = this.con.prepareStatement(updateQuery);
            updateStatement.setString(1, name);
            updateStatement.setLong(2, this.getEpochNow());
            updateStatement.setString(3, id);
            updateStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Exception:" + ex);
        } finally {
            updateStatement.close();
        }
    }

    public void deleteUser(String id) throws SQLException {
        PreparedStatement deleteStatement = null;
        String deleteQuery = "DELETE FROM USERS WHERE id = ?";

        try {
            deleteStatement = this.con.prepareStatement(deleteQuery);
            deleteStatement.setString(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Exception:" + ex);
        } finally {
            deleteStatement.close();
        }
    }

    public void addDebts(String userId, float total) throws SQLException {
        this.con.setAutoCommit(false);
        PreparedStatement updateStatement = null;
        PreparedStatement insertStatement = null;
        String insertQuery = "INSERT INTO DEBTS (TOTAL, USERID) VALUES (?, ?)";
        String updateQuery = "UPDATE USERS SET MODIFIEDDATE = ? WHERE ID = ?";

        try {
            insertStatement = this.con.prepareStatement(insertQuery);
            updateStatement = this.con.prepareStatement(updateQuery);
            insertStatement.setFloat(1, total);
            insertStatement.setString(2, userId);
            updateStatement.setLong(1, this.getEpochNow());
            updateStatement.setString(2, userId);
            insertStatement.executeUpdate();
            updateStatement.executeUpdate();
            this.con.commit();
        } catch (SQLException ex) {
            if (this.con != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                } catch(SQLException excep) {
                    System.err.print(excep);
                }
            }
        } finally {
            if (insertStatement != null) {
                insertStatement.close();
            }
            if (updateStatement != null) {
                updateStatement.close();
            }
            this.con.setAutoCommit(true);
        }
    }

    private Long getEpochNow() {
        return Instant.now().getEpochSecond();
    }

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb?useSSL=false","user","password");
    }
}
