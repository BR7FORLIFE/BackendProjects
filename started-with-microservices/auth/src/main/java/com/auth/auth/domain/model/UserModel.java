package com.auth.auth.domain.model;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public final class UserModel {
    private final UUID id;
    private final String email;
    private final String username;
    private final String password;
    private final Set<String> rols;
    private final Instant createAt;
    private final Instant updateAt;

    public static UserModel createNew(UUID id, String email, String username, String password, Set<String> rols,
            Instant createAt, Instant updateAt) {
        return new UserModel(id, email, username, password, rols, createAt, updateAt);
    }

    public static UserModel createDraft(String email, String username, String password) {
        return new UserModel(UUID.randomUUID(), email, username, password, Set.of("USER"), Instant.now(),
                Instant.now());
    }

    public static UserModel createDetails(UUID id, String username) {
        return new UserModel(id, null, username, null, null, null, null);
    }

    public UserModel(UUID id, String email, String username, String password, Set<String> rols, Instant createAt,
            Instant updateAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.rols = rols;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRols() {
        return rols;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

}
