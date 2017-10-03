package com.example.liferay.reactpoc.portlet;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(id = "MainPortletConfiguration")
public interface MainPortletConfiguration {
    @Meta.AD(
        deflt = "false",
        required = false
    )
    public String universalRender();

    @Meta.AD(
        deflt = "false",
        required = false
    )
    public String historyApi();
}
