package com.example.appbanhang.Retrofit;

import com.example.appbanhang.Model.NotiRespone;
import com.example.appbanhang.Model.NotiSendData;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiPushNotification {
    @Headers(
            {
                    "Content-Type: application/json",
                    "Authorization: key= AAAA9WxyQGc:APA91bE0czqEFbNuhKZ21ZafpkjdItqKGcI3NSZ4FfsTFzGeSLJzK6J8SPoaixuV8rr3vW-FX6Gbxio5srSdwW39ScgXY1STfV47tOcy5dj3Q7_kD0W_x77INZRlNsBi388Udjmc687T"

            }
    )

    @POST("fcm/send")
    Call<NotiRespone> sendNotification(@Body NotiSendData data);
}
