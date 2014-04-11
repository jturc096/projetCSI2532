/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.beans;


import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import model.db.DBHandlerSQLPool;

/**
 *
 * @author Jeremy
 */
public class AccountBean extends BeanBase {
    private int beanId;
    private int beanCid;
    private double beanAmount;
    private String beanType;
    private ArrayList beanActivityList;
    
    public AccountBean() {
        beanId = 0;
        beanCid = 0;
        beanAmount = 0.0;
        beanType = "";
        beanActivityList = new ArrayList();
    }
    
    public AccountBean(int beanId) {
        this.beanId = beanId;
        beanActivityList = new ArrayList();
    }
    
    public AccountBean(AccountBean a) { 
        this.beanId = a.beanId;
        this.beanAmount = a.beanAmount;
        this.beanCid = a.beanCid;
        this.beanType = a.beanType;
        beanActivityList = a.beanActivityList;
    }

    public int getBeanId() {
        return beanId;
    }

    public void setBeanId(int beanCid) {
        this.beanId = beanCid;
    }
    
    public int getBeanCid() {
        return beanCid;
    }

    public void setBeanCid(int beanCid) {
        this.beanCid = beanCid;
    }
    
    public double getBeanAmount() {
        return beanAmount;
    }

    public void setbeanAmount(double beanAmount) {
        this.beanAmount = beanAmount;
    }
    
    public String getBeanType() {
        return beanType;
    }

    public void setbeanType(String beanType) {
        this.beanType = beanType;
    }
    
    public ArrayList getBeanActivityList() {
        return beanActivityList;
    }

    public void setBeanActivityList(ArrayList beanActivityList) {
        this.beanActivityList = beanActivityList;
    }
    
    public void fillAccountActivity() throws SQLException{
        DBHandlerSQLPool d = new DBHandlerSQLPool();
        setBeanActivityList(d.getAccountActivity(beanId));
    }
    
    public String makePayment(double amount, String dest, String date) throws SQLException{
        DBHandlerSQLPool d = new DBHandlerSQLPool();
        String retour = "";
        if(amount <= this.getBeanAmount()){
            if(d.insertPayment(beanId, dest, date, amount)){
                retour = "Payment avec Succès";
            }
            else{
                retour = "Payment non fonctionner";
            }
        }else{
            retour = "Manque de fonds";
        }
        
        return retour;
    }
    
    @Override
    public String toString(){
        return " #Compte : " + getBeanId() + " Type : " + getBeanType() + " Montant : " + getBeanAmount() + "$";
    }
}