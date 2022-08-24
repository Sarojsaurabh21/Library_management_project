package pkg_main;


import java.util.Scanner;

import pkg_book.Book;
import pkg_book.BookManger;
import pkg_exception.BookNotFoundException;
import pkg_exception.StudentNotFoundException;
import pkg_person.Student;
import pkg_person.StudentManger;
import pkg_transaction.BookTransactionManager;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, StudentNotFoundException {
		int choice;
		Scanner sc= new Scanner(System.in);
		BookManger bm = new BookManger();
		StudentManger sm = new StudentManger();
		BookTransactionManager btm =new BookTransactionManager();
		
		do {
			System.out.println("Enter 1 if Student\nEnter 2 if Librarian\nEnter 3 if want to exit");
			choice =sc.nextInt();
			
			
			if(choice== 1)
			{
				System.out.println("Enter your Roll number");
				int rollNo = sc.nextInt();
				try
				{
					Student s = sm.get(rollNo);
					if(s==null)
						throw new StudentNotFoundException();
					int stud_choice;
					
					do {
						System.out.println("enter 1 to View All Books\nEnter 2 to search book by ISBN\nEnter 3 to List Book by Subject\n Enter 4  to Issue a Book\n enter 5  to a Return Book ");
						stud_choice =sc .nextInt();
						
						switch(stud_choice)
						{
						case 1:
							System.out.println("All the Book record are");
							bm.viewAllBooks();
							break;
						
						case 2:
							System.out.println("Please Enter ISBN  of the Book to Search");
							
							int search_isbn;
							System.out.println("Enter ISBN of of the Book to search");
							search_isbn=sc.nextInt();
							Book book = bm.searchBookByIsbn(search_isbn);
							if(book == null)
								System.out.println("No Book with this ISBN Exits in our Library");
							else
								System.out.println(book);
							
							break;
						case 3:
							System.out.println("Enter the subject to search");
							sc.nextLine();
							String search_subject = sc.nextLine();
							bm.listBooksBySubject(search_subject);
							break;
						case 4:
							System.out.println("  Enter the ISBN to Issual a Book");
							int issue_isbn =sc.nextInt();
							book = bm.searchBookByIsbn(issue_isbn);
							try
							{
								if(book == null)
								{
									throw new BookNotFoundException();
								}
								if(book.getAvailable_quantity()>0)
								{
									if(btm.issueBook(rollNo,issue_isbn))
									{
										book.setAvailable_quantity(book.getAvailable_quantity()-1);
										System.out.println("Book Has Been Issued.......................");
									}
								}
								else
								{
									System.out.println("The Book has Been Issued.....");
								}
							}
							catch(BookNotFoundException bnfe)
							{
								System.out.println(bnfe);
								
							}
							break;
						case 5:
							System.out.println(" Please Enter the ISBN to ISBN to Return a Book");
							int return_isbn = sc.nextInt();
							book = bm.searchBookByIsbn(return_isbn);
							if(book !=null)
							{
								if(btm.returnBook(rollNo, return_isbn)) {
									book.setAvailable_quantity(book.getAvailable_quantity()+1);
									System.out.println("Thank You for Returning The Book");
								}
							}
							else
								System.out.println("Could Not Return the Book");
							break;
							
						case 99:
							System.out.println("Thank You for using Library");
							break;
							
							default:
								System.out.println("Invalid Choice");
								
						}
					}while(stud_choice !=99);
					{
						
					}
				}
				catch(StudentNotFoundException snfe)
				{
					System.out.println(snfe);
				}
		}
			
			else if(choice == 2)	// user is a Librarian	
			{
				int lib_choice;
				do {
					
				
				
				System.out.println("Enter 11 to View all Students\nEnter 12 to Print a Student by Roll Number\n Enter 13 to Register a Student\nEnter 14 to Update a Student\nEnter 15 to delete a Student");
				System.out.println("Enter 21 to View all Books\nEnter 22 to Print a Book by ISBN\n Enter 23 to Add a New Book\nEnter 24 to Update a Book\nEnter 25 to delete a Book");
				System.out.println("Enter 31 ti View all Transactions");
				System.out.println("Enter 99 to Exit");
				lib_choice=sc.nextInt();
				switch(lib_choice)
				{
				case 11:   //View all Students
					System.out.println("All the Students Records");
					sm.ViewallStudents();
					break;
				case 12:   // search a student based on roll no
					System.out.println("Enter Roll Number to Fetch Student's recoerd");
					int get_rollNo = sc.nextInt();
					Student student = sm.get(get_rollNo);
					if(student == null)
					{
						System.out.println("No Recoerd Matches this Roll Number");
					}
					else
						System.out.println(student);
					break;
					
				case 13:
					System.out.println("Enter Students Details to Add");
					String name;
					String emailId;
					String phoneNumber;
					String address;
					String dob;
					int  rollNo ;
					int std;
					String division;
					sc.nextLine();
					System.out.println("Name");
				    name=sc.nextLine();
				    
				    System.out.println("EmailId");
				    emailId=sc.nextLine();
				    
				    System.out.println("Phone Number");
				    phoneNumber=sc.nextLine();
				    
				    System.out.println("Address");
				    address=sc.nextLine();
				    
				    System.out.println("DOB");
				    dob=sc.nextLine();
				    
				    System.out.println("Roll No");
				    rollNo = sc.nextInt();
				    
				    System.out.println("STD");
				    std=sc.nextInt();
				    
				    sc.nextLine();
				    
				    System.out.println("Division");
				    division = sc.nextLine();
				     
				    student =new Student( name,  emailId,  phoneNumber,  address,  dob,  rollNo, std, division);
				    sm.addStudent(student);
				    System.out.println("Student is Added");
				    break;
				    
				case 14:    //Update a student
					System.out.println("Enter The Roll Number TO Modify The Record");
					int modify_rollNo;
					modify_rollNo = sc.nextInt();
					student = sm.get(modify_rollNo);
					try
					{
					if(student == null)
					
						throw new StudentNotFoundException();
						
					sc.nextLine();
						System.out.println("Name");
					    name=sc.nextLine();
					    
					    System.out.println("EmailId");
					    emailId=sc.nextLine();
					    
					    System.out.println("Phone Number");
					    phoneNumber=sc.nextLine();
					    
					    System.out.println("Address");
					    address=sc.nextLine();
					    
					    System.out.println("DOB");
					    dob=sc.nextLine();
					    

					    
					    System.out.println("STD");
					    std=sc.nextInt();
					    
					    sc.nextLine();
					    
					    System.out.println("Division");
					    division = sc.nextLine();
					     
					    
					   
					   sm.UpdateStudent(modify_rollNo, name, emailId, phoneNumber, address, dob, std, division);
					   
					    System.out.println("Student Record Updated");
					    
					}
					
				
			
			
					catch(StudentNotFoundException snfe)
					{
						System.out.println(snfe);
					}
					break;
					
					
				case 15:   //delete student 
					System.out.println("Enter the Roll Number to Delete record");
					int delete_rollNo;
					delete_rollNo=sc.nextInt();
					if(sm.deleteStudent(delete_rollNo))
						System.out.println("Student Record is remove");
					else
						System.out.println("No Record with Given Record");
					break;
					
				case 21:   //View all books
					bm.viewAllBooks();
					System.out.println();
					break;
					
				case 22: // searching by isbn
					int search_isbn;
					System.out.println("Enter ISBN of of the Book to search");
					search_isbn=sc.nextInt();
					Book book = bm.searchBookByIsbn(search_isbn);
					if(book == null)
						System.out.println("No Book with this ISBN Exits in our Library");
					else
						System.out.println(book);
					break;
					
				case 23:  // Add  new the books
					System.out.println("Please Enter Book Details to Add ");
					int isbn;
					String title;
					String author;
					String publisher;
					int edition;
					String subject ;
					int available_quantity;
					
					System.out.println("ISBN");
					isbn = sc.nextInt();
					
					
					sc.nextLine();
					
					System.out.println("Title");
					title=sc.nextLine();
					
				
					
					System.out.println("Author");
					author=sc.nextLine();

					
					System.out.println("Publisher");
					publisher=sc.nextLine();
					
					
					System.out.println("Edition");
					edition=sc.nextInt();
					
					
					sc.nextLine();
					
					System.out.println("Subjectsa");
					subject=sc.nextLine();
					
					System.out.println("Availbale_quantity");
					available_quantity=sc.nextInt();
					
					book = new Book( isbn,  title,  author,  publisher,  edition,  subject, available_quantity);
					bm.addABook(book);
					System.out.println("A Book Record is Added");
					break;
					
				case 24:    //Update a record of book
					System.out.println("Please Enter the ISBN to update the record");
					int update_isbn;
					update_isbn = sc.nextInt();
					try
					{
						book= bm.searchBookByIsbn(update_isbn);
						if(book == null)
							throw new BookNotFoundException();
						
                        System.out.println("Enter  Book Details to Modify");                      
                         sc.nextLine();                                    
						
						System.out.println("Title");
						title=sc.nextLine();
																
						System.out.println("Author");
						author=sc.nextLine();

						
						System.out.println("Publisher");
						publisher=sc.nextLine();
						
					
						
						System.out.println("Edition");
						edition=sc.nextInt();
						
						System.out.println("Subject");
						subject=sc.next();
						
												
						System.out.println("Availbale_quantity");
						available_quantity=sc.nextInt();
						
						bm.updateBook(update_isbn, title, author, publisher, edition,  subject,  available_quantity);
				
						
					}
					catch( BookNotFoundException bnfe)
					{
						System.out.println(bnfe);
					}
					break;
					
				case 25:   //delete a record of Book
					
					System.out.println("Please Enter the ISBN to Delete  the record");
					int delete_isbn = 0;
					delete_isbn = sc.nextInt();
					try
					{
						book= bm.searchBookByIsbn(delete_isbn);
						if(book == null)
							throw new BookNotFoundException();
						bm.deleteBook(delete_isbn);
						System.out.println("Book Record is Deleted");
					}
					catch(BookNotFoundException bnfe)
					{
						System.out.println(bnfe);
					}
					break;
					
				case 31:   //To View all transactions
					System.out.println("All the Transactions are ");
					btm.showAll();
					break;
				case 99:
					System.out.println("Thank you for Using library");
					break;
					
				default:					   
					System.out.println("Invalid Choice");  
					}
				    
					
			}
			while(lib_choice != 99) ;
			}
		}
				
			

		while(choice !=3);
		sm.writeToFile();
		bm.writeToFile();
		btm.writeTofile();
		sc.close();
		
		
		}	
		
		
		
	}
		


	

