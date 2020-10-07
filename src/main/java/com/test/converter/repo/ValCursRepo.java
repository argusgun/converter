package com.test.converter.repo;

import com.test.converter.entetities.ValCurs;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ValCursRepo extends JpaRepository<ValCurs, Long> {
    ValCurs findByDate(String date);

}
