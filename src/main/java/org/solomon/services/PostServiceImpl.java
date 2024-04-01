package org.solomon.services;

import org.solomon.dtos.PostRequest;
import org.solomon.model.Post;
import org.solomon.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.solomon.utilits.Mapper.map;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;
    @Override
    public Post createPostWith(PostRequest postRequest) {
        Post newPost = map(postRequest);
        return postRepository.save(newPost);

    }
}
