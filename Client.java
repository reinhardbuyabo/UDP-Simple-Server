/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytrial.udpserverclientdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    Scanner scan = null;            // Scanner for reading user input
    DatagramSocket socket = null;   // DatagramSocket for sending data
    byte[] buffer = null;           // Buffer for storing data
    
    public static void main(String[] args) {
        Client client = new Client();
        client.initializeVariable();
        client.connecting();
    }
    
    // Initializes the necessary variables for the client
    private void initializeVariable() {
        try {
            scan = new Scanner(System.in);  // Create a scanner for reading user input
            socket = new DatagramSocket();  // Create a DatagramSocket for sending data
        } catch (IOException ex) {
            log("InitializeVariable : " + ex.toString());
        }
    }
    
    // Reads a line of text from the keyboard
    private String readFromKeyboard() {
        String line = scan.nextLine();
        return line;
    }
    
    // Sends a message to the server
    private void send(String message) {
        try {
            InetAddress ip = InetAddress.getLocalHost();  // Get the local IP address
            
            buffer = message.getBytes();  // Convert the message to bytes
            
            DatagramPacket packetSend = new DatagramPacket(buffer, buffer.length, ip, Constants.PORT);  // Create a DatagramPacket for sending
            
            socket.send(packetSend);  // Send the packet to the server
        } catch (UnknownHostException ex) {
            log("send : " + ex.toString());
        } catch (IOException ex) {
            log("send : " + ex.toString());
        }
    }
    
    // Sends messages to the server based on user input until "STOP" is entered
    private void connecting() {
        while (true) {
            String line = readFromKeyboard();  // Read user input
            send(line);  // Send the message to the server
            
            if (line.equals(Constants.STOP)) {
                break;  // Exit the loop if the user enters "STOP"
            }
        }
    }
    
    // Logs the given message to the console
    private void log(String message) {
        System.out.println(message);
    }
}
