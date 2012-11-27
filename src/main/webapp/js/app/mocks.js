/* -------------------------------------------------- */
//
//  Mini Profile
//
/* -------------------------------------------------- */

ventalandia.test.mocks.miniProfile = {
    "nickname": "unpetiso",
    "meliId": 123456,
    "name": "Unpe",
    "surname": "Tiso",
    "sellerReputationLevel": 3,
    "thumbnailUrl": "/img/icon_guest.jpeg"
};

/* -------------------------------------------------- */
//
//  Newsfeed entries
//
/* -------------------------------------------------- */

ventalandia.test.mocks.news = [{
    "id": 5006,
    "buyer": {
        "id": 12345,
        "nickname": "ICLACREYO"
    },
    "date": "2012-10-10T13:10:52.067",
    "type": "QUESTION",
    "item": {
        "id": "MLA434525953",
        "title": "Bicicleta rodado 26"
    },
    "entityId": 0,
    "answered": true
}, {
    "id": 5006,
    "buyer": {
        "id": 12345,
        "nickname": "juancito"
    },
    "date": "2012-10-10T13:10:52.067",
    "type": "QUESTION",
    "item": {
        "id": "MLA434525953",
        "title": "Bicicleta rodado 26"
    },
    "entityId": 0,
    "answered": false
}, {
    "id": 5006,
    "buyer": {
        "id": 12345,
        "nickname": "lulu"
    },
    "date": "2012-10-10T13:10:52.067",
    "type": "QUESTION",
    "item": {
        "id": "MLA434525953",
        "title": "Bicicleta rodado 26"
    },
    "entityId": 0,
    "answered": true
}, {
    "id": 5006,
    "buyer": {
        "id": 12345,
        "nickname": "traviesa"
    },
    "date": "2012-10-10T13:10:52.067",
    "type": "QUESTION",
    "item": {
        "id": "MLA434525953",
        "title": "Bicicleta rodado 26"
    },
    "entityId": 0,
    "answered": true
}, {
    "id": 5006,
    "buyer": {
        "id": 12345,
        "nickname": "ICLACREYO"
    },
    "date": "2012-10-10T13:10:52.067",
    "type": "QUESTION",
    "item": {
        "id": "MLA434525953",
        "title": "Bicicleta rodado 26"
    },
    "entityId": 0,
    "answered": false
}, {
    "id": 5006,
    "buyer": {
        "id": 12345,
        "nickname": "ICLACREYO"
    },
    "date": "2012-10-10T13:10:52.067",
    "type": "QUESTION",
    "item": {
        "id": "MLA434525953",
        "title": "Bicicleta rodado 26"
    },
    "entityId": 0,
    "answered": false
}, {
    "id": 5006,
    "buyer": {
        "id": 12345,
        "nickname": "ICLACREYO"
    },
    "date": "2012-10-10T13:10:52.067",
    "type": "QUESTION",
    "item": {
        "id": "MLA434525953",
        "title": "Bicicleta rodado 26"
    },
    "entityId": 0,
    "answered": false
}, {
    "id": 5008,
    "buyer": {
        "id": 12345,
        "nickname": "ICLACREYO"
    },
    "date": "2012-10-10T13:10:52.067",
    "type": "QUESTION",
    "item": {
        "id": "MLA434525953",
        "title": "Bicicleta rodado 26"
    },
    "entityId": 0,
    "answered": false
}, {
    "id": 5006,
    "buyer": {
        "id": 12345,
        "nickname": "ICLACREYO"
    },
    "date": "2012-10-10T13:10:52.067",
    "type": "QUESTION",
    "item": {
        "id": "MLA434525953",
        "title": "Bicicleta rodado 26"
    },
    "entityId": 0,
    "answered": false
}];

/* -------------------------------------------------- */
//
//  Newsfeed entries for update (pulling)
//
/* -------------------------------------------------- */

ventalandia.test.mocks.newsUpdate = [{
    "id": 5007,
    "buyer": {
        "id": 12345,
        "nickname": "COMEGATO"
    },
    "date": "2012-10-10T13:10:53.067",
    "type": "QUESTION",
    "item": {
        "id": "MLA434525953",
        "title": "Bicicleta rodado 26"
    },
    "entityId": 0,
    "answered": false
},{
    "id": 5008,
    "buyer": {
        "id": 12345,
        "nickname": "ble"
    },
    "date": "2012-10-10T13:10:53.067",
    "type": "QUESTION",
    "item": {
        "id": "MLA434525953",
        "title": "Bicicleta rodado 26"
    },
    "entityId": 0,
    "answered": false
}];
/* -------------------------------------------------- */
//
//  News details
//
/* -------------------------------------------------- */

ventalandia.test.mocks.newsDetails = {
    "item": {
        "title": "Camiseta De Boca Impecable- Test Item",
        "pictureUrl": null
    },
        "buyer": {
        "pictureUrl": null,
        "nickname": "TEST6102"
    },
        "questions": [{
            "id": 2518148373,
            "question": {
                "text": "cuanto me sale hasta Dinamarca?",
                "date": "2012-10-10T13:10:52.067"
            },
            "answer": {
                "text": "1000 pesos mas",
                "date": "2012-10-10T13:10:52.067"
            }
    }, {
            "id": 2516147342,
            "question": {
                "text": "tene' una con la cara de cabaña'?",
                "date": "2012-10-10T13:10:52.067"
            }
    }]
}

/* -------------------------------------------------- */
//
//  News summary
//
/* -------------------------------------------------- */

ventalandia.test.mocks.newsSummary = {
    "new_questions": 10,
    "user_id": 118519141
}

/* -------------------------------------------------- */
//
//  Configure mock http service
//
/* -------------------------------------------------- */

ventalandia.test.mocks.configureBackend = function(http) {
    http.whenPOST().respond(200);    
    http.whenGET('/api/users/me').respond(ventalandia.test.mocks.miniProfile);
    http.whenGET('/api/news').respond(ventalandia.test.mocks.news);
    http.whenGET('/api/news/5006').respond(ventalandia.test.mocks.newsDetails);
    http.whenGET('/api/news/5007').respond(ventalandia.test.mocks.newsDetails);
    http.whenGET('/api/news/5008').respond(ventalandia.test.mocks.newsDetails);
    http.whenGET('/api/news/summary').respond(ventalandia.test.mocks.newsSummary);

    http.whenGET('/api/news?since=2012-10-10T13%3A11%3A52.000').respond(ventalandia.test.mocks.newsUpdate);
    http.whenGET('/api/news?since=2012-10-10T13%3A11%3A53.000').respond(null);
}
