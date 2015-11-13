package com.urqaRunnerForAppium.test;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class JUnitExecutionListener extends RunListener {
	
	FileWriter file = null;
	JSONObject obj = null;//new JSONObject();
	JSONArray list = null; //new JSONArray();
	
	public void prepareJUnitExecutionListener(){
		try {
			
			long time = System.currentTimeMillis(); 
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String str = dayTime.format(new Date(time));

			file = new FileWriter("testResult" + str + ".json");
			//file.write(obj.toJSONString());
			//file.flush();
			//file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

    public void testRunStarted(Description description) throws Exception {
    	prepareJUnitExecutionListener();
        System.out.println("Number of tests to execute: " + description.testCount());
    }

    public void testRunFinished(Result result) throws Exception {
        System.out.println("Number of tests executed: " + result.getRunCount());
        
        if(file == null){
        	prepareJUnitExecutionListener();
        }
        
    	obj = new JSONObject();
    	list = new JSONArray();
    	list.add("Number of tests executed: " + result.getRunCount());
    	int totalCount = result.getRunCount();
    	int failureCount = result.getFailureCount();
    	int ignoreCount = result.getIgnoreCount();
    	int passCount = totalCount - failureCount - ignoreCount;
    			
    	list.add("PASS : " + passCount +", FAIL : " + failureCount + ", Ignored : " + ignoreCount);
    	obj.put("Result : ", list);
    	file.write(obj.toJSONString());
    	file.flush();
    	file.close();
    }

    public void testStarted(Description description) throws Exception {
    	if(file == null){
        	prepareJUnitExecutionListener();
        }
    	obj = new JSONObject();
    	list = new JSONArray();
    	list.add("TestCase : " + description.getMethodName());
        System.out.println("TestCase: " + description.getMethodName());
    }

    public void testFinished(Description description) throws Exception {
    	if(file == null){
        	prepareJUnitExecutionListener();
        }
    	if(list.size() == 1 ){
    		list.add("Result: " + "PASS");
    	}
    	else 
    		list.add("Result: " + "FAIL");
    	obj.put(description.getMethodName(), list);
    	
    	file.write(obj.toJSONString());
		file.flush();
    	
        System.out.println("Finished: " + description.getMethodName());
    }

    public void testFailure(Failure failure) throws Exception {
    	if(file == null){
        	prepareJUnitExecutionListener();
        }
    	list.add("Failed(Description) : " 
    			+ failure.getDescription().getMethodName() +"\n" 
    			+"Failed(Exception) : " +  failure.getException() +"\n" 
    			+"Failed(Message) : " +  failure.getMessage() +"\n"
    			+"Failed(Trace) : " +failure.getTrace());
    		
        System.out.println("Failed: Description : " + failure.getDescription().getMethodName());
        System.out.println("-Failed: getException " + failure.getException());
        System.out.println("-Failed: getMessage " + failure.getMessage());
        System.out.println("-Failed: getTrace " + failure.getTrace());
    }

    public void testAssumptionFailure(Failure failure) {
    	if(file == null){
        	prepareJUnitExecutionListener();
        }
    	list.add("Failed(Description) : " 
    			+ failure.getDescription().getMethodName() +"\n" 
    			+"Failed(Exception) : " +  failure.getException() +"\n" 
    			+"Failed(Message) : " +  failure.getMessage() +"\n"
    			+"Failed(Trace) : " +failure.getTrace());
        System.out.println("Failed: " + failure.getDescription().getMethodName());
        System.out.println("Failed: " + failure.getException());
        System.out.println("Failed: " + failure.getMessage());
        System.out.println("Failed: " + failure.getTrace());
    }

    public void testIgnored(Description description) throws Exception {
    	if(file == null){
        	prepareJUnitExecutionListener();
        }
    	obj = new JSONObject();
    	list = new JSONArray();
    	list.add("TestCase : " + description.getMethodName());
    	list.add("Ignored test case");
    	obj.put(description.getMethodName(), list);
        System.out.println("Ignored: " + description.getMethodName());
    }
}
