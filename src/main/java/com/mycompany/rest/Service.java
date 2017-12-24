package com.mycompany.rest;

import java.util.TimeZone;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mycompany.twitterfeed.Analyzer;
import com.mycompany.twitterfeed.Entity;
import com.mycompany.twitterfeed.Response;

@Path("/twitter/analyse/")
public class Service {
	
	@GET
	@Path("{userId}/{interval}/{entity}/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response analyze(@PathParam("userId") String userId, @PathParam("interval") int interval,
			@PathParam("entity") String entity, @PathParam("type") String type) {
		return Analyzer.INSTANCE.analyze(userId, interval, Entity.valueOf(entity.toUpperCase()), TimeZone.getDefault(), type, 100);
	}
}