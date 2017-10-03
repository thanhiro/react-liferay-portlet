package com.example.liferay.reactpoc.rest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import org.osgi.service.component.annotations.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/test")
@Component(immediate = true, service = Application.class)
public class TestResource extends Application {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> working() {
        HashMap<String, String> m = new HashMap<>();
        m.put("foo", "bar");
        return m;
    }

    @GET
    @Path("/user")
    @Produces("text/plain")
    public String hello(@Context User user) throws PortalException {
        return user.getLogin();
    }
}