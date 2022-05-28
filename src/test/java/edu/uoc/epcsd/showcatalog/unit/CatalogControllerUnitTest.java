package edu.uoc.epcsd.showcatalog.unit;

import edu.uoc.epcsd.showcatalog.application.rest.CatalogRESTController;
import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.service.CatalogService;
import edu.uoc.epcsd.showcatalog.infrastructure.repository.jpa.SpringDataCategoryRepository;
import edu.uoc.epcsd.showcatalog.infrastructure.repository.jpa.SpringDataShowRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CatalogRESTController.class)
public class CatalogControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SpringDataShowRepository springDataShowRepository;

    @MockBean
    private SpringDataCategoryRepository springDataCategoryRepository;

    @MockBean
    private CatalogService catalogService;

    @Test
    public void givenCategories_whenFindAllCategories_thenReturnValidCategories() throws Exception {
        Category c1 = new Category();
        c1.setId(1L);
        c1.setName("Music");
        Category c2 = new Category();
        c2.setId(2L);
        c2.setName("Concerts");
        Category c3 = new Category();
        c3.setId(3L);
        c3.setName("Movies");

        List<Category> categories = Arrays.asList(c1, c2, c3);

        given(catalogService.findAllCategories()).willReturn(categories);

        mvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Music")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Concerts")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].name", is("Movies")));

        verify(catalogService, times(1)).findAllCategories();
    }
}
