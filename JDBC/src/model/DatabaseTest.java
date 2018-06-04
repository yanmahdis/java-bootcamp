package model;

import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DatabaseTest {
    Database db = null;
    Connection mockCon = null;
    Statement mockStatement = null;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws SQLException{
        this.mockCon = mock(Connection.class);
        this.mockStatement = mock(Statement.class);
        this.db = spy(new Database());
        when(this.db.getConnection()).thenReturn(this.mockCon);
        this.db.createConnection();
        when(this.mockCon.createStatement()).thenReturn(this.mockStatement);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void insertUserSuccessfully() throws SQLException {
        db.insertUser("a","b");
        verify(this.mockStatement, times(1)).execute(any(String.class));
        verify(this.mockStatement, times(1)).close();
    }

    @org.junit.jupiter.api.Test
    void insertUserFailed() throws SQLException {
        try {
            when(this.mockStatement.execute(any(String.class))).thenThrow(new SQLException("Error"));
            db.insertUser("a","b");
            fail("this test case should fail");
        } catch (SQLException ex) {
            verify(this.mockStatement, times(1)).execute(any(String.class));
            verify(this.mockStatement, times(1)).close();
        }
    }

    @org.junit.jupiter.api.Test
    void updateUser() {
    }

    @org.junit.jupiter.api.Test
    void deleteUser() {
    }

    @org.junit.jupiter.api.Test
    void addDebts() {
    }
}