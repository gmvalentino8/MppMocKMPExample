package io.github.gmvalentino8.tests

import io.github.gmvalentino8.shared.implementations.RepositoryImpl
import io.github.gmvalentino8.shared.interfaces.Api
import io.github.gmvalentino8.shared.interfaces.MockApi
import io.github.gmvalentino8.shared.models.ApiModel
import io.github.gmvalentino8.shared.models.RepositoryModel
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks

@UsesMocks(Api::class)
class RepositoryTestsUseMocks {
    private val mocker = Mocker()
    private val api = MockApi(mocker)
    private val repository = RepositoryImpl(api = api)

    @BeforeTest
    fun setup() {
        mocker.every { api.getRequest() } returns ApiModel(data = 0L)
        mocker.every { api.postRequest(ApiModel(data = isAny())) } returns Unit
    }

    @Test
    fun testGetData() {
        val result = repository.getData()
        mocker.verify { api.getRequest() }
        assertEquals(result.data, 0)
    }

    @Test
    fun testPostData() {
        repository.postData(model = RepositoryModel(data = 0))
        mocker.verify { api.postRequest(body = ApiModel(0L)) }
    }
}
