import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.Calendar;

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

    	try 
    	{
    		variables.st_con1 = variables.con_1.createStatement();
			variables.st_con2 = variables.con_2.createStatement();
		} 
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    	else if(variables.currentScreen == variables.RESEARCH_GROUPS_SCREEN)
    	{
    		output = this.researchGroupScreen(input);
    	}
    	//jma342 - feb 25 1:52AM - Merging cindy's code
    	
    	/*else if(variables.currentScreen == variables.CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN)
    	{
    		output = created_PubDisc_ResGrp_Priviliges_Screen(input);
    	}*/
    	
    	//add new screen for reset privilege and screen for privileges-- rw 446 -- Feb 26th
    	else if(variables.currentScreen == this.variables.PRIVILEGES_SCREEN)
    	{
    		output = this.PrivilegeScreen(input);
    	}
    	
    	else if(variables.currentScreen == this.variables.RESET_PRIVILEGE_SCRREN)
    	{
    		output = this.ResetPrivilegeScreen(input);
    	}
    	//jma342 - feb 26 2:48pm
    	else if(variables.currentScreen == this.variables.SELECT_FRIENDS_FOR_PRIVILEGE_SCREEN)
    	{
    		output = selectFriendsPrivilegeScreen(input);
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
    		if(screen == this.variables.PERSONAL_INFORMATION_SCREEN)
    		{
    			variables.sqlString_con2 = "SELECT * FROM users WHERE user_id = " + Integer.parseInt(variables.loggedIn_User_ID);
    			variables.sqlString_con1 = "SELECT * FROM user_info WHERE user_id = " + Integer.parseInt(variables.loggedIn_User_ID);
    			
    			this.selectQuery_con2();
    			this.selectQuery_con1();
    			/*try 
    			{
					variables.rs_con2 = variables.st_con2.executeQuery(variables.sqlString_con2);
					variables.rs_con1 = variables.st_con1.executeQuery(variables.sqlString_con1);
				} 
    			catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
    		}
    		//try to figure how to clearscreen
    		if(this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			try 
    			{
    				if(variables.rs_con2.next())
    				{
	    				//System.out.println(variables.rs_con2.getNString("first_name")); 
						output = "1. First Name: " + variables.rs_con2.getString("first_name") /*!= null ? 
														variables.rs_con2.getNString("first_name") :""*/;
    				}
    				else
    				{
    					output = "First Name: ";
    				}
				} catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  //+ db.firtname
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 1)
    		{
    			try 
    			{
    				if(variables.rs_con2.next())
    				{
    					output ="2. Last Name: " + variables.rs_con2.getString("last_name");
    				}
    				else
    				{
    					output = "2. Last Name: ";
    				}
				} 
    			catch (SQLException e) 
    			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//db.lastname
    			
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 2)
    		{
    			try 
    			{
    				if(variables.rs_con1.next())
    				{
    					output = "3. Age: " + variables.rs_con1.getString("age");
    				}
    				else
    				{
    					output = "3. Age: ";
    				}
				} 
    			catch (SQLException e) 
    			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//db.age
    			
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 3)
    		{
    			
    			try 
    			{
    				if(variables.rs_con1.next())
    				{
    					output = "4. Gender: " + variables.rs_con1.getString("gender");
    				}
    				else
    				{
    					output = "4. Gender: ";
    				}
    				
				} 
    			catch (SQLException e) 
    			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//db.gender
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 4)
    		{
    			output = "5. Marital Status(enum currently missing from db)";//db.maritalStatus
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 5)
    		{
    			try 
    			{
    				if(variables.rs_con1.next())
    				{
    					output = "6. Home Address: "+ variables.rs_con1.getString("home_address");
    				}
    				else
    				{
    					output = "6. Home Address: ";
    				}
				} 
    			catch (SQLException e) 
    			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//db.HomeAddress
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 6)
    		{
    			try 
    			{
    				if(variables.rs_con1.next())
    				{
    					output = "7. Home Number: " + variables.rs_con1.getString("home_phone");
    				}
    				else
    				{
    					output = "7. Home Number: ";
    				}
				} 
    			catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//db.Home Number
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 7)
    		{
    			try 
    			{
    				if(variables.rs_con1.next())
    				{
    					output = "8. Cell Number"+ variables.rs_con1.getString("cell_phone");
    				}
    				else
    				{
    					output = "8. Cell Number: ";
    				}
				} 
    			catch (SQLException e) 
    			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//db.Cell Number
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 8)
    		{
    			try 
    			{
    				if(variables.rs_con1.next())
    				{
    					output = "9.Hospital" + variables.rs_con1.getString("hospital");
    				}
    				else
    				{
    					output = "9. Hosptial: ";
    				}
				} 
    			catch (SQLException e) 
    			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//db.Hospital
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 9)
    		{
    			try 
    			{
    				if(variables.rs_con1.next())
    				{
    					output = "10. Hospital Address" + variables.rs_con1.getString("hospital_address");
    				}
    				else
    				{
    					output = "10. Hospital Address: ";
    				}
				} 
    			catch (SQLException e) 
    			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//db.Hospital Address
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 10)
    		{
    			try 
    			{
    				if(variables.rs_con1.next())
    				{
    					output = "11. Work Number" + variables.rs_con1.getString("work_number");
    				}
    				else
    				{
    					output = "11. Work Number";
    				}
				} 
    			catch (SQLException e) 
    			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//db.workNumber
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 11)
    		{
    			try 
    			{
    				if(variables.rs_con1.next())
    				{
    					output = "12. Specialty" + variables.rs_con1.getString("specialty");
    				}
    				else
    				{
    					output = "12. Specialty: ";
    				}
				} 
    			catch (SQLException e) 
    			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//db.specialty
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
    			//retrives all research publications post by user
    			if(variables.step_queryingOrUpdatingDB == 0)
    			{
    				variables.sqlString_con1 = "Select * from res_pub where user_id = " + 
    								variables.loggedIn_User_ID;
    				
    				this.selectQuery_con1();
    				variables.step_queryingOrUpdatingDB++;
    			}
    			
    			//displays the users who requested you
    			else if(variables.step_queryingOrUpdatingDB == 1)
    			{  //add database connection for viewing publications on posting board -- rw 446 -- Feb 28th
    				try 
    				{
						if(variables.rs_con1.next())
						{
							
							output = variables.researchPublicationCount + ". " + variables.rs_con1.getString("authors") + 
									" " + variables.rs_con1.getString("title");
							
							//stores id for research publications
							variables.researchPublicationList.add(Integer.parseInt(variables.rs_con1.getString("res_pub_id")));
							
							variables.researchPublicationCount++;
						}
						else
						{
							variables.step_queryingOrUpdatingDB = 0;
							variables.step_SCREEN_OUTPUT++;
							variables.researchPublicationCount = 1;
						}
					} 
    				catch (NumberFormatException e) 
    				{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
    				catch (SQLException e) 
    				{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
        		//output = "get publications from db";
        		
    		}
    		else if (variables.step_SCREEN_OUTPUT==1)
    		{
    			output = "a. Add a publication";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if (variables.step_SCREEN_OUTPUT==2)
    		{
    			output = "b. Delete a publication";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		//jma342 - feb 26th - allowed the user to return to the parent screen
    		else if (variables.step_SCREEN_OUTPUT==3)
    		{
    			output = "c. Return to Main Posting board";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT ==4)
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
    			if(variables.step_queryingOrUpdatingDB == 0)
    			{
    				variables.sqlString_con1 = "Select * from ancmt where user_id = " + 
    								variables.loggedIn_User_ID;
    				
    				this.selectQuery_con1();
    				variables.step_queryingOrUpdatingDB++;
    			}
    			
    			//displays the users who requested you
    			else if(variables.step_queryingOrUpdatingDB == 1)
    			{  //add database connection for viewing publications on posting board -- rw 446 -- Feb 28th
    				try 
    				{
						if(variables.rs_con1.next())
						{
							variables.sqlString_con2 = "Select * from users where user_id = " + 
									Integer.parseInt(variables.rs_con1.getString("user_id"));
							
							this.selectQuery_con2();
							
							output = variables.announcementCount + ". " + variables.rs_con1.getString("date")+" " + variables.rs_con1.getString("time")+ 
									" " + variables.rs_con1.getString("anmct") + variables.rs_con2.getString("first_name") + 
									" " + variables.rs_con2.getString("last_name");
							
							//stores id for each friend request as they are displayed on screen
							variables.accouncementListIDS.add(Integer.parseInt(variables.rs_con1.getString("ancmt_id")));
							
							variables.announcementCount++;
							
						}
						else
						{
							variables.step_queryingOrUpdatingDB = 0;
							variables.step_SCREEN_OUTPUT++;
							variables.announcementCount = 1;
						}
					} 
    				catch (NumberFormatException e) 
    				{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
    				catch (SQLException e) 
    				{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    			
    		  
    		}
    		else if(variables.step_SCREEN_OUTPUT== 1)
    		{
    			output = "a. Add an announcement";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT ==2)
    		{
    			output = "b. Delete an announcement";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		
    		//jma342 - Feb 26 - 4:14pm
       		else if(variables.step_SCREEN_OUTPUT ==3)
    		{
    			output = "c. Return to your main posting board.";
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
    			//retrives all of the discussions from the database
    			if(variables.step_queryingOrUpdatingDB == 0)
    			{
	    			variables.sqlString_con1 = "Select * from disc";
	    			this.selectQuery_con1();
	    			variables.step_queryingOrUpdatingDB++;
    			}
    			
    			//displays each of the discussions
    			else if(variables.step_queryingOrUpdatingDB == 1)
    			{
    				try 
    				{
    					//displays the titles of each of the discussions
						if(variables.rs_con1.next())
						{
							output = variables.discussionCount + ". " + variables.rs_con1.getString("title");
							
							//stores the ids of the discussions
							variables.discussionListIDS.add(Integer.parseInt(variables.rs_con1.getString("disc_id")));
							
							variables.discussionCount++;
						}
						//when the recordset is exhausted move to next step.
						else
						{
							variables.step_SCREEN_OUTPUT++;
							variables.step_queryingOrUpdatingDB = 0;
							variables.discussionCount = 1;
						}
					} 
    				catch (SQLException e) 
    				{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
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
    		else if(variables.step_SCREEN_OUTPUT == 3)
    		{
    			output = "3. Comment on a discussion";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 4)
    		{
    			output = "4. Return to Main Posting Board";
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
    			//retrieve discussion comments for chosen discussion
    			if(variables.step_queryingOrUpdatingDB == 0)
    			{
    				
    				variables.sqlString_con1 = "Select * from pub_disc_com where dis_id = " + 
    						variables.discussionListIDS.elementAt(variables.chosenDiscussion);
    				
    				this.selectQuery_con1();
    				variables.step_queryingOrUpdatingDB++;
    				
    			}
    			
    			//display all of the comments made to a discussion consisting of the author and the corresponding
    			//comment
    			else if(variables.step_queryingOrUpdatingDB == 1)
    			{
    				try 
    				{
						if(variables.rs_con1.next())
						{
							//retrieves each authors name via user_id
							variables.sqlString_con2 = "Select * from users where user_id  = " + 
										variables.rs_con1.getString("user_id");
							
							//displays each authors name and corresponding comment
							output = variables.discussionCommentCount + ". " + variables.rs_con2.getString("first_name") + " " + 
										variables.rs_con2.getString("last_name") + ": " + 
									variables.rs_con1.getString("comment");
							
							variables.discussionCommentCount++;
							//store the comments ids
							variables.discussionCommentsIDS.add(Integer.parseInt(variables.rs_con1.getString("disc_cmt_id")));
							
						}
						else
						{
							variables.discussionCommentCount = 1;
							variables.step_queryingOrUpdatingDB = 0;
							variables.step_SCREEN_OUTPUT++;
						}
					} 
    				catch (SQLException e) 
    				{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    			
    		}

    		else if(variables.step_SCREEN_OUTPUT == 1)
    		{
    			output = "1. Add a new comment";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(variables.step_SCREEN_OUTPUT == 2)
    		{
    			output = "2. Delete a comment";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else
    		{
    			variables.step_SCREEN_OUTPUT = 0;
    			output = "userInput";
    			variables.discussionListIDS.clear();
    			variables.currentScreen = variables.nextScreen;
    		}
    	}
    	//jma342 - Feb26th 1:54AM - merging in Cindy's code
    	
    	else if(screen == this.variables.RESEARCH_GROUPS_SCREEN)
    	{
    		if(this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			if(variables.step_queryingOrUpdatingDB == 0)
    			{
    				//gather all of the research groups user is currently a member of
    				variables.sqlString_con2 = "Select * from res_gro_mem where user_id = " + 
    							variables.loggedIn_User_ID;
    				
    				this.selectQuery_con2();
    				
    				variables.step_queryingOrUpdatingDB++;
    				
    			}
    			else if(variables.step_queryingOrUpdatingDB == 1)
    			{
    				try 
    				{
						if(variables.rs_con2.next())
						{
							variables.sqlString_con1 = "Select * from res_gro where res_gro_id = " +
										Integer.parseInt(variables.rs_con2.getString("res_gro_id"));
							
							this.selectQuery_con1();
							
							output = variables.researchGrpCount + ". " + variables.rs_con1.getString("title");
							variables.researchGrpCount++;
							
							//storing the ids for all displayed researched groups
							variables.researchGrpIDS.add(Integer.parseInt(variables.rs_con2.getString("res_gro_id")));
						}
						else
						{
							output = "-----End of Research Groups-----";
							variables.step_queryingOrUpdatingDB = 0;
							variables.researchGrpCount++;
						}
					} 
    				catch (NumberFormatException e) 
    				{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
    				catch (SQLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		}
    	}
    	//change made - jma 342 - feb 19th
    	else if(screen == this.variables.user_FRIENDS_LIST_SCREEN || 
    			screen == this.variables.FRIEND_FRIENDS_LIST_SCREEN || screen == this.variables.SELECT_FRIENDS_FOR_PRIVILEGE_SCREEN)
    	{
    		if(this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			if(screen == this.variables.user_FRIENDS_LIST_SCREEN || screen == this.variables.SELECT_FRIENDS_FOR_PRIVILEGE_SCREEN)
    			{
    				if(variables.step_FriendsListRetrieval == 0)
    				{
	    				variables.sqlString_con1 = "SELECT * FROM friends_list WHERE user_id = " + Integer.parseInt(variables.loggedIn_User_ID);
	        			this.selectQuery_con1();
	        			variables.step_FriendsListRetrieval++;
    				}
        			
    				else if(variables.step_FriendsListRetrieval == 1)
    				{
    					//loops through list of friends on reentry and grabs each friend's name(first and last) from the users table in db2
	        			try 
	        			{
	        				if(variables.rs_con1.next())
	        				{
	        					//retrieves the names of all of the user's current friends
	        					variables.sqlString_con1 = "Select * from users where user_id = " + 
	        								Integer.parseInt(variables.rs_con1.getString("friend_id"));
	        					
	        					variables.friendsListIDS.add(Integer.parseInt(variables.rs_con1.getString("friend_id")));
	        					
	        					output = variables.friendsListCount + variables.rs_con1.getString("first_name") + " " + variables.rs_con1.getString("last_name");
	        					variables.friendsListCount++;
	        				}
	        				else
	        				{
	        					output = "-----------End of Friends List----------------";
	        					variables.step_SCREEN_OUTPUT++;
	        					variables.step_FriendsListRetrieval = 0;
	        					variables.friendsListCount = 1;
	        				}
	    				} 
	        			catch (SQLException e) 
	    				{
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
    				}
    			}
    			//retrieve records from the friends list
    			//output = rs.firstrecord
    			
    			//allow recordset to move to next record on
    			//reentry in to this function
    			//once the last record is added to the output variable
    			
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 1)
    		{
    			if(screen == this.variables.user_FRIENDS_LIST_SCREEN || 
    	    			screen == this.variables.FRIEND_FRIENDS_LIST_SCREEN)
    			{
    				output = "Please select one of the following actions:";
    				variables.step_SCREEN_OUTPUT++;
    			}
    			//the select friends privilege screen consists of actions different to the other
    			//screens utilising this protocol
    			else if(screen == this.variables.SELECT_FRIENDS_FOR_PRIVILEGE_SCREEN)
    			{
    				this.variables.currentScreen = this.variables.nextScreen;
    				variables.step_SCREEN_OUTPUT = 0;
    			}
    			
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
    	
    	/*else if(screen == this.variables.ADD_FRIEND_SCREEN)
    	{
    		//jma342 - Feb 25 - 3:22pm - informs user to return to previous screen
    		if(this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			output = "Please enter the first name of desired friend(enter -1 to cancel):";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 1)
    		{
    			output = "userInput";
    			variables.step_SCREEN_OUTPUT++;
    			this.variables.step_SCREEN_OUTPUT = 0;
    			variables.currentScreen = variables.nextScreen;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 2)
    		{
    			variables.desiredFriendFirstName = input;
    			
    			output = "Please enter the last name (enter -1 to return to cancel):";
    			variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 1)
    		{
    			output = "userInput";
    			variables.step_SCREEN_OUTPUT++;
    			this.variables.step_SCREEN_OUTPUT = 0;
    			variables.currentScreen = variables.nextScreen;
    		}
    	}*/
    	
    	/*//jma342 - feb26th - 3:58 am
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
    	}*/
    	
    	else if(screen == this.variables.user_FRIEND_REQUESTS_SCREEN)
    	{
    	
    		if(this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			//retrives all of the pending friend requests
    			if(variables.step_queryingOrUpdatingDB == 0)
    			{
    				variables.sqlString_con1 = "Select * from friend_req where requestee_id = " + 
    								variables.loggedIn_User_ID;
    				
    				this.selectQuery_con1();
    				variables.step_queryingOrUpdatingDB++;
    			}
    			
    			//displays the users who requested you
    			else if(variables.step_queryingOrUpdatingDB == 1)
    			{
    				try 
    				{
						if(variables.rs_con1.next())
						{
							variables.sqlString_con2 = "Select * from users where user_id = " + 
									Integer.parseInt(variables.rs_con1.getString("requester_id"));
							
							this.selectQuery_con2();
							
							output = variables.friendsListCount + ". " + variables.rs_con2.getString("first_name") + 
									" " + variables.rs_con2.getString("last_name");
							
							//stores id for each friend request as they are displayed on screen
							variables.friendsListIDS.add(Integer.parseInt(variables.rs_con1.getString("friend_req_id")));
							
							variables.friendsListCount++;
						}
						else
						{
							variables.step_queryingOrUpdatingDB = 0;
							variables.step_SCREEN_OUTPUT++;
							variables.friendsListCount = 1;
						}
					} 
    				catch (NumberFormatException e) 
    				{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
    				catch (SQLException e) 
    				{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    			
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 1)
    		{
				output = "Please enter the number of the desired friend request from the list(enter -1 to cancel)";
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
    	
    	//change made - rw  446  -feb 26th
    	//add in privilege screen
    	else if(screen == this.variables.PRIVILEGES_SCREEN)
    	{
    		if (this.variables.step_SCREEN_OUTPUT == 0)
    		{
    			output = "1. Personal Information Region";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 2)
    		{
    			output = "2. Announcements Region";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 3)
    		{
    			output = "3. Research Publications Region";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 4)
    		{
    			output = "4. Public Discussions Region";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 5)
    		{
    			output = "5. Research Group Region";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 6)
    		{
    			output = "6. Announcements Region";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 7)
    		{
    			output = "7. Public Discussion Posting Boards";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		else if(this.variables.step_SCREEN_OUTPUT == 8)
    		{
    			output = "8. Return to your Main Posting Board";
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
    		
    		//jma342 - Feb 26th - added the option to return to parent screen. 
    		else if(this.variables.step_SCREEN_OUTPUT == 2)
    		{
    			output = "3. Return to Privileges Screen";
    			this.variables.step_SCREEN_OUTPUT++;
    		}
    		
    		else if(this.variables.step_SCREEN_OUTPUT == 3)
    		{
    			output = "Please select the number to reset privilege";
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
		    				variables.step_LOG_IN_SCREEN = 11;/*jma342 - feb 27th - resets screen to 
		    													stage for comparing passwords*/
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
						
						variables.sqlString_con2 = "SELECT username FROM users WHERE username = \'" + variables.userName + "\'";
						
						this.selectQuery_con2();
						
						//variables.rs_con2 = variables.st_con2.executeQuery(variables.sqlString_con2);
						
						//jma342 - feb26 10:51 pm - determines if recordset has anything
						/*while(variables.rs_con2.next())
						{
							rsCount++;
							break;
						}*/
						
						if (variables.rs_con2.next())//(rsCount != 0) 
						{
							output = "Registration failed.\'" + variables.userName + "\' is already registered. " +
									"Press any key to continue...";
							variables.failedLogIn = true;
							variables.step_LOG_IN_SCREEN++;
						}
						else 
						{
							variables.sqlString_con2 = "INSERT INTO users (username, password) VALUES " +
									"(\'" + variables.userName + "\', \'" + variables.password + "\')";
							rsCount = 0;
							
							//rsCount = variables.st_con2.executeUpdate(variables.sqlString_con2);
							
							rsCount = this.InsertUpdateDeleteQuery_con2();
							
							
							if (rsCount != 0) 
							{
								//output = "Registration successful.";
								variables.failedLogIn = false;
								
								variables.sqlString_con2 = "SELECT user_id FROM users WHERE username = \'" + 
					    				variables.userName + "\' AND password = \'" + variables.password + "\'";
								
								//variables.rs_con2 = variables.st_con2.executeQuery(variables.sqlString_con2);
								
								this.selectQuery_con2();
								
								if(variables.rs_con2.next())
								{
									variables.loggedIn_User_ID = variables.rs_con2.getString("user_id");//stores user id of logged in user
								}
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
		    	else if(variables.step_LOG_IN_SCREEN == 9)
		    	{
		    		//password = "";
		    		variables.password = input;
		    		
		    		variables.sqlString_con2 = "SELECT * FROM users WHERE username = \'" + 
		    				variables.userName + "\' AND password = \'" + variables.password + "\'";
					try 
					{
						//variables.rs_con2 = variables.st_con2.executeQuery(variables.sqlString_con2);
						this.selectQuery_con2();
						
						if (variables.rs_con2.next() == false) 
						{
							output = "Either username or password is wrong...press any key to continue";
							variables.failedLogIn = true;
							variables.step_LOG_IN_SCREEN++;
						}
						else 
						{
							variables.failedLogIn = false;
							variables.loggedIn_User_ID = variables.rs_con2.getString("user_id");
							//output = "Access granted.";
							variables.step_LOG_IN_SCREEN++;
						}
						
					} 
					catch (SQLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	        	}
		    	
		    	//after a successful login user is directed to main posting board
		    	else if(variables.step_LOG_IN_SCREEN == 10)
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
				variables.editField_On_Screen = "first_name";
	    		output = "Edit First Name: ";
	    		variables.usingDb_2 = true;
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit Last Name
			else if(input.equals("2"))
			{
				variables.editField_On_Screen = "last_name";
	    		output = "Edit Last Name: ";
	    		variables.usingDb_2 = true;
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit Last Age
			else if(input.equals("3"))
			{
				variables.editField_On_Screen = "age";
	    		output = "Edit Age: ";
	    		variables.usingDb_1 = true;
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit Gender
			else if(input.equals("4"))
			{
				variables.editField_On_Screen = "gender";
				variables.usingDb_1 = true;
				variables.step_PERSONAL_INFORMATION++;
	    		/*variables.nextScreen = this.variables.GENDER_SCREEN;
	    		variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
	    		
	    		output = this.screenOutput(variables.nextScreen);
	    		variables.step_PERSONAL_INFORMATION = 0;*/

			}
			
			//Edit Marital Status- update this when the enumeratgor is added to db table
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
				variables.editField_On_Screen = "home_address";
	    		output = "Edit Home Address: ";
	    		variables.usingDb_1 = true;
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit Home Number
			else if(input.equals("7"))
			{
				variables.editField_On_Screen = "home_phone";
	    		output = "Edit Home Number: ";
	    		variables.usingDb_1 = true;
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit Cell Number
			else if(input.equals("8"))
			{
				variables.editField_On_Screen = "cell_phone";
	    		output = "Edit Cell Number: ";
	    		variables.usingDb_1 = true;
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit Hospital
			else if(input.equals("9"))
			{
				variables.editField_On_Screen = "hospital";
	    		output = "Edit Hospital: ";
	    		variables.usingDb_1 = true;
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit Hospital Address
			else if(input.equals("10"))
			{
				variables.editField_On_Screen = "hospital_address";
	    		output = "Edit Hospital Address: ";
	    		variables.usingDb_1 = true;
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit work Number
			else if(input.equals("11"))
			{
				variables.editField_On_Screen = "work_number";
	    		output = "Edit Work Number: ";
	    		variables.usingDb_1 = true;
	    		variables.step_PERSONAL_INFORMATION++;
			}
			
			//Edit specialty
			else if(input.equals("12"))
			{
				variables.editField_On_Screen = "specialty";
	    		output = "Edit Specialty: ";
	    		variables.usingDb_1 = true;
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
	    		
	    		variables.step_PERSONAL_INFORMATION = 0;
	    		
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
    		if(variables.usingDb_1)//general information database
    		{
    			variables.sqlString_con1 = "select * from user_info where user_id = " + Integer.parseInt(variables.loggedIn_User_ID);
    			    			
    			try 
    			{
    				this.selectQuery_con1();
    				//variables.rs_con1 = variables.st_con1.executeQuery(variables.sqlString_con1);
    				
    				//if record exists in general database then update it
    				if(variables.rs_con1.next())
    				{
    					variables.sqlString_con1 = "update user_info set " + variables.editField_On_Screen + 
            					" = \'" + input + "\' where user_id = " + Integer.parseInt(variables.loggedIn_User_ID);
    				
    					//variables.st_con1.executeUpdate(variables.sqlString_con1);
    					this.InsertUpdateDeleteQuery_con1();
    				}
    				//otherwise insert record
    				else
    				{
    					/*variables.sqlString_con2 = "INSERT INTO users (username, password) VALUES " +
								"(\'" + variables.userName + "\', \'" + variables.password + "\')";*/
    					
    					variables.sqlString_con1 = "insert into user_info (user_id, " + variables.editField_On_Screen + ") " +
    							"values (\'" + variables.loggedIn_User_ID + "\' , \'" + input + "\' )";  
            					
    					this.InsertUpdateDeleteQuery_con1();
    					//variables.st_con1.executeUpdate(variables.sqlString_con1);
    				}
    				
				} 
    			catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
    			
    			variables.usingDb_1 = false;
    		}
    		else if(variables.usingDb_2)//users, privileges databases	
    		{
    			variables.sqlString_con2 = "update users set " + variables.editField_On_Screen + 
    					" = \'" + input + "\' where user_id = " + Integer.parseInt(variables.loggedIn_User_ID);
    			/*try 
    			{*/
    				this.InsertUpdateDeleteQuery_con2();
					//variables.st_con2.executeUpdate(variables.sqlString_con2);
					
				//} 
    			/*catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	*/
    			
    			variables.usingDb_2 = false;
    		}
    		
    		
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
    
    public String researchGroupScreen(String input)
    {
    	String output = "";
    	
    	return output;
    	
    }
    // for research publication screen--rw446 Feb 18th
    public String researchPublicationsScreen(String input)
    {
    	String output = "";
    	
    	if(this.variables.step_RESEARCH_PUBLICATIONS==0)
    	{
    		//add a new publications
    		if (input.equals("a"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			
    			output = "Please enter the author" +
    					"publications(eg. John Smith)(enter -1 to cancel):";
    			
    			this.variables.step_RESEARCH_PUBLICATIONS++;
    			
    		}
    		
    		//delete a publications
    		else if (input.equals("b"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			
    			output = "Please select the publications that you want to delete(enter -1 to cancel)";
    			
    			this.variables.step_RESEARCH_PUBLICATIONS++;
    			
    		}
    		
       		//return to main posting board
    		else if (input.equals("c"))
    		{	
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
        		variables.nextScreen = this.variables.MAIN_POSTING_BOARD_SCREEN;
        		
        		output = this.screenOutput(variables.nextScreen);
        		this.variables.step_RESEARCH_PUBLICATIONS=0;

    		}

    	}
    	
    	else if(this.variables.step_RESEARCH_PUBLICATIONS == 1)
    	{
    		output = "userInput";
    		this.variables.step_RESEARCH_PUBLICATIONS++;
    		
    	}
    	else if (this.variables.step_RESEARCH_PUBLICATIONS == 2)
    	{
    		//add publications
    		if(this.variables.chosen_On_Screen_Action.equals("a")) 
    		{   
    			if(input.equals("-1"))
    			{
    				output = "Addition operation has been cancelled...Press any key to continue";
    			}
    			else
    			{  //add in database connections for adding publication -- rw 446 -- Feb 28th
    			  
    				if(variables.step_queryingOrUpdatingDB == 0)
    				{
    					variables.researchAuthorAdd = input;
    					output = "Please enter the publication title";
    					variables.step_queryingOrUpdatingDB++;
    					
    				}
    				else if (variables.step_queryingOrUpdatingDB == 1)
    				{
    					output = "userInput";
    					this.variables.step_queryingOrUpdatingDB++;
    					
    				}
    				else if(variables.step_queryingOrUpdatingDB == 2)
    				{
    					
    				     variables.researchTitleAdd = input;
    				
    				     variables.sqlString_con1 = "INSERT INTO res_pub (user_id, authors, title) VALUES " +
									"(\'" + variables.loggedIn_User_ID + "\', \'" + variables.researchAuthorAdd + "\', \'" + variables.researchTitleAdd + "\')";
    					
    				     int rsCount = this.InsertUpdateDeleteQuery_con1();
    					
    					variables.step_queryingOrUpdatingDB++;
    					if (rsCount != 0) 
						{
							
    						output = " Research Publications update successful....press any key to continue";
							
						    variables.step_RESEARCH_PUBLICATIONS++;
						}
						else 
						{
							output = "Research Publication failed....press any key to continue";
							
							variables.step_RESEARCH_PUBLICATIONS++;
						}
    					
    				}
    			  }
    		}
    		
    		//delete publications
    		else if (this.variables.chosen_On_Screen_Action.equals("b"))
    		{
    			if(input.equals("-1"))
    			{
    				output = "Deletion operation has been cancelled...Press any key to continue";
    			}
    			else
    			{  //add database connection for deleting publication record -- rw 446 --Feb 28th
    				try {
    					int index = Integer.parseInt(input);
  	    		        /*verify with the db to delete the publications*/
      				    int publicationId = variables.researchPublicationList.get(index);
      				
      				    variables.sqlString_con1 = "DELETE FROM res_pub WHERE res_pub_id=" + publicationId;
      				    
      				    int rsCount = this.InsertUpdateDeleteQuery_con1();
      				    
      				    if (rsCount !=0 )
      				    {
      				    	output = "Research Publication has been deleted...press any key to continue";
      				    	this.variables.step_RESEARCH_PUBLICATIONS++;
      				    }
      				    else {
      				    	output = "failed to delete research publication ...press any key to continue";
      				    	this.variables.step_RESEARCH_PUBLICATIONS++;
      				    }
    				}
    				catch (NumberFormatException e) 
    				{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
    				catch (Exception e) 
    				{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
    			}
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
    		variables.researchPublicationList.clear(); //rw446 -- Feb 28th -- Add to clear the research Publication List
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
    		if (input.equals("a"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			
    			output = "Please enter announcement(enter -1 to cancel):";
    			
    			this.variables.step_ANNOUNCEMENTS++;
    			
    		}
    		
    		//delete an announcement
    		else if (input.equals("b"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			
    			output = "Please select the announcement that you want to delete(enter -1 to cancel)";
    			
    			this.variables.step_ANNOUNCEMENTS++;
    			
    		}
    		
    		//jma342 - Feb 26th - 4:16PM return to main posting board
    		else if (input.equals("c"))
    		{
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
        		variables.nextScreen = this.variables.MAIN_POSTING_BOARD_SCREEN;
        		
        		output = this.screenOutput(variables.nextScreen);//update screen
        		variables.step_ANNOUNCEMENTS=0;
    			
    		}

    	
    	}
    	
    	else if(this.variables.step_ANNOUNCEMENTS == 1)
    	{
    		output = "userInput";
    		this.variables.step_ANNOUNCEMENTS++;
    		
    	}
    	else if (this.variables.step_ANNOUNCEMENTS == 2)
    	{
    		
    		if(this.variables.chosen_On_Screen_Action.equals("a")) 
    		{
    			//jma342 - feb 26th - updated to allow user to cancel addition operation
    			if(input.equals("-1"))
    			{
    				output = "Addition operation has been cancelled...press any key to continue";
    			}
    			else
    			{  //add in the announcement to database -- rw 446 --Feb 28th
    				if(variables.step_queryingOrUpdatingDB == 0)
    				{
    					variables.announcementAdd = input;
    				
    				     variables.sqlString_con1 = "INSERT INTO ancmt (user_id, ancmt) VALUES " +
									"(\'" + variables.loggedIn_User_ID + "\', \'" + variables.announcementAdd + "\')";
    					
    				     int rsCount = this.InsertUpdateDeleteQuery_con1();
    					
    					 variables.step_queryingOrUpdatingDB++;
    					if (rsCount != 0) 
						{
							
    						output = " Announcement update successful....press any key to continue";
							
						    variables.step_ANNOUNCEMENTS++;
						}
						else 
						{
							output = "Announcement update failed....press any key to continue";
							
							variables.step_ANNOUNCEMENTS++;
						}
    				/*add the announcement to db + Along with the date and time appeneded to the front of the message*/
    		
    			}
    		
    			}	
    		}
    		//delete publications
    		else if (this.variables.chosen_On_Screen_Action.equals("b"))
    		{
    		   //jma342 - feb 26th - updated to allow user to cancel deletion operation
    			if(input.equals("-1"))
    			{
    				output = "Deletion operation has been cancelled...press any key to continue";
    			}
    			
    			/*delete selected announcement*/
    			else
    			{   //rw446 -- Feb 28th - add in delete announcement records from DB
    				int index = Integer.parseInt(input);
	    		        
  				    int announcementId = variables.accouncementListIDS.get(index);
  				
  				    variables.sqlString_con1 = "DELETE FROM res_pub WHERE res_pub_id=" + announcementId;
  				    
  				    int rsCount = this.InsertUpdateDeleteQuery_con1();
  				    
  				    if (rsCount !=0 )
  				    {
  				    	output = "Announcement has been deleted...press any key to continue";
  				    	this.variables.step_ANNOUNCEMENTS++;
  				    }
  				    else {
  				    	output = "failed to delete announcement ...press any key to continue";
  				    	this.variables.step_ANNOUNCEMENTS++;
  				    }
    				
    			}
   
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
    		variables.accouncementListIDS.clear();// rw446 -- Feb 28th -- clear the accouncementListIDS
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
    			
    			output = "Please enter the title of the new discussion(enter -1 to cancel): ";
    			
    			this.variables.step_PUBLIC_DISCUSSIONS++;
    		}
    		
    		//delete a discussion
    		else if(input.equals("2"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			output = "Please enter the number of the discussion you want to delete(enter -1 to cancel): ";
    			this.variables.step_PUBLIC_DISCUSSIONS++;
    		}
    		
    		//comment on existing discussion
    		else if(input.equals("3"))
    		{
       			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
        		variables.nextScreen = this.variables.PUBLIC_DISCUSSION_TOPIC_SCREEN;
        		
        		output = this.screenOutput(variables.nextScreen);//update screen
        		this.variables.step_PUBLIC_DISCUSSIONS=0;
    			
    		}
    		//return to main posting board
    		else if(input.equals("4"))
    		{
       			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
        		variables.nextScreen = this.variables.MAIN_POSTING_BOARD_SCREEN;
        		variables.discussionListIDS.clear();
        		output = this.screenOutput(variables.nextScreen);//update screen
        		this.variables.step_PUBLIC_DISCUSSIONS=0;
    			
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
    			if(input.equals("-1"))
    			{
    				output = "Addidition of discussion has been cancelled...press any key to continue";
    				this.variables.step_PUBLIC_DISCUSSIONS = 5;//skip to step 5
    			}
    			else
    			{
    				//add the discussion to the database
    				variables.sqlString_con1 = "Insert into disc (user_id,title) values (" 
    							+ variables.loggedIn_User_ID + ", \'" + input + "\')";
    				
    				this.selectQuery_con1();
    				
	    			
	    			//TO DO: retrieve id of discussion last inserted and store in variables.publicDiscussionID;
    				
	    			output = "Discussion has been created...press any key to continue";
	    			this.variables.step_PUBLIC_DISCUSSIONS++;
	    			//variables.createdPublicDiscussion = true;
    			}
    			
    			
    		}
    		
    		//confirm the desire to delete selected chosen discussion.
    		else if(this.variables.chosen_On_Screen_Action.equals("2"))
    		{
    			if(input.equals("-1"))
    			{
    				output = "Deletion of discussion has been cancelled...press any key to continue";
    				this.variables.step_PUBLIC_DISCUSSIONS = 5;//skip to step 5
    			}
    			else
    			{
    				//store the chosen discussion
    				variables.chosenDiscussion = Integer.parseInt(input);
    				
	    			output = "Are you sure(Yes,No): ";
	    			
	    			this.variables.step_PUBLIC_DISCUSSIONS++;
    			}
    		}
    	}
    	
    	else if (this.variables.step_PUBLIC_DISCUSSIONS ==3)
    	{
    		
			output = "userInput";
    		this.variables.step_PUBLIC_DISCUSSIONS++;	
    		
    	}
    	//transitioning to privileges screen or verifying whether deletion is deisred
    	else if (this.variables.step_PUBLIC_DISCUSSIONS ==4)
    	{
    		if(this.variables.chosen_On_Screen_Action.equals("1"))
    		{
    			/*variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
	    		variables.nextScreen = this.variables.CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN;
	    		
	    		output = this.screenOutput(variables.currentScreen);
	    		variables.step_PUBLIC_DISCUSSIONS=0;*/
    			variables.step_PUBLIC_DISCUSSIONS++;
    		}
    		
    		//after the user confirms the choice either carry out delete operation or cancel the delete operation
    		else if(this.variables.chosen_On_Screen_Action.equals("2"))
    		{
    			if(input.toLowerCase().equals("yes"))
    			{
    				
    				variables.sqlString_con1 = "Delete from disc where disc_id = " + variables.chosenDiscussion;
    				
    				this.InsertUpdateDeleteQuery_con1();
    				
    				variables.discussionListIDS.clear();
    				
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
    		
    		//if(this.variables.chosen_On_Screen_Action.equals("2"))
    		//{
    			output = "userInput";
        		this.variables.step_PUBLIC_DISCUSSIONS++;
    		//}
    	}
    	
    	else if (this.variables.step_PUBLIC_DISCUSSIONS == 6)
    	{
    		//delete operation protocol is complete...so refresh disucssions screen
    		//if(this.variables.chosen_On_Screen_Action.equals("2"))
    		//{    			
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
	    		variables.nextScreen = this.variables.PUBLIC_DISCUSSIONS_SCREEN;
	    		
	    		output = this.screenOutput(variables.currentScreen);//update screen
	    		variables.step_PUBLIC_DISCUSSIONS=0;
    		//}
    	}
    	
    	return output;
    }
    
    //new screen for display all comments for one specific topic--rw446 Feb 18th
    public String discussionTopicScreen(String input)
    {
    	String output = "";
    	
    	if(this.variables.step_PUBLIC_DISCUSSION_TOPIC==0)
    	{
    		//add a new comment
    		if(input.equals("1"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			
    			output = "Please enter the comment: ";
    			
    			this.variables.step_PUBLIC_DISCUSSION_TOPIC++;
    		}
    		
    		//delete a comment
    		else if(input.equals("2"))
    		{
    			this.variables.chosen_On_Screen_Action = input;
    			
    			output = "Please select the comment you want to delete";
    			
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
    		if(this.variables.chosen_On_Screen_Action.equals("1"))
    		{
    			variables.sqlString_con1 = "Insert into pub_dis_com (dis_id,user_id,comment) values (\'" + 
    						variables.chosenDiscussion + "\' , " + variables.loggedIn_User_ID + 
    						", \'" + input + "\')";
    			
    			this.selectQuery_con1();
    			
    			output = "Comments has been added to db...press anykey to continue\n";
    			this.variables.step_PUBLIC_DISCUSSION_TOPIC++;
    			
    		}
    		else if(this.variables.chosen_On_Screen_Action.equals("2"))
    		{
    			
    			//determines if current user is the owner of the discussion
    			variables.sqlString_con1 = "Select * from disc where disc_id = " + 
    						variables.chosenDiscussion + " and user_id = " + variables.loggedIn_User_ID;
    			
    			this.selectQuery_con1();
    			
    			
    			try 
    			{
    				//current user is the owner of the discussion that user can remove any comment from the discussion
					if(variables.rs_con1.next())
					{
						variables.sqlString_con1 = "Delete from pub_dis_com where dis_cmt_id = " + 
									variables.discussionCommentsIDS.elementAt(Integer.parseInt(input));

						this.InsertUpdateDeleteQuery_con1();
						variables.discussionCommentsIDS.clear();
						output = "The comment has been deleted...press any key to continue";
					}
					
					else
					{
						//since user is not owner of the discussion determining whether user is author of comment
						variables.sqlString_con1 = "Select * from pub_dis_com where dis_cmt_id = " + 
								variables.discussionCommentsIDS.elementAt(Integer.parseInt(input)) + " and " +
								"user_id = " + variables.loggedIn_User_ID;
						this.selectQuery_con1();
						
						//user owns selected comment and is permitted to deleted
						if(variables.rs_con1.next())
						{
							variables.sqlString_con1 = "Delete from pub_dis_com where dis_cmt_id = " + 
									variables.discussionCommentsIDS.elementAt(Integer.parseInt(input));

							this.InsertUpdateDeleteQuery_con1();
							variables.discussionCommentsIDS.clear();
							output = "The comment has been deleted...press any key to continue";
						}
						else
						{
							output = "Deletion operation aborted. You are not allowed to delete this" +
									"comment...press any key to continue";
						}
						
					}
				} 
    			catch (NumberFormatException e) 
    			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
    			catch (SQLException e) 
    			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
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

   /*public String setVieworPostPrivlieges(String input)
   {
	   String output = "";
	   
		if(this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN == 0)
	   	{
	   		//set view/post privileges
	   		if(input.equals("1") || input.equals("2"))
	   		{
	   			output = "display the group options from db";
	   			variables.chosen_On_Screen_Action = input;
	   			this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN++;
	   			
	   		}
	   		
	   	}
		
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
				//set the database statements based on whether a region or posting board or announcement topic
				//is having its privileges set.
				
				//if(variables.createdPublicDiscussion)
				//{
					//create variable to store the table
					//if the screen being updated is the public discussions topci screen or an individual
				//annoucnemtn the id of the public disuccions as well as the id of the anncouncement are needed.
				
					added choosen group number along with discussion id and user id to the view privileges table
					 * for discussions
					 
					
					if(!input.equals("4"))
					{
						output = "View Privileges have been set for discussion..press any key to continue";
						this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN++;
					}
					else
					{
						this.variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
		    			this.variables.nextScreen = this.variables.SELECT_FRIENDS_FOR_PRIVILEGE_SCREEN;
		    			
		    			this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN = 0;
		    			
		        		output = this.screenOutput(variables.nextScreen);
					}
					
				}
				
				else if(variables.)
				{
					added choosen group number along with res grp id and user id to the view privileges table
					 * for res grps
					 
					
					if(!input.equals("4"))
					{
						output = "View Privileges have been set for discussion..press any key to continue";
						this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN++;
					}
					
					//add else which would take you to a screen to select specific friends
				//}
			}
			//post privileges
			else if(variables.chosen_On_Screen_Action.equals("2"))
			{
				if(variables.createdPublicDiscussion)
				{
					added choosen group number along with discussion id and user id to the post privileges table
					 * for discussions
					 
					
					if(!input.equals("4"))
					{
						output = "Post Privileges have been set for discussion..press any key to continue";
						this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN++;
					}
					
					//add else which would take you to a screen to select specific friends
				}
				
				else if(variables.createdResearchGroup)
				{
					added choosen group number along with res grp id and user id to the post privileges table
					 * for res grps
					 
					
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
				//once the view and post privileges have been set return to parent screen
				//else refresh current screen
				if(variables.viewPrivilegesSet && variables.postPrivilegesSet)
				{
					variables.viewPrivilegesSet = variables.postPrivilegesSet = false;
					
					//sets the requisite variable to false after public discussion and research group has been set
					if(variables.createdPublicDiscussion)
					{
						variables.createdPublicDiscussion = false;
					}
					else if(variables.createdResearchGroup)
					{
						variables.createdResearchGroup = false;
					}
					
					this.variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
	    			this.variables.nextScreen = this.variables.PUBLIC_DISCUSSIONS_SCREEN;
	    			
	    			this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN = 0;
	    			
	        		output = this.screenOutput(variables.nextScreen);
	    
				}
				else
				{
					this.variables.step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN = 0;
				}
	   			
			}
				
		}

		
	   return output;
   }
*/    //changes made - jma 342 - Feb 25th - function that allows a user to access various functions of
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

    //allows users to select the friends that will be specifically allocated a privilege 
    public String selectFriendsPrivilegeScreen(String input)
    {
    	String output = "";
    	
    	if(variables.step_SELECT_FRIENDS_FOR_PRIVILEGE_SCREEN == 0)
    	{
    		output = "Please select the friends to have this post privilege(eg.1,2,3 or 1-3 or 1,10-40):";
    		variables.step_SELECT_FRIENDS_FOR_PRIVILEGE_SCREEN++;
    		
    	}
    	else if(variables.step_SELECT_FRIENDS_FOR_PRIVILEGE_SCREEN == 1)
    	{
    		output = "userInput";
    		variables.step_SELECT_FRIENDS_FOR_PRIVILEGE_SCREEN++;
    	}
    	
       	else if(variables.step_SELECT_FRIENDS_FOR_PRIVILEGE_SCREEN == 2)
    	{
       		//extract numbers from string using the , and - as delimiters and add to database
    		output = "Privliges have been set...press any key to continue";
    		variables.step_SELECT_FRIENDS_FOR_PRIVILEGE_SCREEN++;
    	}

    	return output;
    }
    
  //change made - jma 342 - Feb 19th	
    public String user_friendsListSreen(String input)
    {
    	String output = " ";
    	
    	if(this.variables.step_FRIENDS_LIST == 0)
    	{
    		//add a friend
    		if(input.equals("1"))
    		{
    			//variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			variables.currentScreen = this.variables.ADD_FRIEND_SCREEN;
    			variables.friendsListIDS.clear();/*empties this vector as it was 
    											populated with the list of friends displayed
    											and it needs to be reused with the add friend screen*/ 
    			output = "";
    			//output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/);
    			
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
    			variables.friendsListIDS.clear();
    			output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/);
    			this.variables.step_FRIENDS_LIST=0;
    			
    		}
    		
    		else if(input.equals("5"))
    		{
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			variables.nextScreen = this.variables.PERSONAL_INFORMATION_SCREEN;
    			variables.friendsListIDS.clear();
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
    				//this call is subsequent to the necessary input value that the user passes in
	    			variables.sqlString_con1 = "Delete from friends_list where user_id = " + 
	    						variables.friendsListIDS.elementAt(Integer.parseInt(input));
	    			
	    			this.InsertUpdateDeleteQuery_con1();
	    			
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
        			variables.friendsListIDS.clear();
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
    			variables.friendsListIDS.clear();
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
    		//jma342 - Feb 25 - 3:22pm - informs user to return to previous screen
    		output = "Please enter the first name of desired friend(enter -1 to cancel):";
    		variables.step_ADD_FRIEND++;
    	}
		else if(this.variables.step_ADD_FRIEND == 1)
		{
			
			output = "userInput";
			variables.step_ADD_FRIEND++;
			/*this.variables.step_SCREEN_OUTPUT = 0;
			variables.currentScreen = variables.nextScreen;*/
		}
		else if(this.variables.step_ADD_FRIEND == 2)
		{
			variables.desiredFriendFirstName = input;
			
			output = "Please enter the last name (enter -1 to return to cancel):";
			variables.step_ADD_FRIEND++;
		}
		else if(this.variables.step_ADD_FRIEND == 3)
		{
			output = "userInput";
			variables.step_ADD_FRIEND++;
			/*this.variables.step_SCREEN_OUTPUT = 0;
			variables.currentScreen = variables.nextScreen;*/
		}
    	
		else if(this.variables.step_ADD_FRIEND == 4)
		{
			//query the database for friends with these names
			if(variables.step_queryingOrUpdatingDB == 0)
			{
				
				variables.desiredFriendLastName = input;
				variables.sqlString_con2 = "Select * from users where first_name = \'" + 
						variables.desiredFriendFirstName + "\' and last_name = \'" + variables.desiredFriendLastName;
				
				this.selectQuery_con2();
				
				variables.step_queryingOrUpdatingDB++;
				
			}
			//queries the database to retrieve the home address from db1 with the name specified to provide some form
			//of uniqueness
			else if(variables.step_queryingOrUpdatingDB == 1)
			{
				try 
				{
					if(variables.rs_con2.next())
					{
						variables.sqlString_con1 = "Select * from user_info where user_id = " + 
									variables.rs_con1.getString("user_id");
									
						this.selectQuery_con1();
						
						output = variables.friendsListCount + ". " + variables.rs_con2.getString("first_name") + 
										" " + variables.rs_con2.getString("last_name") + " - HomeAddress: " + 
								variables.rs_con1.getString("home_address");
						
						variables.friendsListCount++;
						//stores list of potential friends user_ids as they are displayed
						variables.friendsListIDS.add(Integer.parseInt(variables.rs_con2.getString("user_id")));
					}
					else
					{
						variables.step_queryingOrUpdatingDB = 0;
						variables.friendsListCount = 1;
						variables.step_ADD_FRIEND++;
					}
				} 
				catch (NumberFormatException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
    	
		else if(this.variables.step_ADD_FRIEND == 5)
		{
			output = "Please select the friend that you would like to add(enter -1 to cancel): ";
			variables.step_ADD_FRIEND++;
		}
		
		else if(this.variables.step_ADD_FRIEND == 6)
		{
			output = "userInput";
			variables.step_ADD_FRIEND++;
		}
		
		else if(this.variables.step_ADD_FRIEND == 7)
		{
			if(input.equals("-1"))
			{
				output = "Add friend operation has been cancelled...press any key to continue";
				variables.step_ADD_FRIEND++;
			}
			else
			{
				variables.sqlString_con1 = "Insert into friend_req (requester_id,requestee_id) values " + 
							"(" + variables.loggedIn_User_ID + ", " + 
						variables.friendsListIDS.elementAt(Integer.parseInt(input)) + ")";
				
				this.InsertUpdateDeleteQuery_con1();
				
				output = "Friend Request has been sent awaiting response...press any key to continue";
				variables.friendsListIDS.clear();
				variables.step_ADD_FRIEND++;
				
			}
		}
		
	
    	else if(this.variables.step_ADD_FRIEND == 8)
    	{
    		output = "userInput";
    		variables.step_ADD_FRIEND++;
    	}
	
    	else if(this.variables.step_ADD_FRIEND == 9)
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
    			
    			variables.friendsListIDS.clear();
    			
    			output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/);
    			this.variables.step_user_FRIEND_REQUEST = 0;
    		}
    		else
    		{
    			//stores the friend request chosen
    			variables.friendRequestChosen = Integer.parseInt(input);
    			
    			output = "Please enter A or D to Accept or Deny the following friend request from(enter -1 to cancel): ";
    			variables.step_user_FRIEND_REQUEST++;
    		}
    		
    		//jma342 - Feb 25th - 3:49pm - allows user to cancel the operation of accepting a friend request
    	}
    	
    	//retrieve the user first and last name of the selected friend request
    	else if(this.variables.step_user_FRIEND_REQUEST == 1)
    	{
    		//retrive the friend request to get the id for the friend requester.
    		variables.sqlString_con1 = "Select * from friend_req where friend_req_id = " + 
					variables.friendsListIDS.elementAt(variables.friendRequestChosen);
    		
    		this.selectQuery_con1();
    		
    		try 
    		{
    			//store the id of the friend requester 
				variables.requesterForFriendShip = Integer.parseInt(variables.rs_con1.getString("requester_id"));
			} 
    		catch (NumberFormatException e2) 
    		{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} 
    		catch (SQLException e2) 
    		{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
    		
    		this.selectQuery_con1();
    		
    		try 
    		{
    			//retrieve the names of the requester_id
				variables.sqlString_con2 = "Select * from users where user_id = " + 
						variables.requesterForFriendShip;
				
				this.selectQuery_con2();
			}
    		
    		catch (NumberFormatException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    		try 
    		{
    			//display the requester name
				output = variables.rs_con2.getString("first_name") + " " + variables.rs_con2.getString("last_name");
			} 
    		catch (SQLException e) 
    		{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		variables.step_user_FRIEND_REQUEST++;
    	}
    	
    	else if(this.variables.step_user_FRIEND_REQUEST == 2)
    	{
    		output = "userInput";
    		variables.step_user_FRIEND_REQUEST++;
    	}
    	
    	else if(this.variables.step_user_FRIEND_REQUEST == 3)
    	{
    	
    		if(input.equals("A"))
    		{
    			//adds the requester and requestee to each others friends lists
    			variables.sqlString_con1 = "Insert into friends_list (user_id,friend_id) values " + 
    						"(" + variables.loggedIn_User_ID + ", " + variables.requesterForFriendShip + ")";
    			
    			this.InsertUpdateDeleteQuery_con1();
    			
    			variables.sqlString_con1 = "Insert into friends_list (user_id,friend_id) values " + 
						"(" + variables.requesterForFriendShip + ", " + variables.loggedIn_User_ID + ")";
    			
    			this.InsertUpdateDeleteQuery_con1();
    			//adds the requester and requestee to each others friends lists'
    			
    			//removes the friend request from the table as it has been addressed
    			variables.sqlString_con1 = "Delete from friend_req where friend_req_id = " + 
    					variables.friendRequestChosen;
    			
    			this.InsertUpdateDeleteQuery_con1();
    			//removes the friend request from the table as it has been addressed
    			
    			output = "Friend request has been accepted...press any key to continue";
    			
    			variables.step_user_FRIEND_REQUEST++;

    		}
    		
    		else if(input.equals("D"))
    		{
    			//removes the friend request from the table as it has been addressed
    			variables.sqlString_con1 = "Delete from friend_req where friend_req_id = " + 
    					variables.friendRequestChosen;
    			
    			this.InsertUpdateDeleteQuery_con1();
    			//removes the friend request from the table as it has been addressed
    			
    			output = "Friend Request has been denied...press any key to continue";
    			variables.step_user_FRIEND_REQUEST++;
    		}
    		
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
    		
    		//jma342 - feb 26 - added the ability to return to Main postiong board
    		else if(input.equals("8"))
    		{
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    		    variables.nextScreen = this.variables.MAIN_POSTING_BOARD_SCREEN;
    		    this.variables.step_PRIVILEGES_SCREEN = 0;
    		    output = this.screenOutput(variables.nextScreen /*,jump to reset privilege screen*/);
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
    //changed -- rw 446 -- Feb 26th 2012 7:pm
    public String ResetPrivilegeScreen(String input)
    {
    	String output = "";
    	if (this.variables.step_RESET_PRIVILEGE == 0)
    	{     
    	    // added -1 to make sure user can canel reset operation -- rw 446 -- Feb 26th 7pm	
    		if(input.equals("3")) 
    		{
    			variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
    			variables.nextScreen = this.variables.PRIVILEGES_SCREEN;
    			
    			output = this.screenOutput(variables.nextScreen /*,recordset of friendslist*/);
    			this.variables.step_RESET_PRIVILEGE = 0;
    		}
    		else
    		{
    			this.variables.chosen_On_Screen_Action = input; 
       		 
       		     this.variables.step_RESET_PRIVILEGE++;  
    		}
    		 		
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
    	else if(this.variables.step_RESET_PRIVILEGE == 3) //bug due to wrong variable name previously, changed by --rw446 --Feb 26th 7pm
    	{
    		output = "3. All Friends";
    		this.variables.step_RESET_PRIVILEGE++;
    	}
    	else if (this.variables.step_RESET_PRIVILEGE == 4)//bug due to wrong variable name previously, changed by --rw446 --Feb 26th 7pm
    	{
    		output = "4. Specific Friends";
    		this.variables.step_RESET_PRIVILEGE++;
    	}
    	else if (this.variables.step_RESET_PRIVILEGE == 5)
    	{
    		output = "Pleae select specific group to reset privilege";
    		this.variables.step_RESET_PRIVILEGE++;
    	}
    	else if (this.variables.step_RESET_PRIVILEGE == 6)
    	{
    		output = "userInput";
    		this.variables.step_RESET_PRIVILEGE++;
    	}
    	else if (this.variables.step_RESET_PRIVILEGE == 7)
    	{
    		output = "userInput";
    		this.variables.step_RESET_PRIVILEGE++;
    	}
    	else if (this.variables.step_RESET_PRIVILEGE == 7)
    	{
    		if (this.variables.chosen_On_Screen_Action.equals("1"))
    		{
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
    				//switch screen to the specific friends screen
    				output = "Reset post privilege to Specific friends";
    			}
    		}
    		this.variables.step_RESET_PRIVILEGE++;
    	}
    	else if (this.variables.step_RESET_PRIVILEGE == 8)
    	{
    		output = "userInput";
    		this.variables.step_RESET_PRIVILEGE++;
    	}
    	else if (this.variables.step_RESET_PRIVILEGE == 9)
    	{
    		variables.currentScreen = this.variables.CURRENT_OUTPUT_SCREEN;
			variables.nextScreen = this.variables.RESET_PRIVILEGE_SCRREN;
			
			output = this.screenOutput(variables.nextScreen /*refresh screen*/);
			this.variables.step_RESET_PRIVILEGE = 0;
    	}
    	return output;
    }
    
    public void selectQuery_con1()
    {
    	try 
    	{
    		variables.rs_con1 = variables.st_con1.executeQuery(variables.sqlString_con1);
		} 
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    public int InsertUpdateDeleteQuery_con1()
    {
    	int recCount = 0;
    	
    	try 
    	{
    		recCount = variables.st_con1.executeUpdate(variables.sqlString_con1);
		} 
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return recCount;
    }
    public void selectQuery_con2()
    {
    	try 
    	{
    		variables.rs_con2 = variables.st_con2.executeQuery(variables.sqlString_con2);
		} 
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    public int InsertUpdateDeleteQuery_con2()
    {
    	int recCount = 0;
    	
    	try 
    	{
    		recCount = variables.st_con2.executeUpdate(variables.sqlString_con2);
		} 
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return recCount;
		
    }
   
}
