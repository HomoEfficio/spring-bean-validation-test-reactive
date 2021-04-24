package io.homo_efficio.spring.bean.validation.test.reactive;

import lombok.*;

import javax.validation.constraints.Size;

/**
 * @author homo.efficio@gmail.com
 * created on 2021-04-24
 */
@Data
public class HelloMessage {

    private String id;
    @Size(max = 3)
    private String username;
    private String msg;

    public HelloMessage() {
    }

    public HelloMessage(String id, String username, String msg) {
        this.id = id;
        this.username = username;
        this.msg = msg;
    }
}
