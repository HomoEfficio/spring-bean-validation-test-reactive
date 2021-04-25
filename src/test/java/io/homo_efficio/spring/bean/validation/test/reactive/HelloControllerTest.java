package io.homo_efficio.spring.bean.validation.test.reactive;

import org.junit.jupiter.api.DisplayName;
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


    @DisplayName("When validation fails, assertion is performed against null instead of actual value, which is not correct.")
    @Test
    void test_with_invalid_value() {
        String EXPECTED_USERNAME = "Homo";  // username has @Size(max = 3)
        wtc.post().uri("/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(
                        new HelloMessage(
                                null,
                                EXPECTED_USERNAME,
                                "Hi all~ I am " + EXPECTED_USERNAME + " Efficio"
                        )
                )
                .exchange()
                .expectBody(HelloMessage.class)
                .consumeWith(response -> {
                    HelloMessage helloMessage = response.getResponseBody();

                    String username = Objects.requireNonNull(helloMessage).getUsername();
                    String msg = Objects.requireNonNull(helloMessage).getMsg();

                    System.out.println("message.username: " + username);
                    System.out.println("message.msg: " + msg);

                    assertThat(username).isEqualTo(EXPECTED_USERNAME);
                    assertThat(msg).isEqualTo("Hi all~ I am " + EXPECTED_USERNAME + " Efficio");
                });
    }

}
