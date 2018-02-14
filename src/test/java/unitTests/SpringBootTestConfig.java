package unitTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import pack.config.MyTestProjectApplication;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = MyTestProjectApplication.class)
public class SpringBootTestConfig {
	@Test
	public void contextLoads() {
	}
}
