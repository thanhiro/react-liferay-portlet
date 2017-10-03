package com.example.liferay.reactpoc.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.liferay.portal.kernel.exception.PortalException
import com.liferay.portal.kernel.model.User
import org.osgi.service.component.annotations.Component
import javax.ws.rs.ApplicationPath
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Application
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType

@ApplicationPath("/poc")
@Component(immediate = true, service = arrayOf(Application::class))
class TestResource : Application() {

    // Move to JSON provider
    private val objectMapper = ObjectMapper()

    override fun getSingletons(): Set<Any> {
        return emptySet()
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun withSerialize(): String {
        return objectMapper.writeValueAsString(mapOf("this" to "is"))
    }

    @GET
    @Path("/json-schema")
    @Produces(MediaType.APPLICATION_JSON)
    fun formSchema(): String {
        return """
            |{
            |   "schema": {
            |       "type": "object",
            |       "properties": {
            |           "foo": {
            |               "type": "object",
            |               "properties": {
            |                   "bar": {"type": "string"}
            |               }
            |           }
            |       }
            |   },
            |   "ui": {
            |       "foo": {
            |           "bar": {
            |               "ui:widget": "textarea"
            |           }
            |       }
            |   }
            |}
        """.trimMargin()
    }

    /**
     * This is not yet working!
     */
    @GET
    @Path("/user")
    @Produces("text/plain")
    @Throws(PortalException::class)
    fun hello(@Context user: User): String {
        return user.login
    }
}