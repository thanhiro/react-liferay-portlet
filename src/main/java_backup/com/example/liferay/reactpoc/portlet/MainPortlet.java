package com.example.liferay.reactpoc.portlet;

import com.example.liferay.reactpoc.constants.MainPortletKeys;
import com.example.liferay.reactpoc.ssr.SsrRenderer;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author akonovalov
 */
@Component(
        configurationPid = "MainPortletConfiguration",
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Testing",
                "com.liferay.portlet.instanceable=true",
                //"com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.header-portlet-javascript=/js/main.js",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.display-name=reactpoc",
                "javax.portlet.name=" + MainPortletKeys.NAME_MAIN,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class MainPortlet extends MVCPortlet {

    @Reference
    SsrRenderer ssrRenderer;

    @Override
    public void doView(RenderRequest renderRequest,
            RenderResponse renderResponse) throws IOException, PortletException {

        renderRequest.setAttribute("ssrRenderer", ssrRenderer);

        renderRequest.setAttribute(
                MainPortletConfiguration.class.getName(),
                _mainPortletConfiguration);

        super.doView(renderRequest, renderResponse);
    }

    public String getUniversalRender(Map labels) {
        return (String) labels.get(_mainPortletConfiguration.universalRender());
    }

    public String getHistoryApi(Map labels) {
        return (String) labels.get(_mainPortletConfiguration.historyApi());
    }


    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _mainPortletConfiguration = ConfigurableUtil.createConfigurable(
                MainPortletConfiguration.class, properties);
    }

    private volatile MainPortletConfiguration _mainPortletConfiguration;

}
