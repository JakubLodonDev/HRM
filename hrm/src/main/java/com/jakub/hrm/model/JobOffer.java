package com.jakub.hrm.model;


public class JobOffer {

    private int id;
    private String name;
    private String level;
    private String requirement;
    private String description;
    private String status;

    public JobOffer(int id, String name, String level, String requirement, String description, String status) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.requirement = requirement;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
