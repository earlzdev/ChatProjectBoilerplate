package com.earl.chatprojectboilerplate.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.earl.chatprojectboilerplate.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

/**
 * Прошу прощения за костыльные решения и вырвиглазный дизайн. Как бы глупо это не звучало, на данный момент я
 * чувствую, что сильно выгорел и испытываю почти физическое отторжение от любимой работы. Тем не менее, немного жаль потраченого времени
 * и я решил сдать задание в таком виде, так как нет ни сил ни желания причесывать код. Anyway, увроень моих знаний можно примерно понять из кода.
 * Благодарю за понимание, спасибо
 */