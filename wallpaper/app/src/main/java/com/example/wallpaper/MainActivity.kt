package com.example.wallpaper

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.imageviewr.view.*
import java.net.URL
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     /* mylist.adapter=myAdapter()
        mylist.setOnItemClickListener { adapterView, view, i, l ->



        }*/

    var data=Firebase.database.reference.child("images")
        data.get().addOnSuccessListener {
            var dat=it.value as ArrayList<String>
            println("........................................")
            dat.removeAt(0)
            println(dat)
            mylist.adapter=myAdapter(this,dat)
        }



}
    }
class  myAdapter(cxt:Context,data:ArrayList<String>) :BaseAdapter()
{
    var cxt:Context
    var data:ArrayList<String>
    init {
        this.cxt=cxt
        this.data=data
    }
    override fun getCount(): Int {
       return  data.size
    }

    override fun getItem(p0: Int): Any {
        return ""
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val myInflate= LayoutInflater.from(cxt).inflate(R.layout.imageviewr,p2,false)

        Picasso.get().load(Uri.parse(data[p0])).into(myInflate.imageView)



       myInflate.button.setOnClickListener {
           changeWallPaper(cxt,data[p0])
           Toast.makeText(cxt,"wallpaper has been changed",Toast.LENGTH_SHORT).show()
       }
        return  myInflate
    }

}
fun changeWallPaper(cxt:Context,img:String){
    try {
        thread(start = true) {
            val inputStream = URL(img).openStream()
            WallpaperManager.getInstance(cxt).setStream(inputStream)
        }
    }
    catch (err:java.lang.Error)
    {
        println("error")
    }
}

