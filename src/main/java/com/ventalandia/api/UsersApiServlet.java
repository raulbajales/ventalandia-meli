package com.ventalandia.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.meli.api.user.MeliPublicUser;
import com.ventalandia.meli.service.MeliUserContext;
import com.ventalandia.meli.service.UserMeliService;

/**
 * 
 * @author matias
 * 
 */
@Path("/api/users")
@Produces(MediaType.TEXT_PLAIN)
public class UsersApiServlet {

    @Inject
    private UserService userService;

    @Inject
    private UserMeliService userMeliService;

    @Inject
    private Gson gson;

    @GET
    @Path("/{userId}")
    public String getUserById(@PathParam("userId")
    Long userId) {
        MeliPublicUser user = this.userMeliService.getPulicUser(userId);

        PublicUser publicUser = new PublicUser();

        publicUser.setId(user.getId());
        publicUser.setNickname(user.getNickname());
        publicUser.setPermalink(user.getPermalink());

        return this.gson.toJson(publicUser);
    }

    // TODO add memcache or something to this call!!!
    @GET
    @Path("/me")
    public String me() {
        MeliUserContext.set(this.userMeliService.getCurrentUser());
        return this.gson.toJson(this.userService.getCurrent());
    }
}
