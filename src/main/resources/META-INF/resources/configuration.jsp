<%@ page import="com.liferay.portal.kernel.util.Constants" %>

<%@ include file="./init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>"
                           var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>"
                           var="configurationRenderURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">

    <aui:input name="<%= Constants.CMD %>" type="hidden"
               value="<%= Constants.UPDATE %>" />

    <aui:input name="redirect" type="hidden"
               value="<%= configurationRenderURL %>" />

    <aui:fieldset>

        <aui:select name="universalRender" label="Universal render"
                    value="<%= universalRender %>">
            <aui:option value="true">Yes</aui:option>
            <aui:option value="false">No</aui:option>
        </aui:select>

        <aui:select name="historyApi" label="Use HTML5 History API URLs"
                    value="<%= historyApi %>">
            <aui:option value="true">Yes</aui:option>
            <aui:option value="false">No</aui:option>
        </aui:select>

    </aui:fieldset>
    <aui:button-row>
        <aui:button type="submit"></aui:button>
    </aui:button-row>
</aui:form>
