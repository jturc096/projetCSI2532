/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.beans;


import java.sql.SQLException;
import javax.servlet.ServletException;
import model.db.DBHandlerSQLPool;

/**
 *
 * @author Jeremy
 */
public class AccountActivityBean extends BeanBase {
    private int beanId;
    private String beanDate;
    private String beanDescription;
    private double beanAmount;
    private double beanBalance;
    
    public AccountActivityBean() {
        beanId = 0;
        beanDate = "";
        beanDescription = "";
        beanAmount = 0.0;
        beanBalance = 0.0;
    }
    
    public AccountActivityBean(int beanId) {
        this.beanId = beanId;
    }
    
    public AccountActivityBean(AccountActivityBean a) { 
        this.beanId = a.beanId;
        this.beanAmount = a.beanAmount;
        this.beanDate = a.beanDate;
        this.beanDescription = a.beanDescription;
        this.beanBalance = a.beanBalance;
    }

    public int getBeanId() {
        return beanId;
    }

    public void setBeanId(int beanCid) {
        this.beanId = beanCid;
    }
    
    public String getBeanDescription() {
        return beanDescription;
    }

    public void setBeanDescription(String beanDescription) {
        this.beanDescription = beanDescription;
    }
    
    public String getBeanDate() {
        return beanDate;
    }

    public void setBeanDate(String beanDate) {
        this.beanDate = beanDate;
    }
    
    public double getBeanAmount() {
        return beanAmount;
    }

    public void setBeanAmount(double beanAmount) {
        this.beanAmount = beanAmount;
    }
    
    public double getBeanBalance() {
        return beanBalance;
    }

    public void setBeanBalance(double beanBalance) {
        this.beanBalance = beanBalance;
    }
    
    public void fillAccountBean() throws SQLException, ServletException {

        
        
    }  
    
    @Override
    public String toString(){
        return " #Compte : " + getBeanId() + " Date : " + getBeanDate() +
                " Description : " + getBeanDescription() + " Montant : " +
                getBeanAmount() + "$ Balance : " + getBeanBalance() + "$";
    }
}