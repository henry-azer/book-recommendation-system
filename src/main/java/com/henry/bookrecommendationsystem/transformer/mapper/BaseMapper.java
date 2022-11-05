package com.henry.bookrecommendationsystem.transformer.mapper;

import com.henry.bookrecommendationsystem.dto.base.BaseDto;
import com.henry.bookrecommendationsystem.entity.BaseEntity;
import org.mapstruct.MappingTarget;

public interface BaseMapper<Entity extends BaseEntity, Dto extends BaseDto> {

    Entity dtoToEntity(Dto dto);

    Dto entityToDto(Entity entity);

    void updateEntity(Dto dto, @MappingTarget Entity entity);
}