package cn.jokang.demos.tools;

/**
 * @author zhoukang
 * @date 2019-06-05
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * https://km.sankuai.com/page/158571913
 * <p>
 * ClassLocationUtils.where(org.objectweb.asm.ClassVisitor.class)
 * 查询类对应的JAR
 *
 * @author chenxh
 * @date 13-10-31
 */
public class DebugTool {

    /**
     * 获取类所有的路径
     *
     * @param cls 类全名
     * @return 类所在的jar包
     */
    public static String where(final Class cls) {
        if (cls == null) {
            throw new IllegalArgumentException("null input: cls");
        }
        URL result = null;
        final String clsAsResource = cls.getName().replace('.', '/').concat(".class");
        final ProtectionDomain pd = cls.getProtectionDomain();
        if (pd != null) {
            final CodeSource cs = pd.getCodeSource();
            if (cs != null) result = cs.getLocation();
            if (result != null) {
                if ("file".equals(result.getProtocol())) {
                    try {
                        if (result.toExternalForm().endsWith(".jar") ||
                            result.toExternalForm().endsWith(".zip"))
                            result = new URL("jar:".concat(result.toExternalForm())
                                .concat("!/").concat(clsAsResource));
                        else if (new File(result.getFile()).isDirectory())
                            result = new URL(result, clsAsResource);
                    } catch (MalformedURLException ignore) {
                    }
                }
            }
        }
        if (result == null) {
            final ClassLoader clsLoader = cls.getClassLoader();
            result = clsLoader != null ?
                clsLoader.getResource(clsAsResource) :
                ClassLoader.getSystemResource(clsAsResource);
        }
        return result.toString();
    }

}
