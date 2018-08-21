/*
*
* Copyright 2013 Netflix, Inc.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/

package org.iplatform.microservice.ribbon.examples.restclient;

import java.net.URI;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.niws.client.http.RestClient;

public class SampleApp {
	public static void main(String[] args) throws Exception {
		ConfigurationManager.loadPropertiesFromResources("ribbon-with-eureka.properties");
		
		/** Configuring and registering ribbon as eureka-client use eureka-client.properties */
		EurekaInstanceConfig instanceConfig = new MyDataCenterInstanceConfig();
		InstanceInfo instanceInfo = new EurekaConfigBasedInstanceInfoProvider(instanceConfig).get();
		ApplicationInfoManager applicationInfoManager = new ApplicationInfoManager(instanceConfig, instanceInfo);
		EurekaClient eurekaClient = new DiscoveryClient(applicationInfoManager, new DefaultEurekaClientConfig());
		
	    RestClient client = (RestClient) ClientFactory.getNamedClient("myclient");          
	    HttpRequest request = HttpRequest.newBuilder().uri(new URI("/auth/")).build(); 
	    for (int i = 0; i < 20; i++)  {
	                HttpResponse response = client.executeWithLoadBalancer(request);
	                System.out.println("######### Status code for " + response.getRequestedURI() + "  :" + response.getStatus());
	    }
		
		
	}
}
