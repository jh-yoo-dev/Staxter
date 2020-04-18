package com.staxter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import com.staxter.common.Message;
import com.staxter.entity.UserDto;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class DemoApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void test001CreateUser() throws Exception {
		final UserDto user = new UserDto();
		user.setFirstName("Juhyoung");
		user.setLastName("Yoo");
		user.setUserName("jhyoo");
		user.setPassword("1234");

		final Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("id", "1");
		resultMap.put("firstName", "Juhyoung");
		resultMap.put("lastName", "Yoo");
		resultMap.put("userName", "jhyoo");

		final ResponseEntity<Map> result = restTemplate.postForEntity("/userservice/register", user, Map.class);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(resultMap, result.getBody());
	}

	@Test
	public void test002AlreadyExists() throws Exception {
		final UserDto user = new UserDto();
		user.setFirstName("Juhyoung");
		user.setLastName("Yoo");
		user.setUserName("jhyoo");
		user.setPassword("1234");

		final Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code",Message.USER_ALREADY_EXISTS.getCode());
		resultMap.put("description",Message.USER_ALREADY_EXISTS.getDescription());

		final ResponseEntity<Map> result = restTemplate.postForEntity("/userservice/register", user, Map.class);
		assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
		assertEquals(resultMap, result.getBody());
	}

	@Test
	public void test003Login() throws Exception {
		final UserDto user = new UserDto();
		user.setUserName("jhyoo");
		user.setPassword("1234");

		final Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code",Message.LOGIN_SUCCESS.getCode());
		resultMap.put("description",Message.LOGIN_SUCCESS.getDescription());

		final ResponseEntity<Map> result = restTemplate.postForEntity("/userservice/login", user, Map.class);
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		assertEquals(resultMap, result.getBody());

	}

	@Test
	public void test004LoginFailed() throws Exception {
		final UserDto user = new UserDto();
		user.setUserName("jhyoo");
		user.setPassword("1");

		final Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code",Message.LOGIN_FAILED.getCode());
		resultMap.put("description",Message.LOGIN_FAILED.getDescription());

		final ResponseEntity<Map> result = restTemplate.postForEntity("/userservice/login", user, Map.class);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
		assertEquals(resultMap, result.getBody());

	}

}
