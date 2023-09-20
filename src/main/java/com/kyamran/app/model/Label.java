package com.kyamran.app.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Label {
    private Integer id;
    private String name;
    private Date created;
    private Date updated;
    private PostStatus status;
}