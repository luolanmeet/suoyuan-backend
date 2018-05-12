package com.sy.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.cck.Article;
import com.sy.Application;
import com.sy.mapper.ArticleMapper;

/**
 * 
 * @author cck
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Import({ Application.class })
public class ArticleMapperTest {
    
    @Autowired
    ArticleMapper mapper;
    
    @Test
    public void testSave() {
        
        Article article = Article.builder()
                .url("http://abc.com")
                .title("coder")
                .author("ck")
                .content("hello world")
                .emotionVal(5D)
                .build();
        mapper.save(article);
    }
    
    @Test
    public void testGet() {
        
        Article article = mapper.getArticle(-10D);
        
        System.out.println(article.getTitle());
        System.out.println(article.getAuthor());
        System.out.println(article.getContent());
    }

}
