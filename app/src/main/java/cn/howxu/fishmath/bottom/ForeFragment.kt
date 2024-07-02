package cn.howxu.fishmath.bottom

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import cn.howxu.fishmath.R
import cn.howxu.fishmath.util.Algorithm
import cn.howxu.fishmath.util.CheckUtil
import com.github.nikartm.button.FitButton
import com.tapadoo.alerter.Alerter
import java.math.BigDecimal
import java.math.BigInteger

class ForeFragment : Fragment() {

    val algorithmManager: Algorithm = Algorithm()
    val checkManager: CheckUtil = CheckUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }


    override fun onResume() {
        super.onResume()
        //聚焦
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener{ _: View, i: Int, keyEvent: KeyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_UP && i == KeyEvent.KEYCODE_BACK){
                //监听返回，直接退出
                System.exit(0)
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fore, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //添加问号搜索弹窗说我在TODO
        val icon: ImageButton = requireActivity().findViewById(R.id.search)
        icon.setOnClickListener {
            //println("??")
            //System.exit(0)
            Alerter.create(requireActivity()).setText(getString(R.string.do_u_know))
                .setTitle(getString(R.string.onDev)).setBackgroundColorInt(Color.rgb(168, 216, 205)).setIcon(
                    R.drawable.happy
                ).show()
        }

        //Page7 质数的判断处理逻辑
        val page7_edittext: EditText = requireActivity().findViewById(R.id.page7_edit1)
        val page7_submit_button: FitButton =requireActivity().findViewById(R.id.page7_submit_button)
        page7_submit_button.setOnClickListener {
            //添加了null判定算是修个小bug
            if (checkManager.isNotNull(page7_edittext)){
                Alerter.create(requireActivity()).setText(
                    algorithmManager.Page7_PrimeNumberJudge(
                        BigInteger(page7_edittext.text.toString())
                    )//结果
                ).setTitle(getString(R.string.result)).setDuration(3000L).setBackgroundColorInt(Color.rgb(22, 175, 245))
                    .show()
            }
        }

        //Page9 海伦-秦九韶公式
        val page9_edittext_a: EditText = requireActivity().findViewById(R.id.page9_edit1)
        val page9_edittext_b: EditText = requireActivity().findViewById(R.id.page9_edit2)
        val page9_edittext_c: EditText = requireActivity().findViewById(R.id.page9_edit3)
        val page9_submit_button: FitButton =requireActivity().findViewById(R.id.page9_submit_button)
        page9_submit_button.setOnClickListener{

            //判断是否为空，否则无处理
            if (checkManager.isNotNull(page9_edittext_a,page9_edittext_b,page9_edittext_c)){
                var a = BigDecimal(page9_edittext_a.text.toString())
                var b = BigDecimal(page9_edittext_b.text.toString())
                var c = BigDecimal(page9_edittext_c.text.toString())
                var checkout = ""
                checkout = if (!checkManager.isTriangle(a,b,c)){
                    getString(R.string.is_not_triangle)
                }else{
                    algorithmManager.Page9_Herons_formula(a,b,c)
                }
                Alerter.create(requireActivity()).setText(
                    checkout
                ).setTitle(getString(R.string.result)).setDuration(5000L).setBackgroundColorInt(Color.rgb(22, 175, 245))
                    .show()
            }
        }

        //Page12 求解一元二次方程
        val page12_edittext_a: EditText = requireActivity().findViewById(R.id.page12_edit1)
        val page12_edittext_b: EditText = requireActivity().findViewById(R.id.page12_edit2)
        val page12_edittext_c: EditText = requireActivity().findViewById(R.id.page12_edit3)
        val page12_submit_button: FitButton =requireActivity().findViewById(R.id.page12_submit_button)
        page12_submit_button.setOnClickListener{

            //判断是否为空，否则无处理
            if (checkManager.isNotNull(page12_edittext_a,page12_edittext_b,page12_edittext_c)){
                var a = BigDecimal(page12_edittext_a.text.toString())
                var b = BigDecimal(page12_edittext_b.text.toString())
                var c = BigDecimal(page12_edittext_c.text.toString())
                var checkout = ""
                var result = algorithmManager.Page12_Binary_Linear_Equation_Group(a,b,c)
                checkout = when(result.code) {
                    1 -> {
                        "x1=" + result.s1 + " , "+ "x2=" + result.s2
                    }
                    else -> {
                        result.s1 //我勒个代码逻辑啊
                    }
                }
                Alerter.create(requireActivity()).setText(
                    checkout
                ).setTitle(getString(R.string.result)).setDuration(5000L).setBackgroundColorInt(Color.rgb(22, 175, 245))
                    .show()
            }
            }


        //Page15 求1，2，...，100的和 升级版
        val page15_edittext: EditText = requireActivity().findViewById(R.id.page15_edit1)
        val page15_submit_button: FitButton =requireActivity().findViewById(R.id.page15_submit_button)
        val page15_attention_image: ImageButton =requireActivity().findViewById(R.id.page15_attention)

        page15_attention_image.setOnClickListener{
            Alerter.create(requireActivity()).setText(
                getString(R.string.page15_attention)
            ).setTitle(getString(R.string.result)).setDuration(4000L).setBackgroundColorInt(Color.rgb(229, 115, 115)).setIcon(R.drawable.attention)
                .show()
        }
        page15_submit_button.setOnClickListener{
            if (checkManager.isNotNull(page15_edittext)){
                var a = BigInteger(page15_edittext.text.toString())
                Alerter.create(requireActivity()).setText(
                    algorithmManager.Page15_sum(a)
                ).setTitle(getString(R.string.result)).setDuration(5000L).setBackgroundColorInt(Color.rgb(22, 175, 245))
                    .show()
            }
        }

        //Page15 求超过产值某一值最早年份
        val page15_edittext_2: EditText = requireActivity().findViewById(R.id.page15_edit1_2)
        val page15_submit_button_2: FitButton =requireActivity().findViewById(R.id.page15_submit_button_2)
        val page15_attention_image_2: ImageButton =requireActivity().findViewById(R.id.page15_attention_2)

        page15_attention_image_2.setOnClickListener{
            Alerter.create(requireActivity()).setText(
                getString(R.string.page15_attention_2)
            ).setTitle(getString(R.string.result)).setDuration(4000L).setBackgroundColorInt(Color.rgb(229, 115, 115)).setIcon(R.drawable.attention)
                .show()
        }
        page15_submit_button_2.setOnClickListener{
            if (checkManager.isNotNull(page15_edittext_2)){
                var b = BigDecimal(page15_edittext_2.text.toString())
                Alerter.create(requireActivity()).setText(
                    algorithmManager.Page15_ForecastOutputValue(b)
                ).setTitle(getString(R.string.result)).setDuration(5000L).setBackgroundColorInt(Color.rgb(22, 175, 245))
                    .show()
            }
        }

        //Page25 求实数x的绝对值
        val page25_edittext: EditText = requireActivity().findViewById(R.id.page25_edit1)
        val page25_submit_button: FitButton =requireActivity().findViewById(R.id.page25_submit_button)
        page25_submit_button.setOnClickListener {
            //添加了null判定算是修个小bug
            if (checkManager.isNotNull(page25_edittext)){
                Alerter.create(requireActivity()).setText(
                    algorithmManager.Page25_AbsoluteValue(
                        BigDecimal(page25_edittext.text.toString())
                    )//结果
                ).setTitle(getString(R.string.result)).setDuration(3000L).setBackgroundColorInt(Color.rgb(22, 175, 245))
                    .show()
            }
        }

        }
    }


