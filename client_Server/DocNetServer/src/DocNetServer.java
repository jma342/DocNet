import java.net.*;
import java.io.*;
import java.sql.*;


public class DocNetServer {
    public static void main(String[] args) throws IOException {

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

        try {
            con_1 = DriverManager.getConnection(dbconn_1, dbUser, dbPassword);
            con_2 = DriverManager.getConnection(dbconn_2, dbUser, dbPassword);
            //st = con.createStatement();
            
            //rs = st.executeQuery("SELECT VERSION()");

           /* if (rs.next()) {
                System.out.println(rs.getString(1));
            }*/

        } catch (SQLException ex) {
            System.out.println(ex.toString());

        }
        
      //jma 342 -- Feb 21st
        
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(427);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 135.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        
        BufferedReader in = new BufferedReader(
				new InputStreamReader(
				clientSocket.getInputStream()));
        
        String inputLine, outputLine;
        
       DocNetProtocol dnp = new DocNetProtocol(con_1,con_2);//jma342 -- Feb 21st
        //DocNetProtocol dnp = new DocNetProtocol();

        outputLine = dnp.processRequest(null);
       
        out.println(outputLine);


        while ((inputLine = in.readLine()) != null) {
             outputLine = dnp.processRequest(inputLine);
             out.println(outputLine);
            
        }
        
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
       try 
        {
        	//implement this within protocol
             /*   if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }*/
                
        	if (con_1 != null) {
                con_1.close();
            }
        	if (con_2 != null) {
                    con_2.close();
                }
            } 
        catch (SQLException ex) 
        {
            System.out.println(ex.toString());
        }
}
}
