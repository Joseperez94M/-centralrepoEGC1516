
public class Token{
	
	public Token(){
		
	}
	
	private static final Integer[] tokenAuxList = {120338, 127508, 219240, 231958, 
			264907, 301200, 301415, 318851, 328237, 333555, 366710, 376217, 382413, 
			406463, 409921, 436780, 458841, 461513, 530897, 589116, 590265, 590815, 
			593252, 656720, 746976, 830375, 865247, 869061, 885540, 907197, 909246, 
			961864, 976931, 982612};
	
	
	/**
	 * Esta función hace el cálculo del Token necesario para el acceso al subsistema
	 * de verificación. Este token es el que se almacenará en la base de datos
	 * posteriormente. Este token se calcula de la siguiente forma:
	 * 1 - El id de la votación pasa a binario.
	 * 2 - Recorremos el número binario y multiplicamos del final hacia el 
	 *     principio con el índice correspondiente de la lista de números
	 *     estática.
	 * 3 - Vamos sumando el resultado de la multiplicación a lo que ya tuvieramos.
	 * 4 - Finalmente, multiplicamos por dos primos para aumentar el tamaño.
	 * @param votationId. Corresponde al id de la votación.
	 * @return token. Número entero que corresponde con el token generado.
	 */
	public Integer calculateToken(Integer votationId){
		
		Integer token = 0;
		
		checkId(votationId);
		
		String binaryInteger = Integer.toBinaryString(votationId);
		char[] numberByNumber = binaryInteger.toCharArray();
		
		
		for(int i=numberByNumber.length-1; 0 < i; i--){
			Integer digit = new Integer(numberByNumber[i]);
			for (int j=tokenAuxList.length-1; 0 < j; j--){
				if(digit > 0){
					token += digit*tokenAuxList[j];
				}
			}
		}
		
		return token*17*31;
		
	}

	/**
	 * Comprueba que el id de la votación es menor a un número de 9 cifras.
	 * @param votationId. Corresponde al id de la votación.
	 */
	private static void checkId(Integer votationId) {
		assert votationId < 999999998;
		
	}
	
}