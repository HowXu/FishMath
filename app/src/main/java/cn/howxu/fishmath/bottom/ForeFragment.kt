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
                    com.tapadoo.alerter.R.drawable.alerter_ic_face
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
            var a = BigDecimal(page9_edittext_a.text.toString())
            var b = BigDecimal(page9_edittext_b.text.toString())
            var c = BigDecimal(page9_edittext_c.text.toString())
            var checkout = ""
            if (checkManager.isNotNull(page9_edittext_a,page9_edittext_b,page9_edittext_c)){
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

        

    }




}