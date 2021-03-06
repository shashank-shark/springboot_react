package owntryspringboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarRestAuthTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAuthenticationOfJWTTokenOK() {
        try {
            this.mockMvc.perform(post("/login").content("{\"username\":\"user\",\"password\":\"user\"}"))
                    .andDo(print()).andExpect(status().isOk());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void testAuthenticationOfJWTToken4XX() {
        try {
            this.mockMvc.perform(post("/login").content("{\"username\":\"user\",\"password\":\"incorrectPassword\"}"))
                    .andDo(print()).andExpect(status().is4xxClientError());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
