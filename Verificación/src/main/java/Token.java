package main.java;

public class Token{

	
	private static final Integer[] tokenAuxList = {120338, 127508, 219240, 231958, 
			264907, 301200, 301415, 318851, 328237, 333555, 366710, 376217, 382413, 
			406463, 409921, 436780, 458841, 461513, 530897, 589116, 590265, 590815, 
			593252, 656720, 746976, 830375, 865247, 869061, 885540, 907197, 909246, 
			961864, 976931, 982612};
	
	
	/**
	 * Esta funci�n hace el c�lculo del Token necesario para el acceso al subsistema
	 * de verificaci�n. Este token es el que se almacenar� en la base de datos
	 * posteriormente. Este token se calcula de la siguiente forma:
	 * 1 - El id de la votaci�n pasa a binario.
	 * 2 - Recorremos el n�mero binario y multiplicamos del final hacia el 
	 *     principio con el �ndice correspondiente de la lista de n�meros
	 *     est�tica.
	 * 3 - Vamos sumando el resultado de la multiplicaci�n a lo que ya tuvieramos.
	 * 4 - Finalmente, multiplicamos por dos primos para aumentar el tama�o.
	 * @param votationId. Corresponde al id de la votaci�n.
	 * @return token. N�mero entero que corresponde con el token generado.
	 */
	private static Integer calculateToken(Integer votationId){
		
		Integer token = 0;
		
		checkId(votationId);
		
		String binaryInteger = Integer.toBinaryString(votationId);
		char[] numberByNumber = binaryInteger.toCharArray();
		
		int j = 0;
		for(int i=numberByNumber.length-1; 0 <= i; i--){
			String binDigit = Character.toString(numberByNumber[i]);
			Integer digit = new Integer(binDigit);
			if(digit > 0){
				token += digit*tokenAuxList[tokenAuxList.length-1-j];
				
			}
			j++;
		}
		
		return token*17;
		
	}

	/**
	 * Comprueba que el id de la votaci�n es menor a un n�mero de 9 cifras.
	 * @param votationId. Corresponde al id de la votaci�n.
	 */
	private static void checkId(Integer votationId) {
		assert votationId <= 999999998;
		
	}

	/**
	 * Esta funci�n crea el token con la funci�n definida anteriormente y la 
	 * env�a al m�todo de escritura del token en la base de datos.
	 * @param votationId. Corresponde al id de la votaci�n.
	 * @return Resultado booleano correspondiente a la escritura en la base de datos.
	 */
	public static boolean createToken(Integer votationId) {
		return RemoteDataBaseManager.sendGeneratedToken(votationId, 
				calculateToken(votationId));
	}

	/**
	 * Esta funci�n comprueba que el token introducido de acceso al subsistema
	 * corresponde con el token que deber�a generarse con el m�todo de creaci�n
	 * de token. Adem�s, comprueba que el token introducido corresponde con el
	 * que est� guardado en la base de datos para el mismo id de votaci�n.
	 * @param votationId. Corresponde al id de la votaci�n.
	 * @param token. Corresponde al token de acceso al subsistema.
	 * @return result. Booleano que indica si el token introducido es correcto.
	 */
	public static boolean checkTokenDb(Integer votationId, Integer token) {
		boolean result = false;
		
		if(calculateToken(votationId).equals(token)){
			Integer storedToken = RemoteDataBaseManager.getAccessToken(votationId);
			result = token.equals(storedToken);
		}
		
		return result;
	}
	
	/**
	 * Esta funci�n comprueba que el token introducido de acceso al subsistema
	 * corresponde con el token que deber�a generarse con el m�todo de creaci�n
	 * de token. Sirve para cuando se generan las claves y a�n no se tiene el token.
	 * @param votationId. Corresponde al id de la votaci�n.
	 * @param token. Corresponde al token de acceso al subsistema.
	 * @return result. Booleano que indica si el token introducido es correcto.
	 */
	public static boolean checkToken(Integer votationId, Integer token) {
		boolean result = false;
		
		if(calculateToken(votationId).equals(token)){
			result = true;
		}
		
		return result;
	}
	
}
