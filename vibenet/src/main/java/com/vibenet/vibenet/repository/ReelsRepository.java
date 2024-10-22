package com.vibenet.vibenet.repository;

import com.vibenet.vibenet.models.Reel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reel,Integer> {
    public List<Reel> findByUserId(Integer userId);
}
