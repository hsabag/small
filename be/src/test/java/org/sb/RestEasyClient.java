package org.sb;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class RestEasyClient {
	
	public static SmallBusinessAPI getProxy(String uri)
	{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri);
        ResteasyWebTarget rtarget = (ResteasyWebTarget)target;

        SmallBusinessAPI proxy = rtarget.proxy(SmallBusinessAPI.class);
        return proxy;
	}

}
