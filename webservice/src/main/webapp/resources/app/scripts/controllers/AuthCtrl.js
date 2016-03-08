angular.module('appDATN.controller')
    .controller('AuthCtrl', function ($scope,AuthService) {
        $scope.authenticated = AuthService.isAuthenticated();
        $scope.isStudent = AuthService.isStudent();
        $scope.isTeacher = false;
        $scope.isOfficer = false;
    });