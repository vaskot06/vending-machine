package com.example.vendingmachine.enums;

import java.math.BigDecimal;

public enum Coins {

        POINT_TEN(BigDecimal.valueOf(0.10)),
        POINT_TWENTY(BigDecimal.valueOf(0.20)),
        POINT_FIFTY(BigDecimal.valueOf(0.50)),
        ONE(BigDecimal.valueOf(1.0)),
        TWO(BigDecimal.valueOf(2.0));

        private final BigDecimal value;

        Coins(BigDecimal value) {
            this.value = value;
        }

        public BigDecimal getValue() {
            return value;
        }

}
