package main.java;

import java.io.UnsupportedEncodingException;

import javax.crypto.BadPaddingException;

public interface Authority {

	//Recibe la id de la votaci�n, crea las claves y las guarda en BD.
		boolean postKey(String id, Integer token);
		
		//Recibe la id de la votaci�n y devuelve su clave p�blica para poder cifrar.
		String getPublicKey(String id, Integer token);
		
		//Recibe la id de la votaci�n y devuelve su clave privada para poder descifrar.
		String getPrivateKey(String id, Integer token);
		
		//Recibe un voto cifrado y un id de la votaci�n, y comprueba si ese voto ha sido alterado.
		boolean checkVote(byte[] votoCifrado, String id, Integer token);
		
		//Encripta el texto con la clave p�blica de la votaci�n cuya id se pasa como par�metro.
		byte[] encrypt(String idVote,String textToEncypt, Integer token);
		
		//Desencripta el texto con la clave privada de la votaci�n cuya id se pasa como par�metro.	
		String decrypt(String idVote,byte[] cipherText, Integer token) throws BadPaddingException, UnsupportedEncodingException;
		
		//El voto recibido lo corta en bloques de 31 caracteres
		String[] cutVote(String votoEnClaro);

		//El voto cifrado recibido lo corta en bloques para su posterior recorrido en el m�todo de descifrar
		String[] cutCifVote(String votoEnClaro);
	
}
