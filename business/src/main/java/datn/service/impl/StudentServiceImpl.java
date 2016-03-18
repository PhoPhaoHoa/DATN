package datn.service.impl;

import datn.dao.constant.Gender;
import datn.dao.entity.Student;
import datn.dao.repository.StudentRepository;
import datn.interfaces.request.StudentRequest;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import datn.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class StudentServiceImpl implements IStudentService{

    @Autowired
    StudentRepository studentRepository;

    public RestApiResponse<ArrayList<StudentResponse>> getStudents() {
        ArrayList<Student> studentEntities = (ArrayList<Student>) studentRepository.findAll();
        ArrayList<StudentResponse> studentResponses = convertStudentEntitiesToStudentResponses(studentEntities);
        RestApiResponse<ArrayList<StudentResponse>> students = new RestApiResponse<ArrayList<StudentResponse>>(studentResponses);

        return students;
    }

    public RestApiResponse<StudentResponse> getStudent(String id) {
        Student studentEntities = studentRepository.findOne(id);
        StudentResponse studentResponse = convertStudentEntityToStudentResponse(studentEntities);
        RestApiResponse<StudentResponse> student = new RestApiResponse<StudentResponse>(studentResponse);

        return student;
    }

    public Student getStudentEntity(String id){
        Student student = studentRepository.findOne(id);
        return  student;
    }

    public RestApiResponse<StudentResponse> addStudent(StudentRequest studentRequest) {
        Student studentEntity = convertStudentRequestToStudentEntity(studentRequest);
        studentEntity = studentRepository.save(studentEntity);
        StudentResponse studentResponse = convertStudentEntityToStudentResponse(studentEntity);
        RestApiResponse<StudentResponse> student = new RestApiResponse<StudentResponse>(studentResponse);

        return student;
    }

    public RestApiResponse<StudentResponse> deleteStudent(StudentRequest studentRequest) {
        Student studentEntity = convertStudentRequestToStudentEntity(studentRequest);
        studentEntity.setDeleted(true);
        studentEntity = studentRepository.save(studentEntity);
        StudentResponse studentResponse = convertStudentEntityToStudentResponse(studentEntity);
        RestApiResponse<StudentResponse> student = new RestApiResponse<StudentResponse>(studentResponse);

        return student;
    }

    public RestApiResponse<StudentResponse> updateStudent(StudentRequest studentRequest) {
        Student studentEntity = convertStudentRequestToStudentEntity(studentRequest);
        studentEntity = studentRepository.save(studentEntity);
        StudentResponse studentResponse = convertStudentEntityToStudentResponse(studentEntity);
        RestApiResponse<StudentResponse> student = new RestApiResponse<StudentResponse>(studentResponse);

        return student;
    }

    private ArrayList<StudentResponse> convertStudentEntitiesToStudentResponses(ArrayList<Student> studentEntities){
        ArrayList<StudentResponse> students = new ArrayList<StudentResponse>();
        StudentResponse studentResponse;
        for (int i = 0; i < studentEntities.size(); i++) {
            studentResponse = convertStudentEntityToStudentResponse(studentEntities.get(i));
            students.add(studentResponse);
        }

        return  students;
    }

    private StudentResponse convertStudentEntityToStudentResponse(Student studentEntity){
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(studentEntity.getId());
        studentResponse.setName(studentEntity.getName());
        studentResponse.setBirthday(studentEntity.getBirthday());
        studentResponse.setDeleted(studentEntity.getDeleted());
        studentResponse.setDescription(studentEntity.getDescription());
        studentResponse.setEmail(studentEntity.getEmail());
        studentResponse.setDeleted(studentEntity.getDeleted());
        studentResponse.setGender(studentEntity.getGender().getValue());
        studentResponse.setTypeOfUser(studentEntity.getTypeOfUser().getValue());
        studentResponse.setPhoneNumber(studentEntity.getPhoneNumber());
        studentResponse.setStatus(studentEntity.getStatus());
        studentResponse.setClass(studentEntity.getClass_());

        return  studentResponse;
    }

    private Student convertStudentRequestToStudentEntity(StudentRequest studentRequest){
        Student studentEntity = new Student();
        studentEntity.setUsername(studentRequest.getUsername());
        studentEntity.setBirthday(studentRequest.getBirthday());
        studentEntity.setDescription(studentRequest.getDescription());
        studentEntity.setEmail(studentRequest.getEmail());
        studentEntity.setGender(Gender.valueOfKey(studentRequest.getGender()));
        studentEntity.setName(studentRequest.getName());
        studentEntity.setPassword(studentRequest.getPassword());
        studentEntity.setPhoneNumber(studentRequest.getPhoneNumber());
        studentEntity.setStatus(studentRequest.getStatus());
        studentEntity.setClass_(studentRequest.getClass_());


        return studentEntity;
    }

}
