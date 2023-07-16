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

/**
 *
 * @author reinh
 */
public class Client {
    Scanner scan = null;
    DatagramSocket socket = null;
    byte[] buffer = null;
    
    public static void main(String[] args) {
        Client client = new Client();
        client.initializeVariable();
        client.connecting();
    }
    
    private void initializeVariable(){
        try {
            scan = new Scanner(System.in);
            socket = new DatagramSocket();
        } catch (IOException ex) {
            log("InitializeVariable : " + ex.toString());
        }
    }
    
    private String readFromKeyboard() {
        String line = scan.nextLine();
        
        return line;
    }
    
    private void send(String message){
        try {
            InetAddress ip = InetAddress.getLocalHost();
            
            buffer = message.getBytes();
            
            DatagramPacket packetSend = new DatagramPacket(buffer, buffer.length, ip, Constants.PORT);
            socket.send(packetSend);
        } catch (UnknownHostException ex) {
            log("send : " + ex.toString());
        } catch (IOException ex) {
            log("send : " + ex.toString());
        }
    }
    
    private void connecting(){
        while(true){
            String line = readFromKeyboard();
            send(line);
            
            if(line.equals(Constants.STOP)){
                break;
            }
        }
    }
    
    private void log(String message) {
        System.out.println(message);
    }
}
