import java.util.*;
import java.sql.*;
public class Student
{
    public static void main(String[] args){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
            Scanner sc=new Scanner(System.in);
            
            System.out.println("1.Insert student data into Student table");
            System.out.println("2.Update student data into Student table");
            System.out.println("3.Delete student data from Student table");
            System.out.println("4.Get a list of all students");   
            System.out.println("5.Get one student information depending on the student id filter.");
            System.out.println("1.Enter your Choice:");
            int choose=sc.nextInt();
            switch(choose){
                case 1:
                String sname,dob,doj;
                int sno;
                System.out.println("Enter Student Number ->");
                sno=sc.nextInt();
                System.out.println("Enter Student Name ->");
                sname=sc.next();
                System.out.println("Enter Student Date Of Birth in yyyy/mm/dd Format ->");
                dob=sc.next();
                System.out.println("Enter Student Date Of Join in yyyy/mm/dd Format ->");
                doj=sc.next();
                PreparedStatement ps=con.prepareStatement("insert into student values(?,?,?,?)");
                ps.setInt(1,sno);
                ps.setString(2,sname);
                ps.setString(3,dob);
                ps.setString(4,doj);
                int i=ps.executeUpdate();
                if(i>0)
                {
                    System.out.println("Data Successfully Inserted");
                }
                else
                {
                    System.out.println("Error");
                }
                break;
                case 2:
                
                System.out.println("Enter Student Number ->");
                sno=sc.nextInt();
                System.out.println("Enter Student Date Of Join in yyyy/mm/dd ->");
                doj=sc.next();
                PreparedStatement ps1=con.prepareStatement("update student set STUDENT_DOJ=? where STUDENT_NO=?");
                ps1.setString(1,doj);
                ps1.setInt(2,sno);
                i=ps1.executeUpdate();
                if(i>0)
                {
                    System.out.println("Data Successfully Updated");
                }
                else
                {
                    System.out.println("Error");
                }
                break;
                case 3:
               
                System.out.println("Enter Student Number ->");
                sno=sc.nextInt();
                PreparedStatement ps2=con.prepareStatement("delete from student where STUDENT_NO=?");
                ps2.setInt(1,sno);
                i=ps2.executeUpdate();
                if(i>0)
                {
                    System.out.println("Data Successfully Deleted");
                }
                else
                {
                    System.out.println("Error");
                }
                break;
                case 4:
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from student");
                System.out.println("STUDENT_NO " + "\t" + "STUDENT_NAME " + "\t\t" + "STUDENT_DOB" + "\t\t" + "STUDENT_DOJ");
                while(rs.next())
                {
                   sno = rs.getInt("STUDENT_NO");
                    sname = rs.getString("STUDENT_NAME");
                    dob = rs.getString("STUDENT_DOB");
                    doj = rs.getString("STUDENT_DOJ");
                    System.out.println(sno + "\t\t" + sname + "\t\t" + dob + "\t\t" + doj );
                }
                
                break;
                case 5:
               
                PreparedStatement ps3=con.prepareStatement("select * from student  where STUDENT_NO=?");
                
                System.out.println("Enter Student Number ->");
                sno=sc.nextInt();
                ps3.setInt(1,sno);
                ResultSet res=ps3.executeQuery(); 
                System.out.println("STUDENT_NO " + "\t" + "STUDENT_NAME " + "\t\t" + "STUDENT_DOB" + "\t\t" + "STUDENT_DOJ");
                while (res.next())
                {

                   sno = res.getInt("STUDENT_NO");
                    String s = res.getString("STUDENT_NAME");
                    String db = res.getString("STUDENT_DOB");
                    String dj = res.getString("STUDENT_DOJ");
                    System.out.println(sno + "\t\t" + s + "\t\t" + db + "\t\t" + dj );
                }
                break;
                default:
                System.out.println("Wrong Choice....");



                

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}