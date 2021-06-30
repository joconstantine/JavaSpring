package guru.sfg.brewery.web.controllers.api;

import guru.sfg.brewery.domain.Beer;
import guru.sfg.brewery.repositories.BeerRepository;
import guru.sfg.brewery.web.controllers.BaseIT;
import guru.sfg.brewery.web.model.BeerStyleEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BeerRestControllerIT extends BaseIT {
    @Autowired
    BeerRepository beerRepository;

    @DisplayName("Delete Tests")
    @Nested
    class DeleteTests {
        public Beer beerToDelete() {
            Random rand = new Random();

            return beerRepository.saveAndFlush(Beer.builder()
                .beerName("Delete Me Beer")
                .beerStyle(BeerStyleEnum.IPA)
                .minOnHand(12)
                .quantityToBrew(200)
                .upc(String.valueOf(rand.nextInt(999999999)))
                .build());
        }

        @Test
        void deleteBeerUrl() throws Exception {
            mockMvc.perform(delete("/api/v1/beer/" + beerToDelete().getId())
                    .param("apiKey", "spring").param("apiSecret", "guru"))
                    .andExpect(status().isOk());
        }

        @Test
        void deleteBeerBadCredsUrl() throws Exception {
            mockMvc.perform(delete("/api/v1/beer/" + beerToDelete().getId())
                    .param("apiKey", "spring").param("apiSecret", "guruXXXXX"))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void deleteBeerBadCreds() throws Exception {
            mockMvc.perform(delete("/api/v1/beer/" + beerToDelete().getId())
                    .header("Api-Key", "spring").header("Api-Secret", "guruXXXX"))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void deleteBeer() throws Exception {
            mockMvc.perform(delete("/api/v1/beer/" + beerToDelete().getId())
                    .header("Api-Key", "spring").header("Api-Secret", "guru"))
                    .andExpect(status().isOk());
        }

        @Test
        void deleteBeerHttpAdminBasic() throws Exception {
            mockMvc.perform(delete("/api/v1/beer/" + beerToDelete().getId())
                    .with(httpBasic("spring", "guru")))
                    .andExpect(status().is2xxSuccessful());
        }

        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("guru.sfg.brewery.web.controllers.BeerControllerIT#getStreamNotAdmin")
        void deleteBeerHttpBasicNonAdminRole(String user, String pwd) throws Exception {
            mockMvc.perform(delete("/api/v1/beer/" + beerToDelete().getId())
                    .with(httpBasic(user, pwd)))
                    .andExpect(status().isForbidden());
        }

        @Test
        void deleteBeerHttpBasicNoAuth() throws Exception {
            mockMvc.perform(delete("/api/v1/beer/" + beerToDelete().getId()))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void findBeersNoAuth() throws Exception {
            mockMvc.perform(get("/api/v1/beer/"))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void findBeersByIdNoAuth() throws Exception {
            mockMvc.perform(get("/api/v1/beer/" + beerToDelete().getId()))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void findBeersByUpc() throws Exception {
            mockMvc.perform(get("/api/v1/beerUpc/0631234200036"))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void findBeersFormAdmin() throws Exception {
            mockMvc.perform(get("/beers").param("beerName", "")
                    .with(httpBasic("spring", "guru")))
                    .andExpect(status().isOk());
        }
    }
}
