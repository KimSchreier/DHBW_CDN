package de.dhbw.ds.cdn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShopApplication.class)
@WebAppConfiguration
public class ShopApplicationTests {

	private ShopApplication Shop = new ShopApplication();

	@Test
	public void contextLoads(){
	}

	@Test
	public void homePageTest(){
		System.out.println(Shop.toString());

	}

	@Test
	public void logInTest(){
		//TODO

	}
	@Test
	public void newOrderTest(){
		//TODO

	}
	@Test
	public void logOutTest(){
		//TODO

	}

}
