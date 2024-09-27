package com.sda_project.myfluffy.controller;

import com.sda_project.myfluffy.user.User;

// DTO to capture only the required fields for pet creation
class CreatePetRequest {
    private String name;

    private float age;

    private String description;

    private User owner;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}

