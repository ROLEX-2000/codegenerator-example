package com.demo.brandservice.mapper;

import com.demo.brandservice.dto.BrandDTO;
import com.demo.brandservice.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface BrandMapper extends GenericMapper<Brand, BrandDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Brand asEntity(BrandDTO dto);
}