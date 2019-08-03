package com.example.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.network.ApiInterface;
import com.example.network.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;

     TextView ipAddressTextView;
    private ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         ipAddressTextView=(TextView)findViewById(R.id.ip) ;
        final TextView cityTextView=(TextView)findViewById(R.id.city) ;
        final TextView countryTextView=(TextView)findViewById(R.id.country);

        progressBar=(ProgressBar) findViewById(R.id.pbar);

        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);




//        checkUserValidity(new User("hasan","123"));
        getJokeFromServer("hasan");


    }


    /**
     *
     * @param userCredential
     */
    public void checkUserValidity(User userCredential){

        Log.d("aaa", "checkUserValidity: ");
        progressBar.setVisibility(View.VISIBLE);
        Call<ServerResponse> call = apiInterface.getUserValidity(userCredential);

        call.enqueue(new Callback<ServerResponse>() {

            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                progressBar.setVisibility(View.GONE);
                ServerResponse validity = response.body();
                ipAddressTextView.setText(validity.getMessage());
                Toast.makeText(getApplicationContext(), validity.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
//                Log.e(TAG, t.toString());
                progressBar.setVisibility(View.GONE);
                Log.d("aaa", "onFailure: "+t.getMessage());
                Log.d("aaa", "onFailure: ");
            }
        });
    }


    private void getJokeFromServer(String userId) {
        Log.d("network", "getJokeFromServer: "+"function call");

        Call<ServerResponse> call = apiInterface.getJoke(userId);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                progressBar.setVisibility(View.GONE);
                ServerResponse validity = response.body();
                ipAddressTextView.setText(validity.getMessage());

                Log.d("network", "getJokeFromServer: "+"successful");


            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e("aaa", t.toString());
                Log.d("network", "getJokeFromServer: "+"ERROR "+t.toString());

            }
        });
    }


    /**
     *
     * @param a as integer
     * @return a
     */
    public int aaa(int a){

        return a;
    }
}
