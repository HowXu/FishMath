package cn.howxu.fishmath

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import cn.howxu.fishmath.bottom.AboutFragment
import cn.howxu.fishmath.bottom.ForeFragment
import cn.howxu.fishmath.bottom.SelectManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class Entry : AppCompatActivity() {

    //private var ft:FragmentTransaction = supportFragmentManager.beginTransaction()
    //private val mForeFragment: ForeFragment = ForeFragment()
    //private val mAboutFragment: AboutFragment = AboutFragment()

    //看不懂继承
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val selectManager: SelectManager = SelectManager(supportFragmentManager)
        //开头在首页
        selectManager.loadFragment(ForeFragment())
        //loadFragment(ForeFragment())

        //监听按钮变化
        val bottom: BottomNavigationView = findViewById(R.id.bottom)
        bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
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

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        if (ev!!.action == MotionEvent.ACTION_DOWN) {
            var v: View? = currentFocus
            if (v != null) {
                //这里不加非空判断也是正常的但是会在Focus更替时闪退
                if (isShouldHideInput(v, ev)) {
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    //assert v != null;原版Java的写法应该和闪退有关
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                    /** here 另一个版本的Focus更替*/
                    v.clearFocus()
                }
            }
            return super.dispatchTouchEvent(ev)
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return window.superDispatchTouchEvent(ev) || onTouchEvent(ev)
    }


    fun isShouldHideInput(v: View, event: MotionEvent): Boolean {
        if (v is EditText) {
            val leftTop: IntArray = intArrayOf(0, 0)
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop)
            val left: Int = leftTop[0]
            val top: Int = leftTop[1]
            val bottom: Int = top + v.getHeight()
            val right: Int = left + v.getWidth()
            return !(event.x > left && event.x < right
                    && event.y > top && event.y < bottom)
        }
        return false
    }

}
