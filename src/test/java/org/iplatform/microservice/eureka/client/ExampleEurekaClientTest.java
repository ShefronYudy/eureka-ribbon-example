package org.iplatform.microservice.eureka.client;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.util.InstanceInfoGenerator;

/**
 * Unit test for simple App.
 */
public class ExampleEurekaClientTest {

	@Test
	public void testM1() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		InstanceInfo instanceInfo = InstanceInfoGenerator.takeOne();
		System.out.println(mapper.writeValueAsString(instanceInfo));
	}

}
