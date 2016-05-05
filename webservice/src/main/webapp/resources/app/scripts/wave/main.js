angular.module('appDATN.wave')
    .config(function ($stateProvider) {
        $stateProvider
            .state('wave', {
                abstract: true,
                url: '/wave',
                parent: 'logged',
                views: {
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
                    }
                }
            })
            .state('wave.add', {
                url: '/add',
                views: {
                    content_view: {
                        controller: 'AddProjectWaveCtrl',
                        templateUrl: '/resources/app/scripts/wave/views/add_project_wave.html'
                    }
                }
            })
            .state('wave.list', {
                url: '/list',
                views: {
                    content_view: {
                        controller: 'ListProjectWaveCtrl',
                        templateUrl: '/resources/app/scripts/wave/views/list_project_wave.html'
                    }
                }
            })
            .state('wave.detail', {
                url: '/detail',
                abstract: true,
                views: {
                    content_view: {
                        controller: 'DetailCtrl',
                        templateUrl: '/resources/app/scripts/wave/views/detail_project_wave.html'
                    }
                }
            })
            .state('wave.detail.times', {
                url: '/tabs/times/:projectWaveId',
                resolve:{
                    projectWaveTimes: function ($q, $state, $stateParams, ProjectWaveService) {
                        var deferred = $q.defer();
                        console.log("called waveDetailTimes: " + $stateParams.projectWaveId);
                        ProjectWaveService.getProjectWave($stateParams.projectWaveId)
                            .success(function (data) {
                                if(data.headers.resultCode == 0) {
                                    deferred.resolve(data.body);
                                }else{
                                    deferred.reject('Error');
                                }
                            })
                            .error(function (error) {
                                deferred.reject('Error');
                            });
                        return deferred.promise;
                    }
                },
                views: {
                    detail_project_wave_tab_view: {
                        controller: 'DetailTabTimesCtrl',
                        templateUrl: '/resources/app/scripts/wave/views/detail_project_wave_tab_times.html'
                    }
                }
            })
            .state('wave.detail.students', {
                url: '/tabs/students/:projectWaveId',
                resolve:{
                    projectWaveStudents: function ($q, $state, $stateParams, ProjectWaveService) {
                        var deferred = $q.defer();
                        console.log("called waveDetailStudents: " + $stateParams.projectWaveId);
                        ProjectWaveService.getStudents($stateParams.projectWaveId)
                            .success(function (data) {
                                if(data.headers.resultCode == 0) {
                                    deferred.resolve(data.body);
                                }else{
                                    deferred.reject('Error');
                                }
                            })
                            .error(function (error) {
                                deferred.reject('Error');
                            });
                        return deferred.promise;
                    }
                },
                views: {
                    detail_project_wave_tab_view: {
                        controller: 'DetailTabStudentsCtrl',
                        templateUrl: '/resources/app/scripts/wave/views/detail_project_wave_tab_students.html'
                    }
                }
            })
            .state('wave.detail.teachers', {
                url: '/tabs/teachers/:projectWaveId',
                views: {
                    detail_project_wave_tab_view: {
                        templateUrl: '/resources/app/scripts/wave/views/detail_project_wave_tab_teachers.html'
                    }
                }
            })
            .state('wave.detail.assignments', {
                url: '/tabs/assignments/:projectWaveId',
                views: {
                    detail_project_wave_tab_view: {
                        templateUrl: '/resources/app/scripts/wave/views/detail_project_wave_tab_assignments.html'
                    }
                }
            })
    });

