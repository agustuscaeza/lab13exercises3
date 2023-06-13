package lab13exercises3;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerLengthText
{
	
    public void startServer() 
    {
        try {
            // 1.  bind serversocket to a port
            int PortNo = 4228;
            ServerSocket serverSocket = new ServerSocket(PortNo);
           
            System.out.println("Waiting for request...");

            // 2. Accept client requet for connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Successfully Connected with client");

            // 3. Receive request from the client
            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffered = new byte[1024]; // Buffer size for reading data from the client
            int bytesRead = inputStream.read(buffered);
            String text = new String(buffered, 0, bytesRead);
            System.out.println("Text received from client: " + text);

            // 4. Process count the number of words
            int wordCount = countWords(text);

            // 5. Send response back to the client
            OutputStream outputStream = clientSocket.getOutputStream();
            String response = "Word counted : " + wordCount;
            byte[] responseBytes = response.getBytes();
            outputStream.write(responseBytes);
            outputStream.flush();
            System.out.println("Response sent to client: " + response);

            // 6. Close the socket
            clientSocket.close();
            serverSocket.close();
            System.out.println("Server closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int countWords(String text)
    {
        Scanner scanner = new Scanner(text);
        int wordCount = 0;
        while (scanner.hasNext()) 
        {
            scanner.next();
            wordCount++;
        }
        scanner.close();
        return wordCount;
    }
    
    public static void main(String[] args) 
    {
    	ServerLengthText test = new ServerLengthText();
        test.startServer();
    }
        
}