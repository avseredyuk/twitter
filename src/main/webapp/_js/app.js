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
        when('/ShowTweet/:tweetId', {
            templateUrl: 'templates/show_tweet.html',
            controller: 'ShowTweetController',
            css: 'css/show_tweet.css'
        }).
        when('/error', {
            templateUrl: 'templates/error.html',
            controller: 'ShowErrorController',
            css: 'css/error.css'
        }).
        otherwise({
            redirectTo: '/ShowUsers'
        });
    }]);

sampleApp.controller('ShowErrorController', function($scope) {
    $scope.message = "eggog";
});

sampleApp.controller('ShowTweetController', function($scope, $http, $routeParams, $location) {
    var url = "/twitter/rest/tweet/" + $routeParams.tweetId;
    $http({
        url: url,
        method: 'GET'
    }).success(function successCallback(response) {
        $scope.tweet = response;
    }).error(function (response) {
        $scope.errorObj = response;
        $location.path('/error').search(response);

    });
});

sampleApp.controller('ShowTimelineController', function($scope, $http, $routeParams) {
    var url = "/twitter/rest/timeline/" + $routeParams.userName;
    $http({
        url: url,
        method: 'GET'
    }).success(function (response) {
        $scope.timeline = response;
    });
});

sampleApp.controller('ShowUsersController', function($scope, $http) {
    var url = "/twitter/rest/user/all";
    $http({
        url: url,
        method: 'GET'
    }).success(function (response) {
        $scope.users = response;
    });
});

sampleApp.controller('ShowTweetsController', function($scope, $http) {
    var url = "/twitter/rest/tweet/all";
    $http({
        url: url,
        method: 'GET'
    }).success(function (response) {
        $scope.tweets = response;
    });
});