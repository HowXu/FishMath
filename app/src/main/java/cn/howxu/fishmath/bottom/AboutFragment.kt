package cn.howxu.fishmath.bottom

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.howxu.fishmath.R


class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 修不好URL跳转孩子
        val github: View = view.findViewById(R.id.github_page)
        github.setOnClickListener{
            //这里不能用 CATE.BROWSERABLE属性不然找不到Activtity处理
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://github.com/HowXu/FishMath"))    //务必要有https前缀不然找不到Activity
            requireActivity().startActivity(intent)
        }
    }

}