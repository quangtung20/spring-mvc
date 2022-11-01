package com.quangtung.springbootblogwebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;
    @NotEmpty
    private String title;

    private String url;

    @NotEmpty
    private String content;

    @NotEmpty
    private String shortDescription;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private List<CommentDto> comments;
}
