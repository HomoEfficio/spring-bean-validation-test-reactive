package io.homo_efficio.spring.bean.validation.test.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


@WebFluxTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private WebTestClient wtc;

    @Test
    void newHello() {
        wtc.post().uri("/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(
                    new HelloMessage(
                        null,
                        "Homo",
                        "Hi all~ I am Homo Efficio"
                    )
                )
                .exchange()
                .expectBody(HelloMessage.class)
                .consumeWith(it -> {
                    HelloMessage helloMessage = it.getResponseBody();
                    String username = Objects.requireNonNull(helloMessage).getUsername();
                    System.out.println("msg.username: " + username);
                    String msg = Objects.requireNonNull(helloMessage).getMsg();
                    System.out.println("msg.msg: " + msg);
                    assertThat(username).isEqualTo("Homo");
                    assertThat(msg).isEqualTo("Hi all~ I am Homo Efficio");
                });
    }
}
