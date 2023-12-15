package com.bci.exercise.user.repository;

import com.bci.exercise.user.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}

