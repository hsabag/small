package org.sb;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sb.data.Company;
import org.sb.data.Manager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SmallBusinessAPITest {
	
	
	ObjectMapper mapper = new ObjectMapper();
	
	
	@Before
	public void testHealth()
	{
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> status = template.getForEntity("http://localhost:8080/health", String.class);
		Assert.assertEquals("ok", status.getBody());
	}
	
	@Test
	public void test()
	{
		Company company = new Company();
		company.setDesc("test company");
		company.setName("TestCompany");
		Manager manager = new Manager();
		manager.setEmail("haim@haim.com");
		manager.setPassword("12345");
		company.setManager(manager);
		SmallBusinessAPI apiProxy = RestEasyClient.getProxy("http://localhost:8080/");
		ResponseEntity<String> response = apiProxy.registerCompany(company);
		
		try {
			String body = mapper.writeValueAsString(response.getBody());
			System.out.println(body);
			Assert.assertNotNull(body);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
		
		
	}

}
