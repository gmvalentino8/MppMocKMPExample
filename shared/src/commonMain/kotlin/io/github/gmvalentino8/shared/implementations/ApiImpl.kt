package io.github.gmvalentino8.shared.implementations

import io.github.gmvalentino8.shared.interfaces.Api
import io.github.gmvalentino8.shared.models.ApiModel

class ApiImpl : Api {
    override fun getRequest(): ApiModel = ApiModel(data = 0L)
    override fun postRequest(body: ApiModel) = Unit
}
