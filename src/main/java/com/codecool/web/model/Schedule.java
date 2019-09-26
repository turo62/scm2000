package com.codecool.web.model;

public class Schedule extends AbstractModel {
    
    private String title;
    private int userId;
    private int noDays;
    private boolean isPublished;
    
    public Schedule(int id, String title, int userId, int noDays, boolean isPublished) {
        super(id);
        this.title = title;
        this.userId = userId;
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
