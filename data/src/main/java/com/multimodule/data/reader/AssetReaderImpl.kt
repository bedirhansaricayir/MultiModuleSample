package com.multimodule.data.reader

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.multimodule.data.model.PositionAssetModel
import com.multimodule.data.model.SatelliteDetailAssetModel
import com.multimodule.data.model.SatelliteListAssetModel
import javax.inject.Inject


/**
 * Created by bedirhansaricayir on 7.12.2023.
 */
class AssetReaderImpl @Inject constructor(private val context: Context?, private val gson: Gson?) : AssetReader {

    private inline fun <reified T> readFile(fileName: String): T? {
        val jsonFileString = context?.assets?.open(fileName)?.bufferedReader().use { it?.readText() }
        return gson?.fromJson(jsonFileString, object : TypeToken<T>() {}.type)
    }
    override fun readPositions(): PositionAssetModel? = readFile("positions.json")

    override fun readSatelliteDetail(): List<SatelliteDetailAssetModel>? = readFile("satellite_detail.json")

    override fun readSatelliteList(): List<SatelliteListAssetModel>? = readFile("satellite_list.json")
}