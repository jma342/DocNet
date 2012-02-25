import java.io.*;
import java.net.*;

/*update string comparisons to input.equals*/
public class DocNetClient {
    public static void main(String[] args) throws IOException {

        Socket dnSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try 
        {
            dnSocket = new Socket("localhost", 427);
            
            out = new PrintWriter(dnSocket.getOutputStream(), true);
            
            in = new BufferedReader(new InputStreamReader(dnSocket.getInputStream()));
            
        } 
        catch (UnknownHostException e) 
        {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        }
        catch (IOException e) 
        {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        
        String fromDocNet;
        String fromDoc="";

         
       /* while(!fromDoc.equals("exit"))
        {*/
        while(true)
        {
        	
        	while (!(fromDocNet = in.readLine()).equals("userInput")) 
            {
        		if(fromDocNet.equals("killConnection"))
        		{
        			break;
        		}
        		
        		else if(!fromDocNet.equals("killonnection"))
        		{
        			System.out.println("DocNet: " + fromDocNet);
        			out.println("");
        		}
            }
                
            
        	//waits for user input
        	if(fromDocNet.equals("userInput"))
        	{
	        	fromDoc = stdIn.readLine();
	        	
			    if (fromDoc!= null)
			    {   
	                out.println(fromDoc);
			    }
        	}
        	
        	//kills client connection to server
        	else if(fromDocNet.equals("killConnection"))
        	{
        		out.close();
                in.close();
                stdIn.close();
                dnSocket.close();
                System.out.println("Goodbye");
                break;
        	}
        	
           }
        
        
    }
}