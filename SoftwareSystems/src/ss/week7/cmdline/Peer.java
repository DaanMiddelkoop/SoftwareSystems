package ss.week7.cmdline;
 
import java.io.BufferedReader; 
import java.io.BufferedWriter; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter; 
import java.net.InetAddress; 
import java.net.Socket; 
import java.net.UnknownHostException; 
 
/**
 * Peer for a simple client-server application 
 * @author  Theo Ruys 
 * @version 2005.02.21 
 */ 
public class Peer implements Runnable { 
 public static final String EXIT = "exit"; 
 
 protected String name; 
 protected Socket sock; 
 protected BufferedReader in; 
 protected BufferedWriter out; 
 
 
 /*@
    requires (nameArg != null) && (sockArg != null); 
  */ 
 /**
  * Constructor. creates a peer object based in the given parameters. 
  * @param   nameArg name of the Peer-proces 
  * @param   sockArg Socket of the Peer-proces 
  */ 
 public Peer(String nameArg, Socket sockArg) throws IOException { 
  sock = sockArg; 
  name = nameArg; 
  out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())); 
  in = new BufferedReader(new InputStreamReader(sock.getInputStream())); 
 } 
 
 /**
  * Reads strings of the stream of the socket-connection and writes the characters to the default output 
  */ 
 public void run() { 
  String line = null; 
  try { 
   while ((line = in.readLine()) != null) { 
    System.out.println(line); 
   } 
  } catch (IOException e) { 
   System.out.println("Something went wrong reading from socket"); 
   shutDown(); 
  } 
 } 
 
 
 /**
  * Reads a string from the console and sends this string over the socket-connection to the Peer proces. On Peer.EXIT the method ends 
  */ 
 public void handleTerminalInput() { 
  String line = null; 
  try { 
   send("Init"); 
  } catch (IOException e1) { 
   // TODO Auto-generated catch block 
   e1.printStackTrace(); 
  } 
  while ((line = readString(" > ")) != null) { 
   if (line.equals(Peer.EXIT)) { 
    shutDown(); 
    return; 
   } 
   try { 
    send(line); 
   } catch (IOException e) { 
    System.out.println("Could not write to outputstream, pipe broken!"); 
    shutDown(); 
   } 
  } 
 } 
  
 public void send(String msg) throws IOException { 
  out.write(name + ": " + msg); 
  out.newLine(); 
  out.flush(); 
 } 
 
 /**
  * Closes the connection, the sockets will be terminated 
  */ 
 public void shutDown() { 
  try { 
   sock.close(); 
  } catch (IOException e) {} 
 } 
 
 /**  returns name of the peer object*/ 
 public String getName() { 
  return name; 
 } 
 
 /** read a line from the default input */ 
 static public String readString(String tekst) { 
  System.out.print(tekst); 
  String antw = null; 
  try { 
   BufferedReader in = new BufferedReader(new InputStreamReader( 
     System.in)); 
   antw = in.readLine(); 
  } catch (IOException e) { 
  } 
 
  return (antw == null) ? "" : antw; 
 } 
  
 public static InetAddress getIp(String host) { 
  InetAddress addr = null; 
  try { 
   addr = InetAddress.getByName(host); 
  } catch (UnknownHostException e) { 
   return null; 
  } 
  return addr; 
 } 
  
 public static int getPort(String portstr) { 
  int port = 0; 
  try { 
   port = Integer.parseInt(portstr); 
  } catch (NumberFormatException e) { 
   return 0; 
  } 
  return port; 
 } 
}
