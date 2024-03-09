package com.blogspot.softwareengineerrohan.naarirakshak.Fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogspot.softwareengineerrohan.naarirakshak.MenusWork.CreateContactActivity
import com.blogspot.softwareengineerrohan.naarirakshak.R
import com.blogspot.softwareengineerrohan.naarirakshak.databinding.FragmentSosBinding
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SosFragment : Fragment() {
    val binding by lazy {
        FragmentSosBinding.inflate(layoutInflater)
    }

    private lateinit var db: FirebaseFirestore


    // creating a variable for our button


    lateinit var mediaPlayer: MediaPlayer


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): LinearLayout {
        // Inflate the layout for this fragment

        val binding = FragmentSosBinding.inflate(inflater, container, false)

        // sos sound work
        mediaPlayer = MediaPlayer.create(context, R.raw.militaryalarm)
        mediaPlayer.setVolume(1f, 1f)
        mediaPlayer.isLooping = true

        //  totalTime = mediaPlayer.duration

        binding.tweetStart.setOnClickListener {
            Toast.makeText(context, "Playing alarm!", Toast.LENGTH_SHORT).show()
            mediaPlayer.start()


        }

        binding.tweetStop.setOnClickListener {
            mediaPlayer.pause()
        }


// dialog menu
        binding.sosMenuBtn.setOnClickListener {

            val dialog = BottomSheetDialog(this.requireContext())

            val view = layoutInflater.inflate(R.layout.sos_menu_bottom_sheet, null)


            view.findViewById<Button>(R.id.id_instruction_btn).setOnClickListener {
                Toast.makeText(context, "Instructions", Toast.LENGTH_SHORT).show()
            }
            view.findViewById<Button>(R.id.id_register_new_btn).setOnClickListener {
                Toast.makeText(context, "Register New Number", Toast.LENGTH_SHORT).show()

                startActivity(Intent(context, CreateContactActivity::class.java))
                dialog.dismiss()



            }

            view.findViewById<Button>(R.id.id_clear_btn).setOnClickListener {
                dialog.dismiss()

            }





            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)

            dialog.setContentView(view)

            dialog.show()
        }


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseFirestore.getInstance()














    }

}

//
//        val dialog = Dialog(this.requireContext())
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.dialog_sos_sending)
//
//        //work remaining 3 march work not complete
//
//        dialog.setCancelable(false)
//        dialog.setCanceledOnTouchOutside(false)
//        dialog.show()
//        val sosSendBtn = dialog.findViewById<Button>(R.id.send_sos_location_btn)
//        val sosCancelBtn = dialog.findViewById<Button>(R.id.send_sos_cancel_btn)
//        sosSendBtn.setOnClickListener {
//            Toast.makeText(context, "Sending Sos Location", Toast.LENGTH_SHORT).show()
//            //setUpLocationListener()
//        }
//
//        sosCancelBtn.setOnClickListener {
//            dialog.dismiss()
//        }
//
//
//
//    }
////copy this code from main activity ok its is dublicate code using for check location send to contact okk not complete u can remove it bcoz this is copy code for testing
//    private fun setUpLocationListener() {
//        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.requireContext())
//// for getting the current location update after every 2 seconds with high accuracy
//        val locationRequest = LocationRequest().setInterval(2000).setFastestInterval(2000)
//            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//        if (ActivityCompat.checkSelfPermission(
//                this.requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this.requireContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
//        fusedLocationProviderClient.requestLocationUpdates(
//            locationRequest,
//            object : LocationCallback() {
//                override fun onLocationResult(locationResult: LocationResult) {
//                    super.onLocationResult(locationResult)
//                    for (location in locationResult.locations) {
//
//                        // location aagayi current location
//                        Log.d("Location89", location.latitude.toString())
//                        Log.d("Location89", location.longitude.toString())
//
//
//
//
//
//                        val db = FirebaseFirestore.getInstance()
//
//                        val currentUser = FirebaseAuth.getInstance().currentUser
//                        val email = currentUser?.email.toString()
//
//                        val locationData = mutableMapOf<String, Any>(
//                            "latitude" to location.latitude,
//                            "longitude" to location.longitude
//                        )
//
//
//                        db.collection("users")
//                            .document(email)
//                            .update(locationData)
//                            .addOnSuccessListener {
//
////                                val getLocation = Location("provider")
////                                getLocation.latitude = location.latitude
////                                getLocation.longitude = location.longitude
////                               check  working in here start 03 march okk remainging work is to get location of current user
//
//
//                            }
//                            .addOnFailureListener {
//
//
//                            }
//                    }
//                    // Few more things we can do here:
//                    // For example: Update the location of user on server
//                }
//            },
//            Looper.myLooper()
//        )




