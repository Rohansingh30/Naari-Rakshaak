package com.blogspot.softwareengineerrohan.naarirakshak.HeaderFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.softwareengineerrohan.naarirakshak.HeaderAdapter.InviteAdapter
import com.blogspot.softwareengineerrohan.naarirakshak.databinding.FragmentInviteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class InviteFragment : Fragment() , InviteAdapter.OnActionClickListener {

    private lateinit var binding: FragmentInviteBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    //private lateinit var mContext: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentInviteBinding.inflate(inflater, container, false)

        return binding.root

    }

    //
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//    mContext = context
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()

        binding.inviteBtn.setOnClickListener {

            val inviteEmail = binding.etInvite.text.toString()

            val hashMap = hashMapOf("invite_status" to 0L)

            db.collection("users")
                .document(mAuth.currentUser?.email.toString())
                .collection("Invite")
                .document(inviteEmail)
                .set(hashMap)
                .addOnCompleteListener {
                    Toast.makeText(this.context, "Invite Sent", Toast.LENGTH_SHORT).show()

                }


        }

        db.collection("users")
            .document(mAuth.currentUser?.email.toString())
            .collection("Invite")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Toast.makeText(this.context, error.message, Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }
                val inviteList = ArrayList<String>()
                for (document in value!!) {
                    inviteList.add(document.id)
                }
                binding.rcvInvite.layoutManager = LinearLayoutManager(this.context)
                binding.rcvInvite.adapter = InviteAdapter(requireContext(), inviteList, this)



            }


}

    override fun onAcceptClick(inviteEmail: String) {


        val hashMap = hashMapOf("invite_status" to 1L)

        db.collection("users")
            .document(mAuth.currentUser?.email.toString())
            .collection("Invite")
            .document(inviteEmail)
            .set(hashMap)
            .addOnCompleteListener {
                Toast.makeText(this.context, "Accept $inviteEmail", Toast.LENGTH_SHORT).show()



            }

    }

    override fun onDenyClick(inviteEmail: String) {



        val hashMap = hashMapOf("invite_status" to -1L)

        db.collection("users")
            .document(mAuth.currentUser?.email.toString())
            .collection("Invite")
            .document(inviteEmail)
            .set(hashMap)
            .addOnCompleteListener {
                Toast.makeText(this.context, " Deny $inviteEmail", Toast.LENGTH_SHORT).show()



            }

    }
}
