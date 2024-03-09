package com.blogspot.softwareengineerrohan.naarirakshak.MainActivities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.blogspot.softwareengineerrohan.naarirakshak.Ui.Activities.activity.LoginActivity
import com.blogspot.softwareengineerrohan.naarirakshak.Ui.Activities.Fragments.GroupFragment
import com.blogspot.softwareengineerrohan.naarirakshak.Ui.Activities.Fragments.HomeFragment
import com.blogspot.softwareengineerrohan.naarirakshak.Ui.Activities.Fragments.MapsFragment
import com.blogspot.softwareengineerrohan.naarirakshak.Ui.Activities.Fragments.SosFragment
import com.blogspot.softwareengineerrohan.naarirakshak.databinding.ActivityMainBinding
import com.blogspot.softwareengineerrohan.naarirakshak.Ui.Activities.Fragments.ContactFragment
import com.blogspot.softwareengineerrohan.naarirakshak.Ui.Activities.HeaderFragments.InviteFragment
import com.blogspot.softwareengineerrohan.naarirakshak.R
import com.blogspot.softwareengineerrohan.naarirakshak.SharedPreferernences.PrefConstants
import com.blogspot.softwareengineerrohan.naarirakshak.SharedPreferernences.SharedPref
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggled: ActionBarDrawerToggle

    // permissions
    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.CAMERA,
        Manifest.permission.READ_CONTACTS
    )
    val permissionCode = 911



    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()
        binding.toolbar.setTitle("Naari Rakshak")
        binding.toolbar.setSubtitle("we admire you are strong")
        binding.toolbar.setTitleTextColor(getColor(R.color.white))
        binding.toolbar.setSubtitleTextColor(getColor(R.color.white))



        // permissions function


        if (isAllPeremissionsGranted()){

            if(isLocationEnabled(this)){
                setUpLocationListener()
            }else{
                showGPSNotEnabledDialog(this)
            }

        }else{
            askForPermissions()
        }










        val db = FirebaseFirestore.getInstance()

        val currentUser = FirebaseAuth.getInstance().currentUser
        val name = currentUser?.displayName.toString()
        val email = currentUser?.email.toString()
        val phoneNumber = currentUser?.phoneNumber.toString()
        val imageUrl = currentUser?.photoUrl.toString()

        val user = hashMapOf(
            "Name " to name,
            "Email" to email,
            "Phone Number" to phoneNumber,
            "Image Url" to imageUrl
        )


        db.collection("users")
            .document(email)
            .set(user)
            .addOnSuccessListener { }
            .addOnFailureListener { }
//**************************************************************************************
        replaceFragment(HomeFragment())


        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()



        binding.fabSosBtn.setOnClickListener {

            replaceFragment(SosFragment())



        }




        binding.bottomBar.background = null

        binding.bottomBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                }

                R.id.location -> {
                    replaceFragment(MapsFragment())

                }

                R.id.sos -> {
                    replaceFragment(GroupFragment())
                }

                R.id.contact -> {
                    replaceFragment(ContactFragment())
                }

                else -> {
                    false
                }
            }
            true


        }

        toggled = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggled)
        toggled.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.NavViews.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.headerSignOut -> {
                   signouts()
                }

                R.id.header_announcements -> {
                    Toast.makeText(applicationContext, "Announcements Clicked", Toast.LENGTH_SHORT).show()

                }

                R.id.header_home -> {
                    Toast.makeText(applicationContext, "Home Clicked", Toast.LENGTH_SHORT).show()

                }

                R.id.headerProfile -> {
                    Toast.makeText(applicationContext, "Profile Clicked", Toast.LENGTH_SHORT).show()

                }

                R.id.headerInvite -> {
                    replaceFragment(InviteFragment())
                    Toast.makeText(applicationContext, "Invite Clicked", Toast.LENGTH_SHORT).show()

                }

            }
            true
        }


    }

    private fun signouts() {
//        Toast.makeText(applicationContext, "User Signed Out", Toast.LENGTH_SHORT).show()
//        // dekhna remove krke kya change aya smj ni ara ok nxt time see
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val go = GoogleSignIn.getClient(this, gso)



        SharedPref.putBoolean(PrefConstants.IS_USER_LOGGED_IN, false)
        go.signOut()
//        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()

    }

    private fun setUpLocationListener() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
// for getting the current location update after every 2 seconds with high accuracy
        val locationRequest = LocationRequest().setInterval(2000).setFastestInterval(2000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    for (location in locationResult.locations) {

                        // location aagayi current location
                        Log.d("Location89", location.latitude.toString())
                        Log.d("Location89", location.longitude.toString())





                        val db = FirebaseFirestore.getInstance()

                        val currentUser = FirebaseAuth.getInstance().currentUser
                        val email = currentUser?.email.toString()

                        val locationData = mutableMapOf<String, Any>(
                            "latitude" to location.latitude,
                            "longitude" to location.longitude
                        )


                        db.collection("users")
                            .document(email)
                            .update(locationData)
                            .addOnSuccessListener {

//                                val getLocation = Location("provider")
//                                getLocation.latitude = location.latitude
//                                getLocation.longitude = location.longitude
//                               check  working in here start 03 march okk remainging work is to get location of current user


                            }
                            .addOnFailureListener {


                            }
                    }
                    // Few more things we can do here:
                    // For example: Update the location of user on server
                }
            },
            Looper.myLooper()
        )
    }

    fun isLocationEnabled(context: Context): Boolean {
        val locationManager: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    /**
     * Function to show the "enable GPS" Dialog box
     */
    fun showGPSNotEnabledDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("Enable GPS")
            .setMessage("Your GPS seems to be disabled, do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton("Enable") { _, _ ->
                context.startActivity(Intent(ACTION_LOCATION_SOURCE_SETTINGS))
            }
            .show()
    }
    fun isAllPeremissionsGranted(): Boolean {
        for(item in permissions){

            if( ContextCompat
                .checkSelfPermission(
                    this,
                    item
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }

        }
    return true
    }



    private fun askForPermissions() {
        ActivityCompat.requestPermissions(this, permissions, permissionCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == permissionCode) {
            if (allPermissionsGranted()) {
                Toast.makeText(this, "All Permission Granted", Toast.LENGTH_SHORT).show()
                // **when permision is granted to record video then frequently open the camera
                //openCameras()

                setUpLocationListener()

            } else {
                Toast.makeText(this, "Some Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(
                this,
                "All Permission Denied { PermissionCode not found }",
                Toast.LENGTH_SHORT
            ).show()
        }


    }

    private fun openCameras() {
        //intent for casmera opening
        val intentCameras = Intent("android.media.action.IMAGE_CAPTURE")
        startActivity(intentCameras)
    }

    private fun allPermissionsGranted(): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }

        }
        return true
    }


    override fun onBackPressed() {
        super.onBackPressed()

        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressedDispatcher.onBackPressed()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggled.onOptionsItemSelected(item)) {
            return true
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true


    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, fragment)
        fragmentTransaction.commit()
    }


}










