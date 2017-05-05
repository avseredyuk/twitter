'use strict';

angular.module("todoApp.services", ["ngResource"])
    .factory("Todo", function($resource){
    	return $resource('rest/tweet/:id', {id:'@id'}, {
    		update: {
                method: 'PUT'
              }
    	});
    })
    .factory("User", function($resource){
        return $resource('rest/user/:userName', {userName:'@userName'}, {
            update: {
                method: 'PUT'
            }
        });
    })
    .factory("Timeline", function($resource){
        return $resource('rest/timeline/:userName', {userName:'@userName'}, {
            update: {
                method: 'PUT'
            }
        });
    });