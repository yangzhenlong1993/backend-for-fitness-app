package mobile_project.bean;

public class Goal {
    private Integer userId;

    private Integer currentWeight;

    private Integer currentHeight;

    private String partGoal;

    private String basicGoal;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Integer currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Integer getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentHeight(Integer currentHeight) {
        this.currentHeight = currentHeight;
    }

    public String getPartGoal() {
        return partGoal;
    }

    public void setPartGoal(String partGoal) {
        this.partGoal = partGoal == null ? null : partGoal.trim();
    }

    public String getBasicGoal() {
        return basicGoal;
    }

    public void setBasicGoal(String basicGoal) {
        this.basicGoal = basicGoal == null ? null : basicGoal.trim();
    }
}