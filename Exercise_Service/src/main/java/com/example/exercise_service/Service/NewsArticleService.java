package com.example.exercise_service.Service;

import com.example.exercise_service.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NewsArticleService {
    ArrayList<NewsArticle> newsArticleArray =new ArrayList<>();

    public ArrayList<NewsArticle> getNewsArticle(){
        return newsArticleArray ;
    }


    public void addNewsArticle(NewsArticle newsArticle){
        newsArticleArray.add(newsArticle);
    }

    public boolean updateNewsArticle(String id,NewsArticle newsArticle){
        for(NewsArticle n : newsArticleArray){
            if(n.getId().equals(id)){
                int index = newsArticleArray.indexOf(n);
                newsArticleArray.set(index,newsArticle);
                return true;
            }
        }
        return false;
    }

public boolean deleteNewsArticle(String id){
        for(NewsArticle n :newsArticleArray){
            if(n.getId().equals(id)){
        newsArticleArray.remove(newsArticleArray.indexOf(n));
            return true;
            }
        }
        return false;
}

public boolean publishNewsArticle(String id){
        for (NewsArticle n: newsArticleArray){
            if(n.getId().equals(id)){
                n.setPublished(true);
                n.setPublishDate(LocalDate.now());
                return true;
            }
        }
        return false;
}


public ArrayList<NewsArticle> getPublish(){
        ArrayList<NewsArticle> publishArticals =new ArrayList<>();
        for(NewsArticle n: newsArticleArray){
            if(n.isPublished()){
                publishArticals.add(n);
            }
        }

        return publishArticals;
}


public ArrayList<NewsArticle> searchByCategory(String category){
        ArrayList<NewsArticle> newsByCategory=new ArrayList<>();
        for(NewsArticle n:newsArticleArray){
            if(n.getCategory().equals(category)){
                newsByCategory.add(n);
            }
        }
        if(newsByCategory.isEmpty()){
            return null;
        }
        return newsByCategory;
}


}
