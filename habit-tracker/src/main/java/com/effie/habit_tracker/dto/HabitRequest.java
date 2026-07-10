package com.effie.habit_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class HabitRequest {
    @NotBlank(message = "Habit name is required")
    @Size(max = 100, message = "Habit name must be under 100 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}