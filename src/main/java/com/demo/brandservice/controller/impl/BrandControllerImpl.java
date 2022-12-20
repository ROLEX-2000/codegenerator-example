package com.demo.brandservice.controller.impl;

import com.demo.brandservice.controller.BrandController;
import com.demo.brandservice.dto.BrandDTO;
import com.demo.brandservice.entity.Brand;
import com.demo.brandservice.mapper.BrandMapper;
import com.demo.brandservice.service.BrandService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/brand")
@RestController
public class BrandControllerImpl implements BrandController {
    private final BrandService brandService;
    private final BrandMapper brandMapper;

    public BrandControllerImpl(BrandService brandService, BrandMapper brandMapper) {
        this.brandService = brandService;
        this.brandMapper = brandMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BrandDTO save(@RequestBody BrandDTO brandDTO) {
        Brand brand = brandMapper.asEntity(brandDTO);
        return brandMapper.asDTO(brandService.save(brand));
    }

    @Override
    @GetMapping("/{id}")
    public BrandDTO findById(@PathVariable("id") Integer id) {
        Brand brand = brandService.findById(id).orElse(null);
        return brandMapper.asDTO(brand);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        brandService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<BrandDTO> list() {
        return brandMapper.asDTOList(brandService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<BrandDTO> pageQuery(Pageable pageable) {
        Page<Brand> brandPage = brandService.findAll(pageable);
        List<BrandDTO> dtoList = brandPage
                .stream()
                .map(brandMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, brandPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public BrandDTO update(@RequestBody BrandDTO brandDTO, @PathVariable("id") Integer id) {
        Brand brand = brandMapper.asEntity(brandDTO);
        return brandMapper.asDTO(brandService.update(brand, id));
    }
}