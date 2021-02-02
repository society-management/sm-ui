package com.sm.test;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class StudentController {

    private static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1, "Tirth Timaniya"),
            new Student(2, "Sachin Tendulkar"),
            new Student(3, "Amitabh Bachhan")
    );

    // hasRole("ROLE_") hasAnyRole("ROLE_") hasAuthority('permission')  hasAnyAuthority('permission')

    @GetMapping(path = "/student/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_MEMBER')")
    public Student getStudentFromId(@PathVariable int id) {
        return STUDENT_LIST.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("com.sm.test.Student Does not exist"));
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_MEMBER')")
    @GetMapping(path = "/student")
    public List<Student> getAllStudents() {
        return STUDENT_LIST;
    }

    @PreAuthorize("hasAnyAuthority('user:write')")
    @PostMapping(path = "/student")
    public void addStudent(@RequestBody Student student) {
        System.out.println("Added User is --> " + " " + student.getName());
    }

    @PreAuthorize("hasAnyAuthority('user:write')")
    @PutMapping(path = "/student/{id}")
    public void updateStudent(@PathVariable int id) {
        System.out.println("Updated the student with ID -> " + id);
    }

    @PreAuthorize("hasAnyAuthority('user:write')")
    @DeleteMapping(path = "/student/{id}")
    public void deleteStudent(@PathVariable int id) {
        System.out.println("Student Deleted!!");
    }
}
