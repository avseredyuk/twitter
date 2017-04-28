var sampleApp = angular.module('sampleApp', []);

sampleApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
        when('/ShowUsers', {
            templateUrl: 'templates/show_users.html',
            controller: 'ShowUsersController'
        }).
        when('/ShowTweets', {
            templateUrl: 'templates/show_tweets.html',
            controller: 'ShowTweetsController',
            css: 'css/show_tweets.css'
        }).
        when('/ShowTimeline/:userName', {
            templateUrl: 'templates/show_timeline.html',
            controller: 'ShowTimelineController',
            css: 'css/show_timeline.css'
        }).
        otherwise({
            redirectTo: '/ShowUsers'
        });
    }]);

sampleApp.controller('ShowTimelineController', function($scope, $http, $routeParams) {
    var url = "http://localhost:9999/twitter/rest/timeline/" + $routeParams.userName;
    $http({
        url: url,
        method: 'GET'
    }).success(function (response) {
        $scope.timeline = response;
    });
});

sampleApp.controller('ShowUsersController', function($scope, $http) {
    var url = "http://localhost:9999/twitter/rest/user/all";
    $http({
        url: url,
        method: 'GET'
    }).success(function (response) {
        $scope.users = response;
    });
});

sampleApp.controller('ShowTweetsController', function($scope, $http) {
    var url = "http://localhost:9999/twitter/rest/tweet/all";
    $http({
        url: url,
        method: 'GET'
    }).success(function (response) {
        $scope.tweets = response;
    });
});