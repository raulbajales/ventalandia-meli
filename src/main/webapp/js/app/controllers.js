/* Controllers */

ventalandia.controller.MiniProfileController = function($scope, $cookies, $http) {
	$http({method: "GET", url: "/api/users/me", 
		   headers: {"x-vtd-token": $cookies["vtd_token"]}
	    }).success(function(data, status, headers, config) {
			$scope.miniProfile = ventalandia.model.MiniProfile.fromObject(data);
			$scope.reputationClass = ventalandia.ui.reputationClassFor($scope.miniProfile.sellerReputationLevel);
	 		$scope.$emit('broadcast', {id: "miniProfileLoaded", data: $scope.miniProfile});
		}).error(function(data, status, headers, config) {
			console.log("[ERROR] - Unable to get profile data");
			console.log(JSON.stringify(data)); // invoke a general ui error handler
		});
}

ventalandia.controller.NewsController = function($scope, $cookies, $http) {
	$http({method: "GET", url: "/api/news", 
		   headers: {"x-vtd-token": $cookies["vtd_token"]}
	    }).success(function(data, status, headers, config) {
	    	var newsfeed = ventalandia.model.Newsfeed.fromObject(data);

			/* Mock data for test: */
			/*
			var newsfeed = ventalandia.model.Newsfeed.fromObject([{"id":5006,"buyer":{"id":12345,"nickname":"ICLACREYO"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"juancito"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"lulu"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"traviesa"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"ICLACREYO"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"ICLACREYO"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"ICLACREYO"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"ICLACREYO"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"ICLACREYO"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0}]);
			*/
			$scope.newsfeed = newsfeed.hasEntries() ? newsfeed : null;			
		}).error(function(data, status, headers, config) {
			console.log("[ERROR] - Unable to get newsfeed");
			console.log(JSON.stringify(data)); // invoke a general ui error handler
		});

	$scope.showNewsDetails = function($event, entry) {
		ventalandia.ui.newsfeed.activate($event.currentTarget);
 		$scope.$emit('broadcast', {id: "showNewsDetails", data: entry});
	}		
}

ventalandia.controller.NewsDetailsController = function($scope, $cookies, $http) {
	$scope.$on('showNewsDetails', function(event, entry) {
		$http({method: "GET", url: "/api/news/" + entry.id, 
			   headers: {"x-vtd-token": $cookies["vtd_token"]}
		    }).success(function(data, status, headers, config) {
		    	var newsDetails = ventalandia.model.NewsDetails.fromObject(data);

				/* Mock data for test: */
				/*
				var newsDetails = ventalandia.model.NewsDetails.fromObject({
				    "item": {
				        "title": "Bicicleta rodado 26",
				        "pictureUrl": "http://host/bici.jpg"
				    },
				    "buyer": {
				        "pictureUrl": "http://host/photo.jpg",
				        "nickname": "LALO_LANDA"
				    },
				    "questions": [
				         {
	                        "id": "2496684004",
	                        "text": "hola cuanto sale el envio hasta neuquen? saludos",
	                        "answer": "Hola! hasta tu domiclio sale $75 por correo argentino"
	                     },
				         {
	                        "id": "2496684005",
	                        "text": "una preg mas",
	                        "answer": "Hola! tu respuesta"
	                     },
	                     {
	                        "id": "2496684006",
	                        "text": "otra preg",
	                        "answer": null
	                     }
				    ]
				});
				*/
				$scope.newsDetails = newsDetails;
			}).error(function(data, status, headers, config) {
				console.log("[ERROR] - Unable to get newsfeed details for id " + entry.id);
				console.log(JSON.stringify(data)); // invoke a general ui error handler
			});
	});

	$scope.$on('miniProfileLoaded', function(event, miniProfile) {
		$scope.miniProfile = miniProfile;
	});

	$scope.sendAnswer = function(question) {
		if (!question.answer) return;
		var theBody = {"question_id": question.id, "text": question.answer};
		$http({method: "POST", url: "/api/answers/", 
			   headers: {"x-vtd-token": $cookies["vtd_token"],
			             "Content-Type": "application/json"},
			   data: theBody
		    }).success(function(data, status, headers, config) {
		    	question.answered = true;
		    	console.log("Just sent answer: " + JSON.stringify(theBody));
			}).error(function(data, status, headers, config) {
				console.log("[ERROR] - Unable send answer '" + JSON.stringify(question) + "'");
				console.log(JSON.stringify(data)); // invoke a general ui error handler
			});			
	};	
}


ventalandia.controller.TopbarController = function($scope, $cookies, $http) {
	$http({method: "GET", url: "/api/news/summary", 
		   headers: {"x-vtd-token": $cookies["vtd_token"]}
	    }).success(function(data, status, headers, config) {
	    	var summary = ventalandia.model.Summary.fromObject(data);

			/* Mock data for test: */
			/*
			var summary = ventalandia.model.Summary.fromObject({"new_questions":10,"user_id":118519141});
			*/

			$scope.summary = summary;
		}).error(function(data, status, headers, config) {
			console.log("[ERROR] - Unable to get news summary ");
			console.log(JSON.stringify(data)); // invoke a general ui error handler
		});

	$scope.confirmLogout = function() {
        ventalandia.ui.confirm("Salir", "Esta seguro de que desea salir de Ventalandia?", "Cancelar", "Ok", function() {
          document.cookie = 'vtd_token=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
          document.location.href="/";
          document.location.reload();
        });
	}
}

ventalandia.controller.CustomersController = function() {}

ventalandia.controller.ProductsController = function() {}
