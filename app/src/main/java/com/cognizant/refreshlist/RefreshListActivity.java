package com.cognizant.refreshlist;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cognizant.refreshlist.com.cognizant.CustomAdapters.ListviewAdapter;
import com.cognizant.refreshlist.com.cognizant.utils.ApiUrlManager;
import com.cognizant.refreshlist.com.cognizant.utils.AppController;
import com.cognizant.refreshlist.com.cognizant.utils.ListviewDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RefreshListActivity extends AppCompatActivity {
    SwipeRefreshLayout refreshContainer;

    ListView listView;
    ListviewAdapter listviewAdapter;

    ListviewDataModel listviewDataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_list);

        listviewDataModel = new ListviewDataModel();
        _getCountryDetails();

        listView = (ListView) findViewById(R.id.listView);

        //Instantiate ListView Adapter
        listviewAdapter = new ListviewAdapter(RefreshListActivity.this, listviewDataModel);

        //Bind Adapter with ListView Component
        listView.setAdapter(listviewAdapter);

        refreshContainer = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);

        //Add Color for refreshing Progress
        refreshContainer.setColorSchemeColors(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //Bind RefreshListener to fetch new data from server
        refreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                _getCountryDetails();
            }
        });
    }

    //Method to fetch the data from server
    private void _getCountryDetails() {

        JsonObjectRequest requestConuntryDetails = new JsonObjectRequest(Request.Method.GET, ApiUrlManager.Get_Country_Details, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                //Log.i("RESP",jsonObject.toString());
                listviewDataModel.clearData();

                //Parse the response and set it to the datamodel
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject rowJsonObject = jsonArray.getJSONObject(i);

                        String title = rowJsonObject.getString("title");
                        String description = rowJsonObject.getString("description");
                        String url = rowJsonObject.getString("imageHref");

                        //Add Parsed data into DataModels
                        listviewDataModel.setTitleList(title);
                        listviewDataModel.setDescriptionList(description);
                        listviewDataModel.setImageUrlList(url);

                    }
                    refreshContainer.setRefreshing(false);

                } catch (JSONException exe) {
                    Log.e("PARSING EXE", exe.getMessage());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("ERR", volleyError.toString());
            }
        });
        AppController.getInstance().addToRequestQueue(requestConuntryDetails, "requestConuntryDetails");

    }
}
