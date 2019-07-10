package com.hcitest.testapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.hcitest.testapi.adapter.ArticleAdapter;
import com.hcitest.testapi.adapter.ProductAdapter;
import com.hcitest.testapi.interfaces.ApiInterface;
import com.hcitest.testapi.model.Data;
import com.hcitest.testapi.model.Item;
import com.hcitest.testapi.service.ApiService;
import com.hcitest.testapi.ui.Messagebox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    ApiInterface mApiInterface;
    ProductAdapter productAdapter;
    ArticleAdapter articleAdapter;

    RecyclerView gridProduct,listArticle;
    TextView sectionArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridProduct = (RecyclerView) findViewById(R.id.grdView);
        gridProduct.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        gridProduct.setLayoutManager(layoutManager);
        listArticle = (RecyclerView) findViewById(R.id.lstView);
        listArticle.setHasFixedSize(true);
        LinearLayoutManager horizontalManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listArticle.setLayoutManager(horizontalManager);

        mApiInterface = ApiService.getClient().create(ApiInterface.class);
        sectionArticle = (TextView) findViewById(R.id.article_section_title);

        Messagebox.showBusyDialog(MainActivity.this, new Runnable() {
            @Override
            public void run() {
                getResponse();
            }
        }, new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    private void getResponse(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<String> call = api.getData();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        setup(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void setup(String response){

        try {
            JSONObject obj = new JSONObject(response);
            ArrayList<Data> listData = new ArrayList<>();
            JSONArray dataArray  = obj.getJSONArray("data");

            for (int i = 0; i < dataArray.length(); i++) {
                Data data = new Data();
                JSONObject dataobj = dataArray.getJSONObject(i);

                data.setSection(dataobj.getString("section"));
                if(data.getSection().equalsIgnoreCase("articles")) {
                    data.setSectionTitle(dataobj.getString("section_title"));
                } else {
                    data.setSectionTitle("");
                }

                JSONArray itemArr  = dataobj.getJSONArray("items");
                List<Item> itemList = new ArrayList<>();
                for(int j=0; j<itemArr.length(); j++) {
                    Item item = new Item();
                    JSONObject dataobj2 = itemArr.getJSONObject(j);

                    if(data.getSection().equalsIgnoreCase("articles")) {
                        item.setArticleTitle(dataobj2.getString("article_title"));
                        item.setArticleImage(dataobj2.getString("article_image"));
                        item.setProductName("");
                        item.setProductImage("");
                    } else {
                        item.setArticleTitle("");
                        item.setArticleImage("");
                        item.setProductName(dataobj2.getString("product_name"));
                        item.setProductImage(dataobj2.getString("product_image"));
                    }

                    item.setLink(dataobj2.getString("link"));
                    itemList.add(item);

                }

                data.setItems(itemList);

                if(data.getSection().equalsIgnoreCase("products")) {
                    productAdapter = new ProductAdapter(itemList, MainActivity.this);
                    gridProduct.setAdapter(productAdapter);
                } else {
                    ((TextView) findViewById(R.id.article_section_title)).setText(data.getSectionTitle());
                    articleAdapter = new ArticleAdapter(itemList, MainActivity.this);
                    listArticle.setAdapter(articleAdapter);
                }

                listData.add(data);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
