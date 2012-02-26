/**
 * 
 */

import java.io.*;
import java.net.*;
import java.sql.*;

/**
 * @author Yerzhan Bissaliyev
 *
 */
public class DNServerThread extends Thread {
	private Socket socket = null;
	private Connection dbCon1 = null;
	private Connection dbCon2 = null;
	 
    public DNServerThread(Socket socket, Connection dbCon1, Connection dbCon2) {
    	super("DNServerThread");
    	this.socket = socket;
    	this.dbCon1 = dbCon1;
    	this.dbCon2 = dbCon2;
    }
 
    public void run() {
 
	    try {
	        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	        BufferedReader in = new BufferedReader(
	                    new InputStreamReader(
	                    socket.getInputStream()));
	 
	        String inputLine, outputLine;
	        
	        DocNetProtocol dnp = new DocNetProtocol(dbCon1, dbCon2);
	        
	        outputLine = dnp.processRequest(null);
	        out.println(outputLine);
	 
	        while ((inputLine = in.readLine()) != null) {
	        	outputLine = dnp.processRequest(inputLine);
	        	out.println(outputLine);
	        }
	        
	        out.close();
	        in.close();
	        socket.close();
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
    }
}
