package com.syncoder.webbazaar.repositories;

import com.syncoder.webbazaar.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
