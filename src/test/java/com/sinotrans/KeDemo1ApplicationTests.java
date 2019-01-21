package com.sinotrans;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sinotrans.test.TestServer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KeDemo1ApplicationTests {
	
	@Test
	public void contextLoads() {
		ServiceLoader<TestServer> serviceLoader = ServiceLoader.load(TestServer.class);
		Iterator<TestServer> iterator = serviceLoader.iterator();
		if (iterator.hasNext()) {
			TestServer testServer = iterator.next();
			System.out.println(testServer);
			testServer.test();
		}
	}

	
}

