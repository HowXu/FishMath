package cn.howxu.fishmath.util

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import cn.howxu.fishmath.list.Page12_Result
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

        if (n.toString() == "") {
            return "请输入内容"
        }
        if (n <= BigInteger("2")) {
            return "请输入大于2的数"
        }

        var i: BigInteger = BigInteger("2")
        var r: BigInteger = BigInteger("0")
        do {
            r = n.remainder(i) //取余
            i++

        } while (!(i > n.subtract(BigInteger("1")) || r == BigInteger("0"))) //i > n -1 || r == 0
        if (r == BigInteger("0")) {
            return "n不是质数"
        } else {
            return "n是质数"
        }
    }

    /**
     * 海伦公式 p(p-a)(p-b)(p-c)的平方根，其中p为2分之a+b+c
     * @param a,b,c 三边
     */
    fun Page9_Herons_formula(a: BigDecimal, b: BigDecimal, c: BigDecimal): String {
        var p = a.add(b).add(c) / BigDecimal("2") //求p
        println(p.toString())
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            try {//这里有api版本判定
                return (p.multiply(
                    p.subtract(a).multiply(p.subtract(b)).multiply(p.subtract(c))
                )).sqrt(MathContext(10)).toString()
            } catch (e: Exception) {
                return "看看你输入些什么?"
            }
        } else {
            return "你的设备不支持内置大数开方(以后可能优化T_)"
        }
    }

    /**
     * 直接计算二元一次方程组的根
     * @param a,b,c 对应ax2+bx+c=0
     */
    fun Page12_Binary_Linear_Equation_Group(
        a: BigDecimal,
        b: BigDecimal,
        c: BigDecimal
    ): Page12_Result {
        var derta = b.multiply(b).subtract(BigDecimal("4.0").multiply(a).multiply(c)) //求derta
        if (derta >= BigDecimal("0")) {
            var p = b.divide(BigDecimal("2.0").multiply(a)).negate() //取负的2a分之b
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                var q = derta.sqrt(MathContext(4)).divide(BigDecimal("2.0").multiply(a))
                return Page12_Result(1, p.add(q).toString(), p.subtract(q).toString())
            } else {
                return Page12_Result(2, "你的设备不支持内置大数开方(以后可能优化T_)", "")
            }
        }
        return Page12_Result(0, "方程没有实数根", "")
    }

    /**
     * Page15 求和 太简单了孩子
     *
     * @param 求到的位置n
     */
    fun Page15_sum(n: BigInteger): String {
        if (n == BigInteger("0")) {
            return "那你也不能拿个0来啊"
        }
        var i = BigInteger("1")
        var S = BigInteger("0")
        do {
            S = S.add(i)
            i = i.add(BigInteger("1"))
        } while (!(i > n))
        return S.toString()
    }

    /**
     * Page15 预估生产总值，太简单了孩子
     *
     * @param 求取总值数量
     * @return 年份
     */
    fun Page15_ForecastOutputValue(b: BigDecimal): String {
        var n = BigDecimal("2005")
        var a = BigDecimal("200")
        var t = BigDecimal("0")
        if (b <= a) {
            return "那我还算什么"
        }
        do {
            t = a.multiply(BigDecimal("0.05"))
            a = a.add(t)
            n++
        }while (!(a > b))
        // 记住了是超过不是大于等于
        return "年份为$n"
    }
}