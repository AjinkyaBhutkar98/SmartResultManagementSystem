package com.ajinkyabhutkar.smartresult.controller;

import com.ajinkyabhutkar.smartresult.entities.Mark;
import com.ajinkyabhutkar.smartresult.entities.Student;
import com.ajinkyabhutkar.smartresult.payload.MarksForm;
import com.ajinkyabhutkar.smartresult.payload.StudentForm;
import com.ajinkyabhutkar.smartresult.repo.StudentRepo;
import com.ajinkyabhutkar.smartresult.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(value="/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @Autowired
    private StudentRepo studentRepo;

    // roll no input page
    @RequestMapping("/rollno")
    public String showResult(Model model){

        StudentForm studentForm=new StudentForm();

        model.addAttribute("studentForm",studentForm);
        System.out.println("roll no input");
        return "view_result";
    }

    public String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B";
        else if (percentage >= 60) return "C";
        else if (percentage >= 50) return "D";
        else return "F";
    }


//    @RequestMapping(value = "/view",method = RequestMethod.POST)
    @PostMapping("/view")
//    public String getResult(@ModelAttribute StudentForm studentForm,Model model){
    public String getResult(@RequestParam("rollNo") String rollNo, Model model){

        System.out.println(rollNo);

        Student student=studentRepo.findByRollNo(rollNo);

        if (student == null) {
            // Add error message and return to a safe page (e.g., the input form or an error view)
            model.addAttribute("errorMessage", "❌ Invalid roll number. Please try again.");
            return "error_page"; // Or return the form view with error message
        }


        int totalMarks = student.getMarkList().stream().mapToInt(Mark::getMarks).sum();
        int maxTotal = student.getMarkList().stream().mapToInt(Mark::getMaxMarks).sum();
        double percentage = ((double) totalMarks / maxTotal) * 100;
        String overallGrade = calculateGrade(percentage); // A simple method you create



        System.out.println(student.getName());
        System.out.println("Get result");

        model.addAttribute("studentName",student.getName());
        model.addAttribute("studentRollNo",student.getRollNo());
        model.addAttribute("studentSchool",student.getSchoolName());
        model.addAttribute("studentEmail",student.getEmailAddress());
        model.addAttribute("studentDob",student.getDob());
        model.addAttribute("studentGender",student.getGender());

        model.addAttribute("totalMarks", totalMarks);
        model.addAttribute("maxTotalMarks", maxTotal);
        model.addAttribute("percentage", String.format("%.2f", percentage));
        model.addAttribute("overallGrade", overallGrade);

        model.addAttribute("studentMarkList", student.getMarkList());

        String feedbackMessage = switch (overallGrade) {
            case "A+" -> "🌟 Outstanding! You’ve nailed it.";
            case "A" -> "🌟 Excellent performance! Keep up the great work.";
            case "B" -> "👍 Good job. You can aim even higher!";
            case "C" -> "🙂 Decent work. Let’s aim for better next time.";
            default -> "⚠️ Needs improvement. Don’t give up!";
        };

        model.addAttribute("feedbackMessage", feedbackMessage);


        return "result_data";
    }

    @RequestMapping("/add")
    public String addResult(Model model){

        System.out.println("add result");

        StudentForm studentForm=new StudentForm();

        model.addAttribute("studentForm",studentForm);

        return "add_result";
    }

    @RequestMapping(value = "/process-form",method = RequestMethod.POST)
    public String saveResult(@ModelAttribute StudentForm studentForm,Model model){

        System.out.println("Student form: "+studentForm.getName());
        System.out.println(studentForm.getEmailAddress());
        System.out.println(studentForm.getDob());

        System.out.println(studentForm.getMarksFormList().size());

        studentForm.getMarksFormList().forEach(marksForm ->
                System.out.println("subject " + marksForm.getSubject())

        );

        int savedStudentId=resultService.saveResult(studentForm);

        model.addAttribute("studentId",savedStudentId);




        return "save_result";
    }


}
