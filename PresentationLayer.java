/**
 * Group Project 
 * Presentation Layer program 
 * 04/20/21
 */
import java.sql.*;
import javax.swing.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;  
import java.util.Date;   


public class PresentationLayer extends JFrame {
   DataLayer db = new DataLayer();
   Scanner scan = new Scanner(System.in);
   JFrame loginFrame = new JFrame();
   JFrame mainFrame = new JFrame();
   
   //buttons declared here
   JRadioButton insInt ;
   JRadioButton insWork ;
   JRadioButton upInt ;
   JRadioButton upAbs ;
   JRadioButton UpWork ;
   JRadioButton delInt ;
   JRadioButton DelWork ;
   JRadioButton SUI;
   JRadioButton SI;
   JRadioButton SUW;
   JRadioButton SW;
         

   // JPanel northPanel = new JPanel(new GridLayout(3,3));
//    JPanel southPanel = new JPanel(new GridLayout(3,3));
//    northPanel.add(np1, BorderLayout.NORTH);
//    southPanel.add(np2, BorderLayout.SOUTH);

   
   int userRole;
   String userId;
   
   public PresentationLayer()  {
      if (db.connect()){
      
         //add the title in the screen 
         JPanel jpTitle = new JPanel();
      
         JLabel title1 = new JLabel("Login");
         Font fontTitle = new Font("Arial", Font.BOLD, 20 );
         title1.setFont( fontTitle ); 
         jpTitle.add( title1 );
         
         loginFrame.add( jpTitle, BorderLayout.NORTH );
         
         JPanel jpLogin = new JPanel(new GridLayout(0,1));
         jpLogin.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
         
         JLabel lblUser     = new JLabel("Username :");
         JLabel lblPassword = new JLabel("Password :");
         JTextField tfUser     = new JTextField("");
         JTextField tfPassword = new JPasswordField("");
         
         
         tfUser.setForeground(Color.BLUE);
         tfPassword.setForeground(Color.BLUE);
      
         jpLogin.add(lblUser);
         jpLogin.add(tfUser );
         jpLogin.add(lblPassword );
         jpLogin.add(tfPassword );
         
         loginFrame.add( jpLogin, BorderLayout.CENTER);
         
         JPanel jpButton = new JPanel();
         JButton jbReg = new JButton("Register");
         JButton jbLog = new JButton("Login");
         jbReg.addActionListener(
            new ActionListener() { 
               public void actionPerformed(ActionEvent e) { 
                  registerGUI();
               } });
         jbLog.addActionListener(
            new ActionListener() { 
               public void actionPerformed(ActionEvent e) { 
                  String userName = tfUser.getText();
                  String password = tfPassword.getText();
                  userRole = db.login(userName,password);
                  userId = db.getUserId();
                  JOptionPane.showMessageDialog(null, "Login successful!");
                  // do we need to dismiss the login prompt after login is successful?
               
                  System.out.println(userId);
                  if (userRole == 1){
                  // if role is faculty
                  //trying to create a list of all the possible inputs
                  //faculty can insert, update, delete
                  //that the various methods require
                     System.out.println("Faculty accepted");
                     
                     
                     JPanel jpFaculty = new JPanel(new GridLayout(0,1));
                     mainFrame.add( jpFaculty, BorderLayout.CENTER );
                     
                     JPanel jpButtons = new JPanel(new GridLayout(8,0));
                     mainFrame.add ( jpButtons, BorderLayout.EAST );
                     
                     JPanel jpEnter = new JPanel(new GridLayout(1,0));
                     mainFrame.add ( jpEnter, BorderLayout.SOUTH );
                     
                     JLabel title2 = new JLabel("Please enter an operation from the appropriate buttons: ");
                     jpButtons.add( title2 );
                  //                      JLabel jlbOperation     = new JLabel("Operation: ");
                  //                      JTextField jtfOp = new JTextField("Op");
                     
                     JLabel jlbUserID  = new JLabel("User ID: ");
                     JTextField jtfUID = new JTextField("");
                     
                     JLabel jlbInterest     = new JLabel("Interest: ");
                     JTextField jtfInt = new JTextField("");
                     
                     JLabel jlbDescription     = new JLabel("Description: ");
                     JTextField jtfDesc = new JTextField("");
                     
                     JLabel jlbDate     = new JLabel("Date: ");
                     SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyy");
                     JFormattedTextField jtfDate = new JFormattedTextField(format);
                     
                     JLabel jlbInterestID     = new JLabel("Interest ID: ");
                     JTextField jtfIntID = new JTextField("");
                     
                     JLabel jlbWorkID     = new JLabel("Work ID: ");
                     JTextField jtfWID = new JTextField("");
                     
                     //buttons
                     insInt = new JRadioButton("Insert Interests (UserID, Interest)");
                     insWork = new JRadioButton("Insert work (UserID, Description, Date) ");
                     upInt = new JRadioButton("Update Interests (UserID, Interest) ");
                     upAbs = new JRadioButton("Update Abstract (UserID, Description) ");
                     UpWork = new JRadioButton("Update Work (UserID, Date) ");
                     delInt = new JRadioButton("Delete Interest (UserID, InterestID) ");
                     DelWork = new JRadioButton("Delete Work (UserID, WorkID)");
                     ButtonGroup group = new ButtonGroup();
                     group.add(insInt);
                     group.add(UpWork);
                     group.add(DelWork);
                     group.add(insWork);
                     group.add(upInt);
                     group.add(upAbs);
                     group.add(delInt);
                  
                     // //creating a drop-down menu of possible methods to call/select
                     //which enables all the correct fields and greys out the remaining                   
                     String[] choices = { "CHOICE 1", "CHOICE 2", "CHOICE 3" };
                     JComboBox<String> cb = new JComboBox<String>(choices);
                     cb.setVisible(true);
                     
                     jpButtons.add(insInt);
                     jpButtons.add(insWork);
                     jpButtons.add(upInt);
                     jpButtons.add(upAbs);
                     jpButtons.add(UpWork);
                     jpButtons.add(delInt);
                     jpButtons.add(DelWork);
                     
                     JButton jbEnter = new JButton("Enter");
                     jbEnter.addActionListener(
                        new ActionListener() { 
                           public void actionPerformed(ActionEvent e) { 
                           //if
                              if(insInt.isSelected()){
                                 System.out.println("insInt");
                                 String text = jtfUID.getText();
                                 String text2 = jtfInt.getText();
                                 db.facultyInsertInterests(text, text2); //
                              
                              } else if(insWork.isSelected()){
                                 System.out.println("insWork");
                                 String text = jtfUID.getText();
                                 String text2 = jtfDesc.getText();
                                 String text3 = jtfDate.getText();
                                 db.facultyInsertWork(text, text2, text3); //
                              
                              } else if(upInt.isSelected()){
                                 System.out.println("upInt");
                                 String text = jtfUID.getText();
                                 String text2 = jtfInt.getText();
                                 db.facultyUpdateInterests(text, text2); //
                              
                              } else if(upAbs.isSelected()){
                                 System.out.println("upAbs");
                                 String text = jtfUID.getText();
                                 String text2 = jtfDesc.getText();
                                 db.facultyUpdateAbstract(text, text2); //
                              
                              } else if(UpWork.isSelected()){
                                 System.out.println("UpWork");
                                 String text = jtfUID.getText();
                                 String text2 = jtfDate.getText();
                                 db.facultyUpdateWorkDate(text, text2); //
                              
                              } else if(delInt.isSelected()){
                                 System.out.println("delInt");
                                 String text = jtfUID.getText();
                                 String text2 = jtfIntID.getText();
                                 db.facultyDeleteInterest(text, text2);
                              
                              } else if(DelWork.isSelected()){
                                 System.out.println("DelWork");
                                 String text = jtfUID.getText();
                                 String text2 = jtfWID.getText();
                                 db.facultyDeleteWork(text, text2);
                              }
                           //end if
                           } });
                     jpEnter.add(jbEnter);
                  
                     jpFaculty.add(jlbUserID);
                     jpFaculty.add(jtfUID);
                     
                     jpFaculty.add(jlbInterest);
                     jpFaculty.add(jtfInt);
                     
                     jpFaculty.add(jlbDescription);
                     jpFaculty.add(jtfDesc);
                     
                     jpFaculty.add(jlbDate);
                     jpFaculty.add(jtfDate);
                     
                     jpFaculty.add(jlbInterestID);
                     jpFaculty.add(jtfIntID);
                     
                     jpFaculty.add(jlbWorkID);
                     jpFaculty.add(jtfWID);
                     
                     mainFrame.setDefaultCloseOperation( EXIT_ON_CLOSE );
                     mainFrame.pack();
                     mainFrame.setLocationRelativeTo( null );
                     mainFrame.setSize( 880, 350 ); 
                     mainFrame.setVisible( true );
                  
                  }
                  else if (userRole == 2){
                  
                  // if role is student
                  // user can search and view
                     System.out.println("Student accepted");
                     
                  
                     
                     JPanel jpStudent = new JPanel(new GridLayout(0,1));
                     mainFrame.add( jpStudent, BorderLayout.CENTER );
                     
                     JPanel jpButtons = new JPanel(new GridLayout(8,0));
                     mainFrame.add ( jpButtons, BorderLayout.EAST );
                     
                     JPanel jpEnter = new JPanel(new GridLayout(1,0));
                     mainFrame.add ( jpEnter, BorderLayout.SOUTH );
                     
                     JLabel title2 = new JLabel("Please enter an operation from the appropriate buttons: ");
                     jpButtons.add( title2 );
                     
                     JLabel jlbUserID  = new JLabel("User ID: ");
                     JTextField jtfUID = new JTextField("");
                     
                     JLabel jlbInterestID     = new JLabel("Interest ID: ");
                     JTextField jtfIntID = new JTextField("");
                     
                     JLabel jlbWorkID     = new JLabel("Work ID: ");
                     JTextField jtfWID = new JTextField("");
                     
                     //buttons
                     SUI = new JRadioButton("Search User Interests (UserID) ");
                     SI = new JRadioButton("Search Interests (InterestID) ");
                     SUW = new JRadioButton("Search User Work (UserID) ");
                     SW = new JRadioButton("Search Work (WorkID) ");
                     
                     ButtonGroup group = new ButtonGroup();
                     group.add(SUI);
                     group.add(SI);
                     group.add(SUW);
                     group.add(SW);
                  
                     // //creating a drop-down menu of possible methods to call/select
                     //which enables all the correct fields and greys out the remaining                   
                     
                     jpButtons.add(SUI);
                     jpButtons.add(SI);
                     jpButtons.add(SUW);
                     jpButtons.add(SW);
                  
                     
                     JButton jbEnter = new JButton("Enter");
                     jbEnter.addActionListener(
                        new ActionListener() { 
                           public void actionPerformed(ActionEvent e) { 
                           //if
                              if(SUI.isSelected()){
                                 System.out.println("SUI");
                                 String text = jtfUID.getText();
                                 //db.searchUserInterest(text); //
                                 String returns = db.searchUserInterest(text);
                                 System.out.println("Selected User Interests: " + returns);
                              
                              } else if(SI.isSelected()){
                                 System.out.println("SI");
                                 String text = jlbInterestID.getText();
                                 //db.searchInterests(text); //
                                 String returns = db.searchInterests(text);
                                 System.out.println("Selected Interests: " + returns);
                              
                              } else if(SUW.isSelected()){
                                 System.out.println("SUW");
                                 String text = jtfUID.getText();
                                 //db.searchUserWork(text); //
                                 String returns = db.searchUserWork(text);
                                 System.out.println("Selected User Works: " + returns);
                              
                              } else if(SW.isSelected()){
                                 System.out.println("WID");
                                 String text = jtfWID.getText();
                                 //db.searchWorks(text); //
                                 String returns = db.searchWorks(text);
                                 System.out.println("Selected Works: " + returns);
                              }
                                 //end if
                           
                           
                           } });
                     jpEnter.add(jbEnter);
                  
                     jpStudent.add(jlbUserID);
                     jpStudent.add(jtfUID);
                                          
                     jpStudent.add(jlbInterestID);
                     jpStudent.add(jtfIntID);
                     
                     jpStudent.add(jlbWorkID);
                     jpStudent.add(jtfWID);
                     
                     mainFrame.setDefaultCloseOperation( EXIT_ON_CLOSE );
                     mainFrame.pack();
                     mainFrame.setLocationRelativeTo( null );
                     mainFrame.setSize( 880, 350 ); 
                     mainFrame.setVisible( true ); 
                  }                 
                  else if (userRole == 3){
                  
                  // if role is public
                  // user can search and view
                     //////////////////////////////////////
                     System.out.println("Public user accepted");
                     
                  
                     
                     JPanel jpStudent = new JPanel(new GridLayout(0,1));
                     mainFrame.add( jpStudent, BorderLayout.CENTER );
                     
                     JPanel jpButtons = new JPanel(new GridLayout(8,0));
                     mainFrame.add ( jpButtons, BorderLayout.EAST );
                     
                     JPanel jpEnter = new JPanel(new GridLayout(1,0));
                     mainFrame.add ( jpEnter, BorderLayout.SOUTH );
                     
                     JLabel title2 = new JLabel("Please enter an operation from the appropriate buttons: ");
                     jpButtons.add( title2 );
                     
                     JLabel jlbUserID  = new JLabel("User ID: ");
                     JTextField jtfUID = new JTextField("");
                     
                     JLabel jlbInterestID     = new JLabel("Interest ID: ");
                     JTextField jtfIntID = new JTextField("");
                     
                     JLabel jlbWorkID     = new JLabel("Work ID: ");
                     JTextField jtfWID = new JTextField("");
                     
                     //buttons
                     SUI = new JRadioButton("Search User Interests (UserID) ");
                     SI = new JRadioButton("Search Interests (InterestID) ");
                     SUW = new JRadioButton("Search User Work (UserID) ");
                     SW = new JRadioButton("Search Work (WorkID) ");
                     
                     
                     ButtonGroup group = new ButtonGroup();
                     group.add(SUI);
                     group.add(SI);
                     group.add(SUW);
                     group.add(SW);
                  
                     // //creating a drop-down menu of possible methods to call/select
                     //which enables all the correct fields and greys out the remaining                   
                     
                     jpButtons.add(SUI);
                     jpButtons.add(SI);
                     jpButtons.add(SUW);
                     jpButtons.add(SW);
                  
                     
                     JButton jbEnter = new JButton("Enter");
                     jbEnter.addActionListener(
                        new ActionListener() { 
                           public void actionPerformed(ActionEvent e) { 
                           //if
                              if(SUI.isSelected()){
                                 System.out.println("SUI");
                                 String text = jtfUID.getText();
                                 //db.searchUserInterest(text); //
                                 String returns = db.searchUserInterest(text);
                                 System.out.println("Selected User Interests: " + returns);
                              
                              } else if(SI.isSelected()){
                                 System.out.println("SI");
                                 String text = jlbInterestID.getText();
                                 //db.searchInterests(text); //
                                 String returns = db.searchInterests(text);
                                 System.out.println("Selected Interests: " + returns);
                              
                              } else if(SUW.isSelected()){
                                 System.out.println("SUW");
                                 String text = jtfUID.getText();
                                 //db.searchUserWork(text); //
                                 String returns = db.searchUserWork(text);
                                 System.out.println("Selected User Works: " + returns);
                              
                              } else if(SW.isSelected()){
                                 System.out.println("WID");
                                 String text = jtfWID.getText();
                                 //db.searchWorks(text); //
                                 String returns = db.searchWorks(text);
                                 System.out.println("Selected Works: " + returns);
                              }
                                 //end if
                           
                           
                           } });
                     jpEnter.add(jbEnter);
                  
                     jpStudent.add(jlbUserID);
                     jpStudent.add(jtfUID);
                                          
                     jpStudent.add(jlbInterestID);
                     jpStudent.add(jtfIntID);
                     
                     jpStudent.add(jlbWorkID);
                     jpStudent.add(jtfWID);
                     
                     mainFrame.setDefaultCloseOperation( EXIT_ON_CLOSE );
                     mainFrame.pack();
                     mainFrame.setLocationRelativeTo( null );
                     mainFrame.setSize( 880, 350 ); 
                     mainFrame.setVisible( true ); 
                     ///////////////////////////////////////
                  
                  }
                  else{
                  
                  }
               
               } });
         
         jpButton.add(jbReg );
         jpButton.add(jbLog );
          
         loginFrame.add( jpButton, BorderLayout.SOUTH);
        
         //kill gui
         addWindowListener(
            new WindowAdapter(){
               public void windowClosing(WindowEvent we){
                  JOptionPane.showMessageDialog(null, "Thank you, have good day!");
               }
            }); 
      
      // GUI display controls 
         loginFrame.setDefaultCloseOperation( EXIT_ON_CLOSE );
         loginFrame.pack();
         loginFrame.setLocationRelativeTo( null );
         loginFrame.setSize( 500, 300 ); 
         
         loginFrame.setVisible( true );
      
      }//end of if
      
      else{
         System.out.println("\nNo DB connection");
         System.exit(0);
      }
   }//end of CONSTRUCTOR
    
   public static void main(String[] args) {
      System.out.println("Group 6 - Project - ISTE-330");
      new PresentationLayer();
   }//end of main method
   
    /**
    * This function will display the GUI for user to register
    */
   
   public void registerGUI(){
   
      JPanel Inputbox = new JPanel(new GridLayout(6,1));
      // Inputbox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
      JLabel lblUser     = new JLabel("Email(username) :");
      JLabel lblPassword = new JLabel("Password :");
      JTextField tfUser     = new JTextField("");
      JTextField tfPassword = new JTextField("");
      
      JLabel lblFname     = new JLabel("First Name :");
      JLabel lblLname = new JLabel("Last Name :");      
      JTextField tfFname    = new JTextField("");
      JTextField tfLname = new  JTextField("");
      
      JLabel lblrole = new JLabel("Role :");  
      // Radio buttons of the flavor of bubble tea     
      JPanel jprole = new JPanel();  
      JRadioButton jrbStudent    = new JRadioButton("Student", true);
      jrbStudent.setActionCommand("S");
      JRadioButton jrbFaculty  = new JRadioButton("Faculty" );
      jrbFaculty.setActionCommand("F");
      JRadioButton jrbPublic = new JRadioButton("Public" );
      jrbPublic.setActionCommand("P");
      
      ButtonGroup bgrole = new ButtonGroup();
      bgrole.add( jrbStudent);
      bgrole.add( jrbFaculty);
      bgrole.add( jrbPublic);
   
         
      jprole.add( jrbStudent);
      jprole.add( jrbFaculty );
      jprole.add( jrbPublic );
      
     
      JLabel lblPhone = new JLabel("PhoneNumber:");      
      JTextField tfPhone    = new JTextField("");
      
   
      Inputbox.add(lblUser);
      Inputbox.add(tfUser);
      Inputbox.add(lblPassword);
      Inputbox.add(tfPassword);
      Inputbox.add(lblFname);
      Inputbox.add(tfFname);
      Inputbox.add(lblLname);
      Inputbox.add(tfLname);
      
      Inputbox.add(lblrole);
      Inputbox.add(jprole);
      
      Inputbox.add(lblPhone);
      Inputbox.add(tfPhone);
      JOptionPane.showMessageDialog(null, Inputbox,
            		   "Register", JOptionPane.INFORMATION_MESSAGE);
   
      if (tfUser.getText().equals("")|| tfPassword.getText().equals("") || tfFname.getText().equals("") || tfLname.getText().equals("") || tfPhone.getText().equals("")){
            
         JOptionPane.showMessageDialog(null, "You must fill in all the input");
      }
      else{
         String username = tfUser.getText();
         String password = tfPassword.getText();
         String fName = tfFname.getText();
         String lName = tfLname.getText();
         
         String role =  bgrole.getSelection().getActionCommand();
         String phoneNumber = tfPhone.getText();
         db.register(username,password,fName,lName,role,phoneNumber) ;
      }
         
   }//register GUI


}//class