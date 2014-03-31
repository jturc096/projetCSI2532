package model.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import model.db.DBHandlerSQLPool;
import model.db.DataSourceLoader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ahmed
 */


// TA Notes: 
// This class has one public class that receive a string. 
// Depending on the input type, a new bean is created and returned. 
// In this example, we see the method fillBeanForward(), which call the 
// database handler (DBHandlerSQLPool) and execute a select query. 

public class BeanHandler {

    private static Connection conn;
    public static BeanBase fillBean(String type, Connection connect) throws SQLException, ServletException {
        conn = connect;
        BeanBase bi = null;
        if (type.equals("foward")) {

            bi = fillBeanForward();

        }
        return bi;

    }
    private static ForwardBean fillBeanForward() {

        ForwardBean fb = new ForwardBean();
        fb.setBeanId(10);
        fb.setBeanMessage("I am a filled bean!");

        DataSourceLoader d = new DataSourceLoader();
        //fb.pushBeanStringList(d.pro);

        fb.pushBeanStringList("First argument");
        fb.pushBeanStringList("Second argument");
        fb.pushBeanStringList("Third argument");

        return fb;
    }
    
    
    

}
