package ss.week7.cmdline;
 
import java.io.BufferedReader; 
import java.io.BufferedWriter; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter; 
import java.net.Socket; 
 
public class ClientHandler extends Thread { 
 
 private Socket socket; 
 private Server server; 
 private BufferedWriter write; 
 private BufferedReader read; 
  
 public ClientHandler(Socket sock, Server server) { 
  this.server = server; 
  this.socket = sock; 
  try { 
   read = new BufferedReader(new InputStreamReader(sock.getInputStream())); 
   write = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())); 
  } catch (IOException e) { 
   System.out.println("Stuff went wrong. Shutting down."); 
  } 
   
 } 
  
 @Override 
 public void run() { 
  String line = null; 
  try { 
   while ((line = read.readLine()) != null) { 
    server.broadcast(line); 
   } 
  } catch (IOException e) { 
   System.out.println("Something went wrong reading from socket"); 
   shutdown(); 
  } 
 } 
  
 public void send(String msg) { 
  try { 
   write.write(msg); 
   write.newLine(); 
   write.flush(); 
  } catch (Exception e) { 
   System.out.println("Error sending"); 
  } 
 } 
  
 public void shutdown() { 
  try { 
   socket.close(); 
   server.removeClient(this); 
    
  } catch (IOException e) { 
   System.out.println("Error shutting down"); 
  } 
 } 
  
}
