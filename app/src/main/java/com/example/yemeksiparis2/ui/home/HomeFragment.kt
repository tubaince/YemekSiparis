package com.example.yemeksiparis2.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemeksiparis2.databinding.FragmentHomeBinding
import com.example.yemeksiparis2.model.Yemek
import com.example.yemeksiparis2.ui.adapter.YemekAdapter
import com.example.yemeksiparis2.viewmodel.SepetViewModel
import com.example.yemeksiparis2.viewmodel.YemekViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val yemekViewModel: YemekViewModel by viewModels()
    private val sepetViewModel: SepetViewModel by viewModels()
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
            Log.d("yemek1", "Gelen yemekler: $yemekler")
            yemekAdapter.updateYemekList(yemekler)
        }
    }

    private fun setupRecyclerView() {
        yemekAdapter = YemekAdapter(emptyList(),
            onItemClick = { secilenYemek ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetayFragment(secilenYemek)
                findNavController().navigate(action)
            },
            onSepeteEkleClick = { secilenYemek ->
                sepetViewModel.sepeteYemekEkle(
                    yemekAdi = secilenYemek.yemek_adi,
                    yemekResimAdi = secilenYemek.yemek_resim_adi,
                    yemekFiyat = secilenYemek.yemek_fiyat.toString(),
                    yemekAdet = 1,
                    kullaniciAdi = "tuba123",
                    yemekId = secilenYemek.yemek_id
                )
                Toast.makeText(requireContext(), "${secilenYemek.yemek_adi} sepete eklendi", Toast.LENGTH_SHORT).show()
            }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = yemekAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
