import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DocNetServer {
    public static void main(String[] args) throws IOException {

    	Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        String dbHostname = "docnetdbinstance.chjl3x8ea8tv.us-east-1.rds.amazonaws.com";
        String dbName = "docnetdb";
        String dbUrl = "jdbc:mysql:/" + dbHostname + "/" + dbName;
        String dbUser = "securicor";
        String dbPassword = "CS54305188";

        try {
            con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            st = con.createStatement();
            
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        
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
        
        DocNetProtocol dnp = new DocNetProtocol();

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
    }
}
}
