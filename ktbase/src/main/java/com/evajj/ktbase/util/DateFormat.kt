package com.evajj.ktbase.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Author:wenjunjie
 * Date:2023/2/10
 * Time:下午4:20
 * Description:
 **/
/** 一星期的天数  */
const val WEEK_DAYS = 7

/** 一年的月份数  */
const val YEAR_MONTHS = 12

/** 一天的小时数  */
const val DAY_HOURS = 24

/** 一小时分钟数  */
const val HOUR_MINUTES = 60

/** 一天分钟数 (24 * 60)  */
const val DAY_MINUTES = 1440

/** 一分钟的秒数  */
const val MINUTE_SECONDS = 60

/** 一个小时的秒数 (60 * 60)  */
const val HOUR_SECONDS = 3600

/** 一天的秒数 (24 * 60 * 60)  */
const val DAY_SECONDS = 86400

/** 一秒的毫秒数  */
const val SECOND_MILLISECONDS = 1000L

/** 一分钟的毫秒数（60 * 1000）  */
const val MINUTE_MILLISECONDS = 60000L

/** 一小时的毫秒数（60 * 60 * 1000）  */
const val HOUR_MILLISECONDS = 3600000L

/** 一天的毫秒数（24 * 60* 60* 1000）  */
const val DAY_MILLISECONDS = 86400000L

/** 星期一  */
const val WEEK_1_MONDAY = 1

/** 星期二  */
const val WEEK_2_TUESDAY = 2

/** 星期三  */
const val WEEK_3_WEDNESDAY = 3

/** 星期四  */
const val WEEK_4_THURSDAY = 4

/** 星期五  */
const val WEEK_5_FRIDAY = 5

/** 星期六  */
const val WEEK_6_SATURDAY = 6

/** 星期天  */
const val WEEK_7_SUNDAY = 7

/** 一月  */
const val MONTH_1_JANUARY = 1

/** 二月  */
const val MONTH_2_FEBRUARY = 2

/** 三月  */
const val MONTH_3_MARCH = 3

/** 四月  */
const val MONTH_4_APRIL = 4

/** 五月  */
const val MONTH_5_MAY = 5

/** 六月  */
const val MONTH_6_JUNE = 6

/** 七月  */
const val MONTH_7_JULY = 7

/** 八月  */
const val MONTH_8_AUGUST = 8

/** 九月  */
const val MONTH_9_SEPTEMBER = 9

/** 十月  */
const val MONTH_10_OCTOBER = 10

/** 十一月  */
const val MONTH_11_NOVEMBER = 11

/** 十二月  */
const val MONTH_12_DECEMBER = 12

/** 显示到日期  */
const val FORMAT_DATE = "yyyy-MM-dd"

/** 显示到月日  */
const val FORMAT_DATE_MONTH = "MM-dd"

/** 显示到小时  */
const val FORMAT_HOUR = "yyyy-MM-dd HH"

/** 显示到分  */
const val FORMAT_MINUTE = "yyyy-MM-dd HH:mm"

/** 显示到秒  */
const val FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss"

/** 显示到毫秒  */
const val FORMAT_MILLISECOND = "yyyy-MM-dd HH:mm:ss:SSS"

/** 显示到日期（数字格式）  */
const val FORMAT_NO_DATE = "yyyyMMdd"

/** 显示到小时（数字格式）  */
const val FORMAT_NO_HOUR = "yyyyMMddHH"

/** 显示到分（数字格式）  */
const val FORMAT_NO_MINUTE = "yyyyMMddHHmm"

/** 显示到秒（数字格式）  */
const val FORMAT_NO_SECOND = "yyyyMMddHHmmss"

/** 显示到毫秒（数字格式）  */
const val FORMAT_NO_MILLISECOND = "yyyyMMddHHmmssSSS"

/** 时间格式化器集合  */
class DateFormat {
   companion object{

       val simpleDateFormatMap: MutableMap<String, SimpleDateFormat> = mutableMapOf()

       init {
           simpleDateFormatMap[FORMAT_DATE] =  SimpleDateFormat(FORMAT_DATE)
           simpleDateFormatMap[FORMAT_DATE_MONTH] =  SimpleDateFormat (FORMAT_DATE_MONTH)
           simpleDateFormatMap[FORMAT_HOUR] =  SimpleDateFormat (FORMAT_HOUR)
           simpleDateFormatMap[FORMAT_MINUTE] =  SimpleDateFormat (FORMAT_MINUTE)
           simpleDateFormatMap[FORMAT_SECOND] =  SimpleDateFormat (FORMAT_SECOND)
           simpleDateFormatMap[FORMAT_MILLISECOND] =  SimpleDateFormat (FORMAT_MILLISECOND)
           simpleDateFormatMap[FORMAT_NO_DATE] =  SimpleDateFormat (FORMAT_NO_DATE)
           simpleDateFormatMap[FORMAT_NO_HOUR] =  SimpleDateFormat (FORMAT_NO_HOUR)
           simpleDateFormatMap[FORMAT_NO_MINUTE] =  SimpleDateFormat (FORMAT_NO_MINUTE)
           simpleDateFormatMap[FORMAT_NO_SECOND] =  SimpleDateFormat (FORMAT_NO_SECOND)
           simpleDateFormatMap[FORMAT_NO_MILLISECOND] =  SimpleDateFormat (FORMAT_NO_MILLISECOND)
       }
    }
}


/**
 * 获取指定时间格式化器
 *
 * @param formatStyle 时间格式
 * @return 时间格式化器
 */
private fun getSimpleDateFormat(formatStyle: String): SimpleDateFormat? {
    val dateFormat = DateFormat.simpleDateFormatMap[formatStyle]
    return if (Objects.nonNull(dateFormat)) {
        dateFormat
    } else SimpleDateFormat(formatStyle)
}

/**
 * 将 Date 格式时间转化为指定格式时间
 *
 * @param date        Date 格式时间
 * @param formatStyle 转化指定格式（如: yyyy-MM-dd HH:mm:ss）
 * @return 转化格式时间
 */
fun format(date: Date?, formatStyle: String): String? {
    return if (Objects.isNull(date)) {
        ""
    } else getSimpleDateFormat(formatStyle)!!.format(date)
}

/**
 * 将 Date 格式时间转化为 yyyy-MM-dd 格式时间
 *
 * @param date Date 格式时间
 * @return yyyy-MM-dd 格式时间（如：2022-06-17）
 */
fun formatDate(date: Date?): String? {
    return format(date, FORMAT_DATE)
}

/**
 * 将 Date 格式时间转化为 yyyy-MM-dd 格式时间
 *
 * @param date Date 格式时间
 * @return MM-dd 格式时间（如：06-17）
 */
fun formatDateMonth(date: Date?): String? {
    return format(date, FORMAT_DATE)
}

/**
 * 将 Date 格式时间转化为 yyyy-MM-dd HH:mm:ss 格式时间
 *
 * @param date Date 格式时间
 * @return yyyy-MM-dd HH:mm:ss 格式时间（如：2022-06-17 16:06:17）
 */
fun formatDateTime(date: Date?): String? {
    return format(date, FORMAT_SECOND)
}

/**
 * 将 Date 格式时间转化为 yyyy-MM-dd HH:mm:ss:SSS 格式时间
 *
 * @param date Date 格式时间
 * @return yyyy-MM-dd HH:mm:ss:SSS 格式时间（如：2022-06-17 16:06:17:325）
 */
fun formatDateTimeStamp(date: Date?): String? {
    return format(date, FORMAT_MILLISECOND)
}

/**
 * 将 yyyy-MM-dd 格式时间转化为 Date 格式时间
 *
 * @param dateString yyyy-MM-dd 格式时间（如：2022-06-17）
 * @return Date 格式时间
 */
fun parseDate(dateString: String): Date? {
    return parse(dateString, FORMAT_DATE)
}

/**
 * 将 yyyy-MM-dd HH:mm:ss 格式时间转化为 Date 格式时间
 *
 * @param dateTimeStr yyyy-MM-dd HH:mm:ss 格式时间（如：2022-06-17 16:06:17）
 * @return Date 格式时间
 */
fun parseDateTime(dateTimeStr: String): Date? {
    return parse(dateTimeStr, FORMAT_SECOND)
}

/**
 * 将 yyyy-MM-dd HH:mm:ss:SSS 格式时间转化为 Date 格式时间
 *
 * @param dateTimeStr yyyy-MM-dd HH:mm:ss:SSS 格式时间（如：2022-06-17 16:06:17）
 * @return Date 格式时间
 */
fun parseDateTimeStamp(dateTimeStampStr: String): Date? {
    return parse(dateTimeStampStr, FORMAT_MILLISECOND)
}

/**
 * 将字符串格式时间转化为 Date 格式时间
 *
 * @param dateString 字符串时间（如：2022-06-17 16:06:17）
 * @return formatStyle 格式内容
 * @return Date 格式时间
 */
fun parse(dateString: String, formatStyle: String): Date? {
    val s = getString(dateString)
    return if (s.isEmpty()) {
        null
    } else try {
        getSimpleDateFormat(formatStyle)!!.parse(dateString)
    } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
}

/**
 * 获取字符串有效内容
 *
 * @param s 字符串
 * @return 有效内容
 */
private fun getString(s: String): String {
    return if (Objects.isNull(s)) "" else s.trim { it <= ' ' }
}

/**
 * 获取一天的开始时间（即：0 点 0 分 0 秒 0 毫秒）
 *
 * @param date 指定时间
 * @return 当天的开始时间
 */
fun getDateStart(date: Date?): Date? {
    if (Objects.isNull(date)) {
        return null
    }
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar[Calendar.HOUR_OF_DAY] = 0
    calendar[Calendar.MINUTE] = 0
    calendar[Calendar.SECOND] = 0
    calendar[Calendar.MILLISECOND] = 0
    return calendar.time
}

/**
 * 获取一天的截止时间（即：23 点 59 分 59 秒 999 毫秒）
 *
 * @param date 指定时间
 * @return 当天的开始时间
 */
fun getDateEnd(date: Date?): Date? {
    if (Objects.isNull(date)) {
        return null
    }
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar[Calendar.HOUR_OF_DAY] = 23
    calendar[Calendar.MINUTE] = 59
    calendar[Calendar.SECOND] = 59
    calendar[Calendar.MILLISECOND] = 999
    return calendar.time
}

/**
 * 获取日期数字
 *
 * @param date 日期
 * @return 日期数字
 */
fun getDateNo(date: Date?): Int {
    return if (Objects.isNull(date)) {
        0
    } else Integer.valueOf(format(date, FORMAT_NO_DATE))
}

/**
 * 获取日期时间数字（到秒）
 *
 * @param date 日期
 * @return 日期数字
 */
fun getDateTimeNo(date: Date?): Long {
    return if (Objects.isNull(date)) {
        0L
    } else java.lang.Long.valueOf(format(date, FORMAT_NO_SECOND))
}

/**
 * 获取日期时间数字（到毫秒）
 *
 * @param date 日期
 * @return 日期数字
 */
fun getDateTimeStampNo(date: Date?): Long {
    return if (Objects.isNull(date)) {
        0L
    } else java.lang.Long.valueOf(format(date, FORMAT_NO_MILLISECOND))
}

/**
 * 获取星期几
 *
 * @param date 时间
 * @return 0（时间为空）， 1（周一）， 2（周二），3（周三），4（周四），5（周五），6（周六），7（周日）
 */
fun getWeek(date: Date?): Int {
    if (Objects.isNull(date)) {
        return 0
    }
    val calendar = Calendar.getInstance()
    calendar.time = date
    return getWeek(calendar)
}

/**
 * 获取星期几
 *
 * @param date 时间
 * @return 0（时间为空）， 1（周一）， 2（周二），3（周三），4（周四），5（周五），6（周六），7（周日）
 */
private fun getWeek(calendar: Calendar): Int {
    return when (calendar[Calendar.DAY_OF_WEEK]) {
        Calendar.MONDAY -> 1
        Calendar.TUESDAY -> 2
        Calendar.WEDNESDAY -> 3
        Calendar.THURSDAY -> 4
        Calendar.FRIDAY -> 5
        Calendar.SATURDAY -> 6
        Calendar.SUNDAY -> 7
        else -> 0
    }
}

/**
 * 获取该日期是今年的第几周（以本年的周一为第1周，详见下面说明）<br></br>
 *
 * 【说明】<br></br>
 * 比如 2022-01-01（周六）和 2022-01-02（周日）虽然在 2022 年里，但他们两天则属于 2021 年最后一周，<br></br>
 * 那么这两天不会算在 2022 年第 1 周里，此时会返回 0 ；而 2022 年第 1 周将从 2022-01-03（周一） 开始计算。<br></br>
 *
 * @param date 时间
 * @return -1（时间为空）， 0（为上个年的最后一周），其他数字（今年的第几周）
 */
fun getWeekOfYear(date: Date?): Int {
    if (Objects.isNull(date)) {
        return -1
    }
    val weeks = getWeekOfYearIgnoreLastYear(date)
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar[Calendar.MONTH] = Calendar.JANUARY
    calendar[Calendar.DAY_OF_MONTH] = 1
    val week = getWeek(calendar)
    return if (week == 1) {
        weeks
    } else weeks - 1
}

/**
 * 获取今年的第几周（以本年的1月1日为第1周第1天）<br></br>
 *
 * @param date 时间
 * @return -1（时间为空），其他数字（今年的第几周）
 */
fun getWeekOfYearIgnoreLastYear(date: Date?): Int {
    if (Objects.isNull(date)) {
        return -1
    }
    val calendar = Calendar.getInstance()
    calendar.time = date
    val days = calendar[Calendar.DAY_OF_YEAR]
    val weeks = days / 7
    // 如果是 7 的倍数，则表示恰好是多少周
    return if (days % 7 == 0) {
        weeks
    } else weeks + 1
    // 如果有余数，则需要再加 1
}

/**
 * 获取时间节点对象
 *
 * @param date 时间对象
 * @return DateNode
 */
fun getDateNode(date: Date): DateNode? {
    if (Objects.isNull(date)) {
        return null
    }
    val calendar = Calendar.getInstance()
    calendar.time = date
    val node = DateNode()
    node.time = format(date, FORMAT_MILLISECOND)
    node.year = calendar[Calendar.YEAR]
    node.month = calendar[Calendar.MONTH] + 1
    node.day = calendar[Calendar.DAY_OF_MONTH]
    node.hour = calendar[Calendar.HOUR_OF_DAY]
    node.minute = calendar[Calendar.MINUTE]
    node.second = calendar[Calendar.SECOND]
    node.millisecond = calendar[Calendar.MILLISECOND]
    node.week = getWeek(calendar)
    node.dayOfYear = calendar[Calendar.DAY_OF_YEAR]
    node.weekOfYear = getWeekOfYear(date)
    node.weekOfYearIgnoreLastYear = getWeekOfYearIgnoreLastYear(date)
    node.millisecondStamp = date.time
    node.secondStamp = node.millisecondStamp / 1000
    return node
}

/**
 * 日期变更
 *
 * @param date   指定日期
 * @param field  变更属性（如变更年份，则该值为 Calendar.DAY_OF_YEAR）
 * @param amount 变更大小（大于 0 时增加，小于 0 时减少）
 * @return 变更后的日期时间
 */
fun add(date: Date?, field: Int, amount: Int): Date? {
    if (Objects.isNull(date)) {
        return null
    }
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.add(field, amount)
    return calendar.time
}

/**
 * 指定日期加减年份
 *
 * @param date 指定日期
 * @param year 变更年份（大于 0 时增加，小于 0 时减少）
 * @return 变更年份后的日期
 */
fun addYear(date: Date?, year: Int): Date? {
    return add(date, Calendar.YEAR, year)
}

/**
 * 指定日期加减月份
 *
 * @param date  指定日期
 * @param month 变更月份（大于 0 时增加，小于 0 时减少）
 * @return 变更月份后的日期
 */
fun addMonth(date: Date?, month: Int): Date? {
    return add(date, Calendar.MONTH, month)
}

/**
 * 指定日期加减天数
 *
 * @param date 指定日期
 * @param day  变更天数（大于 0 时增加，小于 0 时减少）
 * @return 变更天数后的日期
 */
fun addDay(date: Date?, day: Int): Date? {
    return add(date, Calendar.DAY_OF_YEAR, day)
}

/**
 * 指定日期加减星期
 *
 * @param date 指定日期
 * @param week 变更星期数（大于 0 时增加，小于 0 时减少）
 * @return 变更星期数后的日期
 */
fun addWeek(date: Date?, week: Int): Date? {
    return add(date, Calendar.WEEK_OF_YEAR, week)
}

/**
 * 指定日期加减小时
 *
 * @param date 指定日期时间
 * @param hour 变更小时数（大于 0 时增加，小于 0 时减少）
 * @return 变更小时数后的日期时间
 */
fun addHour(date: Date?, hour: Int): Date? {
    return add(date, Calendar.HOUR_OF_DAY, hour)
}

/**
 * 指定日期加减分钟
 *
 * @param date   指定日期时间
 * @param minute 变更分钟数（大于 0 时增加，小于 0 时减少）
 * @return 变更分钟数后的日期时间
 */
fun addMinute(date: Date?, minute: Int): Date? {
    return add(date, Calendar.MINUTE, minute)
}

/**
 * 指定日期加减秒
 *
 * @param date   指定日期时间
 * @param second 变更秒数（大于 0 时增加，小于 0 时减少）
 * @return 变更秒数后的日期时间
 */
fun addSecond(date: Date?, second: Int): Date? {
    return add(date, Calendar.SECOND, second)
}

/**
 * 指定日期加减秒
 *
 * @param date   指定日期时间
 * @param minute 变更毫秒数（大于 0 时增加，小于 0 时减少）
 * @return 变更毫秒数后的日期时间
 */
fun addMillisecond(date: Date?, millisecond: Int): Date? {
    return add(date, Calendar.MILLISECOND, millisecond)
}

/**
 * 获取该日期所在周指定星期的日期
 *
 * @param date 日期所在时间
 * @return index 指定星期（1 - 7 分别对应星期一到星期天）
 */
fun getWeekDate(date: Date?, index: Int): Date? {
    if (index < WEEK_1_MONDAY || index > WEEK_7_SUNDAY) {
        return null
    }
    val week = getWeek(date)
    return addDay(date, index - week)
}

/**
 * 获取该日期所在周开始日期
 *
 * @param date 日期所在时间
 * @return 所在周开始日期
 */
fun getWeekDateStart(date: Date?): Date? {
    return getDateStart(getWeekDate(date, WEEK_1_MONDAY))
}

/**
 * 获取该日期所在周开始日期
 *
 * @param date 日期所在时间
 * @return 所在周开始日期
 */
fun getWeekDateEnd(date: Date?): Date? {
    return getWeekDateEnd(getWeekDate(date, WEEK_7_SUNDAY))
}

/**
 * 获取该日期所在周的所有日期（周一到周日）
 *
 * @param Date 日期
 * @return 该日照所在周的所有日期
 */
fun getWeekDateList(date: Date?): List<Date?> {
    if (Objects.isNull(date)) {
        return emptyList<Date>()
    }
    // 获取本周开始时间
    val weekFromDate = getWeekDateStart(date)
    // 获取本周截止时间
    val weekeEndDate = getWeekDateEnd(date)
    return getBetweenDateList(weekFromDate, weekeEndDate, true)
}

/**
 * 获取该日期所在周的所有日期（周一到周日）
 *
 * @param dateString
 * @return 该日照所在周的所有日期
 */
fun getWeekDateList(dateString: String): List<String?>? {
    val date = parseDate(dateString)
    return if (Objects.isNull(date)) {
        emptyList<String>()
    } else getDateStrList(getWeekDateList(date))
}

/**
 * 获取该日期所在月的所有日期
 *
 * @param dateString
 * @return 该日照所月的所有日期
 */
fun getMonthDateList(date: Date?): List<Date?> {
    if (Objects.isNull(date)) {
        return emptyList<Date>()
    }
    val monthDateStart = getMonthDateStart(date)
    val monthDateEnd = getMonthDateEnd(date)
    return getBetweenDateList(monthDateStart, monthDateEnd, true)
}

/**
 * 获取该日期所在月的所有日期
 *
 * @param dateString
 * @return 该日照所月的所有日期
 */
fun getMonthDateList(dateString: String): List<String?>? {
    val date = parseDate(dateString)
    return if (Objects.isNull(date)) {
        emptyList<String>()
    } else getDateStrList(getMonthDateList(date))
}

/**
 * 获取本日期所在月第一天
 *
 * @param date 日期
 * @return 本日期所在月第一天
 */
fun getMonthDateStart(date: Date?): Date? {
    if (Objects.isNull(date)) {
        return null
    }
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar[Calendar.DAY_OF_MONTH] = 1
    return getDateStart(calendar.time)
}

/**
 * 获取本日期所在月最后一天
 *
 * @param date 日期
 * @return 本日期所在月最后一天
 */
fun getMonthDateEnd(date: Date?): Date? {
    if (Objects.isNull(date)) {
        return null
    }
    val monthDateStart = getMonthDateStart(date)
    val nextMonthDateStart = getMonthDateStart(addMonth(monthDateStart, 1))
    return getDateEnd(addDay(nextMonthDateStart, -1))
}

/**
 * 获取两个日期相差的天数（以日期为单位计算，不以24小时制计算，详见下面说明）<br></br>
 *
 * 【说明】比如 2022-06-17 23:00:00 和 2022-06-17 01:00:00，两者虽然只相差 2 个小时，但也算相差 1 天 <br></br>
 *
 * @param date1 日期1
 * @param date2 日期2
 * @return 相差天数（若返回 -1，则至少有一个日期存在为空，此时不能进行比较）
 */
fun countBetweenDays(date1: Date?, date2: Date?): Int {
    if (Objects.isNull(date1) || Objects.isNull(date2)) {
        return -1
    }
    // 获取两个日期 0 点 0 时 0 分 0 秒 0 毫秒时的时间戳（毫秒级）
    val t1 = getDateStart(date1)!!.time
    val t2 = getDateStart(date2)!!.time
    // 相差天数 = 相差的毫秒数 / 一天的毫秒数
    return (Math.abs(t1 - t2) / DAY_MILLISECONDS).toInt()
}

/**
 * 获取两个日期之间的所有日期
 *
 * @param date1 日期1
 * @param date2 日期2
 * @return 两个日期之间的所有日期的开始时间
 */
fun getBetweenDateList(date1: Date?, date2: Date?): List<Date?>? {
    return getBetweenDateList(date1, date2, false)
}

/**
 * 获取两个日期之间的所有日期
 *
 * @param date1 日期1
 * @param date2 日期2
 * @return 两个日期之间的所有日期的开始时间
 */
fun getBetweenDateList(date1: Date?, date2: Date?, isContainParams: Boolean): List<Date?> {
    if (Objects.isNull(date1) || Objects.isNull(date2)) {
        return emptyList<Date>()
    }
    // 确定前后日期
    var fromDate = date1
    var toDate = date2
    if (date2!!.before(date1)) {
        fromDate = date2
        toDate = date1
    }
    // 获取两个日期每天的开始时间
    val from = getDateStart(fromDate)
    val to = getDateStart(toDate)
    // 获取日期，开始循环
    val dates: MutableList<Date?> = java.util.ArrayList()
    if (isContainParams) {
        dates.add(from)
    }
    var date = from
    var isBefore = true
    while (isBefore) {
        date = addDay(date, 1)
        isBefore = date!!.before(to)
        if (isBefore) {
            dates.add(getDateStart(date))
        }
    }
    if (isContainParams) {
        dates.add(to)
    }
    return dates
}

/**
 * 获取两个日期之间的所有日期
 *
 * @param dateString1 日期1（如：2022-06-20）
 * @param dateString2 日期2（如：2022-07-15）
 * @return 两个日期之间的所有日期（不包含参数日期）
 */
fun getBetweenDateList(dateString1: String, dateString2: String): List<String?>? {
    return getBetweenDateList(dateString1, dateString2, false)
}

/**
 * 获取两个日期之间的所有日期
 *
 * @param dateString1     日期1（如：2022-06-20）
 * @param dateString2     日期2（如：2022-07-15）
 * @param isContainParams 是否包含参数的两个日期
 * @return 两个日期之间的所有日期的开始时间
 */
fun getBetweenDateList(
    dateString1: String,
    dateString2: String,
    isContainParams: Boolean
): List<String?>? {
    val date1 = parseDate(dateString1)
    val date2 = parseDate(dateString2)
    val dates = getBetweenDateList(date1, date2, isContainParams)
    return getDateStrList(dates)
}

/**
 * List<Date> 转 List<String>
 *
 * @param dates 日期集合
 * @return 日期字符串集合
</String></Date> */
fun getDateStrList(dates: List<Date?>): List<String?>? {
    if (dates.isEmpty()) {
        return emptyList<String>()
    }
    val dateList: MutableList<String?> = ArrayList()
    for (date in dates) {
        dateList.add(formatDate(date))
    }
    return dateList
}

class DateNode {
    /** 年  */
    var year = 0

    /** 月  */
    var month = 0

    /** 日  */
    var day = 0

    /** 时  */
    var hour = 0

    /** 分  */
    var minute = 0

    /** 秒  */
    var second = 0

    /** 毫秒  */
    var millisecond = 0

    /** 星期几（ 1 - 7 对应周一到周日）  */
    var week = 0

    /** 当年第几天  */
    var dayOfYear = 0

    /** 当年第几周（本年周 1 为第 1 周，0 则表示属于去年最后一周）  */
    var weekOfYear = 0

    /** 当年第几周（本年周 1 为第 1 周，0 则表示属于去年最后一周）  */
    var weekOfYearIgnoreLastYear = 0

    /** 时间戳（秒级）  */
    var secondStamp: Long = 0

    /** 时间戳（毫秒级）  */
    var millisecondStamp: Long = 0

    /** 显示时间  */
    var time: String? = null
}