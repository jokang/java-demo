package cn.jokang.demo.es5;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author zhoukang
 * @date 2019-11-28
 */
@Service
public class LocalEsService {
//    private RestHighLevelClient client;

    @PostConstruct
    public void init() {
//        client = new RestHighLevelClient(
//            RestClient.builder(new HttpHost("localhost", 9200, "http")).build());
    }

    public void index() {
//        try {
//            MainResponse mainResponse = client.info();
//            System.out.println("clusterName: " + mainResponse.getClusterName());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
