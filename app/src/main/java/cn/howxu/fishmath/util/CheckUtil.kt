package cn.howxu.fishmath.util

import android.widget.EditText
import java.math.BigDecimal
import java.math.BigInteger

class CheckUtil {
    /**
     * 简单的三角形判断
     * @param a,b,c 三边
     */
    fun isTriangle(a: BigDecimal, b:BigDecimal, c:BigDecimal):Boolean{

        if (a.add(b) > c && b.add(c) > a && c.add(a) > b){
            return true
        }
        return false
    }

    fun isNotNull(ed:EditText):Boolean{
        if (ed.text.toString() == ""){
            return false
        }
        return true
    }

    fun isNotNull(ed1:EditText,ed2:EditText):Boolean{
        if (ed1.text.toString() == ""){
            return false
        }
        if (ed2.text.toString() == ""){
            return false
        }

        return true
    }

    fun isNotNull(ed1:EditText,ed2:EditText,ed3:EditText):Boolean{
        if (ed1.text.toString() == ""){
            return false
        }
        if (ed2.text.toString() == ""){
            return false
        }
        if (ed3.text.toString() == ""){
            return false
        }

        return true
    }

}