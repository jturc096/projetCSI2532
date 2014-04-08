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
public class BrancheBean extends BeanBase {
    
    private String beanName;
    private String beanCity;
    private int beanAssets;
    private int beanid;
    
    
    // Empty constructor
   public BrancheBean() {
       this.beanName = "";
       this.beanCity = "";
       this.beanAssets = 0;       
       this.beanid = 0;
   }
   
   // Default constructor
   public BrancheBean(BrancheBean branche) {
       this.beanName = branche.beanName;
       this.beanCity = branche.beanCity;
       this.beanAssets = branche.beanAssets;
       this.beanid = branche.beanid;
   }
   
   // Default constructor
   public BrancheBean(int beanid) {
       this.beanid = beanid;
   }
   
   // GETTERS
   
   public int getBeanId() {
       return beanid;
   }
   
   public String getBeanName() {
       return beanName;
   }
   
   public String getBeanCity() {
       return beanCity;
   }
   
   public int getBeanAssets() {
       return beanAssets;
   }
   
   // SETTERS
   
   public void setBeanId(int beanid) {
       this.beanid = beanid;
   }
   
   public void setBeanName(String bName) {
       this.beanName = bName;
   }
   
   public void setBeanCity(String bCity) {
       this.beanCity = bCity;
   }
   
   public void setBeanAssets(int beanAssets) {
       this.beanAssets = beanAssets;
   }
   
   public void fillBrancheBean(Connection conn) throws SQLException, ServletException {
        DBHandlerSQLPool d = new DBHandlerSQLPool();
        ResultSet rs = d.fillInfoBranche(conn, beanid);
        
        try {
            while (rs.next()) {
                setBeanId(beanid);
                setBeanName(rs.getString("bname"));
                setBeanCity(rs.getString("bcity"));
                setBeanAssets(rs.getInt("bassets"));
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }   
   }
}