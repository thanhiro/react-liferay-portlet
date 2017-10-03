package com.example.liferay.reactpoc.provider

import com.liferay.portal.kernel.exception.PortalException
import com.liferay.portal.kernel.log.Log
import com.liferay.portal.kernel.log.LogFactoryUtil
import com.liferay.portal.kernel.model.User
import com.liferay.portal.kernel.util.Portal
import org.apache.cxf.jaxrs.ext.ContextProvider
import org.apache.cxf.message.Message
import org.osgi.service.component.annotations.Component
import org.osgi.service.component.annotations.Reference

import javax.servlet.http.HttpServletRequest
import javax.ws.rs.ext.Provider

@Component(immediate = true, service = arrayOf(CustomUserContextProvider::class))
@Provider
class CustomUserContextProvider : ContextProvider<User> {

    @Reference
    private lateinit var _portal: Portal

    override fun createContext(message: Message): User? {
        _log.info("createContext")
        try {
            return _portal!!.getUser(
                    message.getContextualProperty(
                            "HTTP.REQUEST") as HttpServletRequest)
        } catch (pe: PortalException) {
            if (_log.isWarnEnabled) {
                _log.warn("Unable to get user", pe)
            }

            return null
        }

    }

    companion object {
        private val _log = LogFactoryUtil.getLog(
                CustomUserContextProvider::class.java)
    }
}