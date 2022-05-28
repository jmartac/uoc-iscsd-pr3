package edu.uoc.epcsd.showcatalog.unit;

import edu.uoc.epcsd.showcatalog.domain.Show;
import edu.uoc.epcsd.showcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.showcatalog.domain.repository.ShowRepository;
import edu.uoc.epcsd.showcatalog.domain.service.CatalogService;
import edu.uoc.epcsd.showcatalog.domain.service.CatalogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static edu.uoc.epcsd.showcatalog.unit.CatalogServiceUnitTest.CatalogServiceTestConfig.showRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(SpringExtension.class)
public class CatalogServiceUnitTest {

    @TestConfiguration
    static class CatalogServiceTestConfig {
        @MockBean
        protected static ShowRepository showRepository;

        @MockBean
        private static CategoryRepository categoryRepository;

        @MockBean
        private static KafkaTemplate<String, Show> kafkaTemplate;

        @Bean
        public CatalogService catalogService() {
            return new CatalogServiceImpl(showRepository, categoryRepository, kafkaTemplate);
        }
    }

    @Autowired
    private CatalogService catalogService;

    @BeforeEach
    public void setUp() {
        Show show = new Show();
        show.setId(1L);
        Show show2 = new Show();
        show2.setId(2L);
        Show show3 = new Show();
        show3.setId(3L);

        Mockito.when(showRepository.findShowById(1L)).thenReturn(Optional.of(show));
        Mockito.when(showRepository.findShowById(2L)).thenReturn(Optional.of(show2));
        Mockito.when(showRepository.findShowById(3L)).thenReturn(Optional.of(show3));
    }

    @Test
    public void whenValidFindShowById_thenShowShouldBeFound() {
        Long id = 2L;
        Optional<Show> show = catalogService.findShowById(id);

        assertThat(show.isPresent()).isTrue();
        assertThat(show.get().getId()).isEqualTo(id);

        verify(showRepository, times(1))
                .findShowById(Mockito.anyLong());
    }

    @Test
    public void whenInvalidFindShowById_thenShowShouldNotBeFound() {
        Long id = 5L;
        Optional<Show> show = catalogService.findShowById(id);

        assertThat(show.isEmpty()).isTrue();

        verify(showRepository, times(1))
                .findShowById(Mockito.anyLong());
    }
}
