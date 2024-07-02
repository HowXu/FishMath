package cn.howxu.fishmath.bottom

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import cn.howxu.fishmath.R
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.input.input
import com.tapadoo.alerter.Alerter


class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    @SuppressLint("CheckResult") //咱也不知道加这个有啥用
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 修不好URL跳转孩子
        val github: View = view.findViewById(R.id.github_page)
        github.setOnClickListener {
            //这里不能用 CATE.BROWSERABLE属性不然找不到Activtity处理
            val intent =
                Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://github.com/HowXu/FishMath"))    //务必要有https前缀不然找不到Activity
            requireActivity().startActivity(intent)
        }

        val mail: View = view.findViewById(R.id.mail_page)
        mail.setOnClickListener {
            //这里不能用 CATE.BROWSERABLE属性不然找不到Activtity处理
            val intent =
                Intent(Intent.ACTION_VIEW).setData(Uri.parse("mailto:howxu366@outlook.com"))    //务必要有https前缀不然找不到Activity
            requireActivity().startActivity(intent)
        }

        val about_fish: View = view.findViewById(R.id.about_fish_page)
        about_fish.setOnClickListener {
            MaterialDialog(requireActivity())
                .title(R.string.about_fish)
                .message(R.string.fish_bottom)
                .icon(R.drawable.fish)
                .show {
                    positiveButton(text = getString(R.string.agree))
                }
        }


        val jay: View = view.findViewById(R.id.jay_music)

        var player = MediaPlayer.create(requireActivity(),R.raw.jay)

        jay.setOnClickListener {

            MaterialDialog(requireActivity())
                .title(R.string.colorful_egg)
                .icon(R.drawable.jay).show {
                    input(hintRes = R.string.input_hint) { dialog, text ->
                        if (text.toString() == "3536783510"){
                               try {
                                   val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=3536783510"))
                                   requireActivity().startActivity(intent)
                               }catch(_:Exception) {
                                   Alerter.create(requireActivity()).setText(getString(R.string.bug))
                                       .setTitle(getString(R.string.result)).setBackgroundColorInt(Color.rgb(219, 92, 92)).show()
                               }
                        }else if(text.toString() == "3286105285"){
                            try {
                                val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=3286105285"))
                                requireActivity().startActivity(intent)
                            }catch(_:Exception) {
                                Alerter.create(requireActivity()).setText(getString(R.string.bug))
                                    .setTitle(getString(R.string.result)).setBackgroundColorInt(Color.rgb(219, 92, 92)).show()
                            }
                        }else if(text.toString() == "晴天"){
                            player.start() //对了请你听歌
                            val dlg2 = MaterialDialog(requireActivity())
                                .title(R.string.stop)
                                .icon(R.drawable.jay)
                                dlg2.setCanceledOnTouchOutside(false)
                            dlg2.setOnKeyListener { dialogInterface, i, keyEvent ->
                                if (keyEvent.keyCode == KeyEvent.KEYCODE_BACK){
                                    //我了个和用户斗智斗勇啊
                                    dlg2.dismiss()
                                    player.stop()
                                    player = MediaPlayer.create(requireActivity(),R.raw.jay)
                                    return@setOnKeyListener true
                                }
                                return@setOnKeyListener false
                            }
                            dlg2.show{
                                    negativeButton(text = getString(R.string.close)) {
                                        player.stop()
                                        player = MediaPlayer.create(requireActivity(),R.raw.jay)
                                        //TODO 这样写感觉内存和性能消耗有点大啊孩子
                                    }
                                }
                        }else{
                            Alerter.create(requireActivity()).setText(getString(R.string.nothing)).setIcon(R.drawable.what)
                                .setTitle(getString(R.string.nothing_title)).setBackgroundColorInt(Color.rgb(95, 173, 101)).show()
                        }
                    }
                    positiveButton(text = getString(R.string.submit))
                }
        }
    }

}