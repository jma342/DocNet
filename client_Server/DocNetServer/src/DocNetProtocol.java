import java.net.*;
import java.io.*;
import java.sql.*;

public class DocNetProtocol {

	//MENUS---jma342---Feb 14th
    private static final int LOG_IN_SCREEN = 0;
    private static final int MAIN_POSTING_BOARD_SCREEN = 1;
    private static final int PERSONAL_INFORMATION_SCREEN = 2;
    private static final int ANNOUNCEMENTS_SCREEN = 3;
    private static final int RESEARCH_PUBLICATIONS_SCREEN = 4;
    private static final int PUBLIC_DISCUSSIONS_SCREEN = 5;
    private static final int RESEARCH_GROUPS_SCREEN = 6;
    private static final int FRIENDS_LIST_SCREEN = 7;
    private static final int CURRENT_OUTPUT_SCREEN = 8;
    private static final int MARITAL_STATUS_SCREEN = 9;//change made - jma342 - Feb 18th
    private static final int GENDER_SCREEN = 10;//change made - jma342 - Feb 18th
    private static final int ADD_FRIEND_SCREEN = 11;//change made - jma342 - Feb 19th
    private static final int FRIEND_REQUESTS_SCREEN = 12;//change made - jma342 - Feb 19th
    private static final int PRIVILEGES_SCREEN = 13;//added -- jma 342 - February 24th 2012
    //MENUS---jma342---Feb 14th
    
    //current step in a given conversation relative to each menu---jma342---Feb 14th
    private int step_LOG_IN_SCREEN = 0;
    private int step_LOG_IN_SCREEN_reconfirmPword = 0;
    private int step_MAIN_POSTING_BOARD = 0;
    private int step_PERSONAL_INFORMATION = 0;
    private int step_ANNOUNCEMENTS = 0;
    private int step_RESEARCH_PUBLICATIONS = 0;
    private int step_PUBLIC_DISCUSSIONS = 0;
    private int step_RESEARCH_GROUPS = 0;
    private int step_FRIENDS_LIST = 0;
    private int step_SCREEN_OUTPUT = 0;
    private int step_MARITAL_STATUS = 0;//change made - jma342 - Feb 18th
    private int step_GENDER = 0;//change made - jma342 - Feb 18th
    private int step_ADD_FRIEND = 0;//change made - jma342 - Feb 19th
    private int step_FRIEND_REQUEST = 0;//change made - jma342 - Feb 19th
    private int step_ERROR_CHECK = 0;//jma342 - Feb 21st
    private int step_PRIVILEGES_SCREEN = 0;//added -- jma 342 - February 24th 2012
  //current step in a given conversation relative to each menu---jma342---Feb 14th
    
    //yb85 Feb, 25 @6:56pm
    private String userName = "";
	private String password = "";
	private String sqlString = "";
    
    //jma342 -- feb 21st -- both database connections
    private Connection con_1 = null;
	private Connection con_2 = null;
	Statement st = null;
    ResultSet rs = null;
    
    //field on screen chosen to be edited
    private String editField_On_Screen = "";
    
    //holds the action chosen to be executed
    private String chosen_On_Screen_Action = "";
    
    //current screen user is interfacing with
    private int currentScreen = LOG_IN_SCREEN;
    private int nextScreen = 0;

  //jma342 -- feb 21st -- added constructor to initialise db connections
    public DocNetProtocol(Connection c1, Connection c2)
    {
    	con_1 = c1;
    	con_2 = c2;
    }
    
    public String processRequest(String input)
    {	
    	String output = "";
    	
    	if(currentScreen == LOG_IN_SCREEN)
    	{
    		output =  loginScreenMenu(input);
    	}
    	else if(currentScreen == MAIN_POSTING_BOARD_SCREEN)
    	{
    		output = mainPostingBoardSreen(input);
    	}
    	
    	else if(currentScreen == PERSONAL_INFORMATION_SCREEN)
    	{
    		output =  personalInformationScreen(input);
    	}
    	
    	//change made - jma 342 - feb 18th
    	else if(currentScreen == MARITAL_STATUS_SCREEN)
    	{
    		output = this.maritalStatusScreen(input);
    	}
    	else if(currentScreen == GENDER_SCREEN)
    	{
    		output = this.genderScreen(input);
    	}
    	//change made - jma 342 - feb 18th
    	
    	else if(currentScreen == FRIENDS_LIST_SCREEN)
    	{
    		output = this.friendsListSreen(input);
    	}
    	
    	//change made - jma 342 - feb 19th
    	else if(currentScreen == this.ADD_FRIEND_SCREEN)
    	{
    		output = this.addFriendSreen(input);
    	}
    	
    	else if(currentScreen == this.FRIEND_REQUESTS_SCREEN)
    	{
    		output = this.friendRequestScreen(input);
    	}
    	//change made - jma 342 - feb 19th
    	
    	else if(currentScreen == CURRENT_OUTPUT_SCREEN)
    	{
    		output = screenOutput(nextScreen);
    	}
    	return output;
    	
    }
    
    //displays the layout of each screen
    public String screenOutput(int screen/*,recordset*/)
    {
    	String output = "";
    	
    	//changes made - jma 342 - Feb 18th
    	if(screen == this.MAIN_POSTING_BOARD_SCREEN)
    	{
    		
    		if(this.step_SCREEN_OUTPUT == 0)
    		{
    			output = "1. Personal Information";
    			step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 1)
    		{
    			output = "2.Announcements";
    			step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 2)
    		{
    			output = "3.Research Publications";
    			step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 3)
    		{
    			output = "4.Public Discussions";
    			step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 4)
    		{
    			output = "5.Research Groups";
    			step_SCREEN_OUTPUT++;
    		}
    		
    		//added -- jma 342 - February 24th 2012
    		else if(this.step_SCREEN_OUTPUT == 5)
    		{
    			output = "6.Priviliges(Posting Boards/Regions)";
    			step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 6)
    		{
    			output = "7.Log Out";
    			step_SCREEN_OUTPUT++;
    		}
    		//added -- jma 342 - February 24th 2012
    		
    		else if(this.step_SCREEN_OUTPUT == 7)
    		{
    			output = "Please select the number(1-5) for the desired region? ";
    			step_SCREEN_OUTPUT++;
    		}
    		else
    		{
    			currentScreen = nextScreen;
    			step_SCREEN_OUTPUT = 0;
    			output = "userInput";
    		}
    	}
    	else if(screen == this.PERSONAL_INFORMATION_SCREEN)
    	{
    		//try to figure how to clearscreen
    		if(this.step_SCREEN_OUTPUT == 0)
    		{
    			output = "1. First Name";  //+ db.firtname
    			step_SCREEN_OUTPUT++;
    		}
    		else if(step_SCREEN_OUTPUT == 1)
    		{
    			output ="2. Last Name";//db.lastname
    			step_SCREEN_OUTPUT++;
    		}
    		else if(step_SCREEN_OUTPUT == 2)
    		{
    			output = "3. Age";//db.age
    			step_SCREEN_OUTPUT++;
    		}
    		else if(step_SCREEN_OUTPUT == 3)
    		{
    			
    			output = "4. Gender";//db.gender
    			step_SCREEN_OUTPUT++;
    		}
    		else if(step_SCREEN_OUTPUT == 4)
    		{
    			output = "5. Marital Status";//db.maritalStatus
    			step_SCREEN_OUTPUT++;
    		}
    		else if(step_SCREEN_OUTPUT == 5)
    		{
    			output = "6. Home Address";//db.HomeAddress
    			step_SCREEN_OUTPUT++;
    		}
    		else if(step_SCREEN_OUTPUT == 6)
    		{
    			output = "7. Home Number";//db.Home Number
    			step_SCREEN_OUTPUT++;
    		}
    		else if(step_SCREEN_OUTPUT == 7)
    		{
    			output = "8. Cell Number";//db.Cell Number
    			step_SCREEN_OUTPUT++;
    		}
    		else if(step_SCREEN_OUTPUT == 8)
    		{
    			output = "9.Hospital";//db.Hospital
    			step_SCREEN_OUTPUT++;
    		}
    		else if(step_SCREEN_OUTPUT == 9)
    		{
    			output = "10. Hospital Address";//db.Hospital Address
    			step_SCREEN_OUTPUT++;
    		}
    		else if(step_SCREEN_OUTPUT == 10)
    		{
    			output = "11. Work Number";//db.workNumber
    			step_SCREEN_OUTPUT++;
    		}
    		else if(step_SCREEN_OUTPUT == 11)
    		{
    			output = "12. Specialty";//db.specialty
    			step_SCREEN_OUTPUT++;
    		}
    		else if(step_SCREEN_OUTPUT == 12)
    		{
    			output = "13. Friends List";
    			step_SCREEN_OUTPUT++;
    		}
    		
    		//jma342 - Feb 25th - 2:42 pm
    		else if(step_SCREEN_OUTPUT == 13)
    		{
    			output = "14. Return to Main Posting Board";
    			step_SCREEN_OUTPUT++;
    		}
    		
    		else if(step_SCREEN_OUTPUT == 14)
    		{
    			output = "Please select the number(1-14) for the desired option? ";
    			step_SCREEN_OUTPUT++;
    		}
    		//jma342 - Feb 25th - 2:42 pm
    		
    		else
    		{
    			step_SCREEN_OUTPUT = 0;
    			output = "userInput";
    			currentScreen = nextScreen;
    		}
    		
    	}
    	//changes made - jma 342 - Feb 18th
    	
    	//change made - jma 342 - feb 19th
    	else if(screen == this.FRIENDS_LIST_SCREEN)
    	{
    		if(this.step_SCREEN_OUTPUT == 0)
    		{
    			//retrieve records from the friends list
    			//output = rs.firstrecord
    			
    			//allow recordset to move to next record on
    			//reentry in to this function
    			//once the last record is added to the output variable
    			step_SCREEN_OUTPUT++;
    			
    		}
    		else if(this.step_SCREEN_OUTPUT == 1)
    		{
    			output = "Please select one of the following actions:";
    			step_SCREEN_OUTPUT++;
    		}
    		
    		else if(this.step_SCREEN_OUTPUT == 2)
    		{
    			output = "1. Add a friend";
    			step_SCREEN_OUTPUT++;
    		}
    		
    		else if(this.step_SCREEN_OUTPUT == 3)
    		{
    			output ="2. Delete a friend";
    			step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 4)
    		{
    			output = "3. View a friend's profile";
    			step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 5)
    		{
    			output ="4. See friend requests";
    			step_SCREEN_OUTPUT++;
    		}
    		//jma342 - Feb 25th - 2:57pm
    		else if(this.step_SCREEN_OUTPUT == 6)
    		{
    			output ="5. Return to Personal Information";
    			step_SCREEN_OUTPUT++;
    		}
    		//jma342 - Feb 25th - 2:57pm
    		else
    		{
    			output = "userInput";
    			this.step_SCREEN_OUTPUT = 0;
    			currentScreen = nextScreen;
    		}
    		
    	}
    	
    	else if(screen == this.ADD_FRIEND_SCREEN)
    	{
    		
    		if(this.step_SCREEN_OUTPUT == 0)
    		{
    			output = "Please enter the first name and last name(eg. John Doe):";
    			step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 1)
    		{
    			output = "userInput";
    			this.step_SCREEN_OUTPUT = 0;
    			currentScreen = nextScreen;
    		}
    	}
    	
    	else if(screen == this.FRIEND_REQUESTS_SCREEN)
    	{
    	
    		if(this.step_SCREEN_OUTPUT == 0)
    		{
    			//display all of the friend reqests on reccuring to this
    			//function using the recordset
    			//addin the last record to the counter
    			//increment the stepScreenOuput variable
    			step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 1)
    		{
				output = "Please enter the number of the desired friend from the list";
				step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 2)
    		{
    			output = "userInput";
    			this.step_SCREEN_OUTPUT = 0;
    			currentScreen = nextScreen;
    		}
    	}
		
    	//change made - jma 342 - feb 19th
    	
    	//change made - jma 342 - feb 18th
    	else if(screen == this.MARITAL_STATUS_SCREEN)
    	{
    		if(this.step_SCREEN_OUTPUT == 0)
    		{
    			output = "Edit Marital Status:";
    			step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 1)
    		{
    			output = " 1.Single";
    			step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 2)
    		{
    			output = "2.In a relationship";
    			step_SCREEN_OUTPUT++;
    		}
    		
    		else if(this.step_SCREEN_OUTPUT == 3)
    		{
    			output = "3.Married";
    			step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 4)
    		{
    			output = "4.Divored";
    			step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 5)
    		{
				output = "5. Widowed";
				step_SCREEN_OUTPUT++;
    		}
    		else
    		{
    			step_SCREEN_OUTPUT = 0;
    			output = "userInput";
    			currentScreen = nextScreen;
    		}
    	}
    	
    	else if(screen == this.GENDER_SCREEN)
    	{
    		if(this.step_SCREEN_OUTPUT == 0)
    		{
    			output = "Edit Gender: ";
    			this.step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 1)
    		{
    			output = "1.Female";
    			this.step_SCREEN_OUTPUT++;
    		}
    		else if(this.step_SCREEN_OUTPUT == 2)
    		{
    			output = "1.Male";
    			this.step_SCREEN_OUTPUT++;
    		}
    		else
    		{
    			output = "userInput";
    			this.step_SCREEN_OUTPUT = 0;
    			currentScreen = nextScreen;
    		}
    	}
    	
    	//change made - jma 342 - feb 18th
    	
    	return output;
    }
    
    public String loginScreenMenu(String input)
    {
    	String output = "";
    	
    	//prompt for username
    	if(step_LOG_IN_SCREEN == 0)
    	{
    		output = "1. New User ";
    		step_LOG_IN_SCREEN++;
    	}
    	
    	else if(step_LOG_IN_SCREEN == 1)
    	{
    		output = "2. Login ";
    		step_LOG_IN_SCREEN++;
    	}
    	
    	else if(step_LOG_IN_SCREEN == 2)
    	{
    		output = "3. Exit: ";
    		step_LOG_IN_SCREEN++;
    	}
    	
    	else if(step_LOG_IN_SCREEN == 3)
    	{
    		output = "userInput";
    		step_LOG_IN_SCREEN++;
    	}
    	
    	else if(step_LOG_IN_SCREEN == 4)
    	{
    		chosen_On_Screen_Action = input;
    		
    		output = "";
    		
    		this.step_LOG_IN_SCREEN++;
    	}
    	else if(this.step_LOG_IN_SCREEN >= 5)
    	{
	    if(chosen_On_Screen_Action.equals("1"))//steps for new user
	    	{	    		
		    	if(step_LOG_IN_SCREEN == 5)
		    	{
		    		output = "Username: ";
		    		step_LOG_IN_SCREEN++;
		    	}
		    	else if(step_LOG_IN_SCREEN == 6)
		    	{
		    		output = "userInput";
		    		step_LOG_IN_SCREEN++;
		    	}
		    	
		    	//prompt for password
		    	else if(step_LOG_IN_SCREEN == 7)
		    	{
		    		userName = input;
		    		
		    		output = "Password: ";
		    		
		    		
		    		step_LOG_IN_SCREEN++;
		    		
		    	}
		    	
		    	else if(step_LOG_IN_SCREEN == 8)
		    	{
		    		output = "userInput";
		    		step_LOG_IN_SCREEN++;
		    	}
		    	
		    	else if(step_LOG_IN_SCREEN == 9)
		    	{
		    		password = input;
		    		
		    		output = "Re-enter Password:";
		    		step_LOG_IN_SCREEN++;
		    	}
		    	
		    	else if(step_LOG_IN_SCREEN == 10)
		    	{
		    		output = "userInput";
		    		step_LOG_IN_SCREEN++;
		    	}
		    	
		    	else if(step_LOG_IN_SCREEN == 11)
		    	{
		    		if(password.equals(input))
		    		{
		    			step_LOG_IN_SCREEN++;
		    		}
		    		else
		    		{
		    			//if the user enters the wrong password for confirmation
		    			//the step_error_check variable is used to circulate this prompt
		    			//until the correct answer is entered
		    			if(this.step_ERROR_CHECK == 0)
		    			{
		    				output = "Please enter correct password: ";
		    				this.step_ERROR_CHECK++;
		    			}
		    			else if(this.step_ERROR_CHECK == 1)
		    			{
		    				output = "userInput";
		    				this.step_ERROR_CHECK = 0;
		    			}
		    			
		    		}
		    		
		    	}
		    	
		    	//add user database
		    	else if(step_LOG_IN_SCREEN == 12)
		    	{
		    		//addition to database successful
		    		step_LOG_IN_SCREEN = 0;
		    		
		    		try {
						st = con_2.createStatement();
						
						sqlString = "SELECT count(username) FROM users WHERE username = \'" + userName + "\'";
						rs = st.executeQuery(sqlString);
						
						rs.next();
						
						int count = rs.getInt(1);
						
						if (count != 0) {
							output = "Registration failed.\'" + userName + "\' is already registered. Press any key...";
//							loginScreenMenu(input);
							step_LOG_IN_SCREEN = 0;
						}
						else {
							sqlString = "INSERT INTO users (username, password) VALUES (\'" + userName + "\', \'" + password + "\')";
							count = 0;
							count = st.executeUpdate(sqlString);
	
							if (count != 0) {
								output = "Registration successful.";
							}
							else {
								output = "Registration failed.";
							}
							
							step_LOG_IN_SCREEN = 0;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
		    		//after a successful login user is directed to main posting board
		    		currentScreen = this.CURRENT_OUTPUT_SCREEN;
		    		nextScreen = MAIN_POSTING_BOARD_SCREEN;
		    		
		    		output = this.screenOutput(nextScreen);
		    		
		    	}
	    	}//steps for new user
	    	
	    	else if(chosen_On_Screen_Action.equals("2")) //steps for registered user
	    	{
		    	if(step_LOG_IN_SCREEN == 5)
		    	{
		    		output = "Username: ";
		    		step_LOG_IN_SCREEN++;
		    	}
		    	else if(step_LOG_IN_SCREEN == 6)
		    	{
		    		output = "userInput";
		    		step_LOG_IN_SCREEN++;
		    	}
		    	
		    	//prompt for password
		    	else if(step_LOG_IN_SCREEN == 7)
		    	{
		    		userName = input;
		    		
		    		output = "Password: ";
		    		
		    		
		    		step_LOG_IN_SCREEN++;
		    		
		    	}
		    	
		    	else if(step_LOG_IN_SCREEN == 8)
		    	{
		    		output = "userInput";
		    		step_LOG_IN_SCREEN++;
		    	}
		    
		    	
		    	//verify username and password -- if failure of either simply indicate either or failed
		    	else if(step_LOG_IN_SCREEN == 9)
		    	{
		    		//password = "";
		    		password = input;
		    		
		    		sqlString = "SELECT * FROM users WHERE username = \'" + userName + "\' AND password = \'" + password + "\'";
					try {
						rs = st.executeQuery(sqlString);
						
						if (rs.next() == false) {
							output = "Username and password combination is wrong";
							step_LOG_IN_SCREEN = 0;
						}
						else {
							output = "Access granted.";
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	        		//if verification successful
	        		//currently no db connection so verification isnot enforced
	        		step_LOG_IN_SCREEN = 0;
	        		
	        		//after a successful login user is directed to main posting board
	        		currentScreen = this.CURRENT_OUTPUT_SCREEN;
	        		nextScreen = MAIN_POSTING_BOARD_SCREEN;
	        		
	        		output = this.screenOutput(nextScreen);
	        		
	        	}
	    	}//steps for registered user
	    	  
	    	//added -- jma 342 - February 24th 2012
	    	else if(chosen_On_Screen_Action.equals("3"))//terminate connection
	    	{
	    		output = "killConnection";
	    	}
    	}
    	
    	
    	return output;
    		
    }
    
    //changes made - jma 342 - Feb 18th
    public String mainPostingBoardSreen(String input)
    {
    	String output = "";
    	
    	/*if(this.step_MAIN_POSTING_BOARD == 0)
    	{*/
    		//personal information screen
    		if(input.equals("1"))
    		{
    			this.currentScreen = this.CURRENT_OUTPUT_SCREEN;
    			this.nextScreen = this.PERSONAL_INFORMATION_SCREEN;
        		
        		output = this.screenOutput(nextScreen);
        	//	step_MAIN_POSTING_BOARD++;
    		}
    		//announcements screen
    		if(input.equals("2"))
    		{
    			this.currentScreen = this.CURRENT_OUTPUT_SCREEN;
    			this.nextScreen = this.ANNOUNCEMENTS_SCREEN;
        		
        		output = this.screenOutput(nextScreen);
        		//step_MAIN_POSTING_BOARD++;
    		}
    		//research publications screen
    		else if(input.equals("3"))
    		{
    			this.currentScreen = this.CURRENT_OUTPUT_SCREEN;
    			this.nextScreen = this.RESEARCH_PUBLICATIONS_SCREEN;
        		
        		output = this.screenOutput(nextScreen);
        		//step_MAIN_POSTING_BOARD++;
    		}
    		//public discussions screen
    		if(input.equals("4"))
    		{
    			this.currentScreen = this.CURRENT_OUTPUT_SCREEN;
    			this.nextScreen = this.PUBLIC_DISCUSSIONS_SCREEN;
        		
        		output = this.screenOutput(nextScreen);
        		//step_MAIN_POSTING_BOARD++;
    		}
    		//research group screen
    		else if(input.equals("5"))
    		{
    			this.currentScreen = this.CURRENT_OUTPUT_SCREEN;
    			this.nextScreen = this.RESEARCH_GROUPS_SCREEN;
        		
        		output = this.screenOutput(nextScreen);
        		//step_MAIN_POSTING_BOARD++;
    		}
    		//added -- jma 342 - February 24th 2012
    		
    		//privileges screen
    		else if(input.equals("6"))
    		{
    			this.currentScreen = this.CURRENT_OUTPUT_SCREEN;
    			this.nextScreen = this.PRIVILEGES_SCREEN;
        		
        		output = this.screenOutput(nextScreen);
    		}
    		
    		//return to log in screen
    		else if(input.equals("7"))
    		{
    			this.currentScreen = this.LOG_IN_SCREEN;
        		output =  loginScreenMenu(input);
        		
    		}
    		//added -- jma 342 - February 24th 2012

    	//}

    	return output;
    }
  //changes made - jma 342 - Feb 18th

    //changes made - jma 342 - feb 18th
    public String personalInformationScreen(String input)
    {
    	String output = "";
    	
    	if(this.step_PERSONAL_INFORMATION == 0)
    	{
			//Edit First Name
			if(input.equals("1"))
			{
				editField_On_Screen = "firstName";
	    		output = "Edit First Name: ";
	    		step_PERSONAL_INFORMATION++;
			}
			
			//Edit Last Name
			else if(input.equals("2"))
			{
				editField_On_Screen = "lastName";
	    		output = "Edit Last Name: ";
	    		step_PERSONAL_INFORMATION++;
			}
			
			//Edit Last Age
			else if(input.equals("3"))
			{
				editField_On_Screen = "Age";
	    		output = "Edit Age: ";
	    		step_PERSONAL_INFORMATION++;
			}
			
			//Edit Gender
			else if(input.equals("4"))
			{
				editField_On_Screen = "Gender";
	    		
	    		nextScreen = this.GENDER_SCREEN;
	    		currentScreen = this.CURRENT_OUTPUT_SCREEN;
	    		
	    		output = this.screenOutput(nextScreen);
	    		step_PERSONAL_INFORMATION = 0;

			}
			
			//Edit Marital Status
			else if(input.equals("5"))
			{
				editField_On_Screen = "maritalStatus";
	    		
	    		nextScreen = this.MARITAL_STATUS_SCREEN;
	    		currentScreen = this.CURRENT_OUTPUT_SCREEN;
	    		
	    		output = this.screenOutput(nextScreen);
	    		step_PERSONAL_INFORMATION = 0;
			}
			
			//Edit Home Address
			else if(input.equals("6"))
			{
				editField_On_Screen = "homeAddress";
	    		output = "Edit Home Address: ";
	    		step_PERSONAL_INFORMATION++;
			}
			
			//Edit Home Number
			else if(input.equals("7"))
			{
				editField_On_Screen = "homeNumber";
	    		output = "Edit Home Number: ";
	    		step_PERSONAL_INFORMATION++;
			}
			
			//Edit Cell Number
			else if(input.equals("8"))
			{
				editField_On_Screen = "cellNumber";
	    		output = "Edit Cell Number: ";
	    		step_PERSONAL_INFORMATION++;
			}
			
			//Edit Hospital
			else if(input.equals("9"))
			{
				editField_On_Screen = "hospital";
	    		output = "Edit Hospital: ";
	    		step_PERSONAL_INFORMATION++;
			}
			
			//Edit Hospital Address
			else if(input.equals("10"))
			{
				editField_On_Screen = "hospitalAddress";
	    		output = "Edit Hospital Address: ";
	    		step_PERSONAL_INFORMATION++;
			}
			
			//Edit work Number
			else if(input.equals("11"))
			{
				editField_On_Screen = "workNumber";
	    		output = "Edit Work Number: ";
	    		step_PERSONAL_INFORMATION++;
			}
			
			//Edit specialty
			else if(input.equals("12"))
			{
				editField_On_Screen = "specialty";
	    		output = "Edit Specialty: ";
	    		step_PERSONAL_INFORMATION++;
			}
			
						
			//Edit Friends list
			else if(input.equals("13"))
			{
				editField_On_Screen = "Friends List";
				
				currentScreen = this.CURRENT_OUTPUT_SCREEN;
				this.nextScreen = this.FRIENDS_LIST_SCREEN;
				
				//retrieve recordset of friends list from database
	    		output = this.screenOutput(nextScreen /*,recordset of friendslist*/); 
	    		
			}
			
			//Return to Main Posting Board
			else if(input.equals("14"))
			{
        		
				step_PERSONAL_INFORMATION = 0;
				
				currentScreen = this.CURRENT_OUTPUT_SCREEN;
				this.nextScreen = this.MAIN_POSTING_BOARD_SCREEN;
				
	    		output = this.screenOutput(nextScreen /*,recordset of friendslist*/); 
	    		
			}
			
    	}
    	 //changes made - jma 342 - feb 18th
    	
    	//changes made jma342 - Feb 18
    	else if(this.step_PERSONAL_INFORMATION == 1)
    	{
    		output = "userInput";
    		step_PERSONAL_INFORMATION ++;
    	}
    	
    	else if(this.step_PERSONAL_INFORMATION == 2)
    	{
    		//update the database with edited field
    		//requery database for updated record
    		
    		//set the screen to have its information displayed in the
    		//output screen function
    		this.nextScreen = currentScreen;
    		
    		//set current screen to output screen
    		this.currentScreen = this.CURRENT_OUTPUT_SCREEN;
    		
    		output = this.screenOutput(nextScreen/*,updated recordset*/);	
    		step_PERSONAL_INFORMATION = 0;
    	}
    	//changes made jma342 - Feb 18
    	
    	
    	return output;
    }
    
    //change made - jma 342 - Feb 18th
    public String maritalStatusScreen(String input)
    {
    	String output = "";
    	
    	//single
    	if(this.step_MARITAL_STATUS == 0)
    	{
	    	if(input.equals("1") || input.equals("2") || input.equals("3") ||
	    			input.equals("4") || input.equals("5"))
	    	{
	    		//TO DO: update the user's record with the chosen marital status
	    		
	    		currentScreen = this.CURRENT_OUTPUT_SCREEN;
	    		nextScreen = this.PERSONAL_INFORMATION_SCREEN;
	    		
	    		output = this.screenOutput(nextScreen);
	    	}
	    	else
	    	{
	    		output = "Please select a number from 1- 5";
	    		step_MARITAL_STATUS++;
	    		
	    	}
    	}
    	
    	else if(step_MARITAL_STATUS == 1)
    	{
    		output = "userInput";
    		step_MARITAL_STATUS = 0;
    	}
    	
    	return output;
	    	
    }
  //change made - jma 342 - Feb 18th
    public String genderScreen(String input)
    {
    	String output = "";
    	
    	//single
    	if(this.step_GENDER == 0)
    	{
	    	if(input.equals("1") || input.equals("2"))
	    	{
	    		//TO DO: update the user's record with the chosen gender
	    		
	    		currentScreen = this.CURRENT_OUTPUT_SCREEN;
	    		nextScreen = this.PERSONAL_INFORMATION_SCREEN;
	    		
	    		output = this.screenOutput(nextScreen);
	    	}
	    	else
	    	{
	    		output = "Please select numbers 1 - 2";
	    		this.step_GENDER++;
	    		
	    	}
    	}
    	
    	else if(this.step_GENDER == 1)
    	{
    		output = "userInput";
    		this.step_GENDER = 0;
    	}
    	
    	return output;
	    	
    }

  //change made - jma 342 - Feb 18th	


  //change made - jma 342 - Feb 19th	
    public String friendsListSreen(String input)
    {
    	String output = " ";
    	
    	if(this.step_FRIENDS_LIST == 0)
    	{
    		//add a friend
    		if(input.equals("1"))
    		{
    			currentScreen = this.CURRENT_OUTPUT_SCREEN;
    			nextScreen = this.ADD_FRIEND_SCREEN;
    			
    			output = this.screenOutput(nextScreen /*,recordset of friendslist*/);
    			
    			this.step_FRIENDS_LIST=0;
    		}
    		
    		//delete a friend
    		else if(input.equals("2"))
    		{
    			this.chosen_On_Screen_Action = input;
    			
        		output = "Please enter the number of the desired friend from the list";
        	
        		this.step_FRIENDS_LIST++;
    		}
    		
    		else if(input.equals("4"))
    		{
    			currentScreen = this.CURRENT_OUTPUT_SCREEN;
    			nextScreen = this.FRIEND_REQUESTS_SCREEN;
    			
    			output = this.screenOutput(nextScreen /*,recordset of friendslist*/);
    			this.step_FRIENDS_LIST=0;
    			
    		}
    		
    		else if(input.equals("5"))
    		{
    			currentScreen = this.CURRENT_OUTPUT_SCREEN;
    			nextScreen = this.PERSONAL_INFORMATION_SCREEN;
    			
    			output = this.screenOutput(nextScreen /*,recordset of friendslist*/);
    			this.step_FRIENDS_LIST=0;
    			
    		}

    		
    	}
    	
    	else if(this.step_FRIENDS_LIST == 1)
    	{
    		output = "userInput";
    		this.step_FRIENDS_LIST++;
    	}
    	
    	else if(this.step_FRIENDS_LIST == 2)
    	{
    		if(this.chosen_On_Screen_Action.equals("2"))
    		{
    			/*rs.recordset("delete from friends where
    			userid = userid and friend id = 'id of the friend chosen' or 
    			userid = 'id of the friend chosen' and friend id = 'user id')*/
    			
    			//output = build a string with records from above recordset
    			
    			output = "Friend has been deleted...press any key to continue:";
  
    			this.step_FRIENDS_LIST++;
    		}
    	}
    	
    	else if(this.step_FRIENDS_LIST == 3)
    	{
    		output = "userInput";
    		this.step_FRIENDS_LIST++;
    	}
    	
    	else if(this.step_FRIENDS_LIST == 4)
    	{
    		if(this.chosen_On_Screen_Action.equals("2"))
    		{
    			currentScreen = this.CURRENT_OUTPUT_SCREEN;
    			nextScreen = this.FRIENDS_LIST_SCREEN;
    			
    			output = this.screenOutput(nextScreen /*,recordset of friendslist*/);
    			this.step_FRIENDS_LIST = 0;
    		}
    	}
    	
    	return output;
    }
    //change made - jma 342 - Feb 19th
    
  //change made - jma 342 - Feb 19th
    public String addFriendSreen(String input)
    {
    	String output = "";
    	
    	if(this.step_ADD_FRIEND == 0)
    	{
    		/*rs.recordset("Select First Name, Last Name, Home Address where
			firstname = 'firstname' and lastname = 'lastname'")*/
		
    		//allow recordset to move to next record on
			//reentry in to this function
			//once the last record is added to the output variable
    		
    		output = "Please select the desired friend by entering a number";
    		step_ADD_FRIEND++;
			//increment step_AddFriend
    	
    	}
    	
    	else if(this.step_ADD_FRIEND == 1)
    	{
    		output = "userInput";
    		step_ADD_FRIEND++;
    	}
    	
    	else if(this.step_ADD_FRIEND == 2)
    	{
	    	/*rs.recordset("Insert userID, input into
			 * friendRequest table)
			 */
			output = "Friend request has been sent...press any key to continue";
			
			step_ADD_FRIEND++;
    	}
    	
    	else if(this.step_ADD_FRIEND == 3)
    	{
    		output = "userInput";
    		step_ADD_FRIEND++;
    	}
    	
    	else if(this.step_ADD_FRIEND == 4)
    	{
    		currentScreen = this.CURRENT_OUTPUT_SCREEN;
			nextScreen = this.FRIENDS_LIST_SCREEN;
			
			output = this.screenOutput(nextScreen /*,recordset of friendslist*/);
			this.step_ADD_FRIEND = 0;
    	}
	
    	return output;
    }
    
    public String friendRequestScreen(String input)
    {
    	String output = "";
    	
    	if(this.step_FRIEND_REQUEST == 0)
    	{
    		//display the friend request chosen
    		//output =
    		step_FRIEND_REQUEST++;
    	}
    	else if(this.step_FRIEND_REQUEST == 1)
    	{
    		output = "Please enter A - accept or D - Deny:";
    		step_FRIEND_REQUEST++;
    	}
    	else if(this.step_FRIEND_REQUEST == 2)
    	{
    		output = "userInput";
    		step_FRIEND_REQUEST++;
    	}
    	else if(this.step_FRIEND_REQUEST == 3)
    	{
    	
    		if(input.equals("A") || input.equals("D"))
    		{
	    		//if user chooses A then update the friends listing for both
	    		//requester and requestee and remove the request from the friends request table
	    		
	    		//else just remove the request from the friends list table
    		}
    		
    		output = "Request accepted/denied...press any key to continue";
    		step_FRIEND_REQUEST++;
    	}
    	else if(step_FRIEND_REQUEST == 4)
    	{
    		output = "userInput";
    		step_FRIEND_REQUEST++;
    	}
    	
    	else if(step_FRIEND_REQUEST == 5)
    	{
    		currentScreen = this.CURRENT_OUTPUT_SCREEN;
			nextScreen = this.FRIEND_REQUESTS_SCREEN;
			
			output = this.screenOutput(nextScreen /*,recordset of friendslist*/);
			this.step_FRIEND_REQUEST = 0;
    	}
    	
    	return output;
    }
  //change made - jma 342 - Feb 19th
   
}
