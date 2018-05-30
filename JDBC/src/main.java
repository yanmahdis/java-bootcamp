import model.Database;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class main {
    public static void main(String[] args) {
        try {
            Database db = new Database();
            db.createConnection();
            String newName = String.format("NewTest-%s", new Timestamp(System.currentTimeMillis()).toString());
            String id = UUID.randomUUID().toString();
            db.insertUser(id, newName);
            System.out.println("After Insert");
            db.printUser();
            String updatedName = String.format("UpdatedTest-%s", new Timestamp(System.currentTimeMillis()).toString());
            db.updateUser("1a1a3694-4b7c-4e18-ab7b-cc4d17091074", updatedName);
            System.out.println("After update");
            db.printUser();
            db.addDebts("1a1a3694-4b7c-4e18-ab7b-cc4d17091074", 100);
            System.out.println("After add debts");
            db.printUser();
            db.deleteUser(id);
            System.out.println("After Delete");
            db.printUser();

        } catch (SQLException ex) {
            System.out.println();
        }
    }
}
