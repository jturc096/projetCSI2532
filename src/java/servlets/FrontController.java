/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import model.beans.ForwardBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.BeanBase;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import model.beans.AccountActivityBean;
import model.beans.AccountBean;
import model.beans.BeanHandler;
import model.beans.CustomerBean;
import model.beans.EmployeeBean;
import model.beans.BrancheBean;
import model.beans.AccountSupervisorBean;
import model.db.DBHandlerSQLPool;

/**
 *
 * @author ahmed
 */
public class FrontController extends HttpServlet {
    
    public static DBHandlerSQLPool db;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // TA Notes
    // This class is called the FrontController for one main reason: our applicatino 
    // is set such that any HTTP request is forwarded to this servlet. We can 
    // do this by modifying the <servlet-mapping> tag in web.xml ... Surely, we can map 
    // /forward to this servlet, or /login, or /sum .. or any pattern you want. 
    // The processRequest function does the following steps: 
    //  1. it checks from where the request was sent - this is done by checking 
    //      the request.getServletPath(); 
    //  2. Dependign on the request.getServletPath() value, 
    //      a: the servlet fills a data structure 
    //      implemented as a Java Class and called JavaBean 
    //      
    //      b: the servlet inserts the data structure into the request attributes .. 
    // 
    //      c: the servlet forwards
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        String urlPath = request.getServletPath();
        String directedURL = "WEB-INF/error.jsp";
            
        if (urlPath.equals("/loginC")) {
            DBHandlerSQLPool db = new DBHandlerSQLPool();
            // BeanBase fb = new ForwardBean();
            int id = 0;
            if(request.getParameter("cID") == null){
                String username = request.getParameter("jsp_usernameC_txt");
                String password = request.getParameter("jsp_passwordC_txt");
                id = db.verifyLoginCustomer(username, password);
            }else{
                id = Integer.parseInt(request.getParameter("cID"));
            }
            if(id>0){
                CustomerBean cb = new CustomerBean(id);
                cb.fillCustomerBean();
        
                request.setAttribute("customerbean", cb);
                request.setAttribute("msg", "");
                directedURL = "WEB-INF/home_customer.jsp";
            }else{
                directedURL = "index.jsp";
            }
        }else if(urlPath.equals("/account_summary")) {
            db = new DBHandlerSQLPool();
            int id = Integer.parseInt(request.getParameter("cID"));
            if(id>0){
                CustomerBean cb = new CustomerBean(id);
                cb.fillCustomerBean();
                cb.fillAccounts();
                request.setAttribute("customerbean", cb);
                directedURL = "WEB-INF/account_summary.jsp";
            }else{
                directedURL = "index.jsp";
            }
        }
        else if(urlPath.equals("/account_activity")) {
            db = new DBHandlerSQLPool();
            int id = Integer.parseInt(request.getParameter("cID"));
            if(id>0){
                CustomerBean cb = new CustomerBean(id);
                cb.fillCustomerBean();
                request.setAttribute("customerbean", cb);
                directedURL = "WEB-INF/account_activity.jsp";
                if(request.getParameter("jsp_accountid_txt")== null || request.getParameter("jsp_accountid_txt").equalsIgnoreCase("")){
                    request.setAttribute("accountbean", new AccountBean());
                }else{
                    AccountBean ab = new AccountBean(Integer.parseInt(request.getParameter("jsp_accountid_txt")));
                    ab.fillAccountActivity();
                    request.setAttribute("accountbean", ab);
                }
            }else{
                directedURL = "index.jsp";
            }
            
        }
        
        
        else if(urlPath.equals("/loginE")) {
            db = new DBHandlerSQLPool();
            String username = request.getParameter("jsp_usernameE_txt");
            String password = request.getParameter("jsp_passwordE_txt");
            int id = db.verifyLoginEmployee(username, password);
            if(id>0){
                EmployeeBean eb = new EmployeeBean(id);
                eb.fillEmployeeBean();

                request.setAttribute("employeebean", eb);
                request.setAttribute("message", "");

                directedURL = "WEB-INF/home_employee.jsp";
            }else{
                directedURL = "index.jsp";
            }
        }
        
        else if (urlPath.equals("/employee_data")) {
            db = new DBHandlerSQLPool();
            String username = request.getParameter("jsp_usernameE_txt");
            int id = db.verifyEmployeeData(username);
            if(id>0){
                EmployeeBean eb = new EmployeeBean(id);
                eb.fillEmployeeBean();

                request.setAttribute("employeebean", eb);

                directedURL = "WEB-INF/employee_data.jsp";
            }else{
                directedURL = "index.jsp";
            }
        }      
        
        else if (urlPath.equals("/branche_data")) {
            db = new DBHandlerSQLPool();
            String brancheName = request.getParameter("jsp_brancheName_txt");
            int id = db.getInfoBranche(brancheName);
            if(id>0){
                BrancheBean bb = new BrancheBean(id);
                bb.fillBrancheBean();

                request.setAttribute("branchebean", bb);

                directedURL = "WEB-INF/branche_data.jsp";
            }else{
                directedURL = "index.jsp";
            }
        }
        
          else if (urlPath.equals("/accountSupervisor")) {
            db = new DBHandlerSQLPool();
            String tempEmployeeId = request.getParameter("jsp_employeeId_txt");
            int employeeId = Integer.parseInt(tempEmployeeId);
            int id = db.getInfoAccountSupervisor(employeeId);
            if(id>0){
                AccountSupervisorBean asb = new AccountSupervisorBean(id);
                asb.fillAccountSupervisorBean();

                request.setAttribute("accountsupervisorbean", asb);

                directedURL = "WEB-INF/accountSupervisorData.jsp";
            }else{
                directedURL = "index.jsp";
            }
        }
        
        
        
        
        //  I AM WORKING HERE TO VIEW INFORMATION ON A CUSTOMER FROM AN EMPLOYEE POINT OF VIEW
        // WORKING HEREEE!!!!!
        
          
          else if (urlPath.equals("/customerDataE")) {
            db = new DBHandlerSQLPool();
            String tempCustomerId = request.getParameter("jsp_customerId_txt");
            int customerId = Integer.parseInt(tempCustomerId);
            int id = db.getInformationCustomer(customerId);
            if(id>0){
                CustomerBean cb = new CustomerBean(id);
                cb.fillCustomerBean();

                request.setAttribute("customerbean", cb);

                directedURL = "WEB-INF/customerData.jsp";
            }else{
                directedURL = "index.jsp";
            }
        }
          
          
          else if(urlPath.equals("/updateC")) {
                DBHandlerSQLPool db = new DBHandlerSQLPool();
                // BeanBase fb = new ForwardBean();
                String id = request.getParameter("cID");
                String ville = request.getParameter("jsp_cityC_txt");
                String adr = request.getParameter("jsp_adresseC_txt");
                String tel = request.getParameter("jsp_phoneC_txt");
                String email = request.getParameter("jsp_emailC_txt");
                String reach = request.getParameter("jsp_reachableC_txt");
                int cid = Integer.parseInt(id);
                CustomerBean cb = new CustomerBean(cid);
                cb.fillCustomerBean();
                cb.setbeanAddr(adr);
                cb.setbeanCity(ville);
                cb.setbeanPhone(tel);
                cb.setbeanEmail(email);
                cb.setbeanReachable(reach);
                String msg = "";
                if(cb.updateProfile()){
                    msg="Update Successful";
        
                }else{
                    msg="Update Failed";
                }
                directedURL = "WEB-INF/home_customer.jsp";
                request.setAttribute("customerbean", cb);
                request.setAttribute("msg", msg);
      
      
      // TA Notes:
      // Why did we put jsp_forward in WEB-INF/ ? 
      // JSP and Servlets in this folder cannot be accessed directly 
      // from the user! - they must be accessed from requests generated 
      // by the JSP and Servlets of the application 
      // This gives more security to the application in general. 
       }
        
        
        
        
        
        // WORKING HERE FOR THE UPDATE OF EMPLOYEE INFORMATION
        
        
        
        
        
        else if(urlPath.equals("/updateE")) {
            DBHandlerSQLPool db = new DBHandlerSQLPool();
            String id = request.getParameter("jsp_eid_hid");
            String fname = request.getParameter("jsp_firstNameE_txt");
            String lname = request.getParameter("jsp_nomFamille_txt");
            String phone = request.getParameter("jsp_phoneNumber_txt");
            String password = request.getParameter("jsp_employeePassword_txt");
            int eid = Integer.parseInt(id);
            EmployeeBean eb = new EmployeeBean(eid);
            eb.fillEmployeeBean();
            eb.setbeanFname(fname);
            eb.setbeanLname(lname);
            eb.setbeanPhone(phone);
            eb.setBeanPassword(password);
            String message = "";
            if(eb.updateProfile()){
                message="Update Successful";
                
            }else{
                message="Update Failed";
            }
            directedURL = "WEB-INF/home_employee.jsp";
            request.setAttribute("employeebean", eb);
            request.setAttribute("message", message);
        }
        
        
        
        
        
        
        
        
        
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(directedURL);
        dispatcher.forward(request, response);
            
}

    

    
                   // TA Notes:
            // The fillBean(type) is called by the FrontController in our example. 
            // However, we can make our application more comforming to the MVC model.
            // This can be done by forwarding the request to a special Servlet. For example, 
            // if the urlPath is "login", we would forward the request to a LoginServlet. 
            // The LoginServlet would then call fillBean("login") - for example - and then 
            // direct the request to a JSP page - WEB-INF/jsp_login. 
            // Following the second approach is more comforming to the MVC design pattern. 
            // Let me explain why. The FrontController servlet follows a Front Controller 
            // design patter - that is, all user requests shall be treated by front controller or 
            // more than one fron controller .. where each front controller can be 
            // implemented as a Servlet as it is the case in our application. 
            // In the MVC design pattern, however: the idea is to seperate the
            // model (eg. database, data source, hash tables!) from the view (eg Html page) 
            // and this shall be done via a controller. The controller in our case can be 
            // the LoginServlet described above. 
            
            // In a small application like ours, there is no need to seperate the front controller 
            // from the MVC controllers -- but once your application get bigger - then I suggest 
            // you follow the seperation. Why? because you dont want to the front controller 
            // to be very large and complex! - 
            // I tell why more - Let's say you want to add a new feature to your website such 
            // as show - showAllFilmsDoneByArtist .. or any other complex feature. If you want 
            // to add this feature, then all you need to do is to do the Model-Controller-View 
            // of the new feature .. test them  .. and then integrate them to the application 
            // using the FrontController ! - this simplifies the design of new feature, and 
            // simplify the testing of the new feature! 

    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Front Controller Servlet!";
    }

}
