<%@ page contentType="text/html; charset=UTF-8" import="com.example.liferay.reactpoc.portlet.MainPortletConfiguration" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.security.auth.AuthTokenUtil" %>
<%@ page import="com.example.liferay.reactpoc.ssr.SsrRenderer" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
    SsrRenderer ssrRenderer = (SsrRenderer) renderRequest.getAttribute("ssrRenderer");

    MainPortletConfiguration exampleConfiguration =
            (MainPortletConfiguration)
                    renderRequest.getAttribute(MainPortletConfiguration.class.getName());

    String universalRender = "false";
    String historyApi = "false";

    if (Validator.isNotNull(exampleConfiguration)) {
        universalRender =
                portletPreferences.getValue(
                        "universalRender", exampleConfiguration.universalRender());

        historyApi =
                portletPreferences.getValue(
                        "historyApi", exampleConfiguration.historyApi());
    }

    String authToken = AuthTokenUtil.getToken(request);
%>
