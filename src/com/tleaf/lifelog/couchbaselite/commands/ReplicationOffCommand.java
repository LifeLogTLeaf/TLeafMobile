package com.tleaf.lifelog.couchbaselite.commands;

import com.couchbase.lite.replicator.Replication;

public class ReplicationOffCommand implements CouchbaseLiteCommand {

	Replication replication;
	
	public ReplicationOffCommand( Replication replication ) {
		this.replication = replication;
	}
	
	@Override
	public void executeCommand() {
		replication.stop();
	}

}
