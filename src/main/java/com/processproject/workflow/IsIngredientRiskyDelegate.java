package com.processproject.workflow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.processproject.database.DBConnector;
import com.processproject.database.SQLStatements;
import com.processproject.databaseObjects.Cancer;
import com.processproject.databaseObjects.Cancerlink;
import com.processproject.databaseObjects.Ingredient;

public class IsIngredientRiskyDelegate  implements JavaDelegate
{
	
	@Override
	public void execute(DelegateExecution execution) throws Exception 
	{
		String ingredientName  = (String) execution.getVariable("ingredient");
		Integer ingredientAmm  = (Integer) execution.getVariable("ingredientAmm");
		
		Ingredient ingredient = getIngredient(ingredientName);
		List<Cancerlink> knownCancerLinks = getKnownLinks(ingredient, ingredientAmm);
		List<Cancer> cancers = getCancers(knownCancerLinks);
		
		execution.setVariable("cancers", cancers);
		execution.setVariable("foundRisk", cancers.size() > 0);
	}
	
	private List<Cancer> getCancers(List<Cancerlink> knownCancerLinks)
	{
		ArrayList<Cancer> cancers = new ArrayList<Cancer>();
		for (Cancerlink link : knownCancerLinks)
		{
			Cancer cancer = getCancer(link);
			if (cancer != null)
				cancers.add(cancer);
		}
		return cancers;
	}

	private Cancer getCancer(Cancerlink link)
	{
		Cancer cancer = null;		
		ResultSet sqlResult = getCancerQuery(link);
		try {
			while(sqlResult.next())
			{
				cancer = new Cancer(sqlResult.getString(1), sqlResult.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cancer;
	}
	
	public ResultSet getCancerQuery(Cancerlink link)
	{
		
		PreparedStatement statement = DBConnector.createStatement(SQLStatements.getCancer());
		try {
			statement.setInt(1, link.getCancerID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet sqlResult = DBConnector.executeQuery(statement);
		return sqlResult;
	}
	

	private List<Cancerlink> getKnownLinks(Ingredient ingredient, Integer ingredientAmm)
	{
		ArrayList<Cancerlink> links = new ArrayList<Cancerlink>();
		ResultSet sqlResult = getLinkQuery(ingredient.getID(), ingredientAmm);
		try {
			while(sqlResult.next())
			{
				Cancerlink cancerlink = new Cancerlink(sqlResult.getInt(1), sqlResult.getInt(2), sqlResult.getInt(3));
				links.add(cancerlink);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return links;
	}
	
	public ResultSet getLinkQuery(int ingredientID, int ingredientAmm)
	{
		
		PreparedStatement statement = DBConnector.createStatement(SQLStatements.getCancerID());
		try {
			statement.setInt(1, ingredientID);
			statement.setInt(2, ingredientAmm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet sqlResult = DBConnector.executeQuery(statement);
		return sqlResult;
	}

	public Ingredient getIngredient(String ingredientName)
	{
		Ingredient newObj = null;		
		ResultSet sqlResult = getIngredientQuery(ingredientName);
		try {
			while(sqlResult.next())
			{
				newObj = new Ingredient(sqlResult.getString(1), sqlResult.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newObj;
	}
	
	public ResultSet getIngredientQuery(String ingredientName)
	{
		PreparedStatement statement = DBConnector.createStatement(SQLStatements.getIngredientID());
		try {
			statement.setString(1, ingredientName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet sqlResult = DBConnector.executeQuery(statement);
		return sqlResult;
	}
}
