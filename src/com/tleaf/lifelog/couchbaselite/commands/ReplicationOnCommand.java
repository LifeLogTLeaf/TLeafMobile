package com.tleaf.lifelog.couchbaselite.commands;

import com.couchbase.lite.replicator.Replication;

public class ReplicationOnCommand implements CouchbaseLiteCommand {
	
	Replication replication;
	
	public ReplicationOnCommand ( Replication replication ) {
		this.replication = replication;
	}
	
	@Override
	public void executeCommand() {
		replication.start();
	}

}
