package com.arni.domain.repository

import com.arni.data.base.DataStatus
import com.arni.domain.base.BaseRepository
import com.arni.remote.Api
import com.arni.remote.model.response.DictionaryResponse

class DictionaryRepositoryRemote(private val api: Api) : BaseRepository {
    suspend fun getDictionary(): DataStatus<DictionaryResponse> = handleRequest { api.getRefs() }

}
