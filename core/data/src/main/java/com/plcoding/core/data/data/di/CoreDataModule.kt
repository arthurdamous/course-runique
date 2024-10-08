package com.plcoding.core.data.data.di

import com.plcoding.core.data.data.auth.EncryptedSessionStorage
import com.plcoding.core.data.data.networking.HttpClientFactory
import com.plcoding.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }

    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}