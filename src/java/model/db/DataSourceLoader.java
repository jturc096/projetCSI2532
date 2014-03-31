/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 *
 * @author ahmed
 */
public class DataSourceLoader extends HttpServlet {

   private static final String connURL = 
           "jdbc:postgresql://web0.site.uottawa.ca:15432/gston006";
    
   public static final String dataSourceKey = "dataSourceKey";
   private static DataSource dataSource = null; 
   
    @Override 
    public void init() {
     
        
        
        System.out.println("Loading the underlying JDBC driver (PostgreSQL)");
        try { 
            // super.init(cfg);     
            Class.forName("org.postgresql.Driver");
            
        } catch (ClassNotFoundException e) { 
            System.err.println("Cannot find driver class ");
            e.printStackTrace();
            System.exit(0);
        } 
        
        System.out.println("Setting up data source.");
        dataSource = setupDataSource();
          
      ServletContext context = getServletContext (); 
      context.setAttribute (dataSourceKey, dataSource); // Store the pool in the ServletContext. 
      
      System.out.println("Done loading driver and setting data source");
    
    }
    
    private DataSource setupDataSource() {
        
        
        // TODO: load an XML file instead. 
              Properties p = new Properties();
              p.setProperty("user", "gston006");
              p.setProperty("password", "G5621$gab");
              p.setProperty("databaseName", "gston006");
              p.setProperty("portNumber", "15432");
              p.setProperty("serverName", "web0.site.uottawa.ca");
              
        
              ConnectionFactory connectionFactory =
	            new DriverManagerConnectionFactory(connURL,p);
              
              PoolableConnectionFactory poolableConnectionFactory =
	            new PoolableConnectionFactory(connectionFactory, null);
              
              ObjectPool<PoolableConnection> connectionPool =
	                new GenericObjectPool<>(poolableConnectionFactory);
              
              PoolingDataSource<PoolableConnection> dataSource =
	                new PoolingDataSource<>(connectionPool);
              
              return dataSource; 
    
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            System.out.println("at DataSourceLoader Servlet .. received a Get or Post");
        }


    @Override
    public void destroy() { 
        getServletContext ().removeAttribute (dataSourceKey); 
        // if (dataSource != null) dataSource.close (); 
        super.destroy (); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>



}
