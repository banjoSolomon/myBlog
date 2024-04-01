package org.solomon.services;

import org.solomon.dtos.PostRequest;
import org.solomon.model.Post;

public interface PostService {
      Post createPostWith(PostRequest postRequest);

}
