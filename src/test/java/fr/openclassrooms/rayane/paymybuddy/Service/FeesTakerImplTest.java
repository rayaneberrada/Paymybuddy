package fr.openclassrooms.rayane.paymybuddy.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class FeesTakerImplTest {

    private FeesTaker feesTaker = new FeesTakerImpl();

    @Test
    void feesDeducedTest() {
        //GIVEN
        int payment = 50;
        double excepected = payment * 0.95;

        //WHEN
        double moneyLeft = feesTaker.deduceFees(payment);

        //THEN
        assertThat(excepected).isEqualTo(moneyLeft);
    }

}
