package datn.controller;

import datn.entity.*;
import datn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ProjectWaveRepository projectWaveRepository;

    @Autowired
    private StudentWaveRepository studentWaveRepository;

    @Autowired
    private TeacherWaveRepository teacherWaveRepository;

    @Autowired
    private TeacherTopicRepository teacherTopicRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private StudentReportRepository studentReportRepository;

    @Autowired
    private StudentReportDetailRepository studentReportDetailRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPageS(HttpServletRequest request){
        return "index";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String loginPages(HttpServletRequest request){
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPageas(HttpServletRequest request){
        Student student = new Student();
        student.setId(UUID.randomUUID().toString());
        studentRepository.save(student);

        Teacher teacher = new Teacher();
        teacher.setId(UUID.randomUUID().toString());
        teacherRepository.save(teacher);

        ProjectWave projectWave = new ProjectWave();
        projectWave.setId(UUID.randomUUID().toString());
        projectWaveRepository.save(projectWave);

        StudentWave studentWave = new StudentWave();
        studentWave.setStudent(student);
        studentWave.setProjectWave(projectWave);
        studentWaveRepository.save(studentWave);

        TeacherWave teacherWave = new TeacherWave();
        teacherWave.setTeacher(teacher);
        teacherWave.setProjectWave(projectWave);
        teacherWaveRepository.save(teacherWave);

        TeacherTopic teacherTopic = new TeacherTopic();
        teacherTopic.setTeacher(teacher);
        teacherTopicRepository.save(teacherTopic);

        Report report = new Report();
        report.setProjectWave(projectWave);
        reportRepository.save(report);

        StudentReport studentReport = new StudentReport();
        studentReport.setStudent(student);
        studentReport.setReport(report);
        studentReportRepository.save(studentReport);

        StudentReportDetail studentReportDetail = new StudentReportDetail();
        studentReportDetail.setStudentReport(studentReport);
        studentReportDetailRepository.save(studentReportDetail);

        return "index";
    }

}
