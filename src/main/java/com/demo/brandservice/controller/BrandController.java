package com.demo.brandservice.controller;

import com.demo.brandservice.dto.BrandDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Brand API")
public interface BrandController {
    @ApiOperation("Add new data")
    public BrandDTO save(@RequestBody BrandDTO brand);

    @ApiOperation("Find by Id")
    public BrandDTO findById(@PathVariable("id") Integer id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Integer id);

    @ApiOperation("Find all data")
    public List<BrandDTO> list();

    @ApiOperation("Pagination request")
    public Page<BrandDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public BrandDTO update(@RequestBody BrandDTO dto, @PathVariable("id") Integer id);
}