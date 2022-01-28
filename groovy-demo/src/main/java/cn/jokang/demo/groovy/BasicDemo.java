package cn.jokang.demo.groovy;

import org.codehaus.groovy.jsr223.GroovyScriptEngineImpl;

import javax.script.Bindings;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author zhoukang
 * @date 2020-06-28
 */
public class BasicDemo {
    public static void main(String[] args) {
        final GroovyScriptEngineImpl GROOVY_ENGINE = (GroovyScriptEngineImpl) (new ScriptEngineManager()).getEngineByName("groovy");
        try {
            Bindings bindings = GROOVY_ENGINE.createBindings();
            bindings.put("test", "test value");
            Object ret = GROOVY_ENGINE.eval("return test", bindings);
            System.out.println(ret);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
