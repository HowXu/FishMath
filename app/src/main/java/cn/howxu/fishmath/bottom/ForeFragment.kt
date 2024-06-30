package cn.howxu.fishmath.bottom

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import cn.howxu.fishmath.Entry
import cn.howxu.fishmath.R
import com.tapadoo.alerter.Alert
import com.tapadoo.alerter.Alerter

class ForeFragment : Fragment() {



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
        val icon:ImageButton = requireActivity().findViewById(R.id.search)
        icon.setOnClickListener{
            //println("??")
            Alerter.create(requireActivity()).setText("你小子知道这个通知多难做吗T_").setTitle("正在开发中...").setBackgroundColorInt(Color.GREEN).show()
        }
    }
}