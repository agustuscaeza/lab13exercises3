package lab13exercises3;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientLengthText {
    
    public void startClient() throws IOException {
    	Socket socket=null;
        try {
        	
            //1. Connect to the server at localhost, port 4228
            socket = new Socket(InetAddress.getLocalHost(),4228);
            
        	//2. Send request to the server
        	OutputStream outputStream = socket.getOutputStream();
        	String text = "Lab 13 exercises 3";
        	byte[] textBytes = text.getBytes();
        	outputStream.write(textBytes);
        	outputStream.flush();
        	
        	//3. Accept response from the server
        	InputStream inputStream = socket.getInputStream();
        	byte[] buffered = new byte[1024]; // Buffer size for reading data from the server
            int bytesRead;
            StringBuilder responseBuilder = new StringBuilder();
            while ((bytesRead = inputStream.read(buffered)) != -1) {
                  String responseChunk = new String(buffered, 0, bytesRead);
                  responseBuilder.append(responseChunk);
              }
              String response = responseBuilder.toString();
        	
              //4. Response the process
        	System.out.println("Server response: "+response);
        }catch (IOException e){
        	e.printStackTrace();
        }
        	finally
        {
        		 if (socket != null)
        		 {
                     try 
                     {
                         socket.close();
                     } catch (IOException e) 
                     {
                         e.printStackTrace();
                     }
        		 }
        }
    }
    

    	public static void main(String[] args) {
    		ClientLengthText client = new ClientLengthText() ;
    		
    		try {
    				client.startClient();
            	}
    		
    		catch (IOException e)
    		{
    			e.printStackTrace();
    		}
    				   		
    		}
}