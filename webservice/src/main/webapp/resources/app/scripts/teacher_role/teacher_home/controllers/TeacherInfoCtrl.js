angular.module('appDATN.teacher')
    .controller('TeacherInfoCtrl', function ($scope, $state, TeacherInfoService, AuthService) {

        $scope.teacher;

        $scope.logout = function () {
            $scope.error = false;
            AuthService.logout()
                .success(function(){
                    $state.go('login')
                })
                .error(function () {
                    $state.go('error');
                })
        };

        load = function(){
            TeacherInfoService.getTeacherInfo()
                .success(function(data){
                    if(data.body == null)
                        $state.go('login');
                    else{
                        $scope.teacher = data.body;
                    }
                })
                .error(function (error) {

                })
        };

        load();

    });