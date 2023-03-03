package com.evajj.ktbase.util

import org.checkerframework.checker.units.qual.K
import java.util.*

/**
 * Author:wenjunjie
 * Date:2023/2/10
 * Time:下午5:46
 * Description:
 **/
fun <K, V> safeMap(source: Map<K, V>?): MutableMap<K, V> =
    if (source.isNullOrEmpty()) source!!.toMutableMap() else mutableMapOf()

fun <T> safeIterator(source: Iterator<T>?): Iterator<T> =
    Optional.ofNullable(source).orElse(ArrayList<T>().iterator()) as Iterator<T>

fun <T> safeIterable(source: Iterable<T>): Iterable<T> =
    Optional.ofNullable(source).orElse(ArrayList()) as Iterable<T>


fun <T> safeCollection(source: Collection<T>?): Collection<T> =
    Optional.ofNullable(source).orElse(ArrayList()) as Collection<T>

fun <T> safeList(source: List<T>?): List<T> =
    Optional.ofNullable(source).orElse(ArrayList()) as List<T>

fun <T> safeSet(source: Set<T>?): Set<T> =
    Optional.ofNullable(source).orElse(HashSet()) as Set<T>

fun safeString(source: String?): String =
    Optional.ofNullable(source).orElse("") as String

fun safeString(source: String?, defaultStr: String): String =
    Optional.ofNullable(source).orElse(defaultStr) as String
