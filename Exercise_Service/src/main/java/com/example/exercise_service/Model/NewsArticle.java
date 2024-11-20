package com.example.exercise_service.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {
    @NotEmpty(message = "Empty ID")
    private String id;
    @NotEmpty(message = "Empty title")
    @Size(max = 100, message = "Maximum length of 100 characters.")
    private String title;
    @NotEmpty(message = "Empty author")
    @Size(min = 5,message = "Must be more than 4 characters. ")
    @Size(max = 20,message = "Maximum length of 20 characters.")
    private String author;
    @NotEmpty(message = "Empty content")
    @Size(min = 201,message = "Must be more than 200 characters.  ")
    private String content;
    @NotEmpty(message = "Empty category")
    @Pattern(regexp = "politics|sports|technology",message = "Category must be either politics, sports or technology only.")
    private String category;
    @NotEmpty(message ="Empty image URL")
    private String imageUrl;
    private boolean isPublished=false ;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate=null;
}
