package mobile_project.bean;

public class Blog {
    private Integer userId;

    private Integer successWeight;

    private String comment;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSuccessWeight() {
        return successWeight;
    }

    public void setSuccessWeight(Integer successWeight) {
        this.successWeight = successWeight;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}