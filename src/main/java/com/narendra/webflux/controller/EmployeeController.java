package com.narendra.webflux.controller;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import com.narendra.webflux.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class EmployeeController {

	private int rollno;
    private String name;
	
	  public RouterFunction<ServerResponse> routes() {
	        return RouterFunctions.route(RequestPredicates.GET("/employee"), this::getEmployee).
	                andRoute(RequestPredicates.POST("/employee"), this::postEmployee);
	    }
	  
	  public Mono<ServerResponse> postEmployee(ServerRequest req){
	        return  req.bodyToMono(Employee.class)
	                .flatMap(employee1 -> {
	                    this.name = employee1.getName();
	                    this.rollno = employee1.getRollno();
	                    return ServerResponse.ok().body(Mono.just(employee1),Employee.class);
	                }).switchIfEmpty(ServerResponse.badRequest().build());
	    }


	    public Mono<ServerResponse> getEmployee(ServerRequest req) {
	        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
	        Flux<Employee> mapFlux = Flux.fromStream(stream).zipWith(Flux.interval(Duration.ofSeconds(1)))
	                .map(i -> {
	                	
	                	Employee employee = new Employee();
	                    employee.setName(name);
	                    employee.setRollno(rollno);
	                    return employee;
	                });

	        return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(mapFlux,
	                Employee.class);
	    }
	    
}
