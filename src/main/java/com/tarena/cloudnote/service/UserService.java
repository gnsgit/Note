package com.tarena.cloudnote.service;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tarena.cloudnote.dao.UserMybatisDao;
import com.tarena.cloudnote.entity.User;
import com.tarena.cloudnote.util.Md5Utils;
import com.tarena.cloudnote.util.UUIDUtil;

@Service
@Transactional
public class UserService {

	@Resource(name = "userMybatisDao")
	private UserMybatisDao userMybatisDao;

	public User validateUserAndPwd(String userName, String userPwd) {
		User user = new User();
		user.setCnUserName("zhangsan");
	//	user = userMybatisDao.getUserByName(userName);
		if(user!=null && "abc123".equals(userPwd)){
			return user;
		}
		return null;
 	}
}
