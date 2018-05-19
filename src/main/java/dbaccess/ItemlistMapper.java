package dbaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemlistMapper {

    InventoryMapper im = new InventoryMapper();

    public void addToItemlist(String name, String desc, int length, int amount, int orderId) {

        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO itemlist (name, description, length, unit, amount, orderid) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name); //user id
            ps.setString(2, desc);
            ps.setInt(3, length);
            ps.setString(4, im.getUnit(name));
            ps.setInt(5, amount);
            ps.setInt(6, orderId);

            System.out.println("Check sql order " + SQL);

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) { //temporary error
            throw new Error(ex.getMessage());
        }
    }

    public List<String> getFullItemlist(int orderId) {

        List<String> itemlist = new ArrayList<>();

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM itemlist where orderid = '" + orderId + "'";

            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet resultset = ps.executeQuery();
            itemlist.add("Order nr: " + orderId + " Stykliste");
            itemlist.add("");
            while (resultset.next()) {
                String name = resultset.getString("name");
                String desc = resultset.getString("description");
                int length = resultset.getInt("length");
                String unit = resultset.getString("unit");
                int amount = resultset.getInt("amount");

                itemlist.add("Navn: " + name);
                itemlist.add("Beskrivelse: " + desc);
                if (length > 0) {
                    itemlist.add("Længde: " + length);
                }
                itemlist.add("Enhed: " + unit);
                itemlist.add("Antal: " + amount);
                itemlist.add("");

            }
            System.out.println("sql syntax ok? " + SQL);

        } catch (SQLException | ClassNotFoundException ex) { //temporary error
            throw new Error(ex.getMessage());
        }

        return itemlist;

    }
}