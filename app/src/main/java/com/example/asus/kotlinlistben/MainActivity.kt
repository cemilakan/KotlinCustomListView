package com.example.asus.kotlinlistben

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import java.security.AccessControlContext
import java.text.FieldPosition
import android.view.animation.AlphaAnimation
import android.view.animation.Animation



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listview=findViewById<ListView>(R.id.main_listview)//Öncelikle Listview öğemizi tanımlıyoruz.
        listview.adapter=MyCustomAdapter(this)// Ardından liste için bir adaptor oluşturuyoruz ve "MyCustomAdapter" fonksiyonumuzu oluşturuyoruz.

        listview.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val animation1 = AlphaAnimation(0.3f, 1.0f)
            animation1.duration = 500
            view.startAnimation(animation1)

        })

    }
    private class MyCustomAdapter(context:Context):BaseAdapter(){

        private val mContext:Context
        //Aşağıda bir array(dizi) oluşturduk bu diziyi ileride listeye yükleyeceğiz
        private val names= arrayListOf<String>(
                "Ali Kaya","Mehmet Taş"," Ziya Meteor","Emre Vurgun", "Ziya Duru"
        )

        init {
            mContext=context
        }
        //Burada listenin kça satırdan oluşacağını soruyor bizde listemizin boyutu kadar olsun istedik.
        override fun getCount(): Int {
            return names.size
        }
        //Burada ise satırın id sini long türünda return ediyoruz
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
        //Bununla bir işimiz yoktur
        override fun getItem(position: Int): Any {
            return "Deneme String"
        }
        //Burada listenin görüntüsünü yollayacağız. Öncelikle Inflater kullancağız
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            //Burada inflaterı yukarıda olusturduğumuz Contextten alıyor
            val layouinflator=LayoutInflater.from(mContext)
            //Aşağıda satırın görüntüsünü tasarımını nereden alması gerktiğini söylüyoruz önceden oluşturduğumuz row_main isimli XML den alacaktır
            val rowMain=layouinflator.inflate(R.layout.row_main,viewGroup,false)
            //Burada ise satırdaki name_Text'i tanımlıyoruz ve ardından değerini veiryoruz bir alt satırda
            val nameTextView=rowMain.findViewById<TextView>(R.id.name_textView)
            //Değeri bulunduğumuz satırın id sine göre Üstten names isminde oluşturduğumuz dizinin position'una göre değer alıyor
            nameTextView.text="Name: "+names.get(position)
            //Yine aynı şekilde pozisyonumuzu yazdıracağımız text'i tanımladık.
            val positionTextView=rowMain.findViewById<TextView>(R.id.position_textView)
            //Ve değerini pozisyon olarak verdik yani her satırın index numarasını gösterecektir
            positionTextView.text="Satır İndex: $position"
            if(position %2 == 1)
            {
                // satırın rengini değiştirir tek sayı ise pozisyon numarası
                rowMain.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            else
            {
                // satırın rengini değiştirir çift sayı ise pozisyon numarası
                rowMain.setBackgroundColor(Color.parseColor("#9E9E9E"));
            }

            return  rowMain


        }
    }
}
