'use strict';

angular.module("todoApp.controllers", [])
    .controller("TweetListCtrl", function($scope, Todo){
    	$scope.todos = Todo.query();
    	$scope.$on("updateTodos", function (event, todo) {
    		$scope.todos.push(todo);
        });

		$scope.remove = function(idx){
			$scope.todos.forEach(function(tweet, index) {
				if (tweet.idd == idx) {
					$scope.todos[index].$remove({id: idx});
					$scope.todos.splice(index, 1);
				}
			});
		};
    })
    .controller("TweetDetailCtrl", function($scope, $routeParams, Todo){
        $scope.todo = Todo.get({id: $routeParams.tweetId});
    })
    .controller("TweetAddCtrl", function($rootScope, $scope, $location, Todo){
    	$scope.user = {};
		$scope.user.name = "";
    	$scope.text = "";
    	$scope.replyId = "";
		$scope.retweetId = "";

    	$scope.add = function(){
    		var tweet = new Todo();
			tweet.user = {};
    		tweet.user.name = $scope.username;
    		tweet.text = $scope.text;
    		tweet.replyId = $scope.replyId;
			tweet.retweetId = $scope.retweetId;
    		tweet.$save(function(){
    			$rootScope.$broadcast("updateTodos", tweet);
				$location.path("/tweet");
    		});
    	};
    })
    .controller("TweetUpdateCtrl", function($scope, $routeParams, $location, Todo){
    	$scope.todo = Todo.get({id: $routeParams.tweetId});
    	$scope.update = function(){
    		$scope.todo.$update(function(){
                $location.path("/tweet/"+$scope.todo.idd);
            });
    	};
    })
	.controller("UserUpdateCtrl", function($scope, $routeParams, $location, User){
		$scope.user = User.get({userName: $routeParams.userName});
		console.log("WTF");
		$scope.update = function(){
			$scope.user.$update(function(){
				$location.path("/user/"+$scope.user.name);
			});
		};
	})
	.controller("UserListCtrl", function($scope, User){
		$scope.users = User.query();
		$scope.$on("updateUsers", function (event, user) {
			$scope.users.push(user);
		});

		$scope.remove = function(userName){
			$scope.users.forEach(function(user, index) {
				if (user.name == userName) {
					$scope.users[index].$remove({userName: userName});
					$scope.users.splice(index, 1);
				}
			});
		};
	})
	.controller("UserDetailCtrl", function($scope, $routeParams, User){
		$scope.user = User.get({userName: $routeParams.userName});
	})
	.controller("UserAddCtrl", function($rootScope, $scope, $location, User){
		$scope.name = "";
		$scope.firstName = "";
		$scope.lastName = "";
		$scope.add = function(){
			var user = new User();
			user.name = $scope.name;
			user.firstName = $scope.firstName;
			user.lastName = $scope.lastName;
			user.$save(function(){
				$rootScope.$broadcast("updateUsers", user);
				$location.path("/user");
			});
		};
	})
	.controller("UserTimelineCtrl", function($scope, $routeParams, Timeline){
		$scope.timeline = Timeline.get({userName: $routeParams.userName});
	});