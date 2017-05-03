'use strict';

angular.module('todoApp', [
	'ngRoute',
    'todoApp.services',
    'todoApp.controllers'
]).config(function($routeProvider) {
	  $routeProvider.when('/', {templateUrl: 'partial/tweet/list.html', controller: 'TweetListCtrl'});
	  $routeProvider.when('/tweet/:tweetId', {templateUrl: 'partial/tweet/detail.html', controller: 'TweetDetailCtrl'});
	  $routeProvider.when('/update/:tweetId', {templateUrl: 'partial/tweet/update.html', controller: 'TweetUpdateCtrl'});
	  $routeProvider.when('/user', {templateUrl: 'partial/user/list.html', controller: 'UserListCtrl'});
	  $routeProvider.when('/user/:userName', {templateUrl: 'partial/user/detail.html', controller: 'UserDetailCtrl'});
	  $routeProvider.otherwise({redirectTo: '/'});
	});
