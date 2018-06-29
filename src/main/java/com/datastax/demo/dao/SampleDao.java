package com.datastax.demo.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;

public class SampleDao {

	private static Logger logger = LoggerFactory.getLogger(SampleDao.class);
	private Session session;

	private static String keyspaceName = "sample_keyspace";
	private static String testTable = keyspaceName + ".test";
	private List<KeyspaceMetadata> keyspaces;
	
	public SampleDao(String[] contactPoints) {

		Cluster cluster = Cluster.builder().addContactPoints(contactPoints).build();

		this.session = cluster.connect();
		this.keyspaces = cluster.getMetadata().getKeyspaces();
	}

	public List<KeyspaceMetadata> getKeyspaces() {
		return keyspaces;
	}

	public String getJsonForTable(String keyspace, String table, String partitionKey, Object partitionValue){
		
		Statement cql = QueryBuilder.select().all().json().from(keyspace, table).where(QueryBuilder.eq(partitionKey,partitionValue));
				
		logger.info("cql=" + cql.toString());
		ResultSet resultSet = session.execute(cql);
		
		if (!resultSet.isExhausted()){
			List<Row> all = resultSet.all();
			List<String> jsons = new ArrayList<String>();
			StringBuffer buffer = new StringBuffer();
			
			for (Row row : all){
				jsons.add(row.getString(0));
				buffer.append(row.getString(0));
			}
			return buffer.toString();
		}else{
			//return Arrays.asList("empty resultset");
			return "empty resultset";
		}
		
	}
}
