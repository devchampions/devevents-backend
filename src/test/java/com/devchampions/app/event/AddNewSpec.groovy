package com.devchampions.app.event

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
class AddNewSpec extends Specification {

    @Autowired
    WebApplicationContext webapp;

    MockMvc mvc;

    def setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webapp).build()
    }

    def "adding a new event"() {
        given:
        def body = """
        {
            "name": "whatever"
        }
        """

        when:
        def r = mvc.perform(post("/events").contentType(MediaType.APPLICATION_JSON).content(body))

        then:
        r.andExpect(status().isCreated())
    }

}
