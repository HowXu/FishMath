package cn.howxu.fishmath

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import cn.howxu.fishmath.bottom.AboutFragment
import cn.howxu.fishmath.bottom.ForeFragment
import cn.howxu.fishmath.bottom.SelectManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tapadoo.alerter.Alerter

class Entry : AppCompatActivity() {

    //private var ft:FragmentTransaction = supportFragmentManager.beginTransaction()
    //private val mForeFragment: ForeFragment = ForeFragment()
    //private val mAboutFragment: AboutFragment = AboutFragment()

    //看不懂继承
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        val selectManager: SelectManager = SelectManager(supportFragmentManager)
        //开头在首页
        selectManager.loadFragment(ForeFragment())
        //loadFragment(ForeFragment())

        //监听按钮变化
        val bottom:BottomNavigationView = findViewById(R.id.bottom)
        bottom.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.forepage -> {
                    //title = resources.getString(R.string.f)
                    selectManager.loadFragment(ForeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.about -> {
                    //title = resources.getString(R.string.news)
                    selectManager.loadFragment(AboutFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }


        //val searchIcon: ImageButton =  LayoutInflater.from(this).inflate(R.layout.fragment_fore,null).findViewById(R.id.search)
    }

}
