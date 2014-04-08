/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import model.beans.BeanBase;
import model.db.DBHandlerSQLPool;

/**
 *
 * @author Jeremy
 */
public class CustomerBean extends BeanBase {
    private int beanId;
    private String beanFname;
    private String beanLname;
    private String beanCity;
    private String beanAddr;
    private String beanPhone;
    private String beanEmail;
    private String beanReachable;
    private String beanUsername;
    private String beanPassword;
    
    public CustomerBean() {
        beanId = 0;
        beanUsername = "";
        beanPassword = "";
        beanFname = "";
        beanLname = "";
        beanCity = "";
        beanAddr = "";
        beanPhone = "";
        beanEmail = "";
        beanReachable = "";
    }
    
    public CustomerBean(int beanCid) {
        this.beanId = beanCid;
    }
    
    public CustomerBean(CustomerBean b) { 
        this.beanId = b.beanId;
        this.beanUsername = b.beanUsername;
        this.beanPassword = b.beanPassword;
        this.beanFname = b.beanFname;
        this.beanLname = b.beanLname;
        this.beanCity = b.beanCity;
        this.beanAddr = b.beanAddr;
        this.beanPhone = b.beanPhone;
        this.beanEmail = b.beanEmail;
        this.beanReachable = b.beanReachable;
    }

    public int getBeanId() {
        return beanId;
    }

    public void setBeanId(int beanCid) {
        this.beanId = beanCid;
    }
    
    public String getBeanFname() {
        return beanFname;
    }

    public void setbeanFname(String beanFname) {
        this.beanFname = beanFname;
    }
    
    public String getBeanLname() {
        return beanLname;
    }

    public void setbeanLname(String beanLname) {
        this.beanLname = beanLname;
    }
    
    public String getBeanCity() {
        return beanCity;
    }
    
    public void setbeanCity(String beanCity) {
        this.beanCity = beanCity;
    }

    public void setbeanAddr(String beanAddr) {
        this.beanAddr = beanAddr;
    }
    
    public String getbeanAddr() {
        return beanAddr;
    }
    
    public void setbeanPhone(String beanPhone) {
        this.beanPhone = beanPhone;
    }
    
    public String getbeanPhone() {
        return beanPhone;
    }
    
    public void setbeanEmail(String beanEmail) {
        this.beanEmail = beanEmail;
    }
    
    public String getbeanEmail() {
        return beanEmail;
    }
    
    public void setbeanReachable(String beanReachable) {
        this.beanReachable = beanReachable;
    }
    
    public String getbeanReachable() {
        return beanReachable;
    }
    
    public String getBeanUsername() {
        return beanUsername;
    }

    public void setbeanUsername(String beanUsername) {
        this.beanUsername = beanUsername;
    }
    
    public String getBeanPassword() {
        return beanPassword;
    }

    public void setBeanPassword(String beanPassword) {
        this.beanPassword = beanPassword;
    }
    
    public void fillCustomerBean() throws SQLException, ServletException {

        DBHandlerSQLPool d = new DBHandlerSQLPool();
        
        CustomerBean cb = d.getInfoCust( beanId);
        
            setBeanId(beanId);
            setbeanFname(cb.getBeanFname());
            setbeanLname(cb.getBeanLname());
            setbeanCity(cb.getBeanCity());
            setbeanAddr(cb.getbeanAddr());
            setbeanPhone(cb.getbeanPhone());
            setbeanEmail(cb.getbeanEmail());
            setbeanReachable(cb.getbeanReachable());
        
    }
    
    public boolean updateProfile() throws SQLException, ServletException{
        DBHandlerSQLPool d = new DBHandlerSQLPool();
        
        if(d.updateCustomer(this)){
            fillCustomerBean();
            return true;
        }
        return false;
    }
    
    
    
}
