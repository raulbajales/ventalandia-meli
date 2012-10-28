/* Controllers */

ventalandia.controller.MiniProfileController = function($scope, $cookies, $http) {
	$http({method: "GET", url: "/api/users/me", 
		   headers: {"x-vtd-token": $cookies["vtd_token"]}
	    }).success(function(data, status, headers, config) {
			$scope.miniProfile = ventalandia.model.MiniProfile.fromObject(data);
			$scope.reputationClass = ventalandia.ui.reputationClassFor($scope.miniProfile.sellerReputationLevel);
			$scope.$emit("miniprofile-ready", $scope.miniProfile);
		}).error(function(data, status, headers, config) {
			console.log("[ERROR] - Unable to get profile data");
			console.log(JSON.stringify(data)); // invoke a general ui error handler
		});
}

ventalandia.controller.NewsController = function($scope, $cookies, $http) {
	$scope.$on("miniprofile-ready", function(event, miniProfile) {
		console.log("lalala!");
		$scope.miniProfile = miniProfile;
	});
	$http({method: "GET", url: "/api/news", 
		   headers: {"x-vtd-token": $cookies["vtd_token"]}
	    }).success(function(data, status, headers, config) {
	    	//var newsfeed = ventalandia.model.Newsfeed.fromObject(data);

			/* Mock data for test: */
			
			var newsfeed = ventalandia.model.Newsfeed.fromObject([{"id":5006,"buyer":{"id":12345,"nickname":"ICLACREYO"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"juancito"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"lulu"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"traviesa"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"ICLACREYO"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"ICLACREYO"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"ICLACREYO"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"ICLACREYO"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0},
			{"id":5006,"buyer":{"id":12345,"nickname":"ICLACREYO"},"date":"2012-10-10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0}]);

			$scope.newsfeed = newsfeed.hasEntries() ? newsfeed : null;			
		}).error(function(data, status, headers, config) {
			console.log("[ERROR] - Unable to get newsfeed");
			console.log(JSON.stringify(data)); // invoke a general ui error handler
		});

	$scope.showNewsDetails = function($event, entry) {
		$(".newsfeed .news").removeClass("active");
		$($event.currentTarget).addClass("active");
		$http({method: "GET", url: "/api/news/" + entry.id, 
			   headers: {"x-vtd-token": $cookies["vtd_token"]}
		    }).success(function(data, status, headers, config) {
		    	//var newsDetails = ventalandia.model.NewsDetails.fromObject(data);
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

				$scope.newsDetails = newsDetails;
			}).error(function(data, status, headers, config) {
				console.log("[ERROR] - Unable to get newsfeed details for id " + entry.id);
				console.log(JSON.stringify(data)); // invoke a general ui error handler
			});
	}		
}

ventalandia.controller.CustomersController = function() {}

ventalandia.controller.ProductsController = function() {}
