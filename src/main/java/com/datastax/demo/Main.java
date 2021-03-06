package com.datastax.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.demo.service.Service;
import com.datastax.demo.utils.Timer;

public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public Main() {

		//Examples of using variables passed in using -DcontactPoints		
		Service service = new Service();
		
		logger.info("EXPLAIN WHAT THE PROGRAM WILL DO");
		
		Timer timer = new Timer();
		
		//Do something here.
		logger.info(service.getKeyspaces().toString());
		
		timer.end();
		logger.info("Test took " +timer.getTimeTakenSeconds() + " secs.");
		System.exit(0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();

		System.exit(0);
	}

}
