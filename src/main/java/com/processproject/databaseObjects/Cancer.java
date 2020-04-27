package com.processproject.databaseObjects;

/**
 * @author s164166
 */
public class Cancer
{
	public Cancer(String cancerName, int ID)
	{
		this.CANCERNAME = cancerName;
		this.ID = ID;
	}
	
	public String getCancerName()
	{
		return CANCERNAME;
	}
	
	public int getID()
	{
		return ID;
	}
	
	String CANCERNAME;
	int ID;

}
