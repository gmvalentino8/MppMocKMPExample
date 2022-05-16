package io.github.gmvalentino8.shared.interfaces

import io.github.gmvalentino8.shared.models.RepositoryModel

interface Repository {
    fun getData(): RepositoryModel
    fun postData(model: RepositoryModel)
}
