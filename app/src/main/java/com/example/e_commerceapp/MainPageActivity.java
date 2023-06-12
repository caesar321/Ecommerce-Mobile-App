package com.example.e_commerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MainPageActivity extends AppCompatActivity {
private BottomNavigationView bottomNavigationView;
private TextView txtGmail,UserPerson;
private   RecyclerView recyclerView1,recyclerView2;
private ArrayList<myCategory> myCategories;
private ArrayList<productModal> myProduct;
private productAdapter productAdapter;
private ImageView imgCart;
private Button btnAll,btnElectronics,btnJewelrey,btnLadiesWear,btnMenWear;
//private  CategoryAdapter categoryAdapter;
    private RequestQueue requestQueue;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        bottomNavigationView = findViewById(R.id.navigation);
        txtGmail= findViewById(R.id.txtprofile);
        UserPerson = findViewById(R.id.txtprofile);
        recyclerView2 = findViewById(R.id.recycler_view2);
        recyclerView2.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        auth= FirebaseAuth.getInstance();
        imgCart = findViewById(R.id.imgCart);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        recyclerView1 = findViewById(R.id.recycler_view);
        btnAll = findViewById(R.id.btnAll);
        btnElectronics = findViewById(R.id.btnElect);
        btnJewelrey = findViewById(R.id.btnjewelrey);
        btnLadiesWear= findViewById(R.id.btnLW);
        btnMenWear= findViewById(R.id.btnMW);
        myCategories = new ArrayList<>();
        myProduct = new ArrayList<>();
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPageActivity.this,MainPageActivity.class);
                startActivity(intent);
            }
        });
        btnElectronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPageActivity.this,ElectronicsActivity.class);
                startActivity(intent);
            }
        });
        btnMenWear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPageActivity.this,MenWearActivity.class);
                startActivity(intent);
            }
        });
        btnLadiesWear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainPageActivity.this,LadiesWearActivity.class);
                startActivity(intent);
            }
        });
        btnJewelrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainPageActivity.this,JewelreyActivity.class);
                startActivity(intent);
            }
        });
       LinearLayoutManager linearLayoutManager;
       linearLayoutManager= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
       recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setHasFixedSize(true);
        databaseReference = FirebaseDatabase.getInstance().getReference("UserData");
        DatabaseReference userRef= databaseReference.child(Objects.requireNonNull(auth.getUid()));
        DatabaseReference userName= userRef.child("user");
        userName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.getValue(String.class);
                UserPerson.setText( username);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        fetchDataFromAPI();
        displayCategory();
        productData();
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPageActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.cart:
                        Intent intent= new Intent(MainPageActivity.this,DetailsActivity.class);
                        startActivity(intent);

                      Intent intent1 = new Intent(MainPageActivity.this,CartActivity.class);
                      startActivity(intent1);

                        return true;

                    case R.id.explore:
                        Intent intent3 = new Intent(MainPageActivity.this,ExploreActivity.class);
                        startActivity(intent3);

                        return true;
                    case R.id.person:
                       Intent intent2= new Intent(MainPageActivity.this,AccountActivity.class);
                       startActivity(intent2);


                        return true;
                }

                return false;
            }
        });
    }

    private void displayCategory() {
        myCategories.add(new myCategory("Electronics"));
        myCategories.add(new myCategory("Jewellrey"));
        myCategories.add(new myCategory("Mobile phones"));
        myCategories.add(new myCategory("Men wear"));
        myCategories.add(new myCategory("Glasses"));
        myCategories.add(new myCategory("Health and Beauty"));
        myCategories.add(new myCategory("Vehicles"));
        //categoryAdapter = new CategoryAdapter(getApplicationContext(),myCategories);
        //recyclerView1.setAdapter(categoryAdapter);
       /* String url = "https://fakestoreapi.com/products/categories";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                String categoryNames = response.getString(i);
                                    myCategories.add(new myCategory(categoryNames));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });requestQueue.add(request);*/
       /* myCategories.add(new myCategory("Italian Wears",String.valueOf(R.drawable.cartimage),"This is an Italian wear","#FFFFFF",1));
        myCategories.add(new myCategory("Indian Wears",String.valueOf(R.drawable.cartimage),"This is an Italian wear","#FFFFFF",1));
        myCategories.add(new myCategory("Italian Wears",String.valueOf(R.drawable.cartimage),"This is an Italian wear","#FFFFFF",1));
        myCategories.add(new myCategory("Indian Wears",String.valueOf(R.drawable.cartimage),"This is an Italian wear","#FFFFFF",1));
       ;*/
        //
       /* JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray= response.getJSONArray("categories");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject myObject= jsonArray.getJSONObject(i);
                        String name= myObject.getString("name");
                        String icon =myObject.getString("icon");
                        String color= myObject.getString("color");
                        String brief = myObject.getString("brief");
                        int id = myObject.getInt("id");
                        myCategories.add(new myCategory(name,icon,brief,color,id));

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });requestQueue.add(jsonObjectRequest);*/
    }

    private void fetchDataFromAPI() {
        String API_URL= "https://fakestoreapi.com/products";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject productJson = response.getJSONObject(i);

                                int id = productJson.getInt("id");
                                String title = productJson.getString("title");
                                String image = productJson.getString("image");
                                String description= productJson.getString("description");
                                double rate= productJson.getDouble("rate");
                                double price = productJson.getDouble("price");
                                myProduct.add(new productModal(id,price,rate,title,image,description,null,null,null,null,null,null));
                            }
                            productAdapter = new productAdapter(getApplicationContext(),myProduct);
                            recyclerView2.setAdapter(productAdapter);
                            productAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Toast.makeText(MainPageActivity.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        requestQueue.add(request);
    }


    private void productData() {
        myProduct.add(new productModal(1,100.0,3.6,"Mens Casual Premium Slim Fit T-Shirts","https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg","Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.","Jewellrey","Gucci","Cotton","New","100","2.8"));
       // myProduct.add(new productModal(1, 100.00, "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg","Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",3.9,"Men Wear","palm Angelas","cotton","New","100","200"));
      // myProduct.add(new productModal(2, 200.90, 4.3,"Mens Casual Premium Slim Fit T-Shirts", "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg","Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.","Jewelrey","HenryBrand","Cotton","New","75","3.5"));
        myProduct.add(new productModal(2, 230.97, 3, "Mens Cotton Jacket","https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg","great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.","Men wear","Cotton","Leather","New","100","4.9"));
        myProduct.add(new productModal(3, 695 ,4.3,"John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet", "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg","From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean's pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection","Clothes","Addidas","Leather","New","70","2.4"));
        myProduct.add(new productModal(4, 40.55,4.4, "Mens Casual Slim Fit", "https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg","The color could be slightly different between on the screen and in practice. / Please note that body builds vary by person, therefore, detailed size information should be reviewed below on the product description.","Men Collection","Gucci","Leather","New","100","2.7"));
      // myProduct.add(new productModal(5,340,3.6,"Random Image","https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg","ok","Testing","NA","fufu","New","40","4.0"));
        myProduct.add(new productModal(6,168,3.9,"Solid Gold Petite Micropave ","https://fakestoreapi.com/img/61sbMiUnoGL._AC_UL640_QL65_ML3_.jpg","Satisfaction Guaranteed. Return or exchange any order within 30 days.Designed and sold by Hafeez Center in the United States. Satisfaction Guaranteed. Return or exchange any order within 30 days","Testing","NA","N/A","New","40","4.0"));
        myProduct.add(new productModal(7,9.99,3.0,"White Gold Plated Princess","https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_.jpg","Classic Created Wedding Engagement Solitaire Diamond Promise Ring for Her. Gifts to spoil your love more for Engagement, Wedding, Anniversary, Valentine's Day..","jewelery","NA","N/A","New","40","4.0"));
        myProduct.add(new productModal(8,10.99,1.9,"Pierced Owl Rose Gold Plated Stainless Steel Double","https://fakestoreapi.com/img/51UDEzMJVpL._AC_UL640_QL65_ML3_.jpg","Rose Gold Plated Double Flared Tunnel Plug Earrings. Made of 316L Stainless Steel","jewelery","NA","fufu","New","40","4.0"));
        myProduct.add(new productModal(9,64,3.3,"WD 2TB Elements Portable External Hard Drive - USB 3.0","https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg","USB 3.0 and USB 2.0 Compatibility Fast data transfers Improve PC Performance High Capacity; Compatibility Formatted NTFS for Windows 10, Windows 8.1, Windows 7; Reformatting may be required for other operating systems; Compatibility may vary depending on user’s hardware configuration and operating system","electronics","NA","fufu","New","40","4.0"));
        myProduct.add(new productModal(10,129,2.6,"SanDisk SSD PLUS 1TB Internal SSD - SATA III ","https://fakestoreapi.com/img/61U7T1koQqL._AC_SX679_.jpg","Easy upgrade for faster boot up, shutdown, application load and response (As compared to 5400 RPM SATA 2.5” hard drive; Based on published specifications and internal benchmarking tests using PCMark vantage scores) Boosts burst write performance, making it ideal for typical PC workloads The perfect balance of performance and reliability Read/write speeds of up to 535MB/s/450MB/s (Based on internal testing; Performance may vary depending upon drive capacity, host device, OS and application.","electronics","NA","N/A","New","40","4.0"));
        myProduct.add(new productModal(11,109,4.8,"Silicon Power 256GB SSD 3D NAND A55 SLC Cache Performance Boost SATA III","https://fakestoreapi.com/img/71kWymZ+c+L._AC_SX679_.jpg","3D NAND flash are applied to deliver high transfer speeds Remarkable transfer speeds that enable faster bootup and improved overall system performance. The advanced SLC Cache Technology allows performance boost and longer lifespan 7mm slim design suitable for Ultrabooks and Ultra-slim notebooks. Supports TRIM command, Garbage Collection technology, RAID, and ECC (Error Checking & Correction) to provide the optimized performance and enhanced reliability.","electronics","NA","N/A","New","40","4.0"));
        myProduct.add(new productModal(12,114,4.8,"WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive","https://fakestoreapi.com/img/61mtL65D4cL._AC_SX679_.jpg","Expand your PS4 gaming experience, Play anywhere Fast and easy, setup Sleek design with high capacity, 3-year manufacturer's limited warranty.","Electronic","NA","N/A","New","40","4.0"));
        myProduct.add(new productModal(13,599,2.9,"Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin","https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_.jpg","21. 5 inches Full HD (1920 x 1080) widescreen IPS display And Radeon free Sync technology. No compatibility for VESA Mount Refresh Rate: 75Hz - Using HDMI port Zero-frame design | ultra-thin | 4ms response time | IPS panel Aspect ratio - 16: 9. Color Supported - 16. 7 million colors. Brightness - 250 nit Tilt angle -5 degree to 15 degree. Horizontal viewing angle-178 degree. Vertical viewing angle-178 degree 75 hertz.","electronics","NA","fufu","New","40","4.0"));
        myProduct.add(new productModal(14,56.99,2.6,"BIYLACLESEN Women's 3-in-1 Snowboard Jacket Winter Coats","https://fakestoreapi.com/img/51Y5NI-I5jL._AC_UX679_.jpg","\"Note:The Jackets is US standard size, Please choose size as your usual wear Material: 100% Polyester; Detachable Liner Fabric: Warm Fleece. Detachable Functional Liner: Skin Friendly, Lightweigt and Warm.Stand Collar Liner jacket, keep you warm in cold weather. Zippered Pockets: 2 Zippered Hand Pockets, 2 Zippered Pockets on Chest (enough to keep cards or keys)and 1 Hidden Pocket Inside.Zippered Hand Pockets and Hidden Pocket keep your things secure. Humanized Design: Adjustable and Detachable Hood and Adjustable cuff to prevent the wind and water,for a comfortable fit. 3 in 1 Detachable Design provide more convenience, you can separate the coat and inner as needed, or wear it together. It is suitable for different season and help you adapt to different climates","women's clothing","NA","N/A","New","40","4.0"));
        productAdapter = new productAdapter(getApplicationContext(), myProduct);
        recyclerView2.setAdapter(productAdapter);
    }


}