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
    "sellerReputationLevel": 3
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
    "entityId": 0
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
    "entityId": 0
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
    "entityId": 0
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
    "entityId": 0
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
    "entityId": 0
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
    "entityId": 0
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
    "entityId": 0
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
    "entityId": 0
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
    "entityId": 0
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
    http.whenGET('/api/news/summary').respond(ventalandia.test.mocks.newsSummary);
    http.whenGET('/api/news/summary').respond(ventalandia.test.mocks.newsSummary);
}
