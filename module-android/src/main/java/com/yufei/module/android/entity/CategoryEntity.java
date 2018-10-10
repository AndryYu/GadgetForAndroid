package com.yufei.module.android.entity;

import java.io.Serializable;
import java.util.List;

public class CategoryEntity implements Serializable{

    private List<Category> data;

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

    public class Category implements Serializable{
        private String category;

        private List<Sublevel> content;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public List<Sublevel> getContent() {
            return content;
        }

        public void setContent(List<Sublevel> content) {
            this.content = content;
        }

        public class Sublevel implements Serializable{
            private String sublevel;
            private List<DetailEntity> data;

            public String getSublevel() {
                return sublevel;
            }

            public void setSublevel(String sublevel) {
                this.sublevel = sublevel;
            }

            public List<DetailEntity> getData() {
                return data;
            }

            public void setData(List<DetailEntity> data) {
                this.data = data;
            }
        }
    }
}
