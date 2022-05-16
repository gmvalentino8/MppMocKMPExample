package io.github.gmvalentino8.tests

import io.github.gmvalentino8.shared.models.ApiModel
import io.github.gmvalentino8.shared.models.RepositoryModel
import io.github.gmvalentino8.shared.implementations.RepositoryImpl
import io.github.gmvalentino8.shared.interfaces.Api
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks

class RepositoryTestsWithMocks : TestsWithMocks() {
    override fun setUpMocks() = injectMocks(mocker)

    @Mock lateinit var api: Api
    private val repository by withMocks { RepositoryImpl(api = api) }

    @BeforeTest
    fun setup() {
        every { api.getRequest() } returns ApiModel(data = 0L)
        every { api.postRequest(ApiModel(data = isAny())) } returns Unit
    }

    @Test
    fun testGetData() {
        val result = repository.getData()
        verify { api.getRequest() }
        assertEquals(result.data, 0)
    }

    @Test
    fun testPostData() {
        repository.postData(model = RepositoryModel(data = 0))
        verify { api.postRequest(body = ApiModel(0L)) }
    }
}
