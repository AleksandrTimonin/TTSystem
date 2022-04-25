angular.module('ttsystem-front').controller('assignedController', function ($scope, $http, $location, $localStorage) {
   const contextPathCore = $localStorage.corePath;

     $scope.loadOrders = function (pageIndex = 1) {
                $http({
                    url: contextPathCore + '/orders/management',
                    method: 'GET',
                    params: {
                        p: pageIndex,
                        old_date: $scope.filter ? $scope.filter.oldDate : null,
                        new_date: $scope.filter ? $scope.filter.newDate : null
                    }
                }).then(function (response) {
                    $scope.OrdersPage = response.data;
                    $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.OrdersPage.totalPages);
                });
            };
     $scope.loadUsersInfo = function () {
             $http({
             url: contextPathCore + '/orders/getUserInfo',
             method: 'GET' ,
             params: {

              users : $scope.users

              }

              }).then(function (response) {
                    $scope.UserDto = response.data;
              });

              };
              $scope.generatePagesIndexes = function (startPage, endPage) {
                    let arr = [];
                    for (let i = startPage; i < endPage + 1; i++) {
                          arr.push(i);
                    }
                    return arr;
                    }
              $scope.isAllowed = function(elem){
                    var result = $localStorage.allowance.roles.includes(elem);
                    return result ;

              }
              if($scope.isAllowed('EMPLOYEE')){
                    $scope.loadOrders();
              }


});