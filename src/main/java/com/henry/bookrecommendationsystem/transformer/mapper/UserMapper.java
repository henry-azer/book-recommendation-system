package com.henry.bookrecommendationsystem.transformer.mapper;

import com.henry.bookrecommendationsystem.dto.UserDto;
import com.henry.bookrecommendationsystem.entity.User;
import com.henry.bookrecommendationsystem.transformer.UserTransformer;
import com.henry.bookrecommendationsystem.transformer.mapper.configuration.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserTransformer.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface UserMapper extends BaseMapper<User, UserDto> {
}
