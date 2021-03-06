angular.module('appDATN.officer_wave')
    .controller('DetailCtrl', function ($scope, ProjectWaveService) {

        $scope.currentProjectWaveId;
        $scope.projectWave;

        $scope.setCurrentProjectWaveId = function (id) {
            $scope.currentProjectWaveId = id;
            $scope.getInfoProjectWave(id);
        };

        $scope.getCurrentProjectWaveId = function(){
            return $scope.currentProjectWaveId;
        };

        $scope.getInfoProjectWave = function(id){
            ProjectWaveService.getProjectWave(id)
                .success(function (data) {
                    $scope.projectWave = data.body;
                })
        };

    });
