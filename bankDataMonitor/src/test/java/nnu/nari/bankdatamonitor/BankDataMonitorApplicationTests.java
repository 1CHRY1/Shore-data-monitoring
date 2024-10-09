package nnu.nari.bankdatamonitor;

import nnu.nari.bankdatamonitor.model.info.GNSSInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankDataMonitorApplicationTests {

    @Test
    void contextLoads() {
        GNSSInfo gnssInfo = GNSSInfo.GNSSInfoBuilder().build();
        System.out.println(gnssInfo.getNotes());
    }

}
