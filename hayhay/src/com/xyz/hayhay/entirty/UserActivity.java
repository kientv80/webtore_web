package com.xyz.hayhay.entirty;

public class UserActivity {
	private String userId;
    private String action;
    private String type;
    private String data;
    private Long date;
    public UserActivity(String action,String type,String jsonData){
        this.setAction(action);
        this.setType(type);
        this.setData(jsonData);
        this.setDate(System.currentTimeMillis());
    }
    public UserActivity() {
	}
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
