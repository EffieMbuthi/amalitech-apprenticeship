package com.effie.habit_tracker.service;

import com.effie.habit_tracker.exceptions.HabitNotFoundException;
import com.effie.habit_tracker.model.Habit;
import com.effie.habit_tracker.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class HabitService {
    private final HabitRepository habitRepository;

    public Habit completeHabit(Long id){
        Habit habit= habitRepository.findById(id)
                .orElseThrow(()->new HabitNotFoundException("Habit not found with ID: " + id));

        LocalDate today= LocalDate.now();
        LocalDate lastCompletedDate= habit.getLastCompletedDate();

        if(lastCompletedDate==null){
            habit.setCurrentStreak(1);
        }
        else if(lastCompletedDate.isEqual(today)){
            return habit;
        }
        else if(lastCompletedDate.isEqual(today.minusDays(1))){
            habit.setCurrentStreak(habit.getCurrentStreak() +1);
        }
        else{
            habit.setCurrentStreak(1);
        }
        habit.setLastCompletedDate(today);
        return habitRepository.save(habit);

    }
}
