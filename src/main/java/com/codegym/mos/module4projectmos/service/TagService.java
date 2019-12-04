package com.codegym.mos.module4projectmos.service;

import com.codegym.mos.module4projectmos.model.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TagService {
    Optional<Tag> findById(Long id);

    Tag findByName(String name);

    Page<Tag> findAll(Pageable pageable);

    Page<Tag> findAllByNameContaining(String name, Pageable pageable);

    void save(Tag tag);

    void deleteById(Long id);
}
