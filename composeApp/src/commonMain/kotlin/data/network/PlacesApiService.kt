package data.network

import data.network.dto.place.GetPlacesResponse
import data.network.dto.place.PlaceDTO
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

interface PlacesApiService {

    /**
     * [GET] /listplaces/all
     */
    suspend fun getAllPlaces(): GetPlacesResponse

    /**
     * [GET] /places/{id}
     */
    suspend fun getPlaceById(id: Long): PlaceDTO

}

class PlacesApiServiceImpl(
    // This can be different based on buildType OR buildFlavor
    private val restApiEndpoint: String = "https://private-7f9600-parceltrackermobileappchallenge.apiary-mock.com",
    private val ktorClient: HttpClient
) : PlacesApiService {

    override suspend fun getAllPlaces(): GetPlacesResponse {
        try {
            val response = ktorClient.request(
                "${restApiEndpoint}/listplaces/all"
            ) {
                method = HttpMethod.Get
                contentType(ContentType.Application.Json)
            }

            return response.body()
        } catch (
            // Multiple types of error(s) can be defined here (i.e. based on error code received, etc.)
            // OR generic network or server errors
            err: Throwable
        ) {
            err.printStackTrace()
            throw err
        }
    }

    override suspend fun getPlaceById(id: Long): PlaceDTO {
        try {
            val response = ktorClient.request(
                "${restApiEndpoint}/places/${id.toString(10)}"
            ) {
                method = HttpMethod.Get
                contentType(ContentType.Application.Json)
            }

            return response.body()
        } catch (
            // Multiple types of error(s) can be defined here (i.e. based on error code received, etc.)
            // OR generic network or server errors
            err: Throwable
        ) {
            err.printStackTrace()
            throw err
        }
    }


}
