package com.example.testthymeleafwebapp.controller;


import com.example.testthymeleafwebapp.dao.StudentRepository;
import com.example.testthymeleafwebapp.entity.Student;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;
@Slf4j
@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping({"/list", "/"})
    public ModelAndView getAllStudents() {
        log.info("/list -> connection");
        ModelAndView nav = new ModelAndView("list-students");
        nav.addObject("students", studentRepository.findAll());
        return nav;
    }
    @GetMapping("/addStudentForn")
    public ModelAndView addStudentForn() {
        ModelAndView nav = new ModelAndView("add-student-form");
        Student student = new Student();
        nav.addObject("student", student);
        return nav;
    }
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/list";
    }
    @GetMapping("/showUpdateForn")
    public ModelAndView showUpdateForn(@RequestParam Long studentId) {
        ModelAndView nav = new ModelAndView("add-student-forn");
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Student student = new Student();
        if (optionalStudent.isPresent()){
            student = optionalStudent.get();
        }
        nav.addObject("student", student);
        return nav;
    }
    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Long studentId) {
        studentRepository.deleteById(studentId);
        return "redirect:/list";
    }
}
