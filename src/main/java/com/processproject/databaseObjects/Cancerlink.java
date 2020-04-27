package com.processproject.databaseObjects;

/**
 * @author s164166
 */
public class Cancerlink
{
	public Cancerlink(int INGREDIENTID, int CANCERID, int AMM)
	{
		this.INGREDIENTID = INGREDIENTID;
		this.CANCERID = CANCERID;
		this.AMM = AMM;
	}
	
	public int getIngredientID()
	{
		return INGREDIENTID;
	}
	public int getCancerID()
	{
		return CANCERID;
	}
	public int getAmm()
	{
		return AMM;
	}
	int INGREDIENTID;
	int CANCERID;
	int AMM;
}
