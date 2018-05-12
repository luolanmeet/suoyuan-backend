package com.sy.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cck.Article;

/**
 * 
 * @author cck
 */
public class Crawler {
    
    private final static String WUZHI    = "https://meiriyiwen.com";
    private final static String CATEGORY = "http://w.ihx.cc/category/meiriyiwen";
    
    public static void main(String[] args) {
        System.out.println(category());
        System.out.println(wuzhi());
    }
    
    public static List<Article> getArticle() {
        
        List<Article> list = Crawler.category();
        list.add(Crawler.wuzhi());
        return list;
    }
    
    public static List<Article> category() {
        
        Document document;
        try {
            
            document = Jsoup.connect(CATEGORY).get();
            Elements es = document.select(".post-title a");
            
            List<Article> articles = new ArrayList<>();
            
            for (Element e : es) {
                
                String url = e.attr("href");
                
                document = Jsoup.connect(url).get();
                
                String title = document.select("h1 span").first().html();
                String author = document.select(".entry p").first().html();
                
                StringBuilder content = new StringBuilder();
                Elements ps = document.select(".article_text p");
                
                ps.forEach((p) -> {
                    content.append(p.html()).append("\n");
                });
                
                Article article = Article.builder()
                        .url(url)
                        .title(title)
                        .author(author)
                        .content(content.toString())
                        .build();
                articles.add(article);
            }
            
            return articles;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static Article wuzhi() {
        
        Document document;
        try {
            
            document = Jsoup.connect(WUZHI).get();
            
            String url    = document.location();
            String title  = document.select("h1").first().html();
            String author = document.select("p span").first().html();
            
            Elements ps = document.select(".article_text p");
            
            StringBuilder content = new StringBuilder();
            ps.forEach((p) -> {
                content.append(p.html()).append("\n");
            });
            
            return Article.builder()
                    .url(url)
                    .title(title)
                    .author(author)
                    .content(content.toString())
                    .build();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}
