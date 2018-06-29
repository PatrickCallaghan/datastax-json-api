package com.datastax.demo.service;

import java.util.List;

import com.datastax.demo.dao.SampleDao;
import com.datastax.demo.utils.PropertyHelper;
import com.datastax.driver.core.KeyspaceMetadata;

public class Service {

	private SampleDao dao;

	public Service() {		
		String contactPointsStr = PropertyHelper.getProperty("contactPoints", "localhost");
		this.dao = new SampleDao(contactPointsStr.split(","));
	}	
	
	public List<KeyspaceMetadata> getKeyspaces() {
		return dao.getKeyspaces();
	}
	
	public String getJsonForTable(String keyspace, String table, String partitionKey, Object partitionValue){
		return dao.getJsonForTable(keyspace, table, partitionKey, partitionValue);
	}
}
