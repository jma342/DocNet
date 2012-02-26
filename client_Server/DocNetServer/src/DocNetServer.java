import java.net.*;
import java.io.*;
import java.sql.*;


public class DocNetServer 
{
	private static final int PORT_NUMBER = 427;
	
    public static void main(String[] args) throws IOException 
    {

    	//jma 342 -- Feb 21st
    	
    	Connection con_1 = null;
    	Connection con_2 = null;
        //Statement st = null;
        //ResultSet rs = null;
        
        //String dbHostname = "docnetdbinstance.chjl3x8ea8tv.us-east-1.rds.amazonaws.com";
        String dbHostname = "localhost";
        String dbUser = "root";
        
        String dbName_1 = "docnetdb_1";
        String dbconn_1 = "jdbc:mysql://" + dbHostname + "/" + dbName_1;
        String dbName_2 = "docnetdb_2";
        String dbconn_2 = "jdbc:mysql://" + dbHostname + "/" + dbName_2;
 
        
        //String dbPassword = "CS54305188";
        String dbPassword = "";

        try 
        {
            con_1 = DriverManager.getConnection(dbconn_1, dbUser, dbPassword);
            con_2 = DriverManager.getConnection(dbconn_2, dbUser, dbPassword);
            System.out.println("Connected to DB");
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex.toString());

        }
        
        ServerSocket serverSocket = null;
        boolean listening = true;
 
        try 
        {
            serverSocket = new ServerSocket(PORT_NUMBER);
        } 
        catch (IOException e) 
        {
            System.err.println("Could not listen on port: " + PORT_NUMBER);
            System.exit(-1);
        }
 
        System.out.println("Server started\nListening port: " + PORT_NUMBER);
        
        while (listening)
        	new DNServerThread(serverSocket.accept(), con_1, con_2).start();
 
        serverSocket.close();
        System.out.println("Server stopped");
        
        try 
        {
        	if (con_1 != null) 
        	{
        		con_1.close();
        	}
        	if (con_2 != null) 
        	{
        		con_2.close();
        	}
        } 
        catch (SQLException ex) 
        {
        	System.out.println(ex.toString());
        }
        System.out.println("Disconnected from DB");
    }
}
