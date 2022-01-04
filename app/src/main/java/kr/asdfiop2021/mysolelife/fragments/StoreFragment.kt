package kr.asdfiop2021.mysolelife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kr.asdfiop2021.mysolelife.R
import kr.asdfiop2021.mysolelife.databinding.FragmentStoreBinding
import kr.asdfiop2021.mysolelife.databinding.FragmentTalkBinding


/**
 * A simple [Fragment] subclass.
 * Use the [StoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StoreFragment : Fragment() {

    private lateinit var binding : FragmentStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store, container, false)

        binding.tabStore.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_homeFragment)
        }

        binding.tabTip.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_tipFragment)
        }

        binding.tabTalk.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_talkFragment)
        }

        binding.tabBookmark.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_bookmarkFragment)
        }


        val view = inflater.inflate(R.layout.fragment_store, container, false)

        val webView : WebView = view.findViewById(R.id.storeWebView)
        webView.loadUrl("https://www.inflearn.com/")

        return view
    }
}