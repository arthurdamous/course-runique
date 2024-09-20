package com.plcoding.core.data.data.di

import com.plcoding.core.data.data.networking.HttpClientFactory
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory().build()
    }
}