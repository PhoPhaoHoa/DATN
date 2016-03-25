angular.module('appDATN.student')
    .factory("StudentService", function ($http) {

        getStudents = function(pageIndex, sizeOfPage){
            return $http.get('API/students', {params : {pageIndex: pageIndex, sizeOfPage:sizeOfPage}});
        }

        getStudent = function(studentId){
            return $http.get('API/student', {params : {id: studentId}})
        }

        addStudent = function(student){
            return $http.post('API/student', student)
        }

        updateStudent = function(student){
            return $http.put('API/student', student)
        }

        deleteStudent = function(student){
            return $http.delete('API/student', {params : student})
        }

        return {
            getStudents: getStudents,
            getStudent: getStudent,
            addStudent: addStudent,
            updateStudent: updateStudent,
            deleteStudent: deleteStudent
        };

    });