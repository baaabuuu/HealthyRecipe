package com.processproject.database;

/**
 * 
 * @author s164166
 *
 */
public class SQLStatements
{
	/**
	 * Given an ingredient Name, return an ingredient ID back.
	 * @return SQL Statement
	 */
	public static String getIngredientID()
	{
		return "SELECT * FROM INGREDIENT WHERE INGREDIENTNAME = (?)";
	}
	
	/**
	 * Given an Ingredient ID and an amount greater than 0, return a resultset of associated IDs
	 * @return SQL Statement
	 */
	public static String getCancerID()
	{
		return "SELECT * FROM CANCERLINK WHERE INGREDIENTID = (?) AND AMM >= (?)";
	}
	
	/**
	 * Given a cancerID return a cancer if it exists
	 * @return SQL statement
	 */
	public static String getCancer()
	{
		return "SELECT * FROM CANCER WHERE CANCERID = (?)";
	}
	

}
