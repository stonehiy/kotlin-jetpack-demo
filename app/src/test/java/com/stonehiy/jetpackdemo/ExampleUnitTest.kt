package com.stonehiy.jetpackdemo

import kotlinx.coroutines.delay
import org.junit.Test

import org.junit.Assert.*
import timber.log.Timber
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test() {
//        val scheduledThreadPool = Executors.newScheduledThreadPool(1)//一个线程的并行任务线程池 。。
//        val timerTask = timerTask {
//            println("TimerTask")
//        }
//        scheduledThreadPool.scheduleWithFixedDelay(timerTask, 1000, 1000, TimeUnit.MILLISECONDS)


        timer("timer", true, 0, 1000) {
            println(" timer TimerTask")
        }

        Thread.sleep(10000)
    }
}
