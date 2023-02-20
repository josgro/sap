package nl.codegorilla.sap.service;

import jakarta.transaction.Transactional;
import nl.codegorilla.sap.exception.StudentNotFoundException;
import nl.codegorilla.sap.model.Student;
import nl.codegorilla.sap.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<?> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.status(200).body(students);
    }

    public ResponseEntity<?> addStudent(Student student) {
        Student newStudent = studentRepository.save(student);
        return ResponseEntity.status(201).body(newStudent);
    }

    public Student findStudentById(Long id) {
        return studentRepository.findStudentById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id: " + id + " not found."));
    }

    public ResponseEntity<?> updateStudent(Student student) {
        Student updateStudent = studentRepository.save(student);
        return ResponseEntity.status(200).body(updateStudent);
    }

    @Transactional
    public ResponseEntity<?> deleteStudent(Long id) {
        studentRepository.deleteStudentById(id);
        return ResponseEntity.status(200).body(Map.of("message", "Student deleted."));
    }

    public Optional<Student> findStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);

    }
}
