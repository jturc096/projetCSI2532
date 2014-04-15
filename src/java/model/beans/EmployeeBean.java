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
import model.db.DBHandlerSQLPool;

/**
 *
 * @author Jeremy
 */
public class EmployeeBean extends BeanBase {
    private int beanId;
    private String beanFname;
    private String beanLname;
    private String beanPhone;
    private String beanStartDate;
    private String beanUsername;
    private String beanPassword;
    
    // Variable pour les accounts que le employee supervise
    
    private ArrayList beanAccountSupervisorList;
    
    public EmployeeBean() {
        beanId = 0;
        beanUsername = "";
        beanPassword = "";
        beanFname = "";
        beanLname = "";
        beanPhone = "";
        beanStartDate = "";
        beanAccountSupervisorList = new ArrayList();
    }
    
    public EmployeeBean(int beanCid) {
        this.beanId = beanCid;
        beanAccountSupervisorList = new ArrayList();

    }
    
    public EmployeeBean(String beanUsername) {
        this.beanUsername = beanUsername;
        beanAccountSupervisorList = new ArrayList();

    }
    
    public EmployeeBean(EmployeeBean b) { 
        this.beanId = b.beanId;
        this.beanUsername = b.beanUsername;
        this.beanPassword = b.beanPassword;
        this.beanFname = b.beanFname;
        this.beanLname = b.beanLname;
        this.beanPhone = b.beanPhone;
        this.beanStartDate = b.beanStartDate;
        beanAccountSupervisorList = b.beanAccountSupervisorList;
    }
    
    public ArrayList getBeanAccountSupervisorList() {
        return beanAccountSupervisorList;
    }
    
    public void setBeanAccountSupervisorList(ArrayList beanAccountSupervisorList) {
        this.beanAccountSupervisorList = beanAccountSupervisorList;
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
    
    public void setbeanPhone(String beanPhone) {
        this.beanPhone = beanPhone;
    }
    
    public String getbeanPhone() {
        return beanPhone;
    }
    
    public void setbeanStartDate(String beanStartDate) {
        this.beanStartDate = beanStartDate;
    }
    
    public String getbeanStartDate() {
        return beanStartDate;
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
    
    public void fillEmployeeBean() throws SQLException, ServletException {

        DBHandlerSQLPool d = new DBHandlerSQLPool();
        
        
        EmployeeBean eb = d.getInfoEmp(beanId);
        setBeanId(beanId);
        setbeanFname(eb.getBeanFname());
        setbeanLname(eb.getBeanLname());
        setbeanPhone(eb.getbeanPhone());
        setbeanStartDate(eb.getbeanStartDate());
        setbeanUsername(eb.getBeanUsername());
        setBeanPassword(eb.getBeanPassword());
        setBeanAccountSupervisorList(eb.getBeanAccountSupervisorList());
    }
    
    
    public boolean updateProfile() throws SQLException, ServletException{
        DBHandlerSQLPool d = new DBHandlerSQLPool();
        if(d.updateEmployee(this)){
            fillEmployeeBean();
            return true;
        }
        return false;
    }  
    
    
     public void fillAccounts() throws SQLException{
        DBHandlerSQLPool d = new DBHandlerSQLPool();
        setBeanAccountSupervisorList(d.getAccountSupervisorData(beanId));
    }
     
     
     public String showAccountSupervisor(){
        String retour = new String();
        ArrayList al = getBeanAccountSupervisorList();
        for(int i = 0;i<al.size();i++){
            retour += al.get(i).toString() + "<br>";
        }
        return retour;
     } 
}