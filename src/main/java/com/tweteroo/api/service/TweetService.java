package com.tweteroo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.model.Tweet;
import com.tweteroo.api.repository.TweetRepository;

@Service
public class TweetService {
    
    @Autowired
    private TweetRepository repository;

    public List<Tweet> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Page<Tweet> findALl(int page) {
        int size  = 5;
        Pageable pageable = PageRequest.of(page, size);
        
        return repository.findAll(pageable);
    }

    public void save(TweetDTO dto) {
        repository.save(new Tweet(dto));
    }
}
