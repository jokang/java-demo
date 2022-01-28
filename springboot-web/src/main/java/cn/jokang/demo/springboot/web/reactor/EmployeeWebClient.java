package cn.jokang.demo.springboot.web.reactor;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author jokang
 * @date 2021/1/26
 */
public class EmployeeWebClient {

    WebClient client = WebClient.create("https://baidu.com");

    private void work() {
        Mono<String> employeeMono = client.get()
            .uri("/", "1")
            .retrieve()
            .bodyToMono(String.class);

        employeeMono.subscribe(System.out::println);

    }

    public static void main(String[] args) {
        EmployeeWebClient c = new EmployeeWebClient();
        c.work();
    }
}