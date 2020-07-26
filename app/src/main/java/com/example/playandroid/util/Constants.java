package com.example.playandroid.util;

/**
 * 存放常量的类.
 */
public class Constants {

    /**
     * 主活动的常量.
     */
    public static class MainConstant {
        //碎片所对应的常量值.
        public static final int ARTICLE = 0;
        public static final int FRAME = 1;
        public static final int PROJECT = 2;
    }

    /**
     * 文章模块对应的常量.
     */
    public static class ArticleConstant {
        //网络请求对应的状态值
        public static final int GET_ARTICLES_SUCCESS = 0;
        public static final int GET_ARTICLES_FAILURE = 1;

        public static final int LOAD_MORE_SUCCESS = 10;
        public static final int LOAD_MORE_FAILURE = 11;

        public static final int REFRESH_SUCCESS = 20;
        public static final int REFRESH_FAILURE = 21;
    }

    /**
     * 加载文章的网页对应的常量值.
     */
    public static class ArticleDetailConstant {
        //启动活动时，传入的数据所对应的键值
        public static final String TITLE = "title";
        public static final String URL = "url";
    }

    /**
     * 项目模块对应的常量值.
     */
    public static class ProjectConstant {
        //网络请求对应的状态值
        public static final int SUCCESS = 0;
    }

    /**
     * 子项目对应的常量值.
     */
    public static class ProjectChildConstant {
        //网络请求对应的状态值
        public static final int SUCCESS = 0;
    }
    
    /**
     * 搜索热词以及搜索历史界面所对应的常量.
     */
    public static class SearchHintConstant {
        //网络请求搜索热词的状态值
        public static final int HOT_WORD_SUCCESS = 0;
    }

    /**
     * 搜索内容界面所对应的常量值.
     * */
    public static class SearchContentConstant{
        //搜索成功
        public static final int SEARCH_SUCCESS = 0;
    }

    /**
     * 知识体系模块对应的常量值.
     */
    public static class FrameConstant {
        //成功拿到知识体系(一级)的内容
        public static final int FRAME_SUCCESS = 0;
    }

    /**
     * 子体系的活动所对应的常量值.
     */
    public static class FrameChildConstant {
        //启动活动时，传入的对应的键值
        public static final String FRAME = "frame";

        //成功拿到知识体系下的文章
        public static final int SUCCESS = 0;
    }

    /**
     * 数据库类的常量.
     */
    public static class DatabaseConstant {
        //数据库的名字
        public static final String ARTICLE_DB_NAME = "Article.db";
        //“文章”的表的名字
        public static final String ARTICLE_TABLE = "Article";
        //历史搜索的表的名字
        public static final String SEARCH_HISTORY_TABLE = "SearchHistory";
        //当前版本
        public static final int CURRENT_VERSION = 2;
    }

    /**
     * 通知的常量值.
     */
    public static class NotificationConstant {
        public static final String CHANNEL_ID = "10";

        public static final String CHANNEL_NAME = "定时推送";
    }
}
