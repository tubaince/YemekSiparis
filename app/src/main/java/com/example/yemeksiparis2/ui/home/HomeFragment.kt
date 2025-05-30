package com.example.yemeksiparis2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeksiparis2.databinding.FragmentHomeBinding
import com.example.yemeksiparis2.model.Yemek
import com.example.yemeksiparis2.ui.adapter.YemekAdapter
import com.example.yemeksiparis2.viewmodel.YemekViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val yemekViewModel: YemekViewModel by viewModels()
    private lateinit var yemekAdapter: YemekAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        yemekViewModel.yemekListesi.observe(viewLifecycleOwner) { yemekler ->

            // Adapter içindeki verileri güncelle
            yemekAdapter.updateYemekList(yemekler)
        }
    }

    private fun setupRecyclerView() {
        yemekAdapter = YemekAdapter(emptyList()) { secilenYemek ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetayFragment(secilenYemek)
            findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = yemekAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
