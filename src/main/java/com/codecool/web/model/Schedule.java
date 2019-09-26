package com.codecool.web.model;

public class Schedule extends AbstractModel {
    
    private int userId;
    private String title;
    private int noDays;
    private boolean isPublished;
    
    public Schedule(int id, int userId, String title, int noDays, boolean isPublished) {
        super(id);
        this.userId = userId;
        this.title = title;
        this.noDays = noDays;
        this.isPublished = isPublished;
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public int getUserId() {
        return userId;
    }
    
    public int getNoDays() {
        return noDays;
    }
    
    public boolean isPublished() {
        return isPublished;
    }
}
