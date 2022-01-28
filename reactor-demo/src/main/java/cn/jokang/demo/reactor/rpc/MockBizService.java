package cn.jokang.demo.reactor.rpc;

import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * @author jokang
 * @date 2021/1/29
 */
public class MockBizService {
    private MockRpcService contextProvider;
    private MockRpcService recall1;
    private MockRpcService recall2;
    private MockRpcService recall3;
    private MockRpcService scorer;

    public MockBizService() {
        contextProvider = new MockRpcService("获取上下文");
        recall1 = new MockRpcService("召回1");
        recall2 = new MockRpcService("召回2");
        recall3 = new MockRpcService("召回3");
        scorer = new MockRpcService("模型打分");
    }

    public Mono<String> recommend(Integer userId) {
        return contextProvider.monoCall(userId)
            .flatMapMany(context -> Flux.from(recall1.monoCall(context))
                .concatWith(recall2.monoCall(context))
                .concatWith(recall3.monoCall(context))
            )
            .collectList()
            .map(data -> StringUtils.join(data, ";\n"))
            .flatMap(data -> scorer.monoCall(data))
            .timeout(Duration.ofSeconds(3));
    }

}
