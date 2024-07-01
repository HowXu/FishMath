package cn.howxu.fishmath.bottom

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintSet.Layout
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
        //TODO 修不好URL跳转孩子
        val github: View? = requireActivity().findViewById(R.id.github_page)
        github!!.setOnClickListener{
            val intent = Intent().setData(Uri.parse("baidu.com"))
            requireContext().startActivity(intent)
        }
    }

}