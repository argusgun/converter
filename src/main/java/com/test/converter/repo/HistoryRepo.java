package com.test.converter.repo;

import com.test.converter.entetities.ConvertingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepo extends JpaRepository<ConvertingHistory,Long> {
}
