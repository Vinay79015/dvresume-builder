package org.acm.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.acm.authentication.JWTService;
import org.acm.entity.User;
import org.acm.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Path("/userResource")
public class UserResource {

    @Inject
    UserService userService;

    @Inject
    JWTService jwtService;

    @POST
    @Path("/signup")
    public Response signup(User user) {
        if (userService.signUp(user)){
            return Response.status(Response.Status.CREATED).entity("User signed up successfully").build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("User already exists").build();
        }
    }
    @POST
    @Path("/login")
    public Response login(User user) {
        if (userService.login(user.getUserName(), user.getPassWord())) {
            String token = jwtService.generateToken(user.getUserName());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return Response.ok(response).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
        }
    }
}
