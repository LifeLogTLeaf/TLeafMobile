package com.tleaf.lifelog.couchbaselite;

import com.tleaf.lifelog.couchbaselite.commands.CouchbaseLiteCommand;

import android.os.AsyncTask;

public class ExecuteCouchbaseCommands extends
		AsyncTask<CouchbaseLiteCommand, Void, String> {

	@Override
	protected String doInBackground(CouchbaseLiteCommand... params) {
		params[0].executeCommand();
		return null;
	}

}
