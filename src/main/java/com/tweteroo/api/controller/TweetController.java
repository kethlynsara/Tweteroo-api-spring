package com.tweteroo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<Tweet> getByUsername(@PathVariable String USERNAME) {
        return service.findByUsername(USERNAME);
    }

    @GetMapping
    public ResponseEntity<Page<Tweet>> getTweets(@RequestParam(value = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok().body(service.findALl(page)); 
    }
    
    @PostMapping
    public void create(@RequestBody @Valid TweetDTO req) {
        service.save(req);
    }
}
