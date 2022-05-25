package edu.uoc.epcsd.showcatalog;

import edu.uoc.epcsd.showcatalog.domain.Show;
import edu.uoc.epcsd.showcatalog.domain.Status;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ShowUnitTest {

    @Test
    public void whenShowCancel_thenShowStatusIsCancelled() {
        Show show = new Show();
        show.cancel();

        assertThat(show.getStatus())
                .isEqualTo(Status.CANCELLED);
    }

}
