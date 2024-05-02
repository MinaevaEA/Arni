package com.arni.remote

import com.arni.remote.model.request.EditItemRequest
import com.arni.remote.model.body.RequestItemBody
import com.arni.remote.model.request.ContainerItemRequest
import com.arni.remote.model.response.CheckedResponse
import com.arni.remote.model.response.DataWrapper
import com.arni.remote.model.response.DictionaryResponse
import com.arni.remote.model.response.EditRequestResponse
import com.arni.remote.model.response.ListRequestResponse
import com.arni.remote.model.response.TokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("auth/")
    suspend fun getLogin(@Header("Authorization") auth: String): DataWrapper<TokenResponse>
    @GET("getrefs")
    suspend fun getRefs(): DataWrapper<DictionaryResponse>
    @GET("getitems")
    suspend fun getAllRequest(
        @Query("limit") number: Int,
        @Query("listGUID") listId: String? ,
       // @Query("directionup") directionup: Int = 0,
        @Query("divisionitem") divisionitem: String ,
      //  @Query("statusitem") statusitem: String = "020d8b77-9e38-416a-924b-9378eeaeb371",
      //  @Query("dateitembegin") dateitembegin: String = "",
      //  @Query("dateitemend") dateitemend: String = "",
      //  @Query("getdeleteditem") getdeleteditem: Int = 1,
        @Query("pointref") pointref: String?,
        @Query("pointdate") pointdate: String?
    ): DataWrapper<ListRequestResponse>
    @GET("getup")
    suspend fun getAllChangeRequest(
        @Query("limit") number: Int,
        @Query("listid") listId: String? ,
        // @Query("directionup") directionup: Int = 0,
        @Query("divisionitem") divisionitem: String ,
        //  @Query("statusitem") statusitem: String = "020d8b77-9e38-416a-924b-9378eeaeb371",
        //  @Query("dateitembegin") dateitembegin: String = "",
        //  @Query("dateitemend") dateitemend: String = "",
        //  @Query("getdeleteditem") getdeleteditem: Int = 1,
        @Query("onlycheck") onlycheck: Boolean?,
        @Query("pointref") pointref: String?,
        @Query("pointdate") pointdate: String?
    ): DataWrapper<ListRequestResponse>

    @GET("checkitem")
    suspend fun getCheck(
        @Query("listid") listid: String?,
        @Query("itemref") itemref: String,
    ): DataWrapper<CheckedResponse>

//TODO
    @PUT("changeitem")
    suspend fun getChangeItem(
        @Query("listid") listid: String,
        @Query("itemref") itemref: String,
        @Body body: ContainerItemRequest
    ): DataWrapper<CheckedResponse>


    @POST("additem")
    suspend fun addRequest(
        @Query("listid") listid: String?,
        @Body body: ContainerItemRequest
    ): DataWrapper<Any>
}
