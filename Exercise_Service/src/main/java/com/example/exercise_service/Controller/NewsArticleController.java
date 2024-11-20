package com.example.exercise_service.Controller;

import com.example.exercise_service.ApiResponse.ApiResponse;
import com.example.exercise_service.Model.NewsArticle;
import com.example.exercise_service.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/NewsArticle")
@RequiredArgsConstructor
public class NewsArticleController {
    private final NewsArticleService newsArticleService;

    @GetMapping("/get")
    public ResponseEntity getNews(){
        return ResponseEntity.status(200).body(newsArticleService.getNewsArticle());
    }


    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@RequestBody @Valid NewsArticle newsArticle,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        newsArticleService.addNewsArticle(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse("News Article added"));

    }



    @PutMapping("/update/{id}")
    public ResponseEntity updateNewsArticle(@PathVariable String id , @RequestBody @Valid NewsArticle newsArticle, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if(newsArticleService.updateNewsArticle(id,newsArticle)){
            return ResponseEntity.status(200).body(new ApiResponse("news Article updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNewsArticle(@PathVariable String id){
        if(newsArticleService.deleteNewsArticle(id)){
        return ResponseEntity.status(200).body(new ApiResponse("News Article deleted"));}

        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
    }

@PutMapping("publish/{id}")
public ResponseEntity publishNewsArticle(@PathVariable String id){
       if(newsArticleService.publishNewsArticle(id)){
           return ResponseEntity.status(200).body(new ApiResponse("News Article published"));
       }
       return ResponseEntity.status(400).body(new ApiResponse("ID Not found"));
}

@GetMapping("/getPublish")
public ResponseEntity getPublish(){
        return ResponseEntity.status(200).body(newsArticleService.getPublish());
}

@GetMapping("/searchByCategory/{category}")
public ResponseEntity  searchByCategory(@PathVariable String category){
    ArrayList newsByCategory =newsArticleService.searchByCategory(category);
        if(newsByCategory!=null){
            return ResponseEntity.status(200).body(newsByCategory);
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category not found, Category must be either politics, sports or technology only."));
}


}
