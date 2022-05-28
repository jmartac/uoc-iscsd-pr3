package edu.uoc.epcsd.showcatalog.unit;

import edu.uoc.epcsd.showcatalog.domain.Show;
import edu.uoc.epcsd.showcatalog.domain.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class ShowUnitTest {

    @Test
    public void whenShowCancel_thenShowStatusIsCancelled() {
        Show show = new Show();
        show.cancel();

        assertThat(show.getStatus())
                .isEqualTo(Status.CANCELLED);
    }

}
