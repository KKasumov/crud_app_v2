package com.kyamran.app.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class Post {
    private Integer id;
    private String content;
    private Date created;
    private Date updated;
    private List<Label> labels;
    private PostStatus status;
}
