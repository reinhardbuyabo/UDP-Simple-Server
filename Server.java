/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytrial.udpserverclientdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author reinh
 */
public class Server {
    DatagramSocket socket = null;
    byte[] receiverBuffer = null;
    DatagramPacket receivePacket = null;
    
    public static void main(String[] args) {
        Server server = new Server();
        server.initializeVariable();
        server.connecting();
    }
    
    private void initializeVariable(){
        try {
            socket = new DatagramSocket(Constants.PORT);
            receiverBuffer = new byte[Constants.BUFFER_SIZE];
        } catch (SocketException ex) {
            log("InitializeVariable : "  + ex.toString());
        }
    }
    
    private String receiveData() {
        String line = "";
        try {
            receivePacket = new DatagramPacket(receiverBuffer, receiverBuffer.length);
            socket.receive(receivePacket);
            line = new String(receivePacket.getData(), 0, receivePacket.getLength());
        } catch (IOException ex) {
            log("receiveData : " + ex.toString());
        }
        return line;
    }
    
    private void connecting(){
        while(true){
            String data = receiveData();
            log("Client : " + data);
            
            if(data.equals(Constants.STOP)){
                log("Client says Bye ... Exiting");
                break;
            }
            
            receiverBuffer = new byte[Constants.BUFFER_SIZE];
        }
    }
    
    private void log(String message) {
        System.out.println(message);
    }
}
