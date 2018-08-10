/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libapp;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.Random; 
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

class SendAttachmentInEmail {
   public void runn() {
      // Recipient's email ID needs to be mentioned.
      String from = "rajdukist2004@gmail.com";

      // Sender's email ID needs to be mentioned
      String to = "sriramsudharocker@gmail.com";

      
      final String password = "vjlover007";//change accordingly
Properties props=new Properties();
     String host = "localhost";
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port","465");
  

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(from, password);
            }
         });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("Testing Subject");

         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText("This is message body");

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "D:\\Duelist.xlsx";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);

         // Send message
         Transport.send(message);

        // System.out.println("Sent message successfully....");
  
      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }
}

 class Alerter {
 
    public static void runn() {
        // TODO code application logic here
 String res;   
        try{  
//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  


        ResultSet rs=stmt.executeQuery("select s.mailid,s.phonenumber,t.booktaken,t.dateofreturn from transaction t join studentdetails s on s.registernumber=t.studentregisternumber");  

   while(rs.next())
     {
         String mailid=rs.getString("mailid");
         String phonenumber="+91"+rs.getString("phonenumber");
         String bookid=rs.getString("booktaken");
         res=rs.getString("dateofreturn");
         //System.out.println(res);

         Date date=new Date();

DateFormat currentDate=DateFormat.getDateInstance();
String p=currentDate.format(date);

Calendar calendar=new GregorianCalendar();
calendar.setTime(date);
SimpleDateFormat formatter1=new SimpleDateFormat("MMM dd, yyyy");
Date date1=formatter1.parse(res); 
/*System.out.println(date);
System.out.println(date1);*/

  float daysBetween;
             if(date1.getTime()<=date.getTime())
             {
	       long difference = date1.getTime() - date.getTime();
	       daysBetween = (difference / (1000*60*60*24));
               daysBetween++;
             //System.out.println(daysBetween);
               if(1.0==daysBetween)
               {
                   String m="The last date for returning the book that you have taken (ID is) "+bookid+"is"+res+"Do Return the book within due date to the Library in working hours to avoid fine amounts. \n"+"\t \t \t \t \t \t \t Regards AU HOSTEL LIBRARY.";
                   Mailer.send("rajdukist2004@gmail.com","vjlover007",mailid,"Book Return Alert",m);
 JFrame frl= new JFrame("InfoWindow");
     JLabel ll1,ll2;
        
    ll1=new JLabel("Mailer Alert");  
    ll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		ll1.setFont(font1);
		
	ll1.setForeground(customColor1);
    ll2=new JLabel("Mailed Succesfully.");  
    ll2.setBounds(100,135,400,30);  
    	ll2.setFont(font);
		
	ll2.setForeground(customColor);
        
        frl.add(ll1);frl.add(ll2);
    
        frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
        

               }
               else
               { 
                   JFrame frl= new JFrame("InfoWindow");
     JLabel ll1,ll2;
        
    ll1=new JLabel("Mailer Alert");  
    ll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		ll1.setFont(font1);
		
	ll1.setForeground(customColor1);
    ll2=new JLabel("No Return dates today.");  
    ll2.setBounds(100,135,400,30);  
    	ll2.setFont(font);
		
	ll2.setForeground(customColor);
        
        frl.add(ll1);frl.add(ll2);
    
        frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
        
    
               }
}
             
     }
    
    
    }catch(Exception e1){System.out.println(e1);}
  
    
}
}

class Transacx {
   public void runn(String s1,String s2,String s3,String s4) {
   try{
 
//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  
 String sql = "INSERT INTO TRANSACTION "+" VALUES ('"+s1+"','"+s2+"','"+s3+"','"+s4+"')";
stmt.executeUpdate(sql);

       JFrame frl= new JFrame("BookIssuedWindow");
     JLabel ll1,ll2;
        
    ll1=new JLabel("BOOK ISSUED");  
    ll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		ll1.setFont(font1);
		
	ll1.setForeground(customColor1);
    ll2=new JLabel("This Book is issued");  
    ll2.setBounds(100,135,400,30);  
    	ll2.setFont(font);
		
	ll2.setForeground(customColor);
        
        frl.add(ll1);frl.add(ll2);
    
        frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
        
   }catch(Exception se){
      se.printStackTrace();
   }
   }
}

class Datesubtractor {
    public void runn(String s1) {
        // TODO code application logic here
 String res;   
  float daysBetween;
  daysBetween =(float) 0.00;
 try{  
//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  
Statement stmt1=con.createStatement();  

        ResultSet rs=stmt.executeQuery("select t.dateofreturn,s.duesremaining from transaction t join studentdetails s on t.studentregisternumber=s.registernumber where t.studentregisternumber='"+s1+"'");  

   while(rs.next())
     {
         res=rs.getString("dateofreturn");
String due=rs.getString("duesremaining");
         Date date=new Date();
         float number = Float.valueOf(due);
DateFormat currentDate=DateFormat.getDateInstance();
String p=currentDate.format(date);

Calendar calendar=new GregorianCalendar();
calendar.setTime(date);
SimpleDateFormat formatter1=new SimpleDateFormat("MMM dd, yyyy");
Date date1=formatter1.parse(res); 
             if(date.getTime()>date1.getTime())
             {
	       long difference = date.getTime() - date1.getTime();
	       daysBetween+= (difference / (1000*60*60*24));
               daysBetween++;
               daysBetween+=number;
             }
             else
             {
               daysBetween =(float) 0.00;
             }
     String sql = "UPDATE STUDENTDETAILS SET DUESREMAINING = '"+daysBetween+"'  where REGISTERNUMBER= '"+s1+"' ";

       stmt1.executeUpdate(sql);
 
     }
    
    
    }catch(Exception e1){System.out.println(e1);}
  
    
}

}

class Table extends JPanel{
    JTable jt;
     public Table(){
     }
    public Table(String s1){
        

try
{
      DefaultTableModel model = new DefaultTableModel(new String[]{"BookID", "Book Name", "Author Name","Publisher Name","Edition","Subject","Date of Issue","Date of Return"}, 0);

        
      Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  


 ResultSet rs=stmt.executeQuery("SELECT B.BOOKID,B.BOOKNAME,B.AUTHORNAME,B.PUBLISHERNAME,B.EDITIONNUMBER,B.SUBJECT,T.DATEOFISSUE,T.DATEOFRETURN FROM BOOKS B JOIN TRANSACTION T ON B.BOOKID=T.BOOKTAKEN   WHERE T.STUDENTREGISTERNUMBER='"+s1+"'"); 
while(rs.next())  
{
    String x1=rs.getString(1);
    String x2=rs.getString(2);
    String x3=rs.getString(3);
    String x4=rs.getString(4);
    String x5=rs.getString(5);
    String x6=rs.getString(6);
    String x7=rs.getString(7);
    String x8=rs.getString(8); 
     model.addRow(new Object[]{x1, x2, x3,x4,x5,x6,x7,x8});
}
JTable table = new JTable(model);

        table.setPreferredScrollableViewportSize(new Dimension(1000,500));
        table.setFillsViewportHeight(true);
 table.setRowHeight(50);
 JScrollPane js=new JScrollPane(table);
       js.setVisible(true);
       add(js);

}
catch(Exception ee){System.out.println(ee);}
    }
    public static void mann(String x) {

        JFrame jf=new JFrame();
        Table tab= new Table(x);
        jf.setTitle("Table");
        jf.setSize(1366,768);
        jf.setVisible(true);
         jf.add(tab);




    }
      public static void runn() {
           JFrame f=new JFrame("");
            JLabel l1,l2;  
    l1=new JLabel("Search by StudentID");  
    l1.setBounds(50,50,400,50);  
    l2=new JLabel("StudentID");  
    l2.setBounds(50,200,400,50);  
    f.add(l1); f.add(l2);  
 Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,30);
 Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		l1.setFont(font1);
		
	l1.setForeground(customColor1);
     
    l2.setFont(font);
		
	l2.setForeground(customColor);
     
JTextField t1;

 t1=new JTextField();  
        t1.setBounds(250,210, 150,30);
 
        f.add(t1); 
 Button b=new Button("Find");  
    b.setBounds(250,300,150,40);  
	b.setFont(font1);
	b.setBackground(Color.RED);	
	b.setForeground(customColor);
     b.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        b.setBackground(Color.GREEN);
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        b.setBackground(UIManager.getColor("control"));
    }
});

    b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
String x=t1.getText();
            mann(x);
        }});
    f.add(b);
// Create a table with 10 rows and 5 columns
f.setSize(1366,788);
f.setLayout(null);
f.setVisible(true);
 
          
      }

}


class WriteDataToExcel {
    public void sunn(){
try
{
      //Create blank workbook
      XSSFWorkbook workbook = new XSSFWorkbook();
      
      //Create a blank sheet
      XSSFSheet spreadsheet = workbook.createSheet("Due Information");

      //Create row object
      XSSFRow row;
     Iterator<Row> rowIte =  spreadsheet.iterator();
while(rowIte.hasNext()){
    rowIte.next();              
    rowIte.remove();
}
      
      Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  
      int i=1;
      Map < String, Object[] > empinfo = new TreeMap < String, Object[] >();
      
      empinfo.put( "1", new Object[] {
         "Student ID" , "Student Name" , "Branch" , "Semester" , "Year" , "Due" , "Books in Hand" , "MailID" , "PhoneNumber"  });
      
ResultSet rs=stmt.executeQuery("select * from studentdetails");  
while(rs.next())  
{
    String x1=rs.getString(1);
    String x2=rs.getString(2);
    String x3=rs.getString(3);
    String x4=rs.getString(4);
    String x5=rs.getString(5);
    String x6=rs.getString(6);
    String x7=rs.getString(7);
    String x8=rs.getString(8);
    String x9=rs.getString(9);
    i++;
String s=String.valueOf(i);
      //This data needs to be written (Object[])
      
      empinfo.put( s, new Object[] {
         x1 , x2 , x3 , x4 , x5 , x6 , x7 , x8 , x9 });
      
      //Iterate over data and write to sheet
      Set < String > keyid = empinfo.keySet();
      int rowid = 0;
      
      for (String key : keyid) {
         row = spreadsheet.createRow(rowid++);
         Object [] objectArr = empinfo.get(key);
         int cellid = 0;
         
         for (Object obj : objectArr){
            Cell cell = row.createCell(cellid++);
            cell.setCellValue((String)obj);
         }
      }
}
for(int j=0;j<=9;j++)
{
spreadsheet.autoSizeColumn(j); 
}
 FileOutputStream out = new FileOutputStream(
         new File("D:\\Duelist.xlsx"));
      JFrame frl= new JFrame("InfoWindow");
     JLabel lll1,lll2;
        
    lll1=new JLabel("Due List Alert");  
    lll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);
Color customColor = new Color(0,0,0);
   

    		
		//set font for JLabel
		lll1.setFont(font1);
		
	lll1.setForeground(customColor1);
    lll2=new JLabel("Duelist.xlsx written successfully");  
    lll2.setBounds(100,135,600,30);  
    	lll2.setFont(font1);
	lll2.setForeground(customColor);
        
        frl.add(lll1);frl.add(lll2);
    frl.setSize(700,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
    frl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      workbook.write(out);
      out.close();
      SendAttachmentInEmail xx= new SendAttachmentInEmail();
      xx.runn();
}catch(Exception ee){System.out.println(ee);}
//Write the workbook in file system
     
   }
   public void runn(){
try
{
      //Create blank workbook
      XSSFWorkbook workbook = new XSSFWorkbook();
      
      //Create a blank sheet
      XSSFSheet spreadsheet = workbook.createSheet( " Books Info");

      //Create row object
      XSSFRow row;
     Iterator<Row> rowIte =  spreadsheet.iterator();
while(rowIte.hasNext()){
    rowIte.next();              
    rowIte.remove();
}
      
      Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  
      int i=1;
      Map < String, Object[] > empinfo = new TreeMap < String, Object[] >();
      
      empinfo.put( "1", new Object[] {
         "Book ID", "Book Name", "Author Name","Publisher Name","Edition","Price","Subject" });
      
ResultSet rs=stmt.executeQuery("select * from books where reference ='F' ");  
while(rs.next())  
{
    String x1=rs.getString(1);
    String x2=rs.getString(2);
    String x3=rs.getString(3);
    String x4=rs.getString(4);
    String x5=rs.getString(5);
    String x6=rs.getString(6);
    String x7=rs.getString(7);
    i++;
String s=String.valueOf(i);
      //This data needs to be written (Object[])
      
      empinfo.put( s, new Object[] {
         x1, x2, x3, x4 , x5 , x6 ,x7 });
      
      //Iterate over data and write to sheet
      Set < String > keyid = empinfo.keySet();
      int rowid = 0;
      
      for (String key : keyid) {
         row = spreadsheet.createRow(rowid++);
         Object [] objectArr = empinfo.get(key);
         int cellid = 0;
         
         for (Object obj : objectArr){
            Cell cell = row.createCell(cellid++);
            cell.setCellValue((String)obj);
         }
      }
}
for(int j=0;j<=7;j++)
{
spreadsheet.autoSizeColumn(j); 
} 
 FileOutputStream out = new FileOutputStream(
         new File("D:\\Books.xlsx"));
      
      workbook.write(out);
      out.close();
     
}catch(Exception ee){System.out.println(ee);}
//Write the workbook in file system
     
   }
  public void funn(){
try
{
      //Create blank workbook
      XSSFWorkbook workbook = new XSSFWorkbook();
      
      //Create a blank sheet
      XSSFSheet spreadsheet = workbook.createSheet( "Books Info");

      //Create row object
      XSSFRow row;
     Iterator<Row> rowIte =  spreadsheet.iterator();
while(rowIte.hasNext()){
    rowIte.next();              
    rowIte.remove();
}
      
      Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  
      int i=1;
      Map < String, Object[] > empinfo = new TreeMap < String, Object[] >();
      
      empinfo.put( "1", new Object[] {
         "Book ID", "Book Name", "Author Name","Publisher Name","Edition","Price","Subject" });
      
ResultSet rs=stmt.executeQuery("select * from books where reference ='T'");  
while(rs.next())  
{
    String x1=rs.getString(1);
    String x2=rs.getString(2);
    String x3=rs.getString(3);
    String x4=rs.getString(4);
    String x5=rs.getString(5);
    String x6=rs.getString(6);
    String x7=rs.getString(7);
    i++;
String s=String.valueOf(i);
      //This data needs to be written (Object[])
      
      empinfo.put( s, new Object[] {
         x1, x2, x3, x4 , x5 , x6 ,x7 });
      
      //Iterate over data and write to sheet
      Set < String > keyid = empinfo.keySet();
      int rowid = 0;
      
      for (String key : keyid) {
         row = spreadsheet.createRow(rowid++);
         Object [] objectArr = empinfo.get(key);
         int cellid = 0;
         
         for (Object obj : objectArr){
            Cell cell = row.createCell(cellid++);
            cell.setCellValue((String)obj);
         }
      }
}
for(int j=0;j<=7;j++)
{
spreadsheet.autoSizeColumn(j); 
}  
 FileOutputStream out = new FileOutputStream(
         new File("D:\\Referencebooks.xlsx"));
      
      workbook.write(out);
      out.close();
         JFrame frl= new JFrame("InfoWindow");
     JLabel lll1,lll2;
        
    lll1=new JLabel("Books Alert");  
    lll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);
Color customColor = new Color(0,0,0);
   

    		
		//set font for JLabel
		lll1.setFont(font1);
		
	lll1.setForeground(customColor1);
    lll2=new JLabel("Books.xlsx and referencebooks.xlsx written successfully");  
    lll2.setBounds(100,135,800,30);  
    	lll2.setFont(font1);
	lll2.setForeground(customColor);
        
        frl.add(lll1);frl.add(lll2);
    frl.setSize(1000,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
    frl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      //System.out.println("Books.xlsx and referencebooks.xlsx written successfully");
}catch(Exception ee){System.out.println(ee);}
//Write the workbook in file system
     
   }
}
class Mailer
{
    public static void send(String from,String password,String to,String sub,String msg)
    {
        Properties props=new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port","465");
         Session session=Session.getDefaultInstance(props,new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication()
         {
         return new PasswordAuthentication(from,password);
         }
         });
         try
         {
             MimeMessage message=new MimeMessage(session);
             message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
             message.setSubject(sub);
             message.setText(msg);
             Transport.send(message);
             System.out.println("Message sent Successfully");
             
         }catch(MessagingException e)
         {
             throw new RuntimeException(e);
         }
    }
}


/**
 *
 * @author SRIRAM
 */
class Sqlexamp{  
public int dbman(String uname,String pwd){  
try{  
//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  
      
ResultSet rs=stmt.executeQuery("select * from passtab");  
while(rs.next())  
{
    String x=rs.getString(1);
    String y=rs.getString(2);
    
    if(x.equals(uname) && y.equals(pwd))
    {
        System.out.println("DATA FOUND");
        return 1;
    }
    else
    {
        System.out.println("DATA NOT FOUND");
    }
}

con.close();  
  
}
catch(Exception e){ System.out.println(e);}  
return 0;
  
}  
}
class forgetpassword
{
    public void mann(String uname,String pwd,String m)
    {
    JFrame frl= new JFrame("Status");
     JLabel lll1,lll2;
        
    lll1=new JLabel("ConFirm your OTP");  
    lll1.setBounds(100,35,200,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    		
		//set font for JLabel
		lll1.setFont(font1);
		
	lll1.setForeground(customColor1);
    lll2=new JLabel("Enter the OTP");  
    lll2.setBounds(100,135,200,30);  
    	lll2.setFont(font1);
		
	lll2.setForeground(customColor1);
        JTextField t1;
        t1=new JTextField("");
        t1.setBounds(350,135,100,30);
        
Button b=new Button("Confirm");  
    b.setBounds(350,190,100,30);  
	b.setFont(font1);
	b.setBackground(Color.YELLOW);	
	b.setForeground(customColor1);
  
    b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
        
        
        String s=t1.getText();
        try
        {
            if(s.equals(m))
    {
    
//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  

String sql = "UPDATE PASSTAB SET PASSWORD = '"+pwd+"' WHERE STAFFID = '"+uname+"' "; 

       stmt.executeUpdate(sql);
       
  //System.out.println("REGISTRATION DONE SUCCESSFULLY");
    
       
       JFrame fr= new JFrame("Status");
     JLabel ll1,ll2;
        
    ll1=new JLabel("Updation Successful");  
    ll1.setBounds(100,35,400,30);  
    Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);

    		
		//set font for JLabel
		ll1.setFont(font1);
		
	ll1.setForeground(customColor1);
    ll2=new JLabel("Password Succesfully Updated");  
    ll2.setBounds(100,135,400,30);  
    	ll2.setFont(font);
		
	ll2.setForeground(customColor);
        
        fr.add(ll1);fr.add(ll2);
        fr.setSize(500,300);  
        fr.setLayout(null);  
        fr.setVisible(true);  
      con.close();
    }
            else
            {
                
       
       JFrame fr= new JFrame("Status");
     JLabel ll1,ll2;
        
    ll1=new JLabel("OTP Failed");  
    ll1.setBounds(100,35,400,30);  
    Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);

    		
		//set font for JLabel
		ll1.setFont(font1);
		
	ll1.setForeground(customColor1);
    ll2=new JLabel("OTP Doesn't Match please recheck");  
    ll2.setBounds(100,135,400,30);  
    	ll2.setFont(font);
		
	ll2.setForeground(customColor);
        
        fr.add(ll1);fr.add(ll2);
        fr.setSize(500,300);  
        fr.setLayout(null);  
        fr.setVisible(true);
            }
        }catch(Exception el){ System.out.println(el);}  

        }});    
        frl.add(t1);
        frl.add(lll1);frl.add(lll2);
         frl.add(b);
frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);
        

    }

    public void runn(String uname,String pwd)
    {
        
    JFrame frl= new JFrame("Status");
     JLabel lll1,lll2,lll3;
        
    lll1=new JLabel("Enter your MailId");  
    lll1.setBounds(20,35,300,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    		
		//set font for JLabel
		lll1.setFont(font1);
		
	lll1.setForeground(customColor1);
    lll2=new JLabel("Username");  
    lll2.setBounds(20,135,200,30);  
    	lll2.setFont(font1);
		
	lll2.setForeground(customColor1);
    lll3=new JLabel("New Password");  
    lll3.setBounds(20,200,200,30);  
    	lll3.setFont(font1);
		
	lll3.setForeground(customColor1);
    
        
        JTextField t1,t2,t3;
        t1=new JTextField("");
        t1.setBounds(350,35,100,30);
        t2=new JTextField("");
        t2.setBounds(350,135,100,30);
       t3=new JTextField("");
        t3.setBounds(350,200,100,30);
        
Button b=new Button("Send");  
    b.setBounds(350,250,100,30);  
	b.setFont(font1);
	b.setBackground(Color.YELLOW);	
	b.setForeground(customColor1); 
  
    b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
    
    String x=t1.getText();
    String u=t2.getText();
    String p=t3.getText();
Random rand=new Random();
int n=rand.nextInt(10000)+1;
String m=String.valueOf(n);
Mailer.send("rajdukist2004@gmail.com","vjlover007",x,"One Time Password For Library",m);
        
    mann(u,p,m);
        }});
        
    frl.add(t1);frl.add(t2);frl.add(t3);
        frl.add(lll1);frl.add(lll2);frl.add(lll3);
    frl.add(b);    
    frl.setSize(500,400);  
        frl.setLayout(null);  
        frl.setVisible(true);  
    
    }
        
}
public class Libapp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            JFrame f= new JFrame("");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
	f.setContentPane(new JLabel(new ImageIcon("C:\\Users\\SRIRAM\\Downloads\\1-p1.jpg")));
	f.setLayout(new FlowLayout());
	
     JTextField t1,t2;  
     JLabel l1,l2;  
    /*DropDownButtonDemo xx=new DropDownButtonDemo();
    xx.runn();*/
    l1=new JLabel("STAFFID");  
    l1.setBounds(50,75,150,30);  
    Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		l1.setFont(font);
		
	l1.setForeground(customColor);
        t1=new JTextField("");  
    t1.setBounds(50,150, 150,30);  
    l2=new JLabel("PASSWORD");  
    l2.setBounds(250,75,150,40);  
    		l2.setFont(font);
	l2.setForeground(customColor);
    t2=new JTextField("");  
    t2.setBounds(250,150, 150,30);  
    f.add(t1); f.add(t2);  
    f.add(l1); f.add(l2);  
   
     Color customColor1 = new Color(133,69,19);
    Font font1 = new Font("Courier", Font.BOLD,15);

    Button b=new Button("Login");  
    b.setBounds(250,200,150,30);  
	b.setFont(font1);
	b.setBackground(Color.RED);	
	b.setForeground(customColor1);
     b.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        b.setBackground(Color.GREEN);
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        b.setBackground(UIManager.getColor("control"));
    }
});

    b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
        Sqlexamp ex=new Sqlexamp();
String uname = t1.getText();
String pwd = t2.getText();
int c=ex.dbman(uname,pwd);
            if(c==1)
            {
            new Libapp(uname);
            }
      
    }  
    });  
    f.add(b);
JButton b1=new JButton("Forget Password");  
    b1.setBounds(250,300,200,30);
    	b1.setFont(font1);
	b1.setBackground(Color.BLUE);	
	b1.setForeground(customColor1);
   b1.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        b1.setBackground(Color.GREEN);
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        b1.setBackground(UIManager.getColor("control"));
    }
});

    f.add(b1);
    b1.addActionListener(new ActionListener(){  
@Override
public void actionPerformed(ActionEvent e){ 
String uname=t1.getText();
String pwd=t2.getText();    
forgetpassword x=new forgetpassword();
x.runn(uname, pwd);
}    
});
    f.setSize(1366,768);  
    f.setLayout(null);  
    f.setVisible(true);  
        
    }
     
    
    public Libapp(String uname) {
     
    
        
        
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                
                JFrame frame = new JFrame("");
    frame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\SRIRAM\\Downloads\\p3.jpg")));
    Color customColor1 = new Color(0,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);
         
                
               //BOOKS BUTTON
                Button b=new Button("Books");  
                b.setBounds(20,50,250,30);  
  	b.setFont(font1);
		
	b.setForeground(customColor1);
  b.setBackground(Color.BLUE);
  
    b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){ 
       bookss x=new bookss();
       x.runn();
      
      
    }  
    });  
    frame.add(b);
    b.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            b.setBackground(Color.GREEN);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            b.setBackground(UIManager.getColor("control"));
        }
    });

    //STUDENT BUTTON
    Button b1=new Button("Student");  
                b1.setBounds(20,100,250,30);  
  	b1.setFont(font1);
b1.setBackground(Color.BLUE);		
	b1.setForeground(customColor1);
  
    b1.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
      students xx=new students();
       xx.runn();
      
    }  
    }); 
    b1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            b1.setBackground(Color.GREEN);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            b1.setBackground(UIManager.getColor("control"));
        }
    });

    frame.add(b1);
    //OPAC BUTTON
    Button b2=new Button("OPAC");  
                b2.setBounds(20,150,250,30);  
  	b2.setFont(font1);
b2.setBackground(Color.BLUE);		
	b2.setForeground(customColor1);
  
    b2.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
      OPAC xx=new OPAC();
       xx.runn();
      
    }  
    });
    b2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            b2.setBackground(Color.GREEN);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            b2.setBackground(UIManager.getColor("control"));
        }
    });

    frame.add(b2);
    
    //CHECK FOR DUE BUTTON
    Button b3=new Button("Check For Due");  
                b3.setBounds(20,200,250,30);  
  	b3.setFont(font1);
b3.setBackground(Color.BLUE);		
	b3.setForeground(customColor1);
  	
    b3.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
      DUEREMAINING xx= new DUEREMAINING();
      xx.runn();
    }  
    });  
    b3.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            b3.setBackground(Color.GREEN);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            b3.setBackground(UIManager.getColor("control"));
        }
    });

    frame.add(b3);
    
    //BOOK SUBMISSION BUTTON
    Button b4=new Button("Book Submission");  
                b4.setBounds(20,250,250,30);  
  	b4.setFont(font1);
b4.setBackground(Color.BLUE);		
	b4.setForeground(customColor1);
  
    b4.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
      BOOKSUBMISSION xx=new BOOKSUBMISSION();
       xx.runn();
      
    }  
    });
    b4.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            b4.setBackground(Color.GREEN);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            b4.setBackground(UIManager.getColor("control"));
        }
    });

    frame.add(b4);
              Button b5=new Button("BookIssue");  
                b5.setBounds(20,300,250,30);  
  	b5.setFont(font1);
b5.setBackground(Color.BLUE); 
	b5.setForeground(customColor1);
  
    b5.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
      BOOKISSUE xx=new BOOKISSUE();
       xx.runn();
      
    }  
    }); 
    b5.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            b5.setBackground(Color.GREEN);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            b5.setBackground(UIManager.getColor("control"));
        }
    });

    frame.add(b5);
            Button b6=new Button("BookLocation");  
                b6.setBounds(20,350,250,30);  
  	b6.setFont(font1);
b6.setBackground(Color.BLUE);		
	b6.setForeground(customColor1);

    b6.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
      BOOKLOCATION xx=new BOOKLOCATION();
       xx.runn();
      
    }  
    });  
    b6.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            b6.setBackground(Color.GREEN);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            b6.setBackground(UIManager.getColor("control"));
        }
    });

    frame.add(b6);

    
     Button b7=new Button("Generate Book List");  
                b7.setBounds(20,400,250,30);  
  	b7.setFont(font1);
b7.setBackground(Color.BLUE);		
	b7.setForeground(customColor1);

    b7.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
      WriteDataToExcel xx=new WriteDataToExcel();
       xx.runn();
       xx.funn();
      
    }  
    }); 
    b7.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            b7.setBackground(Color.GREEN);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            b7.setBackground(UIManager.getColor("control"));
        }
    });

    frame.add(b7);

     Button b8=new Button("Search By Student");  
                b8.setBounds(20,450,250,30);  
  	b8.setFont(font1);
b8.setBackground(Color.BLUE);		
	b8.setForeground(customColor1);

    b8.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
   Table.runn();
        
        }});
    b8.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            b8.setBackground(Color.GREEN);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            b8.setBackground(UIManager.getColor("control"));
        }
    });

    
    frame.add(b8);
    Button b9=new Button("Send Return Alert");  
    b9.setBounds(20,500,250,30);  
b9.setFont(font1);
b9.setBackground(Color.BLUE);		
b9.setForeground(customColor1);

b9.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
Alerter xx=new Alerter();
xx.runn();
}});
b9.addMouseListener(new java.awt.event.MouseAdapter() {
public void mouseEntered(java.awt.event.MouseEvent evt) {
b9.setBackground(Color.GREEN);
}

public void mouseExited(java.awt.event.MouseEvent evt) {
b9.setBackground(UIManager.getColor("control"));
}
});

frame.add(b9);

    
     Button b10=new Button("Generate Due List");  
                b10.setBounds(20,550,250,30);  
  	b10.setFont(font1);
b10.setBackground(Color.BLUE);		
	b10.setForeground(customColor1);

    b10.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
        WriteDataToExcel xx=new WriteDataToExcel();
        xx.sunn();
        }});
    b10.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            b10.setBackground(Color.GREEN);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            b10.setBackground(UIManager.getColor("control"));
        }
    });

    frame.add(b10);

  
    frame.setSize(1366,768);  
                frame.setLayout(null);  
    
                frame.setVisible(true);
                
// TODO code application logic here
    }
        });
    }


}

class bookss
{
    String h1,h2,h3,h4,h5,h6,h7;
    String s8,s9; 
            
            public void runn() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
                {
                    ex.printStackTrace();
                }
        
    JFrame f= new JFrame("Books");
   
    JLabel l1,l2,l3;  
    l1=new JLabel("Add a Book");  
    l1.setBounds(50,50, 150,40);  
    l2=new JLabel("Delete a Book");  
    l2.setBounds(50,150, 150,40);  
    l3=new JLabel("Edit a book");  
    l3.setBounds(50,250, 150,40);  
    Color customColor = new Color(0,0,139);
    Font font = new Font("Courier", Font.BOLD,15);
		
		//set font for JLabel
		l1.setFont(font);
		
	l1.setForeground(customColor);
    		l2.setFont(font);
		
	l2.setForeground(customColor);
    		l3.setFont(font);
		
	l3.setForeground(customColor);
    
    f.add(l1); f.add(l2);f.add(l3);
    //add button
    Button b=new Button("Click Here");  
    b.setBounds(250,50,60,30);  
  
    b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
          
JFrame fr= new JFrame("BOOKDETAILS");
JLabel l1,l2,l3,l4,l5,l6,l7,l8;
JTextField t1,t2,t3,t4,t5,t6,t7;
        
    l1=new JLabel("BookID");  
    l1.setBounds(50,50,120,30);  
    Color customColor = new Color(0,0,128);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		l1.setFont(font);
		
	l1.setForeground(customColor);
      l2=new JLabel("BookName");  
    l2.setBounds(50,100,180,30);  
    		l2.setFont(font);
	l2.setForeground(customColor);
    l3=new JLabel("AuthorName");  
    l3.setBounds(50,150,180,30);  
    		l3.setFont(font);
	l3.setForeground(customColor);
    l4=new JLabel("PublisherName");  
    l4.setBounds(50,200,180,30);  
    		l4.setFont(font);
	l4.setForeground(customColor);
    l5=new JLabel("Edition");  
    l5.setBounds(50,250,180,30);  
    		l5.setFont(font);
	l5.setForeground(customColor);
    l6=new JLabel("Book Price");  
    l6.setBounds(50,300,200,30);  
    		l6.setFont(font);
	l6.setForeground(customColor);
    l7=new JLabel("Subject");  
    l7.setBounds(50,350,180,30);  
    		l7.setFont(font);
	l7.setForeground(customColor);
    l8=new JLabel("Reference Book");  
    l8.setBounds(50,400,180,30);  
    		l8.setFont(font);
	l8.setForeground(customColor);
    
          t1=new JTextField("");  
        t1.setBounds(350,50, 100,30);  
    t2=new JTextField("");  
    t2.setBounds(350,100, 100,30);  
    t3=new JTextField("");  
    t3.setBounds(350,150, 100,30);  
    t4=new JTextField("");  
    t4.setBounds(350,200, 100,30);  
    t5=new JTextField("");  
    t5.setBounds(350,250,100,30);  
    t6=new JTextField("");  
    t6.setBounds(350,300, 100,30);  
    t7=new JTextField("");  
    t7.setBounds(350,350,100,30);  
     CheckboxGroup cbg = new CheckboxGroup();  
        Checkbox checkBox1 = new Checkbox("Yes", cbg, false);    
        checkBox1.setBounds(350,400,50,50);    
        Checkbox checkBox2 = new Checkbox("No", cbg, false);    
        checkBox2.setBounds(400,400, 50,50);    
        fr.add(checkBox1); fr.add(checkBox2);
    fr.add(t1); fr.add(t2);fr.add(t3);fr.add(t4);fr.add(t5);fr.add(t6);fr.add(t7);  
    fr.add(l1); fr.add(l2);fr.add(l3);fr.add(l4);fr.add(l5);fr.add(l6);fr.add(l7);fr.add(l8);
        checkBox1.addItemListener(new ItemListener() {  
            public void itemStateChanged(ItemEvent e) {               
                 s8="T";
            }  
         });  
        checkBox2.addItemListener(new ItemListener() {  
            public void itemStateChanged(ItemEvent e) {               
                      s8="F";
            }  
         });  
            
    Button bbl=new Button("Done");  
    bbl.setBounds(350,450,60,30);
        
bbl.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
            String s1=t1.getText();//BookID
            String s2=t2.getText();//BookName
            String s3=t3.getText();//AuthorName
            String s4=t4.getText();//PublisherName
            String s5=t5.getText();//Edition
            String s6=t6.getText();//FineAmount
            String s7=t7.getText();//NoofCopies
            
            
            
            
            try{  
    

//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();
String sql = "INSERT INTO BOOKS " +
                   "VALUES ('"+s1+"','"+s2+"','"+s3+"','"+s4+"', '"+s5+"', '"+s6+"', '"+s7+"', '"+s8+"')";

       stmt.executeUpdate(sql);
     JFrame frl= new JFrame("Status");
     JLabel lll1,lll2;
        
    lll1=new JLabel("Registeration Successful");  
    lll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    		
		//set font for JLabel
		lll1.setFont(font1);
		
	lll1.setForeground(customColor1);
    lll2=new JLabel("BookId Succesfully Registered");  
    lll2.setBounds(100,135,400,30);  
    	lll2.setFont(font);
		
	lll2.setForeground(customColor);
        
        frl.add(lll1);frl.add(lll2);
        frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
    

  //System.out.println("REGISTRATION DONE SUCCESSFULLY");

}
catch(Exception el){ System.out.println(el);}  

           // System.out.println(s1+""+s5+""+s7);
        }});        
        fr.add(bbl);
        fr.setSize(600,600);  
        fr.setLayout(null);  
        fr.setVisible(true);  
    
      
    }  
    });  
    f.add(b);
    
    
    //delete button
    Button b1=new Button("Click Here");  
    b1.setBounds(250,150,60,30);  
  
    b1.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
    JFrame fr= new JFrame("Deletion Window");
    JTextField t1;  
    t1=new JTextField("");  
    t1.setBounds(250,50, 100,30);  
    fr.add(t1);
    JLabel l1;  
    l1=new JLabel("BookID");  
    l1.setBounds(50,50, 200,30);  
    Color customColor1 = new Color(0,0,0);
    Font font1 = new Font("Courier", Font.BOLD,30);

    l1.setFont(font1);
	l1.setForeground(customColor1);
  
    fr.add(l1);   
    Button b=new Button("Delete");  
    b.setBounds(175,175,60,30);  
  
    b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
JFrame f1= new JFrame("Deletion Window");
JLabel ll1;  
    ll1=new JLabel("Confirm Delete");  
    ll1.setBounds(50,50,300,200);  
    ll1.setFont(font1);
	ll1.setForeground(customColor1);
  
    f1.add(ll1);   
        
Button b1=new Button("YES");  
    b1.setBounds(50,250,60,30);  
    Button b2=new Button("NO");  
    b2.setBounds(250,250,60,30);  
     f1.add(b1);f1.add(b2);
b1.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
String s1=t1.getText();
            try{  
    

//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  
ResultSet rs=stmt.executeQuery("SELECT *FROM BOOKS  WHERE BOOKID ='"+s1+"' ");  

     while(rs.next())
     {
         h1=rs.getString("BOOKID");
         h2=rs.getString("BOOKNAME");
         h3=rs.getString("AUTHORNAME");
         h4=rs.getString("PUBLISHERNAME");
         h5=rs.getString("EDITIONNUMBER");
         h6=rs.getString("PRICEFINE");
         h7=rs.getString("SUBJECT");
     }
     
     String m="The Library Admin Has Deleted a book of BookID:";
     m=m+h1;
     m=m+"      BookName:";
     m=m+h2;
     m=m+"      AuthorName:";
     m=m+h3;
     m=m+"      PublisherName:";
     m=m+h4;
     m=m+"      EditionNumber:";
     m=m+h5;
     m=m+"      and its Price is:";
     m=m+h6;
     m=m+"      and it belongs to the Subject:";
     m=m+h7;
     m=m+"  category.";

     ResultSet rs1=stmt.executeQuery("SELECT *FROM BOOKLOCATION  WHERE BOOKIDENTITY ='"+s1+"' "); 
     

     while(rs1.next())
     {
         //h1=rs1.getString("BOOKID");
         h2=rs1.getString("DEPARTMENT");
         h3=rs1.getString("ROWNUMBER");
         h4=rs1.getString("COLUMNNUMBER");
     }
     m=m+"And the BookID:";
     m=m+h1;
     m=m+"      belonged to the Rack:";
     m=m+h2;
     m=m+"      RowNumber:";
     m=m+h3;
     m=m+"      ColumnNumber:";
     m=m+h4;
     
    
String sql = "DELETE FROM BOOKLOCATION " +
                   "WHERE BOOKIDENTITY ='"+s1+"' ";

       stmt.executeUpdate(sql);

     
     String sql1 = "DELETE FROM BOOKS " +
                   "WHERE BOOKID ='"+s1+"' ";

       stmt.executeUpdate(sql1);
     
        Mailer.send("rajdukist2004@gmail.com","vjlover007","sriramsudharocker@gmail.com","Book Deletion Alert",m);
       JFrame frl= new JFrame("Staus");
     JLabel lll1,lll2;
        
    lll1=new JLabel("Deletion Successful");  
    lll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    		
		//set font for JLabel
		lll1.setFont(font1);
		
	lll1.setForeground(customColor1);
    lll2=new JLabel("BookId Succesfully Deleted");  
    lll2.setBounds(100,135,400,30);  
    	lll2.setFont(font);
		
	lll2.setForeground(customColor);
        
        frl.add(lll1);frl.add(lll2);
        frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  

  System.out.println("DELETION DONE SUCCESSFULLY");

}
catch(Exception el){ System.out.println(el);}  

        }});  
b2.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
         f1.dispose();
         fr.dispose();
        }});
f1.setSize(400,400);  
        f1.setLayout(null);  
        f1.setVisible(true);  
    
    }  
    });  
        fr.add(b);
        fr.setSize(400,260);  
        fr.setLayout(null);  
        fr.setVisible(true);  
    
      
    }  
    });  
    f.add(b1);
    
    
    //edit button
    Button b2=new Button("Click Here");  
    b2.setBounds(250,250,60,30);  
  
    b2.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
        JFrame fr= new JFrame("Edit Window");
        JTextField t1;  
    t1=new JTextField("");  
    t1.setBounds(250,50, 100,30);  
    fr.add(t1);
    JLabel l1;  
    l1=new JLabel("BookID");  
    l1.setBounds(50,50, 200,30);  
  Color customColor1 = new Color(0,0,0);
    Font font1 = new Font("Courier", Font.BOLD,30);

    l1.setFont(font1);
	l1.setForeground(customColor1);
  
    fr.add(l1);   
    Button bl=new Button("Edit");  
    bl.setBounds(175,175,60,30);
    fr.add(bl);
    bl.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
  String s=t1.getText();
JFrame fl= new JFrame("BOOKDETAILS");
JLabel l1,l2,l3,l4,l5,l6,l7,l8;
JTextField tt1,tt2,tt3,tt4,tt5,tt6,tt7;
        
    l1=new JLabel("BookID");  
    l1.setBounds(50,50,120,30);  
    Color customColor = new Color(0,0,128);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		l1.setFont(font);
		
	l1.setForeground(customColor);
      l2=new JLabel("BookName");  
    l2.setBounds(50,100,180,30);  
    		l2.setFont(font);
	l2.setForeground(customColor);
    l3=new JLabel("AuthorName");  
    l3.setBounds(50,150,180,30);  
    		l3.setFont(font);
	l3.setForeground(customColor);
    l4=new JLabel("PublisherName");  
    l4.setBounds(50,200,180,30);  
    		l4.setFont(font);
	l4.setForeground(customColor);
    l5=new JLabel("Edition");  
    l5.setBounds(50,250,180,30);  
    		l5.setFont(font);
	l5.setForeground(customColor);
    l6=new JLabel("Book Price");  
    l6.setBounds(50,300,200,30);  
    		l6.setFont(font);
	l6.setForeground(customColor);
    l7=new JLabel("Subject");  
    l7.setBounds(50,350,180,30);  
    		l7.setFont(font);
	l7.setForeground(customColor);
    l8=new JLabel("Reference Book");  
    l8.setBounds(50,400,180,30);  
    		l8.setFont(font);
	l8.setForeground(customColor);
    
          tt1=new JTextField();
          tt1.setText(s);
        tt1.setBounds(350,50, 100,30);  
    tt2=new JTextField("");  
    tt2.setBounds(350,100, 100,30);  
    tt3=new JTextField("");  
    tt3.setBounds(350,150, 100,30);  
    tt4=new JTextField("");  
    tt4.setBounds(350,200, 100,30);  
    tt5=new JTextField("");  
    tt5.setBounds(350,250,100,30);  
    tt6=new JTextField("");  
    tt6.setBounds(350,300, 100,30);  
    tt7=new JTextField("");  
    tt7.setBounds(350,350,100,30);  
     CheckboxGroup cbg = new CheckboxGroup();  
        Checkbox checkBox1 = new Checkbox("Yes", cbg, false);    
        checkBox1.setBounds(350,400,50,50);    
        Checkbox checkBox2 = new Checkbox("No", cbg, false);    
        checkBox2.setBounds(400,400, 50,50);    
        fl.add(checkBox1); fl.add(checkBox2);
        checkBox1.addItemListener(new ItemListener() {  
            public void itemStateChanged(ItemEvent e) {               
                 s9="T";
            }  
         });  
        checkBox2.addItemListener(new ItemListener() {  
            public void itemStateChanged(ItemEvent e) {               
                      s9="F";
            }  
         });  
    
    fl.add(tt1); fl.add(tt2);fl.add(tt3);fl.add(tt4);fl.add(tt5);fl.add(tt6);fl.add(tt7);  
    fl.add(l1); fl.add(l2);fl.add(l3);fl.add(l4);fl.add(l5);fl.add(l6);fl.add(l7);fl.add(l8);
    Button bbl=new Button("Done");  
    bbl.setBounds(350,450,60,30);
        
bbl.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
String s1=tt1.getText();//BookID
            String s2=tt2.getText();//BookName
            String s3=tt3.getText();//AuthorName
            String s4=tt4.getText();//PublisherName
            String s5=tt5.getText();//Edition
            String s6=tt6.getText();//FineAmount
            String s7=tt7.getText();//NoofCopies
            try{  
    

//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  
String sql1 = "DELETE FROM BOOKS " +
                   "WHERE BOOKID ='"+s1+"' ";

       stmt.executeUpdate(sql1);
String sql = "INSERT INTO BOOKS " +
                   "VALUES ('"+s1+"','"+s2+"','"+s3+"','"+s4+"', '"+s5+"', '"+s6+"', '"+s7+"', '"+s9+"')";
      stmt.executeUpdate(sql);
JFrame frl= new JFrame("Status");
     JLabel lll1,lll2;
        
    lll1=new JLabel("Registeration Successful");  
    lll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    		
		//set font for JLabel
		lll1.setFont(font1);
		
	lll1.setForeground(customColor1);
    lll2=new JLabel("BookId Succesfully Registered");  
    lll2.setBounds(100,135,400,30);  
    	lll2.setFont(font);
		
	lll2.setForeground(customColor);
        
        frl.add(lll1);frl.add(lll2);
        frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
    
       
  System.out.println("REGISTRATION DONE SUCCESSFULLY");

}
catch(Exception el){ System.out.println(el);}  
//System.out.println(s1+""+s2+""+s3+""+s4+""+s5+""+s6+""+s7+"_______haiii");
        }
        });
        fl.add(bbl);
        fl.setSize(600,600);  
        fl.setLayout(null);  
        fl.setVisible(true);  
    
      
            
        }
        });
        fr.setSize(400,400);  
        fr.setLayout(null);  
        fr.setVisible(true);  
    
      
    }  
    });  
    f.add(b2);
    f.setSize(400,400);  
    f.setLayout(null);  
    f.setVisible(true);  
    
}
    }
    
class students
{
            public void runn() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
        
    JFrame f= new JFrame("Students");
   
    JLabel l1,l2,l3;  
    l1=new JLabel("Add a Student");  
    l1.setBounds(50,50, 150,40);  
    l2=new JLabel("Delete a Student");  
    l2.setBounds(50,150, 150,40);  
    l3=new JLabel("Edit a Student");  
    l3.setBounds(50,250, 150,40);  
    Color customColor = new Color(0,0,139);
    Font font = new Font("Courier", Font.BOLD,15);
		
		//set font for JLabel
		l1.setFont(font);
		
	l1.setForeground(customColor);
    		l2.setFont(font);
		
	l2.setForeground(customColor);
    		l3.setFont(font);
		
	l3.setForeground(customColor);
    
    f.add(l1); f.add(l2);f.add(l3);
    //add button
    Button b=new Button("Click Here");  
    b.setBounds(250,50,60,30);  
  
    b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
          
JFrame fr= new JFrame("STUDENTDETAILS");
JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9;
    l1=new JLabel("StudentID");  
    l1.setBounds(50,50,120,30);  
    Color customColor = new Color(0,0,128);
    Font font = new Font("Courier", Font.BOLD,20);
		//set font for JLabel
		l1.setFont(font);
	l1.setForeground(customColor);
      l2=new JLabel("StudentName");  
    l2.setBounds(50,100,180,30);  
    		l2.setFont(font);
	l2.setForeground(customColor);
    l3=new JLabel("Branch");  
    l3.setBounds(50,150,180,30);  
    		l3.setFont(font);
	l3.setForeground(customColor);
    l4=new JLabel("Semester");  
    l4.setBounds(50,200,180,30);  
    		l4.setFont(font);
	l4.setForeground(customColor);
    l5=new JLabel("Year");  
    l5.setBounds(50,250,180,30);  
    		l5.setFont(font);
	l5.setForeground(customColor);
    l6=new JLabel("Due Remaining");  
    l6.setBounds(50,300,200,30);  
    		l6.setFont(font);
	l6.setForeground(customColor);
    l7=new JLabel("BooksHeld");  
    l7.setBounds(50,350,180,30);  
    		l7.setFont(font);
	l7.setForeground(customColor);
    l8=new JLabel("MailID");  
    l8.setBounds(50,400,180,30);  
    		l8.setFont(font);
	l8.setForeground(customColor);
    l9=new JLabel("Phone Number");  
    l9.setBounds(50,450,180,30);  
    		l9.setFont(font);
	l9.setForeground(customColor);
 
        t1=new JTextField("");  
        t1.setBounds(350,50, 100,30);  
    t2=new JTextField("");  
    t2.setBounds(350,100, 100,30);  
    t3=new JTextField("");  
    t3.setBounds(350,150, 1,30);
    String department[]={"","Centre for Disaster Mitigation and Management",
"Centre for Environmental Studies","Centre for Water Resources","Department of Agricultural Engineering","Department of Civil Engineering",
"Department of Geoinformatics Engineering","Division of Soil Mechanics and Foundation Management","Division of Transportation Engineering",
"Institution of Ocean Management","Institute Of Remote Sensing","Structural Engineering Division","Department of Industrial Engineering",
"Department of Manufacturing Engineering","Department of Material Science and Engineering","Department of Mechanical Engineering",
"Department of Mining Engineering","Department of Printing Technology","Institute of Energy Studies","Manufacturing System Management",
"Department of Electrical Elcetronics Engineering","Department of Biomedical Engineering","Department of Computer Science and Engineering","Department of Information Technology",
"Department of Electronics And Communication Engineering","Ramanujan Computing Centre","Department of Management Studies"
                ,"Department of Chemistry","Department of English","Department of Geology","Department of Mathematics","Department of Media","Department of Medical Physics",
                "Department of Physics"
        };
        JComboBox cb=new JComboBox(department);
        cb.setBounds(350,150, 200,30);
        
        cb.addItemListener(new ItemListener(){  
            @Override
            
            public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange()==ItemEvent.SELECTED )
            {
             String x=(String) cb.getItemAt(cb.getSelectedIndex());
             t3.setText(x);
            }
            }
        });
         
    t4=new JTextField("");  
    t4.setBounds(350,200, 100,30);  
    t5=new JTextField("");  
    t5.setBounds(350,250,100,30);  
    t6=new JTextField("");  
    t6.setBounds(350,300, 100,30);  
    t7=new JTextField("");  
    t7.setBounds(350,350,100,30);  
    t8=new JTextField("");  
    t8.setBounds(350,400,100,30);  
    t9=new JTextField("");  
    t9.setBounds(350,450,100,30);  
    
    fr.add(t3);fr.add(t1); fr.add(t2);fr.add(cb);fr.add(t4);fr.add(t5);fr.add(t6);fr.add(t7);fr.add(t8);fr.add(t9);  
    fr.add(l1); fr.add(l2);fr.add(l3);fr.add(l4);fr.add(l5);fr.add(l6);fr.add(l7);fr.add(l8);fr.add(l9);
        
    Button bbl=new Button("Done");  
    bbl.setBounds(350,500,60,30);
        
bbl.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
            String s1=t1.getText();//BookID
            String s2=t2.getText();//BookName
           
            String s3=t3.getText();
            
            String s4=t4.getText();//PublisherName
         
            String s5=t5.getText();//Edition
            String s6=t6.getText();//FineAmount
            String s7=t7.getText();//NoofCopies
            String s8=t8.getText();
            String s9=t9.getText();
            try{  
Class.forName("oracle.jdbc.driver.OracleDriver");  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
Statement stmt=con.createStatement();  
String sql = "INSERT INTO STUDENTDETAILS " +
                   "VALUES ('"+s1+"','"+s2+"','"+s3+"','"+s4+"', '"+s5+"', '"+s6+"', '"+s7+"','"+s8+"','"+s9+"')";
      stmt.executeUpdate(sql);
       JFrame frl= new JFrame("Status");
     JLabel lll1,lll2;
    lll1=new JLabel("Registeration Successful");  
    lll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);
		lll1.setFont(font1);
	lll1.setForeground(customColor1);
    lll2=new JLabel("StudentId Succesfully Registered");  
    lll2.setBounds(100,135,400,30);  
    	lll2.setFont(font);
	lll2.setForeground(customColor);
        frl.add(lll1);frl.add(lll2);
        frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
  System.out.println("REGISTRATION DONE SUCCESSFULLY");
}
catch(Exception el){ System.out.println(el);}  
    }});        
        fr.add(bbl);
        fr.setSize(600,600);  
        fr.setLayout(null);  
        fr.setVisible(true);  
     }  
    });  
    f.add(b);
    
    
    //delete button
    Button b1=new Button("Click Here");  
    b1.setBounds(250,150,60,30);  
  
    b1.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
    JFrame fr= new JFrame("Deletion Window");
    JTextField t1;  
    t1=new JTextField("");  
    t1.setBounds(250,50, 100,30);  
    fr.add(t1);
    JLabel l1;  
    l1=new JLabel("StudentID");  
    l1.setBounds(50,50, 200,30);  
  Color customColor1 = new Color(0,0,0);
    Font font1 = new Font("Courier", Font.BOLD,30);

    l1.setFont(font1);
	l1.setForeground(customColor1);
  
    fr.add(l1);   
    Button b=new Button("Delete");  
    b.setBounds(175,175,60,30);  
  
    b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
JFrame f1= new JFrame("Deletion Window");
JLabel ll1;  
    ll1=new JLabel("Confirm Delete");  
    ll1.setBounds(50,50,300,200);  
    ll1.setFont(font1);
	ll1.setForeground(customColor1);
  
    f1.add(ll1);   
        
Button b1=new Button("YES");  
    b1.setBounds(50,250,60,30);  
    Button b2=new Button("NO");  
    b2.setBounds(250,250,60,30);  
        b1.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
String s1=t1.getText();
            try{  
    

//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  
String sql1 = "DELETE FROM STUDENTDETAILS " +
                   "WHERE REGISTERNUMBER ='"+s1+"' ";

       stmt.executeUpdate(sql1);
       
  System.out.println("DELETION DONE SUCCESSFULLY");
         JFrame frl= new JFrame("Staus");
     JLabel lll1,lll2;
        
    lll1=new JLabel("Deletion Successful");  
    lll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    		
		//set font for JLabel
		lll1.setFont(font1);
		
	lll1.setForeground(customColor1);
    lll2=new JLabel("StudentId Succesfully Deleted");  
    lll2.setBounds(100,135,400,30);  
    	lll2.setFont(font);
		
	lll2.setForeground(customColor);
        
        frl.add(lll1);frl.add(lll2);
        frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  

            }
catch(Exception el){ System.out.println(el);}  
            
        }});
b2.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
         f1.dispose();
         fr.dispose();
                JFrame frl= new JFrame("Staus");
     JLabel lll1,lll2;
        
    lll1=new JLabel("Deletion Not Successful");  
    lll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    		
		//set font for JLabel
		lll1.setFont(font1);
		
	lll1.setForeground(customColor1);
    lll2=new JLabel("StudentId Cannot be found");  
    lll2.setBounds(100,135,400,30);  
    	lll2.setFont(font);
		
	lll2.setForeground(customColor);
        
        frl.add(lll1);frl.add(lll2);
        frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  

        }});
        
    
    f1.add(b1);f1.add(b2);

     f1.setSize(400,400);  
        f1.setLayout(null);  
        f1.setVisible(true);  
    
    }  
    });  
        fr.add(b);
        fr.setSize(400,260);  
        fr.setLayout(null);  
        fr.setVisible(true);  
    
      
    }  
    });  
    f.add(b1);
    
    
    //edit button
    Button b2=new Button("Click Here");  
    b2.setBounds(250,250,60,30);  
  
    b2.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
        JFrame fr= new JFrame("Edit Window");
        JTextField t1;  
    t1=new JTextField("");  
    t1.setBounds(250,50, 100,30);  
    fr.add(t1);
    JLabel l1;  
    l1=new JLabel("StudentID");  
    l1.setBounds(50,50, 200,30);  
  Color customColor1 = new Color(0,0,0);
    Font font1 = new Font("Courier", Font.BOLD,30);

    l1.setFont(font1);
	l1.setForeground(customColor1);
  
    fr.add(l1);   
    Button bl=new Button("Edit");  
    bl.setBounds(175,175,60,30);
    fr.add(bl);
    bl.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
  String s=t1.getText();
JFrame fl= new JFrame("STUDENTDETAILS");
JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
JTextField tt1,tt2,tt3,tt4,tt5,tt6,tt7,tt8,tt9;
        
    l1=new JLabel("StudentID");  
    l1.setBounds(50,50,120,30);  
    Color customColor = new Color(0,0,128);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		l1.setFont(font);
		
	l1.setForeground(customColor);
      l2=new JLabel("StudentName");  
    l2.setBounds(50,100,180,30);  
    		l2.setFont(font);
	l2.setForeground(customColor);
    l3=new JLabel("Branch");  
    l3.setBounds(50,150,180,30);  
    		l3.setFont(font);
	l3.setForeground(customColor);
    l4=new JLabel("Year");  
    l4.setBounds(50,200,180,30);  
    		l4.setFont(font);
	l4.setForeground(customColor);
    l5=new JLabel("Semester");  
    l5.setBounds(50,250,180,30);  
    		l5.setFont(font);
	l5.setForeground(customColor);
    l6=new JLabel("DueRemaining");  
    l6.setBounds(50,300,200,30);  
    		l6.setFont(font);
	l6.setForeground(customColor);
    l7=new JLabel("BooksHeld");  
    l7.setBounds(50,350,180,30);  
    		l7.setFont(font);
	l7.setForeground(customColor);
    l8=new JLabel("MailID");  
    l8.setBounds(50,400,180,30);  
    		l8.setFont(font);
	l8.setForeground(customColor);
        l9=new JLabel("PhoneNumber");  
    l9.setBounds(50,450,180,30);  
    		l9.setFont(font);
	l9.setForeground(customColor);
          tt1=new JTextField();
          tt1.setText(s);
        tt1.setBounds(350,50, 100,30);  
    tt2=new JTextField("");  
    tt2.setBounds(350,100, 100,30);  
    tt3=new JTextField("");  
    tt3.setBounds(350,150, 1,30);  
    String department[]={"","Centre for Disaster Mitigation and Management",
"Centre for Environmental Studies","Centre for Water Resources","Department of Agricultural Engineering","Department of Civil Engineering",
"Department of Geoinformatics Engineering","Division of Soil Mechanics and Foundation Management","Division of Transportation Engineering",
"Institution of Ocean Management","Institute Of Remote Sensing","Structural Engineering Division","Department of Industrial Engineering",
"Department of Manufacturing Engineering","Department of Material Science and Engineering","Department of Mechanical Engineering",
"Department of Mining Engineering","Department of Printing Technology","Institute of Energy Studies","Manufacturing System Management",
"Department of Electrical Elcetronics Engineering","Department of Biomedical Engineering","Department of Computer Science and Engineering","Department of Information Technology",
"Department of Electronics And Communication Engineering","Ramanujan Computing Centre","Department of Management Studies"
                ,"Department of Chemistry","Department of English","Department of Geology","Department of Mathematics","Department of Media","Department of Medical Physics",
                "Department of Physics"
        };
        JComboBox cb=new JComboBox(department);
        cb.setBounds(350,150, 200,30);
        
        cb.addItemListener(new ItemListener(){  
            @Override
            
            public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange()==ItemEvent.SELECTED )
            {
             String x=(String) cb.getItemAt(cb.getSelectedIndex());
             tt3.setText(x);
            }
            }
        });
    tt4=new JTextField("");  
    tt4.setBounds(350,200, 100,30);  
    tt5=new JTextField("");  
    tt5.setBounds(350,250,100,30);  
    tt6=new JTextField("");  
    tt6.setBounds(350,300, 100,30);  
    tt7=new JTextField("");  
    tt7.setBounds(350,350,100,30);  
    tt8=new JTextField("");  
    tt8.setBounds(350,400,100,30);  
    tt9=new JTextField("");  
    tt9.setBounds(350,450,100,30);  
    
    fl.add(cb);fl.add(tt1); fl.add(tt2);fl.add(tt3);fl.add(tt4);fl.add(tt5);fl.add(tt6);fl.add(tt7);fl.add(tt8);fl.add(tt9);  
    fl.add(l1); fl.add(l2);fl.add(l3);fl.add(l4);fl.add(l5);fl.add(l6);fl.add(l7);fl.add(l8);fl.add(l9);
    Button bbl=new Button("Done");  
    bbl.setBounds(350,500,60,30);
        
bbl.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
String s1=tt1.getText();
String s2=tt2.getText();
String s3=tt3.getText();
String s4=tt4.getText();
String s5=tt5.getText();
String s6=tt6.getText();
String s7=tt7.getText();
String s8=tt8.getText();
String s9=tt9.getText();
try{  
    

//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  
String sql1 = "DELETE FROM STUDENTDETAILS " +
                   "WHERE REGISTERNUMBER ='"+s1+"' ";

       stmt.executeUpdate(sql1);
String sql = "INSERT INTO STUDENTDETAILS " +
                   "VALUES ('"+s1+"','"+s2+"','"+s3+"','"+s4+"', '"+s5+"', '"+s6+"', '"+s7+"', '"+s8+"', '"+s9+"')";

       stmt.executeUpdate(sql);
       
  System.out.println("REGISTRATION DONE SUCCESSFULLY");
       
       JFrame frl= new JFrame("Status");
     JLabel lll1,lll2;
        
    lll1=new JLabel("Registeration Successful");  
    lll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    		
		//set font for JLabel
		lll1.setFont(font1);
		
	lll1.setForeground(customColor1);
    lll2=new JLabel("StudentId Succesfully Registered");  
    lll2.setBounds(100,135,400,30);  
    	lll2.setFont(font);
		
	lll2.setForeground(customColor);
        
        frl.add(lll1);frl.add(lll2);
        frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
    
}
catch(Exception el){ System.out.println(el);}
System.out.println(s1+""+s2+""+s3+""+s4+""+s5+""+s6+""+s7+"_______haiii");
        }
        });
        fl.add(bbl);
        fl.setSize(600,600);  
        fl.setLayout(null);  
        fl.setVisible(true);  
    
      
            
        }
        });
        fr.setSize(400,400);  
        fr.setLayout(null);  
        fr.setVisible(true);  
    
      
    }  
    });  
    f.add(b2);
    f.setSize(400,400);  
    f.setLayout(null);  
    f.setVisible(true);  
    
}
    }

class OPAC
{
    String res1,res2,res3,res4;
    public void runn()
    {
        JFrame fr= new JFrame("OPAC Window");
JLabel l1,l2,l3,l4,l5,l6;
JTextField t1,t2,t3,t4;
        
    l1=new JLabel("No Field should be left blank");  
    l1.setBounds(175,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,30);
		
		//set font for JLabel
		l1.setFont(font1);
		
	l1.setForeground(customColor1);
      l2=new JLabel("Search By");  
    l2.setBounds(250,70,250,60);  
    		l2.setFont(font);
	l2.setForeground(customColor);
    l3=new JLabel("BookTitle");  
    l3.setBounds(50,150,250,60);  
    		l3.setFont(font);
	l3.setForeground(customColor);
    l4=new JLabel("AuthorName");  
    l4.setBounds(50,200,250,60);  
    		l4.setFont(font);
	l4.setForeground(customColor);
    l5=new JLabel("PublisherName");  
    l5.setBounds(50,250,250,60);  
    		l5.setFont(font);
	l5.setForeground(customColor);
l6=new JLabel("Edition");  
    l6.setBounds(50,300,250,60);  
    		l6.setFont(font);
	l6.setForeground(customColor);

    
          t1=new JTextField("");  
        t1.setBounds(450,170, 100,30);  
    t2=new JTextField("");  
    t2.setBounds(450,220, 100,30);  
    t3=new JTextField("");  
    t3.setBounds(450,270, 100,30);  
    t4=new JTextField("");  
    t4.setBounds(450,320, 100,30);  
    
    fr.add(t1); fr.add(t2);fr.add(t3);fr.add(t4);  
    fr.add(l1); fr.add(l2);fr.add(l3);fr.add(l4);fr.add(l5);fr.add(l6);
        
    Button b=new Button("DONE");  
    b.setBounds(300,400,120,30);
        
b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
     String s1=t1.getText();
     String s2=t2.getText();
     String s3=t3.getText();
     String s4=t4.getText();
     
     try{  
//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  


ResultSet rs=stmt.executeQuery("select bl.department,bl.rownumber,bl.columnnumber from booklocation bl join books b on b.bookid=bl.bookidentity WHERE b.bookname='"+s1+"'" + " or b.authorname='"+s2+"' " + "or b.publishername='"+s3+"'" +"or b.editionnumber='"+s4+"'");  

    
     while(rs.next())
     {
        res1=rs.getString("department");
        res2=rs.getString("rownumber");
        res3=rs.getString("columnnumber");
      }
     if(res1==null)
     {
         JFrame frl= new JFrame("NotFoundWindow");
     JLabel lll1,lll2;
        
    lll1=new JLabel("OPAC Alert");  
    lll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    		
		//set font for JLabel
		lll1.setFont(font1);
		
	lll1.setForeground(customColor1);
    lll2=new JLabel("BookId Search Not Found");  
    lll2.setBounds(100,135,400,30);  
    	lll2.setFont(font);
		
	lll2.setForeground(customColor);
        
        frl.add(lll1);frl.add(lll2);
    frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
    frl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.dispose();
     
    
     }
    con.close();  
 
}
    catch(Exception el){ 
        System.out.println(el);
    }  
     
     if(res1!=null)
     {
     
     JFrame f= new JFrame("BookFound");

        JLabel ll2,ll3,ll4,ll5;
        JTextField tt1,tt2,tt3,tt4;

        
        Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
	ll2=new JLabel("BOOKLOCATION");  
    ll2.setBounds(120,30,180,30);  
    		ll2.setFont(font);
	ll2.setForeground(customColor);
    ll3=new JLabel("Department");  
    ll3.setBounds(50,80,180,30);  
    		ll3.setFont(font);
	ll3.setForeground(customColor);
    ll4=new JLabel("Row Number");  
    ll4.setBounds(50,140,180,30);  
    		ll4.setFont(font);
	ll4.setForeground(customColor);
    ll5=new JLabel("Column Number");  
    ll5.setBounds(50,200,180,30);  
    		ll5.setFont(font);
	ll5.setForeground(customColor);

        
          tt1=new JTextField("");  
        tt1.setBounds(250,80, 100,30);  
    tt2=new JTextField("");  
    tt2.setBounds(250,140,100,30);  
    tt3=new JTextField("");  
    tt3.setBounds(250,200, 100,30); 
    
    tt1.setText(res1);
    tt2.setText(res2);
    tt3.setText(res3);
    f.add(tt1); f.add(tt2);f.add(tt3);
    f.add(ll2); f.add(ll3);f.add(ll4);f.add(ll5); 
    f.setSize(400,330);  
        f.setLayout(null);  
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.dispose();
     }
        }});
        fr.add(b);
        fr.setSize(600,600);  
        fr.setLayout(null);  
        fr.setVisible(true);  
    
    }
}
class BOOKSUBMISSION
{
    String res,res1;
    double m;
    
    public void runn()
    {
        JFrame fr= new JFrame("BookSubmissionWindow");
           JLabel l1,l2,l3;
            JTextField t1,t2;
        
    l1=new JLabel("BOOKSUBMISSION");  
    l1.setBounds(100,35,200,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		l1.setFont(font1);
		
	l1.setForeground(customColor1);
      l2=new JLabel("BookID");  
    l2.setBounds(50,100,200,60);  
    		l2.setFont(font);
	l2.setForeground(customColor);
    l3=new JLabel("StudentID");  
    l3.setBounds(50,200,200,60);  
    		l3.setFont(font);
	l3.setForeground(customColor);
    
          t1=new JTextField("");  
        t1.setBounds(300,110, 100,30);  
    t2=new JTextField("");  
    t2.setBounds(300,210, 100,30);  
    fr.add(t1); fr.add(t2);  
    fr.add(l1); fr.add(l2);fr.add(l3);
        
    Button b=new Button("Submit");  
    b.setBounds(150,260,120,30);
        
b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
          	fr.dispose();
String s1=t2.getText();
String s2=t1.getText();
        
JFrame f= new JFrame("DueClearanceWindow");

        JLabel ll2,ll3,ll4,ll5;
        JTextField tt1,tt2;

        
        Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
	ll2=new JLabel("DUEREMAINING");  
    ll2.setBounds(120,30,180,30);  
    		ll2.setFont(font);
	ll2.setForeground(customColor);
    ll3=new JLabel("Fine Amount");  
    ll3.setBounds(50,150,180,30);  
    		ll3.setFont(font);
	ll3.setForeground(customColor);
    ll4=new JLabel("Rs.");  
    ll4.setBounds(200,100,80,30);  
    		ll4.setFont(font);
	ll4.setForeground(customColor);
    ll5=new JLabel("Ps.");  
    ll5.setBounds(250,100,80,30);  
    		ll5.setFont(font);
	ll5.setForeground(customColor);

        
          tt1=new JTextField("");  
        tt1.setBounds(200,150,50,30);  
    tt2=new JTextField("");  
    tt2.setBounds(250,150,50,30); 
    Datesubtractor xx=new Datesubtractor();
    xx.runn(s1);
   //DB EXTRACTION
    try{
  	
//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  


ResultSet rs=stmt.executeQuery("select * from studentdetails where registernumber='"+s1+"'");  

    
     while(rs.next())
     {
        res=rs.getString("duesremaining");
  
        System.out.println(res);
     }
     
     if(res==null)
     {
    	 
    JFrame frl= new JFrame("NotFoundWindow");
     JLabel lll1,lll2;
        
    lll1=new JLabel("BOOK CANNOT BE SUBMITTED");  
    lll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    		
		//set font for JLabel
		lll1.setFont(font1);
		
	lll1.setForeground(customColor1);
	lll2=new JLabel("");
	if(s1.equals("")&&s2.equals(""))
	{
	  lll2.setText("Credentials left Blank");
	
    }
	else if(s1.equals(""))
	{
      lll2.setText("StudentId Not Found");
	
    }
	else if(s2.equals(""))
	{
		lll2.setText("BookId Not Found");
			
	}
	
	lll2.setBounds(100,135,400,30);  
	lll2.setFont(font);
	
lll2.setForeground(customColor);

        frl.add(lll1);frl.add(lll2);
    frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
    
    
     }
     
       
       int len=res.length();
    int k=res.indexOf('.');
       String k1=res.substring(0,k);
        String k2=res.substring(k+1,len);
        tt1.setText(k1);
        tt2.setText(k2);
   
    con.close();  
 
}
    catch(Exception el){ 
        System.out.println(el);
    }  
   
    
    f.add(tt1); f.add(tt2);
    f.add(ll2); f.add(ll3);f.add(ll4);f.add(ll5); 
Button bb=new Button("Clear Due");  
    bb.setBounds(150,260,120,30);
        
bb.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
try{
	f.dispose();
//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement(); 
            String sql1 = "DELETE FROM TRANSACTION " +
                   "WHERE BOOKTAKEN ='"+s2+"' " + "and STUDENTREGISTERNUMBER = '"+s1+"'";

       stmt.executeUpdate(sql1); 
  //System.out.println("DELETION DONE SUCCESSFULLY");
con.close();
}catch(Exception el){System.out.println(el);}

UPDATION x=new UPDATION();
x.runs(s1);
           JFrame frl= new JFrame("");
     JLabel lll1,lll2;
        
    lll1=new JLabel("Success Message");  
    lll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    		
		//set font for JLabel
		lll1.setFont(font1);
		
	lll1.setForeground(customColor1);
    lll2=new JLabel("Due Successfully Cleared");  
    lll2.setBounds(100,135,400,30);  
    	lll2.setFont(font);
		
	lll2.setForeground(customColor);
        
        frl.add(lll1);frl.add(lll2);
    frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
    
BooksInhand xx=new BooksInhand();
xx.funn(s1);
        
   
        }});
    f.add(bb);
    f.setSize(400,400);  
        f.setLayout(null);  
        f.setVisible(true);  

        }});
        fr.add(b);
        fr.setSize(500,400);  
        fr.setLayout(null);  
        fr.setVisible(true);  
     
    }
}
class DUEREMAINING
{
    String res;
    double m;
    public void runn()
    {
        JFrame fr= new JFrame("Due Window");
           JLabel l1,l2;
            JTextField t1;
        
    l1=new JLabel("DUECLEARANCE");  
    l1.setBounds(100,35,200,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		l1.setFont(font1);
		
	l1.setForeground(customColor1);
      l2=new JLabel("StudentID");  
    l2.setBounds(50,140,200,60);  
    		l2.setFont(font);
	l2.setForeground(customColor);
    
          t1=new JTextField("");  
        t1.setBounds(300,150, 100,30);  
    fr.add(t1);   
    fr.add(l1); fr.add(l2);
       
    Button b=new Button("Check For Due");  
    b.setBounds(150,260,150,30);
        
b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
        fr.dispose();
      String s1=t1.getText();
        JFrame f= new JFrame("DueClearanceWindow");

        JLabel ll2,ll3,ll4,ll5;
        JTextField tt1,tt2;

        
        Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
	ll2=new JLabel("DUEREMAINING");  
    ll2.setBounds(120,30,180,30);  
    		ll2.setFont(font);
	ll2.setForeground(customColor);
    ll3=new JLabel("Fine Amount");  
    ll3.setBounds(50,150,180,30);  
    		ll3.setFont(font);
	ll3.setForeground(customColor);
    ll4=new JLabel("Rs.");  
    ll4.setBounds(200,100,80,30);  
    		ll4.setFont(font);
	ll4.setForeground(customColor);
    ll5=new JLabel("Ps.");  
    ll5.setBounds(250,100,80,30);  
    		ll5.setFont(font);
	ll5.setForeground(customColor);

        
          tt1=new JTextField("");  
        tt1.setBounds(200,150,50,30);  
    tt2=new JTextField("");  
    tt2.setBounds(250,150,50,30);
     
  //Datesubtractor xx=new Datesubtractor();
  // xx.runn(s1);
    //DB EXTRACTION
    try{  
//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  


ResultSet rs=stmt.executeQuery("select duesremaining from studentdetails where registernumber='"+s1+"'");  

    
     while(rs.next())
     {
        res=rs.getString("duesremaining");
      }
     if(res==null)
     {
         JFrame frl= new JFrame("NotFoundWindow");
     JLabel lll1,lll2;
        
    lll1=new JLabel("DUE CANNOT BE CLEARED");  
    lll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    		
		//set font for JLabel
		lll1.setFont(font1);
		
	lll1.setForeground(customColor1);
    lll2=new JLabel("StudentId Not Found");  
    lll2.setBounds(100,135,400,30);  
    	lll2.setFont(font);
		
	lll2.setForeground(customColor);
        
        frl.add(lll1);frl.add(lll2);
    frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
    
    
     }
    con.close();  
 
}
    catch(Exception el){ 
        System.out.println(el);
    }  
      //   System.out.println(res);
       int len=res.length();
    int k=res.indexOf('.');
       String k1=res.substring(0,k);
        String k2=res.substring(k+1,len);
        tt1.setText(k1);
        tt2.setText(k2);
    f.add(tt1); f.add(tt2);
    f.add(ll2); f.add(ll3);f.add(ll4);f.add(ll5); 
    Button bb=new Button("Clear Due");  
    bb.setBounds(150,260,120,30);
        
bb.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
        	f.dispose();
UPDATION xx=new UPDATION();
xx.runs(s1);
          JFrame frl= new JFrame("");
     JLabel lll1,lll2;
        
    lll1=new JLabel("Success Message");  
    lll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    		
		//set font for JLabel
		lll1.setFont(font1);
		
	lll1.setForeground(customColor1);
    lll2=new JLabel("Due Successfully Cleared");  
    lll2.setBounds(100,135,400,30);  
    	lll2.setFont(font);
		
	lll2.setForeground(customColor);
        
        frl.add(lll1);frl.add(lll2);
    frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
    
   
        }});
    f.add(bb);
    f.setSize(400,400);  
        f.setLayout(null);  
        f.setVisible(true);  

        }});
        fr.add(b);
        fr.setSize(600,400);  
        fr.setLayout(null);  
        fr.setVisible(true);  
     
        
    }
}

class BOOKISSUE
{
    int x;
    public void runn()
    {
     
        JFrame fr= new JFrame("BookIssueWindow");
           JLabel l1,l2,l3,l4,l5;
            JTextField t1,t2,t3,t4;
        
    l1=new JLabel("BOOKISSUE");  
    l1.setBounds(100,35,200,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		l1.setFont(font1);
		
	l1.setForeground(customColor1);
      l2=new JLabel("BookID");  
    l2.setBounds(50,100,200,60);  
    		l2.setFont(font);
	l2.setForeground(customColor);
    l3=new JLabel("StudentID");  
    l3.setBounds(50,150,200,60);  
    		l3.setFont(font);
	l3.setForeground(customColor);
    l4=new JLabel("Issue Date");  
    l4.setBounds(50,200,200,60);  
    		l4.setFont(font);
	l4.setForeground(customColor);
    l5=new JLabel("Return Date");  
    l5.setBounds(50,250,200,60);  
    		l5.setFont(font);
	l5.setForeground(customColor);
    
          t1=new JTextField("");  
        t1.setBounds(300,110, 100,30);  
    t2=new JTextField("");  
    t2.setBounds(300,160, 100,30);  
    t3=new JTextField("");  
    t3.setBounds(300,210, 100,30);  
    t4=new JTextField("");  
    t4.setBounds(300,270, 100,30);  
Date date=new Date();
Date date1=new Date();
DateFormat currentDate=DateFormat.getDateInstance();
String p=currentDate.format(date);

Calendar calendar=new GregorianCalendar();
calendar.setTime(date);
calendar.add(Calendar.DATE,90);
date1=calendar.getTime();
String q=currentDate.format(date1);
    
    t3.setText(p);
    t4.setText(q);
    
    fr.add(t1); fr.add(t2);fr.add(t3);fr.add(t4);  
    fr.add(l1); fr.add(l2);fr.add(l3);fr.add(l4);fr.add(l5);
        
    Button b=new Button("ISSUE");  
    b.setBounds(150,320,120,30);
        
b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
String s1=t1.getText();
String s2=t2.getText();
String s3=t3.getText();
String s4=t4.getText();

try{  
    

//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  
 String sql1 = "SELECT BOOKID FROM BOOKS WHERE REFERENCE='F'";
      ResultSet rs = stmt.executeQuery(sql1);
      //STEP 5: Extract data from result set
      while(rs.next()){
          String sg=rs.getString("BOOKID");
          if(sg.equals(s1))
          {
              System.out.println(sg+""+s1);
Transacx xx=new Transacx();
    xx.runn(s1,s2,s3,s4);
    
BooksInhand xxx=new BooksInhand();
xxx.runn(s2);
      }
          else 
          {
 JFrame frl= new JFrame("NotFoundWindow");
     JLabel ll1,ll2;
        
    ll1=new JLabel("BOOK CANNOT BE ISSUED");  
    ll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		ll1.setFont(font1);
	ll1.setForeground(customColor1);
    ll2=new JLabel("");  
    if(s1.equals("")&&s2.equals(""))
	{
		ll2.setText("Credentials left Blank");
	}
    else if(s1.equals(""))
	{
		ll2.setText("BookID not found");
	}
	else if(s2.equals(""))
	{
		ll2.setText("StudentID not found");
	}
	else
	{
		ll2.setText("This is not a book or may be a reference Book");
	}
	
    ll2.setBounds(100,135,800,30);  
    	ll2.setFont(font);
		
	ll2.setForeground(customColor);
        
        frl.add(ll1);frl.add(ll2);
    
        frl.setSize(700,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
        
             
          }
            
      }
  System.out.println("REGISTRATION DONE SUCCESSFULLY");

}
catch(Exception el){
    System.out.println(el);
    JFrame frl= new JFrame("NotFoundWindow");
     JLabel ll1,ll2;
        
    ll1=new JLabel("BOOK CANNOT BE ISSUED");  
    ll1.setBounds(100,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,20);

    Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		ll1.setFont(font1);
		
	ll1.setForeground(customColor1);
    ll2=new JLabel("BookID or StudentId Not Found");  
    ll2.setBounds(100,135,400,30);  
    	ll2.setFont(font);
		
	ll2.setForeground(customColor);
        
        frl.add(ll1);frl.add(ll2);
    
        frl.setSize(500,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  
        

}  
 
            }});
        fr.add(b);
        fr.setSize(600,400);  
        fr.setLayout(null);  
        fr.setVisible(true);  
        
    }
    
}
class BOOKLOCATION
{
    public void runn()
    {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
                {
                    ex.printStackTrace();
                }
        
    JFrame f= new JFrame("Books");
   
    JLabel l1,l2,l3;  
    l1=new JLabel("Add a Location");  
    l1.setBounds(20,50, 150,40);  
    l2=new JLabel("Delete a Location");  
    l2.setBounds(20,150, 200,40);  
    l3=new JLabel("Edit a Location");  
    l3.setBounds(20,250, 150,40);  
    Color customColor = new Color(0,0,139);
    Font font = new Font("Courier", Font.BOLD,15);
		
		//set font for JLabel
		l1.setFont(font);
		
	l1.setForeground(customColor);
    		l2.setFont(font);
		
	l2.setForeground(customColor);
    		l3.setFont(font);
		
	l3.setForeground(customColor);
    
    f.add(l1); f.add(l2);f.add(l3);
    //add button
    Button b=new Button("Click Here");  
    b.setBounds(250,50,60,30);  
  
    b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
          
JFrame fr= new JFrame("BOOKDETAILS");
JLabel l1,l2,l3,l4,l5;
JTextField t1,t2,t3,t4;
        
    l5=new JLabel("Book Location");  
    l5.setBounds(150,50,260,30);  
    Color customColor1 = new Color(0,0,0);
    Font font1 = new Font("Courier", Font.BOLD,30);
		
		//set font for JLabel
		l5.setFont(font1);
l5.setForeground(customColor1);
    l1=new JLabel("BookID");  
    l1.setBounds(50,100,180,30);  
    Color customColor = new Color(0,0,128);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		l1.setFont(font);
		
	l1.setForeground(customColor);
      l2=new JLabel("Department");  
    l2.setBounds(50,150,180,30);  
    		l2.setFont(font);
	l2.setForeground(customColor);
    l3=new JLabel("Row Number");  
    l3.setBounds(50,200,180,30);  
    		l3.setFont(font);
	l3.setForeground(customColor);
    l4=new JLabel("Column Number");  
    l4.setBounds(50,250,180,30);  
    		l4.setFont(font);
	l4.setForeground(customColor);
          t1=new JTextField("");  
        t1.setBounds(350,100, 100,30);  
    t2=new JTextField("");  
    t2.setBounds(350,150, 100,30);  
    t3=new JTextField("");  
    t3.setBounds(350,200, 100,30);  
    t4=new JTextField("");  
    t4.setBounds(350,250, 100,30);  
    /*t7=new JTextField("");  
    t7.setBounds(350,350,100,30); */ 
    
    fr.add(t1); fr.add(t2);fr.add(t3);fr.add(t4);  
    fr.add(l1); fr.add(l2);fr.add(l3);fr.add(l4);fr.add(l5);
        
    Button bbl=new Button("Done");  
    bbl.setBounds(350,300,60,30);
        
bbl.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
            String s1=t1.getText();//BookID
            String s2=t2.getText();//Department
            String s3=t3.getText();//RN
            String s4=t4.getText();//CN
            try{  
    

//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  
String sql = "INSERT INTO BOOKLOCATION " +
                   "VALUES ('"+s1+"','"+s2+"','"+s3+"','"+s4+"')";

       stmt.executeUpdate(sql);
       
  System.out.println("REGISTRATION DONE SUCCESSFULLY");

}
catch(Exception el){ System.out.println(el);
    JFrame frl= new JFrame("NotFoundWindow");
     JLabel ll1,ll2;
        
    ll1=new JLabel("BOOK NOT FOUND");  
    ll1.setBounds(150,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,30);

    Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		ll1.setFont(font1);
		
	ll1.setForeground(customColor1);
    ll2=new JLabel("Try Adding the Book in the main menu through Books Section");  
    ll2.setBounds(0,135,800,30);  
    	ll2.setFont(font);
		
	ll2.setForeground(customColor);
        
        frl.add(ll1);frl.add(ll2);
    frl.setSize(820,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  


}  

      //      System.out.println(s1+""+s5+""+s7);
        }});        
        fr.add(bbl);
        fr.setSize(600,400);  
        fr.setLayout(null);  
        fr.setVisible(true);  
    
      
    }  
    });  
    f.add(b);
    
    
    //delete button
    Button b1=new Button("Click Here");  
    b1.setBounds(250,150,60,30);  
  
    b1.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
    JFrame fr= new JFrame("Deletion Window");
    JTextField t1;  
    t1=new JTextField("");  
    t1.setBounds(250,50, 100,30);  
    fr.add(t1);
    JLabel l1;  
    l1=new JLabel("BookID");  
    l1.setBounds(50,50, 200,30);  
    Color customColor1 = new Color(0,0,0);
    Font font1 = new Font("Courier", Font.BOLD,30);

    l1.setFont(font1);
	l1.setForeground(customColor1);
  
    fr.add(l1);   
    Button b=new Button("Delete");  
    b.setBounds(175,175,60,30);  
  
    b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
JFrame f1= new JFrame("Deletion Window");
JLabel ll1;  
    ll1=new JLabel("Confirm Delete");  
    ll1.setBounds(50,50,300,200);  
    ll1.setFont(font1);
	ll1.setForeground(customColor1);
  
    f1.add(ll1);   
        
Button b1=new Button("YES");  
    b1.setBounds(50,250,60,30);  
    Button b2=new Button("NO");  
    b2.setBounds(250,250,60,30);  
     f1.add(b1);f1.add(b2);
b1.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
String s1=t1.getText();
            try{  
    

//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  
String sql1 = "DELETE FROM BOOKLOCATION " +
                   "WHERE BOOKIDENTITY ='"+s1+"' ";

       stmt.executeUpdate(sql1);
       
  System.out.println("DELETION DONE SUCCESSFULLY");

}
catch(Exception el){ System.out.println(el);}  

        }});  
b2.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
         f1.dispose();
         fr.dispose();
        }});
f1.setSize(400,400);  
        f1.setLayout(null);  
        f1.setVisible(true);  
    
    }  
    });  
        fr.add(b);
        fr.setSize(400,260);  
        fr.setLayout(null);  
        fr.setVisible(true);  
    
      
    }  
    });  
    f.add(b1);
    //edit button
    Button b2=new Button("Click Here");  
    b2.setBounds(250,250,60,30);  
  
    b2.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
    
   JFrame fr= new JFrame("Edit Window");
        JTextField t1;  
    t1=new JTextField("");  
    t1.setBounds(250,50, 100,30);  
    fr.add(t1);
    JLabel l1;  
    l1=new JLabel("BookID");  
    l1.setBounds(50,50, 200,30);  
  Color customColor1 = new Color(0,0,0);
    Font font1 = new Font("Courier", Font.BOLD,30);

    l1.setFont(font1);
	l1.setForeground(customColor1);
  
    fr.add(l1);   
    Button bl=new Button("Edit");  
    bl.setBounds(175,175,60,30);
    fr.add(bl);
    bl.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
  String s=t1.getText();
JFrame fl= new JFrame("BOOKDETAILS");
JLabel l1,l2,l3,l4,l5;
JTextField tt1,t2,t3,t4;
        
    l5=new JLabel("Book Location");  
    l5.setBounds(150,50,260,30);  
    Color customColor1 = new Color(0,0,0);
    Font font1 = new Font("Courier", Font.BOLD,30);
		
		//set font for JLabel
		l5.setFont(font1);
l5.setForeground(customColor1);
    l1=new JLabel("BookID");  
    l1.setBounds(50,100,180,30);  
    Color customColor = new Color(0,0,128);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		l1.setFont(font);
		
	l1.setForeground(customColor);
      l2=new JLabel("Department");  
    l2.setBounds(50,150,180,30);  
    		l2.setFont(font);
	l2.setForeground(customColor);
    l3=new JLabel("Row Number");  
    l3.setBounds(50,200,180,30);  
    		l3.setFont(font);
	l3.setForeground(customColor);
    l4=new JLabel("Column Number");  
    l4.setBounds(50,250,180,30);  
    		l4.setFont(font);
	l4.setForeground(customColor);
          tt1=new JTextField("");  
        tt1.setBounds(350,100, 100,30);  
    t2=new JTextField("");  
    t2.setBounds(350,150, 100,30);  
    t3=new JTextField("");  
    t3.setBounds(350,200, 100,30);  
    t4=new JTextField("");  
    t4.setBounds(350,250, 100,30);  
    /*t7=new JTextField("");  
    t7.setBounds(350,350,100,30); */ 
    tt1.setText(s);
    fl.add(tt1); fl.add(t2);fl.add(t3);fl.add(t4);  
    fl.add(l1); fl.add(l2);fl.add(l3);fl.add(l4);fl.add(l5);
        
    Button bbl=new Button("Done");  
    bbl.setBounds(350,300,60,30);
        
bbl.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){
            String s1=tt1.getText();//BookID
            String s2=t2.getText();//Department
            String s3=t3.getText();//RN
            String s4=t4.getText();//CN
            try{  
    

//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  
String sql1 = "DELETE FROM BOOKLOCATION " +
                   "WHERE BOOKIDENTITY ='"+s1+"' ";

       stmt.executeUpdate(sql1);

String sql = "INSERT INTO BOOKLOCATION " +
                   "VALUES ('"+s1+"','"+s2+"','"+s3+"','"+s4+"')";

       stmt.executeUpdate(sql);
       
  System.out.println("REGISTRATION DONE SUCCESSFULLY");

}
catch(Exception el){ System.out.println(el);}  
//System.out.println(s1+""+s2+""+s3+""+s4+""+s5+""+s6+""+s7+"_______haiii");
    JFrame frl= new JFrame("NotFoundWindow");
     JLabel ll1,ll2;
        
    ll1=new JLabel("BOOK NOT FOUND");  
    ll1.setBounds(150,35,400,30);  
    Color customColor1 = new Color(255,0,0);
    Font font1 = new Font("Courier", Font.BOLD,30);

    Color customColor = new Color(0,0,0);
    Font font = new Font("Courier", Font.BOLD,20);
		
		//set font for JLabel
		ll1.setFont(font1);
		
	ll1.setForeground(customColor1);
    ll2=new JLabel("Try Adding the Book in the main menu through Books Section");  
    ll2.setBounds(0,135,600,30);  
    	ll2.setFont(font);
		
	ll2.setForeground(customColor);
        
        frl.add(ll1);frl.add(ll2);
    frl.setSize(620,300);  
        frl.setLayout(null);  
        frl.setVisible(true);  


        
        }
        });
        fl.add(bbl);
        fl.setSize(600,600);  
        fl.setLayout(null);  
        fl.setVisible(true);  
    
      
            
        }
        });
        fr.setSize(400,300);  
        fr.setLayout(null);  
        fr.setVisible(true);  
    
      
    }  
    });  

    f.add(b2);
    f.setSize(400,400);  
    f.setLayout(null);  
    f.setVisible(true);  
    
    }
}
class UPDATION
{
 public void runs(String s1)
 {
                   try{  
    
String k="0.00";
//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  

String sql = "UPDATE STUDENTDETAILS SET DUESREMAINING = '"+k+"'  where REGISTERNUMBER= '"+s1+"' ";

       stmt.executeUpdate(sql);
      
  System.out.println("DELETION DONE SUCCESSFULLY");
con.close();
}
catch(Exception el){ System.out.println(el);}  

 }
}
class BooksInhand {

    /**
     * @param args the command line arguments
     */
    public static void runn(String s2) {
        // TODO code application logic here
 try{  
    int x;

//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  

       ResultSet rs=stmt.executeQuery("select NOOFBOOKSINHAND from STUDENTDETAILS where REGISTERNUMBER ='"+s2+"'");  
while(rs.next())  
{
     x=rs.getInt("NOOFBOOKSINHAND");
     System.out.println(x);
     x=x+1;
      System.out.println(x);
String sql1 = "UPDATE STUDENTDETAILS SET NOOFBOOKSINHAND = '"+x+"' where REGISTERNUMBER = '"+s2+"'";

       stmt.executeUpdate(sql1);

}
 }catch(Exception el)
 {
     System.out.println(el);
 }
    }
    public void funn(String s2) {
        // TODO code application logic here
 try{  
    int x;

//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:XE","system","batman");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  

       ResultSet rs=stmt.executeQuery("select NOOFBOOKSINHAND from STUDENTDETAILS where REGISTERNUMBER ='"+s2+"'");  
while(rs.next())  
{
     x=rs.getInt("NOOFBOOKSINHAND");
     System.out.println(x);
     x=x-1;
      System.out.println(x);
String sql1 = "UPDATE STUDENTDETAILS SET NOOFBOOKSINHAND = '"+x+"' where REGISTERNUMBER = '"+s2+"'";

       stmt.executeUpdate(sql1);

}
 }catch(Exception el)
 {
     System.out.println(el);
 }
}
    
}
