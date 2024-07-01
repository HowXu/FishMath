package cn.howxu.fishmath.util

import java.math.BigInteger

class Algorithm {

    /**
     * Page7 质数的判断
     * 这里使用BigInt大数运算满足更多场景
     * 我感觉所有都要做大数运算T_
     *
     * @param 被判断数字n
     */
    fun Page7_PrimeNumberJudge(n: BigInteger): String {
        if (n <= BigInteger("2")){
            return "请输入大于2的数"
        }

        var i:BigInteger = BigInteger("2")
        var r:BigInteger = BigInteger("0")
        do {
            r = n.remainder(i) //取余
            i++

        } while (!(i > n.min(BigInteger("1"))  || r == BigInteger("0"))) //i > n -1 || r == 0
        if (r == BigInteger("0")) {
            return "n不是质数"
        } else {
            return "n是质数"
        }
    }
}