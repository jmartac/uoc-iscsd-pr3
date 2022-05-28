package edu.uoc.epcsd.showcatalog.unit;

import edu.uoc.epcsd.showcatalog.domain.Show;
import edu.uoc.epcsd.showcatalog.domain.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ShowUnitTest {

    @Test
    public void whenShowCancel_thenShowStatusIsCancelled() {
        Show show = new Show();
        show.cancel();

        assertThat(show.getStatus())
                .isEqualTo(Status.CANCELLED);
    }

}
