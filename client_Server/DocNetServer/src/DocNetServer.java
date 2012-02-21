import java.net.*;
import java.io.*;
import java.sql.*;

public class DocNetServer {
    public static void main(String[] args) throws IOException {

    	//Comment
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