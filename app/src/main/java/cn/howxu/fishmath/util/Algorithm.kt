package cn.howxu.fishmath.util

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext

class Algorithm {

    //TODO 你这怎么国际化

    /**
     * Page7 质数的判断
     * 这里使用BigInt大数运算满足更多场景
     * 我感觉所有都要做大数运算T_
     *
     * @param 被判断数字n
     */
    fun Page7_PrimeNumberJudge(n: BigInteger): String {

        if (n.toString() == ""){
            return "请输入内容"
        }
        if (n <= BigInteger("2")){
            return "请输入大于2的数"
        }

        var i:BigInteger = BigInteger("2")
        var r:BigInteger = BigInteger("0")
        do {
            r = n.remainder(i) //取余
            i++

        } while (!(i > n.subtract(BigInteger("1"))  || r == BigInteger("0"))) //i > n -1 || r == 0
        if (r == BigInteger("0")) {
            return "n不是质数"
        } else {
            return "n是质数"
        }
    }

    fun Page9_Herons_formula(a:BigDecimal, b:BigDecimal, c:BigDecimal):String{
        var p = a.add(b).add(c) / BigDecimal("2")
        println(p.toString())
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            try {
               return (p.multiply(p.subtract(a).multiply(p.subtract(b)).multiply(p.subtract(c)))).sqrt(MathContext(10)).toString()
            }catch (e:Exception){
                return "看看你输入些什么?"
            }
        } else {
            return "你的设备不支持内置大数计算(以后可能优化T_)"
        }
    }
}