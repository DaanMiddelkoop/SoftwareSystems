package ss.week7.cmdline; 
 
import java.io.IOException; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.ArrayList; 
 
/**
 * Server.  
 * @author  Theo Ruys 
 * @version 2005.02.21 
 */ 
public class Server { 
 private static final String USAGE = "Usage: " + Server.class.getName() + " <name> <port>"; 
 
 private ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>(); 
 private ServerSocket serv; 
  
 public Server(ServerSocket serv) { 
  this.serv = serv; 
 } 
  
 /** Start een Server-applicatie op.   * @throws IOException */ 
 public static void main(String[] args) throws IOException { 
   
  int port = 0; 
  if (args.length <= 1 || (port = Peer.getPort(args[1])) == 0) { 
   System.out.println("Could not read port"); 
   System.out.println(Server.USAGE); 
   System.exit(1); 
  } 
   
  ServerSocket serv = null; 
  try { 
   serv = new ServerSocket(port); 
  } catch (IOException e1) { 
   System.out.println("Cannot open socket"); 
   System.exit(1); 
  } 
   
  new Server(serv).acceptor();  
 } 
  
 public void acceptor() throws IOException { 
  while (true) { 
   Socket sock = serv.accept(); 
   System.out.println("New client connected!"); 
   ClientHandler client = new ClientHandler(sock, this); 
   clients.add(client); 
   client.start(); 
  } 
 } 
  
 public synchronized void broadcast(String msg) { 
  System.out.println(msg); 
  for (ClientHandler c : clients) { 
   c.send(msg); 
  } 
 } 
  
 public void removeClient(ClientHandler c) { 
  clients.remove(c); 
 } 
 
}