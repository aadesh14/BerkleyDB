package com.aadesh.example;

import java.io.File;
import java.io.FileNotFoundException;

import com.sleepycat.db.Database;
import com.sleepycat.db.DatabaseConfig;
import com.sleepycat.db.DatabaseException;
import com.sleepycat.db.DatabaseType;
import com.sleepycat.db.Environment;

public class Berkeley_DBCreation {

	/**
	 * @param args
	 * @throws DatabaseException 
	 * @throws FileNotFoundException 
	 */
	static DatabaseConfig dbconfig=null;
	public void create_DB() throws FileNotFoundException, DatabaseException{
		// To create database
		dbconfig = new DatabaseConfig();
		dbconfig.setAllowCreate(true);
		dbconfig.setUnsortedDuplicates(true);  // false when creating secondary and true when creating primary
		dbconfig.setType(DatabaseType.BTREE);
			
		
		//to allow duplication if the access type is binary tree or hash only
			
		
		
		Database friend_list = new Database(CreateEnvironment.environment_location + "\\FriendsDB.db", null,dbconfig);
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, DatabaseException {	
    Berkeley_DBCreation create=new Berkeley_DBCreation();
    create.create_DB();
	}

}
