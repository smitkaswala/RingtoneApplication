package com.example.ringtoneapplication

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ringtoneapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var isPlayButtonStart = false
    private var audioFileName: String = "skype"
    private lateinit var progressUpdate: Runnable
    private var mMediaPlayer: MediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            if (!isPlayButtonStart) {
                isPlayButtonStart = true
                playAudio(this@MainActivity, audioFileName)
            } else {
                stopAudio()
                isPlayButtonStart = false
            }
        }

    }

    @SuppressLint("DiscouragedApi")
    private fun playAudio(mContext: Context, fileName: String) {
        try {
            mMediaPlayer = MediaPlayer.create(
                mContext,
                mContext.resources.getIdentifier(fileName, "raw", mContext.packageName)
            )
            mMediaPlayer.setOnCompletionListener {
                isPlayButtonStart = false
            }
            mMediaPlayer.start()
            updateProgress()

        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    private fun stopAudio() {
        try {

            mMediaPlayer.release()
            mMediaPlayer.pause()

        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    private fun updateProgress() {
        val interval: Long = 100

        progressUpdate.run()
    }

    private fun pauseAudio() {
        try {
            isPlayButtonStart=false
            mMediaPlayer.pause()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

}