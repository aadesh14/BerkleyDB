package com.aadesh.example;
import java.io.File;
import java.io.FileNotFoundException;

import com.sleepycat.db.DatabaseException;
import com.sleepycat.db.Environment;
import com.sleepycat.db.EnvironmentConfig;


public class CreateEnvironment {

	/**
	 * @param args
	 * @throws DatabaseException 
	 * @throws FileNotFoundException 
	 */
	static final String  environment_location = "E:\\Friends_Environment";
	private Environment environment=null;
	

	public Environment create_environment() throws FileNotFoundException, DatabaseException{
		// Create Environment
        EnvironmentConfig envConf= new EnvironmentConfig();
        envConf.setAllowCreate(true);
        envConf.setTransactional(true);
        envConf.setCacheMax(1024);
        envConf.setInitializeCache(true);
        
       
                
        environment=new Environment(new File(environment_location), envConf); 
        return environment;
	}
	
	public static void main(String[] args) throws FileNotFoundException, DatabaseException {
        new CreateEnvironment().create_environment();
       
	}

}
