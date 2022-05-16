package io.github.gmvalentino8.shared.implementations

import io.github.gmvalentino8.shared.models.ApiModel
import io.github.gmvalentino8.shared.models.RepositoryModel
import io.github.gmvalentino8.shared.interfaces.Api
import io.github.gmvalentino8.shared.interfaces.Repository

class RepositoryImpl(private val api: Api) : Repository {
    override fun getData(): RepositoryModel {
        return RepositoryModel(api.getRequest().data.toInt())
    }

    override fun postData(model: RepositoryModel) {
        return api.postRequest(ApiModel(model.data.toLong()))
    }
}
