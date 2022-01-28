package cn.jokang.demo.reactor;

import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Optional;

/**
 * @author jokang`
 * @date 2021/1/25
 */
public class ContextTest {
    static final String HTTP_CORRELATION_ID = "reactive.http.library.correlationId";

    Mono<Tuple2<Integer, String>> doPut(String url, Mono<String> data) {
        Mono<Tuple2<String, Optional<Object>>> dataAndContext =
            data.zipWith(Mono.deferContextual(c ->
                Mono.just(c.getOrEmpty(HTTP_CORRELATION_ID)))
            );

        return dataAndContext.<String>handle((dac, sink) -> {
            if (dac.getT2().isPresent()) {
                sink.next("PUT <" + dac.getT1() + "> sent to " + url +
                    " with header X-Correlation-ID = " + dac.getT2().get());
            }
            else {
                sink.next("PUT <" + dac.getT1() + "> sent to " + url);
            }
            sink.complete();
        })
            .map(msg -> Tuples.of(200, msg));
    }

    @Test
    public void userCode() {
        doPut("www.example.com", Mono.just("Walter"))
            .contextWrite(Context.of(HTTP_CORRELATION_ID, "2-j3r9afaf92j-afkaf"));
    }
}
