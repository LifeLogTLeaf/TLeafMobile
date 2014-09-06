package com.tleaf.lifelog.couchbaselite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.replicator.Replication;
import com.tleaf.lifelog.couchbaselite.commands.CouchbaseLiteCommand;
/**
 * 2014.09.06
 * @author susu
 * CouchbaseLiteCommand Controller
 * Wondering if We should make this a Singleton Object
 */
public class CouchbaseControl {

	Manager manager;
	Replication replication;
	Database database;
	
	ExecuteCouchbaseCommands executeCouchbaseCommands = new ExecuteCouchbaseCommands();

	Map<String, CouchbaseLiteCommand> couchbaseLiteCommandMap = new HashMap<String, CouchbaseLiteCommand>();

	public CouchbaseControl(Manager manager) {
		this.manager = manager;
	}
	
	public void addCouchbaseLiteCommand ( String commandName, CouchbaseLiteCommand couchbaseLiteCommand ) {
		couchbaseLiteCommandMap.put( commandName, couchbaseLiteCommand );
	}
	
	public void executeCouchbaseLiteCommand ( String commandName ) {
		Log.i("CouchbasControl", commandName + " Command Execution");
		executeCouchbaseCommands.execute( couchbaseLiteCommandMap.get( commandName ) );
	}

	public boolean initializeCouchbase(String dbName, String replicationURL) {

		// Informing the User if Database name is invalid
		if (!Manager.isValidDatabaseName(dbName)) {
			Log.e("CouchbaseControl initialization",
					"Invaild database name. Lowercase letter, numbers and _$()+-/ are allowed. "
							+ "Should Begin with a letter");
			return false;
		}

		// Creating Database..
		try {
			database = manager.getDatabase(dbName);
		} catch (CouchbaseLiteException e) {
			Log.e("CouchbaseControl initialization",
					"Database Could not be made");
			e.printStackTrace();
		}

		URL Url = null;
		try {
			Url = new URL(replicationURL);
		} catch (MalformedURLException e) {
			Log.i("CouchbaseControl initialization", "Your URL is Malformed");
			e.printStackTrace();
			return false;
		}

		// Create a Replication but Didn't executed it yet
		replication = database.createPushReplication(Url);

		Log.e("CouchbaseControl initialization", "Initializatoin Completed");

		return true;
	}

	public Replication getReplication() {
		return replication;
	}
	
	public Database getDatabase() {
		return database;
	}

}
