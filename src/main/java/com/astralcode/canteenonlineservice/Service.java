package com.astralcode.canteenonlineservice;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class Service extends ResourceConfig
{
	public Service()
	{
		packages("com.astralcode.canteenonlineservice.resource");
	}
}
