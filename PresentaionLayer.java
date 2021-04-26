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
   
   public void RegisterGUI(){
   
      JPanel Inputbox = new JPanel(new GridLayout(0,2));
      JLabel lblUser     = new JLabel("Username :");
		JLabel lblPassword = new JLabel("Password :");
      JTextField tfUser     = new JTextField("");
      JTextField tfPassword = new JPasswordField("");
      JLabel lblFname     = new JLabel("First Name :");
		JLabel lblLname = new JLabel("Last Name :");      
      JTextField tfFname    = new JTextField("");
      JTextField tfLname = new JPasswordField("");
      Inputbox.add(jbReg);
      Inputbox.add(lblUser);
		Inputbox.add(tfUser);
		Inputbox.add(lblPassword);
      Inputbox.add(tfPassword);
      JOptionPane.showMessageDialog(null, Inputbox,
		      		   "login", JOptionPane.INFORMATION_MESSAGE);


         String userName = tfUser.getText();
         String password = tfPassword.getText();
         db.register(username,password,fName,lName,role,phoneNumber) ;
   
   }

   public PresentaionLayer()  {
      if (db.connect()){
         //add the title in the screen 
         JPanel jpTitle = new JPanel();
      
         JLabel title1 = new JLabel("Login");
         Font fontTitle = new Font("Arial", Font.BOLD, 20 );
         title1.setFont( fontTitle ); 
         jpTitle.add( title1 );
         
         add( jpTitle, BorderLayout.NORTH );
      
      
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
         
         add( jpLogin, BorderLayout.CENTER);
         
    
         
         
         
         JPanel jpButton = new JPanel();
         JButton jbReg = new JButton("Register");
         JButton jbLog = new JButton("Login");
         jbReg.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent e) { 
            System.out.println("dsfdfsdds");
         } });
         jbLog.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent e) { 
            String userName = tfUser.getText();
            String password = tfPassword.getText();
            db.login(userName,password);
         } });
         
         jpButton.add(jbReg );
         jpButton.add(jbLog );
          
          
         add( jpButton, BorderLayout.SOUTH);
         
		   


         //kill gui
        addWindowListener(
         new WindowAdapter(){
            public void windowClosing(WindowEvent we){
               JOptionPane.showMessageDialog(null, "Thank you, have good day!");
            }
         }); 
      
      // GUI display controls 
      setDefaultCloseOperation( EXIT_ON_CLOSE );
      pack();
      setLocationRelativeTo( null );
      setSize( (int)(getWidth()*1.10), getHeight() ); 
      setVisible( true );
     

 
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