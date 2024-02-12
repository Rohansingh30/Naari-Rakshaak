package com.blogspot.softwareengineerrohan.naarirakshak.Fragments

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blogspot.softwareengineerrohan.naarirakshak.R
import com.blogspot.softwareengineerrohan.naarirakshak.databinding.FragmentSosBinding
import com.google.android.gms.location.FusedLocationProviderClient

@Suppress("DEPRECATION")
class SosFragment : Fragment() {
    val binding by lazy {
        FragmentSosBinding.inflate(layoutInflater)
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var mediaPlayer: MediaPlayer


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val binding = FragmentSosBinding.inflate(inflater, container, false)

        // sos sound work
        mediaPlayer = MediaPlayer.create(context, R.raw.sirensound)
        mediaPlayer.setVolume(1f, 1f)
        mediaPlayer.isLooping = true

        //  totalTime = mediaPlayer.duration

        binding.tweetStart.setOnClickListener {
            mediaPlayer.start()


        }

        binding.tweetStop.setOnClickListener {
            mediaPlayer.pause()
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)








    }
}