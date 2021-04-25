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
         System.out.println("ERROR IN METHOD facultyUpdateWork()");
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
      Scanner scan = new Scanner(System.in);
      if (db.connect()){
         System.out.println("Database Connect!!!");
         db.login("person@gmail.com","password");
         
         System.out.println("Enter an option:");
         System.out.println("1. facultyInsertInterests");
         System.out.println("2. facultyInsertWork");
         System.out.println("3. facultyUpdateInterests");
         System.out.println("4. facultyUpdateWork");
         System.out.println("5. facultyDeleteInterest");
         System.out.println("6. facultyDeleteWork");
         System.out.println("7. register");
         int choice;
         choice = scan.nextInt();
         scan.nextLine();
         if (choice == 1) {
            System.out.println("Enter the user ID: ");
            String id = scan.nextLine();
            System.out.println("Enter the interest: ");
            String interest = scan.nextLine();
            db.facultyInsertInterests(id, interest);
         }
         else if (choice == 2) {
            System.out.println("Enter the user ID: ");
            String id = scan.nextLine();
            System.out.println("Enter the desc: ");
            String desc = scan.nextLine();
            System.out.println("Enter the date: ");
            String date = scan.nextLine();
            db.facultyInsertWork(id, desc, date);
         }
         else if (choice == 3) {
            System.out.println("Enter the user id: ");
            String id = scan.nextLine();
            System.out.println("Enter the interest: ");
            String interest = scan.nextLine();
            db.facultyUpdateInterests(id, interest);
         }
         else if (choice == 4) {
            System.out.println("Enter the user id: ");
            String id = scan.nextLine();
            System.out.println("Enter the desc: ");
            String desc = scan.nextLine();
            db.facultyUpdateAbstract(id, desc);
         }
         else if (choice == 5) {
            System.out.println("Enter the user id: ");
            String id = scan.nextLine();
            System.out.println("Enter the interest id: ");
            String int_id = scan.nextLine();
            db.facultyDeleteInterest(id, int_id);
         }
         
         else if (choice == 6) {
            System.out.println("Enter the user id: ");
            String id = scan.nextLine();
            System.out.println("Enter the work id: ");
            String work_id = scan.nextLine();
            db.facultyDeleteWork(id, work_id);
         }
         else if (choice == 7) {
            System.out.println("Enter the username: ");
            String username = scan.nextLine();
            System.out.println("Enter the password: ");
            String password = scan.nextLine();
            System.out.println("Enter the first name: ");
            String fname = scan.nextLine();
            System.out.println("Enter the last name: ");
            String lname = scan.nextLine();
            System.out.println("Enter the role: ");
            String role = scan.nextLine();
            System.out.println("Enter the phone number: ");
            String phone = scan.nextLine();
            int result = db.register(username, password, fname, lname, role, phone);
            System.out.println(result);
         } 
      }//end of if
   }//end of main method
}//end of class
