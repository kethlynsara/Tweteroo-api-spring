package com.tweteroo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.model.Tweet;
import com.tweteroo.api.service.TweetService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    @Autowired
    private TweetService service;

    @GetMapping("/{USERNAME}") 
    private List<Tweet> getByUsername(@PathVariable String username) {
        return service.findByUsername(username);
    }

    @GetMapping
    private Page<Tweet> getTweets(@PageableDefault(page = 0, size = 5) Pageable page) {
        return service.findALl(page);
    }
    
    @PostMapping
    public void create(@RequestBody @Valid TweetDTO req) {
        service.save(req);
    }
}
