package com.sy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cck.Article;
import com.sy.crawler.Crawler;
import com.sy.emotion.judge.impl.EmotionJudge;
import com.sy.emotion.object.Emotions;
import com.sy.mapper.ArticleMapper;
import com.sy.word.segmentation.impl.MaximumMatching;

/**
 * 定时爬取文章
 * @author cck
 */
@Configuration
@ComponentScan("com.sy.service.CrawlerSchedule")
@EnableScheduling
public class CrawlerSchedule {
    
    @Autowired
    private ArticleMapper articleMapper;
    
    @Autowired
    private MaximumMatching matching;
    
    @Autowired
    private EmotionJudge judge;
    
    /**
     * 每天六点爬一次文章
     */
//    @Scheduled(cron = "0 0 6 ? * *")
    // @Scheduled(fixedRate = 86400000)
    public void crawler() {
        
        List<Article> articles = Crawler.getArticle();
        
        for (Article article : articles) {
            
            String content = article.getContent();
            Emotions emotions = judge.judge(matching.seg(content));
            article.setEmotionVal(emotions.getEmotionVal());
            articleMapper.save(article);
        }
        
    }
    
}
