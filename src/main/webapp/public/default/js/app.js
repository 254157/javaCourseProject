var encoderApp = angular.module('encoderApp', [], function($interpolateProvider) {
    $interpolateProvider.startSymbol('[[');
    $interpolateProvider.endSymbol(']]');
});

encoderApp.filter('doubleMe', function() {
    return function(input) {
        return input + input;
    }
});

encoderApp.factory('encoderService', ['$http', function($http) {
    return {
        encode: function (text) {
            return $http.get('/encode/' + text);
        }
    }
}]);

encoderApp.controller('EncodeController', ['$scope', 'encoderService', function($scope, encoderService) {
    $scope.encodedText = '';
    $scope.encode = function(sourceText) {
        encoderService.encode(sourceText).success(function (data) {
            $scope.encodedText = data;
        }).error(function (data) {
            alert("Error: " + data);
        });
    };
}]);