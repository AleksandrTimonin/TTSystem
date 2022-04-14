(function () {
    angular
        .module('ttsystem-front', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/form', {
                templateUrl: 'form/form.html',
                controller: 'formController'
            })

            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .when('/details', {
                templateUrl: 'details/details.html',
                controller: 'detailsController'
            })
            .when('/personal', {
                  templateUrl: 'personal/personal.html',
                  controller: 'personalController'
             })

            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.ttsystemUser) {
            try {
                let jwt = $localStorage.ttsystemUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.ttsystemUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }

            if ($localStorage.ttsystemUser) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.ttsystemUser.token;
            }
        }

    }
})();

angular.module('ttsystem-front').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage) {
 const contextPath = 'http://localhost:5555/auth/';
    $scope.tryToAuth = function () {
        $http.post( contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                   $localStorage.ttsystemUser = {username: $scope.user.username, token: response.data.token}



                    $scope.user.username = null;
                    $scope.user.password = null;
                    $http({
                                url: 'http://localhost:5555/core/api/v1/orders/getRole',
                                method: 'GET'
                            }).then(function (response) {
                                console.log(response.data);
                                $localStorage.allowance = response.data;

                            });



                    $location.path('/');
                }
            }, function errorCallback(response) {
                console.log('login error');
            });

    };


    $rootScope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
        $localStorage.allowance = null;
        $location.path('/');
    };

    $scope.clearUser = function () {
        delete $localStorage.ttsystemUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.ttsystemUser) {
            return true;
        } else {
            return false;
        }
    };
    $rootScope.isAllowed = function(elem){
        console.log(elem);
       return $localStorage.allowance.indexOf(elem) != -1;

    }
});