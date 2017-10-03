<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectWriter" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ include file="./init.jsp" %>
<%
    String appAsString = "";

    ObjectWriter writer = (new ObjectMapper()).writer();

    Map<String, Object> _portletConfig = new HashMap<>();
    _portletConfig.put("authToken", authToken);
    _portletConfig.put("historyApi", historyApi);

    HttpServletRequest r = PortalUtil.getHttpServletRequest(renderRequest);
    String path = PortalUtil.getOriginalServletRequest(r).getRequestURI();

    if ("true".equals(universalRender)) {
        appAsString = ssrRenderer.render(path);
    }
%>
<div id="react-app"><%= appAsString %></div>
<script type="text/javascript">
    // TODO: note CSP inline issues here with nonce
    window.__INITIAL_STATE__ = {
        config: <%= writer.writeValueAsString(_portletConfig) %>
    }
</script>
<!-- Change this primitive cache busting to hash based -->
<script src="<%=request.getContextPath()%>/dist/vendor.js?<%= new java.util.Date().getTime()%>"></script>
<script src="<%=request.getContextPath()%>/dist/app.js?<%= new java.util.Date().getTime()%>"></script>