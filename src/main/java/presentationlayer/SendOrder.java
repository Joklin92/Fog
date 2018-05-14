/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationlayer;


import dbaccess.OrderMapper;
import functionlayer.LoginSampleException;
import functionlayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Joklin
 */
public class SendOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {    
        
        HttpSession session = request.getSession();
        OrderMapper om = new OrderMapper();
        
        int id = Integer.parseInt(request.getParameter("id"));
                
        om.sendOrder(id);       
        
        return "allCurrentOrders";    
    }
    
}