package com.effie.habit_tracker.dto;

import java.time.LocalDate;

public class HabitResponse {

    private Long id;
    private String name;
    private LocalDate createdDate;
    private int currentStreak;
    private LocalDate lastCompletedDate;

    public HabitResponse(Long id, String name, LocalDate createdDate, int currentStreak, LocalDate lastCompletedDate) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.currentStreak = currentStreak;
        this.lastCompletedDate = lastCompletedDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public LocalDate getLastCompletedDate() {
        return lastCompletedDate;
    }
}