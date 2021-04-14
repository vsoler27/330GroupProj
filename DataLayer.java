/**
 * Group Project 
 * Data Layer program 
 * 04/12/21
 */
import java.sql.*;
import javax.swing.*;

public class DataLayer {
   /* Methods needed:
      Connect method - do we want a pop up?
      faculty update
         works
         interests
      faculty delete
         works
         interests
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
      check user
      validate login

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
   public void facultyInsertInterests (String userID, String interest) {
      try {
         PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_interests(userID,interest) VALUES(?,?) ");
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


}//end class