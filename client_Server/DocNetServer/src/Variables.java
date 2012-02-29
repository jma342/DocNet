import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

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
    public static final int CURRENT_OUTPUT_SCREEN = 8;//displays the content for the screen currently in focus
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

    public static final int SET_VIEW_OR_POST_PRIVILEGES_SCREEN = 22;//added -- jma 342 - February 24th 2012
    public static final int SELECT_FRIENDS_FOR_PRIVILEGE_SCREEN = 23;//change made - jma342 - Feb 26th
    public static final int RESET_PRIVILEGE_SCRREN = 24; //added new screen for edit privilege-- rw 446 -Feb 26th



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
    public int step_SET_VIEW_OR_POST_PRIVILEGES_SCREEN = 0;//added -- jma 342 - February 24th 2012 -
    public int step_RESET_PRIVILEGE = 0; //added -- rw  446 - Feb 26th 2012
    																 
    public int step_SELECT_FRIENDS_FOR_PRIVILEGE_SCREEN = 0;//added - jma342 Feb 26th
    /*current step in screens for accessing a friend's screens*/
    public int step_FRIEND_MAIN_POSTING_BOARD_SCREEN = 0;//added -- jma 342 - February 25th 2012
    public int step_FRIEND_PERSONAL_INFORMATION_SCREEN = 0;//added -- jma 342 - February 25th 2012
    public int step_FRIEND_ANNOUNCEMENTS_SCREEN = 0;//added -- jma 342 - February 25th 2012
    public int step_FRIEND_RESEARCH_PUBLICATIONS_SCREEN = 0;//added -- jma 342 - February 25th 2012
    public int step_FRIEND_PUBLIC_DISCUSSIONS_SCREEN = 0;//added -- jma 342 - February 25th 2012
    public int step_FRIEND_RESEARCH_GROUPS_SCREEN = 0;//added -- jma 342 - February 25th 2012
    public int step_FRIEND_user_FRIENDS_LIST_SCREEN = 0;//added -- jma 342 - February 25th 2012
    public int step_FriendsListRetrieval = 0;//dded -- jma342 - February 27th 2012 - keeps track of when to retrieve recordset and when to iterate through it
    /*current step in screens for accessing a friend's screens*/
    
    public int friendsListCount = 1;
    public Vector<Integer> friendsListIDS = new Vector<Integer>();//holds the list of IDS of friends displayed on screen
    public String desiredFriendFirstName = "";//holds the first name of the friend that you desire to add
    public String desiredFriendLastName = "";//hodlds the lastname of the friend that you desire to add
    public int requesterForFriendShip = 0;//used to hold user_id of the friend request that was chosen
    public int friendRequestChosen = 0;//used to hold the friend request that was chosen

    public int discussionCount = 1;
    public Vector<Integer>  discussionListIDS= new Vector<Integer>();//holds the list of IDS of friends displayed on screen
    public int chosenDiscussion = -1;//holds the chosen discussion id.
    public int discussionCommentCount = 1;
    public Vector<Integer>  discussionCommentsIDS= new Vector<Integer>();
     //current step in a given conversation relative to each menu---jma342---Feb 14th
    
    public int researchGrpCount = 1;
    public Vector<Integer>  researchGrpIDS= new Vector<Integer>();//holds the list of IDS of friends displayed on screen
    public int chosenResearchGrp = -1;//holds the chosen discussion id.
    public int researchGrpCommentCount = 1;
    public Vector<Integer>  researchGrpCommentsIDS= new Vector<Integer>();
    
    //jma342 -- feb 21st -- both database connections
    public Connection con_1 = null;
	public Connection con_2 = null;
	Statement st_con1 = null;
	Statement st_con2 = null;
    ResultSet rs_con1 = null;
    ResultSet rs_con2 = null;
    
    //field on screen chosen to be edited
    public String editField_On_Screen = "";
    
    //holds the action chosen to be executed
    public String chosen_On_Screen_Action = "";
    public String userName = "";
    public String password = "";
    public String sqlString_con1 = "";
    public String sqlString_con2 = "";
    
    //current screen user is interfacing with
    public int currentScreen = LOG_IN_SCREEN;
    public int nextScreen = 0;
    
    public boolean failedLogIn = false;//jma342 - feb26th - used to determine if user login failed.
    
    //jma342 - feb 26th 4:13AM - stores id of last pubDiscussion and research group created
    public int publicDiscussionID = -1;
    public int researchGroupID = -1;
    
    //jma342 - feb 26th 4:13AM - indicates whether a publiDiscussion or createdResearch group was recently created
    public boolean publicDiscussionTopic = false;
    public boolean annoucnmentTopic = false;
    
    public boolean publicDiscussionRegion = false;
    public boolean personalInformationRegion = false;
    public boolean researchPublicaionRegion = false;
    public boolean announcementsRegion = false;
    public boolean researchGroupsRegion = false;
    
    
    public boolean viewPrivilegesSet = false;
    public boolean postPrivilegesSet = false;
    
    public String loggedIn_User_ID = "";
    public boolean usingDb_1 = false;//information for screens
    public boolean usingDb_2 = false;//privileges,users
    
    
    public int step_queryingOrUpdatingDB = 0;
    

}
