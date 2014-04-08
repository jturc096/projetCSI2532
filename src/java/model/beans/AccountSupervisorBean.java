/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import model.db.DBHandlerSQLPool;

/**
 *
 * @author Gabriel Stone
 */
public class AccountSupervisorBean {
    
    private int beanEmployeeId;
    private int beanCustomerId;
    private int beanAccount_number;
    
    
    public AccountSupervisorBean() {
        this.beanAccount_number = 0;
        this.beanCustomerId = 0;
        this.beanEmployeeId = 0;
    }
    
    // Default Constructor where everything is known
    public AccountSupervisorBean(int beanAccount_number, int beanCustomerId, int beanEmployeeId) {
        this.beanAccount_number = beanAccount_number;
        this.beanCustomerId = beanCustomerId;
        this.beanEmployeeId = beanEmployeeId;
        
    }
    
    // Constructor where a AccountSupervisorBean is passed
    public AccountSupervisorBean(AccountSupervisorBean asbean) {
        this.beanAccount_number = asbean.beanAccount_number;
        this.beanCustomerId = asbean.beanCustomerId;
        this.beanEmployeeId = asbean.beanEmployeeId;
    }
    
    public AccountSupervisorBean(int beanEmployeeId) {
        this.beanEmployeeId = beanEmployeeId;
    }
    
    // GETTERS
    
    public int getBeanAccount_number() {
        return beanAccount_number;
    }
    
    public int getBeanCustomerId() {
        return beanCustomerId;
    }
    
    public int getBeanEmployeeId() {
        return beanEmployeeId;
    }
    
    // SETTERS
    
    public void setBeanAccount_number(int beanAccount_number) {
        this.beanAccount_number = beanAccount_number;
    }
    
    public void setBeanCustomerId(int beanCustomerId) {
        this.beanCustomerId = beanCustomerId;
    }
    
    public void setBeanEmployeeId(int beanEmployeeId) {
        this.beanEmployeeId = beanEmployeeId;
    }
    
    
    public void fillAccountSupervisorBean(Connection conn) throws SQLException, ServletException {

        DBHandlerSQLPool d = new DBHandlerSQLPool();
        
        // MUST CHANGE GET INFO CUST TO SOMETHING FOR THIS CLASS IN THE CLASS BDHANDLERSQLPOOL
        ResultSet rs = d.fillInfoAccountSupervisor(conn, beanEmployeeId); // HERE!!!
        try {
        while (rs.next()) { 
            setBeanEmployeeId(beanEmployeeId); 
            setBeanCustomerId(rs.getInt("cid"));
            setBeanAccount_number(rs.getInt("account_number"));
            }
        } catch (SQLException e) { 
            System.out.println(e);
        }
    }
}