package com.test.converter.repo;

import com.test.converter.entetities.ConvertingHistory;
import com.test.converter.entetities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepo extends JpaRepository<ConvertingHistory,Long> {
    List<ConvertingHistory> findAllByUser(User user);
}
