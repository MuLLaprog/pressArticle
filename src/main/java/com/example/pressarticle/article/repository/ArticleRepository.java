package com.example.pressarticle.article.repository;

import com.example.pressarticle.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM article WHERE describe_Text LIKE %?1% OR title_Text LIKE %?2%")
    List<Article> findArticleByDescribe(String text, String titleText);
}
