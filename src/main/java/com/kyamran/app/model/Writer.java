package com.kyamran.app.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Writer {
    private Integer id;
    private String firstName;
    private String lastName;
    @Builder.Default
    private List<Post> posts = new ArrayList<>();
}
