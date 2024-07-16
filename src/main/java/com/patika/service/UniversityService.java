package com.patika.service;

import com.patika.dto.request.UniversitySaveRequest;
import com.patika.dto.request.UniversityUpdateRequest;
import com.patika.dto.response.UniversityResponseDto;
import com.patika.entities.University;
import com.patika.exception.ResourceNotFoundException;
import com.patika.message.ErrorMessage;
import com.patika.repository.UniversityRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

   // @Transactional
    public void saveUniversity(UniversitySaveRequest universitySaveRequest) {

        /*University university2 = new University();
        university2.setName("hacettepe");
        university2.setCity("ankara");
        universityRepository.save(university2);

        // Simüle edilmiş hata durumu
        if (true) {
            throw new RuntimeException("Simulated error");
        }*/

        University university = new University();
        university.setName(universitySaveRequest.getName());
        university.setCity(universitySaveRequest.getCity());

        universityRepository.save(university);


    }

    public UniversityResponseDto getById(Long id) {
        University university = universityRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION,id))
        );
        UniversityResponseDto universityDto = new UniversityResponseDto();
        universityDto.setName(university.getName());
        universityDto.setCity(university.getCity());
        universityDto.setStudents(university.getStudents());

      return universityDto;
    }

    public void removeById(Long id) {
      boolean isExist =   universityRepository.existsById(id);

      if (isExist){
          universityRepository.deleteById(id);
      } else throw new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION,id));

    }

    public List<UniversityResponseDto> getAll() {
      List<University> univeristies =   universityRepository.findAll();
      List<UniversityResponseDto> univeristiesDTO = new ArrayList<>();

      for (University w : univeristies){
          UniversityResponseDto universityDto = new UniversityResponseDto();
          universityDto.setName(w.getName());
          universityDto.setCity(w.getCity());
          universityDto.setStudents(w.getStudents());

          univeristiesDTO.add(universityDto);

      }
        return univeristiesDTO;

    }

    public Page<UniversityResponseDto> getAllPage(Pageable pageable) {
       Page<University> universities =  universityRepository.findAll(pageable);
      List<UniversityResponseDto> universityDtos =  universities.stream().map(university ->{
           UniversityResponseDto universityDto = new UniversityResponseDto();
           universityDto.setName(university.getName());
           universityDto.setCity(university.getCity());
           universityDto.setStudents(university.getStudents());
           return universityDto;
       } ).collect(Collectors.toList());

        return new PageImpl<>(universityDtos,pageable,universities.getTotalElements());
    }

    public void updateUniversity(Long id, UniversityUpdateRequest universityUpdateRequest) {
            University university = universityRepository.findById(id).orElseThrow(
                    ()->new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION))
            );
            if (universityUpdateRequest.getName()!=null){
                university.setName(universityUpdateRequest.getName());
            }
            if (universityUpdateRequest.getCity()!=null){
                university.setCity(universityUpdateRequest.getCity());
            }
            universityRepository.save(university);


    }
}
