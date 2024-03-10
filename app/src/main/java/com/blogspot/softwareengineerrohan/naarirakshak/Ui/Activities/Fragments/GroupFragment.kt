package com.blogspot.softwareengineerrohan.naarirakshak.Ui.Activities.Fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.blogspot.softwareengineerrohan.naarirakshak.R
import com.blogspot.softwareengineerrohan.naarirakshak.databinding.FragmentGroupBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 * Use the [GroupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GroupFragment : Fragment() {
    val binding by lazy {
        FragmentGroupBinding.inflate(layoutInflater)
    }

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return binding.root

        // Inflate the layout for this fragment
    }

    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()



        db.collection("users")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful && it.result != null) {

                    val user = mAuth.currentUser
                    val name = user?.displayName
                    val email = user?.email
                    val photo = user?.photoUrl
                    val number = user?.phoneNumber
                    val id = user?.uid

                    binding.apply {
                        nameSetting.text = name
                        emailSetting.text = email
                        Picasso.get().load(photo).into(profileSetting)


                    }


                }


            }

//        open bottomsheet for see profile Login In
        binding.profileSettingBtn.setOnClickListener {

            val dialogSheet = BottomSheetDialog(this.requireContext())

            val view = layoutInflater.inflate(R.layout.profile_menu_bottom_sheet, null)

            val profileLoginImgage =
                view.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.profile_login_img)
            val profileLoginName = view.findViewById<TextView>(R.id.profile_name_login)
            val profileLoginEmail = view.findViewById<TextView>(R.id.profile_email_login)




            db.collection("users")
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful && it.result != null) {

                        val user = mAuth.currentUser
                        val name = user?.displayName
                        val email = user?.email
                        val photo = user?.photoUrl
                        val number = user?.phoneNumber
                        val id = user?.uid

                        Picasso.get().load(photo).into(profileLoginImgage)
                        profileLoginName.text = name
                        profileLoginEmail.text = email


                    }

                    dialogSheet.setCancelable(true)
                    dialogSheet.setCanceledOnTouchOutside(true)

                    dialogSheet.setContentView(view)

                    dialogSheet.show()


                }


        }


    }
}