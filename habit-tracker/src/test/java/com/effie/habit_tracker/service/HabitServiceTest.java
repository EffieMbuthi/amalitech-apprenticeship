package com.effie.habit_tracker.service;

import com.effie.habit_tracker.dto.HabitRequest;
import com.effie.habit_tracker.dto.HabitResponse;
import com.effie.habit_tracker.exceptions.HabitNotFoundException;
import com.effie.habit_tracker.model.Habit;
import com.effie.habit_tracker.repository.HabitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HabitServiceTest {

    @Mock
    private HabitRepository habitRepository;

    @InjectMocks
    private HabitService habitService;

    @Test
    void createHabit_savesAndReturnsHabit() {
        HabitRequest request = new HabitRequest();
        request.setName("Read daily");

        Habit savedHabit = new Habit();
        savedHabit.setName("Read daily");
        savedHabit.setCreatedDate(LocalDate.now());

        when(habitRepository.save(any(Habit.class))).thenReturn(savedHabit);

        HabitResponse response = habitService.createHabit(request);

        assertThat(response.getName()).isEqualTo("Read daily");
        assertThat(response.getCurrentStreak()).isEqualTo(0);
    }

    @Test
    void listHabits_returnsAllHabits() {
        Habit habit1 = new Habit();
        habit1.setName("Read daily");

        Habit habit2 = new Habit();
        habit2.setName("Exercise");

        when(habitRepository.findAll()).thenReturn(List.of(habit1, habit2));

        List<HabitResponse> responses = habitService.listHabits();

        assertThat(responses).hasSize(2);
    }

    @Test
    void completeHabit_firstTimeCompleting_setsStreakToOne() {
        Habit habit = new Habit();
        habit.setCurrentStreak(0);
        habit.setLastCompletedDate(null);

        when(habitRepository.findById(1L)).thenReturn(Optional.of(habit));
        when(habitRepository.save(any(Habit.class))).thenReturn(habit);

        HabitResponse response = habitService.completeHabit(1L);

        assertThat(response.getCurrentStreak()).isEqualTo(1);
    }

    @Test
    void completeHabit_alreadyCompletedToday_streakUnchanged() {
        Habit habit = new Habit();
        habit.setCurrentStreak(3);
        habit.setLastCompletedDate(LocalDate.now());

        when(habitRepository.findById(1L)).thenReturn(Optional.of(habit));

        HabitResponse response = habitService.completeHabit(1L);

        assertThat(response.getCurrentStreak()).isEqualTo(3);
    }

    @Test
    void completeHabit_completedYesterday_streakIncrements() {
        Habit habit = new Habit();
        habit.setCurrentStreak(3);
        habit.setLastCompletedDate(LocalDate.now().minusDays(1));

        when(habitRepository.findById(1L)).thenReturn(Optional.of(habit));
        when(habitRepository.save(any(Habit.class))).thenReturn(habit);

        HabitResponse response = habitService.completeHabit(1L);

        assertThat(response.getCurrentStreak()).isEqualTo(4);
    }

    @Test
    void completeHabit_missedMoreThanOneDay_streakResetsToOne() {
        Habit habit = new Habit();
        habit.setCurrentStreak(5);
        habit.setLastCompletedDate(LocalDate.now().minusDays(3));

        when(habitRepository.findById(1L)).thenReturn(Optional.of(habit));
        when(habitRepository.save(any(Habit.class))).thenReturn(habit);

        HabitResponse response = habitService.completeHabit(1L);

        assertThat(response.getCurrentStreak()).isEqualTo(1);
    }

    @Test
    void completeHabit_habitDoesNotExist_throwsException() {
        when(habitRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(HabitNotFoundException.class, () -> habitService.completeHabit(99L));
    }

    @Test
    void getStreak_habitExists_returnsCurrentStreak() {
        Habit habit = new Habit();
        habit.setCurrentStreak(4);

        when(habitRepository.findById(1L)).thenReturn(Optional.of(habit));

        int streak = habitService.getStreak(1L);

        assertThat(streak).isEqualTo(4);
    }

    @Test
    void getStreak_habitDoesNotExist_throwsException() {
        when(habitRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(HabitNotFoundException.class, () -> habitService.getStreak(99L));
    }

    @Test
    void deleteHabit_habitExists_deletesSuccessfully() {
        when(habitRepository.existsById(1L)).thenReturn(true);

        habitService.deleteHabit(1L);

        verify(habitRepository).deleteById(1L);
    }

    @Test
    void deleteHabit_habitDoesNotExist_throwsException() {
        when(habitRepository.existsById(99L)).thenReturn(false);

        assertThrows(HabitNotFoundException.class, () -> habitService.deleteHabit(99L));
    }
}