package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.androidversionrepository

import com.lukaslechner.coroutineusecasesonandroid.mock.mockAndroidVersions
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase14.AndroidVersionDao
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase14.AndroidVersionEntity
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase14.mapToEntityList

class FakeDatabase: AndroidVersionDao {

    var insertedIntoDb = false

    override suspend fun getAndroidVersions(): List<AndroidVersionEntity> {
        return mockAndroidVersions.mapToEntityList()
    }

    override suspend fun insert(androidVersionEntity: AndroidVersionEntity) {
        insertedIntoDb = true
    }

    override suspend fun clear() {

    }
}