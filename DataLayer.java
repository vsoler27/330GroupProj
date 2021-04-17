/**
 * Group Project 
 * Data Layer program 
 * 04/12/21
 */
import java.sql.*;
import javax.swing.*;

public class DataLayer {
   /* Methods needed:
      create directions.txt
      main method and testing
   */
   private Connection conn;
   private ResultSet rs;
   private ResultSetMetaData rsmd;
   private Statement stmt;
   private String sql;
   
   final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";
   
   public boolean connect(){
      conn = null;
      String userName = "root";
      String password = "student";
      String url = "jdbc:mysql://localhost/project";
      url = url + "?serverTimezone=UTC";

	   String passwordInput = JOptionPane.showInputDialog(null,"Enter password: ",
	                     "Question", JOptionPane.QUESTION_MESSAGE);
      
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
   public void facultyInsertInterests (String userID, String interestID) {
      try {
         PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_interests(userID,interestID) VALUES(?,?) ");
         stmt.setString(1, userID);
         stmt.setString(2, interestID);
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
    public void facultyUpdateWork (String userID, String desc) {
      try {
         PreparedStatement stmt = conn.prepareStatement("UPDATE user_interest SET abstract=? WHERE userID=?");
         stmt.setString(1, desc);
         stmt.setString(2, userID);
         stmt.executeUpdate();
      } catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyUpdateWork()");
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
    * View all works in db
    */
   public void studentViewWork(){
      try {
         PreparedStatement stmt = conn.prepareStatement("Select userId, WorksId, Abstract from works");
         stmt.executeUpdate();
      } catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyUpdateInterest()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
   }

   /**
    * View all interests in db
    */
   public void studentViewInterests(){
      try {
         PreparedStatement stmt = conn.prepareStatement("Select userId, interests.interest from user_interests" + 
                                                            "join interests using (interestId)");
         stmt.executeUpdate();
      } catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyUpdateInterest()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
   }

   /**
    * Searches for a specific work
    * @param userID userID of the owner of the work
    * @param workID the work ID
    */
   public void studentSearchWork(String UserID, String WorkID){
      try {
         PreparedStatement stmt = conn.prepareStatement("Select WorksId, Abstract from works where userId = ? AND worksId = ?");
         stmt.setString(1, UserID);
         stmt.setString(2, WorkID);
         stmt.executeUpdate();
      } catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyUpdateInterest()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
   }

   /**
    * Searches for a specific interest
    * @param userID userID of the owner of the interest
    * @param workID the interest ID
    */   
   public void studentSearchInterests(String UserID, String interestId){
      try {
         PreparedStatement stmt = conn.prepareStatement("Select interest from interests join user_interests using (interestId)" + 
                                                            "where userId = ? AND interestId = ?");
         stmt.setString(1, UserID);
         stmt.setString(1, interestId);
         stmt.executeUpdate();
      } catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyUpdateInterest()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
   }

   /**
    * View all works in db
    */
   public void pubViewWork(){
      try {
         PreparedStatement stmt = conn.prepareStatement("Select userId, WorksId, Abstract from works");
         stmt.executeUpdate();
      } catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyUpdateInterest()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
   }

   /**
    * View all interests in db
    */
   public void pubViewInterests(){
      try {
         PreparedStatement stmt = conn.prepareStatement("Select userId, interests.interest from user_interests" + 
                                                            "join interests using (interestId)");
         stmt.executeUpdate();
      } catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyUpdateInterest()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
   }

   /**
    * Searches for a specific work
    * @param userID userID of the owner of the interest
    * @param workID the interest ID
    */   
   public void pubSearchWork(String UserID, String WorkID){
      try {
         PreparedStatement stmt = conn.prepareStatement("Select WorksId, Abstract from works where userId = ? AND worksId = ?");
         stmt.setString(1, UserID);
         stmt.setString(2, WorkID);
         stmt.executeUpdate();
      } catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyUpdateInterest()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
   }

   /**
    * Searches for a specific interest
    * @param userID userID of the owner of the work
    * @param workID the work ID
    */
   public void pubSearchInterests(String UserID, String interestId){
      try {
         PreparedStatement stmt = conn.prepareStatement("Select interest from interests join user_interests using (interestId)" + 
                                                            "where userId = ? AND interestId = ?");
         stmt.setString(1, UserID);
         stmt.setString(1, interestId);
         stmt.executeUpdate();
      } catch (SQLException sqle) {
         System.out.println("ERROR IN METHOD facultyUpdateInterest()");
         System.out.println("ERROR MESSAGE -> "+sqle);
      }
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
                  System.out.println(auto_id);
               
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
    */
   public void login(String username, String password) {
   
      try {
         stmt = conn.createStatement();
         sql = "SELECT * FROM user Where Email_or_username ='" + username;
         sql += "' AND Password = '" + password + "';";
         rs = stmt.executeQuery(sql); 
         if (rs.next()){ 
           System.out.println("Successfuly login");
           String role = rs.getString(2);
           if (role.equals("s")|| role.equals("S")){
               System.out.println("The role is Student");
           }
           else if (role.equals("f") || role.equals("F")){
               System.out.println("The role is Faculty");
           }
           else if (role.equals("p") || role.equals("P")){
               System.out.println("The role is public");
           }                 
         }
         else{
            System.out.println("username or password are incorrect");
         }
            
      }// end of try
      catch(Exception e)
      {
         System.out.println("Error whlie trying to select the data.");
         System.out.println("Error message is --> "+e);
      }//end of catch
      
   
      
   }// end of method login

   

   public static void main(String[] args) {
      DataLayer db = new DataLayer();
      if (db.connect()){
         System.out.println("Database Connect!!!");
         db.login("person@gmail.com","password");
         
      }// if connect 

           
      
   }//end of main method

}//end class
