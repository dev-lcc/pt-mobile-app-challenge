package data.network

import androidx.annotation.VisibleForTesting
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*

@VisibleForTesting
object PlacesApiServiceMock {

    object GetPlaceById {
        
        fun mockEngineStatusSuccess(placeId: Long) = MockEngine { request ->
            respond(
                content = ByteReadChannel(success(placeId)),
                status = HttpStatusCode.OK,
                headers = headersOf(
                    HttpHeaders.ContentType, "application/json"
                )
            )
        }
        
        fun mockEngineStatusError(
            status: HttpStatusCode = HttpStatusCode.InternalServerError
        ) = MockEngine { request ->
            respond(
                content = ByteReadChannel.Empty,
                status = HttpStatusCode.InternalServerError,
                headers = headersOf(
                    HttpHeaders.ContentType, "application/json"
                )
            )
        }
        
        fun success(placeId: Long) = """
        {
            "id": $placeId,
            "url": "https://www.airbnb.com/rooms/49992714",
            "deeplink": "https://www.airbnb.com/rooms/49992714?check_in=2022-05-16&check_out=2022-05-17&adults=1",
            "position": 7,
            "name": "Lovely little house near Eiffel Tower 2/4P",
            "description": "Indulge in the ultimate Parisian experience at our luxurious apartment. With breathtaking views and stunning architectural details, every element of this space is designed for an unforgettable adventure. Relax in the living room and enjoy a majestic panorama of the Eiffel Tower and the Seine River, our balconies offer unobstructed views of the iconic landmark.",
            "bathrooms": 1,
            "bedrooms": 1,
            "beds": 2,
            "city": "Paris",
            "neighborhood": "Tour Eiffel - Champ de Mars",
            "images": [
                "https://a0.muscache.com/im/pictures/miso/Hosting-49992714/original/e66017bc-ca03-4a9f-99d8-76016b90d149.png?im_w=720",
                "https://a0.muscache.com/im/pictures/miso/Hosting-49992714/original/b15b5001-c34c-41a5-ab8c-9984821cebe4.png?im_w=720",
                "https://a0.muscache.com/im/pictures/miso/Hosting-49992714/original/1141d3a2-eebe-4c1c-b48d-71cad7dbffcc.png?im_w=720",
                "https://a0.muscache.com/im/pictures/miso/Hosting-49992714/original/a5f5fbde-e529-48ca-8c93-12a5221d01e6.png?im_w=720",
                "https://a0.muscache.com/im/pictures/miso/Hosting-49992714/original/00bec9c8-a57c-4f0a-a9d9-7c01a17f95ed.png?im_w=720",
                "https://a0.muscache.com/im/pictures/miso/Hosting-49992714/original/a3ac6d5a-1681-4c3d-ad1c-66934c195956.png?im_w=720"
            ],
            "hostThumbnail": "https://a0.muscache.com/im/pictures/user/82fdf5d1-a3ab-4dad-8a0c-077adfabec13.jpg?aki_policy=profile_x_medium",
            "isSuperhost": false,
            "rareFind": false,
            "lat": 48.85531,
            "lng": 2.30472,
            "persons": 4,
            "reviewsCount": 39,
            "rating": 4.41,
            "type": "Entire rental unit",
            "userId": 402363327,
            "address": "Paris, Île-de-France, France",
            "amenityIds": [
                1,
                4,
                5,
                71,
                8,
                73,
                137,
                12,
                77,
                85,
                89,
                665,
                90,
                91,
                93,
                30,
                94,
                671,
                672,
                33,
                35,
                611,
                36,
                37,
                40,
                41,
                44,
                45,
                47,
                51,
                54,
                251
            ],
            "previewAmenities": [
                "Wifi",
                "Kitchen",
                "Washer",
                "Heating"
            ],
            "cancelPolicy": "CANCEL_FLEXIBLE",
            "price": {
                "rate": 189,
                "currency": "USD",
                "total": 189,
                "priceItems": [
                    {
                        "title": "$106 x 1 night",
                        "amount": 106
                    },
                    {
                        "title": "Cleaning fee",
                        "amount": 51
                    },
                    {
                        "title": "Service fee",
                        "amount": 26
                    },
                    {
                        "title": "Occupancy taxes and fees",
                        "amount": 6
                    }
                ]
            },
            "type": "location",
            "tags": [],
            "isFavorite": true
        }
        """.trimIndent()
        
    }
    
    object GetAllPlaces {

        fun mockEngineStatusSuccess() = MockEngine { request ->
            respond(
                content = ByteReadChannel(Success),
                status = HttpStatusCode.OK,
                headers = headersOf(
                    HttpHeaders.ContentType, "application/json"
                )
            )
        }

        fun mockEngineStatusError(
            status: HttpStatusCode = HttpStatusCode.InternalServerError
        ) = MockEngine { request ->
            respond(
                content = ByteReadChannel.Empty,
                status = HttpStatusCode.InternalServerError,
                headers = headersOf(
                    HttpHeaders.ContentType, "application/json"
                )
            )
        }

        val Success = """
            {
                "limit": 20,
                "offset": 0,
                "qty": 4,
                "totalQty": 4,
                "data": [
                    {
                        "id": 49992714,
                        "url": "https://www.airbnb.com/rooms/49992714",
                        "deeplink": "https://www.airbnb.com/rooms/49992714?check_in=2022-05-16&check_out=2022-05-17&adults=1",
                        "position": 7,
                        "name": "Lovely little house near Eiffel Tower 2/4P",
                        "description": "Indulge in the ultimate Parisian experience at our luxurious apartment. With breathtaking views and stunning architectural details, every element of this space is designed for an unforgettable adventure. Relax in the living room and enjoy a majestic panorama of the Eiffel Tower and the Seine River, our balconies offer unobstructed views of the iconic landmark.",
                        "bathrooms": 1,
                        "bedrooms": 1,
                        "beds": 2,
                        "city": "Paris",
                        "neighborhood": "Tour Eiffel - Champ de Mars",
                        "images": [
                            "https://a0.muscache.com/im/pictures/miso/Hosting-49992714/original/e66017bc-ca03-4a9f-99d8-76016b90d149.png?im_w=720",
                            "https://a0.muscache.com/im/pictures/miso/Hosting-49992714/original/b15b5001-c34c-41a5-ab8c-9984821cebe4.png?im_w=720",
                            "https://a0.muscache.com/im/pictures/miso/Hosting-49992714/original/1141d3a2-eebe-4c1c-b48d-71cad7dbffcc.png?im_w=720",
                            "https://a0.muscache.com/im/pictures/miso/Hosting-49992714/original/a5f5fbde-e529-48ca-8c93-12a5221d01e6.png?im_w=720",
                            "https://a0.muscache.com/im/pictures/miso/Hosting-49992714/original/00bec9c8-a57c-4f0a-a9d9-7c01a17f95ed.png?im_w=720",
                            "https://a0.muscache.com/im/pictures/miso/Hosting-49992714/original/a3ac6d5a-1681-4c3d-ad1c-66934c195956.png?im_w=720"
                        ],
                        "hostThumbnail": "https://a0.muscache.com/im/pictures/user/82fdf5d1-a3ab-4dad-8a0c-077adfabec13.jpg?aki_policy=profile_x_medium",
                        "isSuperhost": false,
                        "rareFind": false,
                        "lat": 48.85531,
                        "lng": 2.30472,
                        "persons": 4,
                        "reviewsCount": 39,
                        "rating": 4.41,
                        "type": "Entire rental unit",
                        "userId": 402363327,
                        "address": "Paris, Île-de-France, France",
                        "amenityIds": [
                            1,
                            4,
                            5,
                            71,
                            8,
                            73,
                            137,
                            12,
                            77,
                            85,
                            89,
                            665,
                            90,
                            91,
                            93,
                            30,
                            94,
                            671,
                            672,
                            33,
                            35,
                            611,
                            36,
                            37,
                            40,
                            41,
                            44,
                            45,
                            47,
                            51,
                            54,
                            251
                        ],
                        "previewAmenities": [
                            "Wifi",
                            "Kitchen",
                            "Washer",
                            "Heating"
                        ],
                        "cancelPolicy": "CANCEL_FLEXIBLE",
                        "price": {
                            "rate": 189,
                            "currency": "USD",
                            "total": 189,
                            "priceItems": [
                                {
                                    "title": "$106 x 1 night",
                                    "amount": 106
                                },
                                {
                                    "title": "Cleaning fee",
                                    "amount": 51
                                },
                                {
                                    "title": "Service fee",
                                    "amount": 26
                                },
                                {
                                    "title": "Occupancy taxes and fees",
                                    "amount": 6
                                }
                            ]
                        },
                        "type": "location",
                        "tags": ["popular"],
                        "isFavorite": false
                    },
                    {
                        "id": 19796151,
                        "url": "https://www.airbnb.com/rooms/19796151",
                        "deeplink": "https://www.airbnb.com/rooms/19796151?check_in=2022-05-16&check_out=2022-05-17&adults=1",
                        "position": 8,
                        "name": "MAGNIFICENT VIEW EIFFEL TOWER PRIVATE STUDIO AND BALCONY",
                        "description": "The apartment is divided into 2 homes each closing with their key. The entrance to the apartment is common to both units. The accommodation for guests with a view on the Eiffel Tower is not shared, it's like a studio with a balcony. There is a bedroom area with a queen-size bed, 1 wardrobe, floor lamps, equipped kitchen, and small shower area. WC. The toilet closet is reserved for the cleaner, please do not use it. On the balcony: 1 table and 2 chairs, a space is reserved for gardening.",
                        "bathrooms": 1,
                        "bedrooms": 0,
                        "beds": 1,
                        "city": "Paris",
                        "neighborhood": "Tour Eiffel - Champ de Mars",
                        "images": [
                            "https://a0.muscache.com/im/pictures/miso/Hosting-19796151/original/e92d2f61-68b1-4977-ade9-e7bad78bd637.jpeg?im_w=720",
                            "https://a0.muscache.com/im/pictures/miso/Hosting-19796151/original/ac05b3bc-8c61-4043-8052-b6e5e4268a63.jpeg?im_w=720",
                            "https://a0.muscache.com/im/pictures/miso/Hosting-19796151/original/7f22b05c-92c0-4445-9fb1-fd4e43f8968e.jpeg?im_w=720",
                            "https://a0.muscache.com/im/pictures/miso/Hosting-19796151/original/3634c659-a275-4055-bd23-d59d3f72a64c.jpeg?im_w=720",
                            "https://a0.muscache.com/im/pictures/miso/Hosting-19796151/original/421fa4fc-e80b-4de1-8152-5443c8dfdf0e.jpeg?im_w=720",
                            "https://a0.muscache.com/im/pictures/miso/Hosting-19796151/original/b11b9c72-0b16-4ba3-8823-84a9050e8672.jpeg?im_w=720"
                        ],
                        "hostThumbnail": "https://a0.muscache.com/im/pictures/user/a5c8da13-276a-4767-835a-4652c2eb9f6e.jpg?aki_policy=profile_x_medium",
                        "isSuperhost": true,
                        "rareFind": false,
                        "lat": 48.8543,
                        "lng": 2.29572,
                        "persons": 2,
                        "reviewsCount": 371,
                        "rating": 4.86,
                        "type": "Private room in rental unit",
                        "userId": 8847681,
                        "address": "Paris, Île-de-France, France",
                        "amenityIds": [
                            1,
                            129,
                            4,
                            8,
                            137,
                            10,
                            522,
                            331,
                            77,
                            85,
                            86,
                            663,
                            280,
                            89,
                            665,
                            90,
                            91,
                            667,
                            93,
                            30,
                            94,
                            95,
                            287,
                            671,
                            96,
                            672,
                            98,
                            35,
                            611,
                            100,
                            40,
                            104,
                            42,
                            107,
                            44,
                            236,
                            45,
                            46,
                            47,
                            625,
                            500
                        ],
                        "previewAmenities": [
                            "Wifi",
                            "Kitchen",
                            "Heating"
                        ],
                        "cancelPolicy": "CANCEL_STRICT_14_WITH_GRACE_PERIOD",
                        "price": {
                            "rate": 454,
                            "currency": "USD",
                            "total": 454,
                            "priceItems": [
                                {
                                    "title": "$305 x 1 night",
                                    "amount": 305
                                },
                                {
                                    "title": "Cleaning fee",
                                    "amount": 79
                                },
                                {
                                    "title": "Service fee",
                                    "amount": 64
                                },
                                {
                                    "title": "Occupancy taxes and fees",
                                    "amount": 6
                                }
                            ]
                        },
                        "type": "location",
                        "tags": ["popular"],
                        "isFavorite": false
                    },
                    {
                        "id": 9825542,
                        "url": "https://www.airbnb.com/rooms/9825542",
                        "deeplink": "https://www.airbnb.com/rooms/9825542?check_in=2022-05-16&check_out=2022-05-17&adults=1",
                        "position": 9,
                        "name": "Charm in Heart of Paris Vaugirard",
                        "description": "Comfortable, spacious , near all amenities , on the ground floor, the 2 pieces of 50m2 consists of a large living room , a bedroom with large dressing room in the basement, a large kitchen, and a bathroom bathroom with toilet. Near the Eiffel Tower, of Champs Elysées and Paris Expo Porte de Versailles",
                        "bathrooms": 1,
                        "bedrooms": 1,
                        "beds": 4,
                        "city": "Paris",
                        "neighborhood": "Vaugirard",
                        "images": [
                            "https://a0.muscache.com/im/pictures/21f05d85-1f77-481f-9156-c590f76ddd63.jpg?im_w=720",
                            "https://a0.muscache.com/im/pictures/55368e1c-ecfa-43ec-8a87-e4695deca077.jpg?im_w=720",
                            "https://a0.muscache.com/im/pictures/6b567724-cbba-488d-9fea-7a788476a38e.jpg?im_w=720",
                            "https://a0.muscache.com/im/pictures/816cd6b8-8fc9-4df2-a9c3-06799ebd2c42.jpg?im_w=720",
                            "https://a0.muscache.com/im/pictures/b54ce644-5778-4f9b-b882-78e152e548e8.jpg?im_w=720",
                            "https://a0.muscache.com/im/pictures/61d831c6-ddde-49f8-80a4-7a55d18df8f0.jpg?im_w=720"
                        ],
                        "hostThumbnail": "https://a0.muscache.com/im/pictures/user/058cd137-7993-4847-a3e1-96d9c2a4f25d.jpg?aki_policy=profile_x_medium",
                        "isSuperhost": false,
                        "rareFind": false,
                        "lat": 48.83757,
                        "lng": 2.29666,
                        "persons": 6,
                        "reviewsCount": 414,
                        "rating": 4.41,
                        "type": "Entire rental unit",
                        "userId": 50605450,
                        "address": "Paris, Île-de-France, France",
                        "amenityIds": [
                            1,
                            2,
                            4,
                            8,
                            10,
                            12,
                            77,
                            146,
                            85,
                            86,
                            663,
                            89,
                            90,
                            93,
                            30,
                            94,
                            31,
                            96,
                            33,
                            35,
                            37,
                            39,
                            40,
                            44,
                            45,
                            46,
                            51,
                            500,
                            54
                        ],
                        "previewAmenities": [
                            "Hosted by a business",
                            "Wifi",
                            "Kitchen",
                            "Washer"
                        ],
                        "cancelPolicy": "CANCEL_MODERATE",
                        "price": {
                            "rate": 135,
                            "currency": "USD",
                            "total": 135,
                            "priceItems": [
                                {
                                    "title": "$78 x 1 night",
                                    "amount": 78
                                },
                                {
                                    "title": "Cleaning fee",
                                    "amount": 33
                                },
                                {
                                    "title": "Service fee",
                                    "amount": 19
                                },
                                {
                                    "title": "Occupancy taxes and fees",
                                    "amount": 5
                                }
                            ]
                        },
                        "type": "location",
                        "tags": ["popular"],
                        "isFavorite": false
                    },
                    {
                        "id": 15297775,
                        "url": "https://www.airbnb.com/rooms/15297775",
                        "deeplink": "https://www.airbnb.com/rooms/15297775?check_in=2022-05-16&check_out=2022-05-17&adults=1",
                        "position": 10,
                        "name": "TOUR EIFFEL VUE FANTASTIQU STUDIO ET BALCON PRIVES",
                        "description": "Located on the 9th and last floor, my apartment has a common entrance to two accommodations: 1.) the guest accommodation overlooking the Eiffel Tower is a bedroom, locked, with balcony, a bar separates the kitchen area, there is also a shower with sink. The toilets are not in the room they are in front of the door of the room and are not shared. 2.) the other dwelling belongs to the owner.",
                        "bathrooms": 1,
                        "bedrooms": 0,
                        "beds": 1,
                        "city": "Paris",
                        "neighborhood": "Tour Eiffel - Champ de Mars",
                        "images": [
                            "https://a0.muscache.com/im/pictures/b1cc348d-d8f0-44f5-8184-274fd4123243.jpg?im_w=720",
                            "https://a0.muscache.com/im/pictures/2ce0854a-da63-4d91-a0f7-7d89901846aa.jpg?im_w=720",
                            "https://a0.muscache.com/im/pictures/6debe6c0-266b-4a52-939e-9e153f950070.jpg?im_w=720",
                            "https://a0.muscache.com/im/pictures/miso/Hosting-15297775/original/04c0be83-c3f0-4352-b477-678a09a7e0ff.jpeg?im_w=720",
                            "https://a0.muscache.com/im/pictures/miso/Hosting-15297775/original/bb2988e6-c0ef-4ea1-907c-36cc6ae3aa0a.jpeg?im_w=720",
                            "https://a0.muscache.com/im/pictures/b2eb07ee-62b8-4431-9921-1087bdaf6765.jpg?im_w=720"
                        ],
                        "hostThumbnail": "https://a0.muscache.com/im/pictures/user/22cec13b-dbee-41f0-b2d8-aba0d8feb741.jpg?aki_policy=profile_x_medium",
                        "isSuperhost": true,
                        "rareFind": false,
                        "lat": 48.85354,
                        "lng": 2.29519,
                        "persons": 2,
                        "reviewsCount": 314,
                        "rating": 4.78,
                        "type": "Private room in rental unit",
                        "userId": 13090594,
                        "address": "Paris, Île-de-France, France",
                        "amenityIds": [
                            1,
                            129,
                            4,
                            8,
                            137,
                            10,
                            522,
                            77,
                            21,
                            85,
                            86,
                            663,
                            280,
                            89,
                            665,
                            90,
                            91,
                            667,
                            93,
                            30,
                            94,
                            95,
                            287,
                            671,
                            96,
                            672,
                            33,
                            35,
                            611,
                            100,
                            40,
                            104,
                            42,
                            44,
                            236,
                            45,
                            46,
                            47,
                            49,
                            50,
                            179,
                            500
                        ],
                        "previewAmenities": [
                            "Wifi",
                            "Kitchen",
                            "Washer",
                            "Heating"
                        ],
                        "cancelPolicy": "CANCEL_STRICT_14_WITH_GRACE_PERIOD",
                        "price": {
                            "rate": 454,
                            "currency": "USD",
                            "total": 454,
                            "priceItems": [
                                {
                                    "title": "$305 x 1 night",
                                    "amount": 305
                                },
                                {
                                    "title": "Cleaning fee",
                                    "amount": 79
                                },
                                {
                                    "title": "Service fee",
                                    "amount": 64
                                },
                                {
                                    "title": "Occupancy taxes and fees",
                                    "amount": 6
                                }
                            ]
                        },
                        "type": "location",
                        "tags": ["popular"],
                        "isFavorite": false
                    }
                ]
            }
        """.trimIndent()
    }
    
}