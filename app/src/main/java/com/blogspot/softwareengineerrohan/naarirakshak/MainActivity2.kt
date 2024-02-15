package com.blogspot.softwareengineerrohan.naarirakshak

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.blogspot.softwareengineerrohan.naarirakshak.Fragments.GroupFragment
import com.blogspot.softwareengineerrohan.naarirakshak.Fragments.HomeFragment
import com.blogspot.softwareengineerrohan.naarirakshak.Fragments.MapsFragment
import com.blogspot.softwareengineerrohan.naarirakshak.Fragments.ContactFragment
import com.blogspot.softwareengineerrohan.naarirakshak.Fragments.SosFragment
import com.blogspot.softwareengineerrohan.naarirakshak.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {


    private lateinit var binding: ActivityMain2Binding
private lateinit var toggled : ActionBarDrawerToggle

// permissions
val permissions = arrayOf(
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.CAMERA,
    Manifest.permission.READ_CONTACTS
)
    val permissionCode = 911





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.toolbar.setTitle("Naari Rakshak")
        binding.toolbar.setSubtitle("we admire you are strong")
        binding.toolbar.setTitleTextColor(getColor(R.color.white))
        binding.toolbar.setSubtitleTextColor(getColor(R.color.white))

        // permissions function
        askForPermissions()














































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
    when(it.itemId){
        R.id.home->{
            replaceFragment(HomeFragment())
        }
        R.id.location->{
            replaceFragment(MapsFragment())

        }
        R.id.sos->{
        replaceFragment(GroupFragment())
        }
        R.id.contact->{
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
            when(it.itemId){
                R.id.headerSignOut->{
                    Toast.makeText(applicationContext, "SignOut Clicked", Toast.LENGTH_SHORT).show()

                }
                R.id.header_announcements->{
                    Toast.makeText(applicationContext, "Announcements Clicked", Toast.LENGTH_SHORT).show()

                }
                R.id.header_home->{
                    Toast.makeText(applicationContext, "Home Clicked", Toast.LENGTH_SHORT).show()

                }
                R.id.headerProfile->{
                    Toast.makeText(applicationContext, "Profile Clicked", Toast.LENGTH_SHORT).show()

                }

            }
            true
        }

    }

    private fun askForPermissions() {
        ActivityCompat.requestPermissions(this,permissions,permissionCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode== permissionCode){
            if(allPermissionsGranted()){
                Toast.makeText(this, "All Permission Granted", Toast.LENGTH_SHORT).show()
               // **when permision is granted to record video then frequently open the camera
                //openCameras()

            }
            else {
                Toast.makeText(this, "Some Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this, "All Permission Denied { PermissionCode not found }", Toast.LENGTH_SHORT).show()
        }



    }

    private fun openCameras(){
        //intent for casmera opening
        val intentCameras = Intent("android.media.action.IMAGE_CAPTURE")
        startActivity(intentCameras)
    }
    private fun allPermissionsGranted() : Boolean{
        for(permission in permissions){
            if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
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

        if (toggled.onOptionsItemSelected(item)){
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









