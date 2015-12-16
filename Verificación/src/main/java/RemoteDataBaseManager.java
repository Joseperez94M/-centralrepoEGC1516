package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RemoteDataBaseManager {
	
	
	/**
	 * Esta funci�n provee el conector con la base de datos
	 * @return conn. Conector de conexi�n con la base de datos.
	 */
	private static Connection getDatabaseConnection(){
		String USER = "jeparcac_egc";
	    String PASS = "kqPTE8dLz3GVtks";  
	    String DB_URL = "jdbc:mysql://egc.jeparca.com:3306/jeparcac_egc";
	    
	    Connection conn = null;
	    
	    try{
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    }catch (Exception e){
	    	e.printStackTrace();
	    }
	    
	    return conn;	    
	    
	}
	
	/**
	 * Funci�n que almacena en la base de datos remota un par de claves de cifrado RSA
	 * asociadas a una votaci�n
	 * @param id La ide de la votaci�n.
	 * @param publicKey La clave p�blica de cifrado asociada a la votaci�n
	 * @param privateKey La clave privada de cifrado asociada a la votaci�n
	 * @return una variable booleana que ser� cierta si el guardado se realiza con �xito.
	 */
	public boolean postKeys(String id, String publicKey,String privateKey ){
		boolean success = false;
		Connection conn = null;
		Statement stmt = null;
	    
		try {	
		
		conn = getDatabaseConnection();
		
		stmt = conn.createStatement();
		
		String sql = "INSERT INTO keysvotes (idvotation, publicKey, privateKey)" +
                "VALUES (?, ?, ?)";
		
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, new Integer(id));
        preparedStatement.setString(2, publicKey);
        preparedStatement.setString(3, privateKey);
        int r = preparedStatement.executeUpdate(); 
        
        if(r == 1){
        	success = true;
        }else if(r == 0){
        	success = false;
        }
	    
		} catch(SQLException se) {
	        se.printStackTrace();
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if(stmt != null)
	                conn.close();
	        } catch(SQLException se) {
	        }
	        try {
	            if(conn != null)
	                conn.close();
	        } catch(SQLException se) {
	            se.printStackTrace();
	        }
	    }
		
		return success;
	}
	
	/**
	 * Funci�n usada para obtener la clave de cifrado AES asociada a una votaci�n.
	 * @param id La id de la votaci�n cuya clave de cifrado AES queremos conocer
	 * @return La clave de cifrado AES asociada a una votaci�n
	 */
	public String getSecretKey(String id){
		String res = "";
		Connection conn = null;
		
	    try {			
		  conn = getDatabaseConnection();
	      Statement select = conn.createStatement();
	      ResultSet result = select
	          .executeQuery("SELECT privateKey FROM keysvotes where idvotation="+id);
	      
	      result.next();
	      res = result.getString(1);
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      if (conn != null) {
	        try {
	          conn.close();
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	      }
	    }
	    
	    return res;
	}
	
	/**
	 * Funci�n usada para obtener la clave p�blica RSA asociada a una votaci�n.
	 * @param id La id de la votaci�n cuya clave p�blica RSA queremos conocer
	 * @return La clave p�blica asociada a una votaci�n
	 */
	public String getPublicKey(String id){
		String res = "";
		Connection conn = null;
	    
	    try {			
		  conn = getDatabaseConnection();
	      Statement select = conn.createStatement();
	      ResultSet result = select
	          .executeQuery("SELECT publicKey FROM keysvotes where idvotation="+id);
	      
	      result.next();
	      res = result.getString(1);
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      if (conn != null) {
	        try {
	          conn.close();
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	      }
	    }
	    
	    return res;
	}

	/**
	 * Esta funci�n env�a a la base de datos el token generado para un id 
	 * de votaci�n correspondiente.
	 * @param votationId. Corresponde al id de la votaci�n.
	 * @param token. Corresponde al token que se va a guardar en la base de datos
	 * @return success. Booleano que indica si se ha ejecutado la operaci�n SQL con �xito.
	 */
	public static boolean sendGeneratedToken(Integer votationId, Integer token){
		boolean success = false;
		Connection conn = null;
		Statement stmt = null;
		
		try {	
		
		conn = getDatabaseConnection();
		
		stmt = conn.createStatement();
		
		String sql = "INSERT INTO token (idvotation, accesstoken)" +
                "VALUES (?, ?)";
		
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, votationId);
        preparedStatement.setInt(2, token);
        int r = preparedStatement.executeUpdate(); 
        
        if(r == 1){
        	success = true;
        }else if(r == 0){
        	success = false;
        }
	    
		} catch(SQLException se) {
	        System.out.println("Ooops! We have detected an error. Please, check that your votationID hasn't a generated token.");
	    }catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if(stmt != null)
	                conn.close();
	        } catch(SQLException se) {
	        }
	        try {
	            if(conn != null)
	                conn.close();
	        } catch(SQLException se) {
	            se.printStackTrace();
	        }
	    }
		
		return success;
	}

	/**
	 * Esta funci�n extrae de la base de datos un token que se ha guardado
	 * anteriormente, dado un id de votaci�n
	 * @param votationId. Corresponde al id de la votaci�n.
	 * @return result. N�mero entero que corresponde con el token extraido.
	 */
	public static Integer getAccessToken(Integer votationId){
		Integer result = -1;
		Connection conn = null;
		Statement stmt = null;
	    
		try {	
		
		conn = getDatabaseConnection();
		
		stmt = conn.createStatement();
		
		String sql = "SELECT * FROM token WHERE idvotation = ?";
		
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, votationId);
        ResultSet r = preparedStatement.executeQuery();
        
        while(r.next()){
        	result = r.getInt("accesstoken");
        }
        
		} catch(SQLException se) {
	        se.printStackTrace();
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if(stmt != null)
	                conn.close();
	        } catch(SQLException se) {
	        }
	        try {
	            if(conn != null)
	                conn.close();
	        } catch(SQLException se) {
	            se.printStackTrace();
	        }
	    }
		
		return result;
	}
	
}
