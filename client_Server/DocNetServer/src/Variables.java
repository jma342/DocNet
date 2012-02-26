import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

//jma342 - Feb 25th - 10:20pm houses all of the class variables that will be used
//within the protocol
public class Variables 
{
	//MENUS---jma342---Feb 14th
    public static final int LOG_IN_SCREEN = 0;
    public static final int MAIN_POSTING_BOARD_SCREEN = 1;
    public static final int PERSONAL_INFORMATION_SCREEN = 2;
    public static final int ANNOUNCEMENTS_SCREEN = 3;
    public static final int RESEARCH_PUBLICATIONS_SCREEN = 4;
    public static final int PUBLIC_DISCUSSIONS_SCREEN = 5;
    public static final int RESEARCH_GROUPS_SCREEN = 6;
    public static final int user_FRIENDS_LIST_SCREEN = 7;
    public static final int CURRENT_OUTPUT_SCREEN = 8;
    public static final int MARITAL_STATUS_SCREEN = 9;//change made - jma342 - Feb 18th
    public static final int GENDER_SCREEN = 10;//change made - jma342 - Feb 18th
    public static final int ADD_FRIEND_SCREEN = 11;//change made - jma342 - Feb 19th
    public static final int user_FRIEND_REQUESTS_SCREEN = 12;//change made - jma342 - Feb 19th
    public static final int PRIVILEGES_SCREEN = 13;//added -- jma 342 - February 24th 2012
    
    /*screens for accessing a friend's screens*/
    public static final int FRIEND_MAIN_POSTING_BOARD_SCREEN = 14;//added -- jma 342 - February 25th 2012
    public static final int FRIEND_PERSONAL_INFORMATION_SCREEN = 15;//added -- jma 342 - February 25th 2012
    public static final int FRIEND_ANNOUNCEMENTS_SCREEN = 16;//added -- jma 342 - February 25th 2012
    public static final int FRIEND_RESEARCH_PUBLICATIONS_SCREEN = 17;//added -- jma 342 - February 25th 2012
    public static final int FRIEND_PUBLIC_DISCUSSIONS_SCREEN = 18;//added -- jma 342 - February 25th 2012
    public static final int FRIEND_RESEARCH_GROUPS_SCREEN = 19;//added -- jma 342 - February 25th 2012
    public static final int FRIEND_FRIENDS_LIST_SCREEN = 20;//added -- jma 342 - February 25th 2012
    /*screens for accessing a friend's screens*/
    
    public static final int PUBLIC_DISCUSSION_TOPIC_SCREEN = 21;//added new screen for specific topic and comments--rw446 Feb 18th    
    public static final int CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN = 22;//added -- jma 342 - February 24th 2012
    //MENUS---jma342---Feb 14th
    
    //current step in a given conversation relative to each menu---jma342---Feb 14th
    public int step_LOG_IN_SCREEN = 0;
    public int step_LOG_IN_SCREEN_reconfirmPword = 0;
    public int step_MAIN_POSTING_BOARD = 0;
    public int step_PERSONAL_INFORMATION = 0;
    public int step_ANNOUNCEMENTS = 0;
    public int step_RESEARCH_PUBLICATIONS = 0;
    public int step_PUBLIC_DISCUSSIONS = 0;
    public int step_RESEARCH_GROUPS = 0;
    public int step_FRIENDS_LIST = 0;
    public int step_SCREEN_OUTPUT = 0;
    public int step_MARITAL_STATUS = 0;//change made - jma342 - Feb 18th
    public int step_GENDER = 0;//change made - jma342 - Feb 18th
    public int step_ADD_FRIEND = 0;//change made - jma342 - Feb 19th
    public int step_user_FRIEND_REQUEST = 0;//change made - jma342 - Feb 19th
    public int step_ERROR_CHECK = 0;//jma342 - Feb 21st
    public int step_PRIVILEGES_SCREEN = 0;//added -- jma 342 - February 24th 2012
    public int step_PUBLIC_DISCUSSION_TOPIC=0;//added for specific topic and comments--rw446--Feb 18th
    public int step_CREATED_PUB_DISC_or_RES_GRP_PRIVILEGES_SCREEN = 0;//added -- jma 342 - February 24th 2012 - 
    																 /*this screen is for when a pub dis or res grp
    																  * is being created and the user is allowed to set
    																  * the privileges during creation.
    																  */
    
    /*current step in screens for accessing a friend's screens*/
    public int step_FRIEND_MAIN_POSTING_BOARD_SCREEN = 0;//added -- jma 342 - February 25th 2012
    public int step_FRIEND_PERSONAL_INFORMATION_SCREEN = 0;//added -- jma 342 - February 25th 2012
    public int step_FRIEND_ANNOUNCEMENTS_SCREEN = 0;//added -- jma 342 - February 25th 2012
    public int step_FRIEND_RESEARCH_PUBLICATIONS_SCREEN = 0;//added -- jma 342 - February 25th 2012
    public int step_FRIEND_PUBLIC_DISCUSSIONS_SCREEN = 0;//added -- jma 342 - February 25th 2012
    public int step_FRIEND_RESEARCH_GROUPS_SCREEN = 0;//added -- jma 342 - February 25th 2012
    public int step_FRIEND_user_FRIENDS_LIST_SCREEN = 0;//added -- jma 342 - February 25th 2012
    /*current step in screens for accessing a friend's screens*/
    
  //current step in a given conversation relative to each menu---jma342---Feb 14th
    
    //jma342 -- feb 21st -- both database connections
    public Connection con_1 = null;
	public Connection con_2 = null;
	Statement st = null;
    ResultSet rs = null;
    
    //field on screen chosen to be edited
    public String editField_On_Screen = "";
    
    //holds the action chosen to be executed
    public String chosen_On_Screen_Action = "";
    public String userName = "";
    public String password = "";
    public String sqlString = "";
    
    //current screen user is interfacing with
    public int currentScreen = LOG_IN_SCREEN;
    public int nextScreen = 0;
    
    public boolean failedLogIn = false;//jma342 - feb26th - used to determine if user login failed.
    
    //jma342 - feb 26th 4:13AM - stores id of last pubDiscussion and research group created
    public int publicDiscussionID = -1;
    public int researchGroupID = -1;
    
    //jma342 - feb 26th 4:13AM - indicates whether a publiDiscussion or createdResearch group was recently created
    public boolean createdPublicDiscussion = false;
    public boolean createdResearchGroup = false;

}
