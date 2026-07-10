package com.effie.habit_tracker.controller;

import com.effie.habit_tracker.dto.HabitRequest;
import com.effie.habit_tracker.dto.HabitResponse;
import com.effie.habit_tracker.service.HabitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PostMapping
    public ResponseEntity<HabitResponse> createHabit(@RequestBody @Valid HabitRequest request) {
        HabitResponse response = habitService.createHabit(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<HabitResponse>> listHabits() {
        return ResponseEntity.ok(habitService.listHabits());
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<HabitResponse> completeHabit(@PathVariable Long id) {
        HabitResponse response = habitService.completeHabit(id);
        return ResponseEntity.ok(response);
    }
}