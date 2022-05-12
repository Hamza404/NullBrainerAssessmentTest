package com.app.nullbrainerassessment.network;

import com.google.gson.JsonElement;

import org.json.JSONObject;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("Mobile/RegisterUser")
    @FormUrlEncoded
    Observable<JsonElement> createUserAccount(@Field("email") String email,
                                              @Field("password") String password,
                                              @Field("firstName") String firstName,
                                              @Field("lastName") String lastName,
                                              @Field("userCellNo") String userCellNo,
                                              @Field("mailingAddress") String mailingAddress);

    @POST("/Mobile/doesUserExist")
    @FormUrlEncoded
    Observable<JsonElement> doesUserExist(@Field("email") String email);

    @POST("/Mobile/UserLogin")
    @FormUrlEncoded
    Observable<JsonElement> userLogin(@Field("email") String email,
                                      @Field("password") String password,
                                      @Field("pushNotificationToken") String pushNotificationToken);

}