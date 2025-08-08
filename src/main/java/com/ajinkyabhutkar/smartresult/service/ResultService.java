package com.ajinkyabhutkar.smartresult.service;

import com.ajinkyabhutkar.smartresult.entities.Mark;
import com.ajinkyabhutkar.smartresult.entities.Student;
import com.ajinkyabhutkar.smartresult.payload.StudentForm;
import com.ajinkyabhutkar.smartresult.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResultService {

    @Autowired
    private StudentRepo studentRepo;

    public int saveResult(StudentForm studentForm){

        Student student=new Student();


        student.setName(studentForm.getName());
        student.setRollNo(studentForm.getRollNo());
        student.setDob(studentForm.getDob());
        student.setGender(studentForm.getGender());
        student.setEmailAddress(studentForm.getEmailAddress());
        student.setCreatedTime(LocalDateTime.now());
        student.setSchoolName(studentForm.getSchoolName());

        // both list type is different to wwe need to convert markform list to mark
       List<Mark> markList=studentForm.getMarksFormList().stream().map(markForm -> {
            Mark mark=new Mark();
            mark.setSubject(markForm.getSubject());
            mark.setMarks(markForm.getMarks());
            mark.setMaxMarks(markForm.getMaxMarks());
            mark.setGrade(markForm.getGrade());
            mark.setRemark(markForm.getRemark());
            mark.setStudent(student);
            return  mark;
        }).toList();

       student.setMarkList(markList);

        Student savedStudent=studentRepo.save(student);

        System.out.println("Student saved with ID:"+savedStudent.getId());

        return savedStudent.getId();
    }

    public Student getStudentResult(String rollNo){

        Student student=studentRepo.findByRollNo(rollNo);

        return  student;
    }
}
