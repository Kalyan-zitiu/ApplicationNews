package com.example.applicationnews.enity;

import java.io.Serializable;
import java.util.List;

public class NewsE {

    private String reason;

    private ResultDTO result;

    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static class ResultDTO {

        private String stat;

        private List<DataDTO> data;

        private String page;

        private String pageSize;

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public List<DataDTO> getData() {
            return data;
        }

        public void setData(List<DataDTO> data) {
            this.data = data;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }




        public static class DataDTO implements Serializable {

            private String uniquekey;

            private String title;

            private String date;

            private String category;
            private String author_name;

            private String url;

            private String thumbnail_pic_s;

            private String isContent;

            private String thumbnailPicS02;

            private String thumbnailPicS03;

            public String getThumbnailPicS03() {
                return thumbnailPicS03;
            }

            public void setThumbnailPicS03(String thumbnailPicS03) {
                this.thumbnailPicS03 = thumbnailPicS03;
            }

            public String getThumbnailPicS02() {
                return thumbnailPicS02;
            }

            public void setThumbnailPicS02(String thumbnailPicS02) {
                this.thumbnailPicS02 = thumbnailPicS02;
            }

            public String getIsContent() {
                return isContent;
            }

            public void setIsContent(String isContent) {
                this.isContent = isContent;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }


        }
    }
}
