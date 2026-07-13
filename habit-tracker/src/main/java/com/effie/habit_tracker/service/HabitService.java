package com.effie.habit_tracker.service;

import com.effie.habit_tracker.dto.HabitRequest;
import com.effie.habit_tracker.dto.HabitResponse;
import com.effie.habit_tracker.exceptions.HabitNotFoundException;
import com.effie.habit_tracker.model.Habit;
import com.effie.habit_tracker.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;

    private static final Logger log = LoggerFactory.getLogger(HabitService.class);

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
        log.info("Habit created: id={}, name={}", saved.getId(), saved.getName());
        return toResponse(saved);
    }

    public HabitResponse completeHabit(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException(id));

        LocalDate today = LocalDate.now();
        LocalDate lastCompletedDate = habit.getLastCompletedDate();

        if (lastCompletedDate == null) {
            habit.setCurrentStreak(1);
            log.info("Habit {} completed for the first time, streak set to 1", id);
        } else if (lastCompletedDate.isEqual(today)) {
            log.info("Habit {} already completed today, streak unchanged", id);
            return toResponse(habit);
        } else if (lastCompletedDate.isEqual(today.minusDays(1))) {
            habit.setCurrentStreak(habit.getCurrentStreak() + 1);
            log.info("Habit {} streak incremented to {}", id, habit.getCurrentStreak());
        } else {
            habit.setCurrentStreak(1);
            log.info("Habit {} missed a day, streak reset to 1", id);
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
        log.info("Habit {} deleted", id);
        habitRepository.deleteById(id);
    }
}