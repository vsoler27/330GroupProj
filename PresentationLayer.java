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
                     System.out.println("User accepted");
                     
                     
                     JPanel jpFaculty = new JPanel(new GridLayout(5,5));
                     mainFrame.add( jpFaculty, BorderLayout.NORTH );
                     JLabel jlbOperation     = new JLabel("Operation: ");
                     JTextField jtfOp = new JTextField("Op");
                     
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
                     insInt = new JRadioButton("Insert Interests");
                     insWork = new JRadioButton("Insert work");
                     upInt = new JRadioButton("Update Interests");
                     upAbs = new JRadioButton("Update Abstract");
                     UpWork = new JRadioButton("Update Work ");
                     delInt = new JRadioButton("Delete Interest ");
                     DelWork = new JRadioButton("Delete Work ");
                     ButtonGroup group = new ButtonGroup();
                     group.add(insWork);
                     group.add(upInt);
                     group.add(upAbs);
                     group.add(UpWork);
                     group.add(delInt);

                  
                  
                     // //creating a drop-down menu of possible methods to call/select
                  //                      //which enables all the correct fields and greys out the remaining                   
                     String[] choices = { "CHOICE 1", "CHOICE 2", "CHOICE 3" };
                     JComboBox<String> cb = new JComboBox<String>(choices);
                     cb.setVisible(true);
//                      mainFrame.add(cb);
//                      jpFaculty.add(cb);
                     
                     jpFaculty.add(insInt);
                     jpFaculty.add(insWork);
                     jpFaculty.add(upInt);
                     jpFaculty.add(upAbs);
                     jpFaculty.add(UpWork);
                     jpFaculty.add(delInt);
                     jpFaculty.add(DelWork);
                     
                    
                    // Thread insIntThread = new Thread();
//                     insIntThread.start();
                    //  boolean able = insInt.isEnabled();
//                     //  while (
//                      if(able == true){
//                         jtfUID.setEditable(true);
//                      } else if (able == false) {
//                         jtfUID.setEditable(false);
//                      }
                     
                     jpFaculty.add(jlbOperation);
                     jpFaculty.add(jtfOp);
                     
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
                     mainFrame.setSize( 700, 700 ); 
                     mainFrame.setVisible( true );
                     
                  }
                  else if (userRole == 2){
                  
                  // if role is student
                  // user can search and view
                  System.out.println("User accepted");
                     
                     JPanel jpStudent = new JPanel(new GridLayout(2,2));
                     mainFrame.add( jpStudent, BorderLayout.CENTER );
                                          
                     mainFrame.setDefaultCloseOperation( EXIT_ON_CLOSE );
                     mainFrame.pack();
                     mainFrame.setLocationRelativeTo( null );
                     mainFrame.setSize( 500, 300 ); 
                     mainFrame.setVisible( true );
                     
                     JLabel jlbUserID     = new JLabel("Which WorkID Do You Want to View? : ");
                     JTextField jtfIntID = new JTextField("");
                     jpStudent.add(jlbUserID);
                     jpStudent.add(jtfIntID);
                     JButton jbEnter = new JButton("Enter");
                     jpStudent.add(jbEnter);
                     jbEnter.addActionListener(
                        new ActionListener() { 
                        public void actionPerformed(ActionEvent e) { 
                        String text = jtfIntID.getText();
                     System.out.println(db.searchWorks(text)); 
                     }
                     });
                     JLabel jlbInterestID     = new JLabel("Which InterestID Do You Want to View? : ");
                     JTextField jtfInterestID = new JTextField("");
                     jpStudent.add(jlbInterestID);
                     jpStudent.add(jtfInterestID);
                     JButton jbEnter_e = new JButton("Enter");
                     jpStudent.add(jbEnter_e);
                     jbEnter_e.addActionListener(
                        new ActionListener() { 
                        public void actionPerformed(ActionEvent e) { 
                        String text_interest = jtfInterestID.getText();
                     System.out.println(db.searchUserInterest(text_interest)); 
                     }
                     });
                  }
                  
                  else if (userRole == 3){
                 
                  // if role is public
                  // user can search and view
                  System.out.println("User accepted");
                     
                     JPanel jpStudent = new JPanel(new GridLayout(2,2));
                     mainFrame.add( jpStudent, BorderLayout.CENTER );
                                          
                     mainFrame.setDefaultCloseOperation( EXIT_ON_CLOSE );
                     mainFrame.pack();
                     mainFrame.setLocationRelativeTo( null );
                     mainFrame.setSize( 500, 300 ); 
                     mainFrame.setVisible( true );
                     
                     JLabel jlbUserID     = new JLabel("Which WorkID Do You Want to View? : ");
                     JTextField jtfIntID = new JTextField("");
                     jpStudent.add(jlbUserID);
                     jpStudent.add(jtfIntID);
                     JButton jbEnter = new JButton("Enter");
                     jpStudent.add(jbEnter);
                     jbEnter.addActionListener(
                        new ActionListener() { 
                        public void actionPerformed(ActionEvent e) { 
                        String text = jtfIntID.getText();
                     System.out.println(db.searchWorks(text)); 
                     }
                     });
                     JLabel jlbInterestID     = new JLabel("Which InterestID Do You Want to View? : ");
                     JTextField jtfInterestID = new JTextField("");
                     jpStudent.add(jlbInterestID);
                     jpStudent.add(jtfInterestID);
                     JButton jbEnter_e = new JButton("Enter");
                     jpStudent.add(jbEnter_e);
                     jbEnter_e.addActionListener(
                        new ActionListener() { 
                        public void actionPerformed(ActionEvent e) { 
                        String text_interest = jtfInterestID.getText();
                     System.out.println(db.searchUserInterest(text_interest)); 
                     }
                     });
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