package com.henry.bookrecommendationsystem.transformer.mapper.base;

import com.henry.bookrecommendationsystem.dto.base.BaseDto;
import com.henry.bookrecommendationsystem.entity.base.BaseEntity;
import org.mapstruct.MappingTarget;

/**
 * @author Henry Azer
 * @since 03/11/2022
 */
public interface BaseMapper<Entity extends BaseEntity, Dto extends BaseDto> {

    Entity dtoToEntity(Dto dto);

    Dto entityToDto(Entity entity);

    void updateEntity(Dto dto, @MappingTarget Entity entity);
}