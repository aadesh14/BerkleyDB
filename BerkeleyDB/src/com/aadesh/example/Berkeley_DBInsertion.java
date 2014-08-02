package com.aadesh.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import com.aadesh.data.Friend;
import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;
import com.sleepycat.db.Database;
import com.sleepycat.db.DatabaseConfig;
import com.sleepycat.db.DatabaseEntry;
import com.sleepycat.db.DatabaseException;
import com.sleepycat.db.DatabaseType;
import com.sleepycat.db.Environment;
import com.sleepycat.db.OperationStatus;

public class Berkeley_DBInsertion extends TupleBinding {

	/**
	 * To store complex objects using a custom tuple binding: 1. Implement the
	 * class whose instances that you want to store. Note that you do not have
	 * to implement the Serializable interface. 2. Write a tuple binding using
	 * the com.sleepycat.bind.tuple.TupleBinding class. 3. Open (create) your
	 * database. Unlike serialization, you only need one. 4. Create an entry
	 * binding that uses the tuple binding that you implemented in step 2. 5.
	 * Instantiate an instance of the object that you want to store, and place
	 * it in a DatabaseEntry using the entry binding that you created in the
	 * previous step.
	 * @throws UnsupportedEncodingException 
	 */

	public void insertInDB() throws FileNotFoundException, DatabaseException, UnsupportedEncodingException {

		Environment environment =new Environment(new File(CreateEnvironment.environment_location), null);
		
//		DatabaseConfig dbconfig = new DatabaseConfig();
//		//dbconfig.setSortedDuplicates(true);	
//		dbconfig.setType(DatabaseType.BTREE);
//		dbconfig.setAllowCreate(true);	
		
		Database friend_list = environment.openDatabase(null,
				CreateEnvironment.environment_location + "\\FriendsDB.db",
				null, null);
		
        Friend friend= new Friend();
        friend.setFirstName("Pallavi");
        friend.setLastName("Bhardwaj");
        friend.setDob("30/08/1988");
        friend.seteMail("pallavi@gmail.com");
        
		DatabaseEntry friend_key = new DatabaseEntry();
        DatabaseEntry friend_data= new  DatabaseEntry();
          
        friend_key.setData(friend.geteMail().getBytes("UTF-8"));
        objectToEntry(friend, friend_data);
        
        OperationStatus ostutus=friend_list.put(null, friend_key, friend_data);
        System.out.println("status="+ostutus.toString());
        
        friend_list.close();
        environment.close();
        
          
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Berkeley_DBInsertion insert=new Berkeley_DBInsertion();
       try {
		insert.insertInDB();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DatabaseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public Object entryToObject(TupleInput friendTupleInput) {
		// To Read data, order is important
		String firstName = friendTupleInput.readString();
		String lastName = friendTupleInput.readString();
		String dob = friendTupleInput.readString();
		String eMail = friendTupleInput.readString();

		Friend friend = new Friend();
		friend.setFirstName(firstName);
		friend.setLastName(lastName);
		friend.setDob(dob);
		friend.seteMail(eMail);

		return friend;
	}

	public void objectToEntry(Object friendWrite, TupleOutput friendTupleOutput) {
		// To write data, order is important
		Friend friend = (Friend) friendWrite;
		friendTupleOutput.writeString(friend.getFirstName());
		friendTupleOutput.writeString(friend.getLastName());
		friendTupleOutput.writeString(friend.getDob());
		friendTupleOutput.writeString(friend.geteMail());

	}

}
