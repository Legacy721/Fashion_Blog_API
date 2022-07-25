package com.legacy.Fashion.blog.model.pageCriteria;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PostPage {

    private int pageNumber = 0;
    private int pageSize = 3;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "title";

}