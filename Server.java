/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * This code represents a UDP server that receives data from clients.
 * The server listens on a specific port, receives packets, and logs the received data.
 */

package com.mytrial.udpserverclientdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    DatagramSocket socket = null;            // DatagramSocket for receiving data
    byte[] receiverBuffer = null;            // Buffer for receiving data
    DatagramPacket receivePacket = null;     // DatagramPacket for receiving packets
    
    public static void main(String[] args) {
        Server server = new Server();
        server.initializeVariable();
        server.connecting();
    }
    
    // Initializes the necessary variables for the server
    private void initializeVariable() {
        try {
            socket = new DatagramSocket(Constants.PORT);   // Create a DatagramSocket to listen on a specific port
            receiverBuffer = new byte[Constants.BUFFER_SIZE];  // Create a buffer to store received data
        } catch (SocketException ex) {
            log("InitializeVariable : "  + ex.toString());
        }
    }
    
    // Receives data from the socket and returns it as a string
    private String receiveData() {
        String line = "";
        try {
            receivePacket = new DatagramPacket(receiverBuffer, receiverBuffer.length);  // Create a DatagramPacket for receiving data
            socket.receive(receivePacket);  // Receive data into the DatagramPacket
            line = new String(receivePacket.getData(), 0, receivePacket.getLength());   // Convert the received data to a string
        } catch (IOException ex) {
            log("receiveData : " + ex.toString());
        }
        return line;
    }
    
    // Receives data indefinitely, logs it, and exits if the received data is "STOP"
    private void connecting() {
        while(true) {
            String data = receiveData();  // Receive data from the client
            log("Client : " + data);     // Log the received data
            
            if (data.equals(Constants.STOP)) {
                log("Client says Bye ... Exiting");
                break;  // Exit the loop if the received data is "STOP"
            }
            
            receiverBuffer = new byte[Constants.BUFFER_SIZE];  // Reset the buffer for receiving more data
        }
    }
    
    // Logs the given message to the console
    private void log(String message) {
        System.out.println(message);
    }
}
