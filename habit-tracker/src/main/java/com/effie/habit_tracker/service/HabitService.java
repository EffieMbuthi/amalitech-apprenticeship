package com.effie.habit_tracker.service;

import com.effie.habit_tracker.dto.HabitRequest;
import com.effie.habit_tracker.dto.HabitResponse;
import com.effie.habit_tracker.exceptions.HabitNotFoundException;
import com.effie.habit_tracker.model.Habit;
import com.effie.habit_tracker.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;

    public List<HabitResponse> listHabits() {
        return habitRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public HabitResponse createHabit(HabitRequest request) {
        Habit newHabit = new Habit();
        newHabit.setName(request.getName());
        newHabit.setCreatedDate(LocalDate.now());
        Habit saved = habitRepository.save(newHabit);
        return toResponse(saved);
    }

    public HabitResponse completeHabit(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException(id));

        LocalDate today = LocalDate.now();
        LocalDate lastCompletedDate = habit.getLastCompletedDate();

        if (lastCompletedDate == null) {
            habit.setCurrentStreak(1);
        } else if (lastCompletedDate.isEqual(today)) {
            return toResponse(habit);
        } else if (lastCompletedDate.isEqual(today.minusDays(1))) {
            habit.setCurrentStreak(habit.getCurrentStreak() + 1);
        } else {
            habit.setCurrentStreak(1);
        }

        habit.setLastCompletedDate(today);
        Habit saved = habitRepository.save(habit);
        return toResponse(saved);
    }

    private HabitResponse toResponse(Habit habit) {
        return new HabitResponse(
                habit.getId(),
                habit.getName(),
                habit.getCreatedDate(),
                habit.getCurrentStreak(),
                habit.getLastCompletedDate()
        );
    }

    public int getStreak(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException(id));
        return habit.getCurrentStreak();
    }

    public void deleteHabit(Long id) {
        if (!habitRepository.existsById(id)) {
            throw new HabitNotFoundException(id);
        }
        habitRepository.deleteById(id);
    }
}