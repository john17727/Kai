package com.john.topheadlines.datasource.network.utils

import com.john.mvi.data.util.EntityMapper
import com.john.topheadlines.domain.model.Source
import com.john.topheadlines.domain.network.SourceNetworkEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SourceNetworkMapper
@Inject
constructor(): EntityMapper<SourceNetworkEntity, Source> {

    override fun mapFromEntity(entity: SourceNetworkEntity): Source {
        return Source(
            entity.id,
            entity.name
        )
    }

    override fun mapToEntity(model: Source): SourceNetworkEntity {
        return SourceNetworkEntity(
            model.id,
            model.name
        )
    }
}