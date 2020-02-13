package cn.tedu.sample2.mapper;

import cn.tedu.sample2.entity.User;

public interface UserMapper {

	User findByUsername(String username);
	
}
