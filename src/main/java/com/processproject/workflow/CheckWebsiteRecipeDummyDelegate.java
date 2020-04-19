package com.processproject.workflow;

import java.util.ArrayList;
import java.util.Random;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckWebsiteRecipeDummyDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception 
	{
		String recipe = getDummyRecipes();
		execution.setVariable("recipe", recipe);
		execution.setVariable("recipeFound", !recipe.equals(""));
	}
	
	public String getDummyRecipes()
	{
		Random rand = new Random();
	    ArrayList<String> dummyRecipes = new ArrayList<String>();
	    dummyRecipes.add("");
	    
	    
	    dummyRecipes.add("{" + 
	    		"	        \"name\": \"Pad Thai\",\r\n" + 
	    		"	        \"author\": \"Recipe courtesy Alton Brown, 2005\",\r\n" + 
	    		"	        \"yield\": \"\",\r\n" + 
	    		"	        \"duration\": \"Cooking time (duration): 40\",\r\n" + 
	    		"	        \"photo\": \"http://www.food.com/recipe-123.jpg\",\r\n" + 
	    		"	        \"summary\": \"My special dish\",\r\n" + 
	    		"	        \"nutrition\": \"NutritionalInformation\\n\\nblah\",\r\n" + 
	    		"	        \"published\": \"2012-08-08\",\r\n" + 
	    		"	        \"instructions\": [],\r\n" + 
	    		"	        \"ingredients\": []\r\n" + 
	    		"	    }");
	    
	    String randomElement = dummyRecipes.get(rand.nextInt(dummyRecipes.size()));
	    return randomElement;
	}

}
