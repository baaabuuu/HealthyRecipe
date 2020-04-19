package com.processproject.workflow;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


public class IsRecipeHealthyRequestDelegate implements JavaDelegate {


	@Override
	public void execute(DelegateExecution execution) throws Exception 
	{
		String recipe	   = (String) execution.getVariable("recipe");		
		
		execution.getProcessEngineServices()
		.getRuntimeService()
		.createMessageCorrelation("IsRecipeHealthy")
		.setVariable( "recipe", recipe)
		.correlate();		
	}
	


}
