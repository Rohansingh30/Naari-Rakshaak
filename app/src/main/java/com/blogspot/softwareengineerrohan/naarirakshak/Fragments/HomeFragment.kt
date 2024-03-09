package com.blogspot.softwareengineerrohan.naarirakshak.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogspot.softwareengineerrohan.naarirakshak.FragmentsAdapters.ExploreAdapter
import com.blogspot.softwareengineerrohan.naarirakshak.FragmentsAdapters.RavEmergencyAdapter
import com.blogspot.softwareengineerrohan.naarirakshak.FragmentsAdapters.ShareLocationAdapter
import com.blogspot.softwareengineerrohan.naarirakshak.FragmentsModels.ExploreModel
import com.blogspot.softwareengineerrohan.naarirakshak.FragmentsModels.RavEmergencyItem
import com.blogspot.softwareengineerrohan.naarirakshak.FragmentsModels.RavShareLocationModel
import com.blogspot.softwareengineerrohan.naarirakshak.R
import com.blogspot.softwareengineerrohan.naarirakshak.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val dataList = ArrayList<RavEmergencyItem>()
        dataList.add(RavEmergencyItem(R.drawable.siren, "Police", "Call 122 for emergencies", "122"))
        dataList.add(RavEmergencyItem(R.drawable.ambulance, "Ambulance", "Call 102 for ambulance", "108"))
        dataList.add(RavEmergencyItem(R.drawable.fire, "Fire Brigade", "Call 101 for fire brigade ", "101"))

        binding.ravEmergency.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.ravEmergency.adapter = RavEmergencyAdapter(requireContext(), dataList)

        val exploreList = ArrayList<ExploreModel>()
        exploreList.add(ExploreModel(R.drawable.polices, "Police"))
        exploreList.add(ExploreModel(R.drawable.hospital, "Hospital"))
        exploreList.add(ExploreModel(R.drawable.pharma, "Pharmacies"))

        binding.ravExplore.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.ravExplore.adapter = ExploreAdapter(requireContext(), exploreList)

        val shareLocation = ArrayList<RavShareLocationModel>()
        shareLocation.add(RavShareLocationModel( " Get Home \n Safe", "Share\nLocation\nPeriodically", R.drawable.home))
        shareLocation.add(RavShareLocationModel( "Safe Tutorial", "Be\nSafe", R.drawable.home))

        binding.ravShareLocation.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.ravShareLocation.adapter = ShareLocationAdapter(requireContext(), shareLocation)











    }

}