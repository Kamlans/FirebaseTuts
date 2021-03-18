package com.example.firebasetuts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.firebasetuts.fragments.Model;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tag" ;
    private RecyclerView recyclerView;
    private Query query;
    public TextView first , last;
    private ImageView img;
    FirestoreRecyclerAdapter adapter;
    ArrayList<Model> arrayList = new ArrayList<Model>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recView);

        query = FirebaseFirestore.getInstance().collection("users");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("dp").child("hospital.png");





        FirestoreRecyclerOptions<Model> options  = new FirestoreRecyclerOptions.Builder<Model>().setQuery(query , Model.class).build();



        adapter = new FirestoreRecyclerAdapter<Model , ModelHolder>(options)
        {
           @NonNull
           @Override
           public ModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

               View view = LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.single_row, parent, false);

               return new ModelHolder(view);
           }

           @Override
           protected void onBindViewHolder(@NonNull ModelHolder holder, int position, @NonNull Model model) {

               first.setText(model.getFirst());
               last.setText(model.getLast());
               Glide.with(getApplicationContext())
                       .load(model.getImg())
                       .into(img);

               Log.d(TAG, "onBindViewHolder: "+model.getFirst());
               Log.d(TAG, "onBindViewHolder: "+model.getLast());
           }

           @Override
           public void onError(FirebaseFirestoreException e) {
               Log.e("error", e.getMessage());
           }
       };

       adapter.notifyDataSetChanged();
       recyclerView.setAdapter(adapter);
       recyclerView.setLayoutManager( new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onOptionsItemSelected: Logout ");
            AuthUI.getInstance().signOut(this);
            startActivity( new Intent( getApplicationContext() , LoginRegisterActivity.class));
            return true;
        }
        else if ( id == R.id.action_profile) {
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onOptionsItemSelected: profile");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private class ModelHolder extends RecyclerView.ViewHolder {


        public ModelHolder(@NonNull View itemView) {
            super(itemView);

            first = itemView.findViewById(R.id.textView);
            last = itemView.findViewById(R.id.textView2);
            img = itemView.findViewById(R.id.imageView);
        }
    }
}

