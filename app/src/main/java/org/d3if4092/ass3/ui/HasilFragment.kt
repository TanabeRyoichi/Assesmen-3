package org.d3if4092.ass3.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import org.d3if4092.ass3.MataUangAdapter
import org.d3if4092.ass3.R
import org.d3if4092.ass3.databinding.FragmentHasilBinding
import org.d3if4092.ass3.model.Convert

class HasilFragment : Fragment() {

    private lateinit var binding: FragmentHasilBinding
    private lateinit var database : DatabaseReference
    private lateinit var listMataUang : ListView
    private lateinit var mataUangList : MutableList<Convert>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHasilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = FirebaseDatabase.getInstance().getReference("Convert")

        listMataUang = binding.listMataUang
        mataUangList = mutableListOf()

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                if (data.exists()) {
                    for (h in data.children) {
                        val convertMataAUang = h.getValue(Convert::class.java)
                        if (convertMataAUang != null) {
                            mataUangList.add(convertMataAUang)
                        }
                    }

                    val adapter = activity?.let { MataUangAdapter(it.applicationContext, R.layout.item_matauang, mataUangList) }
                    listMataUang.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}