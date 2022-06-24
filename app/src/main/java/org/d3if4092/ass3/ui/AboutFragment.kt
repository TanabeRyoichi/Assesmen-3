package org.d3if4092.ass3.ui


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import org.d3if4092.ass3.CourseDataModal
import org.d3if4092.ass3.R
import org.d3if4092.ass3.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AboutFragment : Fragment() {

    lateinit var courseNameTV: TextView
    lateinit var courseDescTV: TextView
    lateinit var courseReqTV: TextView
    lateinit var courseIV: ImageView
    lateinit var visitCourseBtn: Button
    lateinit var loadingPB: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseNameTV = view.findViewById(R.id.idTVCourseName)
        courseDescTV =  view.findViewById(R.id.idTVDesc)
        courseReqTV =  view.findViewById(R.id.idTVPreq)
        courseIV =  view.findViewById(R.id.idIVCourse)
        visitCourseBtn =  view.findViewById(R.id.idBtnVisitCourse)
        loadingPB =  view.findViewById(R.id.idLoadingPB)

        // on below line we are creating a method
        // to get data from api using retrofit.
        getData()

    }
    private fun getData() {
        // on below line we are creating a retrofit
        // builder and passing our base url
        // on below line we are creating a retrofit
        // builder and passing our base url
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonkeeper.com/b/")

            // on below line we are calling add Converter
            // factory as GSON converter factory.
            // at last we are building our retrofit builder.
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // below line is to create an instance for our retrofit api class.
        // below line is to create an instance for our retrofit api class.
        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)

        val call: Call<CourseDataModal?>? = retrofitAPI.getCourse()

        // on below line we are making a call.
        call!!.enqueue(object : Callback<CourseDataModal?> {
            override fun onResponse(
                call: Call<CourseDataModal?>?,
                response: Response<CourseDataModal?>
            ) {
                if (response.isSuccessful()) {
                    // inside the on response method.
                    // we are hiding our progress bar.
                    loadingPB.visibility = View.GONE

                    // on below line we are getting data from our response
                    // and setting it in variables.
                    val courseName: String = response.body()!!.courseName
                    val courseLink: String = response.body()!!.courseLink
                    val courseImg: String = response.body()!!.courseimg
                    val courseDesc: String = response.body()!!.courseDesc
                    val coursePreq: String = response.body()!!.Prerequisites

                    // on below line we are setting our data
                    // to our text view and image view.
                    courseReqTV.text = coursePreq
                    courseDescTV.text = courseDesc
                    courseNameTV.text = courseName

                    // on below line we are setting image view from image url.
                    Picasso.get().load(courseImg).into(courseIV)

                    // on below line we are changing visibility for our button.
                    visitCourseBtn.visibility = View.VISIBLE

                    // on below line we are adding click listener for our button.
                    visitCourseBtn.setOnClickListener {
                        // on below line we are opening a intent to view the url.
                        val i = Intent(Intent.ACTION_VIEW)
                        i.setData(Uri.parse(courseLink))
                        startActivity(i)
                    }
                }
            }

            override fun onFailure(call: Call<CourseDataModal?>?, t: Throwable?) {
                // displaying an error message in toast
                Toast.makeText(context, "Fail to get the data..", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}