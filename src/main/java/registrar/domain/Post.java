package registrar.domain;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("source_id")
    private Integer sourceId;

    @SerializedName("post_id")
    private Integer postId;

    @SerializedName("text")
    private String text;

    @SerializedName("date")
    private Integer date;

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (sourceId != null ? !sourceId.equals(post.sourceId) : post.sourceId != null) return false;
        if (postId != null ? !postId.equals(post.postId) : post.postId != null) return false;
        if (text != null ? !text.equals(post.text) : post.text != null) return false;
        return date != null ? date.equals(post.date) : post.date == null;
    }

    @Override
    public int hashCode() {
        int result = sourceId != null ? sourceId.hashCode() : 0;
        result = 31 * result + (postId != null ? postId.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "sourceId=" + sourceId +
                ", postId=" + postId +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
