package com.vibenet.vibenet.services;

import com.vibenet.vibenet.models.Reel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReelService {
    public Reel createReel(Reel reel,Integer userId) throws Exception;
    public List<Reel>findAll();
    public List<Reel>findUserReel(Integer userId) throws Exception;
}
