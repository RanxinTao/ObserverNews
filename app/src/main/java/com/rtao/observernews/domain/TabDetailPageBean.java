package com.rtao.observernews.domain;

import java.util.List;

public class TabDetailPageBean {

    private DataEntity data;
    private int retcode;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public DataEntity getData() {
        return data;
    }

    public int getRetcode() {
        return retcode;
    }

    public static class DataEntity {
        private String countcommenturl;
        private String more;
        private String title;
        private List<NewsData> news;
        private List<?> topic;
        private List<TopnewsData> topnews;

        public void setCountcommenturl(String countcommenturl) {
            this.countcommenturl = countcommenturl;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setNews(List<NewsData> news) {
            this.news = news;
        }

        public void setTopic(List<?> topic) {
            this.topic = topic;
        }

        public void setTopnews(List<TopnewsData> topnews) {
            this.topnews = topnews;
        }

        public String getCountcommenturl() {
            return countcommenturl;
        }

        public String getMore() {
            return more;
        }

        public String getTitle() {
            return title;
        }

        public List<NewsData> getNews() {
            return news;
        }

        public List<?> getTopic() {
            return topic;
        }

        public List<TopnewsData> getTopnews() {
            return topnews;
        }

        public static class NewsData {
            private int id;
            private String title;
            private String url;
            private String listimage;
            private String pubdate;
            private boolean comment;
            private String commenturl;
            private String type;
            private String commentlist;

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setListimage(String listimage) {
                this.listimage = listimage;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getUrl() {
                return url;
            }

            public String getListimage() {
                return listimage;
            }

            public String getPubdate() {
                return pubdate;
            }

            public boolean isComment() {
                return comment;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public String getType() {
                return type;
            }

            public String getCommentlist() {
                return commentlist;
            }
        }

        public static class TopnewsData {
            private boolean comment;
            private String commentlist;
            private String commenturl;
            private int id;
            private String pubdate;
            private String title;
            private String topimage;
            private String type;
            private String url;

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setTopimage(String topimage) {
                this.topimage = topimage;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isComment() {
                return comment;
            }

            public String getCommentlist() {
                return commentlist;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public int getId() {
                return id;
            }

            public String getPubdate() {
                return pubdate;
            }

            public String getTitle() {
                return title;
            }

            public String getTopimage() {
                return topimage;
            }

            public String getType() {
                return type;
            }

            public String getUrl() {
                return url;
            }
        }
    }
}
