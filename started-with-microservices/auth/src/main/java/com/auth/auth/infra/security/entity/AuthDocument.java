package com.auth.auth.infra.security.entity;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "auth")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthDocument {
    @Id
    private UUID id;
    private String email;
    private String username;
    private String password;
    private Set<String> rols;
    private Instant createAt;
    private Instant updateAt;
}
