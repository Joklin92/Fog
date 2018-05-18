/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccess;

import functionlayer.Orders;
import functionlayer.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author malik
 */
public class OrderMapper 
{
    
    public int createPreOrder(Orders ord) 
    {
        int orderId = 0;
        try 
        {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO orders (userID, length, width, height) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS ); 
            ps.setInt(1, ord.getUserId()); //user id
            ps.setInt(2, ord.getLength());
            ps.setInt(3, ord.getWidth());
            ps.setInt(4, ord.getHeight());

            System.out.println("Check sql order " + SQL);

            int check = ps.executeUpdate();
            if(check == 1)
            {
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                orderId = ids.getInt( 1 );
                System.out.println("Debug " + orderId);
                return orderId;

            }
        } catch ( SQLException | ClassNotFoundException ex ) { //temporary error
            throw new Error( ex.getMessage() );
        }
        return orderId;
    }

    public Orders getOrder(int orderId) 
    {
        Orders ord = null;
        try 
        {
            Connection con = Connector.connection();
            String SQL = "select * from orders where orderid = ?";

            PreparedStatement ps = con.prepareStatement( SQL); 
            ps.setInt(1, orderId);

            System.out.println("Check sql order " + SQL);

            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                int userID = rs.getInt("userID");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                ord  = new Orders(orderId, userID, length, width, height);
                System.out.println("Debug " + orderId);
                return ord;

            }
        } catch ( SQLException | ClassNotFoundException ex ) { //temporary error
            throw new Error( ex.getMessage() );
        }
        return ord;
    }
    
    public int getOrderId() 
    {
        int id = 0;
        try 
        {
            Connection con = Connector.connection();
            String SQL = "select MAX(orderId) from orders";

            PreparedStatement ps = con.prepareStatement( SQL);             

            System.out.println("Check sql order " + SQL);

            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                id = rs.getInt("orderId");
                return id;

            }
        } catch ( SQLException | ClassNotFoundException ex ) { //temporary error
            throw new Error( ex.getMessage() );
        }
        return id;
    }
    
    public List<Orders> allOrders()
    {
        List<Orders> orders = new ArrayList<>();
        Orders ord = null;
        try 
        {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM orders order by orderId desc";
            
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet resultset = ps.executeQuery();
            
            while(resultset.next())
            {   
                int id = resultset.getInt("orderId");
                int userID = resultset.getByte("userID");
                int length = resultset.getInt("length");
                int width = resultset.getInt("width");
                int height = resultset.getInt("height");
                boolean conf = resultset.getBoolean("orderConfirmed");
                
                
                
                ord = new Orders(id, userID, length, width, height, conf);
                orders.add(ord);
               
            }
            System.out.println("sql syntax ok? " + SQL);
            
        } catch ( SQLException | ClassNotFoundException ex ) { //temporary error
            throw new Error( ex.getMessage() );
        }
        
        return orders;
    }
    
    public List<Orders> confirmedOrders()
    {
        List<Orders> orders = new ArrayList<>();
        Orders ord = null;
        
        try 
        {
            Connection con = Connector.connection();
            String SQL = "select * from FogUsers.orders where orderConfirmed "
                    + "= '1' order by orderId desc;";
            
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet resultset = ps.executeQuery();
            
            while(resultset.next())
            {   
                int id = resultset.getInt("orderId");
                int userID = resultset.getByte("userID");
                int length = resultset.getInt("length");
                int width = resultset.getInt("width");
                int height = resultset.getInt("height");
                boolean conf = resultset.getBoolean("orderConfirmed");

                ord = new Orders(id, userID, length, width, height, conf);
                orders.add(ord);
                
            }
            System.out.println("sql syntax ok? " + SQL);
            
        } catch ( SQLException | ClassNotFoundException ex ) { //temporary error
            throw new Error( ex.getMessage() );
        }
        
        return orders;
    }
    

    public List<Orders> pendingOrders()
    {
        List<Orders> orders = new ArrayList<>();
        Orders ord = null;

        try 
        {
            Connection con = Connector.connection();
            String SQL = "select * from FogUsers.orders where orderConfirmed "
                    + "= '0' order by orderId desc;";

            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet resultset = ps.executeQuery();

            while(resultset.next())
            {
                int id = resultset.getInt("orderId");
                int userID = resultset.getByte("userID");
                int length = resultset.getInt("length");
                int width = resultset.getInt("width");
                int height = resultset.getInt("height");
                boolean conf = resultset.getBoolean("orderConfirmed");

                ord = new Orders(id, userID, length, width, height, conf);
                orders.add(ord);

            }
            System.out.println("sql syntax ok? " + SQL);

        } catch ( SQLException | ClassNotFoundException ex ) { //temporary error
            throw new Error( ex.getMessage() );
        }

        return orders;
    }

    public List<Orders> allCustomerOrders(int id)

    {
        List<Orders> orders = new ArrayList<>();
        Orders ord = null;
        
        try 
        {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM FogUsers.orders where userID = " + id;
            
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet resultset = ps.executeQuery();
            
            while(resultset.next())
            {   
                int orderId = resultset.getInt("orderId");
                int userID = resultset.getByte("userID");
                int length = resultset.getInt("length");
                int width = resultset.getInt("width");
                int height = resultset.getInt("height");
                boolean conf = resultset.getBoolean("orderConfirmed");
             
                if(userID == id)
                {
                    ord = new Orders(orderId, userID, length, width, height, conf);
                    orders.add(ord);
                }

            }
            System.out.println("sql syntax ok? " + SQL);
            
        } catch ( SQLException | ClassNotFoundException ex ) { //temporary error
            throw new Error( ex.getMessage() );
        }
        
        return orders;
    }
    
    public boolean isOrderSent(int orderID) throws ClassNotFoundException 
    {
        int sent = 0;
        try 
        {
            Connection con = Connector.connection();
            String sql = "Select orderConfirmed from orders where orderId= '" + orderID +"'"; 

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultset = ps.executeQuery(sql);

            while(resultset.next()) 
            {
                sent = resultset.getInt("orderConfirmed");                
            }
            if(sent == 1) return true;
                            
        } 
        catch (SQLException e) 
        {
             e.printStackTrace();
        }        
         return false;
    } 
    
     
    public void sendOrder(int orderID) 
    {    
        try 
        {
            Connection con = Connector.connection();
            String SQL = "update orders set orderConfirmed= '1' where orderId= '" + orderID +"'";
            
            PreparedStatement ps = con.prepareStatement( SQL ); 

            
            System.out.println("Check sql order " + SQL);
            
            ps.executeUpdate();
        }
        catch( SQLException | ClassNotFoundException ex) 
        {
            throw new Error( ex.getMessage() );
        } 
     }
}