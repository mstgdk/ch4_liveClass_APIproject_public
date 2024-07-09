package com.patika.service;

import com.patika.dto.request.UniversitySaveRequest;
import com.patika.entities.University;
import com.patika.repository.UniversityRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Transactional
    public void saveUniversity(UniversitySaveRequest universitySaveRequest) {

        University university2 = new University();
        university2.setName("hacettepe");
        university2.setCity("ankara");
        universityRepository.save(university2);

        // Simüle edilmiş hata durumu
        if (true) {
            throw new RuntimeException("Simulated error");
        }

        University university = new University();
        university.setName(universitySaveRequest.getName());
        university.setCity(universitySaveRequest.getCity());

        universityRepository.save(university);


    }
}
