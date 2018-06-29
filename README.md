DataStax Json API
========================

This is a simple converter to change the contents of a row into JSON.

To run the web app run (replace localhost with your a contact point for your DSE cluster).
```

mvn jetty:run -DcontactPoints=localhost
		
```

To get JSON for a table and partition, use the following (replace localhost with your node id or name in all uris).
```

http://localhost:8080/datastax-json-api/rest/get/data/<keyspace>/<table>/<partitonkey>/<partitionvalue>
	
```

To get JSON for a table and a default limit of 100, use the following 
```

http://localhost:8080/datastax-json-api/rest/get/data/<keyspace>/<table
	
```

To check if its running - try the following in a browser

```

http://localhost:8080/datastax-json-api/rest/get/data/system/local/key/local
	
```

Another example using https://github.com/PatrickCallaghan/datastax-banking-iot 

```

http://localhost:8080/datastax-json-api/rest/get/data/datastax_banking_iot/latest_transactions/cc_no/1234123412415521

```


