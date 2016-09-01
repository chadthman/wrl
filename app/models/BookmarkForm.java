package models;

import models.Bookmark;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Pattern;
import play.data.validation.Constraints.Required;


public class BookmarkForm {
    @Required
    @MinLength(value = Bookmark.MIN_NAME_LEN)
    @Pattern(Bookmark.URL_VALIDATION_PATTERN)
    private String url;

    private String desc;

    public BookmarkForm() {}

    public BookmarkForm(String url, String desc) {
        this.url = url;
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("BookmarkForm url=").append(url)
        .append(", desc=").append(desc);

        return sb.toString();
    }
}