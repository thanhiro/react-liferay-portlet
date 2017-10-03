package com.example.liferay.reactpoc.portlet

import com.example.liferay.reactpoc.constants.MainPortletKeys
import com.example.liferay.reactpoc.ssr.SsrRenderer
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet
import org.osgi.service.component.annotations.Activate
import org.osgi.service.component.annotations.Component
import org.osgi.service.component.annotations.Modified
import org.osgi.service.component.annotations.Reference
import java.io.IOException
import javax.portlet.Portlet
import javax.portlet.PortletException
import javax.portlet.RenderRequest
import javax.portlet.RenderResponse

/**
 * @author akonovalov
 */
@Component(configurationPid = arrayOf("MainPortletConfiguration"),
        immediate = true,
        property = arrayOf(
                "com.liferay.portlet.display-category=Testing",
                "com.liferay.portlet.instanceable=true",
                //"com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.header-portlet-javascript=/js/main.js",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.display-name=reactpoc",
                "javax.portlet.name=" + MainPortletKeys.NAME_MAIN,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"),
        service = arrayOf(Portlet::class))
class MainPortlet : MVCPortlet() {

    @Reference
    private var ssrRenderer: SsrRenderer? = null

    @Throws(IOException::class, PortletException::class)
    override fun doView(renderRequest: RenderRequest?,
                        renderResponse: RenderResponse?) {

        renderRequest!!.setAttribute("ssrRenderer", ssrRenderer)

        renderRequest.setAttribute(
                MainPortletConfiguration::class.java.name,
                _mainPortletConfiguration)

        super.doView(renderRequest, renderResponse)
    }

    fun getUniversalRender(labels: Map<*, *>): String {
        return labels[_mainPortletConfiguration!!.universalRender()] as String
    }

    fun getHistoryApi(labels: Map<*, *>): String {
        return labels[_mainPortletConfiguration!!.historyApi()] as String
    }


    @Activate
    @Modified
    protected fun activate(properties: Map<Any, Any>) {
        _mainPortletConfiguration = ConfigurableUtil.createConfigurable(
                MainPortletConfiguration::class.java, properties)
    }

    @Volatile private var _mainPortletConfiguration: MainPortletConfiguration? = null

}
