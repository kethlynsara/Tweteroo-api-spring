package com.tweteroo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    public Page<Tweet> findALl(Pageable pageable) {
        int page = Integer.parseInt(pageable.getPageParameter());
        int size = 5;
        PageRequest pageRequest = PageRequest.of(page, size);
        
        return new PageImpl<>(
            repository.findAll(),
            pageRequest, size);
    }

    public void save(TweetDTO dto) {
        repository.save(new Tweet(dto));
    }
}
