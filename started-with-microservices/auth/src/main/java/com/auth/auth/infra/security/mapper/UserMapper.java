package com.auth.auth.infra.security.mapper;

import com.auth.auth.domain.model.UserModel;
import com.auth.auth.infra.security.entity.AuthDocument;

public class UserMapper {

    public static UserModel toDomain(AuthDocument document) {
        return UserModel.createNew(
                document.getId(),
                document.getEmail(),
                document.getUsername(),
                document.getPassword(),
                document.getRols(),
                document.getCreateAt(),
                document.getUpdateAt());
    }

    public static AuthDocument toEntity(UserModel model) {
        AuthDocument document = new AuthDocument();

        document.setId(model.getId());
        document.setEmail(model.getEmail());
        document.setUsername(model.getUsername());
        document.setPassword(model.getPassword());
        document.setRols(model.getRols());
        document.setCreateAt(model.getCreateAt());
        document.setUpdateAt(model.getUpdateAt());

        return document;
    }
}
