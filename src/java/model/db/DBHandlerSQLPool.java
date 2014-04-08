/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import model.beans.CustomerBean;

/**
 *
 * @author ahmed
 */
public class DBHandlerSQLPool extends HttpServlet{

    
    public int verifyLoginCustomer(Connection conn,String usr, String pwd) throws SQLException { 
        
        
        ResultSet rs = null;
        try {
            
            String query = "Select cid FROM customer WHERE usr='"+usr+"' AND psw='"+pwd+"'";
            PreparedStatement stmt = conn.prepareStatement(query);
   
            rs  = stmt.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            if(result > 0){
                return result;
            }
            
            // rs = stmt.executeQuery("SELECT name FROM category");
            
        } catch (SQLException e) { 
            System.out.println(e);
        }
        return 0; 
    }
    
    public ResultSet getInfoCust(Connection conn,int id) throws SQLException{
        
        ResultSet rs = null;
        try {
            
            String query = "Select * FROM customer WHERE cid =" + id;
            PreparedStatement stmt = conn.prepareStatement(query);
   
            rs  = stmt.executeQuery();
            
            // rs = stmt.executeQuery("SELECT name FROM category");
            
        } catch (SQLException e) { 
            System.out.println(e);
        }
        return rs; 
        
    }
    
    public int verifyLoginEmployee(Connection conn,String usr, String pwd) throws SQLException { 
        
        
        ResultSet rs = null;
        try {
            
            String query = "Select eid FROM employee WHERE username='"+usr+"' AND password='"+pwd+"'";
            PreparedStatement stmt = conn.prepareStatement(query);
   
            rs  = stmt.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            if(result > 0){
                return result;
            }
            
            // rs = stmt.executeQuery("SELECT name FROM category");
            
        } catch (SQLException e) { 
            System.out.println(e);
        }
        return 0; 
    }
    
    public int verifyEmployeeData(Connection conn,String usr) throws SQLException { 
        ResultSet rs = null;
        try {            
            String query = "Select eid FROM employee WHERE username='"+usr+"'";
            PreparedStatement stmt = conn.prepareStatement(query);
   
            rs  = stmt.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            if(result > 0){
                return result;
            }            
        } catch (SQLException e) { 
            System.out.println(e);
        }
        return 0; 
    }
    
    
    
    public ResultSet getInfoEmp(Connection conn,int id) throws SQLException{
        
        ResultSet rs = null;
        try {
            
            String query = "Select * FROM employee WHERE eid =" + id;
            PreparedStatement stmt = conn.prepareStatement(query);
   
            rs  = stmt.executeQuery();
            
            // rs = stmt.executeQuery("SELECT name FROM category");
            
        } catch (SQLException e) { 
            System.out.println(e);
        }
        return rs;        
    }
    
    // FOR FRONTCONTROLLER 
    
    public int getInfoBranche(Connection conn, String brancheName) {
        ResultSet rs = null;
        try {            
            String query = "SELECT * FROM branche WHERE bname = '"+brancheName+"'";
            PreparedStatement stmt = conn.prepareStatement(query);   
            rs  = stmt.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            if(result > 0){
                return result;
            }           
        } catch (SQLException e) { 
            System.out.println(e);
        }
        return 0; 
    }
    
    
    // FOR FRONT CONTROLLER FOR ACCOUNT SUPERVISOR BEAN FILLING TIME!!!
    
    
    
    public int getInfoAccountSupervisor(Connection conn, int eid) {
        ResultSet rs = null;
        try {            
            String query = "SELECT * FROM Account_supervisor WHERE eid = '"+eid+"'";
            PreparedStatement stmt = conn.prepareStatement(query);   
            rs  = stmt.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            if(result > 0){
                return result;
            }           
        } catch (SQLException e) { 
            System.out.println(e);
        }
        return 0; 
    }
    
    
    
    // method from front controller
    
    public int getInformationCustomer(Connection conn, int cid) {
        ResultSet rs = null;
        try {            
            String query = "SELECT * FROM Customer WHERE cid = '"+cid+"'";
            PreparedStatement stmt = conn.prepareStatement(query);   
            rs  = stmt.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            if(result > 0){
                return result;
            }           
        } catch (SQLException e) { 
            System.out.println(e);
        }
        return 0; 
    }
    
    
    
    
    
    
    
    
    
    
    
    // FOR BRANCHEBEAN CLASS, FOR METHOD FILL BRANCHE BEAN()
    
    
        public ResultSet fillInfoBranche(Connection conn, int beanid) throws SQLException {
        
        ResultSet rs = null;
        try {            
            String query = "Select * FROM branche WHERE bid = '"+beanid+"'";
            PreparedStatement stmt = conn.prepareStatement(query);
   
            rs  = stmt.executeQuery();
            
            // rs = stmt.executeQuery("SELECT name FROM category");
            
        } catch (SQLException e) { 
            System.out.println(e);
        }
        return rs;        
    }
    
        
        
        
        // FILL INFO ACCOUNT SUSERVISOR IN CLASS ACCOUNTSUPERVISORBEAN
        
        public ResultSet fillInfoAccountSupervisor(Connection conn, int employeeId) throws SQLException{        
        ResultSet rs = null;
        try {            
            String query = "Select * FROM Account_supervisor WHERE eid = '"+employeeId+"'";
            PreparedStatement stmt = conn.prepareStatement(query);   
            rs  = stmt.executeQuery();           
        } catch (SQLException e) { 
            System.out.println(e);
        }
        return rs;        
    }
        
        
        
}
