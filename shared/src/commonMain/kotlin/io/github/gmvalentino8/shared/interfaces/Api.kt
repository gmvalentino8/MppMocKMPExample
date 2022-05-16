package io.github.gmvalentino8.shared.interfaces

import io.github.gmvalentino8.shared.models.ApiModel

interface Api {
    fun getRequest(): ApiModel
    fun postRequest(body: ApiModel)
}
