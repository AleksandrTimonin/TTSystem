angular.module('ttsystem-front').controller('incomeController', function ($scope, $http, $location, $localStorage) {
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
 $scope.loadEmployers = function(){
        $http({
                        url: contextPathCore + '/orders/employers',
                        method: 'GET'

                    }).then(function (response) {
                        $scope.Employers = response.data;


                    });
 }
  var alertPlaceholder = document.getElementById('Alert')


     function alert(message, type) {
       var wrapper = document.createElement('div')
       wrapper.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible" role="alert">' + message + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'

       alertPlaceholder.append(wrapper)
     }

     $scope.assign = function (User, id) {

             $http({
                 url: contextPathCore + '/assign',
                 method: 'POST',
                 data: id, user
             }).then(function successCallback(response) {
                 alert('Исполнитель '+ response.data.username +',Время регистрации : ' + response.data.date,'success');

             }, function errorCallback(response) {
                 alert('Что-то пошло не так - попробуйте позже..' +response.data,'danger');


             });
         };
         $scope.assignMe = function (orderId) {

                      $http({
                          url: contextPathCore + '/orders/cancel',
                          method: 'GET',
                          params: {
                                id : orderId
                          }
                      }).then(function successCallback(response) {
                          alert('Заявка ID: '+response.data.id+' отклонена. Время : ' + response.data.date,'success');

                      }, function errorCallback(response) {
                          alert('Что-то пошло не так - попробуйте позже..' +response.data,'danger');


                      });
                  };
 $scope.cancelOrder = function (id) {

                       $http({
                           url: contextPathCore + '/cancel',
                           method: 'POST',
                           data: id

                       }).then(function successCallback(response) {
                           alert('Вы успешно приняли заявку,\\n необходимо отметить начало работы на странице МОИ ЗАЯВКИ.\\n Время регистрации : ' + response.data.date,'success');

                       }, function errorCallback(response) {
                           alert('Что-то пошло не так - попробуйте позже..' +response.data,'danger');


                       });
                   };
 $scope.loadUsersInfo = function (username) {
         $http({
                 url: contextPathCore + '/orders/info',
                 method: 'POST' ,
                 data:{user : username}

          }).then(function (response) {
                $scope.currentUser = response.data;
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
          if($scope.isAllowed('MANAGER')){
                $scope.loadEmployers();
          }
          $scope.isCreated = function(elem){
                          var result = elem.includes('CREATED');
                          return result ;

                    }

});