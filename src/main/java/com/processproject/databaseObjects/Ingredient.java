package com.processproject.databaseObjects;

/**
 * @author s164166
 */
public class Ingredient
{
	String INGREDIENTNAME;
	int ID;
	
	public Ingredient(String name, int ID)
	{
		INGREDIENTNAME = name;
		this.ID = ID;
	}
	
	public String getName()
	{
		return INGREDIENTNAME;
	}
	
	public int getID()
	{
		return ID;
	}
}
