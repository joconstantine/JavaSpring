package guru.sfg.brewery.web.controllers.api;

import guru.sfg.brewery.web.controllers.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BreweryRestControllerIT extends BaseIT{

    @Test
    void breweriesHttpAdminBasic() throws Exception {
        mockMvc.perform(get("/brewery/api/v1/breweries")
                .with(httpBasic("spring", "guru")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void breweriesHttpUserBasic() throws Exception {
        mockMvc.perform(get("/brewery/api/v1/breweries")
                .with(httpBasic("user", "password")))
                .andExpect(status().isForbidden());
    }

    @Test
    void breweriesHttpCustomerBasic() throws Exception {
        mockMvc.perform(get("/brewery/api/v1/breweries")
                .with(httpBasic("scott", "tiger")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void breweriesNoAuth() throws Exception {
        mockMvc.perform(get("/brewery/api/v1/breweries"))
                .andExpect(status().isUnauthorized());
    }
}
