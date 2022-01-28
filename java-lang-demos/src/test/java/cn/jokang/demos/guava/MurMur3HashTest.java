package cn.jokang.demos.guava;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.primitives.Bytes;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author zhoukang
 * @date 2020/9/27
 */
public class MurMur3HashTest {
    @Test
    public void testHashString() {
        HashFunction hashFunc = Hashing.murmur3_128();
        HashCode hashCode = hashFunc.hashString("123", StandardCharsets.UTF_8);
        System.out.println(hashCode.asInt());
        System.out.println(hashCode.asInt() % 100 + 1);
    }
}
