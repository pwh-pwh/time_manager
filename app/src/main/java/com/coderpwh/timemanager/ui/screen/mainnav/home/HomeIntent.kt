package com.coderpwh.timemanager.ui.screen.mainnav.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class HomeViewModel:ViewModel() {
    private val WeekList = listOf<String>("一","二","三","四","五","六","七")
    private var _dateState = MutableStateFlow(DateState())
    val dateState = _dateState
    private var _timeState = MutableStateFlow(TimeState())
    val timeState = _timeState
    private var _uiState = MutableStateFlow(UIState())
    val uiState = _uiState
    init {
        initTime()
    }
    private fun initTime () {
        LocalDateTime.now().apply {
            _timeState.also {
                it.value = timeState.value.copy(hour = hour,minute = minute,second = second)
                _uiState.value = when(hour) {
                    !in 0..11 -> uiState.value.copy(timeMode = TimeMode.PM)
                        else -> uiState.value.copy(timeMode = TimeMode.AM)
                }
            }
            _dateState.also {
                it.value = dateState.value.copy(day = "$year/$monthValue/$dayOfMonth", week = "(${WeekList[dayOfWeek.value-1]})")
            }
        }
        updateTime()
    }

    private fun updateTime() {
        viewModelScope.launch(Dispatchers.IO) {
            _timeState.apply {
                while(true) {
                    val nowSecond = value.second+1
                    if (nowSecond==60) {
                        value = value.copy(second = 0)
                        val nowMinute = value.minute +1
                        if(nowMinute==60) {
                            value = value.copy(minute = 0)
                            //update hour
                            val nowHour = value.hour +1
                            val timeMode = if(nowHour>12) {
                                TimeMode.PM
                            } else {
                                TimeMode.AM
                            }
                            //change timeMode
                            _uiState.value = uiState.value.copy(timeMode=timeMode)
                            value = if(nowHour==24) {
                                value.copy(hour = 0)
                            } else {
                                value.copy(hour = nowHour)
                            }
                        } else {
                            value = value.copy(minute = nowMinute)
                        }
                    } else {
                        value = value.copy(second = nowSecond)
                    }
                    delay(1000)
                }

            }
        }
    }
}