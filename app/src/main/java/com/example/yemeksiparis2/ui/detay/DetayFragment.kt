package com.example.yemeksiparis2.ui.detay

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.yemeksiparis2.databinding.FragmentDetayBinding
import com.example.yemeksiparis2.model.Yemek
import com.example.yemeksiparis2.viewmodel.SepetViewModel

class DetayFragment : Fragment() {

    private var _binding: FragmentDetayBinding? = null
    private val binding get() = _binding!!

    private val sepetViewModel: SepetViewModel by viewModels()

    private lateinit var secilenYemek: Yemek

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            secilenYemek = DetayFragmentArgs.fromBundle(it).secilenYemek
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.yemekAdiText.text = secilenYemek.yemek_adi
        binding.yemekFiyatText.text = "${secilenYemek.yemek_fiyat} ₺"

        val resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${secilenYemek.yemek_resim_adi}"
        com.bumptech.glide.Glide.with(binding.root.context)
            .load(resimUrl)
            .into(binding.yemekResimImage)

        binding.adetSayisi.text = "1"  // Default adet

        binding.artirButton.setOnClickListener {
            val adet = binding.adetSayisi.text.toString().toInt()
            binding.adetSayisi.text = (adet + 1).toString()
        }

        binding.azaltButton.setOnClickListener {
            val adet = binding.adetSayisi.text.toString().toInt()
            if (adet > 1) {
                binding.adetSayisi.text = (adet - 1).toString()
            }
        }

        binding.sepeteEkleButton.setOnClickListener {
            val adet = binding.adetSayisi.text.toString().toInt()
            Log.d("DetayFragment", "Sepete eklenecek: ${secilenYemek.yemek_adi}, adet: $adet")
            sepetViewModel.sepeteYemekEkle(
                secilenYemek.yemek_adi,
                secilenYemek.yemek_resim_adi,
                secilenYemek.yemek_fiyat.toInt(),
                adet,
                kullaniciAdi = "tuğba123"
            )
            Toast.makeText(requireContext(), "Sepete eklendi", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
