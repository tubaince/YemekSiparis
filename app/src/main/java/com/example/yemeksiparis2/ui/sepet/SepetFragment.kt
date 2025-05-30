package com.example.yemeksiparis2.ui.sepet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemeksiparis2.databinding.FragmentSepetBinding
import com.example.yemeksiparis2.ui.adapter.SepetAdapter
import com.example.yemeksiparis2.viewmodel.SepetViewModel

class SepetFragment : Fragment() {

    private var _binding: FragmentSepetBinding? = null
    private val binding get() = _binding!!

    private val sepetViewModel: SepetViewModel by viewModels()
    private lateinit var sepetAdapter: SepetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSepetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("DetayFragment", "onViewCreated tetiklendi")

        sepetAdapter = SepetAdapter(emptyList())
        binding.sepetRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.sepetRecyclerView.adapter = sepetAdapter

        sepetViewModel.sepetYemekleri.observe(viewLifecycleOwner) { sepetUrunler ->
            sepetAdapter.updateSepetList(sepetUrunler)
            val toplamFiyat = sepetUrunler.sumOf {
                it.yemek_fiyat.toInt() * it.yemek_siparis_adet
            }
            binding.toplamFiyatTextView.text = "Toplam: $toplamFiyat ₺"
        }

        // Kullanıcı adı parametresi kaldırıldı
        sepetViewModel.getSepetYemekleri()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
