package myPackage;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class PasswordSecurity {
	

	private static String seed = "mySeed";

	public static String encrypt(String pass)
	{
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(seed);
		String encrypted= encryptor.encrypt(pass);
		
		return encrypted;
	}
	

	public static String decrypt(String encryptedPass)
	{
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(seed);

		String decrypted = encryptor.decrypt(encryptedPass);
		

		
		System.out.println(decrypted);
		
		return decrypted;
	}

}