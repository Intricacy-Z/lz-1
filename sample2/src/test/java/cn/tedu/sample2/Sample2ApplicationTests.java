package cn.tedu.sample2;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.sample2.entity.User;
import cn.tedu.sample2.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Sample2ApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired
	DataSource dataSource;

	@Test
	public void getConnection() throws SQLException {
		System.err.println(dataSource.getConnection());
	}
	
	@Autowired
	UserMapper userMapper;
	
	@Test
	public void findByUsername() {
		String username = "mybatis";
		User result = userMapper.findByUsername(username);
		System.err.println(result);
	}
	
}






