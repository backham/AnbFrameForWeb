package com.anbtech.anbframe.usermng;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.anbtech.anbframe.usermng.service.UserMngDAOService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath*:/spring/context-*.xml")
public class UserMngTest {

	private static final Logger LOG = LoggerFactory.getLogger(UserMngTest.class);
	
	@Autowired
	private UserMngDAOService dao;
	
	@Test
	public void testName() throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "koala";
        String encoded = encoder.encode(password);
        String encoded2 = encoder.encode(password);
        boolean isMatch = encoder.matches(password, encoded);
        if(isMatch) System.out.println("매치");
        else System.out.println("no 매치");
        assertTrue(encoded2 != encoded);

        System.out.println(encoded);
        System.out.println(encoded2);
	}
	
}
