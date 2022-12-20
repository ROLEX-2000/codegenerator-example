package com.demo.brandservice.dao;

import com.demo.brandservice.entity.Brand;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand, Integer> {
}