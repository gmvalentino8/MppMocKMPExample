package io.github.gmvalentino8.tests

import io.github.gmvalentino8.shared.implementations.RepositoryImpl
import io.github.gmvalentino8.shared.interfaces.Api
import io.github.gmvalentino8.shared.interfaces.MockApi
import io.github.gmvalentino8.shared.models.ApiModel
import io.github.gmvalentino8.shared.models.RepositoryModel
import io.github.gmvalentino8.shared.models.fakeApiModel
import io.github.gmvalentino8.shared.models.fakeRepositoryModel
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import org.kodein.mock.Mocker
import org.kodein.mock.UsesFakes
import org.kodein.mock.UsesMocks

@UsesMocks(Api::class)
@UsesFakes(ApiModel::class, RepositoryModel::class)
class RepositoryTestsUseMocksAndFakes {
    private val mocker = Mocker()
    private val api = MockApi(mocker)
    private val apiModel = fakeApiModel()
    private val repositoryModel = fakeRepositoryModel()
    private val repository = RepositoryImpl(api = api)

    @BeforeTest
    fun setup() {
        mocker.every { api.getRequest() } returns apiModel
        mocker.every { api.postRequest(apiModel) } returns Unit
    }

    @Test
    fun testGetData() {
        val result = repository.getData()
        mocker.verify { api.getRequest() }
        assertEquals(result.data, apiModel.data.toInt())
    }

    @Test
    fun testPostData() {
        repository.postData(model = repositoryModel)
        mocker.verify { api.postRequest(body = apiModel) }
    }
}
