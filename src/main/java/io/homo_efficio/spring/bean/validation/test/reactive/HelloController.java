package io.homo_efficio.spring.bean.validation.test.reactive;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author homo.efficio@gmail.com
 * created on 2021-04-24
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @PostMapping
    public Mono<ResponseEntity<HelloMessage>> save(@Valid @RequestBody HelloMessage message) {
        return Mono.just(ResponseEntity.ok(message));
    }
}
