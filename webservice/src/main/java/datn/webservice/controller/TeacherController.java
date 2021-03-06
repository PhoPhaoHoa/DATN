package datn.webservice.controller;

import datn.interfaces.request.StudentRequest;
import datn.interfaces.request.TeacherRequest;
import datn.interfaces.response.ImportFromFileResponse;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import datn.interfaces.response.TeacherResponse;
import datn.service.IImportTeacherFromFileService;
import datn.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/API")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IImportTeacherFromFileService importTeacherFromFileService;

    @RequestMapping(value = "/getAllTeachers", method = RequestMethod.GET)
    public RestApiResponse<ArrayList<TeacherResponse>> getAllTeachers() {
        return teacherService.getTeachers();
    }

    @RequestMapping(value = "/getTeachersByPage", method = RequestMethod.GET)
    public RestApiResponse<Page<TeacherResponse>> getTeachersByPage(int pageIndex, int sizeOfPage, String searchInput) {
        return teacherService.getPageTeachers(pageIndex, sizeOfPage, searchInput);
    }

    @RequestMapping(value = "/getTeacher", method = RequestMethod.GET)
    public RestApiResponse<TeacherResponse> getTeacher(String id){
        return teacherService.getTeacher(id);
    }

    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
    public RestApiResponse<TeacherResponse> addTeacher(@RequestBody TeacherRequest teacherRequest){
        return teacherService.addTeacher(teacherRequest);
    }

    @RequestMapping(value = "/importTeacherFromFile", method = RequestMethod.POST)
    @ResponseBody
    public RestApiResponse<ImportFromFileResponse<TeacherResponse, TeacherRequest>> importData(@RequestParam("excelFile") MultipartFile excelFile){
        return importTeacherFromFileService.importData(excelFile);
    }

    @RequestMapping(value = "/updateTeacher", method = RequestMethod.PUT)
    public RestApiResponse<TeacherResponse> updateTeacher(@RequestBody TeacherRequest teacherRequest){
        return teacherService.updateTeacher(teacherRequest);
    }

    @RequestMapping(value = "/deleteTeacher", method = RequestMethod.DELETE)
    public RestApiResponse<TeacherResponse> deleteTeacher(TeacherRequest teacherRequest){
        return teacherService.deleteTeacher(teacherRequest);
    }

}
