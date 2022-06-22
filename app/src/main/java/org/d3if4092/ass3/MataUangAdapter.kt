package org.d3if4092.ass3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import org.d3if4092.ass3.R
import org.d3if4092.ass3.model.Convert

class MataUangAdapter(
    val mataUangCtx : Context,
    val layoutResId : Int,
    val mataUangList: MutableList<Convert>
) : ArrayAdapter<Convert>(mataUangCtx, layoutResId, mataUangList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mataUangCtx)
        val view : View = layoutInflater.inflate(layoutResId, null)

        val rupiahInput : TextView = view.findViewById(R.id.idrDisplay)
        val jpyConvert : TextView = view.findViewById(R.id.jpyDisplay)

        val convert = mataUangList[position]

        rupiahInput.text = convert.rupiah
        jpyConvert.text = convert.hasilJepang

        return view
    }
}