package com.example.liferay.reactpoc.portlet

import com.example.liferay.reactpoc.constants.MainPortletKeys
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil
import com.liferay.portal.kernel.portlet.ConfigurationAction
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction
import com.liferay.portal.kernel.util.ParamUtil
import org.osgi.service.component.annotations.Activate
import org.osgi.service.component.annotations.Component
import org.osgi.service.component.annotations.ConfigurationPolicy
import org.osgi.service.component.annotations.Modified

import javax.portlet.ActionRequest
import javax.portlet.ActionResponse
import javax.portlet.PortletConfig
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component(configurationPid = arrayOf("MainPortletConfiguration"),
        configurationPolicy = ConfigurationPolicy.OPTIONAL,
        immediate = true,
        property = arrayOf("javax.portlet.name=" + MainPortletKeys.NAME_MAIN),
        service = arrayOf(ConfigurationAction::class))
class MainPortletConfigurationAction : DefaultConfigurationAction() {

    @Throws(Exception::class)
    override fun processAction(
            portletConfig: PortletConfig?, actionRequest: ActionRequest,
            actionResponse: ActionResponse) {
        val universalRender = ParamUtil.getString(actionRequest, "universalRender")
        setPreference(actionRequest, "universalRender", universalRender)

        val historyApi = ParamUtil.getString(actionRequest, "historyApi")
        setPreference(actionRequest, "historyApi", historyApi)

        super.processAction(portletConfig, actionRequest, actionResponse)
    }

    @Throws(Exception::class)
    override fun include(
            portletConfig: PortletConfig?, httpServletRequest: HttpServletRequest,
            httpServletResponse: HttpServletResponse) {
        httpServletRequest.setAttribute(
                MainPortletConfiguration::class.java.name,
                _mainPortletConfiguration)

        super.include(portletConfig, httpServletRequest, httpServletResponse)
    }

    @Activate
    @Modified
    protected fun activate(properties: Map<Any, Any>) {
        _mainPortletConfiguration = ConfigurableUtil.createConfigurable(
                MainPortletConfiguration::class.java, properties)
    }

    @Volatile private var _mainPortletConfiguration: MainPortletConfiguration? = null


}
