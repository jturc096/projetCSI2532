/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
import model.beans.AccountActivityBean;
import model.beans.AccountBean;
import model.beans.AccountSupervisorBean;
import model.beans.BrancheBean;
import model.beans.CustomerBean;
import model.beans.EmployeeBean;

/**
 *
 * @author ahmed
 */
public class DBHandlerSQLPool extends HttpServlet{

    private static DataSource pool;
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver is loaded .. ");
	} catch (ClassNotFoundException e) {
        	System.err.println("Failed to load the driver");
		e.printStackTrace();
		System.exit(-1);
	}
        String url = "jdbc:postgresql://web0.site.uottawa.ca:15432/gston006"; 
        Connection connect = null;
	try {
            connect = DriverManager.getConnection(url, "gston006", "G5621$gab");
            System.out.println("Connection is open ..");
	} catch (SQLException e) {
            e.printStackTrace();
	}
	return connect; 
    }
    public Connection getPoolConnection() throws SQLException{
        if(pool == null){
              pool = DataSourceLoader.getDataSource();
        }
        Connection conn = null;

        if (pool == null) {
            System.out.println("No connection pool.");
        } else {
            System.out.println("Yes Connection pool");
            try {
                conn = pool.getConnection();
                
                if (conn != null) {
                    System.out.println("Connection exist!");
                    return conn;
                }
            } catch (Exception e) {
                System.out.println("Excption caused probably by connection");
                e.printStackTrace();
            }
        }
        return null;
    }
    
    //Customer
    
    public int verifyLoginCustomer(String usr, String pwd) throws SQLException { 
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        int result = 0;
        if(conn!=null){
            ResultSet rs = null;
            try {
                String query = "Select cid FROM customer WHERE usr='"+usr+"' AND psw='"+pwd+"'";
                stmt = conn.prepareStatement(query);
   
                rs  = stmt.executeQuery();
                rs.next();
                result = rs.getInt(1);
            
            } catch (SQLException e) { 
                System.out.println(e);
            }finally{
                try {
                    if(stmt != null){
                    stmt.close();
                    }
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e) {
                }
            }
        }
        return result; 
    }
    
    public CustomerBean getInfoCust(int id) throws SQLException{
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CustomerBean cb = new CustomerBean();
        if(conn != null){
            try {            
                String query = "Select * FROM customer WHERE cid =" + id;
                stmt = conn.prepareStatement(query);
   
                rs  = stmt.executeQuery();
                
                while (rs.next()) { 
                    cb.setBeanId(id);
                    cb.setbeanFname(rs.getString("fname"));
                    cb.setbeanLname(rs.getString("lname"));
                    cb.setbeanCity(rs.getString("city"));
                    cb.setbeanAddr(rs.getString("addr"));
                    cb.setbeanPhone(rs.getString("phone"));
                    cb.setbeanEmail(rs.getString("email"));
                    cb.setbeanReachable(rs.getString("reachable"));
                    cb.setbeanUsername(rs.getString("usr"));
                }    
                } catch (SQLException e) { 
                    System.out.println(e);
                }finally{
                try {
                    if(rs != null){
                    rs.close();
                    }
                    if(stmt != null){
                    stmt.close();
                    }
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e) {
                }
            }
        }       
        return cb;      
    }
    
    public boolean updateCustomer(CustomerBean cb) throws SQLException { 
        Connection conn = getConnection();
        Boolean retour = false;
        PreparedStatement prest = null;
        if(conn != null){  
            try{    
                String query = "UPDATE customer" +
                    " SET city=?, addr=?, phone=?, email=?, reachable=?" +
                    " WHERE cid=?;";
                prest = conn.prepareStatement(query);
                prest.setString(1,cb.getBeanCity());
                prest.setString(2,cb.getbeanAddr());
                prest.setString(3,cb.getbeanPhone());
                prest.setString(4,cb.getbeanEmail());
                prest.setString(5,cb.getbeanReachable());
                prest.setInt(6,cb.getBeanId());
    
                prest.executeUpdate();
                retour = true;
            }catch (SQLException s){
                retour = false;
            }finally{
                try {
                    if(prest != null){
                    prest.close();
                    }
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e) {
                }
            }
        }
        return retour;
    }
    
    public boolean updateEmployee(EmployeeBean eb) throws SQLException { 
        Connection conn = getConnection();
        Boolean retour = false;
        PreparedStatement prest = null;
        if(conn != null){  
            try{    
                String query = "UPDATE Employee" +
                    " SET fname=?, lname=?, phone=?, password=?" +
                    " WHERE eid=?;";
                prest = conn.prepareStatement(query);
                prest.setString(1,eb.getBeanFname());
                prest.setString(2,eb.getBeanLname());
                prest.setString(3,eb.getbeanPhone());
                prest.setString(4,eb.getBeanPassword());
                prest.setInt(5,eb.getBeanId());
    
                prest.executeUpdate();
                retour = true;
            }catch (SQLException s){
                retour = false;
            }finally{
                try {
                    if(prest != null){
                    prest.close();
                    }
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e) {
                }
            }
        }
        return retour;
    }

    public int verifyLoginEmployee(String usr, String pwd) throws SQLException { 
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        int result = 0;
        if(conn!=null){
        ResultSet rs = null;
        try {
            
            String query = "Select * FROM employee WHERE username='"+usr+"' AND password='"+pwd+"'";
            stmt = conn.prepareStatement(query);
   
            rs  = stmt.executeQuery();
            rs.next();
            result = rs.getInt(1);           
        } catch (SQLException e) { 
            System.out.println(e);
        }finally{
                try {
                    if(stmt != null){
                    stmt.close();
                    }
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e) {
                }
            }
        }
        return result; 
    }
    
    public int verifyEmployeeData(String usr) throws SQLException { 
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        int result = 0;
        if(conn!=null){
        ResultSet rs = null;
        try {            
            String query = "Select * FROM employee WHERE username='"+usr+"'";
            stmt = conn.prepareStatement(query);
   
            rs  = stmt.executeQuery();
            rs.next();
            result = rs.getInt(1);           
        } catch (SQLException e) { 
            System.out.println(e);
        }finally{
                try {
                    if(stmt != null){
                    stmt.close();
                    }
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e) {
                }
            }
        }
        return result; 
    } 
    
    public EmployeeBean getInfoEmp(int id) throws SQLException{
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        EmployeeBean eb = new EmployeeBean();
        if(conn != null){
            try {
            
                String query = "Select * FROM employee WHERE eid =" + id;
                stmt = conn.prepareStatement(query);
   
                rs  = stmt.executeQuery();
                
                while (rs.next()) { 
                    eb.setBeanId(id);
                    eb.setbeanFname(rs.getString("fname"));
                    eb.setbeanLname(rs.getString("lname"));
                    eb.setbeanPhone(rs.getString("phone"));
                    eb.setbeanStartDate(rs.getString("start_date"));
                    eb.setbeanUsername(rs.getString("username"));
                    eb.setBeanPassword(rs.getString("password"));
                }    
        
                } catch (SQLException e) { 
                    System.out.println(e);
                }finally{
                try {
                    if(rs != null){
                    rs.close();
                    }
                    if(stmt != null){
                    stmt.close();
                    }
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e) {
                }
            }
        }
        return eb;        
    }
    
    public int getInfoBranche(String brancheName) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int result = 0;
        if(conn!=null){
        try {            
            String query = "SELECT * FROM branche WHERE bname = '"+brancheName+"'";
            stmt = conn.prepareStatement(query);   
            rs  = stmt.executeQuery();
            rs.next();
            result = rs.getInt(1);         
        } catch (SQLException e) { 
            System.out.println(e);
        }finally{
                try {
                    if(stmt != null){
                    stmt.close();
                    }
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e) {
                }
            }
        
        }
        return result; 
    }
    
    public int getInfoAccountSupervisor(int eid) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        int result = 0;
        if(conn!=null){
        ResultSet rs = null;
        try {            
            String query = "SELECT * FROM Account_supervisor WHERE eid = '"+eid+"'";
            stmt = conn.prepareStatement(query);   
            rs  = stmt.executeQuery();
            rs.next();
            result = rs.getInt(1);        
        } catch (SQLException e) { 
            System.out.println(e);
        }finally{
                try {
                    if(stmt != null){
                    stmt.close();
                    }
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e) {
                }
            }
        
        }
        return result; 
    }
  
    public int getInformationCustomer(int cid) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        int result = 0;
        if(conn!=null){
        ResultSet rs = null;
        try {            
            String query = "SELECT * FROM Customer WHERE cid = '"+cid+"'";
            stmt = conn.prepareStatement(query);   
            rs  = stmt.executeQuery();
            rs.next();
            result = rs.getInt(1);          
        } catch (SQLException e) { 
            System.out.println(e);
        }finally{
                try {
                    if(stmt != null){
                    stmt.close();
                    }
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e) {
                }
            }
        
        }
        return result; 
    }
    
    public BrancheBean fillInfoBranche(int beanid) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        BrancheBean bb = new BrancheBean();
        if(conn != null){
        try {            
            String query = "Select * FROM branche WHERE bid = '"+beanid+"'";
            stmt = conn.prepareStatement(query);
   
            rs  = stmt.executeQuery();
            while (rs.next()) { 
                    bb.setBeanId(beanid);
                    bb.setBeanAssets(rs.getInt("bassets"));
                    bb.setBeanCity(rs.getString("bcity"));
                    bb.setBeanName(rs.getString("bname"));
            }  
            // rs = stmt.executeQuery("SELECT name FROM category");
            
            } catch (SQLException e) { 
                System.out.println(e);
            }finally{
                try {
                        if(rs != null){
                            rs.close();
                        }
                        if(stmt != null){
                            stmt.close();
                        }
                        if(conn != null){
                            conn.close();
                        }
                    } catch (Exception e) {
                    }
                }
            }
            return bb;        
        }
    public AccountSupervisorBean fillInfoAccountSupervisor(int employeeId) throws SQLException{        
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AccountSupervisorBean ab = new AccountSupervisorBean();
        if(conn != null){
        try {            
            String query = "Select * FROM Account_supervisor WHERE eid = '"+employeeId+"'";
            stmt = conn.prepareStatement(query);   
            rs  = stmt.executeQuery(); 
            while (rs.next()) { 
                    ab.setBeanAccount_number(rs.getInt("account_number"));
                    ab.setBeanCustomerId(rs.getInt("cid"));
                    ab.setBeanEmployeeId(rs.getInt("eid"));
            }  
        } catch (SQLException e) { 
            System.out.println(e);
        }finally{
                try {
                        if(rs != null){
                            rs.close();
                        }
                        if(stmt != null){
                            stmt.close();
                        }
                        if(conn != null){
                            conn.close();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        return ab;        
    }
    
    //Customer Account
    public ArrayList getSavingsAccount(int id) throws SQLException{
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList abl = new ArrayList();
        if(conn != null){
        try {            
            String query = "select a.account_number, a.amount from account as a, savings as s, holds as h " +
                                "where (a.account_number = s.account_number) " +
                                "and (a.account_number = h.account_number) " +
                                "and (h.cid = "+id+")";
            stmt = conn.prepareStatement(query);   
            rs  = stmt.executeQuery(); 
            while (rs.next()) { 
                    AccountBean ab = new AccountBean();
                    ab.setBeanId(rs.getInt("account_number"));
                    ab.setBeanCid(id);
                    ab.setbeanAmount(rs.getDouble("amount"));
                    ab.setbeanType("Saving");
                    abl.add(ab);
            }  
        } catch (SQLException e) { 
            System.out.println(e);
        }finally{
                try {
                        if(rs != null){
                            rs.close();
                        }
                        if(stmt != null){
                            stmt.close();
                        }
                        if(conn != null){
                            conn.close();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        return abl;
    }
    
    //Customer Account
    public ArrayList getCheckingAccount(int id) throws SQLException{
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList cbl = new ArrayList();
        if(conn != null){
        try {            
            String query = "select a.account_number, a.amount from account as a, checking as c, holds as h " +
                                "where (a.account_number = c.account_number) " +
                                "and (a.account_number = h.account_number) " +
                                "and (h.cid = "+id+")";
            stmt = conn.prepareStatement(query);   
            rs  = stmt.executeQuery(); 
            while (rs.next()) { 
                    AccountBean ab = new AccountBean();
                    ab.setBeanId(rs.getInt("account_number"));
                    ab.setBeanCid(id);
                    ab.setbeanAmount(rs.getDouble("amount"));
                    ab.setbeanType("Checking");
                    cbl.add(ab);
            }  
        } catch (SQLException e) { 
            System.out.println(e);
        }finally{
                try {
                        if(rs != null){
                            rs.close();
                        }
                        if(stmt != null){
                            stmt.close();
                        }
                        if(conn != null){
                            conn.close();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        return cbl;
    }
    
    public ArrayList getAccountActivity(int id) throws SQLException{
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList aal = new ArrayList();
        if(conn != null){
        try {            
            String query = "SELECT * FROM account_activity WHERE account_number = "+id ;
            stmt = conn.prepareStatement(query);   
            rs  = stmt.executeQuery(); 
            while (rs.next()) { 
                    AccountActivityBean aa = new AccountActivityBean();
                    aa.setBeanId(rs.getInt("account_number"));
                    aa.setBeanDescription(rs.getString("description"));
                    aa.setBeanDate(rs.getString("adate"));
                    aa.setBeanAmount(rs.getDouble("amount"));
                    aa.setBeanBalance(rs.getDouble("remaining_balance"));
                    aal.add(aa);
            }  
        } catch (SQLException e) { 
            System.out.println(e);
        }finally{
                try {
                        if(rs != null){
                            rs.close();
                        }
                        if(stmt != null){
                            stmt.close();
                        }
                        if(conn != null){
                            conn.close();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        return aal;
    }
    
    
    // WORKING HERE WITH ACCOUNT SUPERVISOR DATA WITH MORE THAN ONE ACCOUNT
    
    
    
    
    public ArrayList getAccountSupervisorData(int id) throws SQLException{
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        if(conn != null){
        try {
            
            String query = "SELECT * FROM account_supervisor WHERE eid = '" +id+"'";
            stmt = conn.prepareStatement(query);   
            rs  = stmt.executeQuery(); 
            while (rs.next()) {
                AccountSupervisorBean asb = new AccountSupervisorBean();
                asb.setBeanAccount_number(rs.getInt("account_number"));
                asb.setBeanEmployeeId(rs.getInt("eid"));
                asb.setBeanCustomerId(rs.getInt("cid"));
                list.add(asb);
            }  
        } catch (SQLException e) { 
            System.out.println(e);
        }finally{
                try {
                        if(rs != null){
                            rs.close();
                        }
                        if(stmt != null){
                            stmt.close();
                        }
                        if(conn != null){
                            conn.close();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        return list;
    }
    
    
    
    // WORKING SPACE!!!!!
    
    
    
    
    
    
    
    
    
    
    
}