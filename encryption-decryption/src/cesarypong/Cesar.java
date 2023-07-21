/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cesarypong;

/**
 *
 * @author HP
 */
//import java.util.Scanner;
public class Cesar {
    
    private int privatekey;

    public void setPrivatekey(int privatekey) {
        this.privatekey = privatekey;
    }
    private String alphabet;
    
    public Cesar(){
        alphabet = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-";
        privatekey = 5;
    }
    
    private int module(int a, int b){
        if(a >= 0)
            return a - (a / b) * b;
        else
            return a - (a / b - 1) * b;
    }
    
    public String encrypt(String text){
        int index = 0;
        String encryptedtext = "";
        for(int i = 0; i < text.length(); i++){
            index = alphabet.indexOf(text.charAt(i));
            encryptedtext += alphabet.charAt(module(index + privatekey, alphabet.length()));
        }
        return encryptedtext;
    }

    public int getPrivatekey() {
        return privatekey;
    }
    
    public String decrypt(String text){
        int index = 0;
        String decryptedtext = "";
        for(int i = 0; i < text.length(); i++){
            index = alphabet.indexOf(text.charAt(i));
            decryptedtext += alphabet.charAt(module(index - privatekey, alphabet.length()));
        }
        return decryptedtext;
    }
}
