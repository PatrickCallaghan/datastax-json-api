package com.datastax.demo.webservice;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.demo.service.Service;
import com.datastax.driver.core.KeyspaceMetadata;

@WebService
@Path("/")
public class SampleWS {

	private Logger logger = LoggerFactory.getLogger(SampleWS.class);
	private SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyyMMdd");

	//Service Layer.
	private Service service = new Service();
	
	@GET
	@Path("/get/keyspaces")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovements() {
				
		List<KeyspaceMetadata> keyspaces = service.getKeyspaces();		
		return Response.status(Status.OK).entity(keyspaces.toString()).build();
	}
	
	@GET
	@Path("/get/data/{keyspace}/{table}/{partitionkey}/{partitionvalue}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getData(@PathParam("keyspace") String keyspace, @PathParam("table") String table, @PathParam("partitionkey") String partitionKey,
			@PathParam("partitionvalue") String partitionValue){
				
		logger.info("Getting Json for " + keyspace + "." + table+ " - " + partitionKey + "=" + partitionValue);
		
		String response = service.getJsonForTable(keyspace, table, partitionKey, partitionValue);		
		return Response.status(Status.OK).entity(response).build();
	}
	
	@GET
	@Path("/get/data/{keyspace}/{table}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getDataWithoutKey(@PathParam("keyspace") String keyspace, @PathParam("table") String table){
				
		logger.info("Getting Json for " + keyspace + "." + table);
		
		String response = service.getJsonForTable(keyspace, table, 100);		
		return Response.status(Status.OK).entity(response).build();
	}
}
