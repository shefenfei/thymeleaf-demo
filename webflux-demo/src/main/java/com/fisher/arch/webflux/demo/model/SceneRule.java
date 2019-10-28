package com.fisher.arch.webflux.demo.model;

public class SceneRule {

    private Integer sceneId;
    private Integer plazaId;
    private String activityTime;
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public Integer getPlazaId() {
        return plazaId;
    }

    public void setPlazaId(Integer plazaId) {
        this.plazaId = plazaId;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }
}
