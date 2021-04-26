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


public class PresentaionLayer extends JFrame {
   DataLayer db = new DataLayer();
   Scanner scan = new Scanner(System.in);
   JFrame loginFrame = new JFrame();
   
   int userRole;
   
   public void registerGUI(){
   
      JPanel Inputbox = new JPanel(new GridLayout(8,1));
      JLabel lblUser     = new JLabel("Username :");
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
      jrbStudent.setActionCommand("s");
      JRadioButton jrbFaculty  = new JRadioButton("Faculty" );
      jrbFaculty.setActionCommand("f");
      JRadioButton jrbPublic = new JRadioButton("Public" );
      jrbPublic.setActionCommand("p");
      
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

   
         String username = tfUser.getText();
         String password = tfPassword.getText();
         String fName = tfFname.getText();
         String lName = tfLname.getText();
         
         String role =  bgrole.getSelection().getActionCommand();
         String phoneNumber = tfPhone.getText();
         
         db.register(username,password,fName,lName,role,phoneNumber) ;
         
   
   }
   
    public void loginGUI(){
      
      
      //add the title in the screen 
         JPanel jpTitle = new JPanel();
      
         JLabel title1 = new JLabel("Login");
         Font fontTitle = new Font("Arial", Font.BOLD, 20 );
         title1.setFont( fontTitle ); 
         jpTitle.add( title1 );
         
         loginFrame.add( jpTitle, BorderLayout.NORTH );
      
      
         JPanel jpLogin = new JPanel(new GridLayout(0,1));
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
         jbReg.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent e) { 
            registerGUI();
         } });
         jbLog.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent e) { 
            String userName = tfUser.getText();
            String password = tfPassword.getText();
            userRole = db.login(userName,password);
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
      loginFrame.setSize( 300, 200 ); 
      loginFrame.setVisible( true );
      

         
   
   }

   public PresentaionLayer()  {
      if (db.connect()){
         loginGUI();
         if (userRole == 1){
         // if role is faculty
         
         }
         else if (userRole == 2){
         
         // if role is student
         }
         
         else if (userRole == 3){
         
         // if role is public
         }
         else{
         
         }
          
      }//end of if
      
      else{
         System.out.println("\nNo DB connection");
         System.exit(0);
      }
    }//end of CONSTRUCTOR
    
    public static void main(String[] args) {
	   System.out.println("Group 6 - Project - ISTE-330");
      new PresentaionLayer();
   }//end of main method


}//class