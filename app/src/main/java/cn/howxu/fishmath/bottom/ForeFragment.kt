package cn.howxu.fishmath.bottom

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import cn.howxu.fishmath.R
import cn.howxu.fishmath.util.Algorithm
import com.github.nikartm.button.FitButton
import com.tapadoo.alerter.Alerter
import java.math.BigInteger

class ForeFragment : Fragment() {

    val algorithmManager: Algorithm = Algorithm()

    override fun onResume() {
        super.onResume()
        //聚焦
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener{ view: View, i: Int, keyEvent: KeyEvent ->
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
            Alerter.create(requireActivity()).setText("你小子知道这个通知多难做吗T_")
                .setTitle("正在开发中...").setBackgroundColorInt(Color.rgb(168, 216, 205)).setIcon(
                    com.tapadoo.alerter.R.drawable.alerter_ic_face
                ).show()
        }

        //Page7 质数的判断处理逻辑
        val page7_edittext: EditText = requireActivity().findViewById(R.id.page7_edit1)
        val page7_submit_button: FitButton =
            requireActivity().findViewById(R.id.page7_submit_button)
        page7_submit_button.setOnClickListener {
            Alerter.create(requireActivity()).setText(
                algorithmManager.Page7_PrimeNumberJudge(
                    BigInteger(page7_edittext.text.toString())
                )
            ).setTitle("结果").setDuration(3000L).setBackgroundColorInt(Color.rgb(22, 175, 245))
                .show()
        }


    }


}