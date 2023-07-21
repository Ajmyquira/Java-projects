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
public class ZigZag {
    
    private int privatekey;

    public void setPrivatekey(int privatekey) {
        this.privatekey = privatekey;
    }
    
    public ZigZag(){
        privatekey = 1;
    }

    public int getPrivatekey() {
        return privatekey;
    }
    
    public String encrypt(String text){
        String encryptedtext = "";
        int space1;
        int index = 0;
        if(privatekey == 1){
            space1 = 0;
            while(index < text.length()){
                encryptedtext += text.charAt(index);
                index = index + space1 + 1;
            }
        }else{
            space1 = 2 * privatekey - 3;
            int space2 = 0;
            while(index < text.length()){
                encryptedtext += text.charAt(index);
                index = index + space1 + 1;
            }
            space1 -= 2;
            space2 = 1;
            for(int i = 1; i < privatekey - 1; i++){
                index = i;
                int aux = 0;
                int temp1 = space1;
                int temp2 = space2;
                while(index < text.length()){
                    encryptedtext += text.charAt(index);
                    index = index + temp1 + 1;
                    aux = temp1;
                    temp1 = temp2;
                    temp2 = aux;
                }
                space1 -= 2;
                space2 += 2;
            }
            index = privatekey - 1;
            while(index < text.length()){
                encryptedtext += text.charAt(index);
                index = index + (2 * privatekey - 3) + 1;
            }
        }
        return encryptedtext;
    }
    
    public String decrypt(String text){
        String decryptedtext = text;
        int space1;
        int index = 0;
        int index2 = 0;
        int space2 = 0;
        if(privatekey == 1){
            space1 = 0;
            while(index < text.length()){
                char[] temp = decryptedtext.toCharArray();
                temp[index] = text.charAt(index2);
                decryptedtext = String.valueOf(temp);
                index = index + space1 + 1;
                index2++;
            }
        }else{
            space1 = 2 * privatekey - 3;
            while(index < text.length()){
                char[] temp = decryptedtext.toCharArray();
                temp[index] = text.charAt(index2);
                decryptedtext = String.valueOf(temp);
                index = index + space1 + 1;
                index2++;
            }
            space1 -= 2;
            space2 = 1;
            for(int i = 1; i < privatekey - 1; i++){
                index = i;
                int aux = 0;
                int temp1 = space1;
                int temp2 = space2;
                while(index < text.length()){
                    char[] temp = decryptedtext.toCharArray();
                    temp[index] = text.charAt(index2);
                    decryptedtext = String.valueOf(temp);
                    index = index + temp1 + 1;
                    index2++;
                    aux = temp1;
                    temp1 = temp2;
                    temp2 = aux;
                }
                space1 -= 2;
                space2 += 2;
            }
            index = privatekey - 1;
            while(index < text.length()){
                char[] temp = decryptedtext.toCharArray();
                temp[index] = text.charAt(index2);
                decryptedtext = String.valueOf(temp);
                index = index + (2 * privatekey - 3) + 1;
                index2++;
            }
        }
        return decryptedtext;
    }
}
