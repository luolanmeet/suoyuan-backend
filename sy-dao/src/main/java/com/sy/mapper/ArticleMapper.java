package com.sy.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import com.cck.Article;
import com.sy.map.ArticleResultMap;
import com.sy.sql.ArticleSql;

/**
 *
 * @author cck
 */
public interface ArticleMapper extends ArticleResultMap {

    @InsertProvider(type = ArticleSql.class, method = "save")
    int save(Article article);
    
    @ResultMap("map")
    @SelectProvider(type = ArticleSql.class, method = "getArticle")
    Article getArticle(Double nowEmotion);
    
}
