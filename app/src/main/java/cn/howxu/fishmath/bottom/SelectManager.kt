package cn.howxu.fishmath.bottom

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import cn.howxu.fishmath.R

class SelectManager(fmo: FragmentManager) {

    //val ft:FragmentTransaction = fm.beginTransaction()
    val fm:FragmentManager = fmo

    fun loadFragment(fragment: Fragment):Unit {
        val ft:FragmentTransaction = fm.beginTransaction()
        ft.replace(R.id.content, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }

}