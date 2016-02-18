package com.ucan.server.controller;

import java.io.InputStream;
import java.net.URLDecoder;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucan.server.model.UserInfo;
import com.ucan.server.model.UserCore;
import com.ucan.server.service.UserInfoService;
import com.ucan.server.utils.DigestUtils;
import com.ucan.server.utils.FileUtil;

@Path("/user/info")
public class UserInfoController {

	private UserCore userSecret;
	private ObjectMapper om = new ObjectMapper();
	@Autowired
	private UserInfoService userInfoService;

	private Logger log = Logger.getLogger(UserInfoController.class);

	@GET
	@Path("/{account}/isExist")
	@Consumes("multipart/text_plain;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Response isUserExist(@PathParam("account") String account) {
		try {
			if (userInfoService.isUserExist(account)) {
				return Response.status(Status.OK).entity("").build();
			} else {
				return Response.status(Status.OK).entity("").build();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return Response.ok().build();
	}

	@GET
	@Path("/userLogin")
	@Consumes("multipart/text_plain;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Response userLogin(@QueryParam("account") String account, @QueryParam("password") String password) {

		return Response.status(Status.OK).build();

	}

	

	@POST
	@Path("/saveAvatar")
	@Consumes("multipart/form-data;charset=UTF-8")
	@Produces("appliction/json;charset=UTF-8")
	public Response uploadImage(@FormDataParam("account") String account, @FormDataParam("file") InputStream file,
			@FormDataParam("filename") String filename) {
		// try {
		// File f = new File("d:\\" + DigestUtils.md5(account));
		// if (!f.exists()) {
		// f.mkdir();
		// }
		// FileOutputStream fos = new FileOutputStream(f + filename + ".png");
		//
		// byte[] b = new byte[1024];
		// while ((file.read(b)) != -1) {
		// fos.write(b);
		// }
		// fos.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		return Response.ok().build();
	}
}
