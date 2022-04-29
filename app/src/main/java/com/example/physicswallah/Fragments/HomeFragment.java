package com.example.physicswallah.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.physicswallah.Adapters.HomeRecyclerViewAdapter;
import com.example.physicswallah.Models.FacultyModel;
import com.example.physicswallah.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    ArrayList<FacultyModel> modelArrayList = new ArrayList<FacultyModel>();


    //Asynctask for donwloading content of api
    public class DownLoadTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String result="";
            try{
                //creating url object poining towards resource string[0] is the address
                URL url = new URL(strings[0]);
                // opens the connection to above url
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.connect();
                // end of openning connection

                //creating object of InputStream in order to read form opened connection
                InputStream inputStream = httpsURLConnection.getInputStream();
                //reading data character by character from address and saving it into result string
                int data = inputStream.read();
                while(data!=-1){
                    char ch = (char) data;
                    result = result+ch;
                    data = inputStream.read();
                }
                //end of reading
                //Log.i("result",result);
                return result;

            }catch (Exception e){
                e.printStackTrace();
                return "failed";
            }

        }

        // after exicution of doInBackground result is passed in onPostExecute
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                //creating json array
                JSONArray jsonArray = new JSONArray(s);
                //fetching data
                for(int i=0;i<jsonArray.length();i++){
                    FacultyModel facultyModel = new FacultyModel();
                    //getting json objects
                    JSONObject jobj = jsonArray.getJSONObject(i);
                    //setting json data to faculty model
                    facultyModel.setName(jobj.getString("name"));
                    Log.i("name",jobj.getString("name"));
                    facultyModel.setId(jobj.getString("id"));
                    facultyModel.setProfileurl(jobj.getString("profileImage"));

                    //inner json arrays
                    JSONArray subject = jobj.getJSONArray("subjects");
                    JSONArray qualification = jobj.getJSONArray("qualification");

                    ArrayList<String> subjectlist = new ArrayList<String>();
                    for(int sint=0;sint<subject.length();sint++){
                        Log.i("suject",subject.getString(sint));
                        subjectlist.add(subject.getString(sint));
                    }

                    facultyModel.setSubjects(subjectlist);


                    ArrayList<String> qualificationlist = new ArrayList<String>();
                    for(int qint =0;qint<qualification.length();qint++){
                        Log.i("quali",qualification.getString(qint));
                        qualificationlist.add(qualification.getString(qint));
                    }

                    facultyModel.setQualification(qualificationlist);
                    //adding facultyModel to modelArraylist containing all the data from api
                    modelArrayList.add(facultyModel);
                }


                homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(getContext(),modelArrayList);

                recyclerView.setAdapter(homeRecyclerViewAdapter);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerviewid);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        DownLoadTask downLoadTask = new DownLoadTask();
        downLoadTask.execute("https://my-json-server.typicode.com/easygautam/data/users");


        return view;
    }
}