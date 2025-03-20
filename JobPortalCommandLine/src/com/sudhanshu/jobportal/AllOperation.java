package com.sudhanshu.jobportal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.sudhanshu.connection.DbConnection;

public class AllOperation 
{
    public static void registerOperations() 
    {
        Scanner sc = new Scanner(System.in);
        Connection con = null;

        try {
            con = DbConnection.getConnect(); // Assuming DbConnection is a class that handles DB connection

            while (true) {
                System.out.println("Press 1.Register To job Portal\nPress 2.Retrieve All Register user\n"
                        + "Press 3.Update Registration\nPress 4.Delete User\n"
                        + "Press 5.To Go back to the main menu");
                int input = sc.nextInt();
                switch (input) {
                    case 1:
                        sc.nextLine();  // Consume newline
                        System.out.println("Enter Your ID : ");
                        String userid = sc.nextLine();
                        System.out.println("Enter your First Name : ");
                        String firstName = sc.nextLine();
                        System.out.println("Enter your last Name : ");
                        String lastName = sc.nextLine();
                        System.out.println("Enter your Gender : ");
                        String gender = sc.nextLine();
                        System.out.println("Enter your Email : ");
                        String email = sc.nextLine();

                        try (PreparedStatement ps = con.prepareStatement("INSERT INTO register(id, firstname, lastname, gender, email) VALUES(?,?,?,?,?)")) {
                            ps.setString(1, userid);
                            ps.setString(2, firstName);
                            ps.setString(3, lastName);
                            ps.setString(4, gender);
                            ps.setString(5, email);
                            int i1 = ps.executeUpdate();

                            if (i1 > 0) {
                                System.out.println("User Registered Successfully");
                            }
                        } catch (SQLException e) {
                            System.out.println("Error during registration: " + e.getMessage());
                        }
                        break;

                    case 2:
                        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM register")) { // Updated table name to match register
                            ResultSet rs = ps.executeQuery();
                            while (rs.next()) {
                                System.out.println("ID: " + rs.getString("id"));
                                System.out.println("First Name: " + rs.getString("firstname"));
                                System.out.println("Last Name: " + rs.getString("lastname"));
                                System.out.println("Gender: " + rs.getString("gender"));
                                System.out.println("Email: " + rs.getString("email"));
                            }
                        } catch (SQLException e) {
                            System.out.println("Error retrieving users: " + e.getMessage());
                        }
                        break;

                    case 3:
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter Your Updated ID : ");
                        String upUserid = sc.nextLine();
                        System.out.println("Enter your Updated First Name : ");
                        String upFirstName = sc.nextLine();
                        System.out.println("Enter your Updated Last Name : ");
                        String upLastName = sc.nextLine();
                        System.out.println("Enter your Updated Gender : ");
                        String upGender = sc.nextLine();
                        System.out.println("Enter your Original Email/Email can not be updated : ");
                        String emailForUpdate = sc.nextLine();

                        try (PreparedStatement ps = con.prepareStatement("UPDATE register SET id=?, firstname=?, lastname=?, gender=? WHERE email=?")) {
                            ps.setString(1, upUserid);
                            ps.setString(2, upFirstName);
                            ps.setString(3, upLastName);
                            ps.setString(4, upGender);
                            ps.setString(5, emailForUpdate);
                            int i = ps.executeUpdate();

                            if (i > 0) {
                                System.out.println("User Data Updated Successfully");
                            }
                        } catch (SQLException e) {
                            System.out.println("Error updating user: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("Enter ID of the Student you want to Delete  : ");
                        String id2 = sc.next();
                        try (PreparedStatement ps = con.prepareStatement("DELETE FROM register WHERE id=?")) {
                            ps.setString(1, id2);

                            int i = ps.executeUpdate();
                            if (i > 0) {
                                System.out.println("User Deleted Successfully");
                            }
                        } catch (SQLException e) {
                            System.out.println("Database connection error: " + e.getMessage());
                        }
                        break;

                    case 5:
                        MainOperation.mianOps(); 
                        break;

                    default:
                        System.out.println("Invalid option! Try again.");
                        break;
                }
            }
        } 
         finally {
            try {
                if (con != null) con.close();
                sc.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    public static void EducationOperations() {
        Connection con = null;
        Scanner sc = new Scanner(System.in);

        try {
            con = DbConnection.getConnect(); 

            while (true) {
                System.out.println("Press 1.Add Education Details\n2.Retrieve All Education Info\n"
                        + "3.Update Education\nPress 4.To get back to the main menu");
                int input = sc.nextInt();

                switch (input) {
                    case 1:
                        sc.nextLine();  
                        System.out.println("Enter Your Email");
                        String email = sc.nextLine();
                        System.out.println("Enter your School : ");
                        String school = sc.nextLine();
                        System.out.println("Enter your Degree : ");
                        String degree = sc.nextLine();
                        System.out.println("Enter Year Of Completion : ");
                        int year = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter your Grade : ");
                        String grade = sc.nextLine();

                        try (PreparedStatement ps = con.prepareStatement("INSERT INTO education_detials(email, school, degree, year, grade) VALUES(?,?,?,?,?)")) {
                            ps.setString(1, email);
                            ps.setString(2, school);
                            ps.setString(3, degree);
                            ps.setInt(4, year);
                            ps.setString(5, grade);

                            int i = ps.executeUpdate();
                            if (i > 0) {
                                System.out.println("Educational details added successfully");
                            }
                        } catch (SQLException e) {
                            System.out.println("Error adding educational details: " + e.getMessage());
                        }
                        break;

                    case 2:
                        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM education_detials")) { 
                            ResultSet rs = ps.executeQuery();
                            while (rs.next()) {
                                System.out.println("email: " + rs.getString("email"));
                                System.out.println("school: " + rs.getString("school"));
                                System.out.println("degree: " + rs.getString("degree"));
                                System.out.println("year: " + rs.getString("year"));
                                System.out.println("grade: " + rs.getString("grade"));
                            }
                        } catch (SQLException e) {
                            System.out.println("Error retrieving education details: " + e.getMessage());
                        }
                        break;

                    case 3:
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter your Original Email/ Email Can not be Changed: ");
                        String EmailUP = sc.nextLine();
                        System.out.println("Enter your Updated School : ");
                        String upSchool = sc.nextLine();
                        System.out.println("Enter your Updated Degree : ");
                        String upDegree = sc.nextLine();
                        System.out.println("Enter your Updated Year : ");
                        String upYear = sc.nextLine();
                        System.out.println("Enter your Updated Grade : ");
                        String upGrade = sc.nextLine();

                        try (PreparedStatement ps = con.prepareStatement("UPDATE education_detials SET school=?, degree=?, year=?, grade=? WHERE email=?")) {
                            ps.setString(1, upSchool);
                            ps.setString(2, upDegree);
                            ps.setString(3, upYear);
                            ps.setString(4, upGrade);
                            ps.setString(5, EmailUP);

                            int i = ps.executeUpdate();

                            if (i > 0) 
                            {
                                System.out.println("User Educational Info Updated Successfully");
                            }
                        } catch (SQLException e) {
                            System.out.println("Error updating educational info: " + e.getMessage());
                        }
                        break;

                    case 4:
                        MainOperation.mianOps();
                        break;

                    default:
                        System.out.println("Invalid option! Try again.");
                        break;
                }
            }
        }  finally {
            try {
                if (con != null) con.close();
                sc.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    public static void experienceOperations()
    {
    	 Connection con = null;
    	 Scanner sc = new Scanner(System.in);

    	 
         while (true) {
             System.out.println("Press 1.Add Experience Info\n2.Delete Experience\n3.Update Experience\n4.For Main Menu");
             int input = sc.nextInt();

             con = DbConnection.getConnect(); 
             switch (input)
             {
                 case 1:
                     sc.nextLine();  // Consume newline
                     System.out.println("Enter your email:");
                     String email = sc.nextLine();
                     System.out.println("Enter Your Company:");
                     String company = sc.nextLine();
                     System.out.println("Enter your location:");
                     String location = sc.nextLine();
                     System.out.println("Enter Year Of joining:");
                     String Year = sc.nextLine();
                     System.out.println("Enter your Job Title:");
                     String jobtitle = sc.nextLine();
                     
                     
                     try
                     {
                     
                    PreparedStatement ps=con.prepareStatement("insert into experience_details(email, company, location,year, job_title) values(?,?,?,?,?)");
                     ps.setString(1, email);
                     ps.setString(2, company);
                     ps.setString(3, location);
                     ps.setString(4, Year);
                     ps.setString(5, jobtitle);

                     int i=ps.executeUpdate();
                     if(i>0)
                     {
                    	 
                    	 System.out.println("Experience Details Added");
                     }
                     // Add feedback logic here (for example, store it in the database)
                     }
                      catch (Exception e)
                     {
						System.out.println(e);
					}
                     break;

                 case 2:
                	 System.out.println("Enter Email Of Your you want to Delete Experience : ");
                     String Email = sc.next();
                     try (PreparedStatement ps = con.prepareStatement("DELETE FROM experience_details WHERE Email=?")) {
                         ps.setString(1, Email);

                         int i = ps.executeUpdate();
                         if (i > 0) {
                             System.out.println("Experience Deleted Successfully");
                         }
                     } catch (SQLException e) {
                         System.out.println("Database connection error: " + e.getMessage());
                     }
                     break;
                 case 3:
                	 System.out.println("Enter Email Of Your you want to update (Please provide/ original email) Experience : ");
                     String email1 = sc.next();
                     
                     
                     System.out.println("Enter Your Company:");
                     String company1 = sc.nextLine();
                     System.out.println("Enter your location:");
                     String location1 = sc.nextLine();
                     System.out.println("Enter Year Of joining:");
                     String Year1 = sc.nextLine();
                     System.out.println("Enter your Job Title:");
                     String jobtitle1 = sc.nextLine();
                     
                     try (PreparedStatement ps = con.prepareStatement("UPDATE experience_details SET company=?, location=?, year=?, job_title=? WHERE email=?")) {
                         
                    	 ps.setString(1, company1);
                         ps.setString(2, location1);
                         ps.setString(3, Year1);
                         ps.setString(4, jobtitle1);
                    	 ps.setString(5, email1);

                         int i = ps.executeUpdate();
                         if (i > 0) {
                             System.out.println("Experience Updated Successfully");
                         }
                     } catch (SQLException e) {
                         System.out.println("Database connection error: " + e.getMessage());
                     }
                     break;
                     
                 case 4:
                     MainOperation.mianOps();
                     break;

                 default:
                     System.out.println("Invalid option! Try again.");
                     break;
             }
         }
    	
    }
    
    public static void feedbackOperation() {
        Scanner sc = new Scanner(System.in);
        
        Connection con = null;

        while (true) {
            System.out.println("Press 1.Add Feedback\n2.To get back to the main menu");
            int input = sc.nextInt();
            con = DbConnection.getConnect();

            switch (input) {
                case 1:
                    sc.nextLine();  // Consume newline
                    System.out.println("Enter Your Email");
                    String email=sc.nextLine();
                    
                    System.out.println("Enter Your Feedback: ");
                    
                    String feedback = sc.nextLine();
                       
                    try (PreparedStatement ps = con.prepareStatement("insert into feedback(email, feedback) values(?,?)")) {
                        
                     ps.setString(1, email); 	
                   	 ps.setString(2, feedback);
                   	 
                   	 int i=ps.executeUpdate();
                   	 if(i>0)
                   	 {
                   		 System.out.println("Feedback Received");
                   	 }
                   	 
                    }
                    catch (Exception e) {
                    	System.out.println(e);
                    }
                    

                    break;

                case 2:
                    MainOperation.mianOps();
                    break;

                default:
                    System.out.println("Invalid option! Try again.");
                    break;
            }
        }
    }
}
