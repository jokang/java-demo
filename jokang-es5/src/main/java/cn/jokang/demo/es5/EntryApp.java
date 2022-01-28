package cn.jokang.demo.es5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhoukang
 * @date 2020-03-20
 */
@SpringBootApplication
//@ShellComponent
public class EntryApp {
    @Autowired
    private LocalEsService localEsService;

    @Autowired
    private ValidatorDemoService validatorDemoService;

    public static void main(String[] args) {
        SpringApplication.run(EntryApp.class, args);
    }

//    @ShellMethod("Hello")
//    public String hello(@ShellOption String name) {
//        // invoke service
//        return "Hello " + name;
//    }
//
//    @ShellMethod("cluster")
//    public void cluster() {
//        localEsService.index();
//    }
//
//    @ShellMethod("validate")
//    public void validate() {
////        validatorDemoService.validate();
//        Person p = new Person();
//        p.setAge(1000);
//
//        validatorDemoService.doWork(p);
//    }
//
//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//        return new MethodValidationPostProcessor();
//    }
//
//    @Bean
//    public LocalValidatorFactoryBean validator() {
//        return new LocalValidatorFactoryBean();
//    }
}
