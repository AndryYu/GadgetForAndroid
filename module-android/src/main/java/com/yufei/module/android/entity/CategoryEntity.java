package com.yufei.module.android.entity;

import java.util.List;

public class CategoryEntity {

    private List<Category> data;

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

    public class Category {
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

        public class Sublevel {
            private String sublevel;

            public String getSublevel() {
                return sublevel;
            }

            public void setSublevel(String sublevel) {
                this.sublevel = sublevel;
            }
        }
    }
}
