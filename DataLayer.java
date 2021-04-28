/**
 * Group Project 
 * Data Layer program 
 * 04/12/21
 */
import java.sql.*;
import javax.swing.*;
import java.util.Scanner;

public class DataLayer {
   /* Methods needed:
      public view 
         works
         interests
      public search
         works
         interests
      student view 
         works
         interests
      student search 
         works
         interests
      create directions.txt
      main method and testing
   */
   private Connection conn;
   private ResultSet rs;
   private ResultSetMetaData rsmd;
   private Statement stmt;
   private String sql;
   private String userId;
   
   final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";
   
   public boolean connect(){
      conn = null;
      String userName = "root";
      String password = "student";
      String url = "jdbc:mysql://localhost/project";
      url = url + "?serverTimezone=UTC";
   
      String passwordInput = JOptionPane.showInputDialog(null,"Enter password: ",
                        "Connect to Database", JOptionPane.QUESTION_MESSAGE);
      
      password = passwordInput;
      
      try{
         Class.forName(DEFAULT_DRIVER);
         conn = DriverManager.getConnection(url, userName, password);
      }
      catch(ClassNotFoundException cnfe){
         System.out.println("No DB Connection");
         System.out.println("Class");
         System.out.println("ERROR MESSAGE-> "+cnfe);
         System.exit(0);
      }
      catch(SQLException sqle){
         System.out.println("ERROR SQLExcepiton in connect()");
         System.out.println("ERROR MESSAGE -> "+sqle);
         sqle.printStackTrace();
         System.exit(0);
      }//end of catch
   
      return (conn!=null);
   } //end of connect
   
   public boolean close(){
      try {
         if (!conn.isClosed()) {
            rs.close();
            stmt.close();
            conn.close();
            return true;
         }//end if
         else {
            return false; 
         }//end else
      }
      catch(SQLException sqle){
         System.out.println("ERROR IN METHOD close()");
         System.out.println("ERROR MESSAGE -> "+sqle);
         return false;
      }// end of catch
   }//end of method close

   /**
    * Inserts an interest for faculty 
    * @param userID the user adding the interest 
    * @param interest the interest to be added
    */
   public void facultyInsertInterests (String userID, String interest) {
      try {
         PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_interests(userID,interestID) VALUES(?,?) ");
         stmt.setString(1, userID);
         stmt.setString(2, interest);
         stmt.executeUpdate();
      }//end try
      catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyInsertInterests()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }//end catch
   }//end facultyInsertInterests

   /**
    * Inserts a work for faculty
    * @param userID the user adding the work
    * @param desc the abstract of the work
    * @param date the date of the work
    */
   public void facultyInsertWork(String userID, String desc, String date) {
      try {
         PreparedStatement stmt = conn.prepareStatement("INSERT INTO works(userID,abstract,date) VALUES (?,?,?)");
         stmt.setString(1, userID);
         stmt.setString(2, desc);
         stmt.setString(3, date);
         stmt.executeUpdate();
      } catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyInsertWork()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
   }//end facultyInsertWork

   /**
    * Updates a users interest
    * @param userID the user
    * @param interest the new interest
    */
   public void facultyUpdateInterests (String userID, String interest) {
      try {
         PreparedStatement stmt = conn.prepareStatement("UPDATE user_interest SET interest=? WHERE userID=?");
         stmt.setString(1, interest);
         stmt.setString(2, userID);
         stmt.executeUpdate();
      } catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyUpdateInterest()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
   }

    /**
    * Updates a users work
    * @param userID the user
    * @param desc the abstract to be changed
    */
   public void facultyUpdateAbstract (String userID, String desc) {
      try {
         PreparedStatement stmt = conn.prepareStatement("UPDATE works SET abstract=? WHERE userID=?");
         stmt.setString(1, desc);
         stmt.setString(2, userID);
         stmt.executeUpdate();
      } catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyUpdateAbstract()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
   }

    /**
    * Updates a the date of a work
    * @param userID the user
    * @param date the date to be changed
    */
    public void facultyUpdateWorkDate(String userID, String date) {
      try {
         PreparedStatement stmt = conn.prepareStatement("UPDATE works SET date=? WHERE userID=?");
         stmt.setString(1, date);
         stmt.setString(2, userID);
         stmt.executeUpdate();
      } catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyUpdateWorkDate()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
   }

   /**
    * deletes an interest
    * @param userID user removing the interest
    * @param interestID the interest to be removed
    */
   public void facultyDeleteInterest (String userID, String interestID) {
      try {
         PreparedStatement stmt = conn.prepareStatement("DELETE FROM user_interests WHERE userID = ? AND interestID = ?");
         stmt.setString(1, userID);
         stmt.setString(2, interestID);
         stmt.executeUpdate();
      }
      catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyDeleteInterest()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
   }

   /**
    * deletes a work
    * @param userID user removing the interest
    * @param workID the work to be removed
    */
   public void facultyDeleteWork (String userID, String workID) {
      try {
         PreparedStatement stmt = conn.prepareStatement("DELETE FROM works WHERE userID = ? AND workID = ?");
         stmt.setString(1, userID);
         stmt.setString(2, workID);
         stmt.executeUpdate();
      }
      catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyDeleteWork()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
   }

   /**
    * Search the interests of a user
    * @param userId the user being searched
    * @return a String of all of their interests
    */
   public String searchUserInterest(String userId) {
      String ans = "Inserts of User " + userId + ": ";
      try {
         PreparedStatement stmt = conn.prepareStatement("SELECT interestId FROM user_interests WHERE userId = ?");
         stmt.setString(1, userId);
         rs = stmt.executeQuery();
         while (rs.next()) {
            ans += rs.getString(1) + "\n";
         }

      }
      catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD searchUserInterest()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
      return ans;
   }
   
   /**
    * Search by interests
    * @param interestId the interst being searched for
    * @return a string of the users 
    */
   public String searchInterests(String interestId) {
      String ans = "Users with interest " + interestId + ": ";
      try {
         PreparedStatement stmt = conn.prepareStatement("SELECT userId FROM user_interests WHERE interestId = ?");
         stmt.setString(1, interestId);
         rs = stmt.executeQuery();
         while (rs.next()) {
            ans += rs.getString(1) + "\n";
         }

      }
      catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD searchInterests()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
      return ans;
   }

   /**
    * Search the works of a user
    * @param userId the user being searched
    * @return a String of all of their works
    */
    public String searchUserWork(String userId) {
      String ans = "Works of User " + userId + ": ";
      try {
         PreparedStatement stmt = conn.prepareStatement("SELECT workId FROM works WHERE userId = ?");
         stmt.setString(1, userId);
         rs = stmt.executeQuery();
         while (rs.next()) {
            ans += rs.getString(1) + "\n";
         }

      }
      catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD searchUserWork()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
      return ans;
   }
   
   /**
    * Search by work
    * @param interestId the interst being searched for
    * @return a string of the users 
    */
   public String searchWorks(String workId) {
      String ans = "Users with work " + workId + ": ";
      try {
         PreparedStatement stmt = conn.prepareStatement("SELECT userId FROM works WHERE workId = ?");
         stmt.setString(1, workId);
         rs = stmt.executeQuery();
         while (rs.next()) {
            ans += rs.getString(1) + "\n";
         }

      }
      catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD searchWorks()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
      return ans;
   }

/**
    * Register the user account and insert it to the database
    * @param username the account username 
    * @param password the account password
    * @param fName the user's first name
    * @param lName the user's last name
    * @param role the role of the account
    * @param phoneNumber the user's phone number
    * @return 1 if account successfuly insert to database
    * @return 0 if are not insert to database
    */
   public int register(String username, String password,String fName,String lName,String role,String phoneNumber) {
      int result = 0;
      
      if (username.length() <= 30 && password.length() <= 30){
         try {
            stmt = conn.createStatement();
            sql = "SELECT * FROM user Where Email_or_username ='" + username + "';";
            rs = stmt.executeQuery(sql);
            
            if (!rs.next()){
               try{
                  PreparedStatement stmt1;
               
                  stmt1 = conn.prepareStatement("INSERT INTO user(Email_or_username,Password,firstName,lastName,role)  VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                  stmt1.setString(1,username);
                  stmt1.setString(2,password);
                  stmt1.setString(3,fName);
                  stmt1.setString(4,lName);
                  stmt1.setString(5,role);
                  stmt1.executeUpdate();     // Performs the insert command
               
                  ResultSet rs1 = stmt1.getGeneratedKeys();
                  rs1.next();
                  int auto_id = rs1.getInt(1);
               
                  try{
                     PreparedStatement stmt2;
                  
                     stmt2 = conn.prepareStatement("INSERT INTO contact(phoneNumber,email,userid)  VALUES (?,?,?)");
                     stmt2.setString(1,phoneNumber);
                     stmt2.setString(2,username);
                     stmt2.setInt(3,auto_id);
                     result = stmt2.executeUpdate(); // Performs the insert command
                  
                  }catch(Exception e)
                  {
                     System.out.println("Error whlie trying to insert a contact record.");
                     System.out.println("Error message is --> "+e);
                  }//end of catch
               
               }// end of try
               catch(Exception e)
               {
                  System.out.println("Error whlie trying to insert a user record or close.");
                  System.out.println("Error message is --> "+e);
               }//end of catch
            
            }
            else {
               System.out.print("This username are been use");
            }
         }// end of try
         catch(Exception e)
         {
            System.out.println("Error whlie trying to select the data.");
            System.out.println("Error message is --> "+e);
         }//end of catch
      }
      else{
         if (username.length() > 30){
            System.out.println("Username too long!!!");
         
         }else{
            System.out.println("Password too long!!!");
         }
      
      }
      
      return (result);
   }// end of method register
   
   /**
    * Validate the user login and check to see their role
    * @param username the account username 
    * @param password the account password
    * @return 1 if role is faculty
    * @return 2 if role is student
    * @return 3 if role is public
    * @return 0 if account didn't exit;
    */
   public int login(String username, String password) {
   
      try {
         stmt = conn.createStatement();
         sql = "SELECT * FROM user Where Email_or_username ='" + username;
         sql += "' AND Password = '" + password + "';";
         rs = stmt.executeQuery(sql); 
         if (rs.next()){ 
            String role = rs.getString(2);
            userId = rs.getString(1);
            if (role.equals("s")|| role.equals("S")){
               System.out.println("The role is Student");
               return 2;
            }
            else if (role.equals("f") || role.equals("F")){
               System.out.println("The role is Faculty");
               return 1;
            }
            else if (role.equals("p") || role.equals("P")){
               System.out.println("The role is public");
               return 3;
            }                 
         }
         else{
            JOptionPane.showMessageDialog(null, "username or password are incorrect");
            return 0;
         }
            
      }// end of try
      catch(Exception e)
      {
         System.out.println("Error whlie trying to select the data.");
         System.out.println("Error message is --> "+e);
      }//end of catch
      
      return 0;
      
   }// end of method login
   
   
   /**
    * This function will get the userId who current login.
    * @return userId
    */
   public String getUserId() {
      System.out.println("The ID of currently logged in user is: " + userId);
      return userId;
   }// end of method getUserId


}//end of class
