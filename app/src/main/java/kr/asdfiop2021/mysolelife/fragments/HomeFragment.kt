package kr.asdfiop2021.mysolelife.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kr.asdfiop2021.mysolelife.R
import kr.asdfiop2021.mysolelife.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("HomeFragment", "onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.tabTip.setOnClickListener{
            Log.d("HomeFragment", "tabTip")
            it.findNavController().navigate(R.id.action_homeFragment_to_tipFragment)
        }

        binding.tabTalk.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_talkFragment)
        }

        binding.tabBookmark.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_bookmarkFragment)
        }

        binding.tabStore.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_storeFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}