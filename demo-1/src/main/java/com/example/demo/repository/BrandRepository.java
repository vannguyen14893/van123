package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{

}
