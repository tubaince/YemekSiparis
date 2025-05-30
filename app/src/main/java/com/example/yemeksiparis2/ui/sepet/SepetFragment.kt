package com.example.yemeksiparis2.ui.sepet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemeksiparis2.databinding.FragmentSepetBinding
import com.example.yemeksiparis2.model.SepetYemek
import com.example.yemeksiparis2.ui.adapter.SepetAdapter
import com.example.yemeksiparis2.viewmodel.SepetViewModel

class SepetFragment : Fragment() {

    private var _binding: FragmentSepetBinding? = null
    private val binding get() = _binding!!

    private val sepetViewModel: SepetViewModel by viewModels()
    private lateinit var sepetAdapter: SepetAdapter
    private var sepetYemekListesi = listOf<SepetYemek>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSepetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        // İlk olarak boş liste ile adapter initialize ediliyor
        sepetAdapter = SepetAdapter(sepetYemekListesi) { silinecekYemek ->
            sepetViewModel.yemekSil(silinecekYemek.sepet_yemek_id)
            Toast.makeText(requireContext(), "Yemek silindi", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerView.adapter = sepetAdapter

        // Veriler değiştiğinde adapter güncelleniyor
        sepetViewModel.sepetYemekleri.observe(viewLifecycleOwner) { liste ->
            sepetYemekListesi = liste
            sepetAdapter.updateList(liste)  // Adapter içindeki listeyi güncellemek için method eklemelisin
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
