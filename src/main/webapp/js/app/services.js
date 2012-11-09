/* -------------------------------------------------- */
//
//  Registering services for IoC
//
/* -------------------------------------------------- */

angular.module('vldApp.services', [])
       .factory('UserService', ventalandia.service.UserService)
       .factory('NewsService', ventalandia.service.NewsService);


/* -------------------------------------------------- */
//
//  The Sevices
//
/* -------------------------------------------------- */

ventalandia.service.UserService = function($cookies, $http) {
	var UserService = ventalandia.service.UserService;

	/**
	 * @param {function(ventalandia.model.MiniProfile)} onSuccess
	 */
	UserService.getMyProfile = function(onSuccess) {
		$http({method: "GET", url: "/api/users/me", 
			   headers: {"x-vtd-token": $cookies["vtd_token"]}
		    }).success(function(data, status, headers, config) {
		    	onSuccess(ventalandia.model.MiniProfile.fromObject(data));
			}).error(function(data, status, headers, config) {
				console.log("[ERROR] - Unable to get profile data");
				console.log(JSON.stringify(data)); // invoke a general ui error handler
			});		
	}
}

ventalandia.service.NewsService = function($cookies, $http) {
	var NewsService = ventalandia.service.NewsService;

	/**
	 * @param {function(ventalandia.model.Newsfeed)} onSuccess
	 */
	NewsService.getMyNewsfeed = function(onSuccess) {
		$http({method: "GET", url: "/api/news", 
			   headers: {"x-vtd-token": $cookies["vtd_token"]}
		    }).success(function(data, status, headers, config) {
				onSuccess(ventalandia.model.Newsfeed.fromObject(data));
			}).error(function(data, status, headers, config) {
				console.log("[ERROR] - Unable to get newsfeed");
				console.log(JSON.stringify(data)); // invoke a general ui error handler
			});
	}

	/**
	 * @param {function(ventalandia.model.Newsfeed)} onSuccess
	 */
	NewsService.getNewsDetails = function(newsId, onSuccess) {
		$http({method: "GET", url: "/api/news/" + newsId, 
			   headers: {"x-vtd-token": $cookies["vtd_token"]}
		    }).success(function(data, status, headers, config) {
		    	onSuccess(ventalandia.model.NewsDetails.fromObject(data));
			}).error(function(data, status, headers, config) {
				console.log("[ERROR] - Unable to get newsfeed details for id " + entry.id);
				console.log(JSON.stringify(data)); // invoke a general ui error handler
			});
	}

	/**
	 * @param {function(ventalandia.model.Summary)} onSuccess
	 */
	NewsService.getMyNewsSummary = function(onSuccess) {
		$http({method: "GET", url: "/api/news/summary", 
			   headers: {"x-vtd-token": $cookies["vtd_token"]}
		    }).success(function(data, status, headers, config) {
		    	onSucess(ventalandia.model.Summary.fromObject(data));
			}).error(function(data, status, headers, config) {
				console.log("[ERROR] - Unable to get news summary ");
				console.log(JSON.stringify(data)); // invoke a general ui error handler
			});
	}

	/**
	 * @param {string} newsId
	 * @param {string} answer
	 * @param {function()} onSuccess
	 */
	NewsService.sendAnswer = function(newsId, answer, onSuccess) {
		var theBody = {"question_id": newsId, "text": answer};
		$http({method: "POST", url: "/api/answers/", 
			   headers: {"x-vtd-token": $cookies["vtd_token"],
			             "Content-Type": "application/json"},
			   data: theBody
		    }).success(function(data, status, headers, config) {
		    	onSuccess();
		    	console.log("Just sent answer: " + JSON.stringify(theBody));
			}).error(function(data, status, headers, config) {
				console.log("[ERROR] - Unable send answer '" + JSON.stringify(question) + "'");
				console.log(JSON.stringify(data)); // invoke a general ui error handler
			});			
	}
}