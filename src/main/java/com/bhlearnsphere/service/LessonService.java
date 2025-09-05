package com.bhlearnsphere.service;

import com.bhlearnsphere.dto.*;
import com.bhlearnsphere.entity.*;
import com.bhlearnsphere.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public LessonDto createLesson(Long moduleId, LessonDto lessonDto) {
        com.bhlearnsphere.entity.Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));

        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDto.getTitle());
        lesson.setContent(lessonDto.getContent());
        lesson.setType(lessonDto.getType());
        lesson.setModule(module);

        Lesson savedLesson = lessonRepository.save(lesson);
        return convertToDto(savedLesson);
    }

    public LessonDto updateLesson(Long lessonId, LessonDto lessonDto) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        lesson.setTitle(lessonDto.getTitle());
        lesson.setContent(lessonDto.getContent());
        lesson.setType(lessonDto.getType());

        Lesson savedLesson = lessonRepository.save(lesson);
        return convertToDto(savedLesson);
    }

    public void deleteLesson(Long lessonId) {
        if (!lessonRepository.existsById(lessonId)) {
            throw new RuntimeException("Lesson not found");
        }
        lessonRepository.deleteById(lessonId);
    }

    public LessonDto getLessonById(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        return convertToDto(lesson);
    }

    public List<LessonDto> getLessonsByModule(Long moduleId) {
        com.bhlearnsphere.entity.Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));

        return lessonRepository.findByModule(module).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<LessonDto> getLessonsByType(String type) {
        return lessonRepository.findByType(type).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<LessonDto> getAllLessons() {
        return lessonRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private LessonDto convertToDto(Lesson lesson) {
        LessonDto dto = new LessonDto();
        dto.setId(lesson.getId());
        dto.setTitle(lesson.getTitle());
        dto.setContent(lesson.getContent());
        dto.setType(lesson.getType());
        dto.setModuleId(lesson.getModule().getId());
        return dto;
    }
}
