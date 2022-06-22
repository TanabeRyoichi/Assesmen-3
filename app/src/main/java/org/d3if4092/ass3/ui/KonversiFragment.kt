package org.d3if4092.ass3.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.d3if4092.ass3.R
import org.d3if4092.ass3.databinding.FragmentKonversiBinding
import org.d3if4092.ass3.model.Convert

class KonversiFragment : Fragment() {

    private lateinit var binding: FragmentKonversiBinding
    private lateinit var db: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKonversiBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        db = FirebaseDatabase.getInstance().getReference("Convert")
        binding.konversiButton.setOnClickListener {
            konversi()
        }
        binding.button3.setOnClickListener { reset() }
    }

    @SuppressLint("StringFormatMatches")
    private fun konversi() {


        val jumlah = binding.inputRp.text.toString()
        if (TextUtils.isEmpty(jumlah)) {
            Toast.makeText(context, R.string.jumlah_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val hasil = jumlah.toFloat() / 132

        binding.jumlahTextView.text = getString(R.string.jumlah_x, hasil)

        val inputRupiah: String = binding.inputRp.text.toString()
        val konversiJepang : String = hasil.toString()

        val conId: String? = db.push().key

        val con = conId?.let { Convert(it, inputRupiah, konversiJepang) }

        if (conId != null) {
            db.child(conId).setValue(con).addOnCompleteListener {
                Toast.makeText(
                    (activity as AppCompatActivity).applicationContext,
                    "Data berhasil disimpan",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun reset() {
        binding.inputRp.setText("")
        binding.jumlahTextView.text = ""

    }

}













