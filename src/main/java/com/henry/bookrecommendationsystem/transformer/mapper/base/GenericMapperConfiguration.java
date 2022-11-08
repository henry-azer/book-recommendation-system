package com.henry.bookrecommendationsystem.transformer.mapper.base;

import com.henry.bookrecommendationsystem.dto.base.BaseDto;
import com.henry.bookrecommendationsystem.entity.base.BaseEntity;
import org.mapstruct.*;

/**
 * @author Henry Azer
 * @since 03/11/2022
 */
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface GenericMapperConfiguration {

    @Mappings({
            @Mapping(target = "creationDate", source = "createdDate"),
            @Mapping(target = "lastModificationDate", source = "modifiedDate")
    })
    BaseDto anyEntityToDto(BaseEntity entity);

    @Mappings({
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
    })
    BaseEntity anyDtoToEntity(BaseDto dto);
}