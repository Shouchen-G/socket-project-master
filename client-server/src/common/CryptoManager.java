package common;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;

import javax.crypto.Cipher;

public class CryptoManager {
	
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";   
	
	public static String encrypt(String inputStr)
	{  
		        // convert inputStr into lower case   
		        inputStr = inputStr.toLowerCase();   
		          
		        // encryptStr to store encrypted data   
		        String encryptStr = "";   
		          
		        // use for loop for traversing each character of the input string   
		        for (int i = 0; i < inputStr.length(); i++)   
		        {   
		            // get position of each character of inputStr in ALPHABET   
		            int pos = ALPHABET.indexOf(inputStr.charAt(i));   
		              
		            // get encrypted char for each char of inputStr   
		            int encryptPos = (3 + pos) % 26;   
		            char encryptChar = ALPHABET.charAt(encryptPos);   
		              
		            // add encrypted char to encrypted string   
		            encryptStr += encryptChar;   
		        }   
		          
		        // return encrypted string   
		        return encryptStr;   
		   
	}
	
	
	public static String decrypt(String inputStr)
	{
		        // convert inputStr into lower case   
		        inputStr = inputStr.toLowerCase();   
		          
		        // decryptStr to store decrypted data   
		        String decryptStr = "";   
		          
		        // use for loop for traversing each character of the input string   
		        for (int i = 0; i < inputStr.length(); i++)   
		        {   
		              
		            // get position of each character of inputStr in ALPHABET   
		            int pos = ALPHABET.indexOf(inputStr.charAt(i));   
		              
		            // get decrypted char for each char of inputStr   
		            int decryptPos = (pos - 3) % 26;   
		              
		            // if decryptPos is negative   
		            if (decryptPos < 0){   
		                decryptPos = ALPHABET.length() + decryptPos;   
		            }   
		            char decryptChar = ALPHABET.charAt(decryptPos);   
		              
		            // add decrypted char to decrypted string   
		            decryptStr += decryptChar;   
		        }   
		        // return decrypted string   
		        return decryptStr;   
		      
	}

	
	public static void main(String [] args) throws Exception{
//		String test = "Hhello";
//		System.out.println(encrypt(test));
//		System.out.println(decrypt("ccc"));
		
		
	
//			   //Creating a Signature object
//		      Signature sign = Signature.getInstance("SHA256withRSA");
//		      
//		      //Creating KeyPair generator object
//		      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
//		      
//		      //Initializing the key pair generator
//		      keyPairGen.initialize(2048);
//		      
//		      //Generate the pair of keys
//		      KeyPair pair = keyPairGen.generateKeyPair();   
//		      
//		      //Getting the public key from the key pair
//		      PublicKey publicKey = pair.getPublic();  
//
//		      //Creating a Cipher object
//		      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//
//		      //Initializing a Cipher object
//		      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//			  
//		      //Add data to the cipher
//		      byte[] input = "Welcome to Tutorialspoint".getBytes();	  
//		      cipher.update(input);
//			  
//		      //encrypting the data
//		      byte[] cipherText = cipher.doFinal();	 
//		      System.out.println( new String(cipherText, "UTF8"));
//
//		      //Initializing the same cipher for decryption
//		      cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
//		      
//		      //Decrypting the text
//		      byte[] decipheredText = cipher.doFinal(cipherText);
//		      System.out.println(new String(decipheredText));
		   
	}
}

//reference:https://www.javatpoint.com/caesar-cipher-program-in-java#:~:text=It%20is%20one%20of%20the,become%20Z%2C%20and%20so%20on.

//(https://www.tutorialspoint.com/java_cryptography/java_cryptography_decrypting_data.htm)
