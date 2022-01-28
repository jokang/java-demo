package cn.jokang.demo.reactor.rpc;

import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * @author jokang
 * @date 2021/1/29
 */
public class MockRpcService {
    private String name;

    public MockRpcService(String name) {
        this.name = name;
    }

    private String invokeRpc(Integer param) {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "[" + name + " RPC done with param=" + param + "]";
    }

    public Mono<String> monoCall(Integer param) {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> this.invokeRpc(param));
        return Mono.fromFuture(cf);
    }

    private String invokeRpc(String param) {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "[" + name + " RPC done with param=" + param + "]";
    }


    public Mono<String> monoCall(String param) {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> this.invokeRpc(param));
        return Mono.fromFuture(cf);
    }
}
