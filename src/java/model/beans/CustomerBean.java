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
    private ArrayList beanSavingAccountList;
    private ArrayList beanCheckingAccountList;
    
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
        beanSavingAccountList = new ArrayList();
        beanCheckingAccountList = new ArrayList();
    }
    
    public CustomerBean(int beanCid) {
        this.beanId = beanCid;
        beanSavingAccountList = new ArrayList();
        beanCheckingAccountList = new ArrayList();
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
        beanSavingAccountList = b.beanSavingAccountList;
        beanCheckingAccountList = b.beanCheckingAccountList;
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
    
    public ArrayList getBeanSavingAccountList() {
        return beanSavingAccountList;
    }

    public void setBeanSavingAccountList(ArrayList beanSavingAccountList) {
        this.beanSavingAccountList = beanSavingAccountList;
    }
    
    public ArrayList getBeanCheckingAccountList() {
        return beanCheckingAccountList;
    }

    public void setBeanCheckingAccountList(ArrayList beanCheckingAccountList) {
        this.beanCheckingAccountList = beanCheckingAccountList;
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
            setbeanUsername(cb.getBeanUsername());
            setBeanCheckingAccountList(cb.getBeanCheckingAccountList());
            setBeanSavingAccountList(cb.getBeanSavingAccountList());
        
    }
    
    public boolean updateProfile() throws SQLException, ServletException{
        DBHandlerSQLPool d = new DBHandlerSQLPool();
        
        if(d.updateCustomer(this)){
            fillCustomerBean();
            return true;
        }
        return false;
    }
    
    public void fillAccounts() throws SQLException{
        DBHandlerSQLPool d = new DBHandlerSQLPool();
        //Savings
        setBeanSavingAccountList(d.getSavingsAccount(beanId));
        //Checking
        setBeanCheckingAccountList(d.getCheckingAccount(beanId));
    }
    
    public String showCheckingAccount(){
        String retour = new String();
        ArrayList al = getBeanCheckingAccountList();
        for(int i = 0;i<al.size();i++){
            retour += al.get(i).toString() + "<br>";
        }
        return retour;
    }
    
    public String showSavingAccount(){
        String retour = new String();
        ArrayList al = getBeanSavingAccountList();
        for(int i = 0;i<al.size();i++){
            retour += al.get(i).toString() + "<br>";
        }
        return retour;
    }
    
    public ArrayList listOfAccount(){
        ArrayList al = new ArrayList();
        ArrayList ca = getBeanCheckingAccountList();
        for(int i = 0; i < ca.size(); i++){
            al.add(((AccountBean)ca.get(i)).getBeanId());
        }
        ArrayList sa = getBeanSavingAccountList();
        for(int i = 0; i < sa.size(); i++){
            al.add(((AccountBean)ca.get(i)).getBeanId());
        }
        return al;
    }
    
}