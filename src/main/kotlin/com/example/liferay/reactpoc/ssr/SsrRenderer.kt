package com.example.liferay.reactpoc.ssr

import org.osgi.service.component.annotations.Component
import java.io.InputStreamReader
import java.io.Reader
import javax.script.Invocable
import javax.script.ScriptEngineManager

@Component(immediate = true, service = arrayOf(SsrRenderer::class))
class SsrRenderer {

    private val engineHolder = ThreadLocal.withInitial {
        val nashorn = ScriptEngineManager(null).getEngineByName("nashorn")
        nashorn.eval(read("/META-INF/resources/dist/server.js"))
        nashorn
    }

    fun render(): String {
        try {
            val html = (engineHolder.get() as Invocable).invokeFunction("renderApp")
            return html.toString()
        } catch (e: Exception) {
            throw IllegalStateException("failed to render in JavaScript", e)
        }

    }

    private fun read(path: String): Reader {
        return InputStreamReader(javaClass.classLoader.getResourceAsStream(path))
    }

}