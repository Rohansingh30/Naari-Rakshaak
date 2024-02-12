package com.blogspot.softwareengineerrohan.naarirakshak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.blogspot.softwareengineerrohan.naarirakshak.Fragments.GroupFragment
import com.blogspot.softwareengineerrohan.naarirakshak.Fragments.HomeFragment
import com.blogspot.softwareengineerrohan.naarirakshak.Fragments.LocationFragment
import com.blogspot.softwareengineerrohan.naarirakshak.Fragments.SosFragment
import com.blogspot.softwareengineerrohan.naarirakshak.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {


    private lateinit var binding: ActivityMain2Binding
private lateinit var toggled : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, HomeFragment())
        fragmentTransaction.commit()


        binding.bottomBar.setOnItemSelectedListener {
    when(it.itemId){
        R.id.home->{
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame, HomeFragment())
            fragmentTransaction.commit()
            true
        }
        R.id.location->{
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame, LocationFragment())
            fragmentTransaction.commit()
            true
        }
        R.id.sos->{
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame, SosFragment())
            fragmentTransaction.commit()
            true
        }
        R.id.community->{
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame, GroupFragment())
            fragmentTransaction.commit()
            true}

        else -> {
            false
        }
    }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggled.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)



    }

}









