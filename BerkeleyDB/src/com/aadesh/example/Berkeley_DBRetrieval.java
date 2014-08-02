package com.aadesh.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import com.sleepycat.db.Cursor;
import com.sleepycat.db.Database;
import com.sleepycat.db.DatabaseEntry;
import com.sleepycat.db.DatabaseException;
import com.sleepycat.db.Environment;
import com.sleepycat.db.LockMode;
import com.sleepycat.db.OperationStatus;

public class Berkeley_DBRetrieval {

	
	
	/**
	 * @param args
	 * @throws DatabaseException 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	public void readDB() throws FileNotFoundException, DatabaseException, UnsupportedEncodingException{
		// how to read values from a database 
		
		Environment environment = new Environment(new File(CreateEnvironment.environment_location), null);
		Database db=environment.openDatabase(null,CreateEnvironment.environment_location + "\\FriendsDB.db" , null, null);
	    Cursor cursor =  db.openCursor(null, null);
	    
	    DatabaseEntry found_key= new DatabaseEntry();
	    DatabaseEntry found_data= new DatabaseEntry();
	    
	    while (cursor.getNext(found_key, found_data, LockMode.DEFAULT)==OperationStatus.SUCCESS){
	    	String key= new String(found_key.getData(),"UTF-8");
	    	String data= new String(found_data.getData(),"UTF-8");
	    	System.out.println("key="+key+"......+data="+data);	    	
	    }
	    cursor.close();
	    db.close();
	    environment.close();
	    
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, DatabaseException {
		//call to readDB
        new Berkeley_DBRetrieval().readDB();
	}

}
