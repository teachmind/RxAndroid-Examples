package me.shuza.rxdemoapicall;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * :=  created by:  Shuza
 * :=  create date:  11/5/2017
 * :=  (C) CopyRight Shuza
 * :=  www.shuza.me
 * :=  shuza.sa@gmail.com
 * :=  Fun  :  Coffee  :  Code
 **/

public interface ApiService {

    @POST("dummy_response.php")
    Observable<ResponsePojo> getDataFromAPI();
}
