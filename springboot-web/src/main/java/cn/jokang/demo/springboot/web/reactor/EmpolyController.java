package cn.jokang.demo.springboot.web.reactor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmpolyController {

//    private final EmployeeRepository employeeRepository;
//
//    // constructor...
//
//    @GetMapping
//    private Flux<Employee> getAllEmployees() {
//        return employeeRepository.findAllEmployees();
//    }
//
//    @GetMapping("/{id}")
//    private Mono<Employee> getEmployeeById(@PathVariable String id) {
//        return employeeRepository.findEmployeeById(id);
//    }

}