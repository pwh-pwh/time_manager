package com.coderpwh.timemanager.ui.screen.mainnav.home

data class DateState(
    var day: String = "",
    var week: String = ""
)

data class TimeState(
    var hour: Int = 0,
    var minute: Int = 0,
    var second: Int = 0
)

data class UIState(
    var timeMode: TimeMode = TimeMode.PM,
    var immersionShow: Boolean = false
)

enum class TimeMode {
    PM,
    AM
}
