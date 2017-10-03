package com.example.liferay.reactpoc.ssr;

import org.osgi.service.component.annotations.Component;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStreamReader;
import java.io.Reader;

@Component(immediate = true, service = SsrRenderer.class)
public class SsrRenderer {

    private ThreadLocal<ScriptEngine> engineHolder = ThreadLocal.withInitial(() -> {
        ScriptEngine nashorn = new ScriptEngineManager(null).getEngineByName("nashorn");
        try {
            nashorn.eval(read("/META-INF/resources/dist/server.js"));
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
        return nashorn;
    });

    public String render() {
        try {

            Object html =
                ((Invocable) engineHolder.get()).invokeFunction("renderApp");
            return String.valueOf(html);
        } catch (Exception e) {
            throw new IllegalStateException("failed to render in JavaScript", e);
        }
    }

    private Reader read(String path) {
        return new InputStreamReader(getClass().getClassLoader().getResourceAsStream(path));
    }

}