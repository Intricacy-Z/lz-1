package cn.tedu.sample2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.sample2.entity.User;
import cn.tedu.sample2.mapper.UserMapper;
import cn.tedu.sample2.util.JsonResult;

@RestController
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("login")
	public JsonResult login(String username, String password) {
	    // 创建JsonResult对象
		JsonResult jsonResult = new JsonResult();
	    // 根据参数username查询对应的数据
		User result = userMapper.findByUsername(username);
	    // 判断查询结果是否为null
		if (result == null) {
		    // 是：用户名不存在
		    // -- 将JsonResult对象的state设置为2
			jsonResult.setState(2);
		} else {
		    // 否：用户名正确
		    // -- 判断查询结果中的密码与参数password是否匹配
			if (result.getPassword().equals(password)) {
				// -- 是：将JsonResult对象的state设置为1
				jsonResult.setState(1);
			} else {
				// -- 否：将JsonResult对象的state设置为3
				jsonResult.setState(3);
			}
		}
	    // 返回结果
		return jsonResult;
	}
	
}
