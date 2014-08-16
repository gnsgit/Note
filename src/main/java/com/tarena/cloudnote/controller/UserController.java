package com.tarena.cloudnote.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.cloudnote.entity.Response;
import com.tarena.cloudnote.entity.User;
import com.tarena.cloudnote.service.UserService;
import com.tarena.cloudnote.util.Md5Utils;
import com.tarena.cloudnote.util.UUIDUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource(name = "userService")
	private UserService userService;

	/**
	 * 用户登陆验证
	 */
	@RequestMapping(value = "/login", 
						method = RequestMethod.POST, 
						headers = "Accept=application/json")
	@ResponseBody
	public Response login(HttpServletRequest request,
			HttpServletResponse response) {
		Response message = new Response();
		message.setStatus(1);
		message.setMessage("登录成功");

		String header = request.getHeader("Authorization");

		User loginUser = new User();
		try {
			String headers[] = header.split(" ");
			if (headers.length == 2) {
				String[] decode = new String(Base64.decodeBase64(headers[1]
						.getBytes("utf-8"))).split(":");
				if (decode.length == 2) {
					loginUser = userService.validateUserAndPwd(decode[0],
							decode[1]);
					if (loginUser != null) {
						message.setStatus(1);
						message.setResource(loginUser);
						message.setMessage("用户验证成功！");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message.setMessage("用户登录失败！");
			message.setStatus(-1);
		}
		return message;
	}

}
