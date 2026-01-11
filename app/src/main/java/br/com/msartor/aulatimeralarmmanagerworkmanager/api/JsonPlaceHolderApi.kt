package br.com.msartor.aulatimeralarmmanagerworkmanager.api

import retrofit2.Response
import retrofit2.http.GET

interface JsonPlaceHolderApi {

    @GET("posts")
    suspend fun recuperarPostagens(): Response<List<Postage>>


}
