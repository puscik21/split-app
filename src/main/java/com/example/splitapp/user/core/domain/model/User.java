package com.example.splitapp.user.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private String login; // TODO: probably final
    private String password;
}
