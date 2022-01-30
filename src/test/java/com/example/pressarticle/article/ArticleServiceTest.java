package com.example.pressarticle.article;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    ArticleRepository articleRepository;

    @InjectMocks
    ArticleService articleService;

//    @DisplayName("Should return all Articles")
//    @Test
//    void findAllArticleTransfer() {
//        //given
//        List<Article> list = new ArrayList<>();
//        list.add(new Article(1L, "NewWorld", LocalDate.of(1022,9,17),
//                        "World", "Allan Balkier", new Timestamp(100, 10, 11, 0, 0, 0, 0)));
//        list.add(new Article(2L, "NewWorld", LocalDate.of(1022,9,17),
//                "World", "Allan Balkier", new Timestamp(100, 10, 11, 0, 0, 0, 0)));
//
//        //Mockito.when(articleRepository.findAll()).thenReturn(list);
//         given(articleRepository.findAll()).willReturn(list);//jezeli ktosc zawola metode findAll na articlerepository to ma zwrócic list
//
//        //when
//        List<Article> result = articleService.sortAllArticleTransfer();
//
//        //then
//        assertEquals(list.size(), result.size());
//    }

    @DisplayName("Should return sort Articles")
    @Test
    void sortAllTransfer() {
        //given
        List<Article> list = new ArrayList<>();
        list.add(new Article(1L, "Machines replace human","Machines", LocalDate.of(2022,9,17),
                "World", "Allan Balkier", new Timestamp(100, 10, 11, 0, 0, 0, 0)));
        list.add(new Article(2L, "Robots are a system computer","Robots", LocalDate.of(1022,8,11),
                "World", "Allan Balkier", new Timestamp(100, 10, 11, 0, 0, 0, 0)));

        list.sort(Comparator.comparing(Article::getDataPublication).reversed());


        //Mockito.when(articleRepository.findArticleByQuery()).thenReturn(list);
        Mockito.when(articleRepository.findAll(Mockito.any())).thenReturn(list);

        //when
        Page<Article> articleList =  articleService.sortAllArticleTransfer(0, 20);

        articleList.forEach(System.out::println);

        //then
        assertEquals(list.get(0), articleList.get());
    }
    @DisplayName("Should return Articles for id")
    @Test
    void articleIdTransfer() {
        //given
        List<Article> article = new ArrayList<>();
        article.add(new Article(1L, "NewWorld", "NewTitle",LocalDate.of(2022,9,17),
                "World", "Allan Balkier", new Timestamp(100, 10, 11, 0, 0, 0, 0)));

        Mockito.when(articleRepository.findArticleById(Mockito.anyLong())).thenReturn(article);

        //when
        List<Article> result= articleService.articleIdTransfer(1L);

        //then
        assertEquals(article.size(), result.size());
    }

    @DisplayName("Should return Articles for text")
    @Test
    void articleFindDescriptionTransfer() {
        //given
        List<Article> article = new ArrayList<>();
        article.add(new Article(1L, "NewWorld", "NewDescription", LocalDate.of(2022,9,17),
                "World", "Allan Balkier", new Timestamp(100, 10, 11, 0, 0, 0, 0)));

       // Mockito.when(articleRepository.findArticleByDescribe(Mockito.anyString())).thenReturn(article);
        Mockito.when(articleRepository.findArticleByDescribe("NewWorld", "NenWorld")).thenReturn(article);

        //when
        List<Article> result= articleService.articleDescribeTransfer("NewWorld", "World");

        //then
        assertEquals(article.size(), result.size());
    }

    @DisplayName("Should add Article")
    @Test
    void addArticleTransfer() {
        //given
        Article article = new Article(1L, "NewWorld", "NewTitleWorld",LocalDate.of(2022,9,17),
                "World", "Allan Balkier", new Timestamp(100, 10, 11, 0, 0, 0, 0));

        Mockito.when(articleRepository.save(article)).thenReturn(article);

        //when
        Article result = articleService.addArticleTransfer(article);

        //then
        assertEquals(article.getId(), result.getId());
    }

    @DisplayName("Should update Article")
    @Test
    void updateArticleTransfer() {
        //given
        Article article = new Article(1L, "updateNewWorld","updateText", LocalDate.of(2022,9,17),
                "updateWorld", "Update Allan Balkier", new Timestamp(100, 10, 11, 0, 0, 0, 0));

        Mockito.when(articleRepository.save(article)).thenReturn(article);

        //when
        Article result = articleService.addArticleTransfer(article);

        //then
        assertEquals(article.getDescribeText(), result.getDescribeText());
        assertEquals(article.getNameMagazine(), result.getNameMagazine());
    }

    @DisplayName("Should delete Article")
    @Test
    void deleteArticleTransfer() {
        //given
        var SOME_ID = 1L;
        var BAD_ID = 2L;

        //when
        articleService.deleteArticleTransfer(SOME_ID);

        //then
        Mockito.verify(articleRepository).deleteById(SOME_ID);
    }
}