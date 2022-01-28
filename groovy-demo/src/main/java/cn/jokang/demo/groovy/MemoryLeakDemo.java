package cn.jokang.demo.groovy;

import org.codehaus.groovy.jsr223.GroovyScriptEngineImpl;

import javax.script.Bindings;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author zhoukang
 * @date 2020-06-28
 */
public class MemoryLeakDemo {
    static final GroovyScriptEngineImpl GROOVY_ENGINE =
        (GroovyScriptEngineImpl) (new ScriptEngineManager()).getEngineByName("groovy");

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10000; i++) {
                try {
                    Bindings bindings = GROOVY_ENGINE.createBindings();
                    bindings.put("test", "test value");
                    // 用"这是一段文字"去测试, GroovyScriptEngineImpl.getScriptClass能正取地cache住类
                    // Object ret = GROOVY_ENGINE.eval("这是一段文字", bindings);
                    // 抛异常导致Groovy缓存class失败, ClassLoader.parallelLockMap 膨胀
                    Object ret = GROOVY_ENGINE.eval("\"建议折扣商品数量小于8个，保证用户下单体验；\"", bindings);
                    System.out.println(ret);
                } catch (ScriptException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Done one loop " + j);
            Thread.sleep(1000);
        }
    }
}
