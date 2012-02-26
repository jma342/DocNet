import java.net.*;
import java.io.*;
import java.sql.*;

/*TIP TO YERZHAN LOOK TO PLACE YOUR DATABASE INSERTION, DELETION AND RETRIEVAL IN A 
 * FUNCTION FOR EASE OF USE....SO YOU WOULD PASS THE NECESSARY PARAMETERS TO EACH OF THE FUNCTIONS
 * TO EXECUTE THE REQUISITE QUERIES
 * 
 * TIP TO JAMAAL CREATE THE VIEW FRIEND PROFILE EQUIVALENTS OF SCREENS MERGED IN FROM CINDY*/

public class DocNetProtocol {


	private Variables variables = new Variables();
	
  //jma342 -- feb 21st -- added constructor to initialise db connections
    public DocNetProtocol(Connection c1, Connection c2)
    {
    	variables.con_1 = c1;
    	variables.con_2 = c2;
    }
    
    public String processRequest(String input)
    {	
    	String output = "";
    	
    	if(variables.currentScreen == variables.LOG_IN_SCREEN)
    	{
    		output =  loginScreenMenu(input);
    	}
    	else if(variables.currentScreen == variables.MAIN_POSTING_BOARD_SCREEN)
    	{
    		output = mainPostingBoardSreen(input);
    	}
    	
    	else if(variables.currentScreen == variables.PERSONAL_INFORMATION_SCREEN)
    	{
    		output =  personalInformationScreen(input);
    	}
    	
    	//jma342 - feb 25 1:52AM - Merging cindy's code
    	else if(variables.currentScreen == variables.RESEARCH_PUBLICATIONS_SCREEN)
    	{
    		output = researchPublicationsScreen(input);
    	}
    	
    	else if(variables.currentScreen == variables.ANNOUNCEMENTS_SCREEN)
    	{
    	    output = announcementScreen(input);
    	}
    	else if(variables.currentScreen == variables.PUBLIC_DISCUSSIONS_SCREEN)
    	{
    		output = discussionScreen(input);
    	}
    	else if(variables.currentScreen == variables.PUBLIC_DISCUSSION_TOPIC_SCREEN)
    	{
    		output = discussionTopicScreen(input);
    	}
    	//jma342 - feb 25 1:52AM - Merging cindy's code
    	
    	else if(variables.currentScreen == variables.CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN)
    	{
    		output = created_PubDisc_ResGrp_Priviliges_Screen(input);
    	}
    	
    	//change made - jma 342 - feb 18th
    	else if(variables.currentScreen == variables.MARITAL_STATUS_SCREEN)
    	{
    		output = this.maritalStatusScreen(input);
    	}
    	else if(variables.currentScreen == variables.GENDER_SCREEN)
    	{
    		output = this.genderScreen(input);
    	}
    	//change made - jma 342 - feb 18th
    	
    	else if(variables.currentScreen == variables.user_FRIENDS_LIST_SCREEN)
    	{
    		output = this.user_friendsListSreen(input);
    	}
    	
    	//change made - jma 342 - feb 19th
    	else if(variables.currentScreen == this.variables.ADD_FRIEND_SCREEN)
    	{
    		output = this.addFriendSreen(input);
    	}
    	
    	else if(variables.currentScreen == this.variables.user_FRIEND_REQUESTS_SCREEN)
    	{
    		output = this.user_friendRequestScreen(input);
    	}
    	//change made - jma 342 - feb 19th
    	
    	//change made - jma342 - feb25th
    	else if(variables.currentScreen == this.variables.FRIEND_MAIN_POSTING_BOARD_SCREEN)
    	{
    		output = this.friendMainPostingBoardSreen(input);
    	}
    	else if(variables.currentScreen == this.variables.FRIEND_PERSONAL_INFORMATION_SCREEN)
    	{
    		output = this.friendPersonalInformationScreen(input);
    	}
    	else if(variables.currentScreen == this.variables.FRIEND_FRIENDS_LIST_SCREEN)
    	{
    		output = this.friend_friendsListSreen(input);
    	}
    	//change made - jma342 - feb25th
    	else if(variables.currentScreen == this.variables.PRIVILEGES_SCREEN)
    	{
    		output = this.PrivilegeScreen(input);
    	}
    	//add new screen for reset privilege -- rw 446 -- Feb 26th
    	else if(variables.currentScreen == this.variables.RESET_PRIVILEGE_SCRREN)
    	{
    		output = this.ResetPrivilegeScreen(input);
    	}
    	else if(variables.currentScreen == variables.CURRENT_OUTPUT_SCREEN)
    	{
    		output = screenOutput(variables.nextScreen);
    	}
    	return output;
    	
    }
    
    //displays the layout of each screen
    public String screenOutput(int screen/*,recordset*/)
    {
    	String output = "";
    	
    	//changes made - jma 342 - Feb 25th 4:20pm
    	if(screen == this.variables.MAIN_POSTING_BOARD_SCREEN || screen == this.variables.FRIEND_MAIN_POSTING_BOARD_SCREEN)
    	{
    		
    		if(this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			output = "1. Personal Information";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 1)
    		{
    			output = "2.Announcements";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 2)
    		{
    			output = "3.Research Publications";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 3)
    		{
    			output = "4.Public Discussions";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 4)
    		{
    				
    			output = "5.Research Groups";
    			
    			variables.step_SCREEN_OUTPUT++;
    		}
    		
    		else if(this.variables.step_SCREEN_OUTPUT == 5)
    		{
    			//changes made - jma 342 - Feb 25th 4:20pm - only allows this option to be viewed if 
    			//owner of main posting board is person viewing it
    			if(screen == this.variables.MAIN_POSTING_BOARD_SCREEN)
    			{
    				output = "6.Priviliges(Posting Boards/Regions)";
    			}
    			else if(screen == this.variables.FRIEND_MAIN_POSTING_BOARD_SCREEN)
    			{
    				output = "6.Return to Friends List Screen";
    			}
    			
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 6)
    		{
    			//changes made - jma 342 - Feb 25th 4:20pm - only allows this option to be viewed if 
    			//owner of main posting board is person viewing it
    			if(screen == this.variables.MAIN_POSTING_BOARD_SCREEN)
    			{
    				output = "7.Log Out";
    			}
    			
    			variables.step_SCREEN_OUTPUT++;
    		}
    		//added -- jma 342 - February 24th 2012
    		
    		else if(this.variables.step_SCREEN_OUTPUT == 7)
    		{
    			output = "Please select the number(1-5) for the desired region? ";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else
    		{
    			variables.currentScreen = variables.nextScreen;
    			variables.step_SCREEN_OUTPUT = 0;
    			output = "userInput";
    		}
    	}
    	else if(screen == this.variables.PERSONAL_INFORMATION_SCREEN || screen == this.variables.FRIEND_PERSONAL_INFORMATION_SCREEN)
    	{
    		//try to figure how to clearscreen
    		if(this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			output = "1. First Name";  //+ db.firtname
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 1)
    		{
    			output ="2. Last Name";//db.lastname
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 2)
    		{
    			output = "3. Age";//db.age
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 3)
    		{
    			
    			output = "4. Gender";//db.gender
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 4)
    		{
    			output = "5. Marital Status";//db.maritalStatus
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 5)
    		{
    			output = "6. Home Address";//db.HomeAddress
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 6)
    		{
    			output = "7. Home Number";//db.Home Number
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 7)
    		{
    			output = "8. Cell Number";//db.Cell Number
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 8)
    		{
    			output = "9.Hospital";//db.Hospital
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 9)
    		{
    			output = "10. Hospital Address";//db.Hospital Address
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 10)
    		{
    			output = "11. Work Number";//db.workNumber
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 11)
    		{
    			output = "12. Specialty";//db.specialty
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 12)
    		{
    			output = "13. Friends List";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		
    		//jma342 - Feb 25th - 2:42 pm
    		else if(variables.step_SCREEN_OUTPUT == 13)
    		{
    			if(screen == this.variables.PERSONAL_INFORMATION_SCREEN)
    			{
    				output = "14. Return to Main Posting Board";
    			}
    			else if(screen == this.variables.FRIEND_PERSONAL_INFORMATION_SCREEN)
    			{
    				output = "14. Return to Friend's Main Posting Board";
    			}
    			variables.step_SCREEN_OUTPUT++;
    		}
    		
    		else if(variables.step_SCREEN_OUTPUT == 14)
    		{
    			output = "Please select the number(1-14) for the desired option? ";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		//jma342 - Feb 25th - 2:42 pm
    		
    		else
    		{
    			variables.step_SCREEN_OUTPUT = 0;
    			output = "userInput";
    			variables.currentScreen = variables.nextScreen;
    		}
    		
    	}
    	//changes made - jma 342 - Feb 18th
    	
    	//jma342 - Feb26th 1:54AM - merging in Cindy's code
    	else if(screen == this.variables.RESEARCH_PUBLICATIONS_SCREEN)
    	{
    		if (this.variables.step_SCREEN_OUTPUT==0)
    		{
    			//get research publications from db to display
        		output = "get publications from db";
        		variables.step_SCREEN_OUTPUT++;
    		}
    		else if (variables.step_SCREEN_OUTPUT==1)
    		{
    			output = "1. Add a publication";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if (variables.step_SCREEN_OUTPUT==2)
    		{
    			output = "2. Delete a publication";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT ==3)
    		{
    			output = "Please select number to perform desired action: ";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else
    		{
    			variables.step_SCREEN_OUTPUT = 0;
    			output = "userInput";
    			variables.currentScreen = variables.nextScreen;
    		}
    	    
    	}
    	
    	//jma342 - Feb26th 1:54AM - merging in Cindy's code
    	else if(screen == this.variables.ANNOUNCEMENTS_SCREEN)
    	{
    		if(variables.step_SCREEN_OUTPUT == 0)
    		{
    		  output = "display the announcement retrived from database\n";
    		  variables.step_SCREEN_OUTPUT++;
    		  
    		}
    		else if(variables.step_SCREEN_OUTPUT== 1)
    		{
    			output = "1. Add an announcement";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT ==2)
    		{
    			output = "2. Delete an announcement";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else
    		{
    			variables.step_SCREEN_OUTPUT = 0;
    			output = "userInput";
    			variables.currentScreen = variables.nextScreen;
    		}
    	}
    	
    	//jma342 - Feb26th 1:54AM - merging in Cindy's code
    	else if(screen==this.variables.PUBLIC_DISCUSSIONS_SCREEN)
    	{
    		if(variables.step_SCREEN_OUTPUT ==0)
    		{
    			output = "retrieve public discussion topics from database";
    			variables.step_SCREEN_OUTPUT++;
    		}
    	
    		else if(variables.step_SCREEN_OUTPUT == 1)
    		{
    			output = "1. Add a new discussion";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 2)
    		{
    			output = "2. Delete a discussion";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else
    		{
    			variables.step_SCREEN_OUTPUT = 0;
    			output = "userInput";
    			variables.currentScreen = variables.nextScreen;
    		}
    	}
    	
    	//jma342 - Feb26th 1:54AM - merging in Cindy's code
    	else if(screen==this.variables.PUBLIC_DISCUSSION_TOPIC_SCREEN)
    	{
    		if(variables.step_SCREEN_OUTPUT ==0)
    		{
    			output = "retrieve discussion comments from database";
    			variables.step_SCREEN_OUTPUT++;
    		}

    		else if(variables.step_SCREEN_OUTPUT == 1)
    		{
    			output = "1. Add a new comment";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 6)
    		{
    			output = "2. Delete a comment";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else
    		{
    			variables.step_SCREEN_OUTPUT = 0;
    			output = "userInput";
    			variables.currentScreen = variables.nextScreen;
    		}
    	}
    	//jma342 - Feb26th 1:54AM - merging in Cindy's code
    	
    	//change made - jma 342 - feb 19th
    	else if(screen == this.variables.user_FRIENDS_LIST_SCREEN || screen == this.variables.FRIEND_FRIENDS_LIST_SCREEN)
    	{
    		if(this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			//retrieve records from the friends list
    			//output = rs.firstrecord
    			
    			//allow recordset to move to next record on
    			//reentry in to this function
    			//once the last record is added to the output variable
    			variables.step_SCREEN_OUTPUT++;
    			
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 1)
    		{
    			output = "Please select one of the following actions:";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		
    		else if(this.variables.step_SCREEN_OUTPUT == 2)
    		{
    			//jma342 - Feb 25th - 7:00pm - allows user to view a friend's friend list
    			//with only the necessary actions.
    			
    			if(screen == this.variables.user_FRIENDS_LIST_SCREEN)
    			{
    				output = "1. Add a friend";
    				variables.step_SCREEN_OUTPUT++;
    			}
    			else if(screen == this.variables.FRIEND_FRIENDS_LIST_SCREEN)
    			{
    				this.variables.step_SCREEN_OUTPUT = 6;
    			}
    			
    			//jma342 - Feb 25th - 7:00pm - allows user to view a friend's friend list
    			//with only the necessary actions.
    			
    		}
    		
    		else if(this.variables.step_SCREEN_OUTPUT == 3)
    		{
    			output ="2. Delete a friend";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 4)
    		{
    			output = "3. View a friend's profile";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 5)
    		{
    			output ="4. See friend requests";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		//jma342 - Feb 25th - 2:57pm
    		else if(this.variables.step_SCREEN_OUTPUT == 6)
    		{
    			if(screen == this.variables.user_FRIENDS_LIST_SCREEN)
    			{
    				output ="5. Return to Personal Information";

    			}
    			else if(screen == this.variables.FRIEND_FRIENDS_LIST_SCREEN)
    			{
    				output ="1. Return to Friend's Personal Information";
    			}
    			variables.step_SCREEN_OUTPUT++;
    			
    		}
    		//jma342 - Feb 25th - 2:57pm
    		else
    		{
    			output = "userInput";
    			this.variables.step_SCREEN_OUTPUT = 0;
    			variables.currentScreen = variables.nextScreen;
    		}
    		
    	}
    	
    	else if(screen == this.variables.ADD_FRIEND_SCREEN)
    	{
    		//jma342 - Feb 25 - 3:22pm - informs user to return to previous screen
    		if(this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			output = "Please enter the first name and last name(eg. John Doe, enter EXIT to return to previous screen):";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 1)
    		{
    			output = "userInput";
    			this.variables.step_SCREEN_OUTPUT = 0;
    			variables.currentScreen = variables.nextScreen;
    		}
    	}
    	
    	//jma342 - feb26th - 3:58 am
    	else if(screen == this.variables.CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN)
    	{
    	
    		if(this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			output = "1. Set View Privileges";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 1)
    		{
    			output = "2. Set Post Privileges";
				variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 2)
    		{
    			output = "userInput";
    			this.variables.step_SCREEN_OUTPUT = 0;
    			variables.currentScreen = variables.nextScreen;
    		}
    	}
    	
    	else if(screen == this.variables.user_FRIEND_REQUESTS_SCREEN)
    	{
    	
    		if(this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			//display all of the friend reqests on reccuring to this
    			//function using the recordset
    			//addin the last record to the counter
    			//increment the stepScreenOuput variable
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 1)
    		{
				output = "Please enter the number of the desired friend from the list(enter -1 to cancel)";
				variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 2)
    		{
    			output = "userInput";
    			this.variables.step_SCREEN_OUTPUT = 0;
    			variables.currentScreen = variables.nextScreen;
    		}
    	}
		
    	//change made - jma 342 - feb 19th
    	
    	//change made - jma 342 - feb 18th
    	else if(screen == this.variables.MARITAL_STATUS_SCREEN)
    	{
    		if(this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			output = "Edit Marital Status:";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 1)
    		{
    			output = " 1.Single";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 2)
    		{
    			output = "2.In a relationship";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		
    		else if(this.variables.step_SCREEN_OUTPUT == 3)
    		{
    			output = "3.Married";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 4)
    		{
    			output = "4.Divored";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 5)
    		{
				output = "5. Widowed";
				variables.step_SCREEN_OUTPUT++;
    		}
    		else
    		{
    			variables.step_SCREEN_OUTPUT = 0;
    			output = "userInput";
    			variables.currentScreen = variables.nextScreen;
    		}
    	}
    	
    	else if(screen == this.variables.GENDER_SCREEN)
    	{
    		if(this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			output = "Edit Gender: ";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 1)
    		{
    			output = "1.Female";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 2)
    		{
    			output = "1.Male";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else
    		{
    			output = "userInput";
    			this.variables.step_SCREEN_OUTPUT = 0;
    			variables.currentScreen = variables.nextScreen;
    		}
    	}
    	
    	//change made - jma 342 - feb 18th
    	
    	//change made - rw  446  -feb 26th
    	//add in privilege screen
    	else if(screen == this.variables.PRIVILEGES_SCREEN)
    	{
    		if (this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			output = "Regions";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 1)
    		{
    			output = "1. Personal Information";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 2)
    		{
    			output = "2. Announcements";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 3)
    		{
    			output = "3. Research Publications";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 4)
    		{
    			output = "4. Public Discussions";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 5)
    		{
    			output = "5. Research Group";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 6)
    		{
    			output = "Posting Board";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 7)
    		{
    			output = "6. Announcements";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 8)
    		{
    			output = "7. Public Discussion";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 9)
    		{
    			output = "Please select 1-5 to edit privilege for regions and 6-7 for posting board";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else
    		{
    			output = "userInput";
    			this.variables.step_SCREEN_OUTPUT = 0;
    			variables.currentScreen = variables.nextScreen;
    		}
    	}
    	//add new screen for reset privilege --rw 446 -- Feb 26th 2012
    	else if (screen == this.variables.RESET_PRIVILEGE_SCRREN)
    	{
    		if (this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			output = "1. Reset View Privilege";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if (this.variables.step_SCREEN_OUTPUT == 1)
    		{
    			output = "2. Reset Post Privilege";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 2)
    		{
    			output = "Pleae select the number to reset privilege";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else
    		{
    			output = "userInput";
    			this.variables.step_SCREEN_OUTPUT = 0;
    			variables.currentScreen = variables.nextScreen;
    		}
    		  			
    	}
    	return output;
    }
    
    public String loginScreenMenu(String input)
    {
    	String output = "";
    	
    	//prompt for username
    	if(variables.step_LOG_IN_SCREEN == 0)
    	{
    		output = "1. New User ";
    		variables.step_LOG_IN_SCREEN++;
    	}
    	
    	else if(variables.step_LOG_IN_SCREEN == 1)
    	{
    		output = "2. Login ";
    		variables.step_LOG_IN_SCREEN++;
    	}
    	
    	else if(variables.step_LOG_IN_SCREEN == 2)
    	{
    		output = "3. Exit: ";
    		variables.step_LOG_IN_SCREEN++;
    	}
    	
    	else if(variables.step_LOG_IN_SCREEN == 3)
    	{
    		output = "userInput";
    		variables.step_LOG_IN_SCREEN++;
    	}
    	
    	else if(variables.step_LOG_IN_SCREEN == 4)
    	{
    		variables.chosen_On_Screen_Action = input;
    		
    		output = "";
    		
    		this.variables.step_LOG_IN_SCREEN++;
    	}
    	else if(this.variables.step_LOG_IN_SCREEN >= 5)
    	{
	    if(variables.chosen_On_Screen_Action.equals("1"))//steps for new user
	    	{	    		
		    	if(variables.step_LOG_IN_SCREEN == 5)
		    	{
		    		output = "Username: ";
		    		variables.step_LOG_IN_SCREEN++;
		    	}
		    	else if(variables.step_LOG_IN_SCREEN == 6)
		    	{
		    		output = "userInput";
		    		variables.step_LOG_IN_SCREEN++;
		    	}
		    	
		    	//prompt for password
		    	else if(variables.step_LOG_IN_SCREEN == 7)
		    	{
		    		variables.userName = input;
		    		
		    		output = "Password: ";
		    		
		    		
		    		variables.step_LOG_IN_SCREEN++;
		    		
		    	}
		    	
		    	else if(variables.step_LOG_IN_SCREEN == 8)
		    	{
		    		output = "userInput";
		    		variables.step_LOG_IN_SCREEN++;
		    	}
		    	
		    	else if(variables.step_LOG_IN_SCREEN == 9)
		    	{
		    		variables.password = input;
		    		
		    		output = "Re-enter Password:";
		    		variables.step_LOG_IN_SCREEN++;
		    	}
		    	
		    	else if(variables.step_LOG_IN_SCREEN == 10)
		    	{
		    		output = "userInput";
		    		variables.step_LOG_IN_SCREEN++;
		    	}
		    	
		    	else if(variables.step_LOG_IN_SCREEN == 11)
		    	{
		    		if(variables.password.equals(input))
		    		{
		    			//System.out.println("passwords match");
		    			
		    			variables.step_LOG_IN_SCREEN++;
		    		}
		    		else
		    		{
		    			//if the user enters the wrong password for confirmation
		    			//the variables.step_ERROR_CHECK variable is used to circulate this prompt
		    			//until the correct answer is entered
		    			if(this.variables.step_ERROR_CHECK == 0)
		    			{
		    				output = "Please enter correct password: ";
		    				this.variables.step_ERROR_CHECK++;
		    			}
		    			else if(this.variables.step_ERROR_CHECK == 1)
		    			{
		    				output = "userInput";
		    				this.variables.step_ERROR_CHECK = 0;
		    			}
		    			
		    		}
		    		
		    	}
		    	
		    	//adding new user
		    	else if(variables.step_LOG_IN_SCREEN == 12)
		    	{
		    		//addition to database successful
		    		
		    		int rsCount = 0;//jma342 - feb26 10:51 pm - holds the recordsetCount
		    		
		    		try 
		    		{
						variables.st = variables.con_2.createStatement();
						
						variables.sqlString = "SELECT username FROM users WHERE username = \'" + variables.userName + "\'";
						variables.rs = variables.st.executeQuery(variables.sqlString);
						
						//jma342 - feb26 10:51 pm - determines if recordset has anything
						while(variables.rs.next())
						{
							rsCount++;
							break;
						}
						
						if (rsCount != 0) 
						{
							output = "Registration failed.\'" + variables.userName + "\' is already registered. " +
									"Press any key to continue...";
							variables.failedLogIn = true;
							variables.step_LOG_IN_SCREEN++;
						}
						else 
						{
							variables.sqlString = "INSERT INTO users (username, password) VALUES " +
									"(\'" + variables.userName + "\', \'" + variables.password + "\')";
							rsCount = 0;
							rsCount = variables.st.executeUpdate(variables.sqlString);
	
							if (rsCount != 0) 
							{
								output = "Registration successful.";
								variables.failedLogIn = false;
								variables.step_LOG_IN_SCREEN++;
							}
							else 
							{
								output = "Registration failed....press any key to continue";
								variables.failedLogIn = true;
								variables.step_LOG_IN_SCREEN++;
							}
							
							
						}
					}
		    		catch (SQLException e) 
		    		{
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
		    	}
		    		

		    	//after a successful login user is directed to main posting board
		    	else if(variables.step_LOG_IN_SCREEN == 13)
		    	{
		    		//jma342 - feb 26th 11:15pm - refreshes the log in screen on failed login attempts
		    		if(variables.failedLogIn)
		    		{
		    			output = "userInput";
		    			variables.failedLogIn = false;
		    			variables.step_LOG_IN_SCREEN = 0;
		    		}
		    		else
		    		{
			    		variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
			    		variables.nextScreen = variables.MAIN_POSTING_BOARD_SCREEN;
			    		
			    		output = this.screenOutput(variables.nextScreen);
			    		variables.step_LOG_IN_SCREEN = 0;
		    		}
		    		
		    	}
		    	
	    	}//steps for new user
	    	

	    	else if(variables.chosen_On_Screen_Action.equals("2"))//steps for registered user
	    	{
		    	if(variables.step_LOG_IN_SCREEN == 5)
		    	{
		    		output = "Username: ";
		    		variables.step_LOG_IN_SCREEN++;
		    	}
		    	else if(variables.step_LOG_IN_SCREEN == 6)
		    	{
		    		output = "userInput";
		    		variables.step_LOG_IN_SCREEN++;
		    	}
		    	
		    	//prompt for password
		    	else if(variables.step_LOG_IN_SCREEN == 7)
		    	{
		    		variables.userName = input;
		    		
		    		output = "Password: ";
		    		
		    		
		    		variables.step_LOG_IN_SCREEN++;
		    		
		    	}
		    	
		    	else if(variables.step_LOG_IN_SCREEN == 8)
		    	{
		    		output = "userInput";
		    		variables.step_LOG_IN_SCREEN++;
		    	}
		    
		    	
		    	//verify username and password -- if failure of either simply indicate either or failed
		    	else if(variables.LOG_IN_SCREEN == 9)
		    	{
		    		//password = "";
		    		variables.password = input;
		    		
		    		variables.sqlString = "SELECT * FROM users WHERE username = \'" + 
		    				variables.userName + "\' AND password = \'" + variables.password + "\'";
					try {
						variables.rs = variables.st.executeQuery(variables.sqlString);
						
						if (variables.rs.next() == false) 
						{
							output = "Username and password combination is wrong";
							variables.step_LOG_IN_SCREEN = 0;
						}
						else 
						{
							output = "Access granted.";
						}
						
					} 
					catch (SQLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	        		//if verification successful
	        		//currently no db connection so verification isnot enforced
	        		variables.step_LOG_IN_SCREEN = 0;
	        		
	        		//after a successful login user is directed to main posting board
	        		variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
	        		variables.nextScreen = variables.MAIN_POSTING_BOARD_SCREEN;
	        		
	        		output = this.screenOutput(variables.nextScreen);
	        		
	        	}
	    	}//steps for registered user
	    	  
	    	//added -- jma 342 - February 24th 2012
	    	else if(variables.chosen_On_Screen_Action.equals("3"))//terminate connection
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
    			this.variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			this.variables.nextScreen = this.variables.PERSONAL_INFORMATION_SCREEN;
        		
        		output = this.screenOutput(variables.nextScreen);
        	//	step_MAIN_POSTING_BOARD++;
    		}
    		//announcements screen
    		if(input.equals("2"))
    		{
    			this.variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			this.variables.nextScreen = this.variables.ANNOUNCEMENTS_SCREEN;
        		
        		output = this.screenOutput(variables.nextScreen);
        		//step_MAIN_POSTING_BOARD++;
    		}
    		//research publications screen
    		else if(input.equals("3"))
    		{
    			this.variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			this.variables.nextScreen = this.variables.RESEARCH_PUBLICATIONS_SCREEN;
        		
        		output = this.screenOutput(variables.nextScreen);
        		//step_MAIN_POSTING_BOARD++;
    		}
    		//public discussions screen
    		if(input.equals("4"))
    		{
    			this.variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			this.variables.nextScreen = this.variables.PUBLIC_DISCUSSIONS_SCREEN;
        		
        		output = this.screenOutput(variables.nextScreen);
        		//step_MAIN_POSTING_BOARD++;
    		}
    		//research group screen
    		else if(input.equals("5"))
    		{
    			this.variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			this.variables.nextScreen = this.variables.RESEARCH_GROUPS_SCREEN;
        		
        		output = this.screenOutput(variables.nextScreen);
        		//step_MAIN_POSTING_BOARD++;
    		}
    		//added -- jma 342 - February 24th 2012
    		
    		//privileges screen
    		else if(input.equals("6"))
    		{
    			this.variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			this.variables.nextScreen = this.variables.PRIVILEGES_SCREEN;
        		
        		output = this.screenOutput(variables.nextScreen);
    		}
    		
    		//return to log in screen
    		else if(input.equals("7"))
    		{
    			this.variables.currentScreen = this.variables.LOG_IN_SCREEN;
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
    	
    	if(this.variables.step_PERSONAL_INFORMATION == 0)
    	{
			//Edit First Name
			if(input.equals("1"))
			{
				variables.editField_On_Screen = "firstName";
	    		output = "Edit First Name: ";
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit Last Name
			else if(input.equals("2"))
			{
				variables.editField_On_Screen = "lastName";
	    		output = "Edit Last Name: ";
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit Last Age
			else if(input.equals("3"))
			{
				variables.editField_On_Screen = "Age";
	    		output = "Edit Age: ";
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit Gender
			else if(input.equals("4"))
			{
				variables.editField_On_Screen = "Gender";
	    		
	    		variables.nextScreen = this.variables.GENDER_SCREEN;
	    		variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
	    		
	    		output = this.screenOutput(variables.nextScreen);
	    		variables.step_PERSONAL_INFORMATION = 0;

			}
			
			//Edit Marital Status
			else if(input.equals("5"))
			{
				variables.editField_On_Screen = "maritalStatus";
	    		
	    		variables.nextScreen = this.variables.MARITAL_STATUS_SCREEN;
	    		variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
	    		
	    		output = this.screenOutput(variables.nextScreen);
	    		variables.step_PERSONAL_INFORMATION = 0;
			}
			
			//Edit Home Address
			else if(input.equals("6"))
			{
				variables.editField_On_Screen = "homeAddress";
	    		output = "Edit Home Address: ";
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit Home Number
			else if(input.equals("7"))
			{
				variables.editField_On_Screen = "homeNumber";
	    		output = "Edit Home Number: ";
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit Cell Number
			else if(input.equals("8"))
			{
				variables.editField_On_Screen = "cellNumber";
	    		output = "Edit Cell Number: ";
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit Hospital
			else if(input.equals("9"))
			{
				variables.editField_On_Screen = "hospital";
	    		output = "Edit Hospital: ";
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit Hospital Address
			else if(input.equals("10"))
			{
				variables.editField_On_Screen = "hospitalAddress";
	    		output = "Edit Hospital Address: ";
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit work Number
			else if(input.equals("11"))
			{
				variables.editField_On_Screen = "workNumber";
	    		output = "Edit Work Number: ";
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit specialty
			else if(input.equals("12"))
			{
				variables.editField_On_Screen = "specialty";
	    		output = "Edit Specialty: ";
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
						
			//Edit Friends list
			else if(input.equals("13"))
			{
				variables.editField_On_Screen = "Friends List";
				
				variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
				this.variables.nextScreen = this.variables.user_FRIENDS_LIST_SCREEN;
				
				//retrieve recordset of friends list from database
	    		output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/); 
	    		
			}
			
			//Return to Main Posting Board
			else if(input.equals("14"))
			{
        		
				variables.step_PERSONAL_INFORMATION = 0;
				
				variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
				this.variables.nextScreen = this.variables.MAIN_POSTING_BOARD_SCREEN;
				
	    		output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/); 
	    		
			}
			
    	}
    	 //changes made - jma 342 - feb 18th
    	
    	//changes made jma342 - Feb 18
    	else if(this.variables.step_PERSONAL_INFORMATION == 1)
    	{
    		output = "userInput";
    		variables.step_PERSONAL_INFORMATION ++;
    	}
    	
    	else if(this.variables.step_PERSONAL_INFORMATION == 2)
    	{
    		//update the database with edited field
    		//requery database for updated record
    		
    		//set the screen to have its information displayed in the
    		//output screen function
    		this.variables.nextScreen = variables.currentScreen;
    		
    		//set current screen to output screen
    		this.variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    		
    		output = this.screenOutput(variables.nextScreen/*,updated recordset*/);	
    		variables.step_PERSONAL_INFORMATION = 0;
    	}
    	//changes made jma342 - Feb 18
    	
    	
    	return output;
    }
    
    // for research publication screen--rw446 Feb 18th
    public String researchPublicationsScreen(String input){
    	String output = "";
    	
    	if(this.variables.step_RESEARCH_PUBLICATIONS==0)
    	{
    		//add a new publications
    		if (input.equals("1"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			
    			output = "Please enter the author and name of the " +
    					"publications(eg. John Smith 'Cancer Research'):";
    			
    			this.variables.step_RESEARCH_PUBLICATIONS++;
    			
    		}
    		
    		//delete a publications
    		else if (input.equals("2"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			
    			output = "Please select the publications that you want to delete";
    			
    			this.variables.step_RESEARCH_PUBLICATIONS++;
    			
    		}
    	}
    	
    	else if(this.variables.step_RESEARCH_PUBLICATIONS == 1)
    	{
    		output = "userInput";
    		this.variables.step_RESEARCH_PUBLICATIONS++;
    		
    	}
    	else if (this.variables.step_RESEARCH_PUBLICATIONS == 2)
    	{
    		
    		if(this.variables.chosen_On_Screen_Action.equals("1")) 
    		{
    			/*add the publicaitons to db*/
    			
    			output = "Research Publications has been added...Press anykey to continue";
    			
    			this.variables.step_RESEARCH_PUBLICATIONS++;
    			
    		}
    		//delete publications
    		else if (this.variables.chosen_On_Screen_Action.equals("2"))
    		{
    		   /*verify with the db to delete the publications*/
    			
    			output = "Research Publication has been deleted...press any key to continue";
    			
    			this.variables.step_RESEARCH_PUBLICATIONS++;
    		}
    	}
    	
    	else if (this.variables.step_RESEARCH_PUBLICATIONS ==3)
    	{
    		output = "userInput";
    		this.variables.step_RESEARCH_PUBLICATIONS++;
    	}
    	
    	else if (this.variables.step_RESEARCH_PUBLICATIONS == 4)
    	{
    		variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    		variables.nextScreen = this.variables.RESEARCH_PUBLICATIONS_SCREEN;
    		
    		output = this.screenOutput(variables.currentScreen);//update screen
    		variables.step_RESEARCH_PUBLICATIONS=0;
    	}
    	return output;
    }
    
    //for announcement screen --rw446 Feb 18th
    public String announcementScreen(String input)
    {
    	String output = "";
    	
    	if(this.variables.step_ANNOUNCEMENTS==0)
    	{
    		
    		//add a new announcement
    		if (input.equals("1"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			
    			output = "Please enter announcement:";
    			
    			this.variables.step_ANNOUNCEMENTS++;
    			
    		}
    		
    		//delete an announcement
    		else if (input.equals("2"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			
    			output = "Please select the announcement that you want to delete";
    			
    			this.variables.step_ANNOUNCEMENTS++;
    			
    		}
    	
    	}
    	
    	else if(this.variables.step_ANNOUNCEMENTS == 1)
    	{
    		output = "userInput";
    		this.variables.step_ANNOUNCEMENTS++;
    		
    	}
    	else if (this.variables.step_ANNOUNCEMENTS == 2)
    	{
    		
    		if(this.variables.chosen_On_Screen_Action.equals("1")) 
    		{
    			/*add the announcement to db + Along with the date and time appeneded to the front of the message*/
    			
    			output = "Annoucement has been added...Press anykey to continue";
    			
    			this.variables.step_ANNOUNCEMENTS++;
    			
    		}
    		//delete publications
    		else if (this.variables.chosen_On_Screen_Action.equals("2"))
    		{
    		   /*delete selected announcement*/
    			
    			output = "Announcement has been deleted...press any key to continue";
    			
    			this.variables.step_ANNOUNCEMENTS++;
    		}
    	}
    	
    	else if (this.variables.step_ANNOUNCEMENTS ==3)
    	{
    		output = "userInput";
    		this.variables.step_ANNOUNCEMENTS++;
    	}
    	
    	else if (this.variables.step_ANNOUNCEMENTS == 4)
    	{
    		variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    		variables.nextScreen = this.variables.ANNOUNCEMENTS_SCREEN;
    		
    		output = this.screenOutput(variables.currentScreen);//update screen
    		variables.step_ANNOUNCEMENTS=0;
    	}
    	return output;
    }
    
    // for public discussion screen--rw446 Feb 18th
    //remember to segment the listing of discussions in this screen
    //by those owned by the user and those that are not owned by the user
    public String discussionScreen(String input)
    {
    	String output = "";
    	
    	if(this.variables.step_PUBLIC_DISCUSSIONS==0)
    	{   
    		//create new discussion
    		if(input.equals("1"))
    		{
                this.variables.chosen_On_Screen_Action = input;
    			
    			output = "Please enter the title of the new discussion";
    			
    			this.variables.step_PUBLIC_DISCUSSIONS++;
    		}
    		
    		//delete a discussion
    		else if(input.equals("2"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			output = "Please enter the number of the discussion you want to delete";
    			this.variables.step_PUBLIC_DISCUSSIONS++;
    		}
    	}
    	
    	else if (this.variables.step_PUBLIC_DISCUSSIONS ==1)
    	{
    		output = "userInput";
    		this.variables.step_PUBLIC_DISCUSSIONS++;
    	}
    	
    	else if(this.variables.step_PUBLIC_DISCUSSIONS==2)
    	{
    		
    		if(this.variables.chosen_On_Screen_Action.equals("1"))
    		{
    			//insert discussion into discussions table in db
    			//retrieve id of discussion last inserted and store in variables.publicDiscussionID;
    			output = "Discussion has been created...press any key to continue";
    			variables.createdPublicDiscussion = true;
    			this.variables.step_PUBLIC_DISCUSSIONS++;
    		}
    		
    		//confirm the desire to delete selected chosen discussion.
    		else if(this.variables.chosen_On_Screen_Action.equals("2"))
    		{
    			output = "Are you sure(Yes,No): ";
    			
    			this.variables.step_PUBLIC_DISCUSSIONS++;
    		}
    	}
    	
    	else if (this.variables.step_PUBLIC_DISCUSSIONS ==3)
    	{
    		
			output = "userInput";
    		this.variables.step_PUBLIC_DISCUSSIONS++;	
    		
    	}
    	
    	else if (this.variables.step_PUBLIC_DISCUSSIONS ==4)
    	{
    		if(this.variables.chosen_On_Screen_Action.equals("1"))
    		{
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
	    		variables.nextScreen = this.variables.CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN;
	    		
	    		output = this.screenOutput(variables.currentScreen);
	    		variables.step_PUBLIC_DISCUSSIONS=0;
    		}
    		
    		//after the user confirms the choice either carry out delete operation or cancel the delete operation
    		else if(this.variables.chosen_On_Screen_Action.equals("2"))
    		{
    			if(input.toLowerCase().equals("yes"))
    			{
    				/*delete the topic from db*/
    				output = "Discussion has been deleted...press any key to continue";
    				
    				this.variables.step_PUBLIC_DISCUSSIONS++;
    			}
    			else if(input.toLowerCase().equals("no"))
    			{
    				output = "Deletion operation cancelled...press any key to continue";
    				
    				this.variables.step_PUBLIC_DISCUSSIONS++;
    			}
    		}
    		
    	}
    	else if (this.variables.step_PUBLIC_DISCUSSIONS == 5)
    	{
    		
    		if(this.variables.chosen_On_Screen_Action.equals("2"))
    		{
    			output = "userInput";
        		this.variables.step_PUBLIC_DISCUSSIONS++;
    		}
    	}
    	
    	else if (this.variables.step_PUBLIC_DISCUSSIONS == 6)
    	{
    		//delete operation protocol is complete...so refresh disucssions screen
    		if(this.variables.chosen_On_Screen_Action.equals("2"))
    		{    			
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
	    		variables.nextScreen = this.variables.PUBLIC_DISCUSSIONS_SCREEN;
	    		
	    		output = this.screenOutput(variables.currentScreen);//update screen
	    		variables.step_PUBLIC_DISCUSSIONS=0;
    		}
    	}
    	
    	return output;
    }
    
    //new screen for display all comments for one specific topic--rw446 Feb 18th
    public String discussionTopicScreen(String input)
    {
    	String output = "";
    	if(this.variables.step_PUBLIC_DISCUSSION_TOPIC==0)
    	{
    		if(input.equals("a"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			output = "Please enter the comments\n";
    			this.variables.step_PUBLIC_DISCUSSION_TOPIC++;
    		}
    		else if(input.equals("b"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			output = "Please seletc the comment you want to delete\n";
    			this.variables.step_PUBLIC_DISCUSSION_TOPIC++;
    		}
    	}
    	else if(this.variables.step_PUBLIC_DISCUSSION_TOPIC==1)
    	{
    		output="userInput";
    		this.variables.step_PUBLIC_DISCUSSION_TOPIC++;
    	}
    	else if(this.variables.step_PUBLIC_DISCUSSION_TOPIC==2)
    	{
    		if(this.variables.chosen_On_Screen_Action.equals("a"))
    		{
    			/*add comments to database*/
    			output = "Comments has been added to db...press anykey to continue\n";
    			this.variables.step_PUBLIC_DISCUSSION_TOPIC++;
    			
    		}
    		else if(this.variables.chosen_On_Screen_Action.equals("b"))
    		{
    			/*delete comments from database*/
    			output = "Comments has been deleted successfully..press anykey to continue\n";
    			this.variables.step_PUBLIC_DISCUSSION_TOPIC++;
    		}
    	}
    	else if(this.variables.step_PUBLIC_DISCUSSION_TOPIC==3)
    	{
    		output = "userInput";
    		this.variables.step_PUBLIC_DISCUSSION_TOPIC++;
    	}
    	else if(this.variables.step_PUBLIC_DISCUSSION_TOPIC==4)
    	{
    		variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    		variables.nextScreen = this.variables.PUBLIC_DISCUSSION_TOPIC_SCREEN;
    		
    		output = this.screenOutput(variables.currentScreen);//update screen
    		variables.step_PUBLIC_DISCUSSION_TOPIC=0;
    	}
    	return output;
    }

   public String created_PubDisc_ResGrp_Priviliges_Screen(String input)
   {
	   String output = "";
	   
		if(this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN == 0)
	   	{
	   		//set view privileges
	   		if(input.equals("1") || input.equals("2"))
	   		{
	   			output = "display the group options from db";
	   			variables.chosen_On_Screen_Action = input;
	   			this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN++;
	   			
	   		}
	   		
	   	}
		
		//set post privileges
		else if(this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN == 1)
		{
			output = "Please select the number for desired group";
   			
   			this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN++;
   			
		}
		
		else if(this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN == 2)
		{
			output = "userInput";
   			
   			this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN++;
		}
		
		else if(this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN == 3)
		{
			//view privileges
			if(variables.chosen_On_Screen_Action.equals("1"))
			{
				if(variables.createdPublicDiscussion)
				{
					/*added choosen group number along with discussion id and user id to the view privileges table
					 * for discussions
					 */
					
					if(!input.equals("4"))
					{
						output = "View Privileges have been set for discussion..press any key to continue";
						this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN++;
					}
					
					//add else which would take you to a screen to select specific friends
				}
				
				else if(variables.createdResearchGroup)
				{
					/*added choosen group number along with res grp id and user id to the view privileges table
					 * for res grps
					 */
					
					if(!input.equals("4"))
					{
						output = "View Privileges have been set for discussion..press any key to continue";
						this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN++;
					}
					
					//add else which would take you to a screen to select specific friends
				}
			}
			//post privileges
			else if(variables.chosen_On_Screen_Action.equals("2"))
			{
				if(variables.createdPublicDiscussion)
				{
					/*added choosen group number along with discussion id and user id to the post privileges table
					 * for discussions
					 */
					
					if(!input.equals("4"))
					{
						output = "Post Privileges have been set for discussion..press any key to continue";
						this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN++;
					}
					
					//add else which would take you to a screen to select specific friends
				}
				
				else if(variables.createdResearchGroup)
				{
					/*added choosen group number along with res grp id and user id to the post privileges table
					 * for res grps
					 */
					
					if(!input.equals("4"))
					{
						output = "View Privileges have been set for discussion..press any key to continue";
						this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN++;
					}
					
					//add else which would take you to a screen to select specific friends
				}
			}
			
			else if(this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN == 4)
			{
				output = "userInput";
	   			
	   			this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN++;
	   			
			}
			
			else if(this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN == 5)
			{
				//at this stage..check to see if both sets of privileges have been set...this would indicate
				//if to refresh the current screen or return to the parent screen...use boolean variables
				//do indicate when both privileges have been set.
				
				//requisite created discussion/res grp bools back to false on completion
	   			
			}
				
		}

		
	   return output;
   }
    //changes made - jma 342 - Feb 25th - function that allows a user to access various functions of
    //a friends posting board
    public String friendMainPostingBoardSreen(String input)
    {
    	String output = "";
    	
    	if(this.variables.step_FRIEND_MAIN_POSTING_BOARD_SCREEN == 0)
    	{
    		//friend personal information screen
    		if(input.equals("1"))
    		{
    			//check privileges to determine if user is allowed to view this friends personal info
    			if(true)//allowed then do following
    			{
	    			this.variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
	    			this.variables.nextScreen = this.variables.FRIEND_PERSONAL_INFORMATION_SCREEN;
	    			this.variables.step_FRIEND_MAIN_POSTING_BOARD_SCREEN = 0;
	        		output = this.screenOutput(variables.nextScreen);
    			}
	        	//end if
	        	else
	        	{
	        		output = "Sorry. Your are not allowed to view this region...press any key to continue";
	        		
	        		this.variables.step_FRIEND_MAIN_POSTING_BOARD_SCREEN++;
	        	}
	        	//end else
        	//	step_MAIN_POSTING_BOARD++;
    		}
    		//friend announcements screen
    		if(input.equals("2"))
    		{
    			//check privileges to determine if user is allowed to view this friends personal info
    			//if allowed then do following
    			if(true)
    			{
    				
    			
	    			this.variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
	    			this.variables.nextScreen = this.variables.FRIEND_ANNOUNCEMENTS_SCREEN;
	        		
	    			this.variables.step_FRIEND_MAIN_POSTING_BOARD_SCREEN = 0;
	        		output = this.screenOutput(variables.nextScreen);
    			}
        		
        		//end if
	        	else
	        	{
	        		output = "Sorry. Your are not allowed to view this region...press any key to continue";
	        		
	        		this.variables.step_FRIEND_MAIN_POSTING_BOARD_SCREEN++;
	        	}
	        	//end else
        		
    		}
    		//friend research publications screen
    		else if(input.equals("3"))
    		{
    			//check privileges to determine if user is allowed to view this friends personal info
    			//if allowed then do following
    			if(true)
    			{
	    			this.variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
	    			this.variables.nextScreen = this.variables.FRIEND_RESEARCH_PUBLICATIONS_SCREEN;
	        		
	        		output = this.screenOutput(variables.nextScreen);
	        		this.variables.step_FRIEND_MAIN_POSTING_BOARD_SCREEN = 0;
    			}
        		
        		//end if
	        	else
	        	{
	        		output = "Sorry. Your are not allowed to view this region...press any key to continue";
	        		
	        		this.variables.step_FRIEND_MAIN_POSTING_BOARD_SCREEN++;
	        	}
	        	//end else
    		}
    		//friend public discussions screen
    		if(input.equals("4"))
    		{
    			//check privileges to determine if user is allowed to view this friends personal info
    			//if allowed then do following
    			if(true)
    			{
	    			this.variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
	    			this.variables.nextScreen = this.variables.FRIEND_PUBLIC_DISCUSSIONS_SCREEN;
	    			this.variables.step_FRIEND_MAIN_POSTING_BOARD_SCREEN = 0;
	        		output = this.screenOutput(variables.nextScreen);
    			}
        		
        		//end if
	        	else
	        	{
	        		output = "Sorry. Your are not allowed to view this region...press any key to continue";
	        		
	        		this.variables.step_FRIEND_MAIN_POSTING_BOARD_SCREEN++;
	        	}
    		}
    		//research group screen
    		else if(input.equals("5"))
    		{
    			//check privileges to determine if user is allowed to view this friends personal info
    			//if allowed then do following
    			if(true)
    			{
    				this.variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    				this.variables.nextScreen = this.variables.FRIEND_RESEARCH_GROUPS_SCREEN;
    				this.variables.step_FRIEND_MAIN_POSTING_BOARD_SCREEN = 0;
    				output = this.screenOutput(variables.nextScreen);
    			}
        		
        		//end if
	        	else
	        	{
	        		output = "Sorry. Your are not allowed to view this region...press any key to continue";
	        		
	        		this.variables.step_FRIEND_MAIN_POSTING_BOARD_SCREEN++;
	        	}
	        	//end else
    		}
    		//return to friends list screen
    		else if(input.equals("6"))
    		{
    			this.variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			this.variables.nextScreen = this.variables.user_FRIENDS_LIST_SCREEN;
        		
        		output = this.screenOutput(variables.nextScreen);
        		this.variables.step_FRIEND_MAIN_POSTING_BOARD_SCREEN = 0;
    		}
    		//added -- jma 342 - February 24th 2012
    		
    	}
    	else if(this.variables.step_FRIEND_MAIN_POSTING_BOARD_SCREEN == 1)
    	{
    		output = "userInput";
    		this.variables.step_FRIEND_MAIN_POSTING_BOARD_SCREEN = 0;
    	}
    	return output;
    }
  //changes made - jma 342 - Feb 25th - 4:43pm

     //changes made - jma 342 - feb 25th - allows user to view a friend's personal information
    public String friendPersonalInformationScreen(String input)
    {
    	String output = "";
    	
    	if(this.variables.step_PERSONAL_INFORMATION == 0)
    	{
			//View Friend's Friends list
			if(input.equals("13"))
			{
				variables.editField_On_Screen = "Friends List";
				
				variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
				this.variables.nextScreen = this.variables.FRIEND_FRIENDS_LIST_SCREEN;
				
				//retrieve recordset of friends list from database
	    		output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/); 
	    		
			}
			
			//Return to Friend Main Posting Board
			else if(input.equals("14"))
			{
        		
				variables.step_PERSONAL_INFORMATION = 0;
				
				variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
				this.variables.nextScreen = this.variables.FRIEND_MAIN_POSTING_BOARD_SCREEN;
				
	    		output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/); 
	    		
			}
			
    	}
    	
    	return output;
    }

    
    //change made - jma 342 - Feb 18th
    public String maritalStatusScreen(String input)
    {
    	String output = "";
    	
    	//single
    	if(this.variables.step_MARITAL_STATUS == 0)
    	{
	    	if(input.equals("1") || input.equals("2") || input.equals("3") ||
	    			input.equals("4") || input.equals("5"))
	    	{
	    		//TO DO: update the user's record with the chosen marital status
	    		
	    		variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
	    		variables.nextScreen = this.variables.PERSONAL_INFORMATION_SCREEN;
	    		
	    		output = this.screenOutput(variables.nextScreen);
	    	}
	    	else
	    	{
	    		output = "Please select a number from 1- 5";
	    		variables.step_MARITAL_STATUS++;
	    		
	    	}
    	}
    	
    	else if(variables.step_MARITAL_STATUS == 1)
    	{
    		output = "userInput";
    		variables.step_MARITAL_STATUS = 0;
    	}
    	
    	return output;
	    	
    }
  //change made - jma 342 - Feb 18th
    public String genderScreen(String input)
    {
    	String output = "";
    	
    	//single
    	if(this.variables.step_GENDER == 0)
    	{
	    	if(input.equals("1") || input.equals("2"))
	    	{
	    		//TO DO: update the user's record with the chosen gender
	    		
	    		variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
	    		variables.nextScreen = this.variables.PERSONAL_INFORMATION_SCREEN;
	    		
	    		output = this.screenOutput(variables.nextScreen);
	    	}
	    	else
	    	{
	    		output = "Please select numbers 1 - 2";
	    		this.variables.step_GENDER++;
	    		
	    	}
    	}
    	
    	else if(this.variables.step_GENDER == 1)
    	{
    		output = "userInput";
    		this.variables.step_GENDER = 0;
    	}
    	
    	return output;
	    	
    }

  //change made - jma 342 - Feb 18th	


  //change made - jma 342 - Feb 19th	
    public String user_friendsListSreen(String input)
    {
    	String output = " ";
    	
    	if(this.variables.step_FRIENDS_LIST == 0)
    	{
    		//add a friend
    		if(input.equals("1"))
    		{
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			variables.nextScreen = this.variables.ADD_FRIEND_SCREEN;
    			
    			output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/);
    			
    			this.variables.step_FRIENDS_LIST=0;
    		}
    		
    		//delete a friend
    		else if(input.equals("2"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			
        		output = "Please enter the number of the desired friend from the list(enter -1 to cancel):";
        	
        		this.variables.step_FRIENDS_LIST++;
    		}
    		
    		//View Friends profile screen
    		else if(input.equals("3"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			
        		output = "Please enter the number of the desired friend from the list(enter -1 to cancel):";
        	
        		this.variables.step_FRIENDS_LIST++;
    		}
    		
    		//Friend requests screen
    		else if(input.equals("4"))
    		{
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			variables.nextScreen = this.variables.user_FRIEND_REQUESTS_SCREEN;
    			
    			output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/);
    			this.variables.step_FRIENDS_LIST=0;
    			
    		}
    		
    		else if(input.equals("5"))
    		{
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			variables.nextScreen = this.variables.PERSONAL_INFORMATION_SCREEN;
    			
    			output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/);
    			this.variables.step_FRIENDS_LIST=0;
    			
    		}

    		
    	}
    	
    	else if(this.variables.step_FRIENDS_LIST == 1)
    	{
    		output = "userInput";
    		this.variables.step_FRIENDS_LIST++;
    	}
    	
    	else if(this.variables.step_FRIENDS_LIST == 2)
    	{
    		if(this.variables.chosen_On_Screen_Action.equals("2"))
    		{
    			//jma342 - Feb 25th - 3:54pm allows user to cancel deletion operation
    			if(input.equals("-1"))
    			{
    				output = "Friend deletion has been cancelled...press any key to continue:";
    				this.variables.step_FRIENDS_LIST++;
    			}
    			else
    			{
	    			/*rs.recordset("delete from friends where
	    			userid = userid and friend id = 'id of the friend chosen' or 
	    			userid = 'id of the friend chosen' and friend id = 'user id')*/
	    			
	    			//output = build a string with records from above recordset
	    			
	    			output = "Friend has been deleted...press any key to continue:";
	  
	    			this.variables.step_FRIENDS_LIST++;
    			}
    			//jma342 - Feb 25th - 3:54pm allows user to cancel deletion operation
    		}
    		
    		else if(this.variables.chosen_On_Screen_Action.equals("3"))
    		{
    			//jma342 - Feb 25th - 3:54pm allows user to cancel viewing friend's profile operation
    			if(input.equals("-1"))
    			{
    				output = "Viewing a Friend's profile has been cancelled...press any key to continue:";
    				this.variables.step_FRIENDS_LIST++;
    			}
    			else
    			{
	    			/*retrieve recordset with selected friend's necessary information*/
	    			
    				variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
        			variables.nextScreen = this.variables.FRIEND_MAIN_POSTING_BOARD_SCREEN;
        			
        			output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/);
        			this.variables.step_FRIENDS_LIST=0;
        			
    			}
    			//jma342 - Feb 25th - 3:54pm allows user to cancel deletion operation
    		}
    	}
    	
    	else if(this.variables.step_FRIENDS_LIST == 3)
    	{
    		output = "userInput";
    		this.variables.step_FRIENDS_LIST++;
    	}
    	
    	else if(this.variables.step_FRIENDS_LIST == 4)
    	{
    		if(this.variables.chosen_On_Screen_Action.equals("2"))
    		{
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			variables.nextScreen = this.variables.user_FRIENDS_LIST_SCREEN;
    			
    			output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/);
    			this.variables.step_FRIENDS_LIST = 0;
    		}
    	}
    	
    	return output;
    }
    //change made - jma 342 - Feb 19th

    //change made - jma 342 - Feb 25th 7:10pm -- allows a user to view a friend's
    //friend list with the only possibl action of returning to the
    //previous screen
    public String friend_friendsListSreen(String input)
    {
    	String output = " ";
    	
    /*	if(this.step_FRIENDS_LIST == 0)
    	{
   */ 		//return to friend's personal information
    		if(input.equals("1"))
    		{
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			variables.nextScreen = this.variables.FRIEND_PERSONAL_INFORMATION_SCREEN;
    			
    			output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/);
    			
    //			this.step_FRIENDS_LIST=0;
    		}
    	//}
    		
    		return output;
    }
  //change made - jma 342 - Feb 25th 7:10pm -- allows a user to view a friend's
    //friend list with the only possibl action of returning to the
    //previous screen

  //change made - jma 342 - Feb 19th
    public String addFriendSreen(String input)
    {
    	String output = "";
    	
    	if(this.variables.step_ADD_FRIEND == 0)
    	{
    		if(input.toLowerCase().equals("exit"))
    		{
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			variables.nextScreen = this.variables.user_FRIENDS_LIST_SCREEN;
    			
    			output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/);
    			this.variables.step_ADD_FRIEND = 0;
    		}
    		
    		//jma342 - Feb 25 - 3:28PM 
    		else
    		{
	    		/*rs.recordset("Select First Name, Last Name, Home Address where
				firstname = 'firstname' and lastname = 'lastname'")*/
			
	    		//allow recordset to move to next record on
				//reentry in to this function
				//once the last record is added to the output variable
    		
	    		//jma342 - Feb 25 - 3:28PM - added the ability for the user to cancel operation by entering -1
	    		output = "Please select the desired friend by entering a number(enter -1 to cancel)";
	    		variables.step_ADD_FRIEND++;
				//increment step_AddFriend
    		}
    		//jma342 - Feb 25 - 3:28PM 
    	}
    	
    	else if(this.variables.step_ADD_FRIEND == 1)
    	{
    		output = "userInput";
    		variables.step_ADD_FRIEND++;
    	}
    	
    	else if(this.variables.step_ADD_FRIEND == 2)
    	{
    		//jma342 - Feb 25 - 3:30PM - allows user to return to parent screen after cancelling current operation 
    		if(input.equals("-1"))
    		{
    			
    			this.variables.step_ADD_FRIEND++;
    			
    			output = "Friend request has been cancelled...press any key to continue";
    			
    		}
    		//jma342 - Feb 25 - 3:30PM - returns to parent screen after cancelling current operation 
    		
    		else
    		{
    			/*rs.recordset("Insert userID, input into
    			 * friendRequest table)
    			 */
    			output = "Friend request has been sent...press any key to continue";
    			
    			variables.step_ADD_FRIEND++;
    		}
    		
    	}
    	
    	else if(this.variables.step_ADD_FRIEND == 3)
    	{
    		output = "userInput";
    		variables.step_ADD_FRIEND++;
    	}
    	
    	else if(this.variables.step_ADD_FRIEND == 4)
    	{
    		variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
			variables.nextScreen = this.variables.user_FRIENDS_LIST_SCREEN;
			
			output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/);
			this.variables.step_ADD_FRIEND = 0;
    	}
	
    	return output;
    }
    
    public String user_friendRequestScreen(String input)
    {
    	String output = "";
    	
    	if(this.variables.step_user_FRIEND_REQUEST == 0)
    	{
    		//jma342 - Feb 25th - 3:49pm - allows user to cancel the operation of accepting a friend request
    		if(input.equals("-1"))
    		{
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			variables.nextScreen = this.variables.user_FRIENDS_LIST_SCREEN;
    			
    			output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/);
    			this.variables.step_user_FRIEND_REQUEST = 0;
    		}
    		else
    		{
    			//display the friend request chosen
        		//output =
    			variables.step_user_FRIEND_REQUEST++;
    		}
    		
    		//jma342 - Feb 25th - 3:49pm - allows user to cancel the operation of accepting a friend request
    	}
    	else if(this.variables.step_user_FRIEND_REQUEST == 1)
    	{
    		output = "Please enter A - accept or D - Deny:";
    		variables.step_user_FRIEND_REQUEST++;
    	}
    	else if(this.variables.step_user_FRIEND_REQUEST == 2)
    	{
    		output = "userInput";
    		variables.step_user_FRIEND_REQUEST++;
    	}
    	else if(this.variables.step_user_FRIEND_REQUEST == 3)
    	{
    	
    		if(input.equals("A") || input.equals("D"))
    		{
	    		//if user chooses A then update the friends listing for both
	    		//requester and requestee and remove the request from the friends request table
	    		
	    		//else just remove the request from the friends list table
    		}
    		
    		output = "Request accepted/denied...press any key to continue";
    		variables.step_user_FRIEND_REQUEST++;
    	}
    	else if(variables.step_user_FRIEND_REQUEST == 4)
    	{
    		output = "userInput";
    		variables.step_user_FRIEND_REQUEST++;
    	}
    	
    	else if(variables.step_user_FRIEND_REQUEST == 5)
    	{
    		variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
			variables.nextScreen = this.variables.user_FRIEND_REQUESTS_SCREEN;
			
			output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/);
			this.variables.step_user_FRIEND_REQUEST = 0;
    	}
    	
    	return output;
    }
  //change made - jma 342 - Feb 19th
   
  //change made - rw  446 - Feb 26th
    public String PrivilegeScreen(String input)
    {
    	String output = "";
    	if (this.variables.step_PRIVILEGES_SCREEN == 0)
    	{
    		if (input.equals("1")||input.equals("2")||input.equals("3")||input.equals("4")||input.equals("5"))
    		{
    			//variables.chosen_On_Screen_Action = input; 
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
			    variables.nextScreen = this.variables.RESET_PRIVILEGE_SCRREN;
			     this.variables.step_PRIVILEGES_SCREEN = 0;
			     output = this.screenOutput(variables.nextScreen /*,jump to reset privilege screen*/);
    		}
    		else if (input.equals("6"))
    		{
    			output = "the Announcement from database";
    			//this.variables.chosen_On_Screen_Action = input;
    			this.variables.step_PRIVILEGES_SCREEN++;
    		}
    		else if(input.equals("7"))
    		{
    			output = "the Public discussions topics from database";
    			//this.variables.chosen_On_Screen_Action = input;
    			this.variables.step_PRIVILEGES_SCREEN++;
    		}    		
    	}
    	else if (this.variables.step_PRIVILEGES_SCREEN == 1)
    	{
    		output = "Please select privilege of which topics you want to reset by entering the number";
    		this.variables.step_PRIVILEGES_SCREEN++;
    	}
    	else if (this.variables.step_PRIVILEGES_SCREEN == 2)
    	{
    		output = "userInput";
    		this.variables.step_PRIVILEGES_SCREEN++;
    	}
    	else if (this.variables.step_PRIVILEGES_SCREEN == 3)
    	{
    		variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
		    variables.nextScreen = this.variables.RESET_PRIVILEGE_SCRREN;
		    this.variables.step_PRIVILEGES_SCREEN = 0;
		    output = this.screenOutput(variables.nextScreen /*,jump to reset privilege screen*/);
    	}
    	return output;
    }
    
    public String ResetPrivilegeScreen(String input)
    {
    	String output = "";
    	if (this.variables.step_RESET_PRIVILEGE == 0)
    	{     
    		 if (input.equals("1"))
    		 {
    			 this.variables.chosen_On_Screen_Action = input; 
    			 output = "Please Choose Groups to Reset View Privilege";
    		 }
    		 else if (input.equals("2"))
    		 {
    			 this.variables.chosen_On_Screen_Action = input;
    			 output = "Please Choose Groups to Reset Post Privilege";
    		 }
    		 
    		 this.variables.step_RESET_PRIVILEGE++;  		
    	}
    	
    	else if (this.variables.step_RESET_PRIVILEGE == 1)
    	{
    		output = "1. Communittee members + Visitors";
    		this.variables.step_RESET_PRIVILEGE++;
    	}
    	else if(this.variables.step_RESET_PRIVILEGE == 2)
    	{
    		output = "2. Communittee members";
    		this.variables.step_RESET_PRIVILEGE++;
    	}
    	else if(this.variables.RESET_PRIVILEGE_SCRREN == 3)
    	{
    		output = "3. All Friends";
    		this.variables.step_RESET_PRIVILEGE++;
    	}
    	else if (this.variables.RESET_PRIVILEGE_SCRREN == 4)
    	{
    		output = "4. Specific Friends";
    		this.variables.step_RESET_PRIVILEGE++;
    	}
    	else if (this.variables.step_RESET_PRIVILEGE == 5)
    	{
    		output = "userInput";
    		this.variables.step_RESET_PRIVILEGE++;
    	}
    	else if (this.variables.step_RESET_PRIVILEGE == 6)
    	{
    		if (this.variables.chosen_On_Screen_Action.equals("1")){
    			if (input.equals("1"))
    			{
    				//update database with view privilege to communittee member + visitors
    				output = "Reset view privilege to Communittee members +visitors";
    			}
    			else if (input.equals("2"))
    			{
    				//update database with view privilege to communittee members
    				output = "Reset view privilege to Communittee members";
    			}
    			else if (input.equals("3"))
    			{
    				//update database with view privilege to All friends
    				output = "Reset view privilege to All friends";
    			}
    			else if (input.equals("4"))
    			{
    				//update database with view privilege to specific friends
    				output = "Reset view privilege to Specific friends";
    			}
    		}
    		else if (this.variables.chosen_On_Screen_Action.equals("2"))
    		{
    			if (input.equals("1"))
    			{
    				//update database with view privilege to communittee member + visitors
    				output = "Reset post privilege to Communittee members +visitors";
    			}
    			else if (input.equals("2"))
    			{
    				//update database with view privilege to communittee members
    				output = "Reset post privilege to Communittee members";
    			}
    			else if (input.equals("3"))
    			{
    				//update database with view privilege to All friends
    				output = "Reset post privilege to All friends";
    			}
    			else if (input.equals("4"))
    			{
    				//update database with view privilege to specific friends
    				output = "Reset post privilege to Specific friends";
    			}
    		}
    		this.variables.step_RESET_PRIVILEGE++;
    	}
    	else if (this.variables.step_RESET_PRIVILEGE == 7)
    	{
    		output = "userInput";
    		this.variables.step_RESET_PRIVILEGE++;
    	}
    	else if (this.variables.step_RESET_PRIVILEGE == 8)
    	{
    		variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
			variables.nextScreen = this.variables.RESET_PRIVILEGE_SCRREN;
			
			output = this.screenOutput(variables.nextScreen /*refresh screen*/);
			this.variables.step_RESET_PRIVILEGE = 0;
    	}
    	return output;
    }
}
