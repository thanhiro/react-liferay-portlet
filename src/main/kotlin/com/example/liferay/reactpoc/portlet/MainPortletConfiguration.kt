package com.example.liferay.reactpoc.portlet

import aQute.bnd.annotation.metatype.Meta

@Meta.OCD(id = "MainPortletConfiguration")
interface MainPortletConfiguration {
    @Meta.AD(deflt = "false", required = false)
    fun universalRender(): String

    @Meta.AD(deflt = "false", required = false)
    fun historyApi(): String
}
