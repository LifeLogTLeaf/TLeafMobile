package com.tleaf.lifelog.couchbaselite.commands;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.google.gson.Gson;
import com.tleaf.lifelog.model.Lifelog;

public class CreateDocumentCommand implements CouchbaseLiteCommand {

	Database database;
	Lifelog lifelog;

	public CreateDocumentCommand(Database database, Lifelog lifelog) {
		this.database = database;
		this.lifelog = lifelog;
	}
	
	// We are going to make a lot of Documents and it is unnecessary to make
	// Command Object every time, so just change the Object
	public void setLifelog( Lifelog lifelog ) {
		this.lifelog = lifelog;
	}

	@Override
	public void executeCommand() {

		Document document;
		Gson gson = new Gson();
		Map<String, Object> data = null;

		// Create a Blank Document with Specific ID we want
		document = database.getDocument( lifelog.getId() );

		// Convert Lifelog Object to JSON format String
		// JsonIgnoreProperty ( id,rev ) is ignored here
		String jsonString = gson.toJson(lifelog);

		// Convert JSON String to HashMap<String,Object>, and Inserts it into Document
		ObjectMapper mapper = new ObjectMapper();
		try {

			data = mapper.readValue(jsonString,
					new TypeReference<HashMap<String, Object>>() {});
			document.putProperties(data);
			
			Log.i("Sucessfully Executed CreateDocumentCommand", 
					"Lifelog Document is Created Sucessfully\n"+jsonString);

		} catch (IOException e2) {
			Log.i("IOException in CreateDocumentCommand",
					"Failed to Convert Lifelog Json String into HashMap<String,Object>");
			e2.printStackTrace();
		} catch (CouchbaseLiteException e) {
			Log.i("CouchbasLiteException in CreateDocumentCommand",
					"Failed to put HashMap Properties into Couchbase Doc");
			e.printStackTrace();
		}

	}

}
