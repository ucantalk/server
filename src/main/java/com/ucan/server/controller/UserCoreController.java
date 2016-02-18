package com.ucan.server.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.ucan.server.model.UserCore;
import com.ucan.server.service.UserCoreService;

@Path("/user/core")
public class UserCoreController {
	@Autowired
	private UserCoreService userCoreService;

	private UserCore userCore;

	@POST
	@Path("/userRegister")
	@Consumes("multipart/form-data;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Response userRegister(@Context HttpServletRequest request, @FormDataParam("avatar") InputStream avatar) {
		userCore = new UserCore();
		userCore.setAccount(request.getHeader("account"));
		userCore.setPassword(request.getHeader("password"));

		try {
			userCoreService.post(userCore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok("aaaa").build();
	}

}
