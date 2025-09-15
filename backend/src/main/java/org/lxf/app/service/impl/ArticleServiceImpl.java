package org.lxf.app.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.lxf.app.entity.Article;
import org.lxf.app.mapper.ArticleMapper;
import org.lxf.app.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * 文章API服务
 *
 * @author lxf
 * @version 1.0
 * @since 2025/09/15 23:48
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}
