package com.project.trs.mapper;

import com.project.trs.model.User;
import com.project.trs.response.CreateUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Named("uuidToString")
    static String UUIDToSting(UUID id) {
        if (id == null) {
            return null;
        }

        return id.toString();
    }

    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
    CreateUserResponse toResponse(User user);
}
